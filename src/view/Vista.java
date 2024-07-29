package view;

import java.util.Scanner;

public class Vista {

    private static Scanner scanner = new Scanner(System.in);

    public static void mostrarMenu() {
        System.out.println("\nBienvenido al sistema de venta de tickets.");
        System.out.println("1. Comprar boletos");
        System.out.println("2. Consultar disponibilidad total");
        System.out.println("3. Consultar disponibilidad individual");
        System.out.println("4. Reporte de caja");
        System.out.println("5. Salir");
        System.out.print("Seleccione una opción: ");
    }

    public static void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public static String solicitarEntrada() {
        return scanner.nextLine();
    }

    public static int solicitarEntero() {
        return scanner.nextInt();
    }

    public static double solicitarDouble() {
        return scanner.nextDouble();
    }

    public static void limpiarBuffer() {
        scanner.nextLine(); // Limpiar el buffer del scanner
    }
}
