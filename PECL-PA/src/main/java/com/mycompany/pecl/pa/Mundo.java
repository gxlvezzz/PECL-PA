/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pecl.pa;

import static java.lang.Thread.sleep;
import java.util.*;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 *
 * @author julia_ntxs1ki
 */
public class Mundo {
    private List <Niños> niñosBosque = Collections.synchronizedList(new ArrayList<>());
    private List <Niños> niñosLaboratorio = Collections.synchronizedList(new ArrayList<>());
    private List <Niños> niñosCentroComercial = Collections.synchronizedList(new ArrayList<>());
    private List <Niños> niñosAlcantarillado = Collections.synchronizedList(new ArrayList<>());
    private List <Demogorgons> demogorgonsBosque = Collections.synchronizedList(new ArrayList<>());
    private List <Demogorgons> demogorgonsLaboratorio = Collections.synchronizedList(new ArrayList<>());
    private List <Demogorgons> demogorgonsCentroComercial = Collections.synchronizedList(new ArrayList<>());
    private List <Demogorgons> demogorgonsAlcantarillado = Collections.synchronizedList(new ArrayList<>());
    private List <Niños> niñosCallePrincipal = Collections.synchronizedList(new ArrayList<>());
    private List <Niños> niñosSotanoByers = Collections.synchronizedList(new ArrayList<>());
    private List <Niños> niñosRadioWSQK = Collections.synchronizedList(new ArrayList<>());
    private List <Niños> niñosColmena = Collections.synchronizedList(new ArrayList<>());

    //Para los portales
    private CyclicBarrier portalBosque = new CyclicBarrier(2);
    private CyclicBarrier portalLaboratorio = new CyclicBarrier(3);
    private CyclicBarrier portalCentro = new CyclicBarrier(4);
    private CyclicBarrier portalAlcantarillado = new CyclicBarrier(2);
    private Lock lockBosque = new ReentrantLock();
    private Lock lockLaboratorio = new ReentrantLock();
    private Lock lockCentro = new ReentrantLock();
    private Lock lockAlcantarillado = new ReentrantLock();


    private int niñosEnColmena=0;
    private int contadorDemogorgons=1;
    private int contadorSangre=0;
    private int contadorSangreDuranteEleven = 0;
    
    private Lock atacar = new ReentrantLock();
    private Eventos eventos;
   
    

    public synchronized void entrarNiño(int zona, Niños n){
        switch(zona){
            case 1:
                niñosBosque.add(n);
                break;
            case 2:
                niñosLaboratorio.add(n);
                break;
            case 3:
                niñosCentroComercial.add(n);
                break;
            case 4:
                niñosAlcantarillado.add(n);
                break;
            case 5:
                niñosCallePrincipal.add(n);
                break;
            case 6:
                niñosSotanoByers.add(n);
                break;
            case 7:
                niñosRadioWSQK.add(n);
                break;
            case 8:
                niñosColmena.add(n);
                break;
            default:
                break;
        }
}
    
    public synchronized void salirNiño(int zona, Niños n) {
        switch (zona) {
            case 1: niñosBosque.remove(n); break;
            case 2: niñosLaboratorio.remove(n); break;
            case 3: niñosCentroComercial.remove(n); break;
            case 4: niñosAlcantarillado.remove(n); break;
            case 5: niñosCallePrincipal.remove(n); break;
            case 6: niñosSotanoByers.remove(n); break;
            case 7: niñosRadioWSQK.remove(n); break;
        }
    }
    
    
    public void esperarEnPortal(int zona, Niños n) {

    CyclicBarrier barrier;
    Lock lock;
    String destino;

    switch (zona) {
        case 1:
            barrier = portalBosque;
            lock = lockBosque;
            destino = "BOSQUE";
            break;
        case 2:
            barrier = portalLaboratorio;
            lock = lockLaboratorio;
            destino = "LABORATORIO";
            break;
        case 3:
            barrier = portalCentro;
            lock = lockCentro;
            destino = "CENTRO COMERCIAL";
            break;
        case 4:
            barrier = portalAlcantarillado;
            lock = lockAlcantarillado;
            destino = "ALCANTARILLADO";
            break;
        default:
            throw new IllegalArgumentException("Zona inválida");
    }

    try {
        System.out.println("Niño " + n.getIdNiño()+ " espera portal " + destino);

        barrier.await();

        lock.lock();
        try {
            System.out.println("Niño " + n.getIdNiño()+ " cruza portal a " + destino);
            Thread.sleep(1000);
        } finally {
            lock.unlock();
        }

    } catch (InterruptedException | BrokenBarrierException e) {
        Thread.currentThread().interrupt();
    }
}
    
    public synchronized void incrementarSangre(){
        contadorSangre++;
        if(eventos.hayEleven()){
            contadorSangreDuranteEleven++;
        }
    }
    
