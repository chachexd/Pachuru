package Ejercicio1;

import java.net.*;
import java.io.*;

public class Servidor {
    public static void main(String[] args) {
        String url_ruta = "https://es.wikipedia.org/wiki/Camelus";
        descargarArchivo(url_ruta);
    }

    public static void descargarArchivo(String url_ruta) {
        System.out.println("Descargando: " + url_ruta);

        try {
            URL urlDescarga = new URL(url_ruta);
            InputStream is = urlDescarga.openStream();

            InputStreamReader reader = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(reader);

            FileWriter escritorioFichero = new FileWriter("resultado.html");

            String linea;
            while ((linea = br.readLine())!= null) {
                escritorioFichero.write(linea);
            }

            escritorioFichero.close();
            reader.close();
            br.close();
            is.close();
        } catch (MalformedURLException e) {
            System.out.println();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}