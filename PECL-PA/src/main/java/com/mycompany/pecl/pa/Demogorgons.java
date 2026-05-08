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

    private final Mundo mundo;
    private final Eventos evento;
    private final String id;

    private int zona = 0;
    private int zonaAnterior = 0;
    private int capturas = 0;

    public Demogorgons(Mundo mundo, Eventos eventos, int numid) {
        this.mundo = mundo;
        this.evento = eventos;
        this.id = String.format("D%04d", numid);

        LogHawkins.escribir("Demogorgon " + id + " creado");
    }

    @Override
    public void run() {
        mundo.registrarHilo(this);
        mundo.registrarDemogorgon(this);

        System.out.println(id);
        LogHawkins.escribir("Demogorgon " + id + " inicia ejecución");

        while (true) {
            mundo.comprobarPausa();

            esperarSiIntervieneEleven();

            if (!evento.hayApagon()) {
                cambiarDeZona();
            }

            mundo.demogorgonAtacar(zona, this);
        }
    }

    /**
     * Durante la intervención de Eleven, los demogorgons no se mueven ni atacan.
     */
    private void esperarSiIntervieneEleven() {
        while (evento.hayEleven()) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }

    /**
     * Mueve al demogorgon a una nueva zona.
     * Si está activa la Red Mental, se dirige a la zona con más niños.
     * En caso contrario, elige una zona aleatoria distinta de la anterior.
     */
    private void cambiarDeZona() {
        if (zona != 0) {
            mundo.eliminarListaDemogorgon(zona, this);
            LogHawkins.escribir("Demogorgon " + id + " abandona " + zonaString(zona));
        }

        zonaAnterior = zona;

        if (evento.hayRedMental()) {
            zona = mundo.zonaConMasNiños();
            LogHawkins.escribir("Demogorgon " + id + " se dirige por RED MENTAL hacia " + zonaString(zona));
        } else {
            zona = generarZonaAleatoriaDistinta();
        }

        mundo.entrarDemogorgon(zona, this);

        System.out.println("Demogorgon " + id + " ha entrado en " + zonaString(zona));
        LogHawkins.escribir("Demogorgon " + id + " entra en " + zonaString(zona));
    }

    private int generarZonaAleatoriaDistinta() {
        int nuevaZona = (int) (Math.random() * 4) + 1;

        while (nuevaZona == zonaAnterior) {
            nuevaZona = (int) (Math.random() * 4) + 1;
        }

        return nuevaZona;
    }

    public synchronized void incrementar_capturas() {
        capturas++;
        LogHawkins.escribir("Demogorgon " + id + " aumenta sus capturas a " + capturas);
    }

    public synchronized int getCapturas() {
        return capturas;
    }

    @Override
    public String toString() {
        return id;
    }

    private String zonaString(int zona) {
        return switch (zona) {
            case 0 -> "Sin zona";
            case 1 -> "El Bosque";
            case 2 -> "El Laboratorio";
            case 3 -> "El Centro Comercial";
            case 4 -> "El Alcantarillado";
            default -> "Zona desconocida";
        };
    }
}
