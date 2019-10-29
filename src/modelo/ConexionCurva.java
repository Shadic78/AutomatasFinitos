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
		
		/* Esto es para ver los puntos de control del bezier
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
	
}
