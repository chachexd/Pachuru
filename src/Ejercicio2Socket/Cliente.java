package Ejercicio2Socket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class Cliente {
    void iniciarCliente(){
        try {

            while (true) {
                Socket socket = new Socket("localhost", 1234);
                BufferedReader bf = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String respuesta = bf.readLine();
                if(respuesta != null) {
                    System.out.println("Hora recibida del servidor: " + respuesta);
                }
                Thread.sleep(1000);
            }

//            bf.close();
//            socket.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
