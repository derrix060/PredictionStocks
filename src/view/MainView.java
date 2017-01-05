package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import process.ActivationFunctionFactory.enumActivationFuncion;

import java.awt.Color;
import java.awt.Component;

import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JComboBox;
import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JTable;

public class MainView extends JFrame {

	private JPanel contentPane;
	private JTable tableLayers;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainView frame = new MainView();
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
	public MainView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panelInput = new JPanel();
		panelInput.setToolTipText("");
		panelInput.setBorder(new LineBorder(new Color(171, 173, 179)));
		panelInput.setBounds(32, 32, 157, 500);
		contentPane.add(panelInput);
		panelInput.setLayout(null);
		panelInput.setBorder(BorderFactory.createTitledBorder("Input"));
		
		JLabel lblFrom = new JLabel("From");
		lblFrom.setBounds(25, 29, 109, 14);
		panelInput.add(lblFrom);
		
		JFormattedTextField txtFrom = new JFormattedTextField();
		txtFrom.setBounds(25, 43, 109, 20);
		panelInput.add(txtFrom);
		
		JLabel lblTo = new JLabel("To");
		lblTo.setBounds(25, 74, 109, 14);
		panelInput.add(lblTo);
		
		JFormattedTextField txtTo = new JFormattedTextField();
		txtTo.setBounds(25, 88, 109, 20);
		panelInput.add(txtTo);
		
		JLabel lblStock = new JLabel("Stock");
		lblStock.setBounds(25, 164, 46, 14);
		panelInput.add(lblStock);
		
		JComboBox cmbStock = new JComboBox();
		cmbStock.setBounds(25, 181, 109, 20);
		panelInput.add(cmbStock);
		
		JLabel lblDataInterval = new JLabel("Data interval in days");
		lblDataInterval.setBounds(25, 119, 109, 14);
		panelInput.add(lblDataInterval);
		
		JFormattedTextField txtDataInterval = new JFormattedTextField();
		txtDataInterval.setBounds(25, 133, 109, 20);
		panelInput.add(txtDataInterval);
		
		JPanel panelInputDataType = new JPanel();
		panelInputDataType.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelInputDataType.setBounds(25, 226, 109, 181);
		panelInput.add(panelInputDataType);
		panelInputDataType.setLayout(null);
		panelInputDataType.setBorder(BorderFactory.createTitledBorder("Stock Attribute"));
		
		JCheckBox chckbxClosePrice = new JCheckBox("Close Price");
		chckbxClosePrice.setBounds(15, 25, 77, 23);
		panelInputDataType.add(chckbxClosePrice);
		
		JCheckBox chckbxHighPrice = new JCheckBox("High Price");
		chckbxHighPrice.setBounds(15, 53, 73, 23);
		panelInputDataType.add(chckbxHighPrice);
		
		JCheckBox chckbxNewCheckBox_1 = new JCheckBox("Low Price");
		chckbxNewCheckBox_1.setBounds(15, 81, 71, 23);
		panelInputDataType.add(chckbxNewCheckBox_1);
		
		JCheckBox chckbxOpenPrice = new JCheckBox("Open Price");
		chckbxOpenPrice.setBounds(15, 109, 77, 23);
		panelInputDataType.add(chckbxOpenPrice);
		
		JCheckBox chckbxVolume = new JCheckBox("Volume");
		chckbxVolume.setBounds(15, 138, 59, 23);
		panelInputDataType.add(chckbxVolume);
		
		JPanel panelNetworkConf = new JPanel();
		panelNetworkConf.setLayout(null);
		panelNetworkConf.setToolTipText("");
		panelNetworkConf.setBorder(new LineBorder(new Color(171, 173, 179)));
		panelNetworkConf.setBounds(199, 32, 358, 500);
		contentPane.add(panelNetworkConf);
		panelNetworkConf.setBorder(BorderFactory.createTitledBorder("Network Configuration"));
		
		JLabel lblNeurons = new JLabel("Neurons");
		lblNeurons.setBounds(10, 25, 44, 14);
		panelNetworkConf.add(lblNeurons);
		
		JFormattedTextField txtNeurons = new JFormattedTextField();
		txtNeurons.setBounds(10, 39, 44, 20);
		panelNetworkConf.add(txtNeurons);
		
		JLabel lblBias = new JLabel("Has Bias?");
		lblBias.setBounds(64, 25, 51, 14);
		panelNetworkConf.add(lblBias);
		
		JLabel lblNewLabel_1 = new JLabel("Activation Function");
		lblNewLabel_1.setBounds(125, 25, 97, 14);
		panelNetworkConf.add(lblNewLabel_1);
		
		JLabel lblDropoutRate = new JLabel("DropOut Rate");
		lblDropoutRate.setBounds(232, 25, 73, 14);
		panelNetworkConf.add(lblDropoutRate);
		
		JComboBox cmbBias = new JComboBox();
		cmbBias.setBounds(64, 39, 51, 20);
		panelNetworkConf.add(cmbBias);
		
		JComboBox cmbActivationFunction = new JComboBox();
		cmbActivationFunction.setBounds(125, 39, 97, 20);
		panelNetworkConf.add(cmbActivationFunction);
		
		JFormattedTextField txtDropOut = new JFormattedTextField();
		txtDropOut.setBounds(232, 39, 73, 20);
		panelNetworkConf.add(txtDropOut);
		
