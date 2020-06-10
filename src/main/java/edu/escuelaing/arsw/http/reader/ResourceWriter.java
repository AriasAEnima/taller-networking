/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.escuelaing.arsw.http.reader;

import java.io.BufferedReader;
import java.net.Socket;

/**
 *
 * @author J. Eduardo Arias
 */
public interface ResourceWriter {
    public void write(String file,Socket clientSocket);
    public String exactType();
}
