package multicast1;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Introduce tu nombre de usuario: ");
            String username = scanner.nextLine();

            InetAddress group = InetAddress.getByName("230.0.0.0");
            int port = 4446;
            MulticastSocket multicastSocket = new MulticastSocket();

            System.out.println("Conectando al servidor");

            int messageCount = 1;
            while (true) {
                String message = username + " envía mensaje " + messageCount++;
                byte[] buffer = message.getBytes();
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length, group, port);
                multicastSocket.send(packet);
                System.out.println("Mensaje enviado: " + message);
                Thread.sleep(2000); // Espera 2 segundos
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}