    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pecl.pa;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Niños extends Thread {
    private Mundo mundo;
    private String id;
    private boolean capturado = false; 
    private boolean siendoAtacado = false;

    public Niños(Mundo mundo, int numid) {
        this.mundo = mundo;
        this.id = String.format("N%04d", numid);
    }

    public synchronized void setCapturado(boolean estado) {
        this.capturado = estado;
    }


    public synchronized boolean esCapturado() {
    return capturado;
    }
    
    private String zonaString(int zona){
        switch(zona){
            case 1:
                return " El Bosque";
            case 2: 
                return "El Laboratorio";
            case 3:
                return "El Centro Comercial";
            case 4:
                return "El Alcantarillado";
                
                default:
                return null;
        }
    }
    
    public synchronized boolean intentarSerAtacado() {
        if (capturado || siendoAtacado) {
            return false;
        }
        siendoAtacado = true;
        return true;
    }

    public synchronized void finalizarAtaque(boolean capturado) {
        this.capturado = capturado;
        this.siendoAtacado = false;
    }
    
    
    @Override
    public void run() {
        try {
            mundo.entrarNiño(5, this);
            System.out.println("Nino " + id + " en la Calle Principal.");
            Thread.sleep((long) (Math.random() * 2000) + 3000);

            while (!esCapturado()) {
                mundo.salirNiño(5, this);
                mundo.entrarNiño(6, this);
                System.out.println("Nino " + id + " entra al Sotano.");
                Thread.sleep((long) (Math.random() * 1000) + 1000);

                int zonaElegida = (int) (Math.random() * 4) + 1;
                mundo.salirNiño(6, this);
                mundo.esperarEnPortal(zonaElegida, this);

                if (esCapturado()) break;

                mundo.entrarNiño(zonaElegida, this);
                System.out.println("Nino " + id + " recolectando en " + zonaString(zonaElegida));
                
                Thread.sleep((long) (Math.random() * 2000) + 3000);

                if (esCapturado()) break;

                mundo.salirNiño(zonaElegida, this);
                // aqui el niño tiene que volver a pasar por el portal
                mundo.incrementarSangre();
                mundo.entrarNiño(7, this);
                System.out.println("Nino " + id + " en Radio WSQK.");
                Thread.sleep((long) (Math.random() * 2000) + 2000);

                if (esCapturado()) break;
                mundo.salirNiño(7, this);
                mundo.entrarNiño(5, this);
                System.out.println("Nino " + id + " vuelve a la Calle Principal.");
                Thread.sleep((long) (Math.random() * 2000) + 3000);
            }

            System.out.println(">>> El hilo de " + id + " ha TERMINADO (Capturado).");

        } catch (InterruptedException ex) {
          
        }
    }
}