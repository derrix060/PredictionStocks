package view;

import java.awt.Component;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.ReportNNBtnAction;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.embed.swing.JFXPanel;
import javafx.event.Event;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.input.ScrollEvent;
import model.Data.enumAttributesOfData;
import model.HistoricalData;
import model.Normalizer;
import net.sourceforge.jdatepicker.JDatePicker;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

/**
 * Panel to show the prediction NN performance
 * @author mario
 *
 */
public class ReportPanel extends JPanel {

	/**
	 * generated ID
	 */
	private static final long serialVersionUID = 1548849106952724610L;
	
	private NewView view;
	private JDatePicker txtFrom = new JDatePickerImpl(new JDatePanelImpl(new UtilDateModel()));
	private JDatePicker txtTo = new JDatePickerImpl(new JDatePanelImpl(new UtilDateModel()));

	private JFormattedTextField txtInferiorLimit;
	private JFormattedTextField txtSuperiorLimit;
	private JFormattedTextField txtMargin;
	public JFXPanel panelGraph;
	

	private final DoubleProperty zoomProperty = new SimpleDoubleProperty(200);
	
	/**
	 * Constructor to Report Panel.
	 * @param view - Which view called this panel.
	 */
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
			btnCreateReport.addActionListener(new ReportNNBtnAction(this));
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
			txtInferiorLimit.setValue("");
			panelNormalization.add(txtInferiorLimit);
			
			JLabel lblSuperiorLimit = new JLabel("Superior Limit");
			lblSuperiorLimit.setBounds(10, 65, 138, 14);
			panelNormalization.add(lblSuperiorLimit);
			
			txtSuperiorLimit = new JFormattedTextField();
			txtSuperiorLimit.setBounds(10, 80, 138, 20);
			txtSuperiorLimit.setValue("");
			panelNormalization.add(txtSuperiorLimit);
			
			JLabel lblMargin = new JLabel("Margin %");
			lblMargin.setBounds(10, 104, 138, 14);
			panelNormalization.add(lblMargin);
			
			txtMargin = new JFormattedTextField();
			txtMargin.setBounds(10, 118, 138, 20);
			txtMargin.setValue("");
			panelNormalization.add(txtMargin);
			
