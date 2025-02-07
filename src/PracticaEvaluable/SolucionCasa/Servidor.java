package PracticaEvaluable.SolucionCasa;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

public class Servidor {
    static int puerto = 9797;

    static Socket cliente;
    static BufferedReader lector;
    static PrintWriter escritor;

    static void clienteConexion(){
        try {
            ServerSocket serverSocket = new ServerSocket(puerto);
            System.out.println("Servidor Iniciado");
            System.out.println("Esperando a que se conecte algún cliente");
            cliente = serverSocket.accept();
            System.out.println("Se ha conectado el cliente " + cliente.getInetAddress());
            lector = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            escritor = new PrintWriter(cliente.getOutputStream(), true);
        } catch (IOException e) {
            System.out.println("Error en la conexión: " + e.getMessage());
        }

    }

    public static void main(String[] args) throws Exception{
        clienteConexion();

        while (true) {
            try{
                int opcion = Integer.parseInt(lector.readLine());
                System.out.println("Opción leida: " + opcion + " de " + cliente.getInetAddress());

                switch (opcion) {
                    case 1:
                        String cadena1 = lector.readLine();
                        String cadena2 = lector.readLine();
                        ordenCadenas(cadena1, cadena2, escritor);
                        break;

                    case 2:
                        String cadenaLetras = lector.readLine();
                        sumasVerificacion(cadenaLetras, escritor);
                        break;

                    case 0:
                        System.out.println("Adios " + cliente.getInetAddress());
//                        clienteConexion();
                        break;
                }

        }catch(IOException e){
                System.out.println(e.getMessage());
                break;
            }
        }
    }

    static void ordenCadenas(String cadena1, String cadena2, PrintWriter escritor){
        String cadenas[] = {cadena1, cadena2};
        Arrays.sort(cadenas, String::compareToIgnoreCase);

        escritor.println("La primera palabra es " + cadenas[0] + " y la segunda " + cadenas[1]);

    }

    static void sumasVerificacion(String cadenaLetras, PrintWriter escritor){

        char letras[] = cadenaLetras.toCharArray();
        int resultado = 0;
        for (char letra : letras){
            resultado = resultado + (int) letra;
        }

        escritor.println("Resultado: " + resultado);
    }
}
