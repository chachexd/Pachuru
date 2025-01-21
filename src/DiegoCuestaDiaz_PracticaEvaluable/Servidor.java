package DiegoCuestaDiaz_PracticaEvaluable;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public static void main(String[] args) {
        System.out.println("Servidor en ejecución...");

        int puerto = 9744; // Puerto por defecto

        try (ServerSocket serverSocket = new ServerSocket(puerto)) {
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Cliente conectado");

                try (BufferedReader lectura = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                     BufferedWriter escritura = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))) {

                    boolean abierto = true;
                    while (abierto) {
                        String cadenaRecibida1 = lectura.readLine();
                        String cadenaRecibida2 = lectura.readLine();
                        int numeroRecibido = Integer.parseInt(lectura.readLine());

                        switch (numeroRecibido) {
                            case 1:
                                if (cadenaRecibida1.compareTo(cadenaRecibida2) < 0) {
                                    escritura.write(cadenaRecibida1 + " " + cadenaRecibida2 + "\n");
                                } else {
                                    escritura.write(cadenaRecibida2 + " " + cadenaRecibida1 + "\n");
                                }
                                break;
                            case 2:
                                int suma = 0;
                                for (char c : (cadenaRecibida1 + cadenaRecibida2).toCharArray()) {
                                    suma += c;
                                }
                                escritura.write("Suma ASCII: " + suma + "\n");
                                break;
                            case 3:
                                escritura.write("Conexión cerrada\n");
                                abierto = false;
                                break;
                            default:
                                escritura.write("Opción no válida\n");
                        }

                        escritura.flush();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                socket.close();
                System.out.println("Cliente desconectado");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}