package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.encog.neural.networks.BasicNetwork;

import imports.Normalize;
import types.HistoricalData;
import types.Data.enumAttributesOfData;

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
			Normalize normal = new Normalize();
			normal.normalizeDatas(normalData, mview.getMargin());
			
			//Neural Network
			BasicNetwork network = new BasicNetwork();
			
			
			
			
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
