package model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class DataManager {
	
	
	// Definimos un objeto Document
	private Document dom = null;
	// Definimos un ArrayList del tipo <Action> que albergará los resutlados del parser
	private ArrayList<Action> actions = null;
	
	// Inicializamos el ArrayList desde el constructor
	public DataManager () {
		actions = new ArrayList<Action>();
	}
	
	// Método para parsear un archivo XML y pasarlo a un Document con estructura DOM
	public int parseXML(String fichero) {
		// Creamos un Factory
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		
		try{
			// Creamos un DocumentBuilder
			DocumentBuilder db = dbf.newDocumentBuilder();
			// parseamos el XML y obtenemos una representación DOM
			dom = db.parse(fichero);
			return 0;
			// Controlamos las excepciones
		}catch(ParserConfigurationException pce){
			pce.printStackTrace();
			return 1;
		}catch(SAXException se){
			se.printStackTrace();
			return 2;
		}catch (FileNotFoundException fnfe){
			fnfe.printStackTrace();
			return 3;
		}catch(IOException ioe){
			ioe.printStackTrace();
			return 4;
		}
	}
	
	// Método para parserar un Document (DOM) y almacenar los elementos en un ArrayList
	public void parseDocument(){
		// Obtenemos el elemento raíz del xml
		Element docEle = dom.getDocumentElement();
		
		// Obtenemos el nodelist de elementos
		NodeList nl = docEle.getElementsByTagName("accion");

		// Si la lista no es nula y la longitud es mayor que 0
		if (nl!=null && nl.getLength() > 0){
			// Para cada nodo mientras i sea menor que la longitud de la lista
			for (int i = 0; i < nl.getLength(); i++){
				
				// Obtenemos el elemento de la lista (libro)
				Element el = (Element) nl.item(i);
				
				// Creamos un libro y le pasamos el elemento
				Action a = getAction(el);
				// lo añadimos al array de libros
				actions.add(a);
			}
		}
		
	}
	
	// Método que devuelve una acción a partir de un determinado elemento que hemos pasado como parámetro
	private Action getAction(Element actionEle) {
		// para cada elemento Accion obtendermos sus datos
		String nombre = getActionName(actionEle, "accion", "nombre");
		ArrayList<Operation> operaciones = getOperations(actionEle, "operacion");

		// Creamos una nueva Acción con los elementos leídos del nodo
		Action e = new Action (nombre, operaciones);
		
		// El método devolverá el libro creado
		return e;
	}

	// Método que devuelve un String con el atributo "nombre" de la acción
	private String getActionName (Element ele, String tagName, String attributeName){
		// Creamos un String
		String textVal = null;
		
		// Utilizamos una lista de nodos a la que pasamos los elementos del Element por su nombre
		NodeList nl = ele.getElementsByTagName(tagName);
		
		// Si la lista no es nula y no está vacía
		if (nl != null && nl.getLength() > 0){
			// Creamos un nuevo elemento y le pasamos el primer item de la lista (0)
			Element el = (Element) nl.item(0);
			// Después pasamos al String el valor del primer hijo del elemento creado con getFirstChild y getNodeValue
			textVal = el.getAttribute(attributeName);
		}
		
		// Devolvemos el String con el valor del nodo
		return textVal;
	}

	
	// Método que devuelve un String con el StringBuilder de los Autores del libro en caso de que sean más de uno
	private ArrayList<Operation> getOperations (Element ele, String tagName){
		// Creamos un String
		ArrayList<Operation> operaciones = new ArrayList<Operation>();

		// Utilizamos una lista de nodos a la que pasamos los elementos del Element por su nombre
		NodeList nl = ele.getElementsByTagName(tagName);
		
		// Si la lista no es nula y no está vacía
		if (nl != null && nl.getLength() > 0){
			for (int i = 0; i<nl.getLength(); i++){
				// Creamos un nuevo elemento y le pasamos el primer item de la lista (0)
				Element el = (Element) nl.item(i);
				// Después creamos un objeto Operation pasándole como parámetro el elemento
				Operation operacion = getOperation(el);
				operaciones.add(operacion);
			}
		}
		// Devolvemos el String con el valor del nodo
		return operaciones;
	}
	
	private Operation getOperation (Element actionEle) {
		// para cada elemento Accion obtendermos sus datos
		String tipo = getTextValue (actionEle, "tipo");
		int cantidad = getIntCantidad(actionEle, "cantidad");
		double precio = getDoublePrecio(actionEle, "precio");

		// Creamos una nueva Operation con los elementos leídos del nodo
		Operation o = new Operation (tipo, cantidad, precio);
		
		// El método devolverá la operación creada
		return o;
	}
	
	// Método que devuelve un String con el texto de un nodo a partir del Element y su tagName
	private String getTextValue (Element ele, String tagName){
		// Creamos un String
		String textVal = null;
		
		// Utilizamos una lista de nodos a la que pasamos los elementos del Element por su nombre
		NodeList nl = ele.getElementsByTagName(tagName);
		
		// Si la lista no es nula y no está vacía
		if (nl != null && nl.getLength() > 0){
			// Creamos un nuevo elemento y le pasamos el primer item de la lista (0)
			Element el = (Element) nl.item(0);
			// Después pasamos al String el valor del primer hijo del elemento creado con getFirstChild y getNodeValue
			textVal = el.getFirstChild().getNodeValue();
		}
		
		// Devolvemos el String con el valor del nodo
		return textVal;
	}

	// Método que devuelve un int a partir de un elemento Operación y un nodo, en este caso "cantidad"
	private int getIntCantidad (Element ele, String tagName){
		// Devolvemos el entero resultante de llamar al método getTextValue con los parámetros ele y tagName
		return Integer.parseInt(getTextValue (ele, tagName));
	}

	// Método que devuelve un double a partir de un elemento operación y un nodo, en este caso "precio"
	private double getDoublePrecio (Element ele, String tagName){
		// Devolvemos el entero resultante de llamar al método getTextValue con los parámetros ele y tagName
		return Double.parseDouble(getTextValue (ele, tagName));
	}
	
	
	// Método para recorrer el ArrayList de actions y pasarlo a un string
	public String print(){
		// Creamos un Iterator para recorrer el ArrayList
		Iterator it = actions.iterator();
		
		StringBuilder str = new StringBuilder();
		
		// Mientras haya un elemento siguiente
		while (it.hasNext()){
			// Creamos una Action y le pasamos el casting del Iterator siguiente
			Action x = (Action) it.next();
			// Imprimimos los datos de la acción con el método printAction de su clase
			str.append(x.printAction());
		}
		return str.toString();
	}

}
