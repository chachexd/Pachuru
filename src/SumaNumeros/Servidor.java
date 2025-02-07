package SumaNumeros;

import java.io.*;
import java.net.*;

public class Servidor {
    private static final int puertoRecibe = 12345; // Puerto para recibir números
    private static final int puertoEnvia = 12346; // Puerto para enviar la suma total
    private static final String MULTICAST_IP = "231.0.0.1"; // Grupo multicast

    public static void main(String[] args) {
        int sumaTotal = 0;

        try (DatagramSocket socketRecibir = new DatagramSocket(puertoRecibe);
             MulticastSocket multicastSocket = new MulticastSocket()) {

            InetAddress grupo = InetAddress.getByName(MULTICAST_IP);
            byte[] buffer = new byte[1024];

            System.out.println("Servidor esperando números...");

            while (true) {
                // Recibir número del cliente
                DatagramPacket paquete = new DatagramPacket(buffer, buffer.length);
                socketRecibir.receive(paquete);

                String mensaje = new String(paquete.getData(), 0, paquete.getLength()).trim();

                try {
                    int numero = Integer.parseInt(mensaje);
                    sumaTotal += numero;
                    System.out.println("Número recibido: " + numero + " | Suma total: " + sumaTotal);

                    // Enviar la suma total a los clientes vía multicast en el puerto distinto
                    String sumaResultado = String.valueOf(sumaTotal);
                    byte[] sumaBytes = sumaResultado.getBytes();
                    DatagramPacket paqueteSuma = new DatagramPacket(sumaBytes, sumaBytes.length, grupo, puertoEnvia);
                    multicastSocket.send(paqueteSuma); // Se utiliza otro puerto para enviar la suma total

                } catch (NumberFormatException e) {
                    System.out.println("Mensaje inválido recibido: " + mensaje);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
