package juego;

import java.awt.Color;
import entorno.Entorno;

public class RoseBlade {
    private int x;
    private int y;
    private int ancho;
    private int alto;
    private Color color;
    private BolaDeFuego disparo;
    private int velocidad;
    private boolean estaSeleccionada;
    
    // (Más adelante añadiremos una variable para vida o un temporizador para disparar)

    RoseBlade(int x, int y, int ancho, int alto, Color c) {
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
        this.color = c;
        this.disparo = null;
        this.velocidad = 5;
        this.estaSeleccionada = false;
    }
    
    public void dibujar(Entorno e) {
        e.dibujarRectangulo(x, y, ancho, alto, 0, color);
    }

    public boolean mouseSobreLaPlanta(int mouseX, int mouseY) {
		int bordeIzquierdo = this.x - (this.ancho/2);
		int bordeDerecho = this.x + (this.ancho/2);
		int bordeSuperior = this.y - (this.alto/2);
		int bordeInferior = this.y + (this.alto/2);
		
		if(mouseX >= bordeIzquierdo && mouseX <= bordeDerecho &&
				mouseY >= bordeSuperior && mouseY <= bordeInferior) {
			return true;
		}
		return false;			
	}
    
    public void disparar() {
        if (this.disparo == null) {
            this.disparo = new BolaDeFuego(this.x, this.y, 20, Color.ORANGE);
        }
    }
    
    //Modifica y dibuja si el disparo existe
    public void actualizarDisparo(Entorno e) {
        if (this.disparo != null) {
            this.disparo.dibujar(e);
            this.disparo.moverDerecha();
            
            // Comprueba si el disparo se salió de la pantalla
            if (this.disparo.getX() > e.ancho()) {
                this.disparo = null; 
            }
        }
    }
    
    public BolaDeFuego getDisparo() {
        return this.disparo;
    }

    public void disparoColisionado() {
        this.disparo = null;
    }

    //metodos movimiento
    public void moverDerecha() {
        this.x+=velocidad;
    }

    public void moverIzquierda() {
        this.x-=velocidad;
    }

    public void moverArriba() {
        this.y-=velocidad;
    }

    public void moverAbajo() {
        this.y+=velocidad;
    }

    //esta seleeccionada
    public boolean getEstaSeleccionada() {
        return estaSeleccionada;
    }

    public void setEstaSeleccionada(boolean seleccion) {
        this.estaSeleccionada = seleccion;
    }
    
  }