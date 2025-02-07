package EjemploMultiCastServer.Pruebas;

import java.io.File;
import java.io.FileOutputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class Receptor {
    public static void main(String[] args) {
        try {
            MulticastSocket ms = new MulticastSocket(10000);
            InetAddress group = InetAddress.getByName("231.0.0.1");
            ms.joinGroup(group);

            byte[] buffer = new byte[10000];
            DatagramPacket dgp = new DatagramPacket(buffer, buffer.length);

            ms.receive(dgp);
            File file = new File("D:\\Desktop\\received_image.jpg");
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(dgp.getData(), 0, dgp.getLength());

            fileOutputStream.close();

            ms.leaveGroup(group);
            ms.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
