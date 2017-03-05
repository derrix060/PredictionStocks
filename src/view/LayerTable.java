package view;

import javax.swing.table.DefaultTableModel;

import factories.ActivationFunctionFactory.enumActivationFuncion;

public class LayerTable extends DefaultTableModel {

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
