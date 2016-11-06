package process;

import java.util.List;

import org.encog.neural.networks.BasicNetwork;
import org.encog.neural.networks.layers.BasicLayer;

import process.ActivationFunctionFactory.enumActivationFuncion;

public class NetworkFactory {

	public BasicNetwork getNetwork(List<Integer> layers, boolean hasBias, double dropoutRate, enumActivationFuncion activationFunction){
		BasicNetwork network = new BasicNetwork();
		
		layers.forEach(neuronCount -> {
			network.addLayer(new BasicLayer(new ActivationFunctionFactory().create(activationFunction), hasBias, neuronCount, dropoutRate));
		});
		
		
		network.getStructure().finalizeStructure(true); //build synapse and layer structure
		network.reset(); //reset weight
		
		return network;
	}
}
