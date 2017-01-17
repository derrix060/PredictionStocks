package view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.NumberFormatter;

import net.sourceforge.jdatepicker.JDatePicker;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;
import process.ActivationFunctionFactory.enumActivationFuncion;
import process.PropagationFactory.enumTrainingType;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JFormattedTextField;
import javax.swing.JComboBox;
import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

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
		setBounds(100, 100, 590, 655);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Input Panel
			JPanel panelExtactData = new JPanel();
			panelExtactData.setToolTipText("");
			panelExtactData.setBorder(new LineBorder(new Color(171, 173, 179)));
			panelExtactData.setBounds(10, 11, 179, 414);
			contentPane.add(panelExtactData);
			panelExtactData.setLayout(null);
			panelExtactData.setBorder(BorderFactory.createTitledBorder("Extract Data"));

			//From
				JLabel lblFrom = new JLabel("From");
				lblFrom.setBounds(10, 25, 124, 14);
				panelExtactData.add(lblFrom);
				
				JDatePicker txtFrom = new JDatePickerImpl(new JDatePanelImpl(new UtilDateModel()));
				((Component) txtFrom).setBounds(10, 43, 159, 29);
				panelExtactData.add((Component) txtFrom);
			//To
				JLabel lblTo = new JLabel("To");
				lblTo.setBounds(10, 74, 159, 14);
				panelExtactData.add(lblTo);
				
				JDatePicker txtTo = new JDatePickerImpl(new JDatePanelImpl(new UtilDateModel()));
				((Component) txtTo).setBounds(10, 88, 159, 29);
				panelExtactData.add((Component) txtTo);
			//Stock
				JLabel lblStock = new JLabel("Stock");
				lblStock.setBounds(10, 164, 159, 14);
				panelExtactData.add(lblStock);
				
				JComboBox<String> cmbStock = new JComboBox<>();
					cmbStock.addItem("BBDC4.SA");
					cmbStock.addItem("ABEV3.SA");
					cmbStock.addItem("PETR4.SA");
					cmbStock.addItem("VALE5.SA");
					cmbStock.addItem("PETR3.SA");
					cmbStock.addItem("VALE3.SA");
					cmbStock.addItem("BRFS3.SA");
					cmbStock.addItem("BBAS3.SA");
					cmbStock.addItem("ITSA4.SA");
					cmbStock.addItem("BVMF3.SA");
					cmbStock.addItem("UGPA3.SA");
					cmbStock.addItem("CIEL3.SA");
					cmbStock.addItem("ITUB4.SA");
				cmbStock.setBounds(10, 181, 159, 20);
				panelExtactData.add(cmbStock);
			//Data Interval
				JLabel lblDataInterval = new JLabel("Data interval in days");
				lblDataInterval.setBounds(10, 119, 159, 14);
				panelExtactData.add(lblDataInterval);
				
				JFormattedTextField txtDataInterval = NumberTextField.newField(Integer.class, 1, Integer.MAX_VALUE);
				txtDataInterval.setBounds(10, 133, 159, 20);
				txtDataInterval.setValue(new Integer(5));
				panelExtactData.add(txtDataInterval);
		
			//Stock Atribute	
				JPanel panelStockAtribute = new JPanel();
				panelStockAtribute.setBorder(new LineBorder(new Color(0, 0, 0)));
				panelStockAtribute.setBounds(10, 226, 159, 181);
				panelExtactData.add(panelStockAtribute);
				panelStockAtribute.setLayout(null);
				panelStockAtribute.setBorder(BorderFactory.createTitledBorder("Stock Attribute"));
				
				JCheckBox chckbxClosePrice = new JCheckBox("Close Price");
				chckbxClosePrice.setBounds(6, 25, 147, 23);
				panelStockAtribute.add(chckbxClosePrice);
				
				JCheckBox chckbxHighPrice = new JCheckBox("High Price");
				chckbxHighPrice.setBounds(6, 53, 147, 23);
				panelStockAtribute.add(chckbxHighPrice);
				
				JCheckBox chckbxNewCheckBox_1 = new JCheckBox("Low Price");
				chckbxNewCheckBox_1.setBounds(6, 81, 147, 23);
				panelStockAtribute.add(chckbxNewCheckBox_1);
				
				JCheckBox chckbxOpenPrice = new JCheckBox("Open Price");
				chckbxOpenPrice.setBounds(6, 109, 147, 23);
				panelStockAtribute.add(chckbxOpenPrice);
				
				JCheckBox chckbxVolume = new JCheckBox("Volume");
				chckbxVolume.setBounds(6, 138, 147, 23);
				panelStockAtribute.add(chckbxVolume);
			//End Stock Atribute
				
		//End Input Panel
				
		//Normalization Panel
			JPanel panelNormalization = new JPanel();
			panelNormalization.setBounds(10, 436, 179, 173);
			contentPane.add(panelNormalization);
			panelNormalization.setBorder(BorderFactory.createTitledBorder("Normalization"));
			panelNormalization.setLayout(null);
			
			//Inferior Limit
				JLabel lblInferiorLimit = new JLabel("Inferior Limit");
				lblInferiorLimit.setBounds(10, 25, 159, 14);
				panelNormalization.add(lblInferiorLimit);
				
				JFormattedTextField txtInferiorLimit = NumberTextField.newField(Double.class, -1, 0);
				txtInferiorLimit.setBounds(10, 40, 159, 20);
				txtInferiorLimit.setValue(new Integer(-1));
				panelNormalization.add(txtInferiorLimit);
			//Superior Limit
				JLabel lblSuperiorLimit = new JLabel("Superior Limit");
				lblSuperiorLimit.setBounds(10, 69, 159, 14);
				panelNormalization.add(lblSuperiorLimit);
				
				JFormattedTextField txtSuperiorLimit = NumberTextField.newField(Double.class, 0, 1);
				txtSuperiorLimit.setBounds(10, 85, 159, 20);
				txtSuperiorLimit.setValue(new Integer(1));
				panelNormalization.add(txtSuperiorLimit);
			//Margin
				JLabel lblMargin = new JLabel("Margin %");
				lblMargin.setBounds(10, 116, 159, 14);
				panelNormalization.add(lblMargin);
				
				JFormattedTextField txtMargin = NumberTextField.newField(Double.class, Double.MIN_NORMAL, Double.MAX_VALUE);
				txtMargin.setBounds(10, 132, 159, 20);
				txtMargin.setValue(new Double(99.9));
				panelNormalization.add(txtMargin);
			
		//End Normalization Panel
			
		//Hidden Layer Panel	
			JPanel panelHiddenLayer = new JPanel();
			panelHiddenLayer.setLayout(null);
			panelHiddenLayer.setToolTipText("");
			panelHiddenLayer.setBorder(new LineBorder(new Color(171, 173, 179)));
			panelHiddenLayer.setBounds(199, 11, 372, 214);
			contentPane.add(panelHiddenLayer);
			panelHiddenLayer.setBorder(BorderFactory.createTitledBorder("Hidden Layer"));
			
			
	
			//Add button
				JButton btnAdd = new JButton("");
				btnAdd.setIcon(new ImageIcon(MainView.class.getResource("/view/add.png")));
				btnAdd.setForeground(new Color(0, 128, 0));
				btnAdd.setBounds(10, 27, 41, 23);
				btnAdd.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						dtm.addRow(emptyData);
						tableLayer.setModel(dtm);
						
					}
				});
				panelHiddenLayer.add(btnAdd);
			//Remove button
				JButton btnRemove = new JButton("");
				btnRemove.setIcon(new ImageIcon(MainView.class.getResource("/view/cancel.png")));
				btnRemove.setBounds(61, 27, 41, 23);
				btnRemove.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						dtm.removeRow(tableLayer.getSelectedRow());
						
					}
				});
				panelHiddenLayer.add(btnRemove);
			
			
			//Start Layer Table
				initializeLayerTable();
				JScrollPane scrollPaneLayers = new JScrollPane(tableLayer);
				scrollPaneLayers.setSize(348, 150);
				scrollPaneLayers.setLocation(10, 56);
				tableLayer.setFillsViewportHeight(true);
				
				panelHiddenLayer.add(scrollPaneLayers);
				
		//End Hidden Layer Panel	
		
		//Training Panel
			JPanel panelTraining = new JPanel();
			panelTraining.setToolTipText("");
			panelTraining.setBorder(new LineBorder(new Color(171, 173, 179)));
			panelTraining.setBounds(199, 236, 179, 373);
			contentPane.add(panelTraining);
			panelTraining.setBorder(BorderFactory.createTitledBorder("Training"));
			panelTraining.setLayout(null);
			
			//Learning Rule
				JLabel lblLearningRule = new JLabel("Learning Rule");
				lblLearningRule.setBounds(10, 25, 159, 14);
				panelTraining.add(lblLearningRule);
				
				JComboBox<enumTrainingType> cmbTraining = new JComboBox<>();
					cmbTraining.addItem(enumTrainingType.Backpropagation);
					cmbTraining.addItem(enumTrainingType.ManhattanPropagation);
					cmbTraining.addItem(enumTrainingType.QuickPropagation);
					cmbTraining.addItem(enumTrainingType.ResilientPropagation);
					cmbTraining.addItem(enumTrainingType.ScaledConjugateGradient);
				cmbTraining.setBounds(10, 44, 159, 20);
				panelTraining.add(cmbTraining);
			//From Training
				JLabel lblFromT = new JLabel("From");
				lblFromT.setBounds(10, 75, 159, 14);
				panelTraining.add(lblFromT);
				
				JDatePicker txtFromT = new JDatePickerImpl(new JDatePanelImpl(new UtilDateModel()));
				((Component) txtFromT).setBounds(10, 93, 159, 29);
				panelTraining.add((Component) txtFromT);
			//To Training
				JLabel lblToT = new JLabel("To");
				lblToT.setBounds(10, 124, 159, 14);
				panelTraining.add(lblToT);
				
				JDatePicker txtToT = new JDatePickerImpl(new JDatePanelImpl(new UtilDateModel()));
				((Component) txtToT).setBounds(10, 138, 159, 29);
				panelTraining.add((Component) txtToT);
			//Max Iteration
				JLabel lblMaxIteration = new JLabel("Max Iteration");
				lblMaxIteration.setBounds(10, 169, 159, 14);
				panelTraining.add(lblMaxIteration);
				
				JFormattedTextField txtMaxIteration = NumberTextField.newField(Integer.class, 1, Integer.MAX_VALUE);
				txtMaxIteration.setBounds(10, 185, 159, 20);
				txtMaxIteration.setValue(new Integer(5000));
				panelTraining.add(txtMaxIteration);
			//Min Error
				JLabel lblMinError = new JLabel("Min Error");
				lblMinError.setBounds(10, 216, 159, 14);
				panelTraining.add(lblMinError);
				
				JFormattedTextField txtMinError = NumberTextField.newField(Double.class, 0, 1);
				txtMinError.setBounds(10, 231, 159, 20);
				txtMinError.setValue(new Double(0.001));
				panelTraining.add(txtMinError);
		//End Training Panel	
				
				
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(395, 583, 89, 23);
		contentPane.add(btnNewButton);
	}

	private void initializeLayerTable(){
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

	private static class NumberTextField extends JFormattedTextField{
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public static JFormattedTextField newField(Class<?> classe, Comparable<?> minValue, Comparable<?> maxValue){
			NumberFormatter formatter = new NumberFormatter(NumberFormat.getInstance());
			formatter.setValueClass(classe);
			formatter.setMinimum(minValue);
			formatter.setMaximum(maxValue);
			formatter.setAllowsInvalid(true);
			formatter.setCommitsOnValidEdit(true);
			
			return new JFormattedTextField(formatter);
			
			
		}
	}
}
