package Multicast1;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class MulticastClient {
    public static void main(String[] args) {
        try (MulticastSocket socket = new MulticastSocket(10000)) {
            InetAddress group = InetAddress.getByName("231.0.0.1");
            socket.joinGroup(group);

            byte[] buffer = new byte[256];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

            System.out.println("Waiting for messages from the server...");

            while (true) {
                socket.receive(packet);
                String received = new String(packet.getData(), 0, packet.getLength());
                System.out.println("Received: " + received);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
