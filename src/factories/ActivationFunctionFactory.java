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
		BiPolar(ActivationBiPolar.class),
		BipolarSteepenedSigmoid(ActivationBipolarSteepenedSigmoid.class), 
		ClippedLinear(ActivationClippedLinear.class), 
		Competitive(ActivationCompetitive.class),
		Elliott(ActivationElliott.class), 
		ElliottSymmetric(ActivationElliottSymmetric.class), 
		Gaussian(ActivationGaussian.class), 
		Linear(ActivationLinear.class),
		LOG(ActivationLOG.class),
		Ramp(ActivationRamp.class), 
		Sigmoid(ActivationSigmoid.class),
		SIN(ActivationSIN.class), 
		SoftMax(ActivationSoftMax.class),
		SteepenedSigmoid(ActivationSteepenedSigmoid.class), 
		Step(ActivationStep.class), 
		TANH(ActivationTANH.class);
		
		private final Class<? extends ActivationFunction> clazz;
		
		/**
		 * Enum activation Function constructor
		 * @param activationFunction
		 */
		private enumActivationFuncion(Class<? extends ActivationFunction> activationFunction) {
			this.clazz = activationFunction;
		}
	}
	
	/**
	 * Create activationFunction from an enum
	 * @param enumAct
	 * @return activationFunction
	 */
	public static ActivationFunction create (enumActivationFuncion enumAct){
		try {
			return enumAct.clazz.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static enumActivationFuncion fromString(String vlr){
		switch (vlr.toLowerCase()) {
			case "bipolar":
				return enumActivationFuncion.BiPolar;
			case "bipolar-steepenedsigmoid":
				return enumActivationFuncion.BipolarSteepenedSigmoid;
			case "clippedlinear":
				return enumActivationFuncion.ClippedLinear;
			case "competitive":
				return enumActivationFuncion.Competitive;
			case "elliott":
				return enumActivationFuncion.Elliott;
			case "elliottsymmetric":
				return enumActivationFuncion.ElliottSymmetric;
			case "gaussian":
				return enumActivationFuncion.Gaussian;
			case "linear":
				return enumActivationFuncion.Linear;
			case "log":
				return enumActivationFuncion.LOG;
			case "ramp":
				return enumActivationFuncion.Ramp;
			case "sigmoid":
				return enumActivationFuncion.Sigmoid;
			case "sin":
				return enumActivationFuncion.SIN;
			case "softmax":
				return enumActivationFuncion.SoftMax;
			case "steepenedsigmoid":
				return enumActivationFuncion.SteepenedSigmoid;
			case "step":
				return enumActivationFuncion.Step;
			case "tanh":
				return enumActivationFuncion.TANH;
			default:
				return enumActivationFuncion.ElliottSymmetric;
		}
	}
	
}
