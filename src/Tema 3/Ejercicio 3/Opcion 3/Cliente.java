package Tema

import java.io.*;
import java.net.*;

public class Cliente {
    public static void main(String[] args) {
        // Verificamos que se hayan pasado los dos argumentos (IP y número)
        if (args.length < 2) {
            System.err.println("Uso: java Cliente <dirección IP> <número>");
            System.exit(1);
        }

        // Dirección IP y puerto predeterminados
        String direccion = args[0]; // Dirección IP del servidor, pasada como argumento
        int puerto = 12345; // Puerto

        // Convertir el número recibido como parámetro
        int numero;
        try {
            numero = Integer.parseInt(args[1]); // Número que se va a enviar
        } catch (NumberFormatException e) {
            System.err.println("El argumento debe ser un número entero.");
            System.exit(1);
            return;
        }

        try (Socket socket = new Socket(direccion, puerto);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

            // Enviar el número al servidor
            System.out.println("El cliente envía: " + numero);
            out.println(numero); // Enviar el número como texto

            // Leer el resultado enviado por el servidor
            String resultado = in.readLine(); // Leer el resultado como texto
            System.out.println("El cuadrado de " + numero + " es: " + resultado);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
