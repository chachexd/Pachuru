package EXAMEN_CRIPTO.Practica1;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduce el texto a codificar: ");
        String input = scanner.nextLine();

        // Algoritmos hash a utilizar
        String[] algoritmo= {"MD5", "SHA-1", "SHA-256", "SHA-512"};

        for (String algoritmos : algoritmo) {
            try {
                // Obtener instancia del algoritmo
                MessageDigest md = MessageDigest.getInstance(algoritmos);
                // Calcular el hash
                byte[] hashBytes = md.digest(input.getBytes());
                // Convertir el hash a formato hexadecimal
                StringBuilder sb = new StringBuilder();
                for (byte b : hashBytes) {
                    sb.append(String.format("%02x", b));
                }
                // Mostrar el hash codificado
                System.out.println(algoritmos + " hash: " + sb.toString());
                // Mostrar la longitud del hash
                System.out.println("Longitud del hash: " + hashBytes.length * 8 + " bits");
            } catch (NoSuchAlgorithmException e) {
                System.err.println("Algoritmo no soportado: " + algoritmo);
            }
        }
    }
}


/**
 * Explicación

 * La longitud del hash no tiene relación con la longitud del texto inicial.
 * La longitud del hash es fija y depende del algoritmo utilizado.
 * Los diferentes hash están diseñados para ser únicos y de longitud fija, independientemente del tamaño del texto de entrada.
 */