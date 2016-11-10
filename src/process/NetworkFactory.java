package process;

import java.io.IOException;
import java.util.List;

import org.encog.engine.network.activation.ActivationFunction;
import org.encog.neural.networks.BasicNetwork;
import org.encog.neural.networks.layers.BasicLayer;

import process.ActivationFunctionFactory.enumActivationFuncion;

public class NetworkFactory {

	public BasicNetwork getNetwork(List<Integer> layers, List<Integer> bias, double dropoutRate, List<enumActivationFuncion> activationFunction) throws IOException{
		BasicNetwork network = new BasicNetwork();
		
		//Comparing size with layer and activationFunction
		if (layers.size() != activationFunction.size()){
			throw new IOException("Quantidade de camadas é diferente da de funções de ativacao");
		}
		
		//Comparing size with layer and bias
		if (layers.size() != bias.size()){
			throw new IOException("Quantidade de bias é diferente da de camadas!");
		}
		
		//Validating if bias is 0 or 1
		if (bias.stream().filter(n -> n!=0 || n!= 1).findAny().isPresent()){
			throw new IOException("Argumentos do bias inválido!");
		}
		
		//old for to control index
		for (int i=0; i<layers.size(); i++){
			network.addLayer(new BasicLayer(new ActivationFunctionFactory().create(activationFunction.get(i)), bias.get(i)==1?true:false, layers.get(i), dropoutRate));
		}
		
		
		
		return network;
	}
	
	
	
}
