package juego;

import java.awt.Color;

import entorno.Entorno;

public class Planta {
	private int x;
	private int y;
	private int ancho;
	private int alto;
	private Color color;
	
	Planta (int x, int y, int ancho, int alto, Color c){
		this.x=x;
		this.y=y;
		this.ancho=ancho;
		this.alto=alto;
		this.color=c;
			
	}
	
	public void dibujar(Entorno e) {
		e.dibujarRectangulo(x, y, ancho,alto,0,color);
	}

	public boolean mouseSobreLaPlanta(int mouseX, int mouseY) {
		int bordeIzquierdo=this.x - (this.ancho/2);
		int bordeDerecho=this.x + (this.ancho/2);
		int bordeSuperior=this.y - (this.alto/2);
		int bordeInferior=this.y + (this.alto/2);
		
		if(mouseX >= bordeIzquierdo && mouseX<=bordeDerecho &&
				mouseY >= bordeSuperior && mouseY<= bordeInferior) {
			return true;
		}
		return false;			
		
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getAncho() {
		return ancho;
	}

	public void setAncho(int ancho) {
		this.ancho = ancho;
	}

	public int getAlto() {
		return alto;
	}

	public void setAlto(int alto) {
		this.alto = alto;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
}
