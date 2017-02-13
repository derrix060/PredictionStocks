package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import org.encog.neural.networks.layers.BasicLayer;

public class CreateNNBtnAction implements ActionListener {
	private final NewView view;
	private String name;
	public CreateNNBtnAction(NewView view, String name) { 
		this.view = view;
		this.name = name;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		List<BasicLayer> layers = view.getLayers();
		
	}

}
