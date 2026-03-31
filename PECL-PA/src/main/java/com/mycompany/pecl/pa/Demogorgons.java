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
    private int zona;
    private int capturas = 0;

    
    
    public Demogorgons(Mundo mundo, int numid){
        this.mundo = mundo;
        this.id = String.format("D%04d", numid);
    }
    
    private void moverse(){
        zona = (int)(Math.random()*4)+1;
        mundo.entrarDemogorgon(zona, this);
    }
    

    public void incrementar_capturas(){
        capturas++;
    }
    
    
    public void run(){
        System.out.println(this.id);
        while(true){
        mundo.eliminarListaDemogorgon(this);
        moverse();        
        mundo.demogorgonAtacar(zona, this);        
        }
    }
}
