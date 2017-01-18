package view;

import java.text.NumberFormat;

import javax.swing.JFormattedTextField;
import javax.swing.text.NumberFormatter;

public class NumberTextField extends JFormattedTextField {

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
