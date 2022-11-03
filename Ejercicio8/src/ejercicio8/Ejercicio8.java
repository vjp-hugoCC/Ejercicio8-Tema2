/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio8;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author hcalvoc01
 */
public class Ejercicio8 {

    /**
     * @param args the command line arguments
     */
    public static boolean comprobar(String user, String password) {
        String linea;
        String[] lineas;
        File fich = new File("users.txt");
        FileReader fr = null;
        BufferedReader br = null;
        boolean userExists = false;
        try {
            fr = new FileReader(fich);
            br = new BufferedReader(fr);
            linea = br.readLine();
            while ((linea != null) && !userExists) {
                lineas = linea.split(":");
                if (lineas[0].equals(user) && lineas[1].equals(password)) {
                    userExists = true;
                }
                linea = br.readLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Fichero no encontrado");
        } catch (IOException e) {
            System.out.println("Error en la E/S");
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
            if (fr != null) {
                try {
                    fr.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }

        }
        return userExists;
    }

    public static boolean escribirUsuario(String user, String password) {
        FileWriter fw = null;
        PrintWriter pw = null;
        boolean existeUser;
        boolean introducido = false;
        existeUser = comprobar(user, password);
        if (existeUser) {
            introducido = false;
        } else {
            try {
                fw = new FileWriter("users.txt",true);
                pw = new PrintWriter(fw);
                pw.println(user + ":" + password);
                introducido=true;
            } catch (IOException e) {
                System.out.println("Error de entrada/salida");
                System.out.println(e.getMessage());
            } finally {
                if (pw != null) {
                    pw.close();
                }
                if (fw != null) {
                    try {
                        fw.close();
                    } catch (IOException e) {
                        System.out.println("Error de entrada/salida");
                        System.out.println(e.getMessage());
                    }
                }
            }
        }
        return introducido;
    }
}
