package EjemploMultiCastServer.PruebaBidireccional;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class Cliente {
    public static void main(String[] args) throws IOException {
        MulticastSocket s = new MulticastSocket(10000);
        InetAddress group = InetAddress.getByName("231.0.0.1");
        s.joinGroup(group);
        String salida = new String();
        while(!salida.equals("salir")) {
            byte[] buffer = new byte[256];
            DatagramPacket dgp = new DatagramPacket(buffer, buffer.length);
            s.receive(dgp);
            byte[] buffer2 = new byte[dgp.getLength()];
            System.arraycopy(dgp.getData(), 0, buffer2, 0, dgp.getLength());
            salida = new String(buffer2);
            System.out.println(salida);
        }
        s.leaveGroup(group);
        s.close();
    }


}
