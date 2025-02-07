package Tema

import java.io.*;
import java.net.*;

public class Servidor {
    public static void main(String[] args) {
        int puerto = 12345;

        try (ServerSocket serverSocket = new ServerSocket(puerto)) {
            System.out.println("Servidor esperando conexiones en el puerto " + puerto);

            while (true) {
                //Se acepta la conexión del cliente
                Socket socket = serverSocket.accept();
                System.out.println("Cliente conectado.");

                try (DataInputStream in = new DataInputStream(socket.getInputStream());
                     DataOutputStream out = new DataOutputStream(socket.getOutputStream())) {

                    //Lee el número enviado por el cliente
                    int numero = in.readInt();
                    System.out.println("Número recibido: " + numero);

                    //Calcula el cuadrado
                    int cuadrado = numero * numero;

                    //Envía el resultado de vuelta al cliente
                    out.writeInt(cuadrado);
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