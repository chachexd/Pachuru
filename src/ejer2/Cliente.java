package ejer2;

import java.io.*;
import java.net.Socket;

public class Cliente {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 9744);

        // Enviar un número al servidor
        BufferedWriter escritor = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        escritor.write("10\n");  // Número que se envía al servidor
        escritor.flush();

        // Leer la respuesta del servidor
        BufferedReader lector = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String respuesta = lector.readLine();
        System.out.println("Respuesta del servidor: " + respuesta);

        // Cerrar las conexiones
        lector.close();
        escritor.close();
        socket.close();
    }
}
