/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistemalanchonete.util;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author joão pedro
 */
public class ConnectionFactory {
    //private static final String URL = "jdbc:postgresql://localhost:5432/sistema_financeiro";
    private static final String URL = "jdbc:postgresql://localhost:5432/lanchonete";
    private static final String USUARIO = "postgres";
    private static final String SENHA = "5432";
    
    public static Connection getConnection(){
        try{
            return DriverManager.getConnection(URL, USUARIO, SENHA);
        }catch(Exception e){
            System.out.println("Erro: " + e.getMessage());
            return null;
        }
    }
}
