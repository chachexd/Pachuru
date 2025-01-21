package ejer1;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;

public class Servidor {
    public static void main(String[] args) {
        try(ServerSocket serverSocket = new ServerSocket(9744)){
            while (true){
                Socket socket = serverSocket.accept();
                System.out.println("ejer1.Cliente conectado");
                PrintWriter pw = new PrintWriter(socket.getOutputStream());
                String horaActual = String.valueOf(LocalDateTime.now());
//                pw.println("la hora actual es "+ horaActual);
                pw.println("Anuel Brr");
                pw.close();
                socket.close();
                System.out.println("ejer1.Cliente desconectado");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
