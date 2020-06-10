/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.escuelaing.arsw.networking;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

/**
 *
 * @author J. Eduardo Arias
 */
public class Browser {
    /**
     * Guarda el html de una URL en un archivo resultado.html, si no le ingresan
     * parametros lee la de google.com
     * @param args Url de la pagina
     * @throws Exception si ocurre un error con los sockets
     */
    public static void main(String[] args) throws Exception {
        URL google;       
        if (args.length==0 || args[0]==""){
           google = new URL("http://www.google.com/");
        }else{
            google = new URL(args[0]);
        }       
        try (BufferedReader reader
                = new BufferedReader(new InputStreamReader(google.openStream()))) {
            String inputLine = null;
            FileWriter myWriter = new FileWriter("resultado.html");
            while ((inputLine = reader.readLine()) != null) {
                myWriter.write(inputLine);
                //System.out.println(inputLine);
            }
             myWriter.close();
        } catch (IOException x) {
            System.err.println(x);
        }
    }

}
