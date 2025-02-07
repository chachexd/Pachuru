package Ejercicio3ServidorQueHaceCalculos;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    static int puerto = 9797;

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(puerto);
        System.out.println("Servidor Iniciado");
        System.out.println("Esperando a que se conecte algún cliente");

        while (true) {
            Socket cliente = serverSocket.accept();
            System.out.println("Se ha conectado el cliente " + cliente.getInetAddress());

            // Manejo de la conexión
            BufferedReader lector = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            PrintWriter escritor = new PrintWriter(cliente.getOutputStream(), true);

            try {
                // Leer números del cliente
                double numero1 = Double.parseDouble(lector.readLine());
                System.out.println("Primer número leido: " + numero1 + " de " + cliente.getInetAddress());
                double numero2 = Double.parseDouble(lector.readLine());
                System.out.println("Segundo número leido: " + numero2 + " de " + cliente.getInetAddress());
                // Realizar cálculo
                double resultado = realizarCalculo(numero1, numero2);
                System.out.println("Resultado de " + cliente.getInetAddress() + ": " + resultado);
                // Enviar resultado al cliente
                System.out.println("Enviando resultado a " + cliente.getInetAddress() + "...");
                escritor.println(resultado);
            } catch (NumberFormatException e) {
                escritor.println("Error: Entrada inválida");
            }

            cliente.close();
        }
    }

    static double realizarCalculo(double n1, double n2) {
        return n1 + n2; // Realiza la operación
    }
}

