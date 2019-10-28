package modelo;

import processing.core.PApplet;

public class Conexion {
	PApplet parent;
	private Estado estadoInicio;
	private Estado estadoDestino;
	private String condicion;
	
	public Conexion(PApplet p, Estado estado1, Estado estado2, String condicion) {
		this.parent = p;
		this.estadoInicio = estado1;
		this.estadoDestino = estado2;
		this.condicion = condicion;
	}
	
	public void show() {
		parent.stroke(0);
		parent.strokeWeight(2);
		parent.line(estadoInicio.getX(), estadoInicio.getY(), estadoDestino.getX(), estadoDestino.getY());
	}

}
