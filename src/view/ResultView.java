package view;

import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import imports.Normalize;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import types.Data.enumAttributesOfData;
import types.HistoricalData;

public class ResultView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public ResultView(HistoricalData nnData, HistoricalData realData, Normalize normal, MainView mview) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 590, 655);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JFXPanel fxPanel = new JFXPanel();
		fxPanel.setBounds(10, 11, 554, 435);
		
		final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis("Y-Axis", normal.getMinValue() * 0.9, normal.getMaxValue() * 1.1, 1.0);
        System.out.println("ResultView -> min Value: " + normal.getMinValue() + " | max Value: " + normal.getMaxValue());
        xAxis.setLabel("Date");
        final LineChart<String,Number> lineChart = 
                new LineChart<String,Number>(xAxis,yAxis);
       
        lineChart.setTitle("Neural Network Test - " + realData.getMapHistorical().get(0).getTicker());

        Scene scene  = new Scene(lineChart,554,435);   

        populateSeries(nnData, realData, lineChart);
        fxPanel.setScene(scene);
        
		contentPane.add(fxPanel);
		
		
		//Back button
		Button btn = new Button("Back");
		btn.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent e) {
				closeFrame(mview);
				
			}
		});
		btn.setBounds(460,530,50,25);
		
		contentPane.add(btn);
	}
	
	private void closeFrame(MainView mview){
		this.dispose();
		mview.setVisible(true);
	}
	
	@SuppressWarnings("unchecked")
	private void populateSeries(HistoricalData nnData, HistoricalData realData, LineChart<String,Number> lineChart){
		Series<String, Number> nnSerie = new Series<>();


        //realSerie -> must be first!
        for (enumAttributesOfData attr : realData.getMapHistorical().get(0).getAttributes()){
        	nnSerie = new Series<>();
        	
        	nnSerie = createSeries(realData,attr, "Real - " + attr.toString());
            lineChart.getData().addAll(nnSerie);
        	
        }
        
        //nnSerie
        for (enumAttributesOfData attr : nnData.getMapHistorical().get(0).getAttributes()){
        	nnSerie = new Series<>();
        	
        	nnSerie = createSeries(nnData,attr, "NN - " + attr.toString());
            lineChart.getData().addAll(nnSerie);
        	
        }
	}
	
	private Series<String,Number> createSeries (HistoricalData data, enumAttributesOfData attr, String name){
		Series<String, Number> rtn = new Series<>();
		
		rtn.setName(name);

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        
		for (types.Data dt : data.getMapHistorical()){
			rtn.getData().add(new Data<String, Number>(formatter.format(dt.getDate().getTime()), dt.getValue(attr)));
		}
		
		return rtn;
	}
}
