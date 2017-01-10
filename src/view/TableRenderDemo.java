package view;

import javax.swing.JPanel;


/*
 * TableRenderDemo.java requires no other files.
 */

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

import process.ActivationFunctionFactory.enumActivationFuncion;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** 
 * TableRenderDemo is just like TableDemo, except that it
 * explicitly initializes column sizes and it uses a combo box
 * as an editor for the Sport column.
 */
public class TableRenderDemo extends JPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean DEBUG = false;

    public TableRenderDemo() {
        super(new GridLayout(1,0));
        
        layerTableModel model = new layerTableModel();
        JTable table = new JTable(model);
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);
        
        JButton addBtn = new JButton("Add row");
        addBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				model.addRow();
			}
		});
        
        JButton removeBtn = new JButton("Remove row");
        removeBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table.getSelectedRow();
				if (selectedRow != -1){
					model.removeRow(selectedRow);
				}
			}
		});

        this.add(addBtn);
        this.add(removeBtn);

        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);

        //Set up column sizes.
        initColumnSizes(table);

        //Fiddle with the Sport column's cell editors/renderers.
        setUpSportColumn(table, table.getColumnModel().getColumn(2));

        //Add the scroll pane to this panel.
        add(scrollPane);
    }
    

    /*
     * This method picks good column sizes.
     * If all column heads are wider than the column's cells'
     * contents, then you can just use column.sizeWidthToFit().
     */
    private void initColumnSizes(JTable table) {
        table.getColumnModel().getColumn(0).setPreferredWidth(54);
        table.getColumnModel().getColumn(1).setPreferredWidth(56);
        table.getColumnModel().getColumn(2).setPreferredWidth(103);
        table.getColumnModel().getColumn(3).setPreferredWidth(77);
        
    }

    public void setUpSportColumn(JTable table,
                                 TableColumn sportColumn) {
        //Set up the editor for the sport cells.
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
        sportColumn.setCellEditor(new DefaultCellEditor(comboAct));

        //Set up tool tips for the sport cells.
        DefaultTableCellRenderer renderer =
                new DefaultTableCellRenderer();
        renderer.setToolTipText("Click for combo box");
        sportColumn.setCellRenderer(renderer);
    }
    
    private class RowData{
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

    private class layerTableModel extends AbstractTableModel {
        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		private String[] columnNames = {"Neurons", "HasBias?", "Activation Function", "DropOut Rate"};
		public RowData emptyData = new RowData();
		
			//{Integer.valueOf(1), Boolean.TRUE, enumActivationFuncion.BiPolar, new Double(1.1)};
        
		//private List<Integer> cols = new ArrayList<Integer>();
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

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("TableRenderDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        TableRenderDemo newContentPane = new TableRenderDemo();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}