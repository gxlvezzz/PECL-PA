    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pecl.pa;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Niños extends Thread {

    private final Mundo mundo;
    private final Eventos eventos;
    private final String id;

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

    public String getIdNiño() {
        return id;
    }

    /**
     * Intenta reservar al niño como objetivo de un ataque.
     * Devuelve false si el niño ya está capturado o si otro demogorgon lo está atacando.
     */
    public synchronized boolean intentarSerAtacado() {
        if (capturado || siendoAtacado) {
            LogHawkins.escribir("Niño " + id + " no puede ser atacado porque ya está capturado o en ataque");
            return false;
        }

        siendoAtacado = true;
        return true;
    }

    /**
     * Finaliza el ataque. Si capturado es true, el niño queda en estado de captura.
     * Si es false, vuelve a poder continuar su ciclo normal.
     */
    public synchronized void finalizarAtaque(boolean capturado) {
        this.capturado = capturado;
        this.siendoAtacado = false;

        if (capturado) {
            LogHawkins.escribir("Niño " + id + " ha sido capturado");
        } else {
            LogHawkins.escribir("Niño " + id + " ha sobrevivido al ataque");
        }
    }

    /**
     * Eleven libera al niño de la Colmena.
     * Se activa una bandera para que el hilo del niño reinicie su ciclo desde Hawkins.
     */
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
        return id;
    }

    @Override
    public void run() {
        mundo.registrarHilo(this);
        LogHawkins.escribir("Niño " + id + " inicia ejecución");

        while (true) {
            mundo.comprobarPausa();

            try {
                cicloCompleto();

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Ciclo completo de vida del niño:
     * Calle Principal -> Sótano -> Portal -> Upside Down -> vuelta -> Radio WSQK.
     */
    private void cicloCompleto() throws InterruptedException {
        entrarEnCallePrincipal();
        prepararseEnSotano();

        int zonaElegida = (int) (Math.random() * 4) + 1;

        cruzarAlUpsideDown(zonaElegida);

        if (esperarSiEstaCapturado()) return;

        recolectarSangre(zonaElegida);

        if (esperarSiEstaCapturado()) return;

        regresarAHawkins(zonaElegida);

        descansarEnRadio();

        esperarSiEstaCapturado();
    }

    private void entrarEnCallePrincipal() throws InterruptedException {
        mundo.eliminarNiñoDeTodasLasListas(this);
        mundo.entrarNiño(5, this);

        System.out.println("Niño " + id + " en la Calle Principal.");
        LogHawkins.escribir("Niño " + id + " entra en Calle Principal");

        Thread.sleep((int) (Math.random() * 2000) + 3000);
        mundo.comprobarPausa();
    }

    private void prepararseEnSotano() throws InterruptedException {
        mundo.salirNiño(5, this);
        mundo.entrarNiño(6, this);

        System.out.println("Niño " + id + " entra al Sótano.");
        LogHawkins.escribir("Niño " + id + " entra en el Sótano de los Byers");

        Thread.sleep((int) (Math.random() * 1000) + 1000);
        mundo.comprobarPausa();
    }

    private void cruzarAlUpsideDown(int zonaElegida) {
        mundo.salirNiño(6, this);

        LogHawkins.escribir("Niño " + id + " espera para cruzar al portal de " + zonaString(zonaElegida));

        mundo.esperarEnPortal(zonaElegida, this);

        System.out.println("Niño " + id + " ha cruzado al Upside Down.");
        LogHawkins.escribir("Niño " + id + " cruza al Upside Down hacia " + zonaString(zonaElegida));

        mundo.comprobarPausa();
    }

    private void recolectarSangre(int zonaElegida) throws InterruptedException {
        mundo.entrarNiño(zonaElegida, this);

        System.out.println("Niño " + id + " recolectando en " + zonaString(zonaElegida));

        int tiempoRecoleccion = (int) (Math.random() * 2000) + 3000;

        if (mundo.hayTormenta()) {
            tiempoRecoleccion *= 2;
        }

        Thread.sleep(tiempoRecoleccion);
    }

    private void regresarAHawkins(int zonaElegida) {
        mundo.comprobarPausa();

        LogHawkins.escribir("Niño " + id + " intenta regresar desde " + zonaString(zonaElegida));

        mundo.salirNiño(zonaElegida, this);
        mundo.volverDePortal(zonaElegida, this);

        mundo.incrementarSangre();
        LogHawkins.escribir("Niño " + id + " entrega sangre contaminada");
    }

    private void descansarEnRadio() throws InterruptedException {
        mundo.comprobarPausa();

        mundo.entrarNiño(7, this);

        System.out.println("Niño " + id + " en Radio WSQK.");
        LogHawkins.escribir("Niño " + id + " entra en Radio WSQK");

        Thread.sleep((int) (Math.random() * 2000) + 2000);

        if (!fueLiberadoPorEleven()) {
            mundo.salirNiño(7, this);
        }
    }

    /**
     * Si el niño ha sido capturado, queda esperando hasta que Eleven lo libere.
     * Si fue liberado, se reinicia el ciclo desde Calle Principal.
     */
    private boolean esperarSiEstaCapturado() {
        while (esCapturado()) {
            try {
                Thread.sleep(200);
                mundo.comprobarPausa();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (fueLiberadoPorEleven()) {
            resetLiberadoPorEleven();
            return true;
        }

        return false;
    }

    private String zonaString(int zona) {
        return switch (zona) {
            case 1 -> "El Bosque";
            case 2 -> "El Laboratorio";
            case 3 -> "El Centro Comercial";
            case 4 -> "El Alcantarillado";
            default -> "Zona desconocida";
        };
    }
}