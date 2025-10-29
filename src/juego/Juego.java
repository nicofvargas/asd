package juego;


import java.awt.Color;
import java.util.Random;

import entorno.Entorno;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego
{

	private Entorno entorno;
    private Patio patio;


    //semillas
    private Planta semillaRoja;
    private Planta semillaMarron;
    private Planta plantaSiendoArrastrada;

    //posiciones iniciales de semillas
    private final int inicioRojaX = 112;
    private final int inicioRojaY = 40;
    private final int inicioMarronX = 211;
    private final int inicioMarronY = 40;

    //////////////////////////////////////////////////////////////////
	private Grinch [] grinchs;
	private Regalo [] regalos;
	private RoseBlade[] plantasEnPatio;
	private int puntaje;
	private int tiempo;
	private int restantes =50;


	private BotonCarga1 botonCarga1;
	private BotonCarga2 botonCarga2;
	private BarraCarga1 barraCarga1;
	private BarraCarga2 barraCarga2;
	private int indiceGrinchActual;
	private int grinchVisible;
	private int contAparicion;
	private boolean terminoJuego=false;
	


	// ...
	
	Juego()
	{
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "Proyecto para TP", 1000, 600);
		
		this.grinchs = new Grinch[100];
		this.iniciarGrinchs();
		this.indiceGrinchActual=0;
		this.grinchVisible=0;
		this.contAparicion=0;
	
		
		
		this.regalos = new Regalo[5];
		this.iniciarRegalos();

        // plantilla para (0,0) del patio
        BloquePatio plantilla = new BloquePatio(0, 0, 115, 180, 80,80, Color.LIGHT_GRAY);
        this.patio = new Patio(plantilla);

        // crear semillas (ajusta constructor de Planta a tu implementación)
        this.semillaRoja = new Planta(inicioRojaX, inicioRojaY, 50, 50, Color.RED);
        this.semillaMarron = new Planta(inicioMarronX, inicioMarronY, 50, 50, new Color(139,69,19));
        this.plantaSiendoArrastrada = null;
		
		this.iniciarPlanta ();

		
		//this.iniciarGrinchs();
		//this.iniciarRegalos();
		
		this.plantasEnPatio = new RoseBlade[10];
        
        botonCarga1 = new BotonCarga1 (112,40,90,90, Color.blue);
        botonCarga2 = new BotonCarga2 (210,40,90,90, Color.blue);
   
        barraCarga1 = new BarraCarga1 (112,90,90,10, Color.YELLOW);
        barraCarga2 = new BarraCarga2 (210,90,90,10, Color.YELLOW);
        
		// Inicia el juego!
		this.entorno.iniciar();
	}

	/**
	 * Durante el juego, el método tick() será ejecutado en cada instante y 
	 * por lo tanto es el método más importante de esta clase. Aquí se debe 
	 * actualizar el estado interno del juego para simular el paso del tiempo 
	 * (ver el enunciado del TP para mayor detalle).
	 */
	public void tick()
	{
		
		if (terminoJuego) {
	        entorno.cambiarFont("Arial", 50, Color.RED, entorno.NEGRITA);
	        entorno.escribirTexto("PERDISTE :(", entorno.ancho() / 2 - 100, entorno.alto() / 2);
	        return;
	    }
		
		if(puntaje==50){
			entorno.cambiarFont("Arial", 50, Color.green, entorno.NEGRITA);
	        entorno.escribirTexto("GANASTEEE", entorno.ancho() / 2 - 100, entorno.alto() / 2);
			return;
		}
		
		///Dibuja el patio
		patio.dibujar(entorno);
        semillaRoja.dibujar(entorno);
        semillaMarron.dibujar(entorno);
		
		iniciarGrinchs();
		///Dibuja los grinchs
		
		for(int i =0; i< grinchs.length;i++) {
			if(this.grinchs[i]!=null) {
				this.grinchs[i].dibujar(this.entorno);
				this.grinchs[i].moverIzq();	}
		}
		
		///Dibuja los regalos
		for(int i=0; i<regalos.length;i++) {
			if(this.regalos[i]!=null) {
				this.regalos[i].dibujar(this.entorno);
			}
		}
		///  ///////////////////// for tatata bloque[] patio


		///
		for (int i = 0; i < grinchs.length; i++) {
			if(this.grinchs[i] != null && this.grinchs[i].colisionaConEntornoPorizq(entorno)){
				this.grinchs[i] = null;
			}
		}
		
		///Colision de los grinchs con los regalos
		for (int i = 0; i < grinchs.length; i++) {
			if (this.grinchs[i]!=null) {
				for(int j=0;j<regalos.length;j++) {
					if (this.regalos[j] !=null && this.grinchs[i].colisionaConRegalo(regalos[j])) {
						grinchs[i] =null;
						regalos[j] = null;
						this.terminoJuego=true;
						entorno.cambiarFont("Arial", 40, Color.RED, entorno.NEGRITA);
					    entorno.escribirTexto("GAME OVER", entorno.ancho() / 2 - 100, entorno.alto() / 2);
					    return;
						}
					}

				}
				
			}
	
		
	////Colision de grinch con disparos
    for(int i=0; i < grinchs.length;i++) {    
		if(this.grinchs[i] != null) {
           int j = 0; 
              boolean grinchSigueVivo = true;
              
                while (j < this.plantasEnPatio.length && grinchSigueVivo) { 
                    if (this.plantasEnPatio[j] != null) {                       
                        BolaDeFuego proyectilActual = this.plantasEnPatio[j].getDisparo();
                        if (proyectilActual != null && this.grinchs[i].colisionConDisparo(proyectilActual)) {                           
                            this.grinchs[i].recibirDisparo();
                            this.plantasEnPatio[j].disparoColisionado();
                            
                            
                            if (!this.grinchs[i].estaVivo()) {
                                this.grinchs[i] = null; 
                                this.puntaje++;
                                this.restantes--;
                                grinchSigueVivo = false; 
                            }
                        }
                    }
                    j++;
                }
		    }
		}
		
		/// DIbuja el BotonCarga
  		this.botonCarga1.dibujar(entorno);
  		this.botonCarga2.dibujar(entorno);
  		
  		///
  		this.barraCarga1.dibujar(entorno);
  		this.barraCarga2.dibujar(entorno);
  		
		// Dibuja las plantas rojas y marrones
	    for (int i = 0; i < this.plantasEnPatio.length; i++) {
	        if (this.plantasEnPatio[i] != null) {
	            this.plantasEnPatio[i].dibujar(this.entorno);
	        this.plantasEnPatio[i].actualizarDisparo(this.entorno);
	        }
	    }

        //movimiento rose blade
        if(entorno.estaPresionada(entorno.TECLA_ARRIBA)) {
            	for (int i = 0; i < this.plantasEnPatio.length; i++) {
    		        if (this.plantasEnPatio[i] != null && this.plantasEnPatio[i].getEstaSeleccionada()==true) {
    		        	this.plantasEnPatio[i].moverArriba();
    		        }
            	}
        }

        if(entorno.estaPresionada(entorno.TECLA_ABAJO)) {
            for (int i = 0; i < this.plantasEnPatio.length; i++) {
                if (this.plantasEnPatio[i] != null && this.plantasEnPatio[i].getEstaSeleccionada() == true) {
                    this.plantasEnPatio[i].moverAbajo();
                }
            }
        }

        if(entorno.estaPresionada(entorno.TECLA_DERECHA)) {
            for (int i = 0; i < this.plantasEnPatio.length; i++) {
                if (this.plantasEnPatio[i] != null && this.plantasEnPatio[i].getEstaSeleccionada() == true) {
                    this.plantasEnPatio[i].moverDerecha();
                }
            }
        }

        if (entorno.estaPresionada(entorno.TECLA_IZQUIERDA)) {
            for (int i = 0; i < this.plantasEnPatio.length; i++) {
                if (this.plantasEnPatio[i] != null && this.plantasEnPatio[i].getEstaSeleccionada() == true) {
                    this.plantasEnPatio[i].moverIzquierda();
                }
            }
        }

        //logica de click sobre plantas (RoseBlade)
        if (entorno.sePresionoBoton(entorno.BOTON_IZQUIERDO)) {
            int mouseX = entorno.mouseX();
            int mouseY = entorno.mouseY();

            for (int i = 0; i < this.plantasEnPatio.length; i++) {
                if (this.plantasEnPatio[i] != null) {
                    if (this.plantasEnPatio[i].mouseSobreLaPlanta(mouseX, mouseY)) {
                        this.plantasEnPatio[i].setEstaSeleccionada(!this.plantasEnPatio[i].getEstaSeleccionada());
                    } else {
                        this.plantasEnPatio[i].setEstaSeleccionada(false);
                    }
                }
            }
        }


		// 1. Detecta si EMPIEZA el arrastre


        /// Disparos
	    if (entorno.sePresiono(entorno.TECLA_ESPACIO)) {
	        for (int i = 0; i < this.plantasEnPatio.length; i++) {
	            if (this.plantasEnPatio[i] != null) {
                    this.plantasEnPatio[i].disparar();
                }
	        }
	    }
	    
		entorno.cambiarFont("Arial", 18, Color.CYAN);
		entorno.escribirTexto("Eliminado: " + this.puntaje, entorno.ancho()/2,15);
		entorno.escribirTexto("Restante: " + this.restantes, entorno.ancho()/2,30);
		entorno.escribirTexto("Tiempo: " + this.tiempo, entorno.ancho()/2,45);
		
		}
	
	
	//Grinchs
	public void iniciarGrinchs() {
		int diametro = 50;
	    int xI = 1000; 
	    int yI = 180;   
	    int separacion = 95;
	    Random r = new Random();
	    
	    contAparicion++;
	    
	    int grinchActivo=0;
	    for(int i = 0; i < grinchs.length; i++) {
			if (grinchs[i] != null && !grinchs[i].isPorFuera()) {
				grinchActivo++;
			}
		}
	    
	    if(grinchActivo <5 && contAparicion >100) {
	    	contAparicion=0;
		
			if (indiceGrinchActual < grinchs.length) {
				int fila = r.nextInt(5);
				int y=yI + fila * separacion;
				grinchs[indiceGrinchActual]= new Grinch(xI,y,diametro,Color.green);
				grinchs[indiceGrinchActual].setPorFuera(false);
				
				indiceGrinchActual++;
		        grinchVisible++;
	        
			}
		}
   }
	
	//Regalos
	public void iniciarRegalos() {
		int ancho = 50;
		int alto = 50;
		int x = 115; // coordenada X fija del lado izquierdo
		int separacion = 93; // distancia entre regalos (filas)
		int inicio = 180; // posición inicial de la primera fila

		for (int i = 0; i < regalos.length; i++) {
			int y = inicio + i * separacion;
			this.regalos[i] = new Regalo(x, y, ancho, alto, Color.orange);
		}
		for (int i = 0; i < regalos.length; i++) {
			if (regalos[i] != null) {
				regalos[i].dibujar(entorno);
			}
		}
	}

	
	//Planta
		public void iniciarPlanta () {
			int ancho = 50;
			int alto = 50;
			int y = 40; // coordenada y fija del lado izquierdo

			// Posición de la primera semilla (verde)
			int xRoja = 112; 
			this.semillaRoja = new Planta(xRoja, y, ancho, alto, Color.RED);
			
			// Posición de la segunda semilla (roja)
			int xMarron = 211; // La ponemos un poco a la derecha de la verde
			this.semillaMarron = new Planta(xMarron, y, ancho, alto, Color.orange);
	
		}
		

			
		

	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		Juego juego = new Juego();
	}
}
