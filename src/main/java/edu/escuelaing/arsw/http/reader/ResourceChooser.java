/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.escuelaing.arsw.http.reader;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author J. Eduardo Arias
 */
public class ResourceChooser {
    public static Map<String,ResourceWriter> selector=new HashMap<String,ResourceWriter>(){{
            put("html",new TextWriter("html"));
            put("png",new ImageWriter("png"));
            put("jpg",new ImageWriter("jpg"));
            put("js",new TextWriter("javascript"));
            put("css",new TextWriter("css"));
        }
    };      
    
    public static ResourceWriter choose(String path) throws Exception{
        String resource="";
        try{                   
            String[] s=path.split("\\.");    
            resource=s[s.length-1];            
        }catch(ArrayIndexOutOfBoundsException e){
            throw new Exception(" No es una peticion de Recurso Especifico/ Peticion mal formada");
        }        
        if (!selector.containsKey(resource)){
            throw new Exception("Recurso no soportado: " +resource);            
        }
        return selector.get(resource);        
    }
    
}
