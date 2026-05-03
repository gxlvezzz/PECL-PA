/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.pecl.pa;

import static java.lang.Thread.sleep;

/**
 *
 * @author julia_ntxs1ki
 */
public class PECLPA {
    private static Mundo mundo = new Mundo();
    private static Eventos eventos = new Eventos(mundo);
    
    public static void main(String[] args){     
        mundo.setEventos(eventos);
        eventos.start();
        Demogorgons d = new Demogorgons(mundo,eventos,0);
        d.start();
        for (int i=0; i<1500; i++){
            Niños n = new Niños(mundo,eventos,i);
            n.start();
            try{
                Thread.sleep((int)(Math.random()*1500)+500);
            }catch(Exception e){
                
            }
            
        }                  
    }
}
