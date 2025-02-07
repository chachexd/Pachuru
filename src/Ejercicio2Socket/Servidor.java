package Ejercicio2Socket;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class Servidor {
    void iniciarServidor(){
        try{
            ServerSocket serverSocket = new ServerSocket(1234);
            System.out.println("Servidor iniciado, esperando conexión...");

            while (true){
                Socket socket = serverSocket.accept();
                System.out.println("Cliente conectado: " + socket.getInetAddress());

                PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);

                Date now = new Date();

                pw.println(now.toString());

                pw.close();
                socket.close();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
