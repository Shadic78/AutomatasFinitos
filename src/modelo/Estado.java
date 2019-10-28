package modelo;

import processing.core.PApplet;

public class Estado {
	private int x;
	private int y;
	private String nombre;
	private int radio;
	PApplet parent;
	
	public Estado(PApplet p, int x, int y, String nombre) {
		parent = p;
		this.radio = 40;
		this.x = x;
		this.y = y;
		this.nombre = nombre;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void setRadio(int radio) {
		this.radio = radio;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public int getRadio() {
		return this.radio;
	}
	
	public void show() {
		parent.fill(81, 237, 236);
		parent.strokeWeight(5);
		parent.stroke(70, 206, 205);
		parent.ellipse(this.x, this.y, this.radio, this.radio);
		parent.fill(0);
		parent.text(nombre, this.x, this.y);
	}

}
