package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

import model.NeuralNetwork;
import net.sourceforge.jdatepicker.JDatePicker;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;

public class NewView extends JFrame {

	/**
	 * default serial version
	 */
	private static final long serialVersionUID = 1L;
	
	
	private JPanel contentPane;
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
		
		
		JPanel panelTrain = new TrainPanel(this);
		tabbedPane.addTab("Train", null, panelTrain, "Used to train a Neural Network");
		
		JPanel panelReport = createReportPanel();
		tabbedPane.addTab("Report", null, panelReport, "Used to see the performance of Neural Network");
		
		JLabel lblSelectedNetwork = new JLabel("Selected Network: ");
		lblSelectedNetwork.setBounds(20, 536, 107, 14);
		contentPane.add(lblSelectedNetwork);
		
		
		lblNetw.setBounds(132, 536, 262, 14);
		contentPane.add(lblNetw);
	}
	
	
	/*
	 * Create Panels
	 */
	
	
	private JPanel createReportPanel(){
		JPanel reportPanel = new JPanel();
		reportPanel.setLayout(null);
		
		// Dates
			JDatePicker txtFrom = new JDatePickerImpl(new JDatePanelImpl(new UtilDateModel()));
			JDatePicker txtTo = new JDatePickerImpl(new JDatePanelImpl(new UtilDateModel()));
			
			JLabel lblFrom = new JLabel("From");
			lblFrom.setBounds(10, 11, 160, 14);
			reportPanel.add(lblFrom);
			
			((Component) txtFrom).setBounds(10, 26, 160, 27);
			reportPanel.add((Component) txtFrom);
			
			JLabel lblTo = new JLabel("To");
			lblTo.setBounds(10, 61, 160, 14);
			reportPanel.add(lblTo);
			
			((Component) txtTo).setBounds(10, 76, 160, 27);
			reportPanel.add((Component) txtTo);
		
		// Button
			JButton btnCreateReport = new JButton("Create Report");
			btnCreateReport.setBounds(10, 134, 159, 26);
			reportPanel.add(btnCreateReport);
		
		
		// Normalization Panel
			JFormattedTextField txtInferiorLimit;
			JFormattedTextField txtSuperiorLimit;
			JFormattedTextField txtMargin;
		
			JPanel panelNormalization = new JPanel();
			panelNormalization.setLayout(null);
			panelNormalization.setBorder(BorderFactory.createTitledBorder("Normalization"));
			panelNormalization.setBounds(207, 10, 160, 150);
			reportPanel.add(panelNormalization);
			
			JLabel lblInferiorLimit = new JLabel("Inferior Limit");
			lblInferiorLimit.setBounds(10, 25, 138, 14);
			panelNormalization.add(lblInferiorLimit);
			
			txtInferiorLimit = new JFormattedTextField();
			txtInferiorLimit.setBounds(10, 40, 138, 20);
			panelNormalization.add(txtInferiorLimit);
			
			JLabel lblSuperiorLimit = new JLabel("Superior Limit");
			lblSuperiorLimit.setBounds(10, 65, 138, 14);
			panelNormalization.add(lblSuperiorLimit);
			
			txtSuperiorLimit = new JFormattedTextField();
			txtSuperiorLimit.setBounds(10, 80, 138, 20);
			panelNormalization.add(txtSuperiorLimit);
			
			JLabel lblMargin = new JLabel("Margin %");
			lblMargin.setBounds(10, 104, 138, 14);
			panelNormalization.add(lblMargin);
			
			txtMargin = new JFormattedTextField();
			txtMargin.setBounds(10, 118, 138, 20);
			panelNormalization.add(txtMargin);
			
		// Graph
			JPanel panelGraph = new JPanel();
			panelGraph.setBounds(10, 172, 357, 294);
			reportPanel.add(panelGraph);

		
		return reportPanel;
		
	}
	
	
	/*
	 * Getter and setters
	 */
	
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
