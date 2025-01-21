package ejer4;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;

// El servidor va a recibir un número enviado por el cliente, lo va a recoger y lo va a imprimir.
public class Servidor {
    public static void main(String[] args) {
        System.out.println("Servidor en ejecución...");

        int puerto = 9744; // Puerto por defecto

        try (ServerSocket serverSocket = new ServerSocket(puerto)) {
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Cliente conectado");

                // Leer el número enviado por el cliente
                BufferedReader lectura = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                int numeroRecibido = Integer.parseInt(lectura.readLine());  // Leer el número como String
                int numeroRecibido2 = Integer.parseInt(lectura.readLine());  // Leer el número como String

                //int numeroRecibido = Integer.parseInt(args[0]);


                System.out.println("Número recibido del cliente: " + numeroRecibido);

                // Enviar una respuesta con la hora actual al cliente
                BufferedWriter escritura = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                String horaActual = String.valueOf(LocalDateTime.now());
                escritura.write("Mi numero que ha recibido el servidor es multiplicado: numero1:"+numeroRecibido+" numero2: "+numeroRecibido2+" : "+ numeroRecibido*numeroRecibido2);
                escritura.flush();  // Asegurarse de que se envía el mensaje

                // Cerrar las conexiones
                lectura.close();
                escritura.close();
                socket.close();
                System.out.println("Cliente desconectado");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

