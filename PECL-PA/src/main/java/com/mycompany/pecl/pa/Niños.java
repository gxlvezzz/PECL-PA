    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pecl.pa;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Niños extends Thread {
    private Mundo mundo;
    private Eventos eventos;
    private String id;
    private boolean capturado = false; 
    private boolean siendoAtacado = false;
    private boolean liberadoPorEleven = false;

    public Niños(Mundo mundo, Eventos eventos, int numid) {
        this.mundo = mundo;
        this.eventos = eventos;
        this.id = String.format("N%04d", numid);
        LogHawkins.escribir("Niño " + id + " creado");
    }

    public synchronized void setCapturado(boolean estado) {
        this.capturado = estado;
    }

    public synchronized boolean esCapturado() {
    return capturado;
    }
    
    public String getIdNiño(){
        return id;
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
            LogHawkins.escribir("Niño " + id + " está siendo atacado");
            return false;
        }
        siendoAtacado = true;
        return true;
        
    }

    public synchronized void finalizarAtaque(boolean capturado) {
        this.capturado = capturado;
        this.siendoAtacado = false;
        if(capturado){
            LogHawkins.escribir("Niño " + id + " ha sido capturado");
        }else{
            LogHawkins.escribir("Niño " + id + " ha sobrevivido al ataque");
        }
    }
    
    public synchronized void liberarPorEleven() {
        capturado = false;
        siendoAtacado = false;
        liberadoPorEleven = true;
        LogHawkins.escribir("Niño " + id + " ha sido liberado por Eleven");
    }
    
    public synchronized boolean fueLiberadoPorEleven() {
        return liberadoPorEleven;
    }

    public synchronized void resetLiberadoPorEleven() {
        liberadoPorEleven = false;
    }
    
    private void esperar(int milisegundos) throws InterruptedException {
    int tiempoTranscurrido = 0;
    while (tiempoTranscurrido < milisegundos) {
        Thread.sleep(100); // Espera pequeña
        tiempoTranscurrido += 100;
        mundo.comprobarPausa(); // Si se pausa el juego, el hilo se queda bloqueado aquí
    }
}
    
    
    @Override
    
    public String toString() {
        return id; // Devuelve directamente "N0001", "N0002", etc.
    }
    
    public void run() {
    mundo.registrarHilo(this);
    LogHawkins.escribir("Niño " + id + " inicia ejecución");
    while (true) {
        mundo.comprobarPausa();
        try {
            mundo.eliminarNiñoDeTodasLasListas(this);
            mundo.entrarNiño(5, this);
            LogHawkins.escribir("Niño " + id + " entra en Calle Principal");
            
            esperar((int) (Math.random() * 2000) + 3000);

            mundo.comprobarPausa();
            mundo.salirNiño(5, this);
            mundo.entrarNiño(6, this);
            LogHawkins.escribir("Niño " + id + " entra en el Sótano de los Byers");
            
            esperar((int) (Math.random() * 1000) + 1000);
            
            mundo.comprobarPausa();
            
            int zonaElegida = (int) (Math.random() * 4) + 1;
            mundo.salirNiño(6, this);
            
            mundo.esperarEnPortal(zonaElegida, this);
            LogHawkins.escribir("Niño " + id + " cruza al Upside Down hacia " + zonaString(zonaElegida));
            
            mundo.comprobarPausa();
            
            while(esCapturado()){
                try { 
                    Thread.sleep(200); 
                    mundo.comprobarPausa();
                } catch(Exception e){
                System.err.println("Error: " + e.getMessage());}
            }

            if (fueLiberadoPorEleven()) {
                resetLiberadoPorEleven();
                continue;
            }

            mundo.entrarNiño(zonaElegida, this);

            
            int tiempoRecolectando = (int) (Math.random() * 2000) + 3000;
            if(mundo.hayTormenta()){
                esperar(tiempoRecolectando * 2); 
            } else {
                esperar(tiempoRecolectando);
            }

            while(esCapturado()){
                try { 
                    Thread.sleep(200); 
                    mundo.comprobarPausa();
                } catch(Exception e){
                System.err.println("Error: " + e.getMessage());}
            }

            if (fueLiberadoPorEleven()) {
                resetLiberadoPorEleven();
                continue;
            }
            
            mundo.comprobarPausa();
            mundo.salirNiño(zonaElegida, this);
            mundo.volverDePortal(zonaElegida, this);
            
            mundo.comprobarPausa();
            mundo.incrementarSangre();
            mundo.entrarNiño(7, this);
            LogHawkins.escribir("Niño " + id + " entra en Radio WSQK");
            
            esperar((int) (Math.random() * 2000) + 2000);
            
            while(esCapturado()){
                try { 
                    Thread.sleep(200); 
                    mundo.comprobarPausa();
                } catch(Exception e){
                System.err.println("Error: " + e.getMessage());}
            }

            if (fueLiberadoPorEleven()) {
                resetLiberadoPorEleven();
                continue;
            }

            mundo.salirNiño(7, this);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
  }
}