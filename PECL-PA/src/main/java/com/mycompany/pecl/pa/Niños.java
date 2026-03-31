/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pecl.pa;

/**
 *
 * @author julia_ntxs1ki
 */
public class Niños extends Thread{
    private Mundo mundo;
    public String id;
    
    
    public Niños(Mundo mundo, int numid){
        this.mundo = mundo;
        this.id = String.format("N%04d", numid);
    }
    
    public void run(){
        System.out.println(this.id);
    }
}
