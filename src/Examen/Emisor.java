package Examen;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.Socket;
import java.util.Scanner;

public class Emisor {
    public static void main(String[] args) throws IOException {
        InetAddress grupo = InetAddress.getByName("230.0.0.7");
        int puerto = 4446;

        // Solicitar el nombre del usuario y el mensaje
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduce tu nombre de usuario: ");
        String nombreUsuario = scanner.nextLine();
        System.out.println("Escribe un mensaje:");

        // Iniciar el hilo para recibir mensajes
        new Thread(() -> {
            try (MulticastSocket multicastSocket = new MulticastSocket(puerto)) {
                multicastSocket.joinGroup(grupo);
                byte[] buffer = new byte[1024];
                while (true) {
                    // Recibir mensajes del grupo multicast
                    DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                    multicastSocket.receive(packet);
                    String received = new String(packet.getData(), 0, packet.getLength());
                    System.out.println(received);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

        // Enviar mensajes al servidor
        try (Socket socket = new Socket("localhost", 9744);
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))) {
            while (true) {
                // Leer el mensaje del usuario y enviarlo al servidor
                String mensaje = scanner.nextLine();
                bw.write(nombreUsuario + ": " + mensaje + "\n");
                bw.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}