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
	boolean AddEstado = false;
	boolean MoverEstado = false;
	AutomataFinito automata1;
	
	// Variables para almacenar los estados a los que se les dio click
	int estadoClick1 = -1;
	int estadoClick2 = -1;
	
	/* Variable que controla el estado global del programa
	 * 0 - Default
	 * 1 - Agregar estados
	 * 2 - Mover estados
	 * 3 - Borrar estados
	 * 4 - Agregar conexion normal (Al dar click a un estado el estado del programa cambia a 5)
	 * 5 - (Dar click en el segundo estado)
	 * 6 - Borrar conexion
	 */
	int estadoDelPrograma = 0;	
	
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
    	text("Estado del programa: " + estadoDelPrograma, width / 2, height - 80);
    }
    
    public void mouseClicked() {
    	// Este if es para que no pongan vertices en el area donde estan los botones
    	if(mouseX < width - 150) {
    		// De acuerdo al estado del programa se hace una cosa u otra
        	switch(estadoDelPrograma) {
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
	    				estadoClick1 = automata1.getEstadoClickeado();
	    				actualizarEstadoDelPrograma(5);
	    				println("estado id: " + estadoClick1);
	    			}
	    			break;
	    		case 5: // Segundo click para agregar una conexion entre estados
	    			if(automata1.getEstadoClickeado() >= 0) {
	    				estadoClick2 = automata1.getEstadoClickeado();
	    				automata1.agregarConexion(estadoClick1, estadoClick2, "a");
	    				println("estado id2: " + estadoClick2);
	    				estadoClick1 = -1;
	    				estadoClick2 = -1;
	    				actualizarEstadoDelPrograma(4);
	    			}	    			
	    			break;
	    		default:
	    			println("mouseclicked default");
        	}    		
    	}
    }
    
    public void mouseDragged() {
    	if(estadoDelPrograma == 2) {
        	if(!automata1.getMoviendoEstado()) {
        		automata1.setEstadoMoviendoID(automata1.getEstadoClickeado());
        	}
        	if(automata1.getEstadoMoviendoID() >= 0) {
        		automata1.setMoviendoEstado(true);
        		automata1.listaEstados.get(automata1.getEstadoMoviendoID()).setX(mouseX);
        		automata1.listaEstados.get(automata1.getEstadoMoviendoID()).setY(mouseY);
        	}    		
    	}
    }
    
    public void mouseReleased() {
    	if(automata1.getMoviendoEstado()) {
    		automata1.setMoviendoEstado(false);
    		automata1.setEstadoMoviendoID(-1);
    	}
    }
    
    // De acuerdo al estado del programa desactiva los otros botones
    public void actualizarEstadoDelPrograma(int estado) {
    	estadoDelPrograma = estado;
    	// Desactiva el boton que no hayas presionado de acuerdo al estado del programa
    	switch(estadoDelPrograma) {
    		case 1:
        		((Toggle) ventana.getController("MoverEstado")).setState(false);
        		((Toggle) ventana.getController("AgregarConexion")).setState(false);           		
        		println("Estado 1");        		
        		break;
    		case 2: 
        		((Toggle) ventana.getController("AddEstado")).setState(false);
        		((Toggle) ventana.getController("AgregarConexion")).setState(false);           		
        		println("Estado 2");        		
        		break;
    		case 3:
    			break;
    		case 4:
        		((Toggle) ventana.getController("MoverEstado")).setState(false);       			
        		((Toggle) ventana.getController("AddEstado")).setState(false);   	
        		println("Estado 4");    			
    			break;
        	default:
        		println("Estado default");
    	}    	
    }
    
    /********** EVENTOS DE LOS BOTONES **********/
    public void AddEstado() {
    	if(((Toggle)ventana.getController("AddEstado")).isMousePressed()) {
        	if(((Toggle)ventana.getController("AddEstado")).getState() == true) {
            	actualizarEstadoDelPrograma(1);
        	}
        	else {
        		actualizarEstadoDelPrograma(0);
        	}    		
    	}
    }
    
    public void MoverEstado() {
    	if(((Toggle)ventana.getController("MoverEstado")).isMousePressed()) {
        	if(((Toggle)ventana.getController("MoverEstado")).getState() == true) {
        		actualizarEstadoDelPrograma(2);
        	}   
        	else {
        		actualizarEstadoDelPrograma(0);
        	}    		
    	}
    }
    
    public void AgregarConexion() {
    	if(((Toggle)ventana.getController("AgregarConexion")).isMousePressed() && automata1.listaEstados.size() >= 2) {
        	if(((Toggle)ventana.getController("AgregarConexion")).getState() == true) {
        		actualizarEstadoDelPrograma(4);
        	}   
        	else {
        		actualizarEstadoDelPrograma(0);
        	}    		
    	}
    }
    /******** FIN EVENTOS DE LOS BOTONES ********/   
}
