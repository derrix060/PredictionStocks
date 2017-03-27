package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import model.NeuralNetwork;
import model.Trainer;
import view.TrainPanel;

public class TrainNNBtnAction implements ActionListener {
	
	TrainPanel panel;
	
	public TrainNNBtnAction(TrainPanel trainPanel) {
		this.panel = trainPanel;
	}

	@Override
	public void actionPerformed(ActionEvent ae){
		NeuralNetwork nn = panel.getView().getActiveNN();
		
		if(nn==null){
			JOptionPane.showMessageDialog(null, "Please choose your NN!!");
			return;
		}
		
		
		
		try {
			Trainer trainer = new Trainer(panel.getMargin(), panel.getInferiorLimit(), panel.getSuperiorLimit());
			
			ArrayList<Double> errors = trainer.train(nn, panel.getLearningRule(), panel.getMaxIteration(), panel.getMaxError(), panel.getFrom(), panel.getTo());
			panel.populateGraph(errors);
			
			JOptionPane.showMessageDialog(null, "Network trained with success!");
			
		} catch (UnknownHostException ex){
			JOptionPane.showMessageDialog(null, "Error! - Network not fouded! ");
			ex.printStackTrace();
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error! - " + e.getMessage());
			e.printStackTrace();
		} 
	}

}
