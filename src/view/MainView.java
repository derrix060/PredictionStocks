package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import process.ActivationFunctionFactory.enumActivationFuncion;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JFormattedTextField;
import javax.swing.JComboBox;
import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JTable;

public class MainView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel 					contentPane;
	private LayerTable 				dtm 			= new LayerTable();
	private JTable 					tableLayer 		= new JTable();
	private Object[] 				emptyData 		= {Integer.valueOf(1), Boolean.TRUE, enumActivationFuncion.BiPolar, new Double(1.1)};
    
	
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
		
		JComboBox<?> cmbStock = new JComboBox<Object>();
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
		
		
		//Add button
			JButton btnAdd = new JButton("");
			btnAdd.setIcon(new ImageIcon(MainView.class.getResource("/view/add.png")));
			btnAdd.setForeground(new Color(0, 128, 0));
			btnAdd.setBounds(10, 70, 41, 23);
			btnAdd.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					dtm.addRow(emptyData);
					tableLayer.setModel(dtm);
					
				}
			});
			panelNetworkConf.add(btnAdd);
		//Remove button
			JButton btnRemove = new JButton("");
			btnRemove.setIcon(new ImageIcon(MainView.class.getResource("/view/cancel.png")));
			btnRemove.setBounds(60, 70, 41, 23);
			panelNetworkConf.add(btnRemove);
		
		
		//Start Layer Table
			initializeLayerTable();
			JScrollPane scrollPaneLayers = new JScrollPane(tableLayer);
			scrollPaneLayers.setSize(338, 385);
			scrollPaneLayers.setLocation(10, 104);
			tableLayer.setFillsViewportHeight(true);
			
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
		
		JComboBox<?> comboBox_2 = new JComboBox<Object>();
		comboBox_2.setBounds(10, 170, 109, 20);
		panel_2.add(comboBox_2);
	}

	private void initializeLayerTable(){
		//private String[] columnNames = {"Neurons", "HasBias?", "Activation Function", "DropOut Rate"};
		dtm.addColumn("Neurons");
		dtm.addColumn("HasBias?");
		dtm.addColumn("Activation Function");
		dtm.addColumn("DropOut Rate");
		
		tableLayer.setModel(dtm);
		
		
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
			tableLayer.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(comboAct));
		
			tableLayer.getColumnModel().getColumn(0).setResizable(false);
			tableLayer.getColumnModel().getColumn(1).setResizable(false);
			tableLayer.getColumnModel().getColumn(2).setResizable(false);
			tableLayer.getColumnModel().getColumn(3).setResizable(false);
	
			tableLayer.getColumnModel().getColumn(0).setPreferredWidth(54);
			tableLayer.getColumnModel().getColumn(1).setPreferredWidth(56);
			tableLayer.getColumnModel().getColumn(2).setPreferredWidth(103);
			tableLayer.getColumnModel().getColumn(3).setPreferredWidth(77);
	}



	private class LayerTable extends DefaultTableModel{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public Class<?> getColumnClass(int columnIndex) {
			switch (columnIndex) {
				case 0:
					return Integer.class;
				case 1:
					return Boolean.class;
				case 2:
					return enumActivationFuncion.class;
				case 3:
					return Double.class;
				default:
					return null;
			}
		}
		
		@Override
		public boolean isCellEditable(int row, int col){
			return true;
		}
		
	}
	
}
