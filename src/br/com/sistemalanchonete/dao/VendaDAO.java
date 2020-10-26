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
    public void remover(Venda venda) throws Exception{
        con = ConnectionFactory.getConnection();
        
        sql = "delete from CLIENTES_COMPRA_PRODUTOS  where codigo = ?";
        
        st = con.prepareStatement(sql);
        
        st.setInt(1, venda.getCodigo());
        
        st.executeUpdate();
        
        con.close();
    }
    public List<Venda> listar() throws Exception{
        List<Venda> venda = new ArrayList<>();
        con = ConnectionFactory.getConnection();
        sql = "select * from CLIENTES_COMPRA_PRODUTOS";
        st = con.prepareStatement(sql);
        ResultSet rs = st.executeQuery();
        while(rs.next()){
            int codigo = rs.getInt(1);
            Date data = rs.getDate("data");
            float valor = rs.getFloat("valor");
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
        sql = "select * from CLIENTES_COMPRA_PRODUTOS where codigo = ?";
        st = con.prepareStatement(sql);
        st.setInt(1, codigo);
        ResultSet rs = st.executeQuery();
        if(rs.next()){
            float valor = rs.getFloat("valor");
            Date data = rs.getDate("data");
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
