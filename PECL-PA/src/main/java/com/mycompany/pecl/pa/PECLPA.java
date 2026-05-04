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

        javax.swing.SwingUtilities.invokeLater(() -> {
        try {
            Interfaz interfaz = new Interfaz(mundo);
            interfaz.setVisible(true);

            // Creamos el hilo de actualización dentro, para asegurar que la interfaz ya existe
            Thread hiloRefresco = new Thread(() -> {
                while (true) {
                    try {
                        // Usamos invokeLater también para actualizar la GUI de forma segura
                        javax.swing.SwingUtilities.invokeLater(() -> {
                            interfaz.actualizar();
                        });
                        
                        Thread.sleep(250); // Pausa de 1/4 de segundo
                    } catch (Exception e) {
                        System.err.println("Error en el hilo de refresco: " + e.getMessage());
                    }
                }
            });
            
            hiloRefresco.setDaemon(true); // Para que se cierre al cerrar el programa
            hiloRefresco.start();
            System.out.println("Hilo de refresco de interfaz iniciado correctamente.");

        } catch (Exception e) {
            System.err.println("No se pudo iniciar la interfaz: " + e.getMessage());
            e.printStackTrace();
        }
    });
        
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
