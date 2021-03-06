/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.escuelaing.arsw.http;
import edu.escuelaing.arsw.http.writer.ResourceChooser;
import edu.escuelaing.arsw.http.writer.ResourceWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
/**
 *
 * @author J. Eduardo Arias
 */
public class EchoServerHttp {
    /**
     * Devuelve los recursos solicitados por un socket cliente
     * @param args ninguno 
     * @throws IOException si algo ocurre con los sockets
     */
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;        
        try {
            serverSocket = new ServerSocket(35000);            
        } catch (IOException e) {
            System.err.println("Could not listen on port: 35000.");
            System.exit(1);
        }
        while(true){
            Socket clientSocket = null;
            ResourceWriter rw=null;
            try {
                System.out.println("Listo para recibir ...");
                clientSocket = serverSocket.accept();            
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                String path=getPath(in);    
                rw = ResourceChooser.choose(path); 
                rw.write(path, clientSocket);                    
                in.close();
                clientSocket.close();              
            } catch (IOException e) {
                System.err.println("Accept failed.");
                System.exit(1);
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }       
        }     
    }    
    
    /**
     * Captura el path de una peticion GET
     * @param in Buffer del Socket del Cliente
     * @return el path del archivo.
     * @throws IOException si no es posible leer el buffer
     */
    public static String getPath(BufferedReader in) throws IOException{
        String inputLine,path="";
        while ((inputLine = in.readLine()) != null) {
             System.out.println("Received: " + inputLine);
             if (inputLine.contains("GET")) {
                 path=inputLine.split(" ")[1];                                             
             }
             if (!in.ready()) {
                 break;
             }
        }
        return path;
    }
}
