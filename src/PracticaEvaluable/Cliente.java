package PracticaEvaluable;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    static String IP = "localhost";
    static int puerto = 9797;

    public static void main(String[] args) throws Exception{
        Socket socket = new Socket(IP, puerto);
        System.out.println("Conectando a " + IP + ":" + puerto);

        PrintWriter escritor = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader lector = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        while(true){
            System.out.println("Introduce la opción a realizar");
            System.out.println("1 - Comparar cadenas");
            System.out.println("2 - Sumas de verificación");
            int opcion = new Scanner(System.in).nextInt();

            switch (opcion){
                case 1 :
                    System.out.println("Introduce la primera cadena");
                    String cadena1 = new Scanner(System.in).nextLine();

                    System.out.println("Introduce la segunda cadena");
                    String cadena2 = new Scanner(System.in).nextLine();

                    escritor.println(opcion);
                    escritor.println(cadena1);
                    escritor.println(cadena2);
                    break;
                case 2:
                    System.out.println("Introduce la cadena de letras");
                    String cadenaLetras = new Scanner(System.in).nextLine();
                    escritor.println(opcion);
                    escritor.println(cadenaLetras);
                    escritor.println("nada");
                    break;
            }
        }
    }
}
