package Tema

import java.io.*;
import java.net.*;

public class Cliente {
    private static final int puerto = 12345;
    private static final String direccion = "231.0.0.1";  // Dirección multicast

    public static void main(String[] args) {
        try {
            MulticastSocket server = new MulticastSocket(puerto);
            InetAddress grupo = InetAddress.getByName(direccion);

            server.joinGroup(grupo);

            String salida = new String();
            while (!salida.equals("salir")) {
                byte[] buffer = new byte[256];

                //Creamos el datagrama en el que recibiremos el paquete del socket
                DatagramPacket dgp = new DatagramPacket(buffer, buffer.length);

                // Recibimos el paquete del socket
                server.receive(dgp);

                // Vemos los datos recibidos por pantalla, usando la longitud real del mensaje
                salida = new String(buffer, 0, dgp.getLength());
                System.out.println(salida);

            }

            //Salimos del grupo multicast
            server.leaveGroup(grupo);

            //Cerramos el socket
            server.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
