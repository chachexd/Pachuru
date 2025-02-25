package EXAMEN_CRIPTO.Practica2;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Main {
    private static final String ALGORITMO = "DES";
    private static final String LLAVE_SECRETA = "LLAVE";
    private static final String ARCHIVO_CIFRADO = "C:\\Users\\fjjpo\\IdeaProjects\\Pachuru\\src\\EXAMEN_CRIPTO\\Practica2\\TextoCifrado.txt";
    private static final String ARCHIVO_DESCIFRADO = "C:\\Users\\fjjpo\\IdeaProjects\\Pachuru\\src\\EXAMEN_CRIPTO\\Practica2\\TextoDescifrado.txt";
    private static final String ARCHIVO_TEXTO = "C:\\Users\\fjjpo\\IdeaProjects\\Pachuru\\src\\EXAMEN_CRIPTO\\Practica2\\TextoOriginal.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("1. Cifrar");
            System.out.println("2. Descifrar");
            System.out.println("0. Salir");
            System.out.print("Opción: ");
             opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    try {
                        SecretKey key = generarLlave();
                        guardarLlave(key, LLAVE_SECRETA);
                        procesarArchivoTXT(Cipher.ENCRYPT_MODE, key, ARCHIVO_TEXTO, ARCHIVO_CIFRADO);
                        System.out.println("Archivo cifrado correctamente.");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 2:

                    try {
                        SecretKey key = cargarLlave(LLAVE_SECRETA);
                        procesarArchivoTXT(Cipher.DECRYPT_MODE, key, ARCHIVO_CIFRADO, ARCHIVO_DESCIFRADO);
                        System.out.println("Archivo descifrado correctamente.");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida");
            }
        } while (opcion != 0);
    }

    private static SecretKey generarLlave() throws NoSuchAlgorithmException {
        KeyGenerator keyGen = KeyGenerator.getInstance(ALGORITMO);
        keyGen.init(56);
        return keyGen.generateKey();
    }

    private static void guardarLlave(SecretKey key, String fileName) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(fileName)) {
            fos.write(key.getEncoded());
        }
    }

    private static SecretKey cargarLlave(String fileName) throws IOException {
        byte[] keyBytes = new byte[8];
        try (FileInputStream fis = new FileInputStream(fileName)) {
            fis.read(keyBytes);
        }
        return new SecretKeySpec(keyBytes, ALGORITMO);
    }

    private static void procesarArchivoTXT(int cipherMode, SecretKey key, String inputFile, String outputFile) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITMO);
        cipher.init(cipherMode, key);

        try (FileInputStream fis = new FileInputStream(inputFile);
             FileOutputStream fos = new FileOutputStream(outputFile)) {
            byte[] inputBuffer = new byte[64];
            int bytesRead;

            while ((bytesRead = fis.read(inputBuffer)) != -1) {
                byte[] outputBuffer = cipher.update(inputBuffer, 0, bytesRead);
                if (outputBuffer != null) {
                    fos.write(outputBuffer);
                }
            }

            byte[] outputBuffer = cipher.doFinal();
            if (outputBuffer != null) {
                fos.write(outputBuffer);
            }
        }
    }
}