package view;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
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
	public ResultView(HistoricalData nnData, HistoricalData realData) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 590, 655);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JFXPanel fxPanel = new JFXPanel();
		fxPanel.setBounds(10, 11, 554, 435);
		
		//test
		final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
         xAxis.setLabel("Date");
        final LineChart<String,Number> lineChart = 
                new LineChart<String,Number>(xAxis,yAxis);
       
        lineChart.setTitle("Neural Network Test - " + realData.getMapHistorical().get(0).getTicker());
        
        Series<String, Number> nnSerie = new Series<>();
        nnSerie.setName("NN Data");

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        
        for (types.Data dt : nnData.getMapHistorical()){
        	nnSerie.getData().add(new Data<String, Number>(formatter.format(dt.getDate().getTime()), dt.getHighPrice()));
        }
        
        
        
        Scene scene  = new Scene(lineChart,554,435);       
        lineChart.getData().addAll(nnSerie);
        
        fxPanel.setScene(scene);
        //end test
        
		contentPane.add(fxPanel);
	}
}
