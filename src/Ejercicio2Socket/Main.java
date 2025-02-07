package Ejercicio2Socket;

public class Main {
    public static void main(String[] args) {
//        Thread hiloServidor = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                Servidor servidor = new Servidor();
//                servidor.iniciarServidor();
//            }
//        });
//
//        hiloServidor.start();
//
//        try {
//            Thread.sleep(1000);
//        }catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        Cliente cliente = new Cliente();
        cliente.iniciarCliente();
    }
}
