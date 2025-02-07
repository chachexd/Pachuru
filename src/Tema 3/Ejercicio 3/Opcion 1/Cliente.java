package Tema

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Cliente {
    public static void main(String[] args) {
        String direccion = "localhost";
        int puerto = 12345;

        try {
            //Creamos un socket para conectar con el servidor
            Socket sck = new Socket(direccion, puerto);

            //Creamos un BufferedReader para leer la información del servidor
            BufferedReader reader = new BufferedReader(new InputStreamReader(sck.getInputStream()));


            //Leemos el número del cliente y lo mandamos al servidor con imputStream
            PrintWriter out = new PrintWriter(sck.getOutputStream(), true);
            BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Ingresa un número: ");
            String numero = teclado.readLine();
            out.println(numero);


            //Leemos la información del servidor y la mostramos en pantalla
            String mensaje;
            while ((mensaje = reader.readLine()) != null) {
                System.out.println("Servidor: " + mensaje);
            }

            //Cerramos los BufferedReader para terminar la comunicación
            teclado.close();
            reader.close();

            //Cerramos el PrintWriter para terminar la comunicación
            out.close();
            sck.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

