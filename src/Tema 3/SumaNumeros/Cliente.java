package Tema

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Cliente {
    private static final int puertoEnvia = 12345; // Puerto para enviar números al servidor
    private static final int puertoRecibe = 12346; // Puerto para recibir la suma total
    private static final String direccion = "231.0.0.1"; // Grupo multicast
    private static final String SERVIDOR_IP = "localhost"; // Dirección del servidor

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try (DatagramSocket socket = new DatagramSocket()) {
            MulticastSocket multicastSocket = new MulticastSocket(puertoRecibe); // Puerto para recibir la suma

            InetAddress servidor = InetAddress.getByName(SERVIDOR_IP);
            InetAddress grupo = InetAddress.getByName(direccion);

            // Unirse al grupo multicast
            multicastSocket.joinGroup(grupo);
            System.out.println("Cliente conectado. Ingrese números para enviar al servidor.");

            while (true) {
                // Leer número y enviarlo al servidor
                System.out.print("Ingrese un número: ");
                String numero = sc.nextLine();
                byte[] datos = numero.getBytes();

                DatagramPacket paquete = new DatagramPacket(datos, datos.length, servidor, puertoEnvia); // Usando puerto de envío
                socket.send(paquete);

                // Recibir la suma total en multicast desde otro puerto
                byte[] buffer = new byte[1024];
                DatagramPacket respuesta = new DatagramPacket(buffer, buffer.length);
                multicastSocket.receive(respuesta);

                String mensaje = new String(respuesta.getData(), 0, respuesta.getLength());
                System.out.println("Suma total: " + mensaje);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
