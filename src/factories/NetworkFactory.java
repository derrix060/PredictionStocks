package factories;

import java.util.List;

import org.encog.neural.networks.BasicNetwork;
import org.encog.neural.networks.layers.BasicLayer;


public class NetworkFactory {

	public static BasicNetwork newNetwork(List<BasicLayer> layers){
		BasicNetwork network = new BasicNetwork();
		
		//create layers
		for (BasicLayer layer : layers)
			network.addLayer(layer);
		
		network.getStructure().finalizeStructure();
		network.reset();
		
		return network;
	}
	
	
}
