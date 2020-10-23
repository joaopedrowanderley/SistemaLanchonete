/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistemalanchonete.dao;

import br.com.sistemalanchonete.model.Funcionario;
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
public class FuncionarioDAO {
    private Connection con;
    private String sql;
    private PreparedStatement st;
    private ResultSet rs;
    
    public void inserir(Funcionario funcionario) throws Exception{
        con = ConnectionFactory.getConnection();
        
        sql = "insert into funcionarios ( nome, salario, supervisor) values (?,?,?)";
        
        st = con.prepareStatement(sql);
        
        st.setString(1, funcionario.getNome());
        st.setFloat(2,funcionario.getSalario());
        st.setInt(3, funcionario.getSupervisor());
       
        st.executeUpdate();
        
        con.close();
    }
    public void editar(Funcionario funcionario) throws Exception{
        con = ConnectionFactory.getConnection();
        
        sql = "update funcionarios set  nome = ?, salario = ?, supervisor = ?  where codigo = ?";
        
        st = con.prepareStatement(sql);
        
        st.setString(1, funcionario.getNome());
        st.setFloat(2, funcionario.getSalario());
        st.setInt(3, funcionario.getSupervisor());
        
        st.executeUpdate();
        
        con.close();
    }
    public void remover(Funcionario funcionario) throws Exception{
        con = ConnectionFactory.getConnection();
        
        sql = "delete from funcionarios where codigo = ?";
        
        st = con.prepareStatement(sql);
        
        st.setInt(1, funcionario.getCodigo());
        
        st.executeUpdate();
        
        con.close();
    }
    public List<Funcionario> listar() throws Exception{
        List<Funcionario> funcionario = new ArrayList<>();
        con = ConnectionFactory.getConnection();
        sql = "select * from funcionarios";
        st = con.prepareStatement(sql);
        ResultSet rs = st.executeQuery();
        while(rs.next()){
            int codigo = rs.getInt(1);
            String nome = rs.getString("nome");
            float salario = rs.getFloat("salario");
            int supervisor = rs.getInt("supervisor");
            
            Funcionario f = new Funcionario();
            
            f.setCodigo(codigo);
            f.setNome(nome);
            f.setSalario(salario);
            f.setSupervisor(supervisor);
            
            
           funcionario.add(f);
        }
        con.close();
        return funcionario;
    }
    
        public Funcionario buscar(int codigo) throws Exception{
        Funcionario f = null;
        con = ConnectionFactory.getConnection();
        sql = "select * from funcionario where codigo = ?";
        st = con.prepareStatement(sql);
        st.setInt(1, codigo);
        ResultSet rs = st.executeQuery();
        if(rs.next()){
            String nome = rs.getString("nome");
            float salario = rs.getFloat("salario");
            int supervisor = rs.getInt("supervisor");
            f = new Funcionario();
            f.setCodigo(codigo);
            f.setNome(nome);
            f.setSalario(salario);
            f.setSupervisor(supervisor);
            
        }
        con.close();
        return f;
    }
}