    public synchronized void entrarDemogorgon(int zona, Demogorgons d){
        switch(zona){
            case 1:
                demogorgonsBosque.add(d);
                break;
            case 2:
                demogorgonsLaboratorio.add(d);
                break;
            case 3:
                demogorgonsCentroComercial.add(d);
                break;
            case 4:
                demogorgonsAlcantarillado.add(d);
                break;
            default:
                break;
        }
}

    
    public synchronized void eliminarListaDemogorgon(int zona, Demogorgons d){
        switch (zona) {
            case 1: 
                demogorgonsBosque.remove(d); 
                break;
            case 2: 
                demogorgonsLaboratorio.remove(d); 
                break;
            case 3: 
                demogorgonsCentroComercial.remove(d); 
                break;
            case 4: 
                demogorgonsAlcantarillado.remove(d); 
                break;
        }
    }
    
    
    public synchronized boolean hay_niño(int num){
        switch(num){
            case 1:
                return niñosBosque.size()>=1;
            case 2: 
                return niñosLaboratorio.size()>=1;
            case 3:
                return niñosCentroComercial.size()>=1;
            case 4:
                return niñosAlcantarillado.size()>=1;
            default:
                return false;
        }
    }
    
    private synchronized void eliminarNiñoDeTodasLasListas(Niños n) {
    niñosBosque.remove(n);
    niñosLaboratorio.remove(n);
    niñosCentroComercial.remove(n);
    niñosAlcantarillado.remove(n);
    niñosCallePrincipal.remove(n);
    niñosSotanoByers.remove(n);
    niñosRadioWSQK.remove(n);
    niñosColmena.remove(n);
}
    
    
    public boolean hayTormenta() {
        return eventos.hayTormenta();
    }
    
    
    public synchronized int getContadorSangreDuranteEleven(){
        return contadorSangreDuranteEleven;
    }
    
    
    public synchronized void revivirNiños(){
        Niños niñoRevivido = null;
        for(int i=0; i<getContadorSangreDuranteEleven(); i++){
            if(!niñosColmena.isEmpty()){
                niñoRevivido = niñosColmena.get(0);
                niñosColmena.remove(niñoRevivido);
                niñoRevivido.finalizarAtaque(false);
            }else{
                break;
            }
        }
        contadorSangreDuranteEleven = 0;
        //booleano para que en el run de niño lo reconozca y comience de nuevo en la calle principal
    }
  
    
    public void demogorgonAtacar(int num, Demogorgons d){
        int probabilidad = (int)(Math.random() * 3) + 1;
        Niños objetivo = null;
        boolean capturado=false;

        atacar.lock();
        try{
            if (hay_niño(num)) {
                List<Niños> lista = switch (num) {
                    case 1 -> niñosBosque;
                    case 2 -> niñosLaboratorio;
                    case 3 -> niñosCentroComercial;
                    case 4 -> niñosAlcantarillado;
                    default -> null;
                };

                if (lista != null && !lista.isEmpty()) {
                    Niños candidato = lista.get((int)(Math.random() * lista.size()));
                    if (candidato.intentarSerAtacado()) {
                        objetivo = candidato;
                    }
                }
            }
        } catch(Exception e){
        } finally {
            atacar.unlock();
        }

        if (objetivo == null) {
            try {
                if(eventos.hayTormenta()){
                    Thread.sleep(((int)(Math.random()*1000)+4000)/2);
                }else{
                    Thread.sleep((int)(Math.random()*1000)+4000);
                }
            } catch (Exception e) {
            }
            return;
        }
        try {
                if(eventos.hayTormenta()){
                    Thread.sleep(((int)(Math.random() * 1000) + 500)/2);
                }else{
                    Thread.sleep((int)(Math.random() * 1000) + 500);
                }
            } catch (Exception e) {
            }
        

        atacar.lock();
        try {
            if (probabilidad == 3) {
                capturado=true;
                eliminarNiñoDeTodasLasListas(objetivo);
                niñosColmena.add(objetivo);    
                niñosEnColmena++;
                System.out.println("Ninos en Colmena " + niñosEnColmena);
                d.incrementar_capturas();

                if (niñosEnColmena % 8 == 0) {
                    new Demogorgons(this, contadorDemogorgons++).start();
                }

                objetivo.finalizarAtaque(true);
            } else {
                objetivo.finalizarAtaque(false);
            }
        } catch(Exception e){
        } finally {
            atacar.unlock();
        }
        if(capturado){
            try {
                if(eventos.hayTormenta()){
                    Thread.sleep(((int)(Math.random() * 500) + 500)/2);
                }else{
                    Thread.sleep((int)(Math.random() * 500) + 500);
                }
            } catch (Exception e) {
            }    
        }
        
       
    }
}
