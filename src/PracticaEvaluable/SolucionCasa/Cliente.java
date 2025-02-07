package PracticaEvaluable.SolucionCasa;

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

        boolean comprobador = true;

        while(comprobador){
            System.out.println("Introduce la opción a realizar");
            System.out.println("1 - Comparar cadenas");
            System.out.println("2 - Sumas de verificación");
            System.out.println("0 - Cerrar");
            int opcion = new Scanner(System.in).nextInt();
            escritor.println(opcion);

            if (opcion != 0){

                System.out.println("Introduce la primera cadena");
                String cadena1 = new Scanner(System.in).nextLine();
                escritor.println(cadena1);

                if (opcion != 2) {
                    System.out.println("Introduce la segunda cadena");
                    String cadena2 = new Scanner(System.in).nextLine();
                    escritor.println(cadena2);
                }

                String resultado;

                if ((resultado = lector.readLine()) != null) {
                    System.out.println(resultado);
                }

            } else {
                System.out.println("Desconectando");
                Thread.sleep(200);
                comprobador = false;
            }
        }
    }
}
