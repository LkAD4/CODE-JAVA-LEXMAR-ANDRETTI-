/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.nequix;

import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class GenerarPDF {

    public static void generarReporte(String usuario, List<String> operaciones, double saldoFinal) {
        try {
            //  nombre  del archivo
            String fechaHora = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
            String nombreArchivo = "Reporte_" + usuario + "_" + fechaHora + ".pdf";

            // Crear el PDF
            PdfWriter writer = new PdfWriter(new File(nombreArchivo));
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            // Encabezado
            document.add(new Paragraph("TRANSACCIONES\n\n"));
            document.add(new Paragraph("Cliente: " + usuario));
            document.add(new Paragraph("Fecha de generación: " +
                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"))));
            document.add(new Paragraph("\n--- Operaciones realizadas ---\n"));

            // Listar operaciones
            for (String op : operaciones) {
                document.add(new Paragraph(op));
            }

            // Saldo final
            document.add(new Paragraph("\nSaldo final en cuenta: $" + saldoFinal));
            document.add(new Paragraph("\nGracias por usar el NEQUIX BY LEX"));

            // Cerrar documento
            document.close();

            System.out.println("Reporte consolidado generado: " + nombreArchivo);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sobrecarga conveniente: recibe el objeto User y usa su Id_User (agregación) al generar el PDF.
     */
    public static void generarReporte(User user, List<String> operaciones, double saldoFinal) {
        String usuario = user.getNombre();
        String id = user.getIdUser() != null ? user.getIdUser().getId() : "N/A";
        try {
            String fechaHora = java.time.LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
            String nombreArchivo = "Reporte_" + usuario + "_" + id + "_" + fechaHora + ".pdf";

            PdfWriter writer = new PdfWriter(new File(nombreArchivo));
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            document.add(new Paragraph("TRANSACCIONES\n\n"));
            document.add(new Paragraph("Cliente: " + usuario));
            document.add(new Paragraph("ID: " + id));
            document.add(new Paragraph("Fecha de generación: " +
                    java.time.LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"))));
            document.add(new Paragraph("\n--- Operaciones realizadas ---\n"));

            for (String op : operaciones) {
                document.add(new Paragraph(op));
            }

            document.add(new Paragraph("\nSaldo final en cuenta: $" + saldoFinal));
            document.add(new Paragraph("\nGracias por usar el NEQUIX BY LEX"));

            document.close();

            System.out.println("Reporte consolidado generado: " + nombreArchivo);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
