package Practica2Multicast;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class Receptor {
    public static void main(String[] args) {
        try {
            MulticastSocket ms = new MulticastSocket(10000);
            InetAddress group = InetAddress.getByName("231.0.0.1");
            ms.joinGroup(group);

            byte[] buffer = new byte[256];
            DatagramPacket dgp = new DatagramPacket(buffer, buffer.length);
            String mensajeSalida;
            while (true){
                ms.receive(dgp);
                byte[] bufferAcortado = new byte[dgp.getLength()];
                System.arraycopy(dgp.getData(), 0, bufferAcortado, 0, dgp.getLength());
                mensajeSalida = new String(bufferAcortado);
                System.out.println(mensajeSalida);
            }
//            ms.leaveGroup(group);
//            ms.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
