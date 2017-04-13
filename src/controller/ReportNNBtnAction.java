package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Calendar;

import javax.swing.JOptionPane;

import model.HistoricalData;
import model.NeuralNetwork;
import model.Normalizer;
import view.ReportPanel;

public class ReportNNBtnAction  implements ActionListener{

	private final ReportPanel panel;
	
	public ReportNNBtnAction(ReportPanel panel) {
		this.panel = panel;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		// Check if have a name
		String name = panel.getView().getNNName();
		
		if (name.isEmpty()){
			JOptionPane.showMessageDialog(null, "Please choose your neural network!", "Error: don't have NN selected", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		// Check if values is corrects
		if (!panel.valuesCorrects()){
			JOptionPane.showMessageDialog(null, "Please complete all fields with valid data!", "Error!", JOptionPane.ERROR_MESSAGE);
			return;
		}
		

		try {
			Normalizer norm = panel.getNormalizer();
			Calendar from = panel.getFrom();
			Calendar to = panel.getTo();
			
			NeuralNetwork nn = panel.getView().getActiveNN();
			
			HistoricalData realData = new HistoricalData(nn.getStock(), from, to, nn.getDateInterval(), nn.getAttributes());
			HistoricalData nnData = nn.createNNHistoricalData(norm, from, to);
			
			this.panel.populateGraph(nnData, realData, this.panel.panelGraph);
			
			
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(null, "Error! - " + e1.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
			return;
		}

	}
}





