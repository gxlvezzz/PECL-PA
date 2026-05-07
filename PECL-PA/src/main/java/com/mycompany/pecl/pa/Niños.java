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
    
    
    @Override
    
    public String toString() {
        return id; // Devuelve directamente "N0001", "N0002", etc.
    }
    
    public void run() {
        LogHawkins.escribir("Niño " + id + " inicia ejecución");
            while (true) {
                mundo.comprobarPausa();
                try {
                    mundo.eliminarNiñoDeTodasLasListas(this);
                    mundo.entrarNiño(5, this);
                    System.out.println("Nino " + id + " en la Calle Principal.");
                    LogHawkins.escribir("Niño " + id + " entra en Calle Principal");
                    Thread.sleep((int) (Math.random() * 2000) + 3000);



                    mundo.salirNiño(5, this);
                    mundo.entrarNiño(6, this);
                    System.out.println("Nino " + id + " entra al Sotano.");
                    LogHawkins.escribir("Niño " + id + " entra en el Sótano de los Byers");
                    Thread.sleep((int) (Math.random() * 1000) + 1000);

                    int zonaElegida = (int) (Math.random() * 4) + 1;
                    mundo.salirNiño(6, this);
                    
                    LogHawkins.escribir("Niño " + id + " espera para cruzar al portal de " + zonaString(zonaElegida));
                    mundo.esperarEnPortal(zonaElegida, this);
                    System.out.println("Nino " + id + " ha cruzado al Upside Down.");
                    LogHawkins.escribir("Niño " + id + " cruza al Upside Down hacia " + zonaString(zonaElegida));
                    
                    while(esCapturado()){
                        try { sleep(200); } catch(Exception e){}
                    }

                    if (fueLiberadoPorEleven()) {
                        resetLiberadoPorEleven();
                        continue;
                    }

                    mundo.entrarNiño(zonaElegida, this);
                    System.out.println("Nino " + id + " recolectando en " + zonaString(zonaElegida));

                    if(mundo.hayTormenta()){
                        Thread.sleep(((int) (Math.random() * 2000) + 3000)*2); 
                    } else {
                        Thread.sleep((int) (Math.random() * 2000) + 3000);
                    }

                    while(esCapturado()){
                        try { sleep(200); } catch(Exception e){}
                    }

                    if (fueLiberadoPorEleven()) {
                        resetLiberadoPorEleven();
                        continue;
                    }
                    
                    LogHawkins.escribir("Niño " + id + " intenta regresar desde " + zonaString(zonaElegida));
                    mundo.salirNiño(zonaElegida, this);
                    mundo.volverDePortal(zonaElegida, this);

                    mundo.incrementarSangre();
                    LogHawkins.escribir("Niño " + id + " entrega sangre contaminada");
                    mundo.entrarNiño(7, this);
                    System.out.println("Nino " + id + " en Radio WSQK.");
                    LogHawkins.escribir("Niño " + id + " entra en Radio WSQK");
                    Thread.sleep((int) (Math.random() * 2000) + 2000);
                    
                    while(esCapturado()){
                        try { sleep(200); } catch(Exception e){}
                    }

                    if (fueLiberadoPorEleven()) {
                        resetLiberadoPorEleven();
                        continue;
                    }

                    mundo.salirNiño(7, this);
                }
             catch (Exception e) {
            }
        }
    }
}