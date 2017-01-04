package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
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
		
		JFormattedTextField formattedTextField = new JFormattedTextField();
		formattedTextField.setBounds(25, 43, 109, 20);
		panelInput.add(formattedTextField);
		
		JLabel lblNewLabel = new JLabel("To");
		lblNewLabel.setBounds(25, 74, 109, 14);
		panelInput.add(lblNewLabel);
		
		JFormattedTextField formattedTextField_1 = new JFormattedTextField();
		formattedTextField_1.setBounds(25, 88, 109, 20);
		panelInput.add(formattedTextField_1);
		
		JLabel lblStock = new JLabel("Stock");
		lblStock.setBounds(25, 164, 46, 14);
		panelInput.add(lblStock);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(25, 181, 109, 20);
		panelInput.add(comboBox);
		
		JLabel lblNumberOfDays = new JLabel("Data interval in days");
		lblNumberOfDays.setBounds(25, 119, 109, 14);
		panelInput.add(lblNumberOfDays);
		
		JFormattedTextField formattedTextField_6 = new JFormattedTextField();
		formattedTextField_6.setBounds(25, 133, 109, 20);
		panelInput.add(formattedTextField_6);
		
		JPanel panelInputDataType = new JPanel();
		panelInputDataType.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelInputDataType.setBounds(25, 226, 109, 181);
		panelInput.add(panelInputDataType);
		panelInputDataType.setLayout(null);
		panelInputDataType.setBorder(BorderFactory.createTitledBorder("Stock Attribute"));
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Close Price");
		chckbxNewCheckBox.setBounds(15, 25, 77, 23);
		panelInputDataType.add(chckbxNewCheckBox);
		
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
		
		JFormattedTextField formattedTextField_2 = new JFormattedTextField();
		formattedTextField_2.setBounds(10, 39, 44, 20);
		panelNetworkConf.add(formattedTextField_2);
		
		JLabel lblBiasPerLayer = new JLabel("Has Bias?");
		lblBiasPerLayer.setBounds(64, 25, 51, 14);
		panelNetworkConf.add(lblBiasPerLayer);
		
		JLabel lblNewLabel_1 = new JLabel("Activation Function");
		lblNewLabel_1.setBounds(125, 25, 97, 14);
		panelNetworkConf.add(lblNewLabel_1);
		
		JLabel lblDropoutRate = new JLabel("DropOut Rate");
		lblDropoutRate.setBounds(232, 25, 73, 14);
		panelNetworkConf.add(lblDropoutRate);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(64, 39, 51, 20);
		panelNetworkConf.add(comboBox_1);
		
		JComboBox comboBox_3 = new JComboBox();
		comboBox_3.setBounds(125, 39, 97, 20);
		panelNetworkConf.add(comboBox_3);
		
		JFormattedTextField formattedTextField_3 = new JFormattedTextField();
		formattedTextField_3.setBounds(232, 39, 73, 20);
		panelNetworkConf.add(formattedTextField_3);
		
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
		
		
		tableLayers = new JTable(10,4);
		tableLayers.setBorder(new LineBorder(new Color(0, 0, 0)));
		tableLayers.setBounds(10, 390, 295, -272);
		
		DefaultTableModel model = (DefaultTableModel) tableLayers.getModel();
		
		Object[] row = {1, true, enumActivationFuncion.BiPolar, 0.5f};
		model.addRow(row);
		
		panelNetworkConf.add(tableLayers);
		
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
