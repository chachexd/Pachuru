package Examen;

import java.io.*;
import java.net.*;
import java.util.UUID;

public class Emisor {
    private static final String direccion = "231.0.0.1";
    private static final int puerto = 12345;

    public static void main(String[] args) {

        try (MulticastSocket socket = new MulticastSocket(puerto)) {
            InetAddress grupo = InetAddress.getByName(direccion);
            socket.joinGroup(grupo);

            System.out.print("Introduce tu nombre de usuario: ");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String usuario = reader.readLine();

            Thread recibirMensajes = new Thread(() -> {
                try {
                    byte[] buffer = new byte[256];
                    while (true) {
                        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                        socket.receive(packet);
                        String mensaje = new String(packet.getData(), 0, packet.getLength());
                        System.out.println("Mensaje recibido: " + mensaje);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            recibirMensajes.start();

            int contador = 1;
            String salida = "";
            while (!salida.equals("salir")) {
                System.out.print("Escribe un mensaje (o 'salir' para terminar): ");
                salida = reader.readLine();

                if (!salida.equals("salir")) {
                    String mensajeCompleto = usuario + " envía: " + salida + " #" + contador;
                    byte[] buffer = mensajeCompleto.getBytes();
                    DatagramPacket packet = new DatagramPacket(buffer, buffer.length, grupo, puerto);
                    socket.send(packet);
                    System.out.println("Mensaje enviado: " + mensajeCompleto);
                    contador++;
                }
            }

            socket.leaveGroup(grupo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}