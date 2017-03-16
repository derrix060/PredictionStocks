package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
		
		
		trainer.train(nn, panel.getLearningRule(), panel.getMaxIteration(), panel.getMaxError(), panel.getFrom, to);
	}

}
