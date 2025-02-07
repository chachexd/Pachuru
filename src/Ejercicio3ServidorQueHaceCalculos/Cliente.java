package Ejercicio3ServidorQueHaceCalculos;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    static String host = "localhost"; // Dirección del servidor
    static int puerto = 9797;         // Puerto del servidor

    public static void main(String[] args) {
        try {
            // Conexión al servidor
            Socket socket = new Socket(host, puerto);
            System.out.println("Conectado al servidor en " + host + ":" + puerto);

            // Flujos de entrada y salida
            PrintWriter escritor = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader lector = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Lectura de números del usuario
            Scanner scanner = new Scanner(System.in);
            System.out.print("Introduce el primer número: ");
            double numero1 = scanner.nextDouble();
            escritor.println(numero1);

            System.out.print("Introduce el segundo número: ");
            double numero2 = scanner.nextDouble();
            escritor.println(numero2);

            // Enviar números al servidor

            // Recibir resultado del servidor
            String resultado = lector.readLine();
            System.out.println("El resultado de la operación es: " + resultado);

            // Cerrar la conexión
            socket.close();
        } catch (IOException e) {
            System.out.println("Error de conexión: " + e.getMessage());
        }
    }
}

