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
            default:
                break;
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

    
    public synchronized void eliminarListaDemogorgon(Demogorgons d){
        demogorgonsBosque.remove(d);
        demogorgonsAlcantarillado.remove(d);
        demogorgonsCentroComercial.remove(d);
        demogorgonsLaboratorio.remove(d);
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
        int probabilidad = (int)(Math.random()*3)+1;
        Niños objetivo = null;

        synchronized(this){
            if(hay_niño(num)){
                int index = 0;

                switch(num){
                    case 1:
                        index = (int)(Math.random()*niñosBosque.size());
                        objetivo = niñosBosque.get(index);
                        break;
                    case 2: 
                        index = (int)(Math.random()*niñosLaboratorio.size());
                        objetivo = niñosLaboratorio.get(index);
                        break;
                    case 3:
                        index = (int)(Math.random()*niñosCentroComercial.size());
                        objetivo = niñosCentroComercial.get(index);
                        break;
                    case 4:
                        index = (int)(Math.random()*niñosAlcantarillado.size());
                        objetivo = niñosAlcantarillado.get(index);
                        break;
                }
            }
        }

        if(objetivo != null){
            try{
                Thread.sleep((int)(Math.random()*1000)+500);
            }catch(Exception e){
            }

            if(probabilidad==3){
                synchronized(this){
                    niñosEnColmena += 1;

                    if (niñosEnColmena % 8 == 0) {
                        Demogorgons nuevo = new Demogorgons(this, contadorDemogorgons++);
                        nuevo.start();
                    }

                    switch(num){
                        case 1:
                            niñosBosque.remove(objetivo);
                            break;
                        case 2: 
                            niñosLaboratorio.remove(objetivo);
                            break;
                        case 3:
                            niñosCentroComercial.remove(objetivo);
                            break;
                        case 4:
                            niñosAlcantarillado.remove(objetivo);
                            break;
                    }

                    d.incrementar_capturas();
                }

                try{
                    Thread.sleep((int)(Math.random()*500)+500);
                }catch(Exception e){
                }
            }
        } else {
            try{
                Thread.sleep((int)(Math.random()*1000)+4000);
            }catch(Exception e){
            }
        }
    }
}
