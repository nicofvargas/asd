package juego;

import java.awt.Color;
import entorno.Entorno;

public class Patio {
    private final int filas = 5;
    private final int columnas = 11;
    private final int anchoCelda;
    private final int altoCelda;
    private final int xInicio;
    private final int yInicio;
    private final Color colorClaro;
    private final Color colorOscuro;
    private final BloquePatio[][] bloques;

    // el bloque plantilla va a definir la posicion, tamaño y color, las posiciones de los bloques siguientes
    //es en base a este que se construyen los que le siguen
    public Patio(BloquePatio plantilla) {
        this.anchoCelda = plantilla.getAncho();
        this.altoCelda = plantilla.getAlto();
        this.xInicio = plantilla.getX();
        this.yInicio = plantilla.getY();
        this.colorClaro = plantilla.getColor();
        this.colorOscuro = plantilla.getColor().darker();
        this.bloques = new BloquePatio[filas][columnas];
        iniciarBloques(plantilla);
    }

    private void iniciarBloques(BloquePatio plantilla) {
        for (int f = 0; f < filas; f++) {
            for (int c = 0; c < columnas; c++) {
                int x, y;
                // primera celda usa exactamente la plantilla osea posicion y tamaño
                if (f == 0 && c == 0) {
                    x = plantilla.getX();
                    y = plantilla.getY();
                } else if (c > 0) { //comprueba si esta en una columna mayor a 0
                    // toma X del bloque anterior en la misma fila y suma ancho a x para el nuevo bloque
                    BloquePatio anterior = bloques[f][c - 1];
                    x = anterior.getX() + anchoCelda;
                    y = anterior.getY();
                } else {
                    // misma logica anterior pero para las filas
                    BloquePatio primeraFilaAnterior = bloques[f - 1][0];
                    x = primeraFilaAnterior.getX();
                    y = primeraFilaAnterior.getY() + altoCelda;
                }
                Color color = ((f + c) % 2 == 0) ? colorClaro : colorOscuro;
                bloques[f][c] = new BloquePatio(f, c, x, y, anchoCelda, altoCelda, color);
            }
        }
    }

    public void dibujar(Entorno entorno) {
        for (int f = 0; f < filas; f++) {
            for (int c = 0; c < columnas; c++) {
                bloques[f][c].dibujar(entorno);
            }
        }
    }

    // devuelve un bloque si las coordenadas X e Y estan dentro de alguno quizas les sirve sino borrenlo
    // mas que nada es otra forma mas de saber si un bloque esta ocupado, con el retorno de este metodo
    // pueden acceder al booleano estaOcupado de la clase BloquePatio
    public BloquePatio getBloqueEnPos(int x, int y) {
        for (int f = 0; f < filas; f++) {
            for (int c = 0; c < columnas; c++) {
                if (bloques[f][c].estaEncima(x, y)) {
                    return bloques[f][c];
                }
            }
        }
        return null;
    }

    // Getters
    public int getFilas() { return filas; }
    public int getColumnas() { return columnas; }
    public BloquePatio[][] getBloques() { return bloques; }
}
