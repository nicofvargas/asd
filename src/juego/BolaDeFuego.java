package juego;

import java.awt.Color;
import entorno.Entorno;

public class BolaDeFuego {
    private double x;
    private double y;
    private double diametro;
    private Color color;
    private double velocidad;

    BolaDeFuego(double x, double y, double d, Color c) {
        this.x = x;
        this.y = y;
        this.diametro = d;
        this.color = c;
        this.velocidad = 5; // Puedes ajustar esto
    }
    
    public void dibujar(Entorno e) {
        e.dibujarCirculo(x, y, diametro, color);
    }
    
    // Método para que el disparo se mueva
    public void moverDerecha() {
        this.x = this.x + this.velocidad;
    }
    
    // (Necesitaremos getters para 'x' y 'diametro' para las colisiones)
    public double getX() {
        return x;
    }

    public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getDiametro() {
        return diametro;
    }
}