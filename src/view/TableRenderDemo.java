package view;

import javax.swing.JPanel;


/*
 * TableRenderDemo.java requires no other files.
 */

import javax.swing.DefaultCellEditor;
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

        JTable table = new JTable(new MyTableModel());
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);

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

    class MyTableModel extends AbstractTableModel {
        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		private String[] columnNames = {"Neurons", "HasBias?", "Activation Function", "DropOut Rate"};
        
        private Object[][] data = {
				{Integer.valueOf(1), Boolean.TRUE, enumActivationFuncion.BiPolar, new Double(5.2)},
				{Integer.valueOf(2), Boolean.TRUE, enumActivationFuncion.Elliott, new Double(5.2)},
				{Integer.valueOf(3), Boolean.TRUE, enumActivationFuncion.Ramp, new Double(5.2)},
			};

        public int getColumnCount() {
            return columnNames.length;
        }

        public int getRowCount() {
            return data.length;
        }

        public String getColumnName(int col) {
            return columnNames[col];
        }

        public Object getValueAt(int row, int col) {
            return data[row][col];
        }

        /*
         * JTable uses this method to determine the default renderer/
         * editor for each cell.  If we didn't implement this method,
         * then the last column would contain text ("true"/"false"),
         * rather than a check box.
         */
        @SuppressWarnings({ "unchecked", "rawtypes" })
		public Class getColumnClass(int c) {
            return getValueAt(0, c).getClass();
        }

        /*
         * Don't need to implement this method unless your table's
         * editable.
         */
        /*
        public boolean isCellEditable(int row, int col) {
            //Note that the data/cell address is constant,
            //no matter where the cell appears onscreen.
            if (col < 2) {
                return false;
            } else {
                return true;
            }
        }
        */

        /*
         * Don't need to implement this method unless your table's
         * data can change.
         */
        /*
        public void setValueAt(Object value, int row, int col) {
            if (DEBUG) {
                System.out.println("Setting value at " + row + "," + col
                                   + " to " + value
                                   + " (an instance of "
                                   + value.getClass() + ")");
            }

            data[row][col] = value;
            fireTableCellUpdated(row, col);

            if (DEBUG) {
                System.out.println("New value of data:");
                printDebugData();
            }
        }
		*/
        /*
        private void printDebugData() {
            int numRows = getRowCount();
            int numCols = getColumnCount();

            for (int i=0; i < numRows; i++) {
                System.out.print("    row " + i + ":");
                for (int j=0; j < numCols; j++) {
                    System.out.print("  " + data[i][j]);
                }
                System.out.println();
            }
            System.out.println("--------------------------");
        }
        */
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