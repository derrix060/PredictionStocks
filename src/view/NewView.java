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

import net.sourceforge.jdatepicker.JDatePicker;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;
import process.PropagationFactory.enumTrainingType;
import types.NeuralNetwork;

public class NewView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private JPanel 					contentPane;
	
	
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
		tabbedPane.addTab("Create", null, panelCreate, null);
		
			
		JPanel panelChoose = createChoosePanel();
		tabbedPane.addTab("Choose", null, panelChoose, null);
		
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
	
	
	private JPanel createChoosePanel(){
		JPanel choosePanel = new JPanel();
		choosePanel.setLayout(null);
		
		
		
		return choosePanel;
	}
	
	private JPanel createTrainPanel(){
		JPanel trainPanel = new JPanel();
		trainPanel.setLayout(null);
		
		((Component) txtFrom).setBounds(10, 36, 202, 23);
		trainPanel.add((Component) txtFrom);
		
		JLabel lblFrom = new JLabel("From");
		lblFrom.setBounds(10, 11, 46, 14);
		trainPanel.add(lblFrom);
		
		JLabel lblTo = new JLabel("To");
		lblTo.setBounds(10, 70, 46, 14);
		trainPanel.add(lblTo);
		
		
		return trainPanel;
	}
	
	private JPanel createReportPanel(){
		JPanel reportPanel = new JPanel();
		
		
		return reportPanel;
	}
	
	
	/**
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
