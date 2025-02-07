package Ejercicio2Socket.EjemploProfe;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClienteHora {
    public static void main(String[] args) throws IOException {
        Socket s = new Socket("localhost", 9797);
        InputStream entrada = s.getInputStream();
        BufferedReader lector = new BufferedReader(new InputStreamReader(entrada));
        System.out.println("La hora que me ha dado el servidor: " + lector.readLine());
        lector.close();
        s.close();
    }
}
