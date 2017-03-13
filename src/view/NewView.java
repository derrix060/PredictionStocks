package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

import factories.PropagationFactory.enumTrainingType;
import model.NeuralNetwork;
import net.sourceforge.jdatepicker.JDatePicker;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;
import javax.swing.BorderFactory;
import javax.swing.JButton;

public class NewView extends JFrame {

	/**
	 * default serial version
	 */
	private static final long serialVersionUID = 1L;
	
	
	private JPanel contentPane;
	
	
	//Necessary objects to control in other class
		private JDatePicker txtFrom = new JDatePickerImpl(new JDatePanelImpl(new UtilDateModel()));
		private JDatePicker txtTo = new JDatePickerImpl(new JDatePanelImpl(new UtilDateModel()));
	
		//normalization
				private JFormattedTextField txtSuperiorLimit = NumberTextField.newField(Double.class, 0, 1);
				private JFormattedTextField txtInferiorLimit = NumberTextField.newField(Double.class, -1, 0);
				private JFormattedTextField txtMargin = NumberTextField.newField(Float.class, Float.MIN_NORMAL, Float.MAX_VALUE);
			//Training
				private JComboBox<enumTrainingType> cmbTraining = new JComboBox<>();
				private JDatePicker txtToT = new JDatePickerImpl(new JDatePanelImpl(new UtilDateModel()));
				private JFormattedTextField txtMaxIteration = NumberTextField.newField(Integer.class, 1, Integer.MAX_VALUE);
				private JFormattedTextField txtMinError = NumberTextField.newField(Double.class, 0d, 1d);
			

				private NeuralNetwork activeNetwork = null;
				private JLabel lblNetw = new JLabel("None");
	
	
	
	
	
	
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewView frame = new NewView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public NewView() {
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 420, 600);
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 204, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 384, 506);
		contentPane.add(tabbedPane);

		JPanel panelCreate = new NNPanel(this);
		tabbedPane.addTab("NeuralNetwork", null, panelCreate, "Used to create or choose a Neural Network");
		
		
		JPanel panelTrain = createTrainPanel();
		tabbedPane.addTab("Train", null, panelTrain, null);
		
		JPanel panelReport = createReportPanel();
		tabbedPane.addTab("Report", null, panelReport, null);
		
		JLabel lblSelectedNetwork = new JLabel("Selected Network: ");
		lblSelectedNetwork.setBounds(20, 536, 107, 14);
		contentPane.add(lblSelectedNetwork);
		
		
		lblNetw.setBounds(132, 536, 262, 14);
		contentPane.add(lblNetw);
	}
	
	
	/*
	 * Create Panels
	 */
	
	
	
	private JPanel createTrainPanel(){
		JPanel trainPanel = new JPanel();
		trainPanel.setLayout(null);
		
		
		JLabel lblFrom = new JLabel("From");
		lblFrom.setBounds(10, 11, 160, 14);
		trainPanel.add(lblFrom);
		
		((Component) txtFrom).setBounds(10, 26, 160, 23);
		trainPanel.add((Component) txtFrom);
		
		JLabel lblTo = new JLabel("To");
		lblTo.setBounds(10, 61, 160, 14);
		trainPanel.add(lblTo);
		
		((Component) txtTo).setBounds(10, 76, 160, 23);
		trainPanel.add((Component) txtTo);
		
		JLabel lblLearningRule = new JLabel("Learning Rule");
		lblLearningRule.setBounds(10, 111, 160, 14);
		trainPanel.add(lblLearningRule);
		
		JComboBox<enumTrainingType> cmbLearningRule = new JComboBox<enumTrainingType>();
		cmbLearningRule.setBounds(10, 130, 160, 20);
		trainPanel.add(cmbLearningRule);
		
		JLabel lblMaxIteration = new JLabel("Max Iteration");
		lblMaxIteration.setBounds(10, 162, 160, 14);
		trainPanel.add(lblMaxIteration);
		
		JFormattedTextField txtMaxIteration2 = new JFormattedTextField();
		txtMaxIteration2.setBounds(10, 179, 160, 20);
		trainPanel.add(txtMaxIteration2);
		
		JLabel lblMaxError = new JLabel("Max Error");
		lblMaxError.setBounds(10, 205, 160, 14);
		trainPanel.add(lblMaxError);
		
		JFormattedTextField txtMaxError2 = new JFormattedTextField();
		txtMaxError2.setBounds(10, 221, 160, 20);
		trainPanel.add(txtMaxError2);
		
		JPanel panelNormalization = new JPanel();
		panelNormalization.setLayout(null);
		panelNormalization.setBorder(BorderFactory.createTitledBorder("Normalization"));
		panelNormalization.setBounds(207, 10, 160, 173);
		trainPanel.add(panelNormalization);
		
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
		trainPanel.add(btnTrain);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 253, 357, 213);
		trainPanel.add(panel);
		
		
		return trainPanel;
	}
	
	private JPanel createReportPanel(){
		JPanel reportPanel = new JPanel();
		
		
		return reportPanel;
	}
	
	
	/*
	 * Getter and setters
	 */
	
	
	
	
	
	public Calendar getFrom() {
		Calendar cal = Calendar.getInstance();
		cal.setTime((Date) txtFrom.getModel().getValue());
		return cal;
	}

	public Calendar getTo() {
		Calendar cal = Calendar.getInstance();
		cal.setTime((Date) txtTo.getModel().getValue());
		return cal;
	}

	

	public Double getSuperiorLimit() {
		return (Double) txtSuperiorLimit.getValue();
	}

	public Double getInferiorLimit() {
		return (Double) txtInferiorLimit.getValue();
	}

	public Float getMargin() {
		return (Float) txtMargin.getValue()/100f;
	}

	public enumTrainingType getRule() {
		return (enumTrainingType) cmbTraining.getSelectedItem();
	}

	public Calendar getToTraining() {
		Calendar cal = Calendar.getInstance();
		cal.setTime((Date) txtToT.getModel().getValue());
		return cal;
	}

	public Integer getMaxIteration() {
		return (Integer) txtMaxIteration.getValue();
	}

	public Double getMinError() {
		return (Double) txtMinError.getValue();
	}
	
	public void setActiveNN(NeuralNetwork nn){
		this.activeNetwork = nn;
		lblNetw.setText(nn.getName());
	}
	public NeuralNetwork getActiveNN(){
		return this.activeNetwork;
	}
	
	public String getNNName(){
		return this.getActiveNN().getName();
	}
}
