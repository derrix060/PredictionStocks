package view;

import java.awt.Component;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.TrainNNBtnAction;
import factories.PropagationFactory.enumTrainingType;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import net.sourceforge.jdatepicker.JDatePicker;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

public class TrainPanel extends JPanel {

	/**
	 * generated ID
	 */
	private static final long serialVersionUID = 8719229007858611852L;
	
	//Training
		private JDatePicker txtFrom = new JDatePickerImpl(new JDatePanelImpl(new UtilDateModel()));
		private JDatePicker txtTo = new JDatePickerImpl(new JDatePanelImpl(new UtilDateModel()));
		private NewView view;
		
		
		private JFormattedTextField txtMaxIteration;
		private JFormattedTextField txtMaxError;		
		private JComboBox<enumTrainingType> cmbLearningRule;
		private JFormattedTextField txtInferiorLimit;
		private JFormattedTextField txtSuperiorLimit;
		private JFormattedTextField txtMargin;
		
		public JFXPanel panelGraph;

	/**
	 * Constructor
	 * @param view. What frame it panel belongs to.
	 */
	public TrainPanel(NewView view){
		this.view = view;
		this.setLayout(null);
		
		// Dates
			JLabel lblFrom = new JLabel("From");
			lblFrom.setBounds(10, 11, 160, 14);
			this.add(lblFrom);
			
			((Component) txtFrom).setBounds(10, 26, 160, 27);
			this.add((Component) txtFrom);
			
			JLabel lblTo = new JLabel("To");
			lblTo.setBounds(10, 61, 160, 14);
			this.add(lblTo);
			
			((Component) txtTo).setBounds(10, 76, 160, 27);
			this.add((Component) txtTo);
		
		// Leraning Rule
			JLabel lblLearningRule = new JLabel("Learning Rule");
			lblLearningRule.setBounds(10, 111, 160, 14);
			this.add(lblLearningRule);
			
			cmbLearningRule = new JComboBox<enumTrainingType>();
			cmbLearningRule.setBounds(10, 130, 160, 20);
			populateLearningRule();
			this.add(cmbLearningRule);
		
		// Max Iteration and Error
			JLabel lblMaxIteration = new JLabel("Max Iteration");
			lblMaxIteration.setBounds(10, 162, 160, 14);
			this.add(lblMaxIteration);
			
			txtMaxIteration = new JFormattedTextField();
			txtMaxIteration.setBounds(10, 179, 160, 20);
			txtMaxIteration.setValue("500");
			this.add(txtMaxIteration);
			
			JLabel lblMaxError = new JLabel("Max Error");
			lblMaxError.setBounds(10, 205, 160, 14);
			this.add(lblMaxError);
			
			txtMaxError = new JFormattedTextField();
			txtMaxError.setBounds(10, 221, 160, 20);
			txtMaxError.setValue("0.01");
			this.add(txtMaxError);
		
		// Panel Normalization
			JPanel panelNormalization = new JPanel();
			panelNormalization.setLayout(null);
			panelNormalization.setBorder(BorderFactory.createTitledBorder("Normalization"));
			panelNormalization.setBounds(207, 10, 160, 173);
			this.add(panelNormalization);
			
			JLabel lblInferiorLimit = new JLabel("Inferior Limit");
			lblInferiorLimit.setBounds(10, 25, 138, 14);
			panelNormalization.add(lblInferiorLimit);
			
			txtInferiorLimit = new JFormattedTextField();
			txtInferiorLimit.setBounds(10, 40, 138, 20);
			txtInferiorLimit.setValue("-1");
			panelNormalization.add(txtInferiorLimit);
			
			JLabel lblSuperiorLimit = new JLabel("Superior Limit");
			lblSuperiorLimit.setBounds(10, 69, 138, 14);
			panelNormalization.add(lblSuperiorLimit);
			
			txtSuperiorLimit = new JFormattedTextField();
			txtSuperiorLimit.setBounds(10, 84, 138, 20);
			txtSuperiorLimit.setValue("1");
			panelNormalization.add(txtSuperiorLimit);
			
			JLabel lblMargin = new JLabel("Margin %");
			lblMargin.setBounds(10, 116, 138, 14);
			panelNormalization.add(lblMargin);
			
			txtMargin = new JFormattedTextField();
			txtMargin.setBounds(10, 130, 138, 20);
			txtMargin.setValue("20");
			panelNormalization.add(txtMargin);
		
		// Train button
			JButton btnTrain = new JButton("Train");
			btnTrain.setBounds(240, 205, 98, 26);
			this.add(btnTrain);
			btnTrain.addActionListener(new TrainNNBtnAction(this));
		
		// Graph
			panelGraph = new JFXPanel();
			panelGraph.setBounds(10, 253, 357, 213);
			this.add(panelGraph);
	}
	
	/**
	 * Add every Learning Rule avalible to cmbLearningRule
	 */
	public void populateLearningRule(){
		for(enumTrainingType type :  enumTrainingType.values()){
			cmbLearningRule.addItem(type);
		}
	}
	
	public float getMargin(){
		return Float.parseFloat((String) this.txtMargin.getValue()) / 100f;
	}
	
	public enumTrainingType getLearningRule(){
		return (enumTrainingType) cmbLearningRule.getSelectedItem();
	}
	
	public int getMaxIteration(){
		return Integer.parseInt((String) txtMaxIteration.getValue());
	}
	
	public double getMaxError(){
		return Double.parseDouble((String) txtMaxError.getValue());
	}
	
	public double getInferiorLimit(){
		return Double.parseDouble((String) txtInferiorLimit.getValue());
	}
	
	public double getSuperiorLimit(){
		return Double.parseDouble((String) txtSuperiorLimit.getValue());
	}
	
	public NewView getView(){
		return this.view;
	}
	
	/**
	 * Get the trainning's end date from Panel
	 * @return - End date
	 */
	public Calendar getFrom(){
		Calendar cal = Calendar.getInstance();
		cal.setTime((Date) txtFrom.getModel().getValue());
		return cal;
	}
	

	/**
	 * Get the trainning's begin date from Panel
	 * @return - Begin date
	 */
	public Calendar getTo(){
		Calendar cal = Calendar.getInstance();
		cal.setTime((Date) txtTo.getModel().getValue());
		return cal;
	}
	
	/**
	 *  Populate train graph
	 * @param errors - Errors from trainning
	 */
	public void populateGraph(ArrayList<Double> errors){
		final NumberAxis xAxis = new NumberAxis();
		final NumberAxis yAxis = new NumberAxis();
		yAxis.setForceZeroInRange(false);
		
		yAxis.setLabel("Error");
		xAxis.setLabel("Epoch");
		
		final LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
		
		Scene scene = new Scene(lineChart, panelGraph.getWidth(), panelGraph.getHeight());
		
		populateSeries(errors, lineChart);
		
		panelGraph.setScene(scene);
		
	}
	
	@SuppressWarnings("unchecked")
	private void populateSeries(ArrayList<Double> errors, LineChart<Number,Number> lineChart){
		Series<Number, Number> nnSerie = new Series<>();
		
		nnSerie.setName("Error");
		
		for (int i=0; i<errors.size();i++){
			nnSerie.getData().add(new Data<Number, Number>(i, errors.get(i)));
		}
		
		lineChart.getData().addAll(nnSerie);
	}

}
