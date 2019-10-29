package modelo;

import java.util.ArrayList;

import processing.core.PApplet;

public class AutomataFinito {
	public ArrayList<Estado> listaEstados = new ArrayList<Estado>();
	public ArrayList<ConexionCurva> listaConexiones = new ArrayList<ConexionCurva>();
	private PApplet parent;
	private boolean moviendoEstado;
	private int estadoMoviendoID;
	private String[][] matrizDeCondiciones;
	
	public AutomataFinito(PApplet p) {
		parent = p;
		moviendoEstado = false;
		estadoMoviendoID = -1;
		matrizDeCondiciones = new String[50][50];
		inicializarMatriz(matrizDeCondiciones, 50, "-");
	}
	
	public void agregarEstado(Estado estado) {
		listaEstados.add(estado);
	}
	
	public void imprimirEstados() {
		for(int i = 0; i < listaEstados.size(); i++) {
			listaEstados.get(i).show();
		}
	}
	
	public void imprimirConexiones() {
		for(int i = 0; i < listaConexiones.size(); i++) {
			listaConexiones.get(i).show();
		}
	}
	
	public void setMoviendoEstado(boolean moviendoEstado) {
		this.moviendoEstado = moviendoEstado;
	}
	
	public boolean getMoviendoEstado() {
		return this.moviendoEstado;
	}
	
	public void setEstadoMoviendoID(int id) {
		this.estadoMoviendoID = id;
	}
	
	public int getEstadoMoviendoID() {
		return this.estadoMoviendoID;
	}
	
	public int getEstadoClickeado() {
		int estadoClickeado = -1;
		for(int i = 0 ; i < listaEstados.size(); i++) {
			if(distanciaEntrePuntos(listaEstados.get(i).getX(), listaEstados.get(i).getY(), 
					parent.mouseX, parent.mouseY) <= this.listaEstados.get(i).getRadio()) {
				estadoClickeado = i;
				break;
			}
		}
		return estadoClickeado;
	}
	
	public int distanciaEntrePuntos(int x1, int y1, int x2, int y2) {
		return (int)Math.sqrt(Math.pow(y2 - y1, 2) + Math.pow(x2 - x1, 2));
	}
	
	public void agregarConexion(int estado1, int estado2, String condicion) {
		// Si la conexion ya existe
		if(matrizDeCondiciones[estado1][estado2] != "-") {
			
		}
		else {
			matrizDeCondiciones[estado1][estado2] = condicion;
			listaConexiones.add(new ConexionCurva(parent, listaEstados.get(estado1), listaEstados.get(estado2), condicion));
		}
	}
	
	public void inicializarMatriz(String matriz[][], int tam, String x) {
		for(int fila = 0; fila < tam; fila++) {
			for(int columna = 0; columna < tam; columna++) {
				matriz[fila][columna] = x;
			}
		}
	}

}
