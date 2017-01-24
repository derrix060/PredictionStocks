package process;

import java.io.IOException;
import java.util.List;

import org.encog.neural.networks.BasicNetwork;
import org.encog.neural.networks.layers.BasicLayer;

import process.ActivationFunctionFactory.enumActivationFuncion;

public class NetworkFactory {

	public BasicNetwork getNetwork(List<Integer> layers, List<Boolean> bias, List<Double> dropoutRate, List<enumActivationFuncion> activationFunction) throws IOException{
		BasicNetwork network = new BasicNetwork();
		
		int size = layers.size();
		
		//Comparing sizes
		if (bias.size() != size){
			throw new IOException("Bias quantity is invalid!");
		}
		if (dropoutRate.size() != size){
			throw new IOException("DropOut quantity is invalid!");
		}
		if (activationFunction.size() != size){
			throw new IOException("ActivationFunction quantity is invalid!");
		}
		
		
		//create layers
		for (int i=0; i<layers.size(); i++){
			network.addLayer(new BasicLayer(new ActivationFunctionFactory().create(activationFunction.get(i)), bias.get(i), layers.get(i), dropoutRate.get(i)));
		}
		
		network.getStructure().finalizeStructure();
		network.reset();
		
		return network;
	}
	
	
	
}
