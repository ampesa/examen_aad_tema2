package model;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;

public class Book implements Serializable {

	private String titulo = null;
	private String autor = null;
 	private int anyo = 0;
 	private ArrayList<String> autores;
 	private ArrayList<Autor> objectsAutor;
	private String editor = null;
	private int paginas = 0;
	
	// Constructores
	public Book(){
		
	}
	
	public Book (String t, int y, String a,  String e, int p){
		titulo = t;
		anyo = y;
		autor = a;
		editor = e;
		paginas = p;
	}
	/*
	public Book (String t, int y, ArrayList<String> au,  String e, int p){
		titulo = t;
		anyo = y;
		autores = au;
		editor = e;
		paginas = p;
	}*/
	
	public Book (String t, int y, ArrayList<Autor> obAu,  String e, int p){
		titulo = t;
		anyo = y;
		objectsAutor = obAu;
		editor = e;
		paginas = p;
	}
	
	// Getters y Setters
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}
	
	public ArrayList<String> getAutores() {
		return autores;
	}

	public void setAutores(ArrayList<String> autores) {
		this.autores = autores;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public int getAnyo() {
		return anyo;
	}

	public void setAnyo(int anyo) {
		this.anyo = anyo;
	}

	public String getEditor() {
		return editor;
	}

	public void setEditor(String editor) {
		this.editor = editor;
	}

	public int getNum_paginas() {
		return paginas;
	}

	public void setNum_paginas(int num_paginas) {
		this.paginas = num_paginas;
	}
	
	// Método para mostrar los datos del objeto libro
	public String printBook() {
		StringBuilder str = new StringBuilder("\n");

		str.append("TÍTULO: "+titulo + " - ");
		/*
		Iterator<String> autorIterator = autores.iterator();
		while (autorIterator.hasNext()){
			str.append(autorIterator.next().toString() + ", ");
		}*/
		/*
		for (int i=0; i<autores.size(); i++){
			str.append(autores.get(i));
			if (i+1<autores.size()){
				str.append(", ");
			} else {
				str.append(" ");
			}
		}*/
		for (int i=0; i<objectsAutor.size(); i++){
			str.append(objectsAutor.get(i).getName());
			if (i+1<objectsAutor.size()){
				str.append(", ");
			} else {
				str.append(" ");
			}
		}
		
		str.append("AUTOR: " + autor + " - ");
		str.append("AÑO: " + anyo + " - ");
		str.append("EDITOR: " + editor + " - ");
		str.append("PÁGINAS: " + paginas);
		
		return str.toString();
	}
	/*
	public class Autor {
		private String name;
		
		public Autor(String name){
			this.name = name;
		}
		
	}*/
	
}
