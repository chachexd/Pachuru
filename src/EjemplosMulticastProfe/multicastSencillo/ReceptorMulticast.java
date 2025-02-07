package EjemplosMulticastProfe.multicastSencillo;

import java.io.IOException;
import java.net.*;

public class ReceptorMulticast {
    public static void main(String[] args) throws Exception {
        int port = 4000;
        String message = null;
        InetAddress address = null;
        MulticastSocket socket = null;
        DatagramPacket packet = null;
        try {
            address = InetAddress.getByName("224.0.0.1");
        }
        catch (UnknownHostException e) {
            System.out.println("Error: " + e.toString());
        }
        try {
            socket = new MulticastSocket(port);
            socket.joinGroup(address);
        }
        catch(IOException e) {
            System.out.println("Error: " + e.toString());
        }
        System.out.println("ServidorMulticast esperando...");
        while (true) {
            byte buffer[] = new byte[1024];
            packet = new DatagramPacket(buffer, buffer.length);
            socket.receive(packet);
            message = new String(buffer, 0, packet.getLength());
            System.out.println("Recibido: " + message);
        }
    }
}