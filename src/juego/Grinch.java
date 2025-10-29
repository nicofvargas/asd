package juego;

import java.awt.Color;

import entorno.Entorno;

public class Grinch {
	private int x;
	private int y;
	private int diametro;
	private int ancho;
	private int alto;
	private Color color;
	private boolean porFuera;
	private int vida;
	private boolean vivo;
	
	
	
	Grinch(int x, int y, int d, Color c){
		this.x=x;
		this.y=y;
		this.diametro=d;
		this.ancho = diametro; // círculo, ancho = diametro
		this.alto = diametro; 
		this.color=c;
		this.porFuera = false;
		this.vida= 2;
		this.vivo= true;
	}
	
	public void dibujar(Entorno e) {
		e.dibujarCirculo(x, y, diametro, color);
	}

	public void moverIzq() {
		this.x=this.x-1;
	}
	public boolean colisionaConEntornoPorizq(Entorno e){
		return this.x - this.diametro/2 <=0;
	}
	
	public boolean colisionaConRegalo(Regalo r) {
		return this.x < r.getX() + r.getAncho() &&
		       this.x + this.ancho > r.getX() &&
		       this.y < r.getY() + r.getAlto() &&
		       this.y + this.alto > r.getY();
	}
	public boolean colisionConDisparo(BolaDeFuego bf) {
		if(bf==null) {
			return false;
		}
		return this.x < bf.getX()  + bf.getDiametro() &&
			   this.x + this.ancho > bf.getX() &&
		       this.y < bf.getY() + bf.getDiametro() &&
		       this.y + this.alto > bf.getY();
		
	}
	
	public void recibirDisparo() {
		this.vida = this.vida - 1;
		this.color= Color.red;
		if (this.vida <= 0) {
			this.vivo = false;
		}
		
	}
	
	public boolean estaVivo() {
		return this.vivo;
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

	public int getDiametro() {
		return diametro;
	}
	public void setDiametro(int diametro) {
		this.diametro = diametro;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	public boolean isPorFuera() {
		return porFuera;
	}
	public void setPorFuera(boolean porFuera) {
		this.porFuera = porFuera;
	}
}
