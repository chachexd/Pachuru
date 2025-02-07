package Ejercicio3ServidorQueHaceCalculos.EjemploProfe;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(9797)){
            while (true){
                Socket socket = serverSocket.accept();
                BufferedReader lectura = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter escritura = new PrintWriter(new ObjectOutputStream(socket.getOutputStream()));

                System.out.println("Cliente conectado");

                String mensaje = lectura.readLine();
                int numeroRecoger = Integer.parseInt(mensaje);
                System.out.println("Se ha recogido el numero " + numeroRecoger);

                int cuadrado = numeroRecoger + numeroRecoger;
                escritura.println(cuadrado);
                escritura.flush();

                socket.close();
                escritura.close();
                lectura.close();
                System.out.println("Cliente desconectado");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
