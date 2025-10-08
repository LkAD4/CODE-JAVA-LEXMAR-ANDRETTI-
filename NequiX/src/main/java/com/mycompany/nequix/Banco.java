package com.mycompany.nequix;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Ivam
 */
// Clase Banco
class Banco {
    private String nombre;
    private double iva;

    public Banco(String nombre, double iva) {
        this.nombre = nombre;
        this.iva = iva;
    }

    public String getNombre() {
        return nombre;
    }

    public double getIva() {
        return iva;
    }
}
