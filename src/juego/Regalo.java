package juego;

import java.awt.Color;

import entorno.Entorno;

public class Regalo {
	private double x; 
	private double y;
	private double ancho; 
	private double alto;
	//private double angulo; 
	private Color color;
	
	Regalo(int x, int y, int ancho, int alto, Color c){
		this.x=x;
		this.y=y;
		this.ancho=ancho;
		this.alto=alto;
		//this.angulo=ang;
		this.color=c;
}
	public void dibujar(Entorno e) {
		e.dibujarRectangulo(x, y, ancho,alto,0,color);
	}
	
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	public double getAncho() {
		return ancho;
	}
	public void setAncho(double ancho) {
		this.ancho = ancho;
	}
	public double getAlto() {
		return alto;
	}
	public void setAlto(double alto) {
		this.alto = alto;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
}
