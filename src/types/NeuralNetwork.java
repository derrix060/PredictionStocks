package types;

import java.util.ArrayList;

import org.encog.neural.networks.BasicNetwork;

import types.Data.enumAttributesOfData;

public class NeuralNetwork {

	/**
	 * Attributes
	 */
	private BasicNetwork topology;
	private ArrayList<enumAttributesOfData> attributes;
	private Integer dateInterval;
	private String stock;
	
	
	/**
	 * Constructor
	 */
	public NeuralNetwork(BasicNetwork topology, ArrayList<enumAttributesOfData> attributes, Integer dateInterval, String stock){
		this.topology = topology;
		this.attributes = attributes;
		this.dateInterval = dateInterval;
		this.stock = stock;
	}
	
	
	
	/**
	 * Getters
	 */
	public BasicNetwork getTopology() {
		return topology;
	}
	public ArrayList<enumAttributesOfData> getAttributes() {
		return attributes;
	}
	public Integer getDateInterval() {
		return dateInterval;
	}
	public String getStock() {
		return stock;
	}
	
	
	
}
