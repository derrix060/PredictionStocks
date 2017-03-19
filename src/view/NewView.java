package view;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

import model.NeuralNetwork;

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
		
		JPanel panelReport = new ReportPanel(this);
		tabbedPane.addTab("Report", null, panelReport, "Used to see the performance of Neural Network");
		
		JLabel lblSelectedNetwork = new JLabel("Selected Network: ");
		lblSelectedNetwork.setBounds(20, 536, 107, 14);
		contentPane.add(lblSelectedNetwork);
		
		
		lblNetw.setBounds(132, 536, 262, 14);
		contentPane.add(lblNetw);
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
