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
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.List;

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
    private List<Niños> niñosCallePrincipal = new CopyOnWriteArrayList<>();
    private List<Niños> niñosColmena = new CopyOnWriteArrayList<>();
    private List <Niños> niñosSotanoByers = Collections.synchronizedList(new ArrayList<>());
    private List <Niños> niñosRadioWSQK = Collections.synchronizedList(new ArrayList<>());



    private int niñosEnColmena=0;
    private int contadorDemogorgons=1;
    private int contadorSangre=0;
    private int contadorSangreDuranteEleven = 0;
    
    private Lock atacar = new ReentrantLock();
    private Eventos eventos;
   

    public void setEventos(Eventos eventos) {
    this.eventos = eventos;
}

    
    
    private class Portal {
        int capacidad;
        // Listas para mostrar nombres en la interfaz
        List<Niños> listaEsperaIda = Collections.synchronizedList(new ArrayList<>());
        List<Niños> listaEsperaVuelta = Collections.synchronizedList(new ArrayList<>());
        
        int cruzando = 0;
        int enGrupo = 0; 
        boolean grupoFormado = false;
    }
    
        private Portal bosque = new Portal();
    private Portal laboratorio = new Portal();
    private Portal centro = new Portal();
    private Portal alcantarillado = new Portal();


    public Mundo() {
        bosque.capacidad = 2;
        laboratorio.capacidad = 3;
        centro.capacidad = 4;
        alcantarillado.capacidad = 2;
    }
    
    
    
    private Portal getPortal(int zona) {
        return switch (zona) {
            case 1 -> bosque;
            case 2 -> laboratorio;
            case 3 -> centro;
            case 4 -> alcantarillado;
            default -> throw new IllegalArgumentException();
        };
    }

    private String nombreZona(int zona) {
        return switch (zona) {
            case 1 -> "BOSQUE";
            case 2 -> "LABORATORIO";
            case 3 -> "CENTRO COMERCIAL";
            case 4 -> "ALCANTARILLADO";
            default -> "DESCONOCIDO";
        };
    }
    
    public void esperarEnPortal(int zona, Niños n) {
    Portal p = getPortal(zona);
    
    synchronized (p) {
        p.listaEsperaIda.add(n); // Añadimos el objeto niño a la lista
        
        if (p.listaEsperaIda.size() >= p.capacidad && !p.grupoFormado) {
            p.notifyAll(); 
        }

        while (hayApagon() || p.grupoFormado || p.listaEsperaIda.size() < p.capacidad) {
            try { p.wait(); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        }

        if (!p.grupoFormado) {
            p.grupoFormado = true;
            p.enGrupo = p.capacidad;
        }
    }

    synchronized (p) {
        while (p.cruzando > 0 || !p.listaEsperaVuelta.isEmpty() || hayApagon()) {
            try { p.wait(); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        }
        
        p.cruzando++;
        p.listaEsperaIda.remove(n); // <--- Lo quitamos de la lista de espera al empezar a cruzar
        p.notifyAll(); 
    }

    try { Thread.sleep(1000); } catch (Exception e) {}

    synchronized (p) {
        p.cruzando--;
        p.enGrupo--;
        if (p.enGrupo == 0) p.grupoFormado = false;
        p.notifyAll();
    }
}

public void volverDePortal(int zona, Niños n) {
    Portal p = getPortal(zona);

    synchronized (p) {
        p.listaEsperaVuelta.add(n); // Añadimos a la lista de vuelta
        p.notifyAll(); 
    }

    synchronized (p) {
        while (p.cruzando > 0 || hayApagon()) {
            try { p.wait(); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        }
        p.cruzando++;
        p.listaEsperaVuelta.remove(n); // <--- Lo quitamos al empezar a cruzar
        p.notifyAll();
    }

    try { Thread.sleep(1000); } catch (Exception e) {}

    synchronized (p) {
        p.cruzando--;
        p.notifyAll();
    }
}
    
    public boolean hayApagon() {
    return eventos != null && eventos.hayApagon();
}
    
    
    public void despertarPortales() {
    synchronized (bosque) { bosque.notifyAll(); }
    synchronized (laboratorio) { laboratorio.notifyAll(); }
    synchronized (centro) { centro.notifyAll(); }
    synchronized (alcantarillado) { alcantarillado.notifyAll(); }
}
    
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
    
    public synchronized void eliminarNiñoDeTodasLasListas(Niños n) {
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
        int cantidad = Math.min(contadorSangreDuranteEleven, niñosColmena.size());

        for(int i = 0; i < cantidad; i++){
            Niños niñoRevivido = niñosColmena.remove(0);

            niñosEnColmena--;

            niñoRevivido.liberarPorEleven();
            entrarNiño(5, niñoRevivido);

            System.out.println("Eleven libera a " + niñoRevivido.getIdNiño()
                    + " y vuelve a la Calle Principal.");
        }

        contadorSangreDuranteEleven = 0;
    }
  
    
    public void demogorgonAtacar(int num, Demogorgons d){
        int probabilidad = (int)(Math.random() * 3) + 1;
        Niños objetivo = null;
        boolean capturado=false;
        
        while(eventos.hayEleven()){
            try {
                Thread.sleep(500);
            } catch (Exception e) {
            }
        }
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
        while(eventos.hayEleven()){
            try {
                Thread.sleep(500);
            } catch (Exception e) {
            }
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
        
        while(eventos.hayEleven()){
            try {
                Thread.sleep(500);
            } catch (Exception e) {
            }
        }
        
        try {
                if(eventos.hayTormenta()){
                    Thread.sleep(((int)(Math.random() * 1000) + 500)/2);
                }else{
                    Thread.sleep((int)(Math.random() * 1000) + 500);
                }
            } catch (Exception e) {
            }
        
        while(eventos.hayEleven()){
            try {
                Thread.sleep(500);
            } catch (Exception e) {
            }
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
                    new Demogorgons(this,eventos, contadorDemogorgons++).start();
                }

                objetivo.finalizarAtaque(true);
            } else {
                objetivo.finalizarAtaque(false);
            }
        } catch(Exception e){
        } finally {
            atacar.unlock();
        }
        while(eventos.hayEleven()){
            try {
                Thread.sleep(500);
            } catch (Exception e) {
            }
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
    
    
    public synchronized int zonaConMasNiños() {
        int max = niñosBosque.size();
        int zona = 1;

        if (niñosLaboratorio.size() > max) {
            max = niñosLaboratorio.size();
            zona = 2;
        }
        if (niñosCentroComercial.size() > max) {
            max = niñosCentroComercial.size();
            zona = 3;
        }
        if (niñosAlcantarillado.size() > max) {
            zona = 4;
        }

        return zona;
    }
    
    public List<Niños> getNiñosColmena() {
        return niñosColmena;
    }
    
    public List<Niños> getNiñosCallePrincipal() {
        return niñosCallePrincipal;
    }
    
    public List<Niños> getNiñosSotanoByers(){
        return niñosSotanoByers;
    }
    public List<Niños> getNiñosRadioWSQK(){
            return niñosRadioWSQK;
    }
    
    public List<Niños> getEntidadesBosque() {
        return new ArrayList<>(niñosBosque); 
    }

    public List<Niños> getEntidadesLab() {
        return new ArrayList<>(niñosLaboratorio);
    }

    public List<Niños> getEntidadesCentroComercial() {
        return new ArrayList<>(niñosCentroComercial);
    }

    public List<Niños> getEntidadesAlcantarillado() {
        return new ArrayList<>(niñosAlcantarillado);
    }
    
    public List<Demogorgons> getDemosBosque() {
        return new ArrayList<>(demogorgonsBosque);
    }

    public List<Demogorgons> getDemosLab() {
        return new ArrayList<>(demogorgonsLaboratorio);
    }
    
    public List<Niños> getColaEntradaPortal(int zona) {
    return new ArrayList<>(getPortal(zona).listaEsperaIda);
    }

    public List<Niños> getColaVolverPortal(int zona) {
        return new ArrayList<>(getPortal(zona).listaEsperaVuelta);
    }
    
    public List<Demogorgons> getDemosCentroComercial() {
        return new ArrayList<>(demogorgonsCentroComercial);
    }

    public List<Demogorgons> getDemosAlcantarillado() {
        return new ArrayList<>(demogorgonsAlcantarillado);
    }
    
    
}
