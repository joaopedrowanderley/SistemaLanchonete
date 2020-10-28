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
public class Filial {
    private int codigo;
    private String nome;
    private String telefone;
    private Integer cnpj;
    private Endereco endereco;
    private int cod_gerente;

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

    public int getCod_gerente() {
        return cod_gerente;
    }

    public void setCod_gerente(int cod_gerente) {
        this.cod_gerente = cod_gerente;
    }
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(codigo)
                .append(" - ")
                .append(nome)
                .append(" - ")
                .append(telefone)
                .append(" - ")
                .append(endereco)
                .append(" - ")
                .append(cnpj)
                .append(" - ")
                .append(cod_gerente);
        return sb.toString();
    }

	public Integer getCnpj() {
		return cnpj;
	}

	public void setCnpj(Integer cnpj) {
		this.cnpj = cnpj;
	}    
    
}
