package solucion;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        try (Socket socket = new Socket("127.0.0.1", 8765)) {
            String opcion = "";
            while (!opcion.equalsIgnoreCase("salir")) {
                Scanner lector = new Scanner(socket.getInputStream());
                PrintWriter escritor = new PrintWriter(socket.getOutputStream(), true);

                System.out.println("Introduzca la opción: ordenar / ascii / salir");
                opcion = scanner.nextLine();
                escritor.println(opcion);

                if (opcion.equalsIgnoreCase("salir")) {
                    // Salir
                    System.out.println("Saliendo...");
                } else if (opcion.equalsIgnoreCase("ascii")){
                    // Obtener suma de verificación
                    System.out.println("Introduzca la cadena a calcular la suma de verificación:");
                    String cadena = scanner.nextLine();
                    escritor.println(cadena);

                    System.out.println("Suma de verificación: " + lector.nextInt());
                } else {
                    // Ordenar cadenas
                    System.out.println("Introduzca la primera cadena:");
                    String cadena = scanner.nextLine();
                    escritor.println(cadena);
                    System.out.println("Introduzca la segunda cadena:");
                    cadena = scanner.nextLine();
                    escritor.println(cadena);

                    System.out.println("Cadenas ordenadas: " + lector.nextLine());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
