package multicast1;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class Servidor {
    public static void main(String[] args) {
        try {
            InetAddress group = InetAddress.getByName("230.0.0.0");
            int port = 4446;
            MulticastSocket multicastSocket = new MulticastSocket(port);
            multicastSocket.joinGroup(group);

            System.out.println("Servidor iniciado");
            byte[] buffer = new byte[1024];
            while (true) {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                multicastSocket.receive(packet);
                String received = new String(packet.getData(), 0, packet.getLength());
                System.out.println("Mensaje recibido: " + received);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
