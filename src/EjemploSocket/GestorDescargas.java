package EjemploSocket;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class GestorDescargas {
    
    public static void descargarArchivos(String url_descargar){
        try {
            URL urlDescarga = new URL(url_descargar);

            InputStream is = urlDescarga.openStream();

            InputStreamReader reader = new InputStreamReader(is);

            BufferedReader breader = new BufferedReader(reader);

            FileWriter escritorFichero = new FileWriter("socketDeInternet.html");

            String linea;

            while((linea = breader.readLine())!=null){
                escritorFichero.write(linea + "\n");

            }

            escritorFichero.close();

            breader.close();

            reader.close();

            is.close();

        }catch (MalformedURLException e){
            System.out.println("Error URL mal escrita: " + e.getMessage());

        } catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        GestorDescargas gd = new GestorDescargas();
        String url = "https://es.wikipedia.org/wiki/Socket_de_Internet";

        descargarArchivos(url);

    }
}
