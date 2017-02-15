package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.encog.neural.networks.BasicNetwork;
import org.encog.neural.networks.layers.BasicLayer;

import process.NetworkFactory;
import types.Data.enumAttributesOfData;
import types.NeuralNetwork;

public class CreateNNBtnAction implements ActionListener {
	private final NewView view;
	private String name;
	private BasicNetwork topology;
	ArrayList<enumAttributesOfData> attributes;
	private Integer dateInterval;
	private String stock;
	
	
	public CreateNNBtnAction(NewView view) { 
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		//attributes
		List<BasicLayer> layers = view.getLayers();
		stock = view.getStock();
		attributes = view.getAtributes();
		dateInterval = view.getDataInterval();
		name = view.getName();
		topology = NetworkFactory.newNetwork(layers);
		
		//create NN
		NeuralNetwork nn = new NeuralNetwork(topology, attributes, dateInterval, stock);
		
		//save network
		try {
			nn.save(name);
		} catch (IOException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
		
	}

}
