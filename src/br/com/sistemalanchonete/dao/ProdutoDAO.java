/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistemalanchonete.dao;

import br.com.sistemalanchonete.model.Produto;
import br.com.sistemalanchonete.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author joão pedro
 */
public class ProdutoDAO {
    private Connection con;
    private String sql;
    private PreparedStatement st;
    private ResultSet rs;
    
    public void inserir(Produto produto) throws Exception{
        con = ConnectionFactory.getConnection();
        
        sql = "INSERT INTO \"LANCHONETE\".\"PRODUTOS\"(\n" + 
        		"   \"Nome\", \"Valor\")\n" + 
        		"	VALUES ( ?, ?);";
        
        st = con.prepareStatement(sql);
        
        
        
        st.setString(1,produto.getNome());
        
        st.setFloat(2, produto.getValor());
        
        
 
        
        st.executeUpdate();
        
        con.close();
    }
    
    public void editar(Produto produto) throws Exception{
        con = ConnectionFactory.getConnection();
        
        sql = "UPDATE \"LANCHONETE\".\"PRODUTOS\"\n" + 
        		"	SET \"Nome\"=?, \"Valor\"=?\n" + 
        		"	WHERE \"Codigo_produto\"=?;";
        
        st = con.prepareStatement(sql);
        
        
        st.setString(1, produto.getNome());
        st.setFloat(2, produto.getValor());
        st.setInt(3, produto.getQuantidade());
        st.setInt(4, produto.getCodigo());
        
        st.executeUpdate();
        
        con.close();
    }
    
    public void remover(Produto produto) throws Exception{
        con = ConnectionFactory.getConnection();
        
        sql = "DELETE FROM \"LANCHONETE\".\"PRODUTOS\"\n" + 
        		"	WHERE \"Codigo_produto\"=?;";
        
        st = con.prepareStatement(sql);
        
        st.setInt(1, produto.getCodigo());
        
        st.executeUpdate();
        
        con.close();
    }
    
    public List<Produto> listar() throws Exception{
        List<Produto> produto = new ArrayList<>();
        con = ConnectionFactory.getConnection();
        sql = "select * from \"LANCHONETE\".\"PRODUTOS\"";
        st = con.prepareStatement(sql);
        ResultSet rs = st.executeQuery();
        while(rs.next()){
            int codigo = rs.getInt(1);
            String nome = rs.getString("Nome");
            float valor = rs.getFloat("Valor");
           
            
            
            Produto p = new Produto();
            
            p.setCodigo(codigo);
            p.setNome(nome);
            p.setValor(valor);
            
            
            
           produto.add(p);
        }
        con.close();
        return produto;
    }
    
    public Produto buscar(int codigo) throws Exception{
        Produto p = null;
        con = ConnectionFactory.getConnection();
        sql = "select * from \"LANCHONETE\".\"PRODUTOS\" where \"Codigo_produto\" = ?";
        st = con.prepareStatement(sql);
        st.setInt(1, codigo);
        ResultSet rs = st.executeQuery();
        if(rs.next()){
        	 String nome = rs.getString("Nome");
             float valor = rs.getFloat("Valor");
            
            p = new Produto();
            p.setCodigo(codigo);
            p.setNome(nome);
            p.setValor(valor);
          
        }
        con.close();
        return p;
    }
}
