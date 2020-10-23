/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistemalanchonete.dao;

import br.com.sistemalanchonete.model.Cliente;
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
public class ClienteDAO {
     private Connection con;
    private String sql;
    private PreparedStatement st;
    private ResultSet rs;
    
    public void inserir(Cliente cliente) throws Exception{
        con = ConnectionFactory.getConnection();
        
        sql = "insert into endereco (rua, bairro, cidade, referencia, numero) values (?,?,?,?,?)";
        
        // informa ao jdbc que o codigo gerado deverá ser retornado
        st = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        
        st.setString(1, cliente.getEndereco().getRua());
        st.setString(2, cliente.getEndereco().getBairro());
        st.setString(3, cliente.getEndereco().getCidade());
        st.setString(4, cliente.getEndereco().getReferencia());
        st.setInt(5, cliente.getEndereco().getNumero());
        
        st.executeUpdate();
        
        rs = st.getGeneratedKeys();
        
        int codigoEndereco = 0;
        
        if(rs.next()){
            codigoEndereco = rs.getInt(1);
        }
        
        sql = "insert into clientes ( nome, telefone,cod_endereco) values (?,?,?)";
        
        st = con.prepareStatement(sql);
        
        
        st.setString(1, cliente.getNome());
        st.setString(2, cliente.getTelefone());
        st.setInt(3, codigoEndereco);
        
        st.executeUpdate();
        
        con.close();
    }
    public void editar(Cliente cliente) throws Exception{
      con = ConnectionFactory.getConnection();
        
        sql = " update endereco set rua = ?, bairro = ?, cidade = ?,  referencia = ?, numero = ?  where codigo_endereco = ? ";
        
       
        st = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        
        st.setString(1, cliente.getEndereco().getRua());
        st.setString(2, cliente.getEndereco().getBairro());
        st.setString(3, cliente.getEndereco().getCidade());
        st.setString(4, cliente.getEndereco().getReferencia());
        st.setInt(5, cliente.getEndereco().getNumero());
        st.setInt(6, cliente.getEndereco().getCodigo());
        
        st.executeUpdate();
        
        rs = st.getGeneratedKeys();
        
        int codigoEndereco = 0;
        
        if(rs.next()){
            codigoEndereco = rs.getInt(1);
        }
        
        sql = "insert into clientes ( nome, telefone,cod_endereco) values (?,?,?)";
        
        st = con.prepareStatement(sql);
        
        
        st.setString(1, cliente.getNome());
        st.setString(2, cliente.getTelefone());
        st.setInt(3, codigoEndereco);
        
        st.executeUpdate();
        
        con.close();
    }
    public void remover(Cliente cliente) throws Exception{
        con = ConnectionFactory.getConnection();
        
        sql = "delete from clientes where codigo = ?";
        
        st = con.prepareStatement(sql);
        
        st.setInt(1, cliente.getCodigo());
        
        st.executeUpdate();
        
        con.close();
    }
    public List<Cliente> listar() throws Exception{
        List<Cliente> filial = new ArrayList<>();
        con = ConnectionFactory.getConnection();
        sql = "select c.*, e.* from clientes c, endereco e where c.cod_endereco = e.codigo";
        st = con.prepareStatement(sql);
        ResultSet rs = st.executeQuery();
        while(rs.next()){
            int codigo = rs.getInt(1);
             String nome  = rs.getString("nome");
            String  telefone  = rs.getString("telefone");
            String rua = rs.getString("rua");
            String bairro = rs.getString("bairro");
            String cidade = rs.getString("cidade");
            String referencia = rs.getString("referencia");
            int numero = rs.getInt("numero");
            int codigoEndereco = rs.getInt("codigo_endereco");
            
            Cliente c = new Cliente();
            
            c.setCodigo(codigo);
            c.setNome(nome);
            c.setTelefone(telefone);
            c.getEndereco().setRua(rua);
            c.getEndereco().setBairro(bairro);
            c.getEndereco().setCidade(cidade);
            c.getEndereco().setReferencia(referencia);
            c.getEndereco().setNumero(numero);
            c.getEndereco().setCodigo(codigoEndereco);
            
            filial.add(c);
        }
        con.close();
        return filial;
    }
    
        public Cliente buscar(int codigo) throws Exception{
        Cliente c = null;
        con = ConnectionFactory.getConnection();
        sql = "select * from clientes where codigo = ?";
        st = con.prepareStatement(sql);
        st.setInt(1, codigo);
        ResultSet rs = st.executeQuery();
        if(rs.next()){
            String nome  = rs.getString("nome");
            String  telefone  = rs.getString("telefone");
            String rua = rs.getString("rua");
            String bairro = rs.getString("bairro");
            String cidade = rs.getString("cidade");
            String referencia = rs.getString("referencia");
            int numero = rs.getInt("numero");
            int codigoEndereco = rs.getInt("codigo_endereco");
            
             c = new Cliente();
            
            c.setNome(nome);
            c.setTelefone(telefone);
            c.getEndereco().setRua(rua);
            c.getEndereco().setBairro(bairro);
            c.getEndereco().setCidade(cidade);
            c.getEndereco().setReferencia(referencia);
            c.getEndereco().setNumero(numero);
            c.getEndereco().setCodigo(codigoEndereco);
        }
        con.close();
        return c;
    }
}
