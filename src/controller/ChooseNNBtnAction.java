package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JOptionPane;

import model.NeuralNetwork;
import view.NNPanel;

public class ChooseNNBtnAction implements ActionListener {
	private final NNPanel view;
	
	public ChooseNNBtnAction(NNPanel view) {
		this.view = view;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// Check if have a name
		String name = view.getNNName();
		
		if (name.isEmpty()){
			JOptionPane.showMessageDialog(null, "Please insert the name of network to choose them!", "Error: don't have name", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		try {
			view.getView().progressBar.setVisible(true);
			NeuralNetwork nn = NeuralNetwork.load(name);
			view.setActiveNN(nn);
			view.updateFields(nn);
			
		
		} catch (IOException ex) {
			JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Error: network cannot be loaded!", JOptionPane.ERROR_MESSAGE);
		}
		
		view.getView().progressBar.setVisible(false);
		
	}

}
