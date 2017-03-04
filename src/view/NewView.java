package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.encog.neural.networks.layers.BasicLayer;

import net.sourceforge.jdatepicker.JDatePicker;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;
import process.ActivationFunctionFactory;
import process.ActivationFunctionFactory.enumActivationFuncion;
import process.PropagationFactory.enumTrainingType;
import types.Data.enumAttributesOfData;
import types.NeuralNetwork;

import javax.swing.border.TitledBorder;

public class NewView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private JPanel 					contentPane;
	private LayerTable 				dtm 			= new LayerTable();
	private JTable 					tableLayer 		= new JTable();
	private Object[] 				emptyData 		= {Integer.valueOf(1), Boolean.TRUE, enumActivationFuncion.BiPolar, new Double(1.1)};
	
	
	//Necessary objects to control in other class
			//Data
				private JDatePicker txtFrom = new JDatePickerImpl(new JDatePanelImpl(new UtilDateModel()));
				private JDatePicker txtTo = new JDatePickerImpl(new JDatePanelImpl(new UtilDateModel()));
				private JComboBox<String> cmbStock = new JComboBox<>();
				private JFormattedTextField txtDataInterval = NumberTextField.newField(Integer.class, 1, Integer.MAX_VALUE);
			//Stock attributes
				private JCheckBox chckbxHighPrice = new JCheckBox("High Price");
				private JCheckBox chckbxOpenPrice = new JCheckBox("Open Price");
				private JCheckBox chckbxClosePrice = new JCheckBox("Close Price");
				private JCheckBox chckbxLowPrice = new JCheckBox("Low Price");
				private JCheckBox chckbxVolume = new JCheckBox("Volume");
			//normalization
				private JFormattedTextField txtSuperiorLimit = NumberTextField.newField(Double.class, 0, 1);
				private JFormattedTextField txtInferiorLimit = NumberTextField.newField(Double.class, -1, 0);
				private JFormattedTextField txtMargin = NumberTextField.newField(Float.class, Float.MIN_NORMAL, Float.MAX_VALUE);
			//Training
				private JComboBox<enumTrainingType> cmbTraining = new JComboBox<>();
				private JDatePicker txtToT = new JDatePickerImpl(new JDatePanelImpl(new UtilDateModel()));
				private JFormattedTextField txtMaxIteration = NumberTextField.newField(Integer.class, 1, Integer.MAX_VALUE);
				private JFormattedTextField txtMinError = NumberTextField.newField(Double.class, 0d, 1d);
			//Network
				private JTextField txtName;
				private JTextField txtStock;
				private NeuralNetwork activeNetwork = null;
	
	
	
	
	
	
	
	
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

		JPanel panelCreate = createCreatePanel();
		tabbedPane.addTab("Create", null, panelCreate, null);
		
			
		JPanel panelChoose = createChoosePanel();
		tabbedPane.addTab("Choose", null, panelChoose, null);
		
		JPanel panelTrain = createTrainPanel();
		tabbedPane.addTab("Train", null, panelTrain, null);
		
		JPanel panelReport = createReportPanel();
		tabbedPane.addTab("Report", null, panelReport, null);
		
		JLabel lblSelectedNetwork = new JLabel("Selected Network: ");
		lblSelectedNetwork.setBounds(20, 536, 91, 14);
		contentPane.add(lblSelectedNetwork);
		
		JLabel lblNetw = new JLabel("None");
		lblNetw.setBounds(128, 536, 266, 14);
		contentPane.add(lblNetw);
	}
	
	
	/**
	 * Create Panels
	 */
	
	private JPanel createCreatePanel(){
		JPanel createPanel = new JPanel();
		createPanel.setLayout(null);
		
			//Name
				JLabel lblName = new JLabel("Name");
				lblName.setBounds(10, 11, 150, 14);
				createPanel.add(lblName);
				
				txtName = new JTextField();
				txtName.setBounds(10, 28, 167, 20);
				createPanel.add(txtName);
				txtName.setColumns(10);
				
			//Attributes
				JPanel panelAttr = new JPanel();
				panelAttr.setLayout(null);
				panelAttr.setBorder(new TitledBorder(null, "Attributes", TitledBorder.LEADING, TitledBorder.TOP, null, null));
				panelAttr.setBounds(244, 11, 107, 160);
				createPanel.add(panelAttr);
			
				
				chckbxClosePrice.setBounds(6, 26, 96, 23);
				chckbxHighPrice.setBounds(6, 52, 96, 23);
				chckbxLowPrice.setBounds(6, 78, 96, 23);
				chckbxOpenPrice.setBounds(6, 104, 96, 23);
				chckbxVolume.setBounds(6, 130, 96, 23);
				
				panelAttr.add(chckbxClosePrice);
				panelAttr.add(chckbxHighPrice);				
				panelAttr.add(chckbxLowPrice);				
				panelAttr.add(chckbxOpenPrice);				
				panelAttr.add(chckbxVolume);
				
			//Date Interval
				JLabel lblDtInterval = new JLabel("Date Interval");
				lblDtInterval.setBounds(10, 59, 150, 15);
				createPanel.add(lblDtInterval);
				

				txtDataInterval.setBounds(10, 76, 167, 20);
				createPanel.add(txtDataInterval);
				
			//Stock
				JLabel label = new JLabel("Stock");
				label.setBounds(10, 107, 150, 14);
				createPanel.add(label);
				
				txtStock = new JTextField();
				txtStock.setBounds(10, 124, 167, 20);
				createPanel.add(txtStock);
		
			//Hidden Layer	
				
				JLabel lblHiddenLayer = new JLabel("Hidden Layer");
				lblHiddenLayer.setBounds(10, 164, 84, 14);
				createPanel.add(lblHiddenLayer);
				
				//Add button
					JButton btnAdd = new JButton("");
					btnAdd.setIcon(new ImageIcon(MainView.class.getResource("/view/add.png")));
					btnAdd.setForeground(new Color(0, 128, 0));
					btnAdd.setBounds(84, 155, 41, 23);
					btnAdd.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							dtm.addRow(emptyData);
							tableLayer.setModel(dtm);
							
						}
					});
					createPanel.add(btnAdd);
			
				//Remove button
					JButton btnRemove = new JButton("");
					btnRemove.setIcon(new ImageIcon(MainView.class.getResource("/view/cancel.png")));
					btnRemove.setBounds(136, 155, 41, 23);
					btnRemove.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							
							//if don't have any row
							if (tableLayer.getRowCount() == 0) return;
							
							try{
								dtm.removeRow(tableLayer.getSelectedRow());
							}
							
							//if nothing is selected -> remove the last one
							catch (ArrayIndexOutOfBoundsException ae){
								dtm.removeRow(tableLayer.getRowCount() -1);
							}
						}
					});
					createPanel.add(btnRemove);	
				
				//Start Layer Table
					initializeLayerTable();
					JScrollPane scrollPaneLayers = new JScrollPane(tableLayer);
					scrollPaneLayers.setSize(343, 247);
					scrollPaneLayers.setLocation(10, 189);
					tableLayer.setFillsViewportHeight(true);
					
					createPanel.add(scrollPaneLayers);
				
				
			// Btns
				JButton btnNewButton = new JButton("Create");
				btnNewButton.setBounds(264, 447, 89, 23);
				createPanel.add(btnNewButton);
				btnNewButton.addActionListener(new CreateNNBtnAction(this));
				
				
			return createPanel;
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
	
	public ArrayList<enumAttributesOfData> getAtributes(){
		ArrayList<enumAttributesOfData> rtn = new ArrayList<>();

		if (isHighPrice()) rtn.add(enumAttributesOfData.highPrice);
		if (isClosePrice()) rtn.add(enumAttributesOfData.closePrice);
		if (isLowPrice()) rtn.add(enumAttributesOfData.lowPrice);
		if (isOpenPrice()) rtn.add(enumAttributesOfData.openPrice);
		if (isVolume()) rtn.add(enumAttributesOfData.volume);
		
		
		return rtn;
	}
	
	public List<BasicLayer> getLayers(){
		List<BasicLayer> layers = new ArrayList<>();
		
		//inputLayer
		BasicLayer layer = new BasicLayer(this.getAtributes().size() * this.getDataInterval());
		layers.add(layer);
		
		//hiddenLayer
		for (int i=0; i<tableLayer.getRowCount(); i++){
			//for each row from table
			Integer neurons = (Integer) tableLayer.getValueAt(i, 0);
			Boolean hasBias = (Boolean) tableLayer.getValueAt(i, 1);
			enumActivationFuncion actFct = (enumActivationFuncion) tableLayer.getValueAt(i, 2);
			Double drop = (Double) tableLayer.getValueAt(i, 3);
			
			layer = new BasicLayer(ActivationFunctionFactory.create(actFct), hasBias, neurons, drop);
			layers.add(layer);
		}
		
		//outputLayer
		layer = new BasicLayer(this.getAtributes().size());
		layers.add(layer);
		
		return layers;
	}
	
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

	public String getStock() {
		return (String) cmbStock.getSelectedItem();
	}

	public Integer getDataInterval() {
		return (Integer) txtDataInterval.getValue();
	}

	public Boolean isHighPrice() {
		return chckbxHighPrice.isSelected();
	}

	public Boolean isOpenPrice() {
		return chckbxOpenPrice.isSelected();
	}

	public Boolean isClosePrice() {
		return chckbxClosePrice.isSelected();
	}

	public Boolean isLowPrice() {
		return chckbxLowPrice.isSelected();
	}

	public Boolean isVolume() {
		return chckbxVolume.isSelected();
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
	}
	public NeuralNetwork getActiveNN(){
		return this.activeNetwork;
	}
}
