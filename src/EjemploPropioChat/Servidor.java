package EjemploPropioChat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Servidor extends Thread{
    static int puerto = 1234;
    static Socket cliente;

    Servidor(Socket socket){
        this.cliente = socket;
    }



    public static void main(String[] args) {
        System.out.println("Servidor iniciado en el puerto " + puerto);
        try (ServerSocket serverSocket = new ServerSocket(puerto)) {
            System.out.println("Esperando conexiones de clientes...");
            Socket cliente = serverSocket.accept();
            System.out.println("Cliente conectado desde: " + cliente.getInetAddress());

            // Hilo para leer mensajes del cliente
            new EjemploPropioChat.Servidor(cliente).start();

            // Flujo de salida para enviar mensajes al cliente
            try (PrintWriter writer = new PrintWriter(cliente.getOutputStream(), true);
                 BufferedReader consola = new BufferedReader(new InputStreamReader(System.in))) {
                String mensajeEnviar;
                while ((mensajeEnviar = consola.readLine()) != null) {
                    writer.println(mensajeEnviar);
                }
            }
        } catch (IOException e) {
            System.out.println("Error en el servidor: " + e.getMessage());
        }
    }

    @Override
    public void run() {
        System.out.println("Hilo lector del Servidor iniciado");
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            String mensaje;
            while ((mensaje = reader.readLine()) != null){
                System.out.println("Cliente: " + mensaje);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

}
