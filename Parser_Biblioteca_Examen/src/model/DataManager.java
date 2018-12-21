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
	// Definimos un ArrayList del tipo <Persona> que albergará los resutlados del parser
	private ArrayList<Book> libros = null;
	
	// Inicializamos el ArrayList desde el constructor
	public DataManager () {
		libros = new ArrayList<Book>();
	}
	
	// Método para parsear un archivo XML y pasarlo a un Document con estructura DOM
	public int parseLibrary(String fichero) {
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
		NodeList nl = docEle.getElementsByTagName("libro");

		// Si la lista no es nula y la longitud es mayor que 0
		if (nl!=null && nl.getLength() > 0){
			// Para cada nodo mientras i sea menor que la longitud de la lista
			for (int i = 0; i < nl.getLength(); i++){
				
				// Obtenemos el elemento de la lista (libro)
				Element el = (Element) nl.item(i);
				
				// Creamos un libro y le pasamos el elemento
				Book b = getBook(el);
				// lo añadimos al array de libros
				libros.add(b);
			}
		}
		
	}
	
	// Método que devuelve un libro a partir de un determinado elemento que hemos pasado como parámetro
	private Book getBook(Element bookEle) {
		// para cada elemento libro obtendermos sus datos
		String titulo = getTextValue(bookEle, "titulo");
		System.out.println("El libro tiene el título: " + titulo);
		int anyo = getIntAnyo(bookEle, "titulo", "anyo");
		System.out.println("Publicado el año: " + anyo);
		ArrayList autores = getAutores(bookEle, "nombre");
		String editor = getTextValue(bookEle, "editor");
		int paginas = getIntValue(bookEle, "paginas");
		
		// Creamos un nuevo libro con los elementos leídos del nodo
		Book e = new Book (titulo, anyo, autores, editor, paginas);
		
		// El método devolverá el libro creado
		return e;
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
	
	// Método que devuelve un int con el año correspondiente al atributo anyo
	private int getIntAnyo (Element ele, String tagName, String attributeName){
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
		return Integer.parseInt(textVal);
	}
	
	// Método que devuelve un String con el StringBuilder de los Autores del libro en caso de que sean más de uno
	private String getAutor (Element ele, String tagName){
		// Creamos un String
		StringBuilder strVal = new StringBuilder();
		
		// Utilizamos una lista de nodos a la que pasamos los elementos del Element por su nombre
		NodeList nl = ele.getElementsByTagName(tagName);
		
		// Si la lista no es nula y no está vacía
		if (nl != null && nl.getLength() > 0){
			for (int i = 0; i<nl.getLength(); i++){
				// Creamos un nuevo elemento y le pasamos el primer item de la lista (0)
				Element el = (Element) nl.item(i);
				// Después pasamos al String el valor del primer hijo del elemento creado con getFirstChild y getNodeValue
				strVal.append(el.getFirstChild().getNodeValue() + ", ");
			}
		}
		// Devolvemos el String con el valor del nodo
		return strVal.toString();
	}
	
	// Método que devuelve un String con el StringBuilder de los Autores del libro en caso de que sean más de uno
	private ArrayList<Autor> getAutores (Element ele, String tagName){
		// Creamos un String
		ArrayList<Autor> autores = new ArrayList<Autor>();
		String name; 
		// Utilizamos una lista de nodos a la que pasamos los elementos del Element por su nombre
		NodeList nl = ele.getElementsByTagName(tagName);
		
		// Si la lista no es nula y no está vacía
		if (nl != null && nl.getLength() > 0){
			for (int i = 0; i<nl.getLength(); i++){
				// Creamos un nuevo elemento y le pasamos el primer item de la lista (0)
				Element el = (Element) nl.item(i);
				// Después pasamos al String el valor del primer hijo del elemento creado con getFirstChild y getNodeValue
				Autor autor = new Autor(el.getFirstChild().getNodeValue());
				autores.add(autor);
			}
		}
		// Devolvemos el String con el valor del nodo
		return autores;
	}
	
	// Método que devuelve un int a partir de un elemento Book y un nodo
	private int getIntValue (Element ele, String tagName){
		// Devolvemos el entero resultante de llamar al método getTextValue con los parámetros ele y tagName
		return Integer.parseInt(getTextValue (ele, tagName));
	}
	
	// Método para recorrer el ArrayList de Persona e imprimirlo por consola
	public String print(){
		// Creamos un Iterator para recorrer el ArrayList
		Iterator it = libros.iterator();
		
		StringBuilder str = new StringBuilder();
		
		// Mientras haya un elemento siguiente
		while (it.hasNext()){
			// Creamos un Book y le pasamos el casting del Iterator siguiente
			Book p = (Book) it.next();
			// Imprimimos los datos del libro con el método printBook de su clase
			str.append(p.printBook());
		}
		return str.toString();
	}

}