		// Graph
			panelGraph = new JFXPanel();
			panelGraph.setBounds(10, 172, 357, 294);
			this.add(panelGraph);
	}
	
	/**
	 * Write graph with HistoricalData. Don't neet to be normalized!
	 * @param nnData
	 * @param realData
	 */
	public void populateGraph(HistoricalData nnData, HistoricalData realData, JFXPanel panel){
		final CategoryAxis xAxis = new CategoryAxis();
		final NumberAxis yAxis = new NumberAxis();
		yAxis.setForceZeroInRange(false);
		//final NumberAxis yAxis = new NumberAxis("Y-Axis", getMinValue(nnData, realData) * 0.9, getMaxValue(nnData, realData)  * 1.1, 1.0);
        
		xAxis.setLabel("Date");
		
		
		
		final LineChart<String, Number> lineChart = new LineChart<>(xAxis, yAxis);
		
		lineChart.setTitle("Neural Network report - " + view.getActiveNN().getStock());
	
		Scene scene = new Scene(lineChart, 357, 294);
		
		scene.addEventHandler(ScrollEvent.ANY, new javafx.event.EventHandler<Event>(){

			@SuppressWarnings("unused")
			public void handle(ScrollEvent event) {
				if (event.getDeltaY() > 0) {
                    zoomProperty.set(zoomProperty.get() * 1.1);
                } else if (event.getDeltaY() < 0) {
                    zoomProperty.set(zoomProperty.get() / 1.1);
                }
			}

			@Override
			public void handle(Event arg0) { }
			
		});
		
		populateSeries(nnData, realData, lineChart);

		//yAxis.setLowerBound(getMinValue(nnData, realData) * 0.95);
		//yAxis.setUpperBound(getMaxValue(nnData, realData) * 1.05);
		
		
		panel.setScene(scene);
	}
	
	/**
	 * Create and populate Historical Datas to graph
	 * @param nnData - Data calculated by NN
	 * @param realData - Real data from the same period
	 * @param lineChart - line to be populated
	 */
	@SuppressWarnings("unchecked")
	private void populateSeries(HistoricalData nnData, HistoricalData realData, LineChart<String,Number> lineChart){
		Series<String, Number> nnSerie = new Series<>();


        //realSerie -> must be first!
        for (enumAttributesOfData attr : this.view.getActiveNN().getAttributes()){
        	nnSerie = new Series<>();
        	
        	nnSerie = createSeries(realData,attr, "Real - " + attr.toString());
            lineChart.getData().addAll(nnSerie);
        	
        }
        
        //nnSerie
        for (enumAttributesOfData attr : this.view.getActiveNN().getAttributes()){
        	nnSerie = new Series<>();
        	
        	nnSerie = createSeries(nnData,attr, "NN - " + attr.toString());
            lineChart.getData().addAll(nnSerie);
        	
        }
        
        
	}
	
	/**
	 * Crete series from HistoricalData to be used in a chart.
	 * @param data - A Historical Data.
	 * @param attr - A single attribute.
	 * @param name - Name to be showd in a chart.
	 * @return serie - A serie from the single attribute of data.
	 */
	private Series<String,Number> createSeries (HistoricalData data, enumAttributesOfData attr, String name){
		Series<String, Number> rtn = new Series<>();
		
		rtn.setName(name);

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        
		for (model.Data dt : data.getMapHistorical()){
			rtn.getData().add(new Data<String, Number>(formatter.format(dt.getDate().getTime()), dt.getValue(attr)));
		}
		
		return rtn;
	}
	

	/**
	 * Get the view where it panel belongs to.
	 */
	public NewView getView() {
		return this.view;
	}
	
	/**
	 * Get the report's end date from Panel
	 * @return - End date
	 */
	public Calendar getFrom(){
		Calendar cal = Calendar.getInstance();
		cal.setTime((Date) txtFrom.getModel().getValue());
		return cal;
	}
	

	/**
	 * Get the report's begin date from Panel
	 * @return - Begin date
	 */
	public Calendar getTo(){
		Calendar cal = Calendar.getInstance();
		cal.setTime((Date) txtTo.getModel().getValue());
		return cal;
	}
	
	/**
	 * Create a normalizer with values from Panel
	 * @return - A normalizer
	 */
	public Normalizer getNormalizer(){
		return new Normalizer(
				Float.parseFloat((String) txtMargin.getValue()) / 100f,
				Double.parseDouble((String) txtSuperiorLimit.getValue()),
				Double.parseDouble((String) txtInferiorLimit.getValue()));
	}
	
	/**
	 * Check if all fields were filled correctly.
	 * It include if weren't filled and if
	 * dates are right.
	 * @return 
	 */
	public boolean valuesCorrects(){
		try{
			String valueTemp;
			valueTemp = txtInferiorLimit.getValue().toString();
			if(valueTemp.equals("")) return false;

			valueTemp = (String) txtSuperiorLimit.getValue();
			if(valueTemp.equals("")) return false;

			valueTemp = (String) txtMargin.getValue();
			if(valueTemp.equals("")) return false;

			
			if(txtTo.getModel().getValue() == null) return false;
			if(txtFrom.getModel().getValue() == null) return false;
			
			//Date
				if(getTo().before(getFrom())) return false;
				Calendar tempDate = Calendar.getInstance();
				tempDate.add(Calendar.DAY_OF_MONTH, -2);
				if(getTo().after(tempDate)) return false;
			
			return true;
		}
		catch (NullPointerException e) {
			e.printStackTrace();
			return false;
		}
	}

}
