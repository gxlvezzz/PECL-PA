/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pecl.pa;

import static java.lang.Thread.sleep;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
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
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Mundo extends UnicastRemoteObject implements InterfazMundo {

    // ============================================================
    // LISTAS DE NIÑOS POR ZONA
    // ============================================================

    private List<Niños> niñosBosque = Collections.synchronizedList(new ArrayList<>());
    private List<Niños> niñosLaboratorio = Collections.synchronizedList(new ArrayList<>());
    private List<Niños> niñosCentroComercial = Collections.synchronizedList(new ArrayList<>());
    private List<Niños> niñosAlcantarillado = Collections.synchronizedList(new ArrayList<>());

    private List<Niños> niñosCallePrincipal = new CopyOnWriteArrayList<>();
    private List<Niños> niñosColmena = new CopyOnWriteArrayList<>();
    private List<Niños> niñosSotanoByers = Collections.synchronizedList(new ArrayList<>());
    private List<Niños> niñosRadioWSQK = Collections.synchronizedList(new ArrayList<>());

    // ============================================================
    // LISTAS DE DEMOGORGONS POR ZONA
    // ============================================================

    private List<Demogorgons> demogorgonsBosque = Collections.synchronizedList(new ArrayList<>());
    private List<Demogorgons> demogorgonsLaboratorio = Collections.synchronizedList(new ArrayList<>());
    private List<Demogorgons> demogorgonsCentroComercial = Collections.synchronizedList(new ArrayList<>());
    private List<Demogorgons> demogorgonsAlcantarillado = Collections.synchronizedList(new ArrayList<>());

    // Lista auxiliar para poder calcular el ranking remoto de capturas.
    private List<Demogorgons> todosDemogorgons = Collections.synchronizedList(new ArrayList<>());

    // Lista auxiliar con todos los hilos registrados.
    private List<Thread> hilosActivos = Collections.synchronizedList(new ArrayList<>());

    // ============================================================
    // CONTADORES DEL SISTEMA
    // ============================================================

    private int niñosEnColmena = 0;
    private int contadorDemogorgons = 1;
    private int contadorSangre = 0;
    private int contadorSangreDuranteEleven = 0;

    // ============================================================
    // SINCRONIZACIÓN GENERAL
    // ============================================================

    /*
     * Lock usado para proteger la elección de objetivo y la modificación
     * de listas durante los ataques de los demogorgons.
     */
    private Lock atacar = new ReentrantLock();

    /*
     * Objeto usado exclusivamente para pausar y reanudar la simulación.
     * Así no se bloquea el monitor principal de Mundo.
     */
    private final Object lockPausa = new Object();

    private volatile boolean pausado = false;

    private Eventos eventos;

    // ============================================================
    // CLASE INTERNA PORTAL
    // ============================================================

    /*
     * Cada zona insegura tiene su propio portal.
     * Cada portal actúa como monitor independiente, de forma que un portal
     * puede estar bloqueado sin bloquear necesariamente el resto del mundo.
     */
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

    // ============================================================
    // CONSTRUCTOR
    // ============================================================

    public Mundo() throws RemoteException {
        bosque.capacidad = 2;
        laboratorio.capacidad = 3;
        centro.capacidad = 4;
        alcantarillado.capacidad = 2;
    }

    public void setEventos(Eventos eventos) {
        this.eventos = eventos;
    }

    // ============================================================
    // MÉTODOS AUXILIARES DE ZONAS Y PORTALES
    // ============================================================

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

    public boolean hayApagon() {
        return eventos != null && eventos.hayApagon();
    }

    public boolean hayTormenta() {
        return eventos.hayTormenta();
    }

    public String getEventoActual() {
        if (eventos == null) {
            return "SIN EVENTO";
        }

        return eventos.getEventoActual();
    }

    // ============================================================
    // GESTIÓN DE PORTALES
    // ============================================================

    /*
     * Gestiona la entrada de los niños al Upside Down.
     *
     * Reglas principales:
     * - Cada portal tiene una capacidad de grupo distinta.
     * - El grupo de ida es exclusivo.
     * - No se cruza durante un apagón.
     * - Si hay niños esperando para volver, tienen prioridad.
     * - Los niños del grupo cruzan individualmente, pero pertenecen a un grupo ya formado.
     */
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

    /*
     * Gestiona el regreso desde el Upside Down hacia Hawkins.
     * Los niños que vuelven tienen prioridad frente a los que quieren entrar.
     */
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

    /*
     * Se llama al finalizar un apagón para despertar a los niños que estaban
     * esperando en los portales.
     */
    public void despertarPortales() {
        synchronized (bosque) {
            bosque.notifyAll();
        }

        synchronized (laboratorio) {
            laboratorio.notifyAll();
        }

        synchronized (centro) {
            centro.notifyAll();
        }

        synchronized (alcantarillado) {
            alcantarillado.notifyAll();
        }
    }

    // ============================================================
    // ENTRADA Y SALIDA DE NIÑOS EN ZONAS
    // ============================================================

    public synchronized void entrarNiño(int zona, Niños n) {
        switch (zona) {
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
            case 1:
                niñosBosque.remove(n);
                break;
            case 2:
                niñosLaboratorio.remove(n);
                break;
            case 3:
                niñosCentroComercial.remove(n);
                break;
            case 4:
                niñosAlcantarillado.remove(n);
                break;
            case 5:
                niñosCallePrincipal.remove(n);
                break;
            case 6:
                niñosSotanoByers.remove(n);
                break;
            case 7:
                niñosRadioWSQK.remove(n);
                break;
            default:
                break;
        }
    }

    /*
     * Método defensivo: elimina un niño de todas las listas posibles.
     * Se usa especialmente cuando un niño es capturado o reinicia ciclo.
     */
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

    public synchronized boolean hay_niño(int num) {
        switch (num) {
            case 1:
                return niñosBosque.size() >= 1;
            case 2:
                return niñosLaboratorio.size() >= 1;
            case 3:
                return niñosCentroComercial.size() >= 1;
            case 4:
                return niñosAlcantarillado.size() >= 1;
            default:
                return false;
        }
    }

    // ============================================================
    // ENTRADA Y SALIDA DE DEMOGORGONS EN ZONAS
    // ============================================================

    public synchronized void entrarDemogorgon(int zona, Demogorgons d) {
        switch (zona) {
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

    public synchronized void eliminarListaDemogorgon(int zona, Demogorgons d) {
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
            default:
                break;
        }
    }

    public synchronized void registrarDemogorgon(Demogorgons d) {
        if (!todosDemogorgons.contains(d)) {
            todosDemogorgons.add(d);
        }
    }

    // ============================================================
    // SANGRE Y EVENTO DE ELEVEN
    // ============================================================

    public synchronized void incrementarSangre() {
        contadorSangre++;

        if (eventos.hayEleven()) {
            contadorSangreDuranteEleven++;
        }
    }

    public int getSangreAcumulada() {
        return contadorSangre;
    }

    public synchronized int getContadorSangreDuranteEleven() {
        return contadorSangreDuranteEleven;
    }

    /*
     * Libera niños de la Colmena cuando finaliza la intervención de Eleven.
     * Se liberan tantos niños como unidades de sangre se hayan recogido durante el evento.
     */
    public synchronized void revivirNiños() {
        int cantidad = Math.min(contadorSangreDuranteEleven, niñosColmena.size());

        for (int i = 0; i < cantidad; i++) {
            Niños niñoRevivido = niñosColmena.remove(0);

            niñosEnColmena--;

            niñoRevivido.liberarPorEleven();
            entrarNiño(5, niñoRevivido);

            System.out.println("Eleven libera a " + niñoRevivido.getIdNiño()
                    + " y vuelve a la Calle Principal.");

            LogHawkins.escribir("EVENTO GLOBAL: Eleven ha liberado al niño "
                    + niñoRevivido.getIdNiño() + " de la Colmena");
        }

        contadorSangreDuranteEleven = 0;
    }

    // ============================================================
    // ATAQUES DE DEMOGORGONS
    // ============================================================

    /*
     * Gestiona el ataque de un demogorgon.
     *
     * Se usa el Lock atacar para proteger:
     * - selección del niño objetivo,
     * - eliminación del niño de su zona,
     * - traslado a la Colmena,
     * - incremento de contadores.
     *
     * Además, cada niño protege su propio estado con métodos synchronized,
     * evitando que dos demogorgons ataquen al mismo niño simultáneamente.
     */
    public void demogorgonAtacar(int num, Demogorgons d) {
        int probabilidad = (int) (Math.random() * 3) + 1;
        Niños objetivo = null;
        boolean capturado = false;

        while (eventos.hayEleven()) {
            try {
                Thread.sleep(500);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        atacar.lock();

        try {
            if (hay_niño(num)) {
                List<Niños> lista = switch (num) {
                    case 1 -> niñosBosque;
                    case 2 -> niñosLaboratorio;
                    case 3 -> niñosCentroComercial;
                    case 4 -> niñosAlcantarillado;
                    default -> null;
                };

                if (lista != null && !lista.isEmpty()) {
                    Niños candidato = lista.get((int) (Math.random() * lista.size()));

                    if (candidato.intentarSerAtacado()) {
                        objetivo = candidato;
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            atacar.unlock();
        }

        while (eventos.hayEleven()) {
            try {
                Thread.sleep(500);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (objetivo == null) {
            try {
                if (eventos.hayTormenta()) {
                    Thread.sleep(((int) (Math.random() * 1000) + 4000) / 2);
                } else {
                    Thread.sleep((int) (Math.random() * 1000) + 4000);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            return;
        }

        while (eventos.hayEleven()) {
            try {
                Thread.sleep(500);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        try {
            if (eventos.hayTormenta()) {
                Thread.sleep(((int) (Math.random() * 1000) + 500) / 2);
            } else {
                Thread.sleep((int) (Math.random() * 1000) + 500);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        while (eventos.hayEleven()) {
            try {
                Thread.sleep(500);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        atacar.lock();

        try {
            if (probabilidad == 3) {
                capturado = true;

                eliminarNiñoDeTodasLasListas(objetivo);
                niñosColmena.add(objetivo);
                niñosEnColmena++;

                d.incrementar_capturas();

                LogHawkins.escribir("El demogorgon " + d.toString()
                        + " ataca al niño " + objetivo.getIdNiño());

                LogHawkins.escribir("El niño " + objetivo.getIdNiño()
                        + " ha sido capturado");

                if (niñosEnColmena % 8 == 0) {
                    new Demogorgons(this, eventos, contadorDemogorgons++).start();

                    LogHawkins.escribir("La Red Mental se expande: "
                            + "Un nuevo Demogorgon ha nacido.");
                }

                objetivo.finalizarAtaque(true);

            } else {
                objetivo.finalizarAtaque(false);
            }

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            atacar.unlock();
        }

        while (eventos.hayEleven()) {
            try {
                Thread.sleep(500);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (capturado) {
            try {
                if (eventos.hayTormenta()) {
                    Thread.sleep(((int) (Math.random() * 500) + 500) / 2);
                } else {
                    Thread.sleep((int) (Math.random() * 500) + 500);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // ============================================================
    // RED MENTAL
    // ============================================================

    /*
     * Devuelve la zona del Upside Down con más niños.
     * Se usa cuando está activo el evento de la Red Mental.
     */
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

    // ============================================================
    // PAUSA Y REANUDACIÓN
    // ============================================================

    /*
     * Punto de control de pausa.
     * Los hilos llaman periódicamente a este método.
     * Si el sistema está pausado, quedan esperando en lockPausa.
     */
    public void comprobarPausa() {
        synchronized (lockPausa) {
            while (pausado) {
                try {
                    lockPausa.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    public void pausar() {
        pausado = true;
    }

    public void reanudar() {
        synchronized (lockPausa) {
            pausado = false;
            lockPausa.notifyAll();
        }
    }

    public void registrarHilo(Thread t) {
        hilosActivos.add(t);
    }

    // ============================================================
    // GETTERS PARA INTERFAZ LOCAL
    // ============================================================

    public List<Niños> getNiñosColmena() {
        return niñosColmena;
    }

    public List<Niños> getNiñosCallePrincipal() {
        return niñosCallePrincipal;
    }

    public List<Niños> getNiñosSotanoByers() {
        return niñosSotanoByers;
    }

    public List<Niños> getNiñosRadioWSQK() {
        return niñosRadioWSQK;
    }

    public synchronized List<Niños> getNiñosColmenaPrueba() {
        return new ArrayList<>(niñosColmena);
    }

    public synchronized List<Niños> getNiñosCallePrincipalPrueba() {
        return new ArrayList<>(niñosCallePrincipal);
    }

    public synchronized List<Niños> getNiñosSotanoByersPrueba() {
        return new ArrayList<>(niñosSotanoByers);
    }

    public synchronized List<Niños> getNiñosRadioWSQKPrueba() {
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

    public List<Demogorgons> getDemosCentroComercial() {
        return new ArrayList<>(demogorgonsCentroComercial);
    }

    public List<Demogorgons> getDemosAlcantarillado() {
        return new ArrayList<>(demogorgonsAlcantarillado);
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

    // ============================================================
    // CONTADORES PARA INTERFAZ REMOTA
    // ============================================================

    public synchronized int contadorNiñosHawkins() {
        return niñosCallePrincipal.size()
                + niñosSotanoByers.size()
                + niñosRadioWSQK.size();
    }

    public synchronized int contadorNiñosdUPD(int zona) {
        int contador = 0;

        switch (zona) {
            case 1:
                contador = niñosBosque.size();
                break;
            case 2:
                contador = niñosLaboratorio.size();
                break;
            case 3:
                contador = niñosCentroComercial.size();
                break;
            case 4:
                contador = niñosAlcantarillado.size();
                break;
            default:
                break;
        }

        return contador;
    }

    public synchronized int contadorDemogorgons(int zona) {
        int contador = 0;

        switch (zona) {
            case 1:
                contador = demogorgonsBosque.size();
                break;
            case 2:
                contador = demogorgonsLaboratorio.size();
                break;
            case 3:
                contador = demogorgonsCentroComercial.size();
                break;
            case 4:
                contador = demogorgonsAlcantarillado.size();
                break;
            default:
                break;
        }

        return contador;
    }

    // ============================================================
    // MÉTODOS REMOTOS RMI
    // ============================================================

    @Override
    public synchronized String getEventoActualRemoto() throws RemoteException {
        return getEventoActual();
    }

    @Override
    public synchronized int getHawkinsRemoto() throws RemoteException {
        return contadorNiñosHawkins();
    }

    @Override
    public synchronized int getPortal1Remoto() throws RemoteException {
        return bosque.listaEsperaIda.size()
                + bosque.listaEsperaVuelta.size()
                + bosque.cruzando;
    }

    @Override
    public synchronized int getPortal2Remoto() throws RemoteException {
        return laboratorio.listaEsperaIda.size()
                + laboratorio.listaEsperaVuelta.size()
                + laboratorio.cruzando;
    }

    @Override
    public synchronized int getPortal3Remoto() throws RemoteException {
        return centro.listaEsperaIda.size()
                + centro.listaEsperaVuelta.size()
                + centro.cruzando;
    }

    @Override
    public synchronized int getPortal4Remoto() throws RemoteException {
        return alcantarillado.listaEsperaIda.size()
                + alcantarillado.listaEsperaVuelta.size()
                + alcantarillado.cruzando;
    }

    @Override
    public synchronized int getNiñosBosqueRemoto() throws RemoteException {
        return contadorNiñosdUPD(1);
    }

    @Override
    public synchronized int getNiñosLaboratorioRemoto() throws RemoteException {
        return contadorNiñosdUPD(2);
    }

    @Override
    public synchronized int getNiñosCentroComercialRemoto() throws RemoteException {
        return contadorNiñosdUPD(3);
    }

    @Override
    public synchronized int getNiñosAlcantarilladoRemoto() throws RemoteException {
        return contadorNiñosdUPD(4);
    }

    @Override
    public synchronized int getNiñosColmenaRemoto() throws RemoteException {
        return niñosEnColmena;
    }

    @Override
    public synchronized int getDemogorgonsBosqueRemoto() throws RemoteException {
        return contadorDemogorgons(1);
    }

    @Override
    public synchronized int getDemogorgonsLaboratorioRemoto() throws RemoteException {
        return contadorDemogorgons(2);
    }

    @Override
    public synchronized int getDemogorgonsCentroComercialRemoto() throws RemoteException {
        return contadorDemogorgons(3);
    }

    @Override
    public synchronized int getDemogorgonsAlcantarilladoRemoto() throws RemoteException {
        return contadorDemogorgons(4);
    }

    @Override
    public synchronized int getTiempoEventoRemoto() throws RemoteException {
        if (eventos == null) {
            return 0;
        }

        return eventos.getTiempoRestanteEvento();
    }

    /*
     * Devuelve el ranking de los tres demogorgons con más capturas.
     * Se devuelve como String porque el cliente remoto sólo necesita mostrarlo.
     */
    @Override
    public synchronized String getRankingDemogorgonsRemoto() throws RemoteException {
        List<Demogorgons> copia = new ArrayList<>(todosDemogorgons);

        copia.sort((d1, d2) -> Integer.compare(d2.getCapturas(), d1.getCapturas()));

        StringBuilder sb = new StringBuilder();

        int limite = Math.min(3, copia.size());

        for (int i = 0; i < limite; i++) {
            Demogorgons d = copia.get(i);

            sb.append(i + 1)
              .append(". ")
              .append(d.toString())
              .append(" (")
              .append(d.getCapturas())
              .append(")")
              .append("\n");
        }

        if (sb.length() == 0) {
            return "Sin demogorgons";
        }

        return sb.toString();
    }

    @Override
    public void pausarRemoto() throws RemoteException {
        pausar();
    }

    @Override
    public void reanudarRemoto() throws RemoteException {
        reanudar();
    }
}