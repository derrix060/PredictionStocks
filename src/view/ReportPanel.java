package view;

import java.awt.Component;
import java.text.SimpleDateFormat;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;

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
	

	private final DoubleProperty zoomProperty = new SimpleDoubleProperty(200);
	
	/**
	 * Constructor to Report Panel.
	 * @param view - Wich view called this panel.
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
	
	/**
	 * Write graph with HistoricalData. Don't neet to be normalized!
	 * @param nnData
	 * @param realData
	 */
	public void populateGraph(HistoricalData nnData, HistoricalData realData){
		final CategoryAxis xAxis = new CategoryAxis();
		final NumberAxis yAxis = new NumberAxis("Y-Axis", getMinValue(nnData, realData) * 0.9, getMaxValue(nnData, realData)  * 1.1, 1.0);
        
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
	 * Get min value for all attributes from two Historical Datas
	 * @param d1 - One Historical Data
	 * @param d2 - Other Historical Data
	 * @return - min value
	 */
	private double getMinValue(HistoricalData d1, HistoricalData d2){
		double rtn = 0;
		double temp;
		
		for(enumAttributesOfData attr : view.getActiveNN().getAttributes()){
			temp = d1.getMapHistorical().stream().min((model.Data dt1, model.Data dt2) -> Double.compare(dt1.getValue(attr), dt2.getValue(attr))).get().getValue(attr);
			
			rtn = temp<rtn?temp:rtn;
		}
		
		return rtn;
	}

	/**
	 * Get max value for all attributes from two Historical Datas
	 * @param d1 - One Historical Data
	 * @param d2 - Other Historical Data
	 * @return - max value
	 */
	private double getMaxValue(HistoricalData d1, HistoricalData d2){
		double rtn = 0;
		double temp;
		
		for(enumAttributesOfData attr : view.getActiveNN().getAttributes()){
			temp = d1.getMapHistorical().stream().max((model.Data dt1, model.Data dt2) -> Double.compare(dt1.getValue(attr), dt2.getValue(attr))).get().getValue(attr);
			
			rtn = temp>rtn?temp:rtn;
		}
		
		return rtn;
	}

}
