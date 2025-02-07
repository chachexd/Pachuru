package practicas.practicaExamen;

import java.io.IOException;
import java.net.*;

public class ReceptorMulticast {
    public static void main(String[] args) throws Exception {
        int port = 4000;
        InetAddress address = InetAddress.getByName("224.0.0.1");
        MulticastSocket socket = new MulticastSocket(port);
        socket.joinGroup(address);
        System.out.println("ServidorMulticast esperando...");

        while (true) {
            byte[] buffer = new byte[1024];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            socket.receive(packet);
            String message = new String(buffer, 0, packet.getLength());
            System.out.println("Recibido: " + message);

            // Reenviar el mensaje a todos los emisores
            String reenvio = "Mensaje de " + packet.getAddress().getHostAddress() + ": " + message;
            byte[] data = reenvio.getBytes();
            DatagramPacket reenvioPacket = new DatagramPacket(data, data.length, address, port);
            socket.send(reenvioPacket);
        }
    }
}