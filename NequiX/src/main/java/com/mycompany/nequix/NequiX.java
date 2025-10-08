package com.mycompany.nequix;

import java.util.ArrayList;
import java.util.Scanner;

public class NequiX {

    public static void main(String[] args) {
        ArrayList<Banco> bancos = new ArrayList<>();
        bancos.add(new Banco("Bancolombia", 0.19));
        bancos.add(new Banco("Banco de Occidente", 0.15));
        bancos.add(new Banco("Banco Popular", 0.12));

        ArrayList<Double> depositos = new ArrayList<>();
        for (int i = 0; i < bancos.size(); i++) depositos.add(0.0);

        ArrayList<String> operaciones = new ArrayList<>();

        Scanner sc = new Scanner(System.in);

        System.out.print("Ingrese su nombre: ");
        String nombre = sc.nextLine();

        System.out.print("Ingrese su edad: ");
        int edad = sc.nextInt();

        System.out.print("Ingrese dinero inicial: ");
        double dinero = sc.nextDouble();

        User usuario = new User(nombre, edad, dinero);

        int opcion;
        do {
            System.out.println("\n=== WELCOME TO NEQUIX ===");
            System.out.println("1. Depositar dinero");
            System.out.println("2. Retirar dinero");
            System.out.println("3. Ver saldos por banco");
            System.out.println("4. Salir y generar reporte");
            System.out.print("Elige una opción: ");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1 -> {
                    int indiceBanco = elegirBanco(bancos, sc);
                    Banco banco = bancos.get(indiceBanco);
                    System.out.print("Monto a depositar: ");
                    double monto = sc.nextDouble();
                    usuario.depositar(monto, banco, operaciones, depositos, indiceBanco);
                }
                case 2 -> {
                    int indiceBanco = elegirBanco(bancos, sc);
                    Banco banco = bancos.get(indiceBanco);
                    System.out.print("Monto a retirar: ");
                    double monto = sc.nextDouble();
                    usuario.retirar(monto, banco, operaciones, depositos, indiceBanco);
                }
                case 3 -> {
                    System.out.println("\n--- Saldos por banco ---");
                    for (int i = 0; i < bancos.size(); i++) {
                        System.out.println(bancos.get(i).getNombre() + ": $" + depositos.get(i));
                    }
                }
                case 4 -> {
                    System.out.println("Generando reporte...");
                    // 👉 Aquí se conecta tu clase PDF sin modificarla
                    GenerarPDF.generarReporte(usuario.getNombre(), operaciones, usuario.getSaldo());
                    System.out.println("Gracias por usar NequiX. ¡SEE YA LATER!");
                }
                default -> System.out.println("Opción no válida.");
            }

        } while (opcion != 4);
    }

    private static int elegirBanco(ArrayList<Banco> bancos, Scanner sc) {
        System.out.println("\nElige un banco:");
        for (int i = 0; i < bancos.size(); i++) {
            System.out.println((i + 1) + ". " + bancos.get(i).getNombre() + " (IVA: " + (bancos.get(i).getIva() * 100) + "%)");
        }
        int eleccion = sc.nextInt();
        return eleccion - 1;
    }
}
