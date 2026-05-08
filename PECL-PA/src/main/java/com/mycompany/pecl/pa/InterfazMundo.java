/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.pecl.pa;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author julia_ntxs1ki
 */
public interface InterfazMundo extends Remote {

    // Información general de Hawkins.
    int getHawkinsRemoto() throws RemoteException;

    // Estado de los portales.
    int getPortal1Remoto() throws RemoteException;
    int getPortal2Remoto() throws RemoteException;
    int getPortal3Remoto() throws RemoteException;
    int getPortal4Remoto() throws RemoteException;

    // Niños en el Upside Down.
    int getNiñosBosqueRemoto() throws RemoteException;
    int getNiñosLaboratorioRemoto() throws RemoteException;
    int getNiñosCentroComercialRemoto() throws RemoteException;
    int getNiñosAlcantarilladoRemoto() throws RemoteException;
    int getNiñosColmenaRemoto() throws RemoteException;

    // Demogorgons en el Upside Down.
    int getDemogorgonsBosqueRemoto() throws RemoteException;
    int getDemogorgonsLaboratorioRemoto() throws RemoteException;
    int getDemogorgonsCentroComercialRemoto() throws RemoteException;
    int getDemogorgonsAlcantarilladoRemoto() throws RemoteException;

    // Información de eventos y ranking.
    String getRankingDemogorgonsRemoto() throws RemoteException;
    String getEventoActualRemoto() throws RemoteException;
    int getTiempoEventoRemoto() throws RemoteException;

    // Control remoto del programa principal.
    void pausarRemoto() throws RemoteException;
    void reanudarRemoto() throws RemoteException;
}
