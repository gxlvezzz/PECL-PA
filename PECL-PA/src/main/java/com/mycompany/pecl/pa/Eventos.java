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
    private Mundo mundo;
    private int num = 0;
    private boolean apagon = false;
    private boolean tormenta = false;
    private boolean eleven = false;
    private boolean redMental = false;
    
    public Eventos(Mundo mundo){
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

    public synchronized void activarEvento(int num) {
        switch(num){
            case 0:
                apagon = true;
                System.out.println("EVENTO: APAGON");
                LogHawkins.escribir("EVENTO GLOBAL: APAGON DEL LABORATORIO iniciado");
                break;

            case 1:
                tormenta = true;
                System.out.println("EVENTO: TORMENTA");
                LogHawkins.escribir("EVENTO GLOBAL: TORMENTA DEL UPSIDE DOWN iniciada");
                break;

            case 2:
                eleven = true;
                System.out.println("EVENTO: ELEVEN");
                LogHawkins.escribir("EVENTO GLOBAL: INTERVENCION DE ELEVEN iniciada");
                break;

            case 3:
                redMental = true;
                System.out.println("EVENTO: RED MENTAL");
                LogHawkins.escribir("EVENTO GLOBAL: LA RED MENTAL iniciada");
                break;
        }
    }

    public synchronized void desactivarEvento(int num) {
        switch(num){
            case 0:
                apagon = false;
                mundo.despertarPortales();
                LogHawkins.escribir("EVENTO GLOBAL: APAGON DEL LABORATORIO finalizado");
                break;

            case 1:
                tormenta = false;
                LogHawkins.escribir("EVENTO GLOBAL: TORMENTA DEL UPSIDE DOWN finalizada");
                break;

            case 2:
                eleven = false;
                LogHawkins.escribir("EVENTO GLOBAL: INTERVENCION DE ELEVEN finalizada");
                break;

            case 3:
                redMental = false;
                LogHawkins.escribir("EVENTO GLOBAL: LA RED MENTAL finalizada");
                break;
        }

        System.out.println("EVENTO FINALIZADO");
    }

    public void elegirEvento() {
        num = (int)(Math.random()*4);

        activarEvento(num);

        try {
            Thread.sleep((int)(Math.random()*5000)+5000);
        } catch(Exception e) {
        }

        desactivarEvento(num);

        if(num==2){
            LogHawkins.escribir("Eleven comienza a liberar niños de la Colmena");
            mundo.revivirNiños();
        }
    }
    
    public synchronized String getEventoActual() {
        if (apagon) {
            return "APAGÓN DEL LABORATORIO";
        }
        if (tormenta) {
            return "TORMENTA DEL UPSIDE DOWN";
        }
        if (eleven) {
            return "INTERVENCIÓN DE ELEVEN";
        }
        if (redMental) {
            return "LA RED MENTAL";
        }
        return "SIN EVENTO";
    }

    public void run(){
        while(true){
            try{
                Thread.sleep((int)(Math.random()*30000)+30000);
            }catch(Exception e){
            }

            elegirEvento();
        }
    }
}
