package modelo;

import processing.core.PApplet;

public class ConexionCurva {
	private PApplet parent;
	private int xMedio;
	private int yMedio;
	private float angulo;
	private int xControl;
	private int yControl;
	private int alturaCurvatura;
	private String condicion;
	private Estado origen;
	private Estado destino;
	// Variables de la punta de flecha
	private float anguloFlecha;
	
	public ConexionCurva(PApplet p, Estado origen, Estado destino, String condicion) {
		this.parent = p;
		this.origen = origen;
		this.destino = destino;
		this.alturaCurvatura = 40;
		this.condicion = condicion;
		
		actualizarPuntoDeControl();
	}
	
	public void show() {
		actualizarPuntoDeControl();
		parent.noFill();
		parent.strokeWeight(2);
		parent.stroke(0);
		parent.bezier(origen.getX(), origen.getY(), xMedio + xControl, yMedio + yControl, 
				xMedio + xControl, yMedio + yControl, destino.getX(), destino.getY());	
		dibujarPuntaDeFlecha();
		
		 /*Esto es para ver los puntos de control del bezier
		  * el punto de control del bezier es (xControl + xMedio, yControl + yMedio),
		  * aqui para evitar esa suma utilice translate();
		  *
		parent.fill(255, 140, 90);
		parent.ellipse(xMedio, yMedio, 10, 10);
		parent.pushMatrix();
		parent.translate(xMedio, yMedio);
		parent.ellipse(xControl, yControl, 15, 15);
		parent.popMatrix();
		*/		
	}

	public void actualizarPuntoDeControl() {
		/* Calcular los datos para trazar el bezier */
		// Se calcula el punto medio entre los vertices
		xMedio = (origen.getX() + destino.getX()) / 2;
		yMedio = (origen.getY() + destino.getY()) / 2;
		// Se calcula el angulo
		angulo = PApplet.atan2(destino.getY() - origen.getY(), destino.getX() - origen.getX()) - PApplet.radians(90);
		// Se calcula las coordenadas de los puntos de control del bezier
		xControl = (int) (alturaCurvatura * PApplet.cos(angulo));
		yControl = (int) (alturaCurvatura * PApplet.sin(angulo));		
	}
	
	public void dibujarPuntaDeFlecha() {
		/*
		 * Para calcular donde se debe dibujar la flecha lo que hago
		 * es calcular el angulo entre el vertice destino y el punto de control del bezier (xControl + xMedio, yControl + yMedio)
		 * y de esa manera dibujo la flecha respecto a la recta que va del punto de control del bezier
		 * hasta el vertice destino
		 */
		anguloFlecha = PApplet.atan2(destino.getY() - (yControl + yMedio), destino.getX() - (xControl + xMedio));
		
		parent.pushMatrix();
		parent.translate(destino.getX(), destino.getY());
		parent.rotate(anguloFlecha);
	    
		parent.stroke(0);
	    parent.fill(0);
	    parent.triangle(-5 - (destino.getRadio() + 5) , -5, 
	    	      -5 - (destino.getRadio() + 5), 5, 
	    	      -(destino.getRadio() + 5), 0);	    
		parent.popMatrix();
		
		// Esto es para ver la recta con respecto a la cual se dibuja la punta de flecha
		//parent.fill(129, 80, 240);
		//parent.line(xControl + xMedio, yControl + yMedio, destino.getX(), destino.getY());
		
	}
	
}
