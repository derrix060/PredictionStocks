package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.encog.neural.networks.BasicNetwork;
import org.encog.neural.networks.layers.BasicLayer;

import factories.NetworkFactory;
import model.Data.enumAttributesOfData;
import model.NeuralNetwork;
import view.NNPanel;

public class CreateNNBtnAction implements ActionListener {
	private final NNPanel view;
	
	
	public CreateNNBtnAction(NNPanel nnPanel) { 
		this.view = nnPanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		//Check if has at least one attribute
		ArrayList<enumAttributesOfData> attributes = view.getAtributes();
		if (attributes.isEmpty()){
			JOptionPane.showMessageDialog(null, "Please select at least one attribute!", "Error: Attribute not selected", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		//Check if has any value
		// TODO: implement checks
		
		//Get values from view
		List<BasicLayer> layers = view.getLayers();
		String stock = view.getStock();
		Integer dateInterval = view.getDataInterval();
		String name = view.getNNName();
		BasicNetwork topology = NetworkFactory.newNetwork(layers);
		

		
		
		
		//create NN
		NeuralNetwork nn = new NeuralNetwork(topology, attributes, dateInterval, stock, name);

		
		//select the network
		view.setActiveNN(nn);
				
		//save network
		try {
			nn.save(name);
		} catch (IOException ex) {
			JOptionPane.showMessageDialog(null, "Wasn't possible save the network. Please Try again.\n\nError: " + ex.getMessage(), "Error: Cannot save the network!", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		
		
	}
	

}
