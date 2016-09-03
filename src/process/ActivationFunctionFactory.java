package process;
import org.encog.engine.network.activation.*;

public class ActivationFunctionFactory {
	
	public enum enumActivationFuncion{
		BiPolar, BipolarSteepenedSigmoid, ClippedLinear, Competitive, Elliott, ElliottSymmetric, Gaussian, Linear, LOG, Ramp, Sigmoid, SIN, SoftMax, SteepenedSigmoid, Step, TANH;
	}
	
	public ActivationFunction create (enumActivationFuncion activationType){
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
