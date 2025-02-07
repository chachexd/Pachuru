package Multicast2;

import java.io.IOException;
import java.net.*;
import java.util.Random;
import java.util.Scanner;

public class Emisor {
    public static void main(String[] args) throws Exception {
        System.out.println("Introduce tu nombre de usuario: ");
        Scanner sc = new Scanner(System.in);
        String nombreUsuario = sc.nextLine();

        String message;
        int n = 1;
        InetAddress address = null;
        MulticastSocket socket = null;
        DatagramPacket packet = null;
        Random r = new Random();
        long t;
        try {
            address = InetAddress.getByName("224.0.0.1");
        }
        catch (UnknownHostException e) {
            System.out.println("Error: " + e.toString());
        }
        try {
            socket = new MulticastSocket();
// socket.setTimeToLive(255);
        }
        catch (IOException e) {
            System.out.println("Error: " + e.toString());
        }
        while (true) {
            message = nombreUsuario + " envía el mensaje:  #" +
                    Integer.toString(n++);
            byte[] data = new byte[1024];
            data = message.getBytes();
            packet = new DatagramPacket(data, data.length, address, 4000);
            socket.send(packet);
            System.out.println("Enviado: " + message);
            t = (r.nextInt(10) + 1) * 100; // valor aleatorio de espera
            Thread.sleep(t);
        }
    }
}
