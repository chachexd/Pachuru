package Tema

import java.io.*;
import java.net.*;

public class Servidor {
    private static final int puerto = 12345;
    private static final String direccion = "231.0.0.1";  // Dirección multicast

    public static void main(String[] args) {
        System.out.println("Arrancando el servidor multicast... \n");

        try {
            // Creamos el socket multicast
            MulticastSocket server = new MulticastSocket();
            InetAddress grupo = InetAddress.getByName(direccion);

            // Preparamos el datagrama vacío para la transmisión inicial
            byte[] vacio = new byte[0];
            DatagramPacket dgp = new DatagramPacket(vacio, 0, grupo, puerto);

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String linea = br.readLine();

            // El servidor enviará los datos por teclado hasta que se escriba 'salir'
            while (!linea.equals("salir")) {
                // Convertimos la línea escrita en bytes
                byte[] buffer = linea.getBytes();

                // Establecemos los datos del datagrama
                dgp.setData(buffer);
                dgp.setLength(buffer.length); // Definimos la longitud del datagrama

                // Enviamos el datagrama al grupo multicast
                server.send(dgp);

                // Leemos la siguiente línea del teclado
                linea = br.readLine();
            }

            // Salimos del grupo multicast y cerramos el socket
            server.leaveGroup(grupo);
            server.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
