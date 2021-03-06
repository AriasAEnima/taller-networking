/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.escuelaing.arsw.function;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author J. Eduardo Arias
 */
public class EchoServerFunction {
    // Define las funciones y permite asignarlas con un simple string    
    private static Map<String,TFunction> selector=new HashMap<String,TFunction>(){{
        put("cos",(a)->Math.cos(a));
        put("sen",(a)->Math.sin(a));
        put("tan",(a)->Math.tan(a));
        }
    };      
    // Define la forma de las funciones trigonometricas.s
    public interface TFunction{
        double operate(double n);
    }
    
    /**
     * Le responde a un socket de cliente operaciones sen, cos y tan 
     * de un numero ingresado
     * @param args ninguno
     * @throws IOException si algo ocurre con los sockets
     */
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(35000);
            System.out.println("Waiting for numbers");
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
        String inputLine, outputLine, option="cos";
        TFunction funcion=selector.get(option);
        while ((inputLine = in.readLine()) != null) {
            System.out.println("Mensaje: " + inputLine);
            if (inputLine.contains("fun:")){
                option=inputLine.split(":")[1];
                if (selector.containsKey(option)){
                    funcion=selector.get(option);
                    outputLine="Funcion aceptada";
                }else{
                    System.out.println("Opcion incorrecta");
                    outputLine="Funcion ignorada";
                }                
            }else{
                try{
                    outputLine=funcion.operate(Double.valueOf(inputLine))+"";
                }catch(NumberFormatException e){
                    outputLine="No es un numero";
                }
            }
            out.println(outputLine);
            if (outputLine.equals("Respuesta: Bye."))
                break;
        }
        out.close();
        in.close();
        clientSocket.close();
        serverSocket.close();
    }
    
   
}
