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
    private String id;
    private int zona=0;
    private int zona_anterior=0;
    private int capturas = 0;

    
    
    public Demogorgons(Mundo mundo, int numid){
        this.mundo = mundo;
        this.id = String.format("D%04d", numid);
    }
    
    private void moverse(){
        zona_anterior = zona;
        zona = (int)(Math.random()*4)+1;
        while(zona_anterior==zona){
            zona = (int)(Math.random()*4)+1;
        }
        mundo.entrarDemogorgon(zona, this);
        System.out.println("Demogorgon " + this.id + " ha entrado en " + zonaString(zona));
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
    }
    
    
    public void run(){
        System.out.println(this.id);
        while(true){
            if (zona != 0) {
                mundo.eliminarListaDemogorgon(zona, this);
            }
        moverse();        
        mundo.demogorgonAtacar(zona, this);        
        }
    }
}
