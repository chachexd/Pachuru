package Tema

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    private static final String direccion = "localhost";
    private static final int puerto = 12345;

    public static void main(String[] args) {
        try (Socket socket = new Socket(direccion, puerto)) {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            Scanner sc = new Scanner(System.in);
            System.out.println("Conectado al servidor.");

            while (true) {
                System.out.print("Introduzca la opción: ");
                String opcion = sc.next();
                System.out.println("Cadena 1:");
                String cadena1 = sc.next();
                System.out.println("Cadena 2:");
                String cadena2 = sc.next();


                if (opcion.equalsIgnoreCase("salir")) {
                    break;
                }

                out.println(opcion);
                out.println(cadena1);
                out.println(cadena2);


                //Recibimos el resultado del servidor
                String resultado = in.readLine();
                System.out.println("Resultado: " + resultado);

                String mensaje = in.readLine();
                System.out.println("Servidor: " + mensaje);
            }

            socket.close();
            System.out.println("Te has desconectado");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}