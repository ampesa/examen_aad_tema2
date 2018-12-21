package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import model.DataManager;
import view.LaunchView;

public class EventManager {
	
	private DataManager model;
	private LaunchView view;
	private ActionListener actionListener_parse;
	
	// Constructor
	public EventManager (DataManager model, LaunchView view) {
		this.model = model;
		this.view = view;
	}
	
	// Método contenedor de los listeners
	public void control(){
		
		// Implementación del listener botón Parse. Llama al método call_ParseLibrary()
		actionListener_parse = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				// Ejecutamos call_ParseLibrary()
				try {
					call_ParseXML();
				} catch (IOException e) {
					// Mostramos el error a través del método showError de la clase LaunchView
					view.showError("Fallo al parsear el fichero");
				}

			}
		};
		// Añadimos el listener al botón
		view.getParser().addActionListener(actionListener_parse);
		
	}
	
	// Método que llama a la función de la clase DataManager para parsear el XML
	private void call_ParseXML() throws FileNotFoundException, IOException {
		
		// Si no se ha introducido un fichero mostramos el error. Si el método arroja alguno de las exceciones se mostrará la excecpción 
		if (view.getFichero().getText().equals("")){
			view.showError("Error. Debe introducir el nombre de un archivo xml");
		} else if (model.parseXML(view.getFichero().getText()) == 1){
			view.showError("Error: ParserConfigurationException");
		} else if (model.parseXML(view.getFichero().getText()) == 2){
			view.showError("Error: SAXException");
		} else if (model.parseXML(view.getFichero().getText()) == 3){
			view.showError("Error: No se encuentra el archivo. FileNotFoundException");
		} else if (model.parseXML(view.getFichero().getText()) == 4){
			view.showError("Error: IOException");
		}  else {
			// Si no hay errores se ejecutará el código
			model.parseXML(view.getFichero().getText());
			model.parseDocument();
			view.writeTextArea(model.print());
		}
	}

}
