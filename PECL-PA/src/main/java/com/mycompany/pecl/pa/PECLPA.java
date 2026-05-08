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

    private static final int NUM_NIÑOS = 1500;
    private static final int PUERTO_RMI = 1099;
    private static final String NOMBRE_SERVICIO = "MundoStranger";

    public static void main(String[] args) {
        try {
            Mundo mundo = new Mundo();
            Eventos eventos = new Eventos(mundo);
            mundo.setEventos(eventos);

            iniciarServidorRMI(mundo);
            iniciarInterfazLocal(mundo);

            eventos.start();

            Demogorgons demogorgonAlpha = new Demogorgons(mundo, eventos, 0);
            demogorgonAlpha.start();

            crearNiñosEscalonadamente(mundo, eventos);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Publica el objeto Mundo mediante RMI para que la interfaz remota pueda consultar
     * el estado del sistema y ejecutar operaciones como pausar/reanudar.
     */
    private static void iniciarServidorRMI(Mundo mundo) throws Exception {
        try {
            LocateRegistry.createRegistry(PUERTO_RMI);
            System.out.println("Registro RMI creado en el puerto " + PUERTO_RMI);
        } catch (Exception e) {
            System.out.println("El registro RMI ya estaba activo.");
        }

        Naming.rebind("rmi://localhost:" + PUERTO_RMI + "/" + NOMBRE_SERVICIO, mundo);
        System.out.println("Objeto Mundo registrado en RMI como " + NOMBRE_SERVICIO);
    }

    /**
     * Inicia la interfaz gráfica local y lanza un hilo de refresco periódico.
     * Las actualizaciones de Swing se hacen siempre sobre el EDT.
     */
    private static void iniciarInterfazLocal(Mundo mundo) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            try {
                Interfaz interfaz = new Interfaz(mundo);
                interfaz.setVisible(true);

                Thread hiloRefresco = new Thread(() -> {
                    while (true) {
                        try {
                            javax.swing.SwingUtilities.invokeLater(interfaz::actualizar);
                            Thread.sleep(250);
                        } catch (Exception e) {
                            System.err.println("Error en el hilo de refresco: " + e.getMessage());
                        }
                    }
                });

                hiloRefresco.setDaemon(true);
                hiloRefresco.start();

            } catch (Exception e) {
                System.err.println("No se pudo iniciar la interfaz: " + e.getMessage());
                e.printStackTrace();
            }
        });
    }

    /**
     * Genera los 1500 niños de forma escalonada.
     */
    private static void crearNiñosEscalonadamente(Mundo mundo, Eventos eventos) {
        for (int i = 0; i < NUM_NIÑOS; i++) {
            Niños niño = new Niños(mundo, eventos, i);
            niño.start();

            try {
                Thread.sleep((int) (Math.random() * 1500) + 500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }
}
