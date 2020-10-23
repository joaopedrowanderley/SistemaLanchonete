/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistemalanchonete.model;

/**
 *
 * @author joão pedro
 */
public class Funcionario {
    private int codigo;
    private String nome;
    private float salario;
    private int supervisor;

    public int getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(int supervisor) {
        this.supervisor = supervisor;
    }
    
    

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getSalario() {
        return salario;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }
    
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(codigo)
                .append(" - ")
                .append(nome)
                .append(" - ")
                .append(salario);
        return sb.toString();
    }
}
