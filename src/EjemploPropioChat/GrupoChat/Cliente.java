package EjemploPropioChat.GrupoChat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class Cliente extends JFrame{

    static MulticastSocket msEmisor;
    static InetAddress iaEmisor;
    static DatagramPacket dpEmisor;
    static BufferedReader brEmisor;

    static String salida;

    static JTextArea chat;
    static JTextField mensaje;
    static JButton botonEnviar;

    static JTextField campoNombreUsuario;
    static JButton botonAceptarUsuario;

    static String nombreUsuario;

   public Cliente() {
       setLayout(new BorderLayout());
       setSize(300, 600);

       chat = new JTextArea();
       chat.setEditable(false);
       JScrollPane scrollPane = new JScrollPane(chat);
       add(scrollPane, BorderLayout.CENTER);

       JPanel panelMensajes = new JPanel(new BorderLayout());
       mensaje = new JTextField();
       mensaje.setPreferredSize(new Dimension(0, 30)); // Altura fija para la barra de mensaje
       botonEnviar = new JButton("Enviar");
       botonEnviar.setPreferredSize(new Dimension(100, 30)); // Tamaño fijo para el botón de enviar
       panelMensajes.add(mensaje, BorderLayout.CENTER);
       panelMensajes.add(botonEnviar, BorderLayout.EAST);
       add(panelMensajes, BorderLayout.SOUTH);
       setDefaultCloseOperation(EXIT_ON_CLOSE);
   }
    public static void main(String[] args) throws Exception {
        ventanaNombreUsuario();
    }


    static void ventanaNombreUsuario() {
        JFrame jFrame = new JFrame("Nombre de Usuario");
        jFrame.setSize(100, 20);
        jFrame.setLayout(new GridLayout(2, 1));
        campoNombreUsuario = new JTextField();
        botonAceptarUsuario = new JButton("Aceptar");
        jFrame.add(campoNombreUsuario);
        jFrame.add(botonAceptarUsuario);
        jFrame.pack();
        jFrame.setLocationRelativeTo(null); // Centrar la ventana
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Cerrar la aplicación al cerrar la ventana

        botonAceptarUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!campoNombreUsuario.getText().isEmpty()) {
                    nombreUsuario = campoNombreUsuario.getText();
                    jFrame.dispose(); // Cerrar la ventana de nombre de usuario
                    try {
                        arrancarChat();
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(jFrame, "El nombre de usuario no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    static void arrancarChat() throws InterruptedException {
        Cliente cliente = new Cliente();
        cliente.setVisible(true);
        hiloReceptor();
        Thread.sleep(100);
        hiloEmisor();
    }

    static void hiloEmisor(){
        new Thread(() -> {
            System.out.println("Hilo emisor");
            try {
                brEmisor = new BufferedReader(new InputStreamReader(System.in));
                msEmisor = new MulticastSocket(10000);
                iaEmisor = InetAddress.getByName("231.0.0.1");
                dpEmisor = new DatagramPacket(new byte[0], 0, iaEmisor, 10000);
                botonEnviar.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String leido = nombreUsuario + ": " + mensaje.getText();
                        byte[] buffer = leido.getBytes();
                        dpEmisor.setData(buffer);
                        dpEmisor.setLength(buffer.length);
                        try {
                            msEmisor.send(dpEmisor);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                });


            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }

    static void hiloReceptor(){
       new Thread(() -> {
               System.out.println("Hilo receptor");

           try {
               MulticastSocket multicastSocket = new MulticastSocket(10000);
               InetAddress inetAddress = InetAddress.getByName("231.0.0.1");
               multicastSocket.joinGroup(inetAddress);
               salida = new String();

               while (!salida.equals("salir")){
                   byte[] buffer = new byte[256];
                   DatagramPacket datagramPacketReceptor = new DatagramPacket(buffer, buffer.length);
                   multicastSocket.receive(datagramPacketReceptor);
                   byte[] buffer2 = new byte[datagramPacketReceptor.getLength()];
                   System.arraycopy(datagramPacketReceptor.getData(), 0, buffer2, 0, datagramPacketReceptor.getLength());
                   salida = new String(buffer2);
                   System.out.println(datagramPacketReceptor.getAddress() + " - " + salida);
                   chat.append(datagramPacketReceptor.getAddress() + " - " + salida + "\n");
               }
               multicastSocket.leaveGroup(inetAddress);
               multicastSocket.close();
           } catch (IOException e) {
               throw new RuntimeException(e);
           }
       }).start();
    }
}
