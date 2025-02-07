package Tema

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Cadena 1");
        String cadena1 = sc.nextLine();
        System.out.println("Cadena 2");
        String cadena2 = sc.nextLine();

        int resultado = cadena1.compareTo(cadena2);
        if (resultado < 0) {
            System.out.println(cadena1 + " " + cadena2);
        } else if (resultado > 0) {
            System.out.println(cadena2 + " " + cadena1);
        } else {
            System.out.println(cadena1 + " " + cadena2 + ": son iguales");
        }

        int comprobar = 0;
        for (char c : cadena1.toCharArray()) {
            comprobar += (int) c;
        }
        System.out.println("Checksum of " + cadena1 + ": " + comprobar);
    }
}
