package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.encog.neural.networks.BasicNetwork;

import imports.Normalize;
import process.NetworkFactory;
import types.HistoricalData;
import types.Trainer;
import types.Data.enumAttributesOfData;

public class MainButtonAction implements ActionListener {
	private final MainView mview;
	public MainButtonAction(MainView view) { 
		mview = view;
	}

	@SuppressWarnings("unused")
	@Override
	public void actionPerformed(ActionEvent e) {
		try{
			//checks
			checkDates();
			checkStockAtribute();
			
			ArrayList<enumAttributesOfData> attr = mview.getAtributes();
			//datas
			HistoricalData normalData = new HistoricalData(mview.getStock(), mview.getFrom(), mview.getTo(), mview.getDataInterval(), attr);
			double[][] input = normalData.toInput(attr);
			double[][] out = normalData.toIdealOutput(attr);
			
			Normalize normal = new Normalize(mview.getMargin());
			normal.normalizeDatas(normalData);
			
			//Neural Network
			BasicNetwork network = NetworkFactory.newNetwork(mview.getLayers());
			
			
			//Train
			Trainer.train(network, normalData, attr, mview.getRule(), mview.getMaxIteration(), mview.getMinError(), normal);

			
			JOptionPane.showMessageDialog(null, "ok");
		}
		catch (Exception ex){
			JOptionPane.showMessageDialog(null, ex.getClass() + "\n\n" +  ex.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
		}
		

	}
	
	private void checkDates() throws Exception{
		
		//Data Section
			if(mview.getFrom().after(mview.getTo())) throw new Exception("In extract data section, 'from' cannot be after than 'to'!");
		
		//Training Section
			/*
			if(mview.getFromTraining().before(mview.getFrom())) 
				throw new Exception("Training 'from' cannot be before than Extract data 'from'!");
			
			if(mview.getToTraining().after(mview.getTo())) 
				throw new Exception("Training 'to' cannot be after than Extract data 'to'!");
			
			if(mview.getFromTraining().after(mview.getTo())) 
				throw new Exception("Training 'from' cannot be after than Extract data 'to'!");
			
			if(mview.getFromTraining().after(mview.getToTraining())) 
				throw new Exception("In training section, 'from' cannot be after than 'to'!");
			*/
	}
	
	private void checkStockAtribute() throws Exception{
		
		//Check if some attribute of stock is checked
		if(!mview.isClosePrice() && !mview.isHighPrice() && !mview.isLowPrice() && !mview.isOpenPrice() && !mview.isVolume()) 
			throw new Exception ("You must select at least one attribute of stock!");
		
	}
	
	
}
