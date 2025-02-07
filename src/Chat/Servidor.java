package Chat;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class Servidor implements Runnable {
    private static final int PUERTO = 12345;
    private static final String DIRECCION = "231.0.0.1";  // Dirección multicast

    public static void main(String[] args) {
        new Thread(new Servidor()).start();
    }

    @Override
    public void run() {
        try (MulticastSocket socket = new MulticastSocket(PUERTO)) {
            InetAddress grupo = InetAddress.getByName(DIRECCION);
            socket.joinGroup(grupo);
            System.out.println("Servidor unido al grupo multicast.");

            byte[] buffer = new byte[1024];
            DatagramPacket paquete = new DatagramPacket(buffer, buffer.length);

            while (true) {
                socket.receive(paquete);
                String mensaje = new String(paquete.getData(), 0, paquete.getLength());
                System.out.println("Servidor recibió: " + mensaje);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}