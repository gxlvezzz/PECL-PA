/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pecl.pa;

import java.util.*;

/**
 *
 * @author julia_ntxs1ki
 */
public class Demogorgons extends Thread {
    private Mundo mundo;
    private Eventos evento;
    private String id;
    private int zona=0;
    private int zona_anterior=0;
    private int capturas = 0;

    
    
    public Demogorgons(Mundo mundo, Eventos eventos, int numid){
        this.mundo = mundo;
        this.evento = eventos;
        this.id = String.format("D%04d", numid);
        LogHawkins.escribir("Demogorgon " + id + " creado");
    }
    
    private void moverse(){
        while(evento.hayEleven()){
            try {
                sleep(500);
            } catch (Exception e) {
            }
        }

        
        zona_anterior = zona;
        if(evento.hayRedMental()){
            zona = mundo.zonaConMasNiños();
            LogHawkins.escribir("Demogorgon " + id + " se dirige por RED MENTAL hacia " + zonaString(zona));
            
        } else {
            zona = (int)(Math.random()*4)+1;
            while(zona_anterior == zona){
                zona = (int)(Math.random()*4)+1;
            }
        }
        mundo.entrarDemogorgon(zona, this);
        System.out.println("Demogorgon " + this.id + " ha entrado en " + zonaString(zona));
        
        LogHawkins.escribir("Demogorgon " + id +" entra en " + zonaString(zona));
    }
    
    private String zonaString(int zona){
        switch(zona){
            case 0:
                return "Sin zona";
            case 1:
                return "El Bosque";
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

    public void incrementar_capturas(){
        capturas++;
         LogHawkins.escribir("Demogorgon " + id + " aumenta sus capturas a " + capturas);
    }
    
    public String toString() {
        return id;
    }
    
    public void run(){
        mundo.registrarHilo(this);
        System.out.println(this.id);
         LogHawkins.escribir("Demogorgon " + id + " inicia ejecución");

        while(true){
            mundo.comprobarPausa();
            while(evento.hayEleven()){
                try { Thread.sleep(500); } catch(Exception e) {}
            }

            if (!evento.hayApagon()) {
                if (zona != 0) {
                    mundo.eliminarListaDemogorgon(zona, this);
                    
                    LogHawkins.escribir("Demogorgon " + id +" abandona " + zonaString(zona));
                }

                moverse();
            }

            mundo.demogorgonAtacar(zona, this);        
        }
    }
}
