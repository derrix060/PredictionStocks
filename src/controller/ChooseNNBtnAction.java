package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

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
		
		
		// Check if have the network
	
		
	}

}
