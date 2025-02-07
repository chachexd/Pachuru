package Practica2Multicast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

            System.out.println("Introduce tu nombre de usuario");
            String nombreUsuario = new Scanner(System.in).nextLine();

            int contador = 0;
            while(true){
                String mensaje = nombreUsuario + " envia el mensaje nº " + contador;

                byte[] buffer = mensaje.getBytes();
                dgp.setData(buffer);
                dgp.setLength(buffer.length);
                ms.send(dgp);

                Thread.sleep(500);

                contador++;
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
