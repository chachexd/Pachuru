package Ejercicio4;

import java.io.*;
import java.net.*;

public class Servidor {
    private static final String direccion = "231.0.0.1";  // Dirección multicast
    private static final int puerto = 12345;  // Puerto del multicast

    public static void main(String[] args) {
        try {
            //Crear un socket multicast para recibir los mensajes
            MulticastSocket socket = new MulticastSocket(puerto);

            //Dirección multicast
            InetAddress grupo = InetAddress.getByName(direccion);

            //Unirse al grupo multicast
            socket.joinGroup(grupo);

            //Buffer para almacenar los datos recibidos
            byte[] buffer = new byte[256];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

            System.out.println("Esperando mensajes...");

            //Bucle para recibir mensajes indefinidamente
            while (true) {
                //Recibir un paquete
                socket.receive(packet);

                //Convertir los datos recibidos en un mensaje
                String mensaje = new String(packet.getData(), 0, packet.getLength());
                System.out.println("Mensaje recibido: " + mensaje);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}