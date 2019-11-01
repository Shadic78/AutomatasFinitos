package controlador;

import controlP5.ControlP5;
import controlP5.Toggle;
import modelo.AutomataFinito;
import modelo.Estado;
import processing.core.PApplet;
import vista.VentanaPrincipal;

public class Main extends PApplet {
	
	// El objeto que usaremos para crear la interfaz de usuario
	ControlP5 ventana;
	AutomataFinito automata1;
	EstadoDelPrograma controladorPrograma;	
	
    public static void main(String[] args) {
        PApplet.main("controlador.Main");
    }

    public void settings(){
    	//size(640, 480);
    	fullScreen();
    }
    
    public void setup() {
    	// Se crea un objeto VentanaPrincipal que es la que contiene
    	// los elementos graficos con los que interactuara el usuario
    	VentanaPrincipal ventana1 = new VentanaPrincipal(this);
    	// Se asigna la ventana
    	ventana = ventana1.getControlP5();
    	
    	automata1 = new AutomataFinito(this);
    	controladorPrograma = new EstadoDelPrograma(this, ventana);
    	
    	textAlign(CENTER, CENTER);
    	textSize(14);
    	noStroke();    	
    }
    
    public void draw(){
    	background(232, 233, 234);
    	noStroke();
    	fill(255, 218, 143);
    	rect(width - 160, 0, 160, height);
    	
    	automata1.imprimirConexiones();
    	automata1.imprimirEstados();
    	
    	fill(0);
    	text("Estado del programa: " + controladorPrograma.getEstadoDelPrograma(), width / 2, height - 80);
    }
    
    public void mouseClicked() {
    	// Este if es para que no pongan vertices en el area donde estan los botones
    	if(mouseX < width - 150) {
    		// De acuerdo al estado del programa se hace una cosa u otra
        	switch(controladorPrograma.getEstadoDelPrograma()) {
	    		case 1: // Agregar vertices
	    	    	Estado estadoNuevo = new Estado(this, mouseX, mouseY, "VerticeNuevo");
	    	    	automata1.agregarEstado(estadoNuevo);
	    	    	break;
	    		case 2: // Mover vertices
	    			println("mouseclicked 2");
	    			break;
	    		case 3: // Borrar vertices
	    			break;
	    		case 4: // Primer click para agregar una conexion entre estados
	    			if(automata1.getEstadoClickeado() >= 0) {
	    				controladorPrograma.setEstadoClick1(automata1.getEstadoClickeado());
	    				controladorPrograma.actualizarEstadoDelPrograma(5);
	    				println("estado id: " + controladorPrograma.getEstadoClick1());
	    			}
	    			break;
	    		case 5: // Segundo click para agregar una conexion entre estados
	    			if(automata1.getEstadoClickeado() >= 0) {
	    				controladorPrograma.setEstadoClick2(automata1.getEstadoClickeado());
	    				automata1.agregarConexion(controladorPrograma.getEstadoClick1(), controladorPrograma.getEstadoClick2(), "a");
	    				println("estado id2: " + controladorPrograma.getEstadoClick2());
	    				controladorPrograma.setEstadoClick1(-1);
	    				controladorPrograma.setEstadoClick2(-1);
	    				controladorPrograma.actualizarEstadoDelPrograma(4);
	    			}	    			
	    			break;
	    		default:
	    			println("mouseclicked default");
        	}    		
    	}
    }
    
    public void mouseDragged() {
    	if(controladorPrograma.getEstadoDelPrograma() == 2) {
        	if(!controladorPrograma.getMoviendoEstado()) {
        		controladorPrograma.setEstadoMoviendoID(automata1.getEstadoClickeado());
        	}
        	if(controladorPrograma.getEstadoMoviendoID() >= 0) {
        		controladorPrograma.setMoviendoEstado(true);
        		automata1.listaEstados.get(controladorPrograma.getEstadoMoviendoID()).setX(mouseX);
        		automata1.listaEstados.get(controladorPrograma.getEstadoMoviendoID()).setY(mouseY);
        	}    		
    	}
    }
    
    public void mouseReleased() {
    	if(controladorPrograma.getMoviendoEstado()) {
    		controladorPrograma.setMoviendoEstado(false);
    		controladorPrograma.setEstadoMoviendoID(-1);
    	}
    }
    
    /********** EVENTOS DE LOS BOTONES **********/
    public void AddEstado() {
    	if(((Toggle)ventana.getController("AddEstado")).isMousePressed()) {
        	if(((Toggle)ventana.getController("AddEstado")).getState() == true) {
            	controladorPrograma.actualizarEstadoDelPrograma(1);
        	}
        	else {
        		controladorPrograma.actualizarEstadoDelPrograma(0);
        	}    		
    	}
    }
    
    public void MoverEstado() {
    	if(((Toggle)ventana.getController("MoverEstado")).isMousePressed()) {
        	if(((Toggle)ventana.getController("MoverEstado")).getState() == true) {
        		controladorPrograma.actualizarEstadoDelPrograma(2);
        	}   
        	else {
        		controladorPrograma.actualizarEstadoDelPrograma(0);
        	}    		
    	}
    }
    
    public void AgregarConexion() {
    	if(((Toggle)ventana.getController("AgregarConexion")).isMousePressed() && automata1.listaEstados.size() >= 2) {
        	if(((Toggle)ventana.getController("AgregarConexion")).getState() == true) {
        		controladorPrograma.actualizarEstadoDelPrograma(4);
        	}   
        	else {
        		controladorPrograma.actualizarEstadoDelPrograma(0);
        	}    		
    	}
    }
    /******** FIN EVENTOS DE LOS BOTONES ********/   
}
