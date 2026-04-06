/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pecl.pa;

import static java.lang.Thread.sleep;
import java.util.*;


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
    
    private final int[] esperandoEnPortal = new int[5];
    
    private int niñosEnColmena=0;
    private int contadorDemogorgons=1;
    

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
    
    
    public synchronized void esperarEnPortal(int zona, Niños n) throws InterruptedException {
        int capacidad = (zona == 1 || zona == 4) ? 2 : (zona == 2 ? 3 : 4);
        esperandoEnPortal[zona]++;

        if (esperandoEnPortal[zona] < capacidad) {
            wait(); 
        } else {
            esperandoEnPortal[zona] = 0; 
            notifyAll(); 
        }
        Thread.sleep(1000); 
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
        int probabilidad = (int) (Math.random() * 3) + 1;
        Niños objetivo = null;

        synchronized (this) {
            if (hay_niño(num)) {
                List<Niños> lista = switch (num) {
                    case 1 -> niñosBosque;
                    case 2 -> niñosLaboratorio;
                    case 3 -> niñosCentroComercial;
                    case 4 -> niñosAlcantarillado;
                    default -> null;
                };
                if (lista != null && !lista.isEmpty()) {
                    objetivo = lista.get((int) (Math.random() * lista.size()));
                }
            }
        }

        if (objetivo != null) {
            try { Thread.sleep((int) (Math.random() * 1000) + 500); } catch (Exception e) {}

            if (probabilidad == 3) {  
                synchronized (this) {
                    objetivo.setCapturado(true); 
                    
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
                }
            }
        } else {
            try { Thread.sleep(4000); } catch (Exception e) {}
        }
    
        
    }
}
