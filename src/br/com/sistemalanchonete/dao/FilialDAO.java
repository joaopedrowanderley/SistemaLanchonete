/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistemalanchonete.dao;

import br.com.sistemalanchonete.model.Filial;
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
public class FilialDAO {
    private Connection con;
    private String sql;
    private PreparedStatement st;
    private ResultSet rs;
    
    public void inserir(Filial filial) throws Exception{
        con = ConnectionFactory.getConnection();
        
        sql = "insert into endereco (rua, bairro, cidade, referencia, numero) values (?,?,?,?,?)";
        
        // informa ao jdbc que o codigo gerado deverá ser retornado
        st = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        
        st.setString(1, filial.getEndereco().getRua());
        st.setString(2, filial.getEndereco().getBairro());
        st.setString(3, filial.getEndereco().getCidade());
        st.setString(4, filial.getEndereco().getReferencia());
        st.setInt(5, filial.getEndereco().getNumero());
        
        st.executeUpdate();
        
        rs = st.getGeneratedKeys();
        
        int codigoEndereco = 0;
        
        if(rs.next()){
            codigoEndereco = rs.getInt(1);
        }
        
        sql = "insert into filial ( nome, telefone, funcionario,cod_endereco) values (?,?,?,?)";
        
        st = con.prepareStatement(sql);
        
        
        st.setString(1, filial.getNome());
        st.setString(2, filial.getTelefone());
        st.setInt(3, filial.getCod_gerente());
        st.setInt(4, codigoEndereco);
        
        st.executeUpdate();
        
        con.close();
    }
    public void editar(Filial filial) throws Exception{
      con = ConnectionFactory.getConnection();
        
        sql = " update endereco set rua = ?, bairro = ?, cidade = ?,  referencia = ?, numero = ?  where codigo_endereco = ? ";
        
       
        st = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        
        st.setString(1, filial.getEndereco().getRua());
        st.setString(2, filial.getEndereco().getBairro());
        st.setString(3, filial.getEndereco().getCidade());
        st.setString(4, filial.getEndereco().getReferencia());
        st.setInt(5, filial.getEndereco().getNumero());
        st.setInt(6, filial.getEndereco().getCodigo());
        
        st.executeUpdate();
        
        rs = st.getGeneratedKeys();
        
        int codigoEndereco = 0;
        
        if(rs.next()){
            codigoEndereco = rs.getInt(1);
        }
        
        sql = "insert into filial ( nome, telefone, funcionario,cod_endereco) values (?,?,?,?)";
        
        st = con.prepareStatement(sql);
        
        
        st.setString(1, filial.getNome());
        st.setString(2, filial.getTelefone());
        st.setInt(3, filial.getCod_gerente());
        st.setInt(4, codigoEndereco);
        
        st.executeUpdate();
        
        con.close();
    }
    public void remover(Filial filial) throws Exception{
        con = ConnectionFactory.getConnection();
        
        sql = "delete from filial where codigo = ?";
        
        st = con.prepareStatement(sql);
        
        st.setInt(1, filial.getCodigo());
        
        st.executeUpdate();
        
        con.close();
    }
    public List<Filial> listar() throws Exception{
        List<Filial> filial = new ArrayList<>();
        con = ConnectionFactory.getConnection();
        sql = "select f.*, e.* from filial f, endereco e where f.cod_endereco = e.codigo";
        st = con.prepareStatement(sql);
        ResultSet rs = st.executeQuery();
        while(rs.next()){
            int codigo = rs.getInt(1);
             String nome  = rs.getString("nome");
            String  telefone  = rs.getString("telefone");
            int codigo_gerente = rs.getInt("codigo_gerente");
            String rua = rs.getString("rua");
            String bairro = rs.getString("bairro");
            String cidade = rs.getString("cidade");
            String referencia = rs.getString("referencia");
            int numero = rs.getInt("numero");
            int codigoEndereco = rs.getInt("codigo_endereco");
            
            Filial f = new Filial();
            
            f.setCodigo(codigo);
            f.setNome(nome);
            f.setTelefone(telefone);
            f.setCod_gerente(codigo_gerente);
            f.getEndereco().setRua(rua);
            f.getEndereco().setBairro(bairro);
            f.getEndereco().setCidade(cidade);
            f.getEndereco().setReferencia(referencia);
            f.getEndereco().setNumero(numero);
            f.getEndereco().setCodigo(codigoEndereco);
            
            filial.add(f);
        }
        con.close();
        return filial;
    }
    
        public Filial buscar(int codigo) throws Exception{
        Filial f = null;
        con = ConnectionFactory.getConnection();
        sql = "select * from filial where codigo = ?";
        st = con.prepareStatement(sql);
        st.setInt(1, codigo);
        ResultSet rs = st.executeQuery();
        if(rs.next()){
            String nome  = rs.getString("nome");
            String  telefone  = rs.getString("telefone");
            int codigo_gerente = rs.getInt("codigo_gerente");
            String rua = rs.getString("rua");
            String bairro = rs.getString("bairro");
            String cidade = rs.getString("cidade");
            String referencia = rs.getString("referencia");
            int numero = rs.getInt("numero");
            int codigoEndereco = rs.getInt("codigo_endereco");
            
             f = new Filial();
            
            f.setNome(nome);
            f.setTelefone(telefone);
            f.setCod_gerente(codigo_gerente);
            f.getEndereco().setRua(rua);
            f.getEndereco().setBairro(bairro);
            f.getEndereco().setCidade(cidade);
            f.getEndereco().setReferencia(referencia);
            f.getEndereco().setNumero(numero);
            f.getEndereco().setCodigo(codigoEndereco);
        }
        con.close();
        return f;
    }
}
