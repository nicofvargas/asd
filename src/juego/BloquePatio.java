package juego;

import java.awt.Color;
import entorno.Entorno;

public class BloquePatio {
    private int fila, columna;
    private int x, y;
    private int ancho, alto;
    private Color color;
    private boolean estaOcupado;

    public BloquePatio(int fila, int columna, int x, int y, int ancho, int alto, Color color) {
        this.fila = fila;
        this.columna = columna;
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
        this.color = color;
        this.estaOcupado = false;
    }

    public void dibujar(Entorno entorno) {
        entorno.dibujarRectangulo(this.x, this.y, this.ancho, this.alto, 0, this.color);
    }

    public boolean estaEncima (int x, int y) {
        int bordeIzquierdo = this.x - (this.ancho/2);
        int bordeDerecho = this.x + (this.ancho/2);
        int bordeSuperior = this.y - (this.alto/2);
        int bordeInferior = this.y + (this.alto/2);

        if(x >= bordeIzquierdo && x <= bordeDerecho &&
                y >= bordeSuperior && y <= bordeInferior) {
            return true;
        }
        return false;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getAncho() {
        return ancho;
    }

    public int getAlto() {
        return alto;
    }

    public Color getColor() {
        return color;
    }

    public boolean estaOcupado() {
        return estaOcupado;
    }

    public void setOcupado(boolean estaOcupado) {
        this.estaOcupado = estaOcupado;
    }

    public void ocupar() {
        this.estaOcupado = true;
    }

    public void liberar() {
        this.estaOcupado = false;
    }
}
