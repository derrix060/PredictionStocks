package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.AbstractTableModel;
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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		
		
		
		// TBL
		
		//Start create JTable
		
			tableLayers = new JTable();
			
			class RowData{
		    	private Map<Integer, Object> values = new HashMap<Integer, Object>();
		    	
		    	public RowData() {
					// TODO Auto-generated constructor stub
		    		this.setValueForCol(Integer.valueOf(1), 0);
		    		this.setValueForCol(Boolean.TRUE, 1);
		    		this.setValueForCol(enumActivationFuncion.BiPolar, 2);
		    		this.setValueForCol(new Double(1.1), 3);
				}
		    	
		    	public Object getValueForCol(int columnIndex){
		    		if (values.containsKey(columnIndex)){
		    			return values.get(columnIndex);
		    		}
		    		return "";
		    	}
		    	
		    	public void setValueForCol (Object value, int columnIndex){
		    		values.put(columnIndex, value);
		    	}
		    }
		
		    class layerTableModel extends AbstractTableModel {
		        /**
				 * 
				 */
				private static final long serialVersionUID = 1L;
				
				private String[] columnNames = {"Neurons", "HasBias?", "Activation Function", "DropOut Rate"};
				private List<RowData> rows = new ArrayList<RowData>();
				
		      
		        @Override
		        public int getColumnCount() {
		            return columnNames.length;
		        }
		        
		        @Override
		        public int getRowCount() {
		            return rows.size();
		        }
		        
		        @Override
		        public String getColumnName(int col) {
		            return columnNames[col];
		        }
		        
		
		        @SuppressWarnings({ "unchecked", "rawtypes" })
				public Class getColumnClass(int c) {
		            return getValueAt(0, c).getClass();
		        }
		
		        @Override
		        public boolean isCellEditable(int row, int col) {
		            return true;
		        }
		
		        @Override
		        public Object getValueAt(int row, int col) {
		            RowData data = rows.get(row);
		            //return data.getValueForCol(cols.get(col));
		            return data.getValueForCol(col);
		        }
		        
		        @Override
		        public void setValueAt(Object value, int row, int col) {
		            RowData data = rows.get(row);
		            //data.setValueForCol(value, cols.get(col));
		            data.setValueForCol(value, col);
		            
		            fireTableCellUpdated(row, col);
		        }
		        
		        public void addRow(){
		        	rows.add(new RowData());
		        	fireTableRowsInserted(rows.size(), rows.size());
		        }
		        
		        public void removeRow(int selectedRow){
		        	rows.remove(selectedRow);
		        	fireTableRowsDeleted(selectedRow, selectedRow);
		        }
		        
		        
		    }
		
		   
			
			
			layerTableModel model = new layerTableModel();
			
			JButton addBtn = new JButton("");
			addBtn.setIcon(new ImageIcon(MainView.class.getResource("/view/add.png")));
			addBtn.setForeground(new Color(0, 128, 0));
			addBtn.setBounds(10, 25, 41, 23);
			addBtn.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					model.addRow();
				}
			});
			panelNetworkConf.add(addBtn);
			
			JButton rmvBtn = new JButton("");
			rmvBtn.setIcon(new ImageIcon(MainView.class.getResource("/view/cancel.png")));
			rmvBtn.setBounds(61, 25, 41, 23);
			rmvBtn.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					int selectedRow = tableLayers.getSelectedRow();
					if (selectedRow != -1){
						model.removeRow(selectedRow);
					}
				}
			});
			panelNetworkConf.add(rmvBtn);
			
			
		
			JScrollPane scrollPaneLayers = new JScrollPane(tableLayers);
			scrollPaneLayers.setSize(338, 430);
			scrollPaneLayers.setLocation(10, 59);
			tableLayers.setFillsViewportHeight(true);
		
			panelNetworkConf.add(scrollPaneLayers);
			//panelNetworkConf.add(new TableRenderDemo());
			
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
