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
    
    private Lock atacar = new ReentrantLock();
   
    

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
        System.out.println("Niño " + n.getId() + " espera portal " + destino);

        barrier.await();

        lock.lock();
        try {
            System.out.println("Niño " + n.getId() + " cruza portal a " + destino);
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
  
    
    public void demogorgonAtacar(int num, Demogorgons d){
        int probabilidad = (int)(Math.random() * 3) + 1;
        Niños objetivo = null;

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
                Thread.sleep(4000);
            } catch (Exception e) {
            }
            return;
        }

        try {
            Thread.sleep((int)(Math.random() * 1000) + 500);
        } catch (Exception e) {
        }

        atacar.lock();
        try {
            if (probabilidad == 3) {
                switch (num) {
                    case 1 -> niñosBosque.remove(objetivo);
                    case 2 -> niñosLaboratorio.remove(objetivo);
                    case 3 -> niñosCentroComercial.remove(objetivo);
                    case 4 -> niñosAlcantarillado.remove(objetivo);
                }

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
    }
}
