package Tema

import java.io.*;
import java.net.*;

public class Servidor {
    private static final int puerto = 12345;
    private static final String direccion = "231.0.0.1";  //Dirección multicast

    public static void main(String[] args) {
        try {
            MulticastSocket socket = new MulticastSocket(puerto);
            InetAddress grupo = InetAddress.getByName(direccion);

            socket.joinGroup(grupo);
            System.out.println("Servidor unido al grupo multicast.");

            Thread recibirMensajes = new Thread(() -> {
                try {
                    byte[] buffer = new byte[1024];
                    while (true) {
                        DatagramPacket paquete = new DatagramPacket(buffer, buffer.length);
                        socket.receive(paquete);  //Esperar y recibir mensajes

                        //Convertir el mensaje recibido en un String
                        String mensaje = new String(paquete.getData(), 0, paquete.getLength());
                        System.out.println("Servidor: " + mensaje);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            recibirMensajes.start();

            //Hilo para enviar mensajes desde la consola
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String mensaje;
            while (true) {
                mensaje = reader.readLine();

                //Si el mensaje es "salir", terminamos
                if ("salir".equalsIgnoreCase(mensaje)) {
                    System.out.println("El servidor ha finalizado.");
                    break;
                }

                //Convertir el mensaje a bytes
                byte[] buffer = mensaje.getBytes();

                //Crear DatagramPacket para enviar el mensaje
                DatagramPacket paquete = new DatagramPacket(buffer, buffer.length, grupo, puerto);

                //Enviar el mensaje
                socket.send(paquete);
            }

            socket.leaveGroup(grupo);
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
