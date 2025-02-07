package Examen;

import java.io.*;
import java.net.*;

public class Receptor {
    private static final String direccion = "231.0.0.1";  // Dirección multicast
    private static final int puerto = 12345;  // Puerto del multicast

    public static void main(String[] args) {
        try (MulticastSocket socket = new MulticastSocket(puerto)) {
            InetAddress grupo = InetAddress.getByName(direccion);
            socket.joinGroup(grupo);

            byte[] buffer = new byte[256];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

            System.out.println("Esperando mensajes...");

            while (true) {
                socket.receive(packet);
                String mensaje = new String(packet.getData(), 0, packet.getLength());
                System.out.println("Mensaje recibido: " + mensaje);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}