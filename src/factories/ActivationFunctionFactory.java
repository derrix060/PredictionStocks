package factories;
import org.encog.engine.network.activation.ActivationBiPolar;
import org.encog.engine.network.activation.ActivationBipolarSteepenedSigmoid;
import org.encog.engine.network.activation.ActivationClippedLinear;
import org.encog.engine.network.activation.ActivationCompetitive;
import org.encog.engine.network.activation.ActivationElliott;
import org.encog.engine.network.activation.ActivationElliottSymmetric;
import org.encog.engine.network.activation.ActivationFunction;
import org.encog.engine.network.activation.ActivationGaussian;
import org.encog.engine.network.activation.ActivationLOG;
import org.encog.engine.network.activation.ActivationLinear;
import org.encog.engine.network.activation.ActivationRamp;
import org.encog.engine.network.activation.ActivationSIN;
import org.encog.engine.network.activation.ActivationSigmoid;
import org.encog.engine.network.activation.ActivationSoftMax;
import org.encog.engine.network.activation.ActivationSteepenedSigmoid;
import org.encog.engine.network.activation.ActivationStep;
import org.encog.engine.network.activation.ActivationTANH;

public class ActivationFunctionFactory {
	
	public enum enumActivationFuncion{
		BiPolar, //-1 or 1
		BipolarSteepenedSigmoid, 
		ClippedLinear, 
		Competitive, //?
		Elliott, 
		ElliottSymmetric, 
		Gaussian, 
		Linear, //any - return the parameter
		LOG, //any
		Ramp, 
		Sigmoid, // [-1;1]
		SIN, 
		SoftMax, //?
		SteepenedSigmoid, 
		Step, 
		TANH; //[-1;1]
	}
	
	public static ActivationFunction create (enumActivationFuncion activationType){
		if (activationType.equals(enumActivationFuncion.BiPolar)) return new ActivationBiPolar();
		if (activationType.equals(enumActivationFuncion.BipolarSteepenedSigmoid)) return new ActivationBipolarSteepenedSigmoid();
		if (activationType.equals(enumActivationFuncion.ClippedLinear)) return new ActivationClippedLinear();
		if (activationType.equals(enumActivationFuncion.Competitive)) return new ActivationCompetitive();
		if (activationType.equals(enumActivationFuncion.Elliott)) return new ActivationElliott();
		if (activationType.equals(enumActivationFuncion.ElliottSymmetric)) return new ActivationElliottSymmetric();
		if (activationType.equals(enumActivationFuncion.Gaussian)) return new ActivationGaussian();
		if (activationType.equals(enumActivationFuncion.Linear)) return new ActivationLinear();
		if (activationType.equals(enumActivationFuncion.LOG)) return new ActivationLOG();
		if (activationType.equals(enumActivationFuncion.Ramp)) return new ActivationRamp();
		if (activationType.equals(enumActivationFuncion.Sigmoid)) return new ActivationSigmoid();
		if (activationType.equals(enumActivationFuncion.SIN)) return new ActivationSIN();
		if (activationType.equals(enumActivationFuncion.SoftMax)) return new ActivationSoftMax();
		if (activationType.equals(enumActivationFuncion.SteepenedSigmoid)) return new ActivationSteepenedSigmoid();
		if (activationType.equals(enumActivationFuncion.Step)) return new ActivationStep();
		if (activationType.equals(enumActivationFuncion.TANH)) return new ActivationTANH();
		else return null;
	}
}
