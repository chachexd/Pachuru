package Ejercicio2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Cliente {
    public static void main(String[] args) {
        String direccion = "localhost";
        int puerto = 12345;

        try {
            Socket sck = new Socket(direccion, puerto);

            BufferedReader reader = new BufferedReader(new InputStreamReader(sck.getInputStream()));

            String mensaje = reader.readLine();
            System.out.println("Mensaje del servidor: " + mensaje);

            sck.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}


