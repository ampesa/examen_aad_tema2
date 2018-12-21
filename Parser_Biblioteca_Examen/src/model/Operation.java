package model;

public class Operation {

	private String tipo;
	private int cantidad;
	private double precio;
	
	public Operation (String t, int c, double p){
		this.tipo = t;
		this.cantidad = c;
		this.precio = p;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
	
	
	
}
