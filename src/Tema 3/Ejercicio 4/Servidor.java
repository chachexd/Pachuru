package Tema

import java.io.*;
import java.net.*;

public class Servidor {
    public static void main(String[] args) {
        int puerto = 12345;

        try (ServerSocket serverSocket = new ServerSocket(puerto)) {
            System.out.println("Servidor esperando conexiones en el puerto " + puerto);

            while (true) {
                // Se acepta la conexión del cliente
                Socket socket = serverSocket.accept();
                System.out.println("Cliente conectado.");

                try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                     PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

                    // Leer la operación y los números enviados por el cliente
                    String operacion = in.readLine();
                    double num1 = Double.parseDouble(in.readLine());
                    double num2 = Double.parseDouble(in.readLine());

                    // Realizar la operación correspondiente
                    double resultado = 0;
                    String mensajeResultado = "";

                    switch (operacion.toLowerCase()) {
                        case "s":
                            resultado = num1 + num2;
                            mensajeResultado = "La suma de " + num1 + " y " + num2 + " es: " + resultado;
                            break;
                        case "r":
                            resultado = num1 - num2;
                            mensajeResultado = "La resta de " + num1 + " y " + num2 + " es: " + resultado;
                            break;
                        case "m":
                            resultado = num1 * num2;
                            mensajeResultado = "La multiplicación de " + num1 + " y " + num2 + " es: " + resultado;
                            break;
                        case "d":
                            if (num2 == 0) {
                                mensajeResultado = "Error: División por cero.";
                            } else {
                                resultado = num1 / num2;
                                mensajeResultado = "La división de " + num1 + " entre " + num2 + " es: " + resultado;
                            }
                            break;
                        default:
                            mensajeResultado = "Error: Operación no reconocida.";
                            break;
                    }

                    // Enviar el resultado al cliente
                    out.println(mensajeResultado);
                    System.out.println("Resultado enviado: " + mensajeResultado);
                } catch (IOException e) {
                    System.err.println("Error al procesar la comunicación con el cliente: " + e.getMessage());
                } finally {
                    socket.close();
                    System.out.println("Cliente desconectado.");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
