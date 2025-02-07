package Tema

import java.io.*;
import java.net.*;

public class Cliente {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.err.println("Uso: java Cliente <dirección IP> <número>");
            System.exit(1);
        }

        String direccion = args[0]; //Dirección IP del servidor
        int puerto = 12345; //Puerto
        int numero = Integer.parseInt(args[1]); //Número que se va a enviar

        try (Socket socket = new Socket(direccion, puerto);
             DataInputStream in = new DataInputStream(socket.getInputStream());
             DataOutputStream out = new DataOutputStream(socket.getOutputStream())) {

            //Enviar el número al servidor
            System.out.println("El ciente envía: " + numero);
            out.writeInt(numero);

            //Leer el resultado enviado por el servidor
            int cuadrado = in.readInt();
            System.out.println("El cuadrado de " + numero + " es: " + cuadrado);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
