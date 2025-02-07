package EjemploPropioChat;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Cliente extends Thread{
    static String IP = "localhost";
    static int puerto = 1234;
    static Socket socket;

    Cliente(Socket socket){
        this.socket = socket;
    }



    public static void main(String[] args) throws IOException {
        System.out.println("Conectando al servidor " + IP + ":" + puerto);
        try(Socket socket = new Socket(IP, puerto)) {

            Cliente hiloLector = new Cliente(socket);
            hiloLector.start();

            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            while(true){
                String mensajeEnviar = new Scanner(System.in).nextLine();
                writer.println(mensajeEnviar);
            }
        }




    }

    @Override
    public void run() {
        System.out.println("Hilo lector del Cliente iniciado");
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String mensaje;
        while ((mensaje = reader.readLine()) != null){
            System.out.println("Servidor: " + mensaje);
        }
    } catch (IOException e) {
        throw new RuntimeException(e);
    } catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
}
