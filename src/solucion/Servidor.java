package solucion;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Servidor {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(8765)){
            System.out.println("Servidor iniciado");
            Socket socketCliente = serverSocket.accept();
            System.out.println("Ciente conectado");
            while (true) {
                Scanner lector = new Scanner(socketCliente.getInputStream());
                PrintWriter escritor = new PrintWriter(socketCliente.getOutputStream(), true);
                String opcion = lector.nextLine();


                // Ejecución según la opción
                if (opcion.equalsIgnoreCase("ascii")) {
                    System.out.println("La opción recibida es: " + opcion);
                    String cadena = lector.nextLine();
                    System.out.println("Cadena recibida: " + cadena);
                    int resultado = sumaAsciiCadena(cadena);
                    escritor.println(resultado);
                    System.out.println("Suma de verificación que se envia al cliente: " + resultado);
                } else if (opcion.equals("ordenar")) {
                    System.out.println("La opción recibida es: " + opcion);
                    // Obtener cadenas
                    String cadena1 = lector.nextLine();
                    System.out.println("Cadena recibida: " + cadena1);
                    String cadena2 = lector.nextLine();
                    System.out.println("Cadena recibida: " + cadena2);
                    String cadenasOrdenadas = ordenarCadenas(cadena1, cadena2);
                    escritor.println(cadenasOrdenadas);
                    System.out.println("Cadenas ordenadas enviadas al cliente: " + cadenasOrdenadas);
                } else if (opcion.equals("salir")) {
                    // Salir del bucle
                    System.out.println("Usuario se desconecta...");
                    break;
                } else{
                    System.out.println("Esperando al cliente...");
                }
            }
        } catch (Exception e) {
            System.out.println("Se ha cerrado la conexión forzosamente.");
        }
    }

    private static int sumaAsciiCadena(String cadena) {
        int suma = 0;
        for (int i = 0; i < cadena.length(); i++) {
            int codigo = cadena.charAt(i);
            suma += codigo;
        }
        return suma;
    }

    private static String ordenarCadenas(String cadena1, String cadena2) {
        // Comparar lexicograficamente
        if (cadena1.compareTo(cadena2) < 0) {
            return cadena1 + " " + cadena2;
        } else {
            return cadena2 + " " + cadena1;
        }

    }
}
