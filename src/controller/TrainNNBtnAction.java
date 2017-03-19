package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		NeuralNetwork nn = panel.getView().getActiveNN();
		
		Trainer trainer = new Trainer(panel.getMargin(), panel.getInferiorLimit(), panel.getSuperiorLimit());
		
		
		try {
			trainer.train(nn, panel.getLearningRule(), panel.getMaxIteration(), panel.getMaxError(), panel.getFrom(), panel.getTo());
			JOptionPane.showMessageDialog(null, "Network trained with success!");
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error! - " + e.getMessage());
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
