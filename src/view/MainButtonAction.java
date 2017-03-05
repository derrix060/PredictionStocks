package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.encog.neural.networks.BasicNetwork;

import factories.NetworkFactory;
import model.Data.enumAttributesOfData;
import model.HistoricalData;
import model.NeuralNetwork;
import model.Normalizer;
import model.Trainer;

public class MainButtonAction implements ActionListener {
	private final MainView mview;
	public MainButtonAction(MainView view) { 
		mview = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try{
			//checks
			checkDates();
			checkStockAtribute();
			
			ArrayList<enumAttributesOfData> attr = mview.getAtributes();
			//datas
			HistoricalData normalData = new HistoricalData(mview.getStock(), mview.getFrom(), mview.getTo(), mview.getDataInterval(), attr);
			
			//Normalize
			Normalizer normal = new Normalizer(mview.getMargin());
			normal.normalizeDatas(normalData);
			
			HistoricalData normalTrainingData = normalData.createTrainHistoricalData(attr, mview.getToTraining());
			
			//Neural Network
			BasicNetwork network = NetworkFactory.newNetwork(mview.getLayers());
			
			NeuralNetwork net = new NeuralNetwork(network, attr, mview.getDataInterval(), mview.getStock());
			//net.save("TesteNN");
			
			//Train
			Trainer.train(network, normalTrainingData, attr, mview.getRule(), mview.getMaxIteration(), mview.getMinError(), normal);
			
			//EncogDirectoryPersistence.saveObject(new File("network.eg"), network);
			
			//Create NN data
			HistoricalData nnData = Trainer.createNNHistoricalData(network, normalData, attr, normal, mview.getToTraining());
			
			//Denormalize Data
			normal.denormalizeDatas(normalData);
			
			ResultView rv = new ResultView(nnData, normalData, normal, mview);
			rv.setVisible(true);
		
			mview.setVisible(false);
		}
		catch (Exception ex){
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, ex.getClass() + "\n\n" +  ex.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
		}
		

	}
	
	private void checkDates() throws Exception{
		
		//Data Section
			if(mview.getFrom().after(mview.getTo())) 
				throw new Exception("In extract data section, 'from' cannot be after than 'to'!");
		
		//Training Section
			if(mview.getToTraining().after(mview.getTo()))
				throw new Exception("Training 'to' cannot be after than Extract data 'to'!");
			
	}
	
	private void checkStockAtribute() throws Exception{
		
		//Check if some attribute of stock is checked
		if(!mview.isClosePrice() && !mview.isHighPrice() && !mview.isLowPrice() && !mview.isOpenPrice() && !mview.isVolume()) 
			throw new Exception ("You must select at least one attribute of stock!");
		
	}
	
}
