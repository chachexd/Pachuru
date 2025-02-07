package Tema

import java.io.*;
import java.net.*;

public class Cliente {
    public static void main(String[] args) {
        // Verificamos que se hayan pasado los 4 argumentos: IP, operación, número1, número2
        if (args.length < 4) {
            System.err.println("Uso: java Cliente <dirección IP> <operación> <número1> <número2>");
            System.exit(1);
        }

        // Dirección IP y puerto predeterminados
        String direccion = args[0]; // Dirección IP del servidor
        String operacion = args[1]; // Operación a realizar (suma, resta, multiplicación, división)
        double num1, num2;

        // Convertir los números recibidos como parámetros
        try {
            num1 = Double.parseDouble(args[2]);
            num2 = Double.parseDouble(args[3]);
        } catch (NumberFormatException e) {
            System.err.println("Los argumentos numéricos deben ser válidos.");
            System.exit(1);
            return;
        }

        try (Socket socket = new Socket(direccion, 12345);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

            // Enviar la operación y los números al servidor
            out.println(operacion);
            out.println(num1);
            out.println(num2);

            // Leer el resultado de la operación enviado por el servidor
            String resultado = in.readLine();
            System.out.println("Resultado: " + resultado);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
