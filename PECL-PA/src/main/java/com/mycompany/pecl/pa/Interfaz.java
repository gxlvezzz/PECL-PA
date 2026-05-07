/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.pecl.pa;

import java.util.ArrayList;
import java.util.List;
import javax.swing.SwingUtilities;

/**
 *
 * @author adrii
 */
public class Interfaz extends javax.swing.JFrame {
    private final Mundo mundo;
    /**
     * Creates new form Interfaz
     */
    public Interfaz(Mundo mundo) {
        this.mundo = mundo;
        initComponents();
        this.getContentPane().setBackground(new java.awt.Color(45, 55, 45)); 

    }
    
    public void actualizar() {
    try {
        // 1. Obtención de listas de Portales (Colas de espera)
        // Usamos generarTexto con los nuevos métodos que devuelven List<Niños>
        String p1In = generarTexto(mundo.getColaEntradaPortal(1));
        String p2In = generarTexto(mundo.getColaEntradaPortal(2));
        String p3In = generarTexto(mundo.getColaEntradaPortal(3));
        String p4In = generarTexto(mundo.getColaEntradaPortal(4));

        String p1Out = generarTexto(mundo.getColaVolverPortal(1));
        String p2Out = generarTexto(mundo.getColaVolverPortal(2));
        String p3Out = generarTexto(mundo.getColaVolverPortal(3));
        String p4Out = generarTexto(mundo.getColaVolverPortal(4));

        // 2. Obtención de datos de Entidades (Niños y Demos) en Upside Down
        String bosqueN = generarTexto(mundo.getEntidadesBosque());
        String labN = generarTexto(mundo.getEntidadesLab());
        String ccN = generarTexto(mundo.getEntidadesCentroComercial());
        String alcanN = generarTexto(mundo.getEntidadesAlcantarillado());

        String bosqueD = generarTexto(mundo.getDemosBosque());
        String labD = generarTexto(mundo.getDemosLab());
        String ccD = generarTexto(mundo.getDemosCentroComercial());
        String alcanD = generarTexto(mundo.getDemosAlcantarillado());

        // 3. Obtención de datos de Hawkins
        String calleP = generarTexto(mundo.getNiñosCallePrincipalPrueba());
        String colmena = generarTexto(mundo.getNiñosColmenaPrueba());
        String sotano = generarTexto(mundo.getNiñosSotanoByersPrueba());
        String radio = generarTexto(mundo.getNiñosRadioWSQKPrueba());
        
        String eventoActual = mundo.getEventoActual();
        String sangre = String.valueOf(mundo.getSangreAcumulada());
        
        // 4. Actualización efectiva en el EDT
        SwingUtilities.invokeLater(() -> {
            // Portales Ida (Listas de espera)
            txtPortal1.setText(p1In);
            txtPortal2.setText(p2In);
            txtPortal3.setText(p3In);
            txtPortal4.setText(p4In);

            // Portales Vuelta (Listas de espera)
            txtVuelta1.setText(p1Out);
            txtVuelta2.setText(p2Out);
            txtVuelta3.setText(p3Out);
            txtVuelta4.setText(p4Out);

            // Niños Upside Down
            txtBosque.setText(bosqueN);
            txtLaboratorio.setText(labN);
            txtCentroComercial.setText(ccN);
            txtAlcantarillado.setText(alcanN);

            // Demos Upside Down
            txtBosqueDemo.setText(bosqueD);
            txtLaboratorioDemo.setText(labD);
            txtCentroComercialDemo.setText(ccD);
            txtAlcantarilladoDemo.setText(alcanD);

            // Hawkins
            txtCallePrincipal.setText(calleP);
            txtColmenaLista.setText(colmena);
            txtSotano.setText(sotano);
            txtRadio.setText(radio);
            
            txtEventoActual.setText(eventoActual);
            
            txtSangre.setText(sangre);
        });

    } catch (Exception e) {
        System.err.println("Error en el refresco de la interfaz: " + e.getMessage());
    }
}

    // Método sencillo para no repetir el bucle for 4 veces
    private String generarTexto(List<?> lista) {
    if (lista == null || lista.isEmpty()) {
        return "";
    }
    StringBuilder sb = new StringBuilder();
    for (Object obj : lista) {
        // obj.toString() llamará al método toString de Niño o de Demogorgon
        sb.append(obj.toString()).append("\n");
    }
    return sb.toString();
}

