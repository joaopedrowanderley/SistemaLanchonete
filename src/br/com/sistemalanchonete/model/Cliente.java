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
public class Cliente {
    private int codigo;
    private String nome;
    private String telefone;
    private Endereco endereco;

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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
    
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(codigo)
                .append(" - ")
                .append(nome)
                .append(" - ")
                .append(telefone)
                .append(" - ")
                .append(endereco);
        return sb.toString();
    }
}
