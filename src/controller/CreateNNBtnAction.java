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

		if (!view.isFieldsValid()){
			JOptionPane.showMessageDialog(null, "Please complete with valid datas!!", "Error: Invalid datas", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		
		String name = view.getNNName();
		
		//Check if has already this NN
		if (NeuralNetwork.existInSystem(name + ".json")){
			if (JOptionPane.showConfirmDialog(null, "This NN already have in system. Do you want overwrite them?") != JOptionPane.YES_OPTION){
				JOptionPane.showMessageDialog(null, "Okay! Please change the name or choose this network.");
				return;
			}
		}
		
		//Get values from view
		List<BasicLayer> layers = view.getLayers();
		String stock = view.getStock();
		Integer dateInterval = view.getDataInterval();
		BasicNetwork topology = NetworkFactory.newNetwork(layers);
		ArrayList<enumAttributesOfData> attributes = view.getAtributes();
		


		
		
		
		//create NN
		NeuralNetwork nn = new NeuralNetwork(topology, attributes, dateInterval, stock, name);

		
		//select the network
		view.setActiveNN(nn);
				
		//save network
		try {
			nn.save(name);
			JOptionPane.showMessageDialog(null, "Create!", "Success!", JOptionPane.INFORMATION_MESSAGE);
		} catch (IOException ex) {
			JOptionPane.showMessageDialog(null, "Wasn't possible save the network. Please Try again.\n\nError: " + ex.getMessage(), "Error: Cannot save the network!", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		
		
	}
	

}
