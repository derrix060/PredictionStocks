package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
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
			panel.getView().progressBar.setVisible(true);
			Trainer trainer = new Trainer(panel.getMargin(), panel.getInferiorLimit(), panel.getSuperiorLimit());
			
			ArrayList<Double> errors = trainer.train(nn, panel.getLearningRule(), panel.getMaxIteration(), panel.getMaxError(), panel.getFrom(), panel.getTo());
			panel.populateGraph(errors);
			
			JOptionPane.showMessageDialog(null, "Network trained with success!");
			
		} catch (UnknownHostException ex){
			JOptionPane.showMessageDialog(null, "Error! - Network not fouded! ");
			ex.printStackTrace();
			
		} catch (NullPointerException nEx){
			JOptionPane.showMessageDialog(null, "Error! - Please select dates!");
		} catch (FileNotFoundException fEx){
			JOptionPane.showMessageDialog(null, "Error! - Don't exist data from selected dates!");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error! - " + e.getMessage());
			e.printStackTrace();
		}
		
		panel.getView().progressBar.setVisible(false);
	}

}
