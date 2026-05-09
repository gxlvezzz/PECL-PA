/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.pecl.pa;

import static java.lang.Thread.sleep;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

/**
 *
 * @author julia_ntxs1ki
 */
public class PECLPA {

    public static void main(String[] args) {
        try {
            Mundo mundo = new Mundo();
            Eventos eventos = new Eventos(mundo);
            mundo.setEventos(eventos);

            LocateRegistry.createRegistry(1099);
            Naming.rebind("//localhost/MundoStranger", mundo);

            javax.swing.SwingUtilities.invokeLater(() -> {
                try {
                    
                    
                    InterfazRemota interfazRemota = new InterfazRemota(mundo);
                    interfazRemota.setVisible(true);
                    Interfaz interfaz = new Interfaz(mundo);
                    interfaz.setVisible(true);

                    Thread hiloRefresco = new Thread(() -> {
                        while (true) {
                            try {
                                
                                javax.swing.SwingUtilities.invokeLater(() -> {
                                    interfaz.actualizar(); 
                                    interfazRemota.actualizarRemoto();
                                });

                                Thread.sleep(250); // 4 veces por segundo es suficiente para que se vea fluido
                            } catch (Exception e) {
                                System.err.println("Error en el hilo de refresco: " + e.getMessage());
                            }
                        }
                    });

                    hiloRefresco.setDaemon(true);
                    hiloRefresco.start();

                } catch (Exception e) {
                    System.err.println("No se pudo iniciar la interfaz: " + e.getMessage());
                }
            });

            eventos.start();

            Demogorgons d = new Demogorgons(mundo, eventos, 0);
            d.start();

            for (int i = 0; i < 1500; i++) {
                Niños n = new Niños(mundo, eventos, i);
                n.start();

                try {
                    Thread.sleep((int) (Math.random() * 1500) + 500);
                } catch (Exception e) {
                    System.err.println("Error: " + e.getMessage());
                }
            }

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
