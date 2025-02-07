package practicas.Ejer4;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) throws IOException {
        InetAddress group = InetAddress.getByName("224.0.0.1");
        int port = 4446;

        // Solicitar el nombre del usuario
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduce tu nombre de usuario: ");
        String nombreUsuario = scanner.nextLine();

        // Thread to receive multicast messages
        new Thread(() -> {
            try (MulticastSocket multicastSocket = new MulticastSocket(port)) {
                multicastSocket.joinGroup(group);
                byte[] buffer = new byte[1024];
                while (true) {
                    DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                    multicastSocket.receive(packet);
                    String received = new String(packet.getData(), 0, packet.getLength());
                    System.out.println(received);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

        // Sending messages to the server
        try (Socket socket = new Socket("localhost", 9744);
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))) {
            while (true) {
                String mensaje = scanner.nextLine();
                bw.write(nombreUsuario + ": " + mensaje + "\n");
                bw.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}