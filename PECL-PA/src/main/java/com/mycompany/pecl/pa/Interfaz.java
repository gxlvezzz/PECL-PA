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
        String calleP = generarTexto(mundo.getNiñosCallePrincipal());
        String colmena = generarTexto(mundo.getNiñosColmena());
        String sotano = generarTexto(mundo.getNiñosSotanoByers());
        String radio = generarTexto(mundo.getNiñosRadioWSQK());

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

        jTextField1.setText("jTextField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("HAWKINS");

        jLabel2.setText("Calle Principal");

        jLabel3.setText("Sótano Byers");

        jLabel4.setText("Radio WSQK");

        jLabel5.setText("Niños En Colmena");

        jLabel6.setText("PORTALES");

        jLabel7.setText("Bosque");

        jLabel8.setText("UpsideDown");

        jLabel9.setText("Laboratorio");

        jLabel10.setText("Centro Comercial");

        jLabel11.setText("Alcantarillado");

        txtColmenaLista.setEditable(false);
        txtColmenaLista.setBackground(new java.awt.Color(5, 15, 5));
        txtColmenaLista.setColumns(20);
        txtColmenaLista.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        txtColmenaLista.setForeground(new java.awt.Color(50, 255, 100));
        txtColmenaLista.setLineWrap(true);
        txtColmenaLista.setRows(5);
        txtColmenaLista.setWrapStyleWord(true);
        jScrollPane2.setViewportView(txtColmenaLista);

        txtCallePrincipal.setEditable(false);
        txtCallePrincipal.setBackground(new java.awt.Color(5, 15, 5));
        txtCallePrincipal.setColumns(20);
        txtCallePrincipal.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        txtCallePrincipal.setForeground(new java.awt.Color(50, 255, 100));
        txtCallePrincipal.setLineWrap(true);
        txtCallePrincipal.setRows(5);
        txtCallePrincipal.setWrapStyleWord(true);
        jScrollPane1.setViewportView(txtCallePrincipal);

        txtSotano.setEditable(false);
        txtSotano.setBackground(new java.awt.Color(5, 15, 5));
        txtSotano.setColumns(20);
        txtSotano.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        txtSotano.setForeground(new java.awt.Color(50, 255, 100));
        txtSotano.setLineWrap(true);
        txtSotano.setRows(5);
        txtSotano.setWrapStyleWord(true);
        jScrollPane3.setViewportView(txtSotano);

        txtRadio.setEditable(false);
        txtRadio.setBackground(new java.awt.Color(5, 15, 5));
        txtRadio.setColumns(20);
        txtRadio.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        txtRadio.setForeground(new java.awt.Color(50, 255, 100));
        txtRadio.setLineWrap(true);
        txtRadio.setRows(5);
        txtRadio.setWrapStyleWord(true);
        jScrollPane4.setViewportView(txtRadio);

        txtPortal1.setEditable(false);
        txtPortal1.setBackground(new java.awt.Color(5, 15, 5));
        txtPortal1.setColumns(20);
        txtPortal1.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        txtPortal1.setForeground(new java.awt.Color(50, 255, 100));
        txtPortal1.setLineWrap(true);
        txtPortal1.setRows(5);
        txtPortal1.setWrapStyleWord(true);
        jScrollPane5.setViewportView(txtPortal1);

        txtBosque.setEditable(false);
        txtBosque.setBackground(new java.awt.Color(5, 15, 5));
        txtBosque.setColumns(20);
        txtBosque.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        txtBosque.setForeground(new java.awt.Color(50, 255, 100));
        txtBosque.setLineWrap(true);
        txtBosque.setRows(5);
        txtBosque.setWrapStyleWord(true);
        jScrollPane6.setViewportView(txtBosque);

        txtLaboratorio.setEditable(false);
        txtLaboratorio.setBackground(new java.awt.Color(5, 15, 5));
        txtLaboratorio.setColumns(20);
        txtLaboratorio.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        txtLaboratorio.setForeground(new java.awt.Color(50, 255, 100));
        txtLaboratorio.setLineWrap(true);
        txtLaboratorio.setRows(5);
        txtLaboratorio.setWrapStyleWord(true);
        jScrollPane7.setViewportView(txtLaboratorio);

        txtCentroComercial.setEditable(false);
        txtCentroComercial.setBackground(new java.awt.Color(5, 15, 5));
        txtCentroComercial.setColumns(20);
        txtCentroComercial.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        txtCentroComercial.setForeground(new java.awt.Color(50, 255, 100));
        txtCentroComercial.setLineWrap(true);
        txtCentroComercial.setRows(5);
        txtCentroComercial.setWrapStyleWord(true);
        jScrollPane8.setViewportView(txtCentroComercial);

        txtPortal2.setEditable(false);
        txtPortal2.setBackground(new java.awt.Color(5, 15, 5));
        txtPortal2.setColumns(20);
        txtPortal2.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        txtPortal2.setForeground(new java.awt.Color(50, 255, 100));
        txtPortal2.setLineWrap(true);
        txtPortal2.setRows(5);
        txtPortal2.setWrapStyleWord(true);
        jScrollPane9.setViewportView(txtPortal2);

        txtPortal3.setEditable(false);
        txtPortal3.setBackground(new java.awt.Color(5, 15, 5));
        txtPortal3.setColumns(20);
        txtPortal3.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        txtPortal3.setForeground(new java.awt.Color(50, 255, 100));
        txtPortal3.setLineWrap(true);
        txtPortal3.setRows(5);
        txtPortal3.setWrapStyleWord(true);
        jScrollPane10.setViewportView(txtPortal3);

        txtAlcantarillado.setEditable(false);
        txtAlcantarillado.setBackground(new java.awt.Color(5, 15, 5));
        txtAlcantarillado.setColumns(20);
        txtAlcantarillado.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        txtAlcantarillado.setForeground(new java.awt.Color(50, 255, 100));
        txtAlcantarillado.setLineWrap(true);
        txtAlcantarillado.setRows(5);
        txtAlcantarillado.setWrapStyleWord(true);
        jScrollPane11.setViewportView(txtAlcantarillado);

        txtPortal4.setEditable(false);
        txtPortal4.setBackground(new java.awt.Color(5, 15, 5));
        txtPortal4.setColumns(20);
        txtPortal4.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        txtPortal4.setForeground(new java.awt.Color(50, 255, 100));
        txtPortal4.setLineWrap(true);
        txtPortal4.setRows(5);
        txtPortal4.setWrapStyleWord(true);
        jScrollPane12.setViewportView(txtPortal4);

        txtVuelta1.setEditable(false);
        txtVuelta1.setBackground(new java.awt.Color(5, 15, 5));
        txtVuelta1.setColumns(20);
        txtVuelta1.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        txtVuelta1.setForeground(new java.awt.Color(50, 255, 100));
        txtVuelta1.setLineWrap(true);
        txtVuelta1.setRows(5);
        txtVuelta1.setWrapStyleWord(true);
        jScrollPane21.setViewportView(txtVuelta1);

        txtVuelta2.setEditable(false);
        txtVuelta2.setBackground(new java.awt.Color(5, 15, 5));
        txtVuelta2.setColumns(20);
        txtVuelta2.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        txtVuelta2.setForeground(new java.awt.Color(50, 255, 100));
        txtVuelta2.setLineWrap(true);
        txtVuelta2.setRows(5);
        txtVuelta2.setWrapStyleWord(true);
        jScrollPane22.setViewportView(txtVuelta2);

        txtVuelta3.setEditable(false);
        txtVuelta3.setBackground(new java.awt.Color(5, 15, 5));
        txtVuelta3.setColumns(20);
        txtVuelta3.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        txtVuelta3.setForeground(new java.awt.Color(50, 255, 100));
        txtVuelta3.setLineWrap(true);
        txtVuelta3.setRows(5);
        txtVuelta3.setWrapStyleWord(true);
        jScrollPane23.setViewportView(txtVuelta3);

        txtVuelta4.setEditable(false);
        txtVuelta4.setBackground(new java.awt.Color(5, 15, 5));
        txtVuelta4.setColumns(20);
        txtVuelta4.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        txtVuelta4.setForeground(new java.awt.Color(50, 255, 100));
        txtVuelta4.setLineWrap(true);
        txtVuelta4.setRows(5);
        txtVuelta4.setWrapStyleWord(true);
        jScrollPane24.setViewportView(txtVuelta4);

        txtBosqueDemo.setEditable(false);
        txtBosqueDemo.setBackground(new java.awt.Color(5, 15, 5));
        txtBosqueDemo.setColumns(20);
        txtBosqueDemo.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        txtBosqueDemo.setForeground(new java.awt.Color(50, 255, 100));
        txtBosqueDemo.setLineWrap(true);
        txtBosqueDemo.setRows(5);
        txtBosqueDemo.setWrapStyleWord(true);
        jScrollPane13.setViewportView(txtBosqueDemo);

        txtLaboratorioDemo.setEditable(false);
        txtLaboratorioDemo.setBackground(new java.awt.Color(5, 15, 5));
        txtLaboratorioDemo.setColumns(20);
        txtLaboratorioDemo.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        txtLaboratorioDemo.setForeground(new java.awt.Color(50, 255, 100));
        txtLaboratorioDemo.setLineWrap(true);
        txtLaboratorioDemo.setRows(5);
        txtLaboratorioDemo.setWrapStyleWord(true);
        jScrollPane14.setViewportView(txtLaboratorioDemo);

        txtCentroComercialDemo.setEditable(false);
        txtCentroComercialDemo.setBackground(new java.awt.Color(5, 15, 5));
        txtCentroComercialDemo.setColumns(20);
        txtCentroComercialDemo.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        txtCentroComercialDemo.setForeground(new java.awt.Color(50, 255, 100));
        txtCentroComercialDemo.setLineWrap(true);
        txtCentroComercialDemo.setRows(5);
        txtCentroComercialDemo.setWrapStyleWord(true);
        jScrollPane15.setViewportView(txtCentroComercialDemo);

        txtAlcantarilladoDemo.setEditable(false);
        txtAlcantarilladoDemo.setBackground(new java.awt.Color(5, 15, 5));
        txtAlcantarilladoDemo.setColumns(20);
        txtAlcantarilladoDemo.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        txtAlcantarilladoDemo.setForeground(new java.awt.Color(50, 255, 100));
        txtAlcantarilladoDemo.setLineWrap(true);
        txtAlcantarilladoDemo.setRows(5);
        txtAlcantarilladoDemo.setWrapStyleWord(true);
        jScrollPane16.setViewportView(txtAlcantarilladoDemo);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel4))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(123, 123, 123)
                                        .addComponent(jLabel6))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(63, 63, 63)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
                                                .addComponent(jScrollPane9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(jScrollPane10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE)
                                                .addComponent(jScrollPane12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jScrollPane22, javax.swing.GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE)
                                            .addComponent(jScrollPane21, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                            .addComponent(jScrollPane23, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                            .addComponent(jScrollPane24, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))
                                .addGap(103, 103, 103)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                        .addGroup(layout.createSequentialGroup()
                                                            .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                            .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(layout.createSequentialGroup()
                                                            .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addGap(18, 18, 18)
                                                            .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                                .addGap(104, 104, 104))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel9)
                                                .addGap(170, 170, 170)))
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel7)
                                            .addComponent(jLabel8))
                                        .addGap(184, 184, 184)
                                        .addComponent(jLabel5))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(408, 408, 408)
                                .addComponent(jLabel11))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(394, 394, 394)
                                .addComponent(jLabel10))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(246, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(28, 28, 28)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(39, 39, 39)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(305, 305, 305))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(126, 126, 126)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane23, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(61, 61, 61)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
                                    .addComponent(jScrollPane24, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(21, 21, 21)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(271, 284, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel8)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jScrollPane21, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jScrollPane22, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jScrollPane14, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
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
    private javax.swing.JTextArea txtLaboratorio;
    private javax.swing.JTextArea txtLaboratorioDemo;
    private javax.swing.JTextArea txtPortal1;
    private javax.swing.JTextArea txtPortal2;
    private javax.swing.JTextArea txtPortal3;
    private javax.swing.JTextArea txtPortal4;
    private javax.swing.JTextArea txtRadio;
    private javax.swing.JTextArea txtSotano;
    private javax.swing.JTextArea txtVuelta1;
    private javax.swing.JTextArea txtVuelta2;
    private javax.swing.JTextArea txtVuelta3;
    private javax.swing.JTextArea txtVuelta4;
    // End of variables declaration//GEN-END:variables
}
