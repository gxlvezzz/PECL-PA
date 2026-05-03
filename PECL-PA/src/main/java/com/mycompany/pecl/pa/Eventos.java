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
                break;
            case 1:
                tormenta = true;
                System.out.println("EVENTO: TORMENTA");
                break;
            case 2:
                eleven = true;
                System.out.println("EVENTO: ELEVEN");
                break;
            case 3:
                redMental = true;
                System.out.println("EVENTO: RED MENTAL");
                break;
        }
    }

    public synchronized void desactivarEvento(int num) {
        switch(num){
            case 0:
                apagon = false;
                break;
            case 1:
                tormenta = false;
                break;
            case 2:
                eleven = false;
                break;
            case 3:
                redMental = false;
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
            mundo.revivirNiños();
        }
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
