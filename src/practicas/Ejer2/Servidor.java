package practicas.Ejer2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
// cliente envia mensaje al servidor y el servidor lo imprime y devuelve mensaje "x" recibido
public class Servidor {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(9744)) {
            while (true) {
                try (Socket socket = serverSocket.accept();
                     BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                     PrintWriter pw = new PrintWriter(socket.getOutputStream(), true)) {
                    String mensajeCliente = br.readLine();
                    System.out.println("Mensaje recibido: " + mensajeCliente);
                    pw.println("Mensaje recibido: " + mensajeCliente);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
