package Práctica2Multicast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.Scanner;

public class Cliente {

    public static void main(String[] args) throws IOException {
        System.out.println("Cliente iniciado");
        MulticastSocket s = new MulticastSocket(10000);
        InetAddress group = InetAddress.getByName("231.0.0.1");

        byte[] vacio = new byte[0];
        DatagramPacket dgp = new DatagramPacket(vacio, 0, group, 10000);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String linea = br.readLine();

        while (!linea.equals("salir")){
            byte[] buffer = linea.getBytes();
            dgp.setData(buffer);
            dgp.setLength(buffer.length);
            s.send(dgp);
            linea = new Scanner(System.in).nextLine();
        }
        s.close();
    }
}
