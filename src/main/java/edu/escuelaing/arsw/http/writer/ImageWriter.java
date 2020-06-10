/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.escuelaing.arsw.http.writer;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author J. Eduardo Arias
 */
public class ImageWriter implements ResourceWriter{
    private String type;
    
    public ImageWriter(String type) {
        this.type = type;
    }    
    
    /**
     * Escribe bits de una imagen utilizando el socket del cliente.
     * @param file path del archivo
     * @param clientSocket para responder
     */
    @Override
    public void write(String file,Socket clientSocket) {
         try {
            File graphicResource= new File("resources" +file);
            FileInputStream inputImage = new FileInputStream(graphicResource);
            byte[] bytes = new byte[(int) graphicResource.length()];
            inputImage.read(bytes);
            DataOutputStream binaryOut;
            binaryOut = new DataOutputStream(clientSocket.getOutputStream());
            binaryOut.writeBytes("HTTP/1.1 200 OK \r\n");
            binaryOut.writeBytes("Content-Type: image/"+type+"\r\n");
            binaryOut.writeBytes("Content-Length: " + bytes.length);
            binaryOut.writeBytes("\r\n\r\n");
            binaryOut.write(bytes);
            binaryOut.close();
        } catch (IOException ex){           
            System.err.println("Error en la lectura de el archivo");
        }
    }

    @Override
    public String exactType() {
        return "image/"+type;
    }

   
    
}
