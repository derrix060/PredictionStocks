package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class MainButtonAction implements ActionListener {
	private final MainView mview;
	public MainButtonAction(MainView view) { 
		mview = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JOptionPane.showMessageDialog(null, mview.getTxtFrom());

	}
	
	private boolean isDateCorrect(){
		
		
		return true;
	}

}
