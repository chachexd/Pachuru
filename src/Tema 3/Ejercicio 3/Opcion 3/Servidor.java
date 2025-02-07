package Tema

import java.io.*;
import java.net.*;

public class Servidor {
    public static void main(String[] args) {
        int puerto = 12345;

        try (ServerSocket serverSocket = new ServerSocket(puerto)) {
            System.out.println("Servidor esperando conexiones en el puerto " + puerto);

            while (true) {
                // Se acepta la conexión del cliente
                Socket socket = serverSocket.accept();
                System.out.println("Cliente conectado.");

                try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                     PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

                    // Lee el número enviado por el cliente como texto
                    String numeroStr = in.readLine();
                    int numero = Integer.parseInt(numeroStr); // Convertir la cadena a un entero
                    System.out.println("Número recibido: " + numero);

                    // Se calcula el cuadrado
                    int cuadrado = numero * numero;

                    // Envía el resultado de vuelta al cliente como texto
                    out.println(cuadrado);
                    System.out.println("Resultado enviado: " + cuadrado);
                } catch (IOException e) {
                    System.err.println("Error al procesar datos: " + e.getMessage());
                } finally {
                    socket.close();
                    System.out.println("Cliente desconectado.");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
