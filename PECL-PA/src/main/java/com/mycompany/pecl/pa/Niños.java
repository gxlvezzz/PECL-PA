    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pecl.pa;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Niños extends Thread {
    private Mundo mundo;
    public String id;
    private boolean capturado = false; 

    public Niños(Mundo mundo, int numid) {
        this.mundo = mundo;
        this.id = String.format("N%04d", numid);
    }

    public void setCapturado(boolean estado) {
        this.capturado = estado;
    }

    @Override
    public void run() {
        try {
            System.out.println("Niño " + id + " en Calle Principal.");
            Thread.sleep((long) (Math.random() * 2000) + 3000);

            while (!capturado) {
                System.out.println("Niño " + id + " entra al Sótano.");
                Thread.sleep((long) (Math.random() * 1000) + 1000);

                int zonaElegida = (int) (Math.random() * 4) + 1;
                mundo.esperarEnPortal(zonaElegida, this);

                if (capturado) break;

                mundo.entrarNiño(zonaElegida, this);
                System.out.println("Niño " + id + " recolectando en zona " + zonaElegida);
                
                Thread.sleep((long) (Math.random() * 2000) + 3000);

                if (capturado) break;

                mundo.salirNiño(zonaElegida, this);
                System.out.println("Niño " + id + " en Radio WSQK.");
                Thread.sleep((long) (Math.random() * 2000) + 2000);

                if (capturado) break;

                System.out.println("Niño " + id + " vuelve a Calle Principal.");
                Thread.sleep((long) (Math.random() * 2000) + 3000);
            }

            System.out.println(">>> El hilo de " + id + " ha TERMINADO (Capturado).");

        } catch (InterruptedException ex) {
          
        }
    }
}