/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistemalanchonete.test;

import br.com.sistemalanchonete.util.ConnectionFactory;
import java.sql.SQLException;

/**
 *
 * @author joão pedro
 */
public class TesteConexao {

    public static void main(String[] args) throws SQLException {
        
        if(ConnectionFactory.getConnection() != null){
            System.out.println("ok!");
        }else{
            System.out.println("deu erro!");
        }
        
        
        
    }
}

