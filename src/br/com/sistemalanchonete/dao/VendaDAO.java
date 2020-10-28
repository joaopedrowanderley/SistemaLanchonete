/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistemalanchonete.dao;

import br.com.sistemalanchonete.model.Venda;
import br.com.sistemalanchonete.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author joão pedro
 */
public class VendaDAO {
  private Connection con;
    private String sql;
    private PreparedStatement st;
    private ResultSet rs;
    
    public void inserir(Venda venda) throws Exception{
        con = ConnectionFactory.getConnection();
        
        sql = "INSERT INTO \"LANCHONETE\".\"CLIENTES_COMPRA_PRODUTOS\"(\n" + 
        		"	 \"Data_compra\", \"Valor\", \"Codigo_cliente_CLIENTES\", \"Codigo_produto_PRODUTOS\")\n" + 
        		"	VALUES ( ?, (select \"Valor\" from \"LANCHONETE\".\"PRODUTOS\" \n" + 
        		"							  WHERE \"Codigo_produto\" = ?), ?, ?);";
        
        st = con.prepareStatement(sql);
        
    
        java.sql.Date dataSql = new java.sql.Date(venda.getData().getTime());
        st.setDate(1, dataSql);
        st.setFloat(2,venda.getCod_produto());
        st.setInt(3, venda.getCod_cliente());
        st.setInt(4, venda.getCod_produto());
        
        st.executeUpdate();
        
        con.close();
    }
    public void editar(Venda venda) throws Exception{
        con = ConnectionFactory.getConnection();
        
        sql = "UPDATE \"LANCHONETE\".\"CLIENTES_COMPRA_PRODUTOS\"\n" + 
        		"	SET \"Data_compra\"=?, \"Valor\"=?, \"Codigo_cliente_CLIENTES\"=?, \"Codigo_produto_PRODUTOS\"=?\n" + 
        		"	WHERE id=?;";
        
        st = con.prepareStatement(sql);
        
        java.sql.Date dataSql = new java.sql.Date(venda.getData().getTime());
        st.setDate(1, dataSql);
        st.setFloat(2,venda.getValor());
        st.setInt(3, venda.getCod_cliente());
        st.setInt(4, venda.getCod_produto());
        st.setInt(5, venda.getCodigo());
        
        st.executeUpdate();
        
        con.close();
    }
    public void remover(Venda venda) throws Exception{
        con = ConnectionFactory.getConnection();
        
        sql = "DELETE FROM \"LANCHONETE\".\"CLIENTES_COMPRA_PRODUTOS\"\n" + 
        		"	WHERE id=? ;";
        
        st = con.prepareStatement(sql);
        
        st.setInt(1, venda.getCodigo());
        
        st.executeUpdate();
        
        con.close();
    }
    public List<Venda> listar() throws Exception{
        List<Venda> venda = new ArrayList<>();
        con = ConnectionFactory.getConnection();
        sql = "SELECT *	FROM \"LANCHONETE\".\"CLIENTES_COMPRA_PRODUTOS\";";
        st = con.prepareStatement(sql);
        ResultSet rs = st.executeQuery();
        while(rs.next()){
            int codigo = rs.getInt(1);
            Date data = rs.getDate("Data_compra");
            float valor = rs.getFloat("Valor");
            int cod_produto = rs.getInt("Codigo_produto_PRODUTOS");
            int cod_cliente = rs.getInt("Codigo_cliente_CLIENTES");
            
            
            Venda v = new Venda();
            
            v.setCodigo(codigo);
            v.setValor(valor);
            v.setData((java.sql.Date) data);
            v.setCod_produto(cod_produto);
            v.setCod_cliente(cod_cliente);
            
           venda.add(v);
        }
        con.close();
        return venda;
    }
    
        public Venda buscar(int codigo) throws Exception{
        Venda v = null;
        con = ConnectionFactory.getConnection();
        sql = "SELECT *	FROM \"LANCHONETE\".\"CLIENTES_COMPRA_PRODUTOS\" where id=?;";
        st = con.prepareStatement(sql);
        st.setInt(1, codigo);
        ResultSet rs = st.executeQuery();
        if(rs.next()){
            float valor = rs.getFloat("Valor");
            Date data = rs.getDate("Data_compra");
            int cod_produto = rs.getInt("Codigo_produto_PRODUTOS");
            int cod_cliente = rs.getInt("Codigo_cliente_CLIENTES");

            v.setValor(valor);
            v.setData((java.sql.Date) data);
            v.setCod_produto(cod_produto);
            v.setCod_cliente(cod_cliente);
        }
        con.close();
        return v;
    }   
}
