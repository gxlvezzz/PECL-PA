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

    private static final DateTimeFormatter formato =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public synchronized static void escribir(String mensaje) {

        String tiempo = LocalDateTime.now().format(formato);

        try (PrintWriter pw = new PrintWriter(new FileWriter(ARCHIVO, true))) {

            pw.println(tiempo + " - " + mensaje);

        } catch (IOException e) {
            System.out.println("Error escribiendo log");
        }
    }
}
