package DiegoCuestaDiaz_PracticaEvaluable;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 9744);
        BufferedReader lector = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedWriter escritor = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        String cadena2 = "hola\n";
        String cadena1 = "mundo\n";
        String respuesta;
        boolean abierto = true;
        Scanner sc = new Scanner(System.in);

        while (abierto) {
            System.out.println("-----Menu-----");
            System.out.println("1. Mostrar Hola Mundo");
            System.out.println("2. Hacer suma ASCII");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");
            String opcion = sc.nextLine();

            switch (opcion) {
                case "1":
                    escritor.write(cadena1);
                    escritor.write(cadena2);
                    escritor.write("1\n");
                    escritor.flush();
                    respuesta = lector.readLine();
                    System.out.println("Respuesta del servidor: " + respuesta);
                    break;
                case "2":
                    escritor.write(cadena1);
                    escritor.write(cadena2);
                    escritor.write("2\n");
                    escritor.flush();
                    respuesta = lector.readLine();
                    System.out.println("Respuesta del servidor: " + respuesta);
                    break;
                case "3":
                    escritor.write("salir\n");
                    escritor.write("salir\n");
                    escritor.write("3\n");
                    escritor.flush();
                    abierto = false;
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }

        lector.close();
        escritor.close();
        socket.close();
    }
}