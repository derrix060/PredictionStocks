package process;

import java.util.Set;

import org.encog.neural.networks.BasicNetwork;
import org.encog.neural.networks.layers.BasicLayer;

import process.ActivationFunctionFactory.enumActivationFuncion;

public class NetworkFactory {

	public BasicNetwork getNetwork(Set<Integer> layers, boolean hasBias, double dropoutRate, enumActivationFuncion activationFunction){
		BasicNetwork network = new BasicNetwork();
		
		layers.forEach(neuronCount -> {
			network.addLayer(new BasicLayer(new ActivationFunctionFactory().create(activationFunction), hasBias, neuronCount, dropoutRate));
		});
		
		network.getStructure().finalizeStructure(); //build synapse and layer structure
		network.reset(); //reset weight
		
		return network;
	}
}
