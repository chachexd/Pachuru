package practicas.Ejer2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
// cliente envia y recibe mensaje del servidor y lo imprime
public class Cliente {
    public static void main(String[] args) throws IOException {
        try(Socket socket = new Socket("localhost", 9744)) {
            PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            pw.println("aviones");
            System.out.println(br.readLine());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
