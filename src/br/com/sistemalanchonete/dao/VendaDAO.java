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
        
        sql = "insert into CLIENTES_COMPRA_PRODUTOS ( Valor, Data_compra,Codigo_cliente_CLIENTES, Codigo_produto_PRODUTOS) values (?,?,?,?)";
        
        st = con.prepareStatement(sql);
        
    
        st.setFloat(1,venda.getValor());
        java.sql.Date dataSql = new java.sql.Date(venda.getData().getTime());
        st.setDate(2, dataSql);
        st.setInt(3, venda.getCod_cliente());
        st.setInt(4, venda.getCod_produto());
        
        st.executeUpdate();
        
        con.close();
    }
    public void editar(Venda venda) throws Exception{
        con = ConnectionFactory.getConnection();
        
        sql = "insert into CLIENTES_COMPRA_PRODUTOS ( Valor, Data_compra,Codigo_cliente_CLIENTES, Codigo_produto_PRODUTOS) values (?,?,?,?)";
        
        st = con.prepareStatement(sql);
        
    
        st.setFloat(1,venda.getValor());
        java.sql.Date dataSql = new java.sql.Date(venda.getData().getTime());
        st.setDate(2, dataSql);
        st.setInt(3, venda.getCod_cliente());
        st.setInt(4, venda.getCod_produto());
        
        st.executeUpdate();
        
        con.close();
    }
    public void remover(Entradas entrada) throws Exception{
        con = ConnectionFactory.getConnection();
        
        sql = "delete from entradas where codigo = ?";
        
        st = con.prepareStatement(sql);
        
        st.setInt(1, entrada.getCodigo());
        
        st.executeUpdate();
        
        con.close();
    }
    public List<Entradas> listar() throws Exception{
        List<Entradas> entradas = new ArrayList<>();
        con = ConnectionFactory.getConnection();
        sql = "select * from entradas";
        st = con.prepareStatement(sql);
        ResultSet rs = st.executeQuery();
        while(rs.next()){
            int codigo = rs.getInt(1);
            Date data = rs.getDate("data");
            float valor = rs.getFloat("valor");
            
            
            Entradas e = new Entradas();
            
            e.setCodigo(codigo);
            e.setValor(valor);
            e.setData(data);
            
            
           entradas.add(e);
        }
        con.close();
        return entradas;
    }
    
        public Entradas buscar(int codigo) throws Exception{
        Entradas e = null;
        con = ConnectionFactory.getConnection();
        sql = "select * from entradas where codigo = ?";
        st = con.prepareStatement(sql);
        st.setInt(1, codigo);
        ResultSet rs = st.executeQuery();
        if(rs.next()){
            int valor = rs.getInt("valor");
            Date data = rs.getDate("data");
            e = new Entradas();
            e.setCodigo(codigo);
            e.setValor(valor);
            e.setData(data);
        }
        con.close();
        return e;
    }   
}
