package Tema

import java.io.*;
import java.net.*;

public class Cliente {
    private static final String direccion = "230.0.0.0";
    private static final int puerto = 4446;

    public static void main(String[] args) {
        try {
            // Crear un socket para enviar los mensajes
            MulticastSocket socket = new MulticastSocket();

            // Dirección multicast
            InetAddress grupo = InetAddress.getByName(direccion);

            // Pedir el nombre de usuario
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Introduce tu nombre de usuario: ");
            String usuario = reader.readLine();

            // Bucle para enviar mensajes
            int contador = 1;
            String salida = "";  //Inicializamos la variable
            while (!salida.equals("salir")) {
                //Leer el mensaje del usuario
                System.out.print("Escribe un mensaje (o 'salir' para terminar): ");
                salida = reader.readLine();  //Actualizamos la variable con la entrada del usuario

                if (!salida.equals("salir")) {
                    //Crear el mensaje a enviar
                    String mensajeCompleto = usuario + " envía: " + salida + " #" + contador;

                    // Convertir el mensaje a bytes
                    byte[] buffer = mensajeCompleto.getBytes();

                    // Crear el paquete para el datagrama
                    DatagramPacket packet = new DatagramPacket(buffer, buffer.length, grupo, puerto);

                    // Enviar el mensaje al grupo multicast
                    socket.send(packet);
                    System.out.println("Enviado: " + mensajeCompleto);

                    // Incrementar el contador
                    contador++;
                }
            }

            // Cerrar el socket
            socket.close();
            System.out.println("Conexión cerrada.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