    private String generarTextoFiltrado(List<?> lista, char inicial) {
        if (lista == null || lista.isEmpty()) return "";
        StringBuilder sb = new StringBuilder();
        for (Object obj : lista) {
            String id = obj.toString();
            if (!id.isEmpty() && id.toUpperCase().charAt(0) == inicial) {
                sb.append(id).append("\n");
            }
        }
        return sb.toString();
    }



    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtColmenaLista = new javax.swing.JTextArea();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtCallePrincipal = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtSotano = new javax.swing.JTextArea();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtRadio = new javax.swing.JTextArea();
        jScrollPane5 = new javax.swing.JScrollPane();
        txtPortal1 = new javax.swing.JTextArea();
        jScrollPane6 = new javax.swing.JScrollPane();
        txtBosque = new javax.swing.JTextArea();
        jScrollPane7 = new javax.swing.JScrollPane();
        txtLaboratorio = new javax.swing.JTextArea();
        jScrollPane8 = new javax.swing.JScrollPane();
        txtCentroComercial = new javax.swing.JTextArea();
        jScrollPane9 = new javax.swing.JScrollPane();
        txtPortal2 = new javax.swing.JTextArea();
        jScrollPane10 = new javax.swing.JScrollPane();
        txtPortal3 = new javax.swing.JTextArea();
        jScrollPane11 = new javax.swing.JScrollPane();
        txtAlcantarillado = new javax.swing.JTextArea();
        jScrollPane12 = new javax.swing.JScrollPane();
        txtPortal4 = new javax.swing.JTextArea();
        jScrollPane21 = new javax.swing.JScrollPane();
        txtVuelta1 = new javax.swing.JTextArea();
        jScrollPane22 = new javax.swing.JScrollPane();
        txtVuelta2 = new javax.swing.JTextArea();
        jScrollPane23 = new javax.swing.JScrollPane();
        txtVuelta3 = new javax.swing.JTextArea();
        jScrollPane24 = new javax.swing.JScrollPane();
        txtVuelta4 = new javax.swing.JTextArea();
        jScrollPane13 = new javax.swing.JScrollPane();
        txtBosqueDemo = new javax.swing.JTextArea();
        jScrollPane14 = new javax.swing.JScrollPane();
        txtLaboratorioDemo = new javax.swing.JTextArea();
        jScrollPane15 = new javax.swing.JScrollPane();
        txtCentroComercialDemo = new javax.swing.JTextArea();
        jScrollPane16 = new javax.swing.JScrollPane();
        txtAlcantarilladoDemo = new javax.swing.JTextArea();
        txtEventoActual = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane17 = new javax.swing.JScrollPane();
        txtSangre = new javax.swing.JTextArea();
        jLabel13 = new javax.swing.JLabel();
        btnReanudar = new javax.swing.JButton();
        btnPausa = new javax.swing.JButton();

