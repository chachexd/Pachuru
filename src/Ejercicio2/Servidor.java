package Ejercicio2;

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

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Cliente conectado.");

                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

                String horaActual = new Date().toString();

                out.println("Hora actual del servidor: " + horaActual);

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
