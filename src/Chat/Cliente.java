package Chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class Cliente {
    private static final int PUERTO = 12345;
    private static final String DIRECCION = "231.0.0.1";  // Dirección multicast

    public static void main(String[] args) {
        try (MulticastSocket socket = new MulticastSocket(PUERTO)) {
            InetAddress grupo = InetAddress.getByName(DIRECCION);
            socket.joinGroup(grupo);
            System.out.println("Cliente unido al grupo multicast.");

            Thread recibirMensajes = new Thread(() -> {
                try {
                    byte[] buffer = new byte[1024];
                    while (true) {
                        DatagramPacket paquete = new DatagramPacket(buffer, buffer.length);
                        socket.receive(paquete);
                        String mensaje = new String(paquete.getData(), 0, paquete.getLength());
                        System.out.println("Mensaje Enviado: " + mensaje);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            recibirMensajes.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String mensaje;
            while (true) {
                System.out.print("Cliente: ");
                mensaje = reader.readLine();
                if ("salir".equalsIgnoreCase(mensaje)) {
                    System.out.println("Cliente desconectado.");
                    break;
                }
                byte[] buffer = mensaje.getBytes();
                DatagramPacket paquete = new DatagramPacket(buffer, buffer.length, grupo, PUERTO);
                socket.send(paquete);
            }

            socket.leaveGroup(grupo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}