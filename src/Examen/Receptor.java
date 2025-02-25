package Examen;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

public class Receptor {
    // Crear un socket multicast y un socket servidor
    public static void main(String[] args) {

        // Crear un socket multicast y un socket servidor
        try (MulticastSocket multicastSocket = new MulticastSocket(4446)) {
            // Unirse al grupo multicast
            InetAddress grupo = InetAddress.getByName("230.0.0.7");
            multicastSocket.joinGroup(grupo);
            // Crear un socket servidor
            ServerSocket serverSocket = new ServerSocket(9744);
            System.out.println("Servidor en ejecución...");

            while (true) {
                // Aceptar conexiones de los clientes
                Socket socket = serverSocket.accept();
                new Thread(() -> handlerClient(socket, multicastSocket, grupo)).start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void handlerClient(Socket socket, MulticastSocket multicastSocket, InetAddress grupo) {
        // Recibir mensajes de los clientes y enviarlos al grupo multicast
        try (BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            String mensajeCliente;
            while ((mensajeCliente = br.readLine()) != null) {
                // Enviar mensaje al grupo multicast
                System.out.println("Mensaje recibido: " + mensajeCliente);
                byte[] buffer = mensajeCliente.getBytes();
                // Crear un paquete con el mensaje y enviarlo al grupo multicast
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length, grupo, 4446);
                multicastSocket.send(packet);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}