package factories;

import org.encog.ml.data.MLDataSet;
import org.encog.neural.networks.BasicNetwork;
import org.encog.neural.networks.training.propagation.Propagation;
import org.encog.neural.networks.training.propagation.back.Backpropagation;
import org.encog.neural.networks.training.propagation.manhattan.ManhattanPropagation;
import org.encog.neural.networks.training.propagation.quick.QuickPropagation;
import org.encog.neural.networks.training.propagation.resilient.ResilientPropagation;
import org.encog.ml.data.basic.BasicMLDataSet;

import model.HistoricalData;
import model.NeuralNetwork;

public class PropagationFactory {
	
	public static final double DEFAULT_TRAINING_RATE = 0.1;

	public enum enumTrainingType{
		ResilientPropagation, Backpropagation, QuickPropagation, ScaledConjugateGradient, ManhattanPropagation;
	}
	
	public static Propagation create(enumTrainingType type, NeuralNetwork network, HistoricalData hd, double learningRate){

		BasicMLDataSet trainingSet = new BasicMLDataSet(hd.toInput(network.getAttributes()), hd.toIdealOutput(network.getAttributes()));


		if (type.equals(enumTrainingType.ResilientPropagation)) return new ResilientPropagation(network.getTopology(), trainingSet);
		if (type.equals(enumTrainingType.Backpropagation)) return new Backpropagation(network.getTopology(), trainingSet);
		if (type.equals(enumTrainingType.QuickPropagation)) return new QuickPropagation(network.getTopology(), trainingSet);
		if (type.equals(enumTrainingType.ManhattanPropagation)) return new ManhattanPropagation(network.getTopology(), trainingSet, learningRate);
		else return null;
	}
}
