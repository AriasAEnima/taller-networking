/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.escuelaing.arsw.networking;

import java.net.MalformedURLException;
import java.net.URL;

/**
 *
 * @author J. Eduardo Arias
 */
public class URLManager {
    public static void main(String[] args){

        try {
            URL personalSite = new URL("http://ldbn.escuelaing.edu.co:80/");
            System.out.println("Protocol "+personalSite.getProtocol());
            System.out.println("Authority "+personalSite.getAuthority());
            System.out.println("Host "+personalSite.getHost());
            System.out.println("Port "+personalSite.getPort());
            System.out.println("Path "+personalSite.getPath());
            System.out.println("Query "+personalSite.getQuery());
            System.out.println("File "+personalSite.getFile());
            System.out.println("Ref "+personalSite.getRef());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
