package Tema

import java.net.*;
import java.io.*;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Servidor extends Observable implements Runnable {
    private int puerto;

    public Servidor(int puerto) {
        this.puerto = puerto;
    }

    @Override
    public void run() {
        ServerSocket server = null;
        Socket sock = null;
        DataInputStream in = null;

        try {
            server = new ServerSocket(puerto);
            System.out.println("Servidor iniciado");

            while (true) {
                sock = server.accept();

                System.out.println("Cliente conectado");
                in = new DataInputStream(sock.getInputStream());

                String mensaje = in.readUTF();

                System.out.println(mensaje);

                this.setChanged();
                this.notifyObservers(mensaje);
                this.clearChanged();

                sock.close();
                System.out.println("Cliente desconectado");
            }

        } catch (IOException e) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}