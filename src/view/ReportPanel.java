package view;

import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;

import javafx.embed.swing.JFXPanel;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import model.HistoricalData;
import net.sourceforge.jdatepicker.JDatePicker;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

public class ReportPanel extends JPanel {

	/**
	 * generated ID
	 */
	private static final long serialVersionUID = 1548849106952724610L;
	
	NewView view;
	private JDatePicker txtFrom = new JDatePickerImpl(new JDatePanelImpl(new UtilDateModel()));
	private JDatePicker txtTo = new JDatePickerImpl(new JDatePanelImpl(new UtilDateModel()));

	private JFormattedTextField txtInferiorLimit;
	private JFormattedTextField txtSuperiorLimit;
	private JFormattedTextField txtMargin;
	
	
	public ReportPanel(NewView view) {
		this.view = view;
		
		this.setLayout(null);
		
		// Dates
			JLabel lblFrom = new JLabel("From");
			lblFrom.setBounds(10, 11, 160, 14);
			this.add(lblFrom);
			
			((Component) txtFrom).setBounds(10, 26, 160, 27);
			this.add((Component) txtFrom);
			
			JLabel lblTo = new JLabel("To");
			lblTo.setBounds(10, 61, 160, 14);
			this.add(lblTo);
			
			((Component) txtTo).setBounds(10, 76, 160, 27);
			this.add((Component) txtTo);
		
		// Button
			JButton btnCreateReport = new JButton("Create Report");
			btnCreateReport.setBounds(10, 134, 159, 26);
			this.add(btnCreateReport);
		
		
		// Normalization Panel		
			JPanel panelNormalization = new JPanel();
			panelNormalization.setLayout(null);
			panelNormalization.setBorder(BorderFactory.createTitledBorder("Normalization"));
			panelNormalization.setBounds(207, 10, 160, 150);
			this.add(panelNormalization);
			
			JLabel lblInferiorLimit = new JLabel("Inferior Limit");
			lblInferiorLimit.setBounds(10, 25, 138, 14);
			panelNormalization.add(lblInferiorLimit);
			
			txtInferiorLimit = new JFormattedTextField();
			txtInferiorLimit.setBounds(10, 40, 138, 20);
			panelNormalization.add(txtInferiorLimit);
			
			JLabel lblSuperiorLimit = new JLabel("Superior Limit");
			lblSuperiorLimit.setBounds(10, 65, 138, 14);
			panelNormalization.add(lblSuperiorLimit);
			
			txtSuperiorLimit = new JFormattedTextField();
			txtSuperiorLimit.setBounds(10, 80, 138, 20);
			panelNormalization.add(txtSuperiorLimit);
			
			JLabel lblMargin = new JLabel("Margin %");
			lblMargin.setBounds(10, 104, 138, 14);
			panelNormalization.add(lblMargin);
			
			txtMargin = new JFormattedTextField();
			txtMargin.setBounds(10, 118, 138, 20);
			panelNormalization.add(txtMargin);
			
		// Graph
			JFXPanel panelGraph = new JFXPanel();
			panelGraph.setBounds(10, 172, 357, 294);
			this.add(panelGraph);
	}
	
	public void populateGraph(HistoricalData nnData, HistoricalData realData){
		double minValue;
		double maxValue;
		
		final CategoryAxis xAxis = new CategoryAxis();
		final NumberAxis yAxis = new NumberAxis("Y-Axis", normal.getMinValue() * 0.9, normal.getMaxValue() * 1.1, 1.0);
        
	}
	
	private double getMinValue(HistoricalData d1, HistoricalData d2){
		double rtn = 0;
		
		double minD1 = d1.getMapHistorical().stream().min();
		
		return rtn;
	}

}
