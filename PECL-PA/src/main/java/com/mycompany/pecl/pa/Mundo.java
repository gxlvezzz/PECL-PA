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
    private List<Thread> hilosActivos = Collections.synchronizedList(new ArrayList<>());


    private int niñosEnColmena=0;
    private int contadorDemogorgons=1;
    private int contadorSangre=0;
    private int contadorSangreDuranteEleven = 0;
    
    private Lock atacar = new ReentrantLock();
    private Eventos eventos;
    

    private final Object lockPausa = new Object(); // Objeto dedicado solo a la pausa
    private volatile boolean pausado = false;
    
    private LogHawkins log = new LogHawkins();

    public void setEventos(Eventos eventos) {
    this.eventos = eventos;
}

    
    
    private class Portal {
        int capacidad;

        List<Niños> listaEsperaIda = Collections.synchronizedList(new ArrayList<>());
        List<Niños> listaEsperaVuelta = Collections.synchronizedList(new ArrayList<>());

        List<Niños> grupoIdaActual = new ArrayList<>();

        int cruzando = 0;
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
        comprobarPausa();
        Portal p = getPortal(zona);
        String destino = nombreZona(zona);

        synchronized (p) {
            p.listaEsperaIda.add(n);
            System.out.println("Niño " + n.getIdNiño() + " espera portal hacia " + destino);
            
            while (!p.grupoIdaActual.contains(n)) {

                if (!hayApagon()
                        && !p.grupoFormado
                        && p.cruzando == 0
                        && p.listaEsperaVuelta.isEmpty()
                        && p.listaEsperaIda.size() >= p.capacidad) {

                    p.grupoFormado = true;
                    p.grupoIdaActual.clear();

                    for (int i = 0; i < p.capacidad; i++) {
                        Niños niñoGrupo = p.listaEsperaIda.remove(0);
                        p.grupoIdaActual.add(niñoGrupo);
                    }

                    p.cruzando = p.capacidad;
                    p.notifyAll();
                } else {
                    try {
                        p.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
            }
        }

        System.out.println("Niño " + n.getIdNiño() + " cruza hacia " + destino);
        LogHawkins.escribir("El niño " + n.getIdNiño() + " ha cruzado el portal hacia " + destino);
        
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        synchronized (p) {
            p.cruzando--;
            p.grupoIdaActual.remove(n);

            if (p.cruzando == 0) {
                p.grupoFormado = false;
                p.grupoIdaActual.clear();
            }

            p.notifyAll();
        }
    }

    public void volverDePortal(int zona, Niños n) {
        Portal p = getPortal(zona);
        String origen = nombreZona(zona);

        synchronized (p) {
            p.listaEsperaVuelta.add(n);
            System.out.println("Niño " + n.getIdNiño() + " espera para volver desde " + origen);
            p.notifyAll();
            
        }

        synchronized (p) {
            while (hayApagon() || p.cruzando > 0) {
                try {
                    p.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }

            p.listaEsperaVuelta.remove(n);
            p.cruzando = 1;
        }

        System.out.println("Niño " + n.getIdNiño() + " REGRESA desde " + origen);
        LogHawkins.escribir("El niño " + n.getIdNiño() + " ha regresado a Hawkins desde " + origen);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        synchronized (p) {
            p.cruzando = 0;
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
    
    public int getSangreAcumulada() {
    return contadorSangre;
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
            LogHawkins.escribir("EVENTO GLOBAL: Eleven ha liberado al niño " + niñoRevivido.getIdNiño() + " de la Colmena");
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
                
                LogHawkins.escribir("El demogorgon " + d.toString() + " ataca al niño " + objetivo.getIdNiño());
                LogHawkins.escribir("El niño " + objetivo.getIdNiño() + " ha sido capturado");
                
                if (niñosEnColmena % 8 == 0) {
                    new Demogorgons(this,eventos, contadorDemogorgons++).start();
                    LogHawkins.escribir("La Red Mental se expande: Un nuevo Demogorgon ha nacido.");
                
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
    
    public synchronized List<Niños> getNiñosColmenaPrueba() {
        return new ArrayList<>(niñosColmena);
    }

    public synchronized List<Niños> getNiñosCallePrincipalPrueba() {
        return new ArrayList<>(niñosCallePrincipal);
    }

    public synchronized List<Niños> getNiñosSotanoByersPrueba(){
        return new ArrayList<>(niñosSotanoByers);
    }

    public synchronized List<Niños> getNiñosRadioWSQKPrueba(){
        return new ArrayList<>(niñosRadioWSQK);
    }
    
    public synchronized List<Niños> getEntidadesBosque() {
        return new ArrayList<>(niñosBosque); 
    }

    public synchronized List<Niños> getEntidadesLab() {
        return new ArrayList<>(niñosLaboratorio);
    }

    public synchronized List<Niños> getEntidadesCentroComercial() {
        return new ArrayList<>(niñosCentroComercial);
    }

    public synchronized List<Niños> getEntidadesAlcantarillado() {
        return new ArrayList<>(niñosAlcantarillado);
    }
    
    public List<Demogorgons> getDemosBosque() {
        return new ArrayList<>(demogorgonsBosque);
    }

    public List<Demogorgons> getDemosLab() {
        return new ArrayList<>(demogorgonsLaboratorio);
    }
    
    public List<Niños> getColaEntradaPortal(int zona) {
        Portal p = getPortal(zona);
        synchronized (p) {
            return new ArrayList<>(p.listaEsperaIda);
        }
    }

    public List<Niños> getColaVolverPortal(int zona) {
        Portal p = getPortal(zona);
        synchronized (p) {
            return new ArrayList<>(p.listaEsperaVuelta);
        }
    }
    
    public List<Demogorgons> getDemosCentroComercial() {
        return new ArrayList<>(demogorgonsCentroComercial);
    }

    public List<Demogorgons> getDemosAlcantarillado() {
        return new ArrayList<>(demogorgonsAlcantarillado);
    }
    
    public String getEventoActual() {
        if (eventos == null) {
            return "SIN EVENTO";
        }
        return eventos.getEventoActual();
    }
    
    public void comprobarPausa() {
        synchronized (lockPausa) {
            while (pausado) {
                try {
                    lockPausa.wait(); // El hilo se duerme aquí sin bloquear a Mundo
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    public void pausar() {
        pausado = true; // No necesita synchronized(this)
    }

    public void reanudar() {
        synchronized (lockPausa) {
            pausado = false;
            lockPausa.notifyAll(); // Despierta a todos los hilos
        }
    }
    
    

    public void registrarHilo(Thread t) {
        hilosActivos.add(t);
    }
    
    public LogHawkins getLog() {
        return log;
    }
    
    
}
