package com.mycompany.nequix;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.util.ArrayList;

public class User extends Persona {
    private double saldo;
    private final Historial historial;
    // agregación: el Id_User se crea fuera y se pasa al User
    private final Id_User idUser;

    public User(String nombre, int edad, double dinero) {
        super(nombre, edad, dinero);
        if (edad < 18) {
            throw new IllegalArgumentException("Debes ser mayor de edad para crear una cuenta Nequi.");
        }
        this.saldo = dinero;//este saldo es viene del atributo de dinero
        this.historial = new Historial(this); // composición: User crea y posee su Historial
        // Por compatibilidad, si no se proporciona Id_User, lo creamos aquí a partir del nombre y la edad
        this.idUser = new Id_User(nombre, edad);
    }

    /**
     * Constructor alternativo que recibe una credencial externa (agregación).
     */
    public User(String nombre, int edad, double dinero, Id_User idUser) {
        super(nombre, edad, dinero);
        if (edad < 18) {
            throw new IllegalArgumentException("Debes ser mayor de edad para crear una cuenta Nequi.");
        }
        this.saldo = dinero;
        this.historial = new Historial(this);
        this.idUser = idUser == null ? new Id_User(nombre, edad) : idUser;
    }

    public double getSaldo() {
        return saldo;
    }

    public String getNombre() {
        return nombre;
    }

    public Historial getHistorial() {
        return historial;
    }

    public Id_User getIdUser() {
        return idUser;
    }

    public void depositar(double monto, Banco banco, ArrayList<String> operaciones, ArrayList<Double> depositos, int indiceBanco) {
        double iva = banco.getIva();
        double montoFinal = monto - (monto * iva);

        saldo += montoFinal;
        double saldoBanco = depositos.get(indiceBanco) + montoFinal;
        depositos.set(indiceBanco, saldoBanco);

        String operacion = "Depósito en " + banco.getNombre() + ": +" + montoFinal +
                           " (IVA aplicado: " + (iva * 100) + "%)";
        operaciones.add(operacion);

        System.out.println(" Depósito exitoso en " + banco.getNombre() + ". Saldo en ese banco: $" + saldoBanco);
    }

    public void retirar(double monto, Banco banco, ArrayList<String> operaciones, ArrayList<Double> depositos, int indiceBanco) {
        double iva = banco.getIva();
        double montoConIva = monto + (monto * iva);

        double saldoBanco = depositos.get(indiceBanco);

        if (montoConIva > saldoBanco) {
            System.out.println(" Fondos insuficientes en " + banco.getNombre());
            return;
        }

        saldo -= montoConIva;
        saldoBanco -= montoConIva;
        depositos.set(indiceBanco, saldoBanco);

        String operacion = "Retiro en " + banco.getNombre() + ": -" + monto +
                           " (IVA cobrado: " + (iva * 100) + "%)";
        operaciones.add(operacion);

        System.out.println(" Retiro exitoso en " + banco.getNombre() + ". Saldo restante: $" + saldoBanco);
    }
}
