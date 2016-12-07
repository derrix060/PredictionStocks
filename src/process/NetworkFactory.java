package process;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.encog.neural.networks.BasicNetwork;
import org.encog.neural.networks.layers.BasicLayer;

import process.ActivationFunctionFactory.enumActivationFuncion;

public class NetworkFactory {

	public BasicNetwork getNetwork(List<Integer> layers, List<Boolean> bias, double dropoutRate, List<enumActivationFuncion> activationFunction) throws IOException{
		BasicNetwork network = new BasicNetwork();
		
		//Comparing size with layer and activationFunction
		if (layers.size() != activationFunction.size()){
			throw new IOException("Quantidade de camadas é diferente da de funções de ativacao");
		}
		
		//Comparing size with layer and bias
		if (layers.size() != bias.size()){
			throw new IOException("Quantidade de bias é diferente da de camadas!");
		}
		
		
		//create layers
		for (int i=0; i<layers.size(); i++){
			network.addLayer(new BasicLayer(new ActivationFunctionFactory().create(activationFunction.get(i)), bias.get(i), layers.get(i), dropoutRate));
		}
		
		network.getStructure().finalizeStructure();
		network.reset();
		
		return network;
	}

	public BasicNetwork getNetwork(List<Integer> layers, boolean hasBias, double dropoutRate, enumActivationFuncion actvFunction) throws IOException {
		List<Boolean> bias = new ArrayList<>();
		List<enumActivationFuncion> activation = new ArrayList<>();
		
		for (int i=0; i<layers.size(); i++){
			bias.add(hasBias);
			activation.add(actvFunction);
		}
		
		return getNetwork(layers, bias, dropoutRate, activation);
	}
	
	
	
}
