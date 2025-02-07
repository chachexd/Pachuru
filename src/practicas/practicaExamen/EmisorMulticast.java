package practicas.practicaExamen;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class EmisorMulticast {
    public static void main(String[] args) throws Exception {
        System.out.println("Introduce tu nombre de usuario: ");
        Scanner sc = new Scanner(System.in);
        String nombreUsuario = sc.nextLine();

        InetAddress address = InetAddress.getByName("224.0.0.1");
        MulticastSocket socket = new MulticastSocket(4000);
        socket.joinGroup(address);

        // Thread to receive multicast messages
        new Thread(() -> {
            try {
                byte[] buffer = new byte[1024];
                while (true) {
                    DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                    socket.receive(packet);
                    String received = new String(packet.getData(), 0, packet.getLength());
                    System.out.println("Recibido: " + received);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

        // Sending messages to the multicast group
        while (true) {
            String message = nombreUsuario + " envía el mensaje: #" + (int) (Math.random() * 100);
            byte[] data = message.getBytes();
            DatagramPacket packet = new DatagramPacket(data, data.length, address, 4000);
            socket.send(packet);
            System.out.println("Enviado: " + message);
            Thread.sleep(1000); // Espera de 1 segundo entre mensajes
        }
    }
}