package PracticaEvaluable;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class Servidor {
    static int puerto = 9797;

    public static void main(String[] args) throws Exception{
        ServerSocket serverSocket = new ServerSocket(puerto);
        System.out.println("Servidor Iniciado");
        System.out.println("Esperando a que se conecte algún cliente");

        while (true) {
            Socket cliente = serverSocket.accept();
            System.out.println("Se ha conectado el cliente " + cliente.getInetAddress());
            BufferedReader lector = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            PrintWriter escritor = new PrintWriter(cliente.getOutputStream(), true);

            int opcion = Integer.parseInt(lector.readLine());
            System.out.println("Opción leida: " + opcion + " de " + cliente.getInetAddress());
            String cadena1 = lector.readLine();
            String cadena2 = lector.readLine();

            switch (opcion){
                case 1:
                    ordenCadenas(cadena1, cadena2, escritor);
                    break;

                case 2:
                    sumasVerificacion(cadena1, escritor);
                    break;
            }


        }
    }

    static void ordenCadenas(String cadena1, String cadena2, PrintWriter escritor){
        String diccionario[] = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "ñ", "o", "p", "q", "r", "s", "t", "v", "w", "x", "y", "z"};
        char cadena1separada[] = cadena1.toCharArray();
        char cadena2separada[] = cadena2.toCharArray();

        String primeraPalabra = "";
        String segundaPalabra = "";

        for (int i = 0; i < diccionario.length; i++) {
            if (diccionario[i] == String.valueOf(cadena1separada[0])){

            }
        }

        escritor.println("La primera palabra es " + primeraPalabra + " y la segunda "+ segundaPalabra);

    }

    static void sumasVerificacion(String cadenaLetras, PrintWriter escritor){

    }
}
