package Multicast1;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.Scanner;

public class MulticastServer {
    public static void main(String[] args) {
        try (MulticastSocket socket = new MulticastSocket()) {
            InetAddress group = InetAddress.getByName("231.0.0.1");
            Scanner scanner = new Scanner(System.in);
            String message;

            System.out.println("Enter messages to send to clients. Type 'salir' to exit.");

            while (true) {
                message = scanner.nextLine();
                if (message.equalsIgnoreCase("salir")) {
                    break;
                }
                byte[] buffer = message.getBytes();
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length, group, 10000);
                socket.send(packet);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