		JButton button = new JButton("");
		button.setIcon(new ImageIcon(MainView.class.getResource("/view/add.png")));
		button.setForeground(new Color(0, 128, 0));
		button.setBounds(10, 70, 41, 23);
		panelNetworkConf.add(button);
		
		JButton button_1 = new JButton("");
		button_1.setIcon(new ImageIcon(MainView.class.getResource("/view/cancel.png")));
		button_1.setBounds(60, 70, 41, 23);
		panelNetworkConf.add(button_1);
		
		JButton button_2 = new JButton("");
		button_2.setIcon(new ImageIcon(MainView.class.getResource("/view/edit.png")));
		button_2.setBounds(107, 70, 41, 23);
		panelNetworkConf.add(button_2);
		
		
		//Start create JTable
			
			tableLayers = new JTable();
			tableLayers.setModel(new DefaultTableModel(
				new Object[][] {
					{Integer.valueOf(1), Boolean.TRUE, enumActivationFuncion.BiPolar, new Double(5.2)},
					{Integer.valueOf(2), Boolean.TRUE, enumActivationFuncion.Elliott, new Double(5.2)},
					{Integer.valueOf(3), Boolean.TRUE, enumActivationFuncion.Ramp, new Double(5.2)},
				},
				new String[] {
					"Neurons", "HasBias?", "Activation Function", "DropOut Rate"
				}
			) {
				Class[] columnTypes = new Class[] {
					Integer.class, Boolean.class, enumActivationFuncion.class, Double.class
				};
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
			});
			
			//comboBox Activation
				JComboBox<enumActivationFuncion> comboAct = new JComboBox<>();
				comboAct.addItem(enumActivationFuncion.BiPolar);
				comboAct.addItem(enumActivationFuncion.BipolarSteepenedSigmoid);
				comboAct.addItem(enumActivationFuncion.ClippedLinear);
				comboAct.addItem(enumActivationFuncion.Competitive);
				comboAct.addItem(enumActivationFuncion.Elliott);
				comboAct.addItem(enumActivationFuncion.ElliottSymmetric);
				comboAct.addItem(enumActivationFuncion.Gaussian);
				comboAct.addItem(enumActivationFuncion.Linear);
				comboAct.addItem(enumActivationFuncion.LOG);
				comboAct.addItem(enumActivationFuncion.Ramp);
				comboAct.addItem(enumActivationFuncion.Sigmoid);
				comboAct.addItem(enumActivationFuncion.SIN);
				comboAct.addItem(enumActivationFuncion.SoftMax);
				comboAct.addItem(enumActivationFuncion.SteepenedSigmoid);
				comboAct.addItem(enumActivationFuncion.Step);
				comboAct.addItem(enumActivationFuncion.TANH);
			
			//Modify Activation Column
				tableLayers.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(comboAct));
				
				
				
			tableLayers.getColumnModel().getColumn(0).setResizable(false);
			tableLayers.getColumnModel().getColumn(1).setResizable(false);
			tableLayers.getColumnModel().getColumn(2).setResizable(false);
			tableLayers.getColumnModel().getColumn(3).setResizable(false);
			
			tableLayers.getColumnModel().getColumn(0).setPreferredWidth(54);
			tableLayers.getColumnModel().getColumn(1).setPreferredWidth(56);
			tableLayers.getColumnModel().getColumn(2).setPreferredWidth(103);
			tableLayers.getColumnModel().getColumn(3).setPreferredWidth(77);
			

			JScrollPane scrollPaneLayers = new JScrollPane(tableLayers);
			scrollPaneLayers.setSize(338, 385);
			scrollPaneLayers.setLocation(10, 104);
			tableLayers.setFillsViewportHeight(true);
			
			panelNetworkConf.add(scrollPaneLayers);
			
			//tableLayers.setBorder(new LineBorder(new Color(0, 0, 0)));
			//tableLayers.setBounds(10, 390, 295, -272);
			
			//DefaultTableModel model = (DefaultTableModel) tableLayers.getModel();
			
			//Object[] row = {1, true, enumActivationFuncion.BiPolar, 0.5f};
			//model.addRow(row);
			
			//panelNetworkConf.add(tableLayers);
		//End create JTable
			
			
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setToolTipText("");
		panel_2.setBorder(new LineBorder(new Color(171, 173, 179)));
		panel_2.setBounds(619, 32, 137, 500);
		contentPane.add(panel_2);
		
		JLabel lblMargin = new JLabel("Margin %");
		lblMargin.setBounds(10, 11, 109, 14);
		panel_2.add(lblMargin);
		
		JFormattedTextField formattedTextField_4 = new JFormattedTextField();
		formattedTextField_4.setBounds(10, 25, 109, 20);
		panel_2.add(formattedTextField_4);
		
		JLabel label_4 = new JLabel("To");
		label_4.setBounds(10, 70, 109, 14);
		panel_2.add(label_4);
		
		JFormattedTextField formattedTextField_5 = new JFormattedTextField();
		formattedTextField_5.setBounds(10, 84, 109, 20);
		panel_2.add(formattedTextField_5);
		
		JLabel label_5 = new JLabel("Stock");
		label_5.setBounds(10, 145, 109, 14);
		panel_2.add(label_5);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setBounds(10, 170, 109, 20);
		panel_2.add(comboBox_2);
	}
}