        jTextField1.setText("jTextField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("STRANGER THINGS");
        setBackground(new java.awt.Color(255, 0, 0));
        setPreferredSize(new java.awt.Dimension(1150, 600));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Monospaced", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 153, 51));
        jLabel1.setText("HAWKINS");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 0, 140, 50));

        jLabel2.setFont(new java.awt.Font("Monospaced", 1, 13)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 153, 51));
        jLabel2.setText("Calle Principal");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(37, 44, -1, -1));

        jLabel3.setFont(new java.awt.Font("Monospaced", 1, 13)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 153, 51));
        jLabel3.setText("Sótano Byers");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(37, 204, 100, -1));

        jLabel4.setFont(new java.awt.Font("Monospaced", 1, 13)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 153, 51));
        jLabel4.setText("Radio WSQK");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(37, 353, -1, -1));

        jLabel5.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 153, 51));
        jLabel5.setText("Niños En Colmena");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 40, -1, -1));

        jLabel6.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 153, 51));
        jLabel6.setText("PORTALES");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 40, -1, -1));

        jLabel7.setFont(new java.awt.Font("Monospaced", 1, 13)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 153, 51));
        jLabel7.setText("Bosque");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 150, -1, -1));

        jLabel8.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 153, 51));
        jLabel8.setText("UpsideDown");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 20, -1, -1));

        jLabel9.setFont(new java.awt.Font("Monospaced", 1, 13)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 153, 51));
        jLabel9.setText("Laboratorio");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 350, -1, -1));

        jLabel10.setFont(new java.awt.Font("Monospaced", 1, 13)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 153, 51));
        jLabel10.setText("Centro Comercial");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 50, -1, -1));

        jLabel11.setFont(new java.awt.Font("Monospaced", 1, 13)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 153, 51));
        jLabel11.setText("Alcantarillado");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 250, -1, -1));

        jScrollPane2.setBorder(null);
        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        jScrollPane2.setOpaque(false);

        txtColmenaLista.setEditable(false);
        txtColmenaLista.setBackground(new java.awt.Color(5, 15, 5));
        txtColmenaLista.setColumns(20);
        txtColmenaLista.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        txtColmenaLista.setForeground(new java.awt.Color(50, 255, 100));
        txtColmenaLista.setLineWrap(true);
        txtColmenaLista.setRows(5);
        txtColmenaLista.setWrapStyleWord(true);
        jScrollPane2.setViewportView(txtColmenaLista);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 80, 97, 100));

        jScrollPane1.setBorder(null);
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        jScrollPane1.setOpaque(false);

        txtCallePrincipal.setEditable(false);
        txtCallePrincipal.setBackground(new java.awt.Color(5, 15, 5));
        txtCallePrincipal.setColumns(20);
        txtCallePrincipal.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        txtCallePrincipal.setForeground(new java.awt.Color(50, 255, 100));
        txtCallePrincipal.setLineWrap(true);
        txtCallePrincipal.setRows(5);
        txtCallePrincipal.setWrapStyleWord(true);
        jScrollPane1.setViewportView(txtCallePrincipal);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(37, 72, 100, 100));

        jScrollPane3.setBorder(null);
        jScrollPane3.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane3.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        txtSotano.setEditable(false);
        txtSotano.setBackground(new java.awt.Color(5, 15, 5));
        txtSotano.setColumns(20);
        txtSotano.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        txtSotano.setForeground(new java.awt.Color(50, 255, 100));
        txtSotano.setLineWrap(true);
        txtSotano.setRows(5);
        txtSotano.setWrapStyleWord(true);
        jScrollPane3.setViewportView(txtSotano);

        getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(37, 232, 100, 100));

        jScrollPane4.setBorder(null);
        jScrollPane4.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane4.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        jScrollPane4.setOpaque(false);

        txtRadio.setEditable(false);
        txtRadio.setBackground(new java.awt.Color(5, 15, 5));
        txtRadio.setColumns(20);
        txtRadio.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        txtRadio.setForeground(new java.awt.Color(50, 255, 100));
        txtRadio.setLineWrap(true);
        txtRadio.setRows(5);
        txtRadio.setWrapStyleWord(true);
        jScrollPane4.setViewportView(txtRadio);

        getContentPane().add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(37, 381, 100, 100));

        jScrollPane5.setBorder(null);
        jScrollPane5.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane5.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        jScrollPane5.setOpaque(false);

        txtPortal1.setEditable(false);
        txtPortal1.setBackground(new java.awt.Color(5, 15, 5));
        txtPortal1.setColumns(20);
        txtPortal1.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        txtPortal1.setForeground(new java.awt.Color(50, 255, 100));
        txtPortal1.setLineWrap(true);
        txtPortal1.setRows(5);
        txtPortal1.setWrapStyleWord(true);
        jScrollPane5.setViewportView(txtPortal1);

        getContentPane().add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 80, 90, 60));

        jScrollPane6.setBorder(null);
        jScrollPane6.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane6.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        jScrollPane6.setOpaque(false);

        txtBosque.setEditable(false);
        txtBosque.setBackground(new java.awt.Color(5, 15, 5));
        txtBosque.setColumns(20);
        txtBosque.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        txtBosque.setForeground(new java.awt.Color(50, 255, 100));
        txtBosque.setLineWrap(true);
        txtBosque.setRows(5);
        txtBosque.setWrapStyleWord(true);
        jScrollPane6.setViewportView(txtBosque);

        getContentPane().add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 180, 90, 60));

        jScrollPane7.setBorder(null);
        jScrollPane7.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane7.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        jScrollPane7.setOpaque(false);

        txtLaboratorio.setEditable(false);
        txtLaboratorio.setBackground(new java.awt.Color(5, 15, 5));
        txtLaboratorio.setColumns(20);
        txtLaboratorio.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        txtLaboratorio.setForeground(new java.awt.Color(50, 255, 100));
        txtLaboratorio.setLineWrap(true);
        txtLaboratorio.setRows(5);
        txtLaboratorio.setWrapStyleWord(true);
        jScrollPane7.setViewportView(txtLaboratorio);

        getContentPane().add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 380, 89, 60));

        jScrollPane8.setBorder(null);
        jScrollPane8.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane8.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        jScrollPane8.setOpaque(false);

        txtCentroComercial.setEditable(false);
        txtCentroComercial.setBackground(new java.awt.Color(5, 15, 5));
        txtCentroComercial.setColumns(20);
        txtCentroComercial.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        txtCentroComercial.setForeground(new java.awt.Color(50, 255, 100));
        txtCentroComercial.setLineWrap(true);
        txtCentroComercial.setRows(5);
        txtCentroComercial.setWrapStyleWord(true);
        jScrollPane8.setViewportView(txtCentroComercial);

        getContentPane().add(jScrollPane8, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 80, 90, 60));

        jScrollPane9.setBorder(null);
        jScrollPane9.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane9.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        jScrollPane9.setOpaque(false);

        txtPortal2.setEditable(false);
        txtPortal2.setBackground(new java.awt.Color(5, 15, 5));
        txtPortal2.setColumns(20);
        txtPortal2.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        txtPortal2.setForeground(new java.awt.Color(50, 255, 100));
        txtPortal2.setLineWrap(true);
        txtPortal2.setRows(5);
        txtPortal2.setWrapStyleWord(true);
        jScrollPane9.setViewportView(txtPortal2);

        getContentPane().add(jScrollPane9, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 180, 90, 60));

        jScrollPane10.setBorder(null);
        jScrollPane10.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane10.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        jScrollPane10.setOpaque(false);

        txtPortal3.setEditable(false);
        txtPortal3.setBackground(new java.awt.Color(5, 15, 5));
        txtPortal3.setColumns(20);
        txtPortal3.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        txtPortal3.setForeground(new java.awt.Color(50, 255, 100));
        txtPortal3.setLineWrap(true);
        txtPortal3.setRows(5);
        txtPortal3.setWrapStyleWord(true);
        jScrollPane10.setViewportView(txtPortal3);

        getContentPane().add(jScrollPane10, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 280, 90, 60));

        jScrollPane11.setBorder(null);
        jScrollPane11.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane11.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        jScrollPane11.setOpaque(false);

        txtAlcantarillado.setEditable(false);
        txtAlcantarillado.setBackground(new java.awt.Color(5, 15, 5));
        txtAlcantarillado.setColumns(20);
        txtAlcantarillado.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        txtAlcantarillado.setForeground(new java.awt.Color(50, 255, 100));
        txtAlcantarillado.setLineWrap(true);
        txtAlcantarillado.setRows(5);
        txtAlcantarillado.setWrapStyleWord(true);
        jScrollPane11.setViewportView(txtAlcantarillado);

        getContentPane().add(jScrollPane11, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 280, 90, 60));

        jScrollPane12.setBorder(null);
        jScrollPane12.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane12.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        jScrollPane12.setOpaque(false);

        txtPortal4.setEditable(false);
        txtPortal4.setBackground(new java.awt.Color(5, 15, 5));
        txtPortal4.setColumns(20);
        txtPortal4.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        txtPortal4.setForeground(new java.awt.Color(50, 255, 100));
        txtPortal4.setLineWrap(true);
        txtPortal4.setRows(5);
        txtPortal4.setWrapStyleWord(true);
        jScrollPane12.setViewportView(txtPortal4);

        getContentPane().add(jScrollPane12, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 380, 90, 60));

        jScrollPane21.setBorder(null);
        jScrollPane21.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane21.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        jScrollPane21.setOpaque(false);

        txtVuelta1.setEditable(false);
        txtVuelta1.setBackground(new java.awt.Color(5, 15, 5));
        txtVuelta1.setColumns(20);
        txtVuelta1.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        txtVuelta1.setForeground(new java.awt.Color(50, 255, 100));
        txtVuelta1.setLineWrap(true);
        txtVuelta1.setRows(5);
        txtVuelta1.setWrapStyleWord(true);
        jScrollPane21.setViewportView(txtVuelta1);

        getContentPane().add(jScrollPane21, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 80, 90, 60));

        jScrollPane22.setBorder(null);
        jScrollPane22.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane22.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        jScrollPane22.setOpaque(false);

        txtVuelta2.setEditable(false);
        txtVuelta2.setBackground(new java.awt.Color(5, 15, 5));
        txtVuelta2.setColumns(20);
        txtVuelta2.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        txtVuelta2.setForeground(new java.awt.Color(50, 255, 100));
        txtVuelta2.setLineWrap(true);
        txtVuelta2.setRows(5);
        txtVuelta2.setWrapStyleWord(true);
        jScrollPane22.setViewportView(txtVuelta2);

        getContentPane().add(jScrollPane22, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 180, 90, 60));

        jScrollPane23.setBorder(null);
        jScrollPane23.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane23.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        jScrollPane23.setOpaque(false);

        txtVuelta3.setEditable(false);
        txtVuelta3.setBackground(new java.awt.Color(5, 15, 5));
        txtVuelta3.setColumns(20);
        txtVuelta3.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        txtVuelta3.setForeground(new java.awt.Color(50, 255, 100));
        txtVuelta3.setLineWrap(true);
        txtVuelta3.setRows(5);
        txtVuelta3.setWrapStyleWord(true);
        jScrollPane23.setViewportView(txtVuelta3);

        getContentPane().add(jScrollPane23, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 280, 90, 60));

        jScrollPane24.setBorder(null);
        jScrollPane24.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane24.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        jScrollPane24.setOpaque(false);

        txtVuelta4.setEditable(false);
        txtVuelta4.setBackground(new java.awt.Color(5, 15, 5));
        txtVuelta4.setColumns(20);
        txtVuelta4.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        txtVuelta4.setForeground(new java.awt.Color(50, 255, 100));
        txtVuelta4.setLineWrap(true);
        txtVuelta4.setRows(5);
        txtVuelta4.setWrapStyleWord(true);
        jScrollPane24.setViewportView(txtVuelta4);

        getContentPane().add(jScrollPane24, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 380, 90, 60));

        jScrollPane13.setBorder(null);
        jScrollPane13.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane13.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        jScrollPane13.setOpaque(false);

        txtBosqueDemo.setEditable(false);
        txtBosqueDemo.setBackground(new java.awt.Color(5, 15, 5));
        txtBosqueDemo.setColumns(20);
        txtBosqueDemo.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        txtBosqueDemo.setForeground(new java.awt.Color(50, 255, 100));
        txtBosqueDemo.setLineWrap(true);
        txtBosqueDemo.setRows(5);
        txtBosqueDemo.setWrapStyleWord(true);
        jScrollPane13.setViewportView(txtBosqueDemo);

        getContentPane().add(jScrollPane13, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 180, 90, 60));

        jScrollPane14.setBorder(null);
        jScrollPane14.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane14.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        jScrollPane14.setOpaque(false);

        txtLaboratorioDemo.setEditable(false);
        txtLaboratorioDemo.setBackground(new java.awt.Color(5, 15, 5));
        txtLaboratorioDemo.setColumns(20);
        txtLaboratorioDemo.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        txtLaboratorioDemo.setForeground(new java.awt.Color(50, 255, 100));
        txtLaboratorioDemo.setLineWrap(true);
        txtLaboratorioDemo.setRows(5);
        txtLaboratorioDemo.setWrapStyleWord(true);
        jScrollPane14.setViewportView(txtLaboratorioDemo);

        getContentPane().add(jScrollPane14, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 380, 89, 60));

        jScrollPane15.setBorder(null);
        jScrollPane15.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane15.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        jScrollPane15.setOpaque(false);

        txtCentroComercialDemo.setEditable(false);
        txtCentroComercialDemo.setBackground(new java.awt.Color(5, 15, 5));
        txtCentroComercialDemo.setColumns(20);
        txtCentroComercialDemo.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        txtCentroComercialDemo.setForeground(new java.awt.Color(50, 255, 100));
        txtCentroComercialDemo.setLineWrap(true);
        txtCentroComercialDemo.setRows(5);
        txtCentroComercialDemo.setWrapStyleWord(true);
        jScrollPane15.setViewportView(txtCentroComercialDemo);

        getContentPane().add(jScrollPane15, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 80, 90, 60));

        jScrollPane16.setBorder(null);
        jScrollPane16.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane16.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        jScrollPane16.setOpaque(false);

        txtAlcantarilladoDemo.setEditable(false);
        txtAlcantarilladoDemo.setBackground(new java.awt.Color(5, 15, 5));
        txtAlcantarilladoDemo.setColumns(20);
        txtAlcantarilladoDemo.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        txtAlcantarilladoDemo.setForeground(new java.awt.Color(50, 255, 100));
        txtAlcantarilladoDemo.setLineWrap(true);
        txtAlcantarilladoDemo.setRows(5);
        txtAlcantarilladoDemo.setWrapStyleWord(true);
        jScrollPane16.setViewportView(txtAlcantarilladoDemo);

        getContentPane().add(jScrollPane16, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 280, 90, 60));

        txtEventoActual.setEditable(false);
        txtEventoActual.setBackground(new java.awt.Color(5, 15, 5));
        txtEventoActual.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        txtEventoActual.setForeground(new java.awt.Color(50, 255, 100));
        txtEventoActual.setBorder(null);
        getContentPane().add(txtEventoActual, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 230, 290, 66));

        jLabel12.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 153, 51));
        jLabel12.setText("Evento");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 200, 70, -1));

        jScrollPane17.setBorder(null);
        jScrollPane17.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane17.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        jScrollPane17.setOpaque(false);

        txtSangre.setEditable(false);
        txtSangre.setBackground(new java.awt.Color(5, 15, 5));
        txtSangre.setColumns(20);
        txtSangre.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        txtSangre.setForeground(new java.awt.Color(255, 0, 0));
        txtSangre.setLineWrap(true);
        txtSangre.setRows(5);
        txtSangre.setWrapStyleWord(true);
        jScrollPane17.setViewportView(txtSangre);

        getContentPane().add(jScrollPane17, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 400, 50, 40));

        jLabel13.setFont(new java.awt.Font("Monospaced", 1, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 0, 0));
        jLabel13.setText("Sangre");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 380, -1, -1));

        btnReanudar.setBackground(new java.awt.Color(0, 102, 0));
        btnReanudar.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        btnReanudar.setForeground(new java.awt.Color(204, 255, 204));
        btnReanudar.setText("REANUDAR");
        btnReanudar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReanudarActionPerformed(evt);
            }
        });
        getContentPane().add(btnReanudar, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 340, 130, 60));

        btnPausa.setBackground(new java.awt.Color(0, 102, 0));
        btnPausa.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        btnPausa.setForeground(new java.awt.Color(204, 255, 204));
        btnPausa.setText("PAUSAR");
        btnPausa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPausaActionPerformed(evt);
            }
        });
        getContentPane().add(btnPausa, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 340, 130, 60));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnReanudarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReanudarActionPerformed
        // TODO add your handling code here:
        mundo.reanudar();
        btnPausa.setEnabled(true);
        btnReanudar.setEnabled(false);
    }//GEN-LAST:event_btnReanudarActionPerformed

    private void btnPausaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPausaActionPerformed
        // TODO add your handling code here:
        mundo.pausar();
        btnPausa.setEnabled(false);    // Desactivar pausa si ya está pausado
        btnReanudar.setEnabled(true);
    }//GEN-LAST:event_btnPausaActionPerformed

    /**
     * @param args the command line arguments
     */
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPausa;
    private javax.swing.JButton btnReanudar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane17;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane21;
    private javax.swing.JScrollPane jScrollPane22;
    private javax.swing.JScrollPane jScrollPane23;
    private javax.swing.JScrollPane jScrollPane24;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextArea txtAlcantarillado;
    private javax.swing.JTextArea txtAlcantarilladoDemo;
    private javax.swing.JTextArea txtBosque;
    private javax.swing.JTextArea txtBosqueDemo;
    private javax.swing.JTextArea txtCallePrincipal;
    private javax.swing.JTextArea txtCentroComercial;
    private javax.swing.JTextArea txtCentroComercialDemo;
    private javax.swing.JTextArea txtColmenaLista;
    private javax.swing.JTextField txtEventoActual;
    private javax.swing.JTextArea txtLaboratorio;
    private javax.swing.JTextArea txtLaboratorioDemo;
    private javax.swing.JTextArea txtPortal1;
    private javax.swing.JTextArea txtPortal2;
    private javax.swing.JTextArea txtPortal3;
    private javax.swing.JTextArea txtPortal4;
    private javax.swing.JTextArea txtRadio;
    private javax.swing.JTextArea txtSangre;
    private javax.swing.JTextArea txtSotano;
    private javax.swing.JTextArea txtVuelta1;
    private javax.swing.JTextArea txtVuelta2;
    private javax.swing.JTextArea txtVuelta3;
    private javax.swing.JTextArea txtVuelta4;
    // End of variables declaration//GEN-END:variables
}
