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
        
        sql = "INSERT INTO \"LANCHONETE\".\"ENDERECO\"(\n" + 
        		"   \"Rua\", \"Bairro\", \"Cidade\", \"Referencia\", \"Numero\")\n" + 
        		"	VALUES (?, ?, ?, ?, ?);";
        
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
        
        sql = "INSERT INTO \"LANCHONETE\".\"CLIENTES\"(\n" + 
        		"	\"Nome_cliente\", \"Telefone\", \"Cod_endereco\")\n" + 
        		"	VALUES (?, ?, ?);";
        
        st = con.prepareStatement(sql);
        
        
        st.setString(1, cliente.getNome());
        st.setString(2, cliente.getTelefone());
        st.setInt(3, codigoEndereco);
        
        st.executeUpdate();
        
        con.close();
    }
    public void editar(Cliente cliente) throws Exception{
      con = ConnectionFactory.getConnection();
        
        sql = " UPDATE \"LANCHONETE\".\"ENDERECO\"\n" + 
        		"	SET  \"Rua\"=?, \"Bairro\"=?, \"Cidade\"=?, \"Referencia\"=?, \"Numero\"=?\n" + 
        		"	WHERE \"Codigo_endereco\"=?; ";
        
       
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
        
        sql = "UPDATE \"LANCHONETE\".\"CLIENTES\"\n" + 
        		"	SET \"Nome_cliente\"=?, \"Telefone\"=?, \"Cod_endereco\"=?\n" + 
        		"	WHERE \"Codigo_cliente\"=?;";
        
        st = con.prepareStatement(sql);
        
        
        st.setString(1, cliente.getNome());
        st.setString(2, cliente.getTelefone());
        st.setInt(3, codigoEndereco);
        
        st.executeUpdate();
        
        con.close();
    }
    public void remover(Cliente cliente) throws Exception{
        con = ConnectionFactory.getConnection();
        
        sql = "DELETE FROM \"LANCHONETE\".\"CLIENTES\"\n" + 
        		"	WHERE \"Codigo_cliente\"=?;";
        
        st = con.prepareStatement(sql);
        
        st.setInt(1, cliente.getCodigo());
        
        st.executeUpdate();
        
        con.close();
    }
    public List<Cliente> listar() throws Exception{
        List<Cliente> filial = new ArrayList<>();
        con = ConnectionFactory.getConnection();
        sql = "select * from \"LANCHONETE\".\"CLIENTES\" c, \"LANCHONETE\".\"ENDERECO\" e \n" + 
        		"where c.\"Cod_endereco\" = e.\"Codigo_endereco\"";
        st = con.prepareStatement(sql);
        ResultSet rs = st.executeQuery();
        while(rs.next()){
            int codigo = rs.getInt("Codigo_cliente");
            String nome  = rs.getString("Nome_cliente");
            String  telefone  = rs.getString("Telefone");
            String rua = rs.getString("Rua");
            String bairro = rs.getString("Bairro");
            String cidade = rs.getString("Cidade");
            String referencia = rs.getString("Referencia");
            int numero = rs.getInt("Numero");
            int codigoEndereco = rs.getInt("Codigo_endereco");
            
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
        sql = "select * from \"LANCHONETE\".\"CLIENTES\" c, \"LANCHONETE\".\"ENDERECO\" e \n" + 
        		"where c.\"Cod_endereco\" = e.\"Codigo_endereco\" and c.\"Codigo_cliente\" = ?";
        st = con.prepareStatement(sql);
        st.setInt(1, codigo);
        ResultSet rs = st.executeQuery();
        if(rs.next()){
        	String nome  = rs.getString("Nome_cliente");
            String  telefone  = rs.getString("Telefone");
            String rua = rs.getString("Rua");
            String bairro = rs.getString("Bairro");
            String cidade = rs.getString("Cidade");
            String referencia = rs.getString("Referencia");
            int numero = rs.getInt("Numero");
            int codigoEndereco = rs.getInt("Codigo_endereco");
            
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
