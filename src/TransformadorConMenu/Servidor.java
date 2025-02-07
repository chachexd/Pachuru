package TransformadorConMenu;

import java.io.*;
import java.net.*;

public class Servidor {
    private static final int PUERTO = 12345; // Puerto para recibir y enviar cadenas
    private static final String MULTICAST_IP = "231.0.0.1"; // IP de grupo multicast (aunque en este caso no se usará, ya que se usa UDP)

    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket(PUERTO)) {

            byte[] buffer = new byte[1024];
            System.out.println("Servidor esperando cadenas para transformar...");

            while (true) {
                // Recibir el paquete con el número de opción y la cadena
                DatagramPacket paquete = new DatagramPacket(buffer, buffer.length);
                socket.receive(paquete);

                String mensaje = new String(paquete.getData(), 0, paquete.getLength());
                String[] cadenas = mensaje.split(" ", 2);

                // Verificamos que la opción y la cadena se recibieron correctamente
                if (cadenas.length < 2) {
                    System.out.println("Mensaje recibido no válido.");
                    continue;
                }

                String opcion = cadenas[0];
                String cadena = cadenas[1];
                String respuesta = "";

                // Según la opción, transformamos la cadena
                switch (opcion) {
                    case "1":
                        respuesta = cadena.toUpperCase();
                        break;
                    case "2":
                        respuesta = cadena.toLowerCase();
                        break;
                    default:
                        respuesta = "Opción no válida";
                }

                // Enviar la respuesta al cliente (transformación de la cadena)
                byte[] datosRespuesta = respuesta.getBytes();
                DatagramPacket paqueteRespuesta = new DatagramPacket(datosRespuesta, datosRespuesta.length, paquete.getAddress(), paquete.getPort());
                socket.send(paqueteRespuesta);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
