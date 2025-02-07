package practicas.Ejer4b;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

public class Servidor {
    public static void main(String[] args) {
        try (MulticastSocket multicastSocket = new MulticastSocket()) {
            InetAddress group = InetAddress.getByName("230.0.0.0");
            int port = 4446;
            ServerSocket serverSocket = new ServerSocket(9744);
            System.out.println("Servidor en ejecución...");

            while (true) {
                Socket socket = serverSocket.accept();
                new Thread(new ClientHandler(socket, multicastSocket, group, port)).start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

class ClientHandler implements Runnable {
    private Socket socket;
    private MulticastSocket multicastSocket;
    private InetAddress group;
    private int port;

    public ClientHandler(Socket socket, MulticastSocket multicastSocket, InetAddress group, int port) {
        this.socket = socket;
        this.multicastSocket = multicastSocket;
        this.group = group;
        this.port = port;
    }

    @Override
    public void run() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            String mensajeCliente;
            while ((mensajeCliente = br.readLine()) != null) {
                System.out.println("Mensaje recibido: " + mensajeCliente);
                byte[] buffer = mensajeCliente.getBytes();
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length, group, port);
                multicastSocket.send(packet);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}