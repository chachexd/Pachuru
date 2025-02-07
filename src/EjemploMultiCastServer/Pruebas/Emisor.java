package EjemploMultiCastServer.Pruebas;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.Scanner;

public class Emisor {
    public static void main(String[] args) {
        System.out.println("Emisor activo");
        try {
            MulticastSocket ms = new MulticastSocket();
            InetAddress grupo = InetAddress.getByName("231.0.0.1");
            DatagramPacket dgp = new DatagramPacket(new byte[0], 0, grupo, 10000);

            while(true){
                File file = new File("D:\\Desktop\\foto.jpg");
                byte[] buffer = new byte[(int)file.length()];
                FileInputStream fileInputStream = new FileInputStream(file);
                fileInputStream.read(buffer);

                dgp.setData(buffer);
                dgp.setLength(buffer.length);
                ms.send(dgp);

                Thread.sleep(500);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
