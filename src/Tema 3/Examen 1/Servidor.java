package Tema

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    private static final int puerto = 12345;

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(puerto)) {
            System.out.println("Servidor iniciado");

            while (true) {
                Socket sck = server.accept();
                System.out.println("Cliente conectado");

                BufferedReader in = new BufferedReader(new InputStreamReader(sck.getInputStream()));
                PrintWriter out = new PrintWriter(sck.getOutputStream(), true);

                String operacion = in.readLine();
                String cadena1 = in.readLine();
                String cadena2 = in.readLine();
                System.out.println("Operación: " + operacion + ", cadena1: " + cadena1 + ", cadena2: " + cadena2);

                switch (operacion) {
                    case "comparar":
                        if (cadena1.length() >= 3 && cadena2.length() >= 3) {
                            compararCadenas(cadena1, cadena2, out);
                        } else {
                            out.println("Error: las cadenas deben tener al menos 3 caracteres");
                        }
                        break;
                    case "calculo":
                        if (cadena1.length() >= 2) {
                            sumaVerificacion(cadena1, out);
                        } else {
                            out.println("Error: la cadena debe tener al menos 2 caracteres");
                        }
                        break;
                    default:
                        out.println("Opción incorrecta");
                }

                sck.close();
                System.out.println("Cliente desconectado");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void compararCadenas(String cadena1, String cadena2, PrintWriter out) {
        int resultado = cadena1.compareTo(cadena2);
        if (resultado < 0) {
            out.println(cadena1 + " " + cadena2);
        } else if (resultado > 0) {
            out.println(cadena2 + " " + cadena1);
        } else {
            out.println(cadena1 + " " + cadena2 + ": son iguales");
        }
    }

    private static void sumaVerificacion(String cadena, PrintWriter out) {
        int comprobar = 0;
        for (char c : cadena.toCharArray()) {
            comprobar += (int) c;
        }
        out.println("Checksum of " + cadena + ": " + comprobar);
    }
}

