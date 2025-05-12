package main.java.com.granja.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class EntradaSegura {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static int leerEnteroSeguro(Scanner sc, String mensaje) {
        int numero;
        while (true) {
            System.out.print(mensaje);
            if (sc.hasNextInt()) {
                numero = sc.nextInt();
                sc.nextLine(); // limpiar buffer
                return numero;
            } else {
                System.out.println("Entrada inválida. Por favor, introduce un número entero.");
                sc.nextLine(); // descartar entrada incorrecta
            }
        }
    }

    public static LocalDate leerFechaSegura(Scanner sc, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String entrada = sc.nextLine();
            try {
                return LocalDate.parse(entrada, DATE_FORMATTER);
            } catch (DateTimeParseException e) {
                System.out.println("Formato de fecha inválido. Usa el formato exacto: dd/MM/yyyy (ej. 01/06/2005)");
            }
        }
    }

    public static String leerNombreSeguro(Scanner sc, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String nombre = sc.nextLine().trim();
            if (nombre.matches("[A-Za-zÁÉÍÓÚáéíóúñÑ ]{3,50}")) {
                return nombre;
            } else {
                System.out.println("Nombre inválido. Usa solo letras y espacios. Mínimo 3 letras.");
            }
        }
    }

    public static String leerCadena(Scanner sc, String mensaje) {
        System.out.print(mensaje);
        return sc.nextLine().trim();
    }

    public static String leerCadenaOpcional(Scanner sc, String mensaje, String valorActual) {
        System.out.print(mensaje + " [" + valorActual + "]: ");
        String entrada = sc.nextLine().trim();
        return entrada.isEmpty() ? valorActual : entrada;
    }

    public static int leerEnteroOpcional(Scanner sc, String mensaje, int valorActual) {
        while (true) {
            System.out.print(mensaje + " [" + valorActual + "]: ");
            String entrada = sc.nextLine().trim();
            if (entrada.isEmpty()) {
                return valorActual;
            }
            try {
                return Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, introduce un número entero.");
            }
        }
    }

}
