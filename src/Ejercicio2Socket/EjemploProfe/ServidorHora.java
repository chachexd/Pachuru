package Ejercicio2Socket.EjemploProfe;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DateFormat;
import java.util.Date;

public class ServidorHora {
    static Date date = new Date();
    static int puerto = 9797;
    private static String darHora(){
        DateFormat hora = DateFormat.getTimeInstance(DateFormat.SHORT);
        return hora.format(date);
    }

    public static void main(String[] args) throws IOException {
        ServerSocket servidor = new ServerSocket(puerto);
        System.out.println("Servidor esperando que se conecte algún cliente...");
        while (true){
            Socket cliente = servidor.accept();
            System.out.println("Se ha conectado el cliente " + cliente.getInetAddress() + " y me ha pedido la hora");
            PrintWriter escritor = new PrintWriter(cliente.getOutputStream());
            escritor.println(darHora() + " (Se vende Ford Focus ST 2007 175.000km 4000€. Más info -> Pega una voz y pregunta por Fausto)");
            escritor.close();
            cliente.close();
        }
    }
}
