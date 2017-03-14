package view;

import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;

import factories.PropagationFactory.enumTrainingType;
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
		NewView view;
		
		JComboBox<enumTrainingType> cmbLearningRule;

	/**
	 * Constructor
	 * @param view. What frame it panel belongs to.
	 */
	public TrainPanel(NewView view){
		this.view = view;
		this.setLayout(null);
		
		
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
		
		JLabel lblLearningRule = new JLabel("Learning Rule");
		lblLearningRule.setBounds(10, 111, 160, 14);
		this.add(lblLearningRule);
		
		cmbLearningRule = new JComboBox<enumTrainingType>();
		cmbLearningRule.setBounds(10, 130, 160, 20);
		populateLearningRule();
		this.add(cmbLearningRule);
		
		JLabel lblMaxIteration = new JLabel("Max Iteration");
		lblMaxIteration.setBounds(10, 162, 160, 14);
		this.add(lblMaxIteration);
		
		JFormattedTextField txtMaxIteration2 = new JFormattedTextField();
		txtMaxIteration2.setBounds(10, 179, 160, 20);
		this.add(txtMaxIteration2);
		
		JLabel lblMaxError = new JLabel("Max Error");
		lblMaxError.setBounds(10, 205, 160, 14);
		this.add(lblMaxError);
		
		JFormattedTextField txtMaxError2 = new JFormattedTextField();
		txtMaxError2.setBounds(10, 221, 160, 20);
		this.add(txtMaxError2);
		
		JPanel panelNormalization = new JPanel();
		panelNormalization.setLayout(null);
		panelNormalization.setBorder(BorderFactory.createTitledBorder("Normalization"));
		panelNormalization.setBounds(207, 10, 160, 173);
		this.add(panelNormalization);
		
		JLabel lblInferiorLimit = new JLabel("Inferior Limit");
		lblInferiorLimit.setBounds(10, 25, 138, 14);
		panelNormalization.add(lblInferiorLimit);
		
		JFormattedTextField txtInferiorLimit2 = new JFormattedTextField();
		txtInferiorLimit2.setBounds(10, 40, 138, 20);
		panelNormalization.add(txtInferiorLimit2);
		
		JLabel label_2 = new JLabel("Superior Limit");
		label_2.setBounds(10, 69, 138, 14);
		panelNormalization.add(label_2);
		
		JFormattedTextField txtSuperiorLimit2 = new JFormattedTextField();
		txtSuperiorLimit2.setBounds(10, 84, 138, 20);
		panelNormalization.add(txtSuperiorLimit2);
		
		JLabel label_3 = new JLabel("Margin %");
		label_3.setBounds(10, 116, 138, 14);
		panelNormalization.add(label_3);
		
		JFormattedTextField txtMargin2 = new JFormattedTextField();
		txtMargin2.setBounds(10, 130, 138, 20);
		panelNormalization.add(txtMargin2);
		
		JButton btnTrain = new JButton("Train");
		btnTrain.setBounds(240, 205, 98, 26);
		this.add(btnTrain);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 253, 357, 213);
		this.add(panel);
	}
	
	/**
	 * Add every Learning Rule avalible to cmbLearningRule
	 */
	public void populateLearningRule(){
		for(enumTrainingType type :  enumTrainingType.values()){
			cmbLearningRule.addItem(type);
		}
	}

	


}
