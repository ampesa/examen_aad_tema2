package model;

import java.util.ArrayList;

public class Action {

	private String nombre;
	private ArrayList<Operation> operaciones;
	
	public Action(){
		
	}
	
	public Action(String n, ArrayList<Operation> op){
		this.nombre = n;
		this.operaciones = op;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public ArrayList<Operation> getOperaciones() {
		return operaciones;
	}

	public void setOperaciones(ArrayList<Operation> operaciones) {
		this.operaciones = operaciones;
	}
	
	// Método para mostrar los datos del objeto libro
	public String printAction() {
		StringBuilder str = new StringBuilder("\n");

		str.append("NOMBRE: "+ nombre + " - \n");

		for (int i=0; i<operaciones.size(); i++){
			str.append("TIPO: "+ operaciones.get(i).getTipo() + "\n");
			str.append("CANTIDAD: " + operaciones.get(i).getCantidad() + "\n");
			str.append("PRECIO: " + operaciones.get(i).getPrecio() + "\n\n");
		}
		
		return str.toString();
	}
	
}
