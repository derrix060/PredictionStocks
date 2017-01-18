package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class MainButtonAction implements ActionListener {
	private final MainView mview;
	public MainButtonAction(MainView view) { 
		mview = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try{
			checkDates();
			JOptionPane.showMessageDialog(null, "ok");
		}
		catch (Exception ex){
			JOptionPane.showMessageDialog(null, ex.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
		}
		

	}
	
	private void checkDates() throws Exception{
		
		//Data Section
			if(mview.getFrom().after(mview.getTo())) throw new Exception("In extract data section, 'from' cannot be after than 'to'!");
		
		//Training Section
			if(mview.getFromTraining().before(mview.getFrom())) 
				throw new Exception("Training 'from' cannot be before than Extract data 'from'!");
			
			if(mview.getToTraining().after(mview.getTo())) 
				throw new Exception("Training 'to' cannot be after than Extract data 'to'!");
			
			if(mview.getFromTraining().after(mview.getTo())) 
				throw new Exception("Training 'from' cannot be after than Extract data 'to'!");
			
			if(mview.getFromTraining().after(mview.getToTraining())) 
				throw new Exception("In training section, 'from' cannot be after than 'to'!");
	
	}
	
	private void checkStockAtribute(){
		
	}

}
