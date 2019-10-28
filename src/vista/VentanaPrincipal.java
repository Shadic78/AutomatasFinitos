package vista;

import controlP5.ControlP5;
import processing.core.PApplet;

/*
 * En esta clase se declaran
 * los objetos graficos que tendra la interfaz
 * de usuario del programa
 */
public class VentanaPrincipal {
	public ControlP5 cp5;
	private PApplet parent;
	
	public VentanaPrincipal(PApplet p) {
		this.parent = p;
    	cp5 = new ControlP5(p);	
    	
    	/**	Se crean los botones **/
    	cp5.addToggle("AddEstado")
    	.setPosition(parent.width - 130, 80)
    	.setSize(100,40)
    	.setLabel("Agregar estado")
    	.getCaptionLabel().align(ControlP5.CENTER, ControlP5.CENTER); 
    	
    	cp5.addToggle("MoverEstado")
    	.setPosition(parent.width - 130, 140)
    	.setSize(100,40)
    	.setLabel("Mover estado")
    	.getCaptionLabel().align(ControlP5.CENTER, ControlP5.CENTER); 
    	
    	cp5.addToggle("AgregarConexion")
    	.setPosition(parent.width - 130, 200)
    	.setSize(100,40)
    	.setLabel("Agregar conexion")
    	.getCaptionLabel().align(ControlP5.CENTER, ControlP5.CENTER);     	
	}
	
	public ControlP5 getControlP5() {
		return this.cp5;
	}
	
}
