package controlador;

import controlP5.ControlP5;
import controlP5.Toggle;
import processing.core.PApplet;

public class EstadoDelPrograma {
	/* Variable que controla el estado global del programa
	 * 0 - Default
	 * 1 - Agregar estados
	 * 2 - Mover estados
	 * 3 - Borrar estados
	 * 4 - Agregar conexion normal (Al dar click a un estado el estado del programa cambia a 5)
	 * 5 - (Dar click en el segundo estado)
	 * 6 - Borrar conexion
	 */
	private int estadoDelPrograma = 0;
	
	private PApplet parent;
	private ControlP5 ventana;
	// Variables para almacenar los estados a los que se les dio click
	private int estadoClick1 = -1;
	private int estadoClick2 = -1;
	// Variables para saber si se esta moviendo un estado y cual es
	private boolean moviendoEstado;
	private int estadoMoviendoID;
	
	public EstadoDelPrograma(PApplet p, ControlP5 ventana) {
		this.parent = p;
		this.ventana = ventana;
		// Para evitar problemas al mover estados por primera vez
		this.moviendoEstado = false;
		this.estadoMoviendoID = -1;		
	}
		
    public int getEstadoClick1() {
		return estadoClick1;
	}

	public void setEstadoClick1(int estadoClick1) {
		this.estadoClick1 = estadoClick1;
	}

	public int getEstadoClick2() {
		return estadoClick2;
	}

	public void setEstadoClick2(int estadoClick2) {
		this.estadoClick2 = estadoClick2;
	}

	public int getEstadoDelPrograma() {
		return estadoDelPrograma;
	}

	public void setEstadoDelPrograma(int estadoDelPrograma) {
		this.estadoDelPrograma = estadoDelPrograma;
	}

	public boolean getMoviendoEstado() {
		return moviendoEstado;
	}

	public void setMoviendoEstado(boolean moviendoEstado) {
		this.moviendoEstado = moviendoEstado;
	}

	public int getEstadoMoviendoID() {
		return estadoMoviendoID;
	}

	public void setEstadoMoviendoID(int estadoMoviendoID) {
		this.estadoMoviendoID = estadoMoviendoID;
	}

	// De acuerdo al estado del programa desactiva los otros botones
    public void actualizarEstadoDelPrograma(int estado) {
    	estadoDelPrograma = estado;
    	// Desactiva los botones que no hayas presionado de acuerdo al estado del programa
    	switch(estadoDelPrograma) {
    		case 1:
        		((Toggle) ventana.getController("MoverEstado")).setState(false);
        		((Toggle) ventana.getController("AgregarConexion")).setState(false);           		
        		PApplet.println("Estado 1");        		
        		break;
    		case 2: 
        		((Toggle) ventana.getController("AddEstado")).setState(false);
        		((Toggle) ventana.getController("AgregarConexion")).setState(false);           		
        		PApplet.println("Estado 2");        		
        		break;
    		case 3:
    			break;
    		case 4:
        		((Toggle) ventana.getController("MoverEstado")).setState(false);       			
        		((Toggle) ventana.getController("AddEstado")).setState(false);   	
        		PApplet.println("Estado 4");    			
    			break;
        	default:
        		PApplet.println("Estado default");
    	}    	
    }
    
}
