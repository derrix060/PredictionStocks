package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import org.encog.neural.networks.BasicNetwork;
import org.encog.neural.networks.layers.BasicLayer;
import org.encog.neural.networks.layers.Layer;

import controller.ChooseNNBtnAction;
import controller.CreateNNBtnAction;
import factories.ActivationFunctionFactory;
import factories.ActivationFunctionFactory.enumActivationFuncion;
import model.Data.enumAttributesOfData;
import model.NeuralNetwork;

public class NNPanel extends JPanel {

	/**
	 * Generated serial ID
	 */
	private static final long serialVersionUID = 1706251343663324042L;
	
	// Table
	private LayerTable dtm = new LayerTable();
	private JTable tableLayer = new JTable();
	private Object[] emptyData = {Integer.valueOf(3), Boolean.TRUE, enumActivationFuncion.ElliottSymmetric, new Double(0.3)};
	
	// Date
	private JFormattedTextField txtDataInterval = NumberTextField.newField(Integer.class, 1, Integer.MAX_VALUE);
	
	// Stock attributes
	private JCheckBox chckbxHighPrice = new JCheckBox("High Price");
	private JCheckBox chckbxOpenPrice = new JCheckBox("Open Price");
	private JCheckBox chckbxClosePrice = new JCheckBox("Close Price");
	private JCheckBox chckbxLowPrice = new JCheckBox("Low Price");
	private JCheckBox chckbxVolume = new JCheckBox("Volume");

	
	// Network
	private JTextField txtNNName;
	private JTextField txtStock;
	
	//Who called
	private NewView frame;
	
	/**
	 * Constructor
	 * set Layout to absolute
	 */
	public NNPanel(NewView frame) {
		super();
		this.setLayout(null);
		this.frame = frame;
		
		//Name
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(10, 11, 150, 14);
		this.add(lblName);
		
		txtNNName = new JTextField();
		txtNNName.setBounds(10, 28, 167, 20);
		txtNNName.setText("teste"); //test
		this.add(txtNNName);
		txtNNName.setColumns(10);
		
	//Attributes
		JPanel panelAttr = new JPanel();
		panelAttr.setLayout(null);
		panelAttr.setBorder(new TitledBorder(null, "Attributes", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelAttr.setBounds(244, 11, 107, 160);
		this.add(panelAttr);

		
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
		this.add(lblDtInterval);
		

		txtDataInterval.setBounds(10, 76, 167, 20);
		txtDataInterval.setValue(4); //test
		this.add(txtDataInterval);


		
	//Stock
		JLabel label = new JLabel("Stock");
		label.setBounds(10, 107, 150, 14);
		this.add(label);
		
		txtStock = new JTextField();
		txtStock.setBounds(10, 124, 167, 20);
		txtStock.setText("PETR4.SA"); //test
		this.add(txtStock);

	//Hidden Layer	
		
		JLabel lblHiddenLayer = new JLabel("Hidden Layer");
		lblHiddenLayer.setBounds(10, 164, 84, 14);
		this.add(lblHiddenLayer);
		
		//Add button
			JButton btnAdd = new JButton("");
			btnAdd.setIcon(new ImageIcon(_old_MainView.class.getResource("/view/add.png")));
			btnAdd.setForeground(new Color(0, 128, 0));
			btnAdd.setBounds(90, 155, 41, 23);
			btnAdd.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					dtm.addRow(emptyData);
					tableLayer.setModel(dtm);
					
				}
			});
			this.add(btnAdd);
	
		//Remove button
			JButton btnRemove = new JButton("");
			btnRemove.setIcon(new ImageIcon(_old_MainView.class.getResource("/view/cancel.png")));
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
			this.add(btnRemove);	
		
		//Start Layer Table
			initializeLayerTable();
			JScrollPane scrollPaneLayers = new JScrollPane(tableLayer);
			scrollPaneLayers.setSize(343, 247);
			scrollPaneLayers.setLocation(10, 189);
			tableLayer.setFillsViewportHeight(true);
			
			this.add(scrollPaneLayers);
		
		
	// Btns
		JButton btnCreateNN = new JButton("Create");
		btnCreateNN.setBounds(264, 447, 90, 23);
		this.add(btnCreateNN);
		btnCreateNN.addActionListener(new CreateNNBtnAction(this));
		
		JButton btnChooseNN = new JButton("Choose");
		btnChooseNN.setBounds(164, 447, 90, 23);
		this.add(btnChooseNN);
		btnChooseNN.addActionListener(new ChooseNNBtnAction(this));
		
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
	
	public String getStock() {
		return txtStock.getText();
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

	public String getNNName(){
		return this.txtNNName.getText();
	}
	
	public void setActiveNN(NeuralNetwork nn){
		this.frame.setActiveNN(nn);
	}
	
	/**
	 * Populate fields in Panel with NN information
	 * 
	 * @param nn
	 */
	public void updateFields(NeuralNetwork nn){
		
		// Clean old values
		cleanFields();
				
				
		this.txtDataInterval.setValue(nn.getDateInterval());
		this.txtStock.setText(nn.getStock());
		
		// Attributes
		ArrayList<enumAttributesOfData> attrs = nn.getAttributes();
		this.chckbxClosePrice.setSelected(attrs.contains(enumAttributesOfData.closePrice));
		this.chckbxHighPrice.setSelected(attrs.contains(enumAttributesOfData.highPrice));
		this.chckbxLowPrice.setSelected(attrs.contains(enumAttributesOfData.lowPrice));
		this.chckbxOpenPrice.setSelected(attrs.contains(enumAttributesOfData.openPrice));
		this.chckbxVolume.setSelected(attrs.contains(enumAttributesOfData.volume));
	
		
		// Clean hidden layer table
		while (dtm.getRowCount() > 0)
			dtm.removeRow(0);
		
		
		// Hidden Layers
		BasicNetwork topology = nn.getTopology();
		List<Layer> layers = topology.getStructure().getLayers();
		
		for (int i=1; i< layers.size() -1; i++){
			Layer layer = layers.get(i);
			// For each layer
			Object[] data = {layer.getNeuronCount(), layer.hasBias() , layer.getActivationFunction(), new Double(0.3)};
		}
		//TODO: check if layers.
	private void cleanFields(){
		this.txtDataInterval.setValue("");
		this.txtStock.setText("");
		
		while (dtm.getRowCount() > 0)
			dtm.removeRow(0);
		
		chckbxClosePrice.setSelected(false);
		chckbxHighPrice.setSelected(false);
		chckbxLowPrice.setSelected(false);
		chckbxOpenPrice.setSelected(false);
		chckbxVolume.setSelected(false);
	}
}
