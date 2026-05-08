/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pecl.pa;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
/**
 *
 * @author adrii
 */
public class LogHawkins {

    private static final String ARCHIVO = "hawkins.txt";

    private static final DateTimeFormatter FORMATO =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private LogHawkins() {
        // Clase de utilidad: no se instancia.
    }

    /**
     * Recurso compartido protegido.
     * Todos los hilos escriben en el mismo fichero, por eso el método es synchronized.
     */
    public static synchronized void escribir(String mensaje) {
        String tiempo = LocalDateTime.now().format(FORMATO);

        try (PrintWriter pw = new PrintWriter(new FileWriter(ARCHIVO, true))) {
            pw.println(tiempo + " - " + mensaje);

        } catch (IOException e) {
            System.err.println("Error escribiendo log: " + e.getMessage());
        }
    }
}