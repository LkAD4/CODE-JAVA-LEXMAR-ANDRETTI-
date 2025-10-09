package com.mycompany.nequix;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.util.ArrayList;

public class User extends Persona {
    private double saldo;

    public User(String nombre, int edad, double dinero) {
        super(nombre, edad, dinero);
        if (edad < 18) {
            throw new IllegalArgumentException("Debes ser mayor de edad para crear una cuenta Nequi.");
        }
        this.saldo = dinero;
    }

    public double getSaldo() {
        return saldo;
    }

    public String getNombre() {
        return nombre;
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
