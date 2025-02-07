package Chat;

import java.io.*;
import java.net.*;

public class Cliente {
    private static final int puerto = 12345;
    private static final String direccion = "231.0.0.1";  // Dirección multicast

    public static void main(String[] args) {
        try {
            //Crear socket multicast para el cliente
            MulticastSocket socket = new MulticastSocket(puerto);
            InetAddress grupo = InetAddress.getByName(direccion);

            //Unirse al grupo multicast
            socket.joinGroup(grupo);
            System.out.println("Cliente unido al grupo multicast.");

            //Hilo para recibir mensajes del servidor o de otros clientes
            Thread recibirMensajes = new Thread(() -> {
                try {
                    byte[] buffer = new byte[1024];
                    while (true) {
                        DatagramPacket paquete = new DatagramPacket(buffer, buffer.length);
                        socket.receive(paquete);  // Esperar y recibir mensajes

                        // Convertir el mensaje recibido en un String
                        String mensaje = new String(paquete.getData(), 0, paquete.getLength());
                        System.out.println(mensaje);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            //Iniciar el hilo para recibir mensajes
            recibirMensajes.start();

            //Leer y enviar mensajes desde el cliente
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String mensaje;
            while (true) {
                //Leer mensaje desde consola
                System.out.print("Cliente: ");
                mensaje = reader.readLine();

                //Si el mensaje es "salir", terminamos
                if ("salir".equalsIgnoreCase(mensaje)) {
                    System.out.println("Cliente desconectado.");
                    break;
                }

                //Convertir el mensaje a bytes
                byte[] buffer = mensaje.getBytes();

                //Crear DatagramPacket para enviar el mensaje
                DatagramPacket paquete = new DatagramPacket(buffer, buffer.length, grupo, puerto);

                //Enviar el mensaje
                socket.send(paquete);
            }

            socket.leaveGroup(grupo);  //Dejar el grupo multicast antes de cerrar
            socket.close();  //Cerrar el socket después de finalizar la comunicación
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
