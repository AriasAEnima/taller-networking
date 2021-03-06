/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.escuelaing.arsw.square;

/**
 *
 * @author J. Eduardo Arias
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServerSquare {
     /**
     * Le responde al socket del cliente el cuadrado de un numero ingresado
     * @param args ninguno
     * @throws IOException si no falla la utilizacion de sockets
     */
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(35000);
            System.out.println("Esperando ...");
        } catch (IOException e) {
            System.err.println("Could not listen on port: 35000.");
            System.exit(1);
        }
        Socket clientSocket = null;
        try {
            clientSocket = serverSocket.accept();
        } catch (IOException e) {
            System.err.println("Accept failed.");
            System.exit(1);
        }
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        String inputLine, outputLine;
        while ((inputLine = in.readLine()) != null) {
            if (inputLine.equals("Bye."))
                break;
            System.out.println("Mensaje: " + inputLine);
            outputLine = "Respuesta: " + String.valueOf(Math.pow(Integer.parseInt(inputLine),2));
            out.println(outputLine);            
        }
        out.close();
        in.close();
        clientSocket.close();
        serverSocket.close();
    }
}