package EjemploPropioChat.OtraSolucion;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor extends Thread {
    private static int puerto = 1234;
    private Socket cliente;

    public Servidor(Socket cliente) {
        this.cliente = cliente;
    }

    @Override
    public void run() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(cliente.getInputStream()))) {
            String mensaje;
            while ((mensaje = reader.readLine()) != null) {
                System.out.println("Cliente: " + mensaje);
            }
        } catch (IOException e) {
            System.out.println("Error en el hilo del servidor: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        System.out.println("Servidor iniciado en el puerto " + puerto);
        try (ServerSocket serverSocket = new ServerSocket(puerto)) {
            System.out.println("Esperando conexiones de clientes...");
            Socket cliente = serverSocket.accept();
            System.out.println("Cliente conectado desde: " + cliente.getInetAddress());

            // Hilo para leer mensajes del cliente
            new Servidor(cliente).start();

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
}

