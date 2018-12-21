package principal;

import javax.swing.JFrame;

import controller.EventManager;
import model.DataManager;
import view.LaunchView;

public class Principal {

	public static void main(String[] args) {

		DataManager model = new DataManager();
		
		LaunchView view = new LaunchView();
		view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		view.setVisible(true);
		
		EventManager controller = new EventManager(model,view);
		controller.control();

	}

}
