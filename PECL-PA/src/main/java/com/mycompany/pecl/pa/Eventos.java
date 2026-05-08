/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pecl.pa;

/**
 *
 * @author julia_ntxs1ki
 */
public class Eventos extends Thread {

    private final Mundo mundo;

    private int num = 0;

    private boolean apagon = false;
    private boolean tormenta = false;
    private boolean eleven = false;
    private boolean redMental = false;

    private long finEventoMillis = 0;

    public Eventos(Mundo mundo) {
        this.mundo = mundo;
    }

    public synchronized boolean hayApagon() {
        return apagon;
    }

    public synchronized boolean hayTormenta() {
        return tormenta;
    }

    public synchronized boolean hayEleven() {
        return eleven;
    }

    public synchronized boolean hayRedMental() {
        return redMental;
    }

    @Override
    public void run() {
        while (true) {
            try {
                // Tiempo aleatorio entre eventos: 30-60 segundos.
                Thread.sleep((int) (Math.random() * 30000) + 30000);
                elegirEvento();

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Selecciona un evento aleatorio, lo mantiene activo durante 5-10 segundos
     * y después lo desactiva.
     */
    public void elegirEvento() {
        num = (int) (Math.random() * 4);

        int duracion = (int) (Math.random() * 5000) + 5000;

        synchronized (this) {
            finEventoMillis = System.currentTimeMillis() + duracion;
        }

        activarEvento(num);

        try {
            Thread.sleep(duracion);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return;
        }

        desactivarEvento(num);

        synchronized (this) {
            finEventoMillis = 0;
        }

        if (num == 2) {
            LogHawkins.escribir("Eleven comienza a liberar niños de la Colmena");
            mundo.revivirNiños();
        }
    }

    /**
     * Activa el evento global correspondiente.
     */
    public synchronized void activarEvento(int num) {
        switch (num) {
            case 0 -> {
                apagon = true;
                System.out.println("EVENTO: APAGÓN");
                LogHawkins.escribir("EVENTO GLOBAL: APAGÓN DEL LABORATORIO iniciado");
            }
            case 1 -> {
                tormenta = true;
                System.out.println("EVENTO: TORMENTA");
                LogHawkins.escribir("EVENTO GLOBAL: TORMENTA DEL UPSIDE DOWN iniciada");
            }
            case 2 -> {
                eleven = true;
                System.out.println("EVENTO: ELEVEN");
                LogHawkins.escribir("EVENTO GLOBAL: INTERVENCIÓN DE ELEVEN iniciada");
            }
            case 3 -> {
                redMental = true;
                System.out.println("EVENTO: RED MENTAL");
                LogHawkins.escribir("EVENTO GLOBAL: LA RED MENTAL iniciada");
            }
        }
    }

    /**
     * Desactiva el evento global activo.
     */
    public synchronized void desactivarEvento(int num) {
        switch (num) {
            case 0 -> {
                apagon = false;
                mundo.despertarPortales();
                LogHawkins.escribir("EVENTO GLOBAL: APAGÓN DEL LABORATORIO finalizado");
            }
            case 1 -> {
                tormenta = false;
                LogHawkins.escribir("EVENTO GLOBAL: TORMENTA DEL UPSIDE DOWN finalizada");
            }
            case 2 -> {
                eleven = false;
                LogHawkins.escribir("EVENTO GLOBAL: INTERVENCIÓN DE ELEVEN finalizada");
            }
            case 3 -> {
                redMental = false;
                LogHawkins.escribir("EVENTO GLOBAL: LA RED MENTAL finalizada");
            }
        }

        System.out.println("EVENTO FINALIZADO");
    }

    public synchronized String getEventoActual() {
        if (apagon) return "APAGÓN DEL LABORATORIO";
        if (tormenta) return "TORMENTA DEL UPSIDE DOWN";
        if (eleven) return "INTERVENCIÓN DE ELEVEN";
        if (redMental) return "LA RED MENTAL";

        return "SIN EVENTO";
    }

    /**
     * Devuelve el tiempo restante aproximado del evento activo en segundos.
     */
    public synchronized int getTiempoRestanteEvento() {
        if (finEventoMillis == 0) {
            return 0;
        }

        long restante = finEventoMillis - System.currentTimeMillis();

        if (restante <= 0) {
            return 0;
        }

        return (int) (restante / 1000);
    }
}
