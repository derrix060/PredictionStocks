package process;

import org.encog.ml.data.MLDataSet;
import org.encog.neural.networks.BasicNetwork;
import org.encog.neural.networks.training.propagation.Propagation;
import org.encog.neural.networks.training.propagation.back.Backpropagation;
import org.encog.neural.networks.training.propagation.manhattan.ManhattanPropagation;
import org.encog.neural.networks.training.propagation.quick.QuickPropagation;
import org.encog.neural.networks.training.propagation.resilient.ResilientPropagation;

public class PropagationFactory {
	
	private final double default_trainingRate = 0.1;

	public enum trainingType{
		ResilientPropagation, Backpropagation, QuickPropagation, ScaledConjugateGradient, ManhattanPropagation;
	}
	
	public Propagation create(trainingType type, BasicNetwork network, MLDataSet trainingSet){
		return create(type, network, trainingSet, default_trainingRate);
	}
	public Propagation create(trainingType type, BasicNetwork network, MLDataSet trainingSet, double learningRate){
		if (type.equals(trainingType.ResilientPropagation)) return new ResilientPropagation(network, trainingSet);
		if (type.equals(trainingType.Backpropagation)) return new Backpropagation(network, trainingSet);
		if (type.equals(trainingType.QuickPropagation)) return new QuickPropagation(network, trainingSet);
		if (type.equals(trainingType.ManhattanPropagation)) return new ManhattanPropagation(network, trainingSet, learningRate);
		else return null;
	}
}
