package Tema

import java.io.*;
import java.net.*;
import java.util.*;

public class Servidor {
    public static void main(String[] args) {
        String direccionServidor = "localhost";
        int puerto = 12345;

        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(puerto);
            System.out.println("Servidor esperando conexiones en " + direccionServidor + " en el puerto " + puerto);

            //Creamos un bucle para que el servidor se mantenga en constante funcionamiento
            while (true) {
                //Aceptamos una conexión del cliente y creamos un Socket para comunicarnos con él
                Socket socket = serverSocket.accept();
                System.out.println("Cliente conectado.");

                //Creamos un PrintWriter para enviar la información al cliente
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

                //Calculamos el cuadrado del número recibido por el cliente y mandaremos de vuelta un mensaje con dicho calculo
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String mensaje = reader.readLine();
                int numero = Integer.parseInt(mensaje);

                System.out.println("Recibido: " + numero);

                int cuadrado = numero * numero;
                out.println("El cuadrado de " + numero + ": " + cuadrado);

                //Cerramos el socket y el PrintWriter
                out.close();
                socket.close();
                System.out.println("Cliente desconectado.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (serverSocket != null && !serverSocket.isClosed()) {
                    serverSocket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
