package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.FileNotFoundException;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

import model.NeuralNetwork;
import model.Trainer;
import view.TrainPanel;

public class TrainNNBtnAction implements ActionListener, PropertyChangeListener {
	TrainPanel panel;
	NeuralNetwork nn;
	
	private Task task;

    class Task extends SwingWorker<Void, Void> {
        /*
         * Main task. Executed in background thread.
         */
        @Override
        public Void doInBackground() {
        	
        	try{
        		Trainer trainer = new Trainer(panel.getMargin(), panel.getInferiorLimit(), panel.getSuperiorLimit());
    			
    			ArrayList<Double> errors = trainer.train(nn, panel.getLearningRule(), panel.getMaxIteration(), panel.getMaxError(), panel.getFrom(), panel.getTo());
    			panel.populateGraph(errors);
    			
    			//save NN
    			nn.save(nn.getName(), true);
    			
    			JOptionPane.showMessageDialog(null, "Network trained with success!");
    			
        	} catch (UnknownHostException ex){
    			JOptionPane.showMessageDialog(null, "Error! - Network not fouded! ");
    			ex.printStackTrace();
    			
    		} catch (NullPointerException nEx){
    			JOptionPane.showMessageDialog(null, "Error! - Please select dates!");
    		} catch (FileNotFoundException fEx){
    			JOptionPane.showMessageDialog(null, "Error! - Don't exist data from selected dates!");
    		} catch (Exception e) {
    			JOptionPane.showMessageDialog(null, "Error! - " + e.getMessage());
    			e.printStackTrace();
    		}
        	
            return null;
        }

        @Override
        public void done() {
        	panel.getView().progressBar.setVisible(false);
        }

    }
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
	}
	
	
	public TrainNNBtnAction(TrainPanel trainPanel) {
		this.panel = trainPanel;
	}

	@Override
	public void actionPerformed(ActionEvent ae){
		nn = panel.getView().getActiveNN();
		if(nn==null){
			JOptionPane.showMessageDialog(null, "Please choose your NN!!");
			return;
		}

    	panel.getView().progressBar.setVisible(true);
		task = new Task();
		task.addPropertyChangeListener(this);
	    task.execute();
	}

}
