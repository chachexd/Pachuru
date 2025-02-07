package TransformadorConMenu;

import java.io.*;
import java.net.*;

public class Cliente {
    private static final int PUERTO = 12345; // Puerto para enviar y recibir cadenas
    private static final String SERVIDOR_IP = "localhost"; // Dirección del servidor

    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket()) {
            InetAddress servidor = InetAddress.getByName(SERVIDOR_IP);
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            while (true) {
                // Mostrar menú con las opciones numeradas
                System.out.println("Seleccione una opción de transformación:");
                System.out.println("1. Convertir a MAYÚSCULAS");
                System.out.println("2. Convertir a minúsculas");
                System.out.println("3. Salir");

                // Leer la opción seleccionada
                String opcion = reader.readLine().trim();

                if (opcion.equals("3")) {
                    System.out.println("Cliente finalizado.");
                    break; // Salir del bucle y finalizar el cliente
                }

                // Verificar que la opción es válida
                if (!opcion.equals("1") && !opcion.equals("2")) {
                    System.out.println("Opción no válida. Por favor, seleccione 1, 2 o 3.");
                    continue; // Volver a pedir la opción
                }

                // Leer la cadena a transformar
                System.out.println("Ingrese la cadena a transformar:");
                String cadena = reader.readLine();

                // Crear el mensaje con la opción seleccionada y la cadena
                String mensaje = opcion + " " + cadena;
                byte[] datos = mensaje.getBytes();

                // Enviar la cadena al servidor
                DatagramPacket paquete = new DatagramPacket(datos, datos.length, servidor, PUERTO);
                socket.send(paquete);

                // Recibir la respuesta del servidor (cadena transformada)
                byte[] buffer = new byte[1024];
                DatagramPacket respuesta = new DatagramPacket(buffer, buffer.length);
                socket.receive(respuesta);

                // Mostrar la cadena transformada
                String mensajeRespuesta = new String(respuesta.getData(), 0, respuesta.getLength());
                System.out.println("Cadena transformada: " + mensajeRespuesta);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

