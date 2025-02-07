package Práctica2Multicast.prueba;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.Scanner;

public class Cliente {

    static MulticastSocket s;
    static InetAddress group;

    static {
        try {
            s = new MulticastSocket(10000);
            group = InetAddress.getByName("231.0.0.1");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws IOException {
        System.out.println("Cliente iniciado");
        lanzarHiloReceptor();
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

    static void lanzarHiloReceptor(){
        new Thread(() -> {
            System.out.println("Hilo receptor iniciado");
            try {
                s.joinGroup(group);
                String salida = new String();
                while(!salida.equals("salir")) {
                    byte[] buffer = new byte[256];
                    DatagramPacket dgp = new DatagramPacket(buffer, buffer.length);

                    s.receive(dgp);
                    byte[] buffer2 = new byte[dgp.getLength()];
                    System.arraycopy(dgp.getData(), 0, buffer2, 0, dgp.getLength());
                    salida = new String(buffer2);
                    System.out.println(dgp.getAddress() + ": " + salida);
                }
                s.leaveGroup(group);
                s.close();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).start();
    }
}
