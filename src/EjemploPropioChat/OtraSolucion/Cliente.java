package EjemploPropioChat.OtraSolucion;

import java.io.*;
import java.net.Socket;

public class Cliente extends Thread {
    private Socket socket;

    public Cliente(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            String mensaje;
            while ((mensaje = reader.readLine()) != null) {
                System.out.println("Servidor: " + mensaje);
            }
        } catch (IOException e) {
            System.out.println("Error en el hilo del cliente: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        String IP = "localhost";
        int puerto = 1234;
        System.out.println("Conectando al servidor " + IP + ":" + puerto);

        try (Socket socket = new Socket(IP, puerto)) {
            System.out.println("Conexión establecida con el servidor.");

            // Hilo para leer mensajes del servidor
            new Cliente(socket).start();

            // Flujo de salida para enviar mensajes al servidor
            try (PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
                 BufferedReader consola = new BufferedReader(new InputStreamReader(System.in))) {
                String mensajeEnviar;
                while ((mensajeEnviar = consola.readLine()) != null) {
                    writer.println(mensajeEnviar);
                }
            }
        } catch (IOException e) {
            System.out.println("Error en el cliente: " + e.getMessage());
        }
    }
}

