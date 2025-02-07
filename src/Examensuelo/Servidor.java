package Examensuelo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

public class Servidor {
    // Servidor que recibe mensajes de los clientes y los envía a un grupo multicast
    public static void main(String[] args) {
        try (MulticastSocket multicastSocket = new MulticastSocket()) {
            InetAddress grupo = InetAddress.getByName("224.0.0.1");
            int puerto = 4446;
            ServerSocket serverSocket = new ServerSocket(9744);
            System.out.println("Servidor en ejecución...");

            while (true) {
                Socket socket = serverSocket.accept();
                new Thread(new ClientHandler(socket, multicastSocket, grupo, puerto)).start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
// Clase que maneja la conexión con el cliente
class ClientHandler implements Runnable {
    private Socket socket;
    private MulticastSocket multicastSocket;
    private InetAddress grupo;
    private int puerto;

    public ClientHandler(Socket socket, MulticastSocket multicastSocket, InetAddress grupo, int puerto) {
        this.socket = socket;
        this.multicastSocket = multicastSocket;
        this.grupo = grupo;
        this.puerto = puerto;
    }

    @Override
    public void run() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            String mensajeCliente;
            while ((mensajeCliente = br.readLine()) != null) {
                System.out.println("Mensaje recibido: " + mensajeCliente);
                byte[] buffer = mensajeCliente.getBytes();
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length, grupo, puerto);
                multicastSocket.send(packet);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}