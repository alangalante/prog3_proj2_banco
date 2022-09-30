package br.edu.femass.model;

import br.edu.femass.utils.Cpf;



public class Cliente {

    private String nome;
    private String cpf;
    private String endereco;

    public Cliente() {

    }
    public Cliente(String nome, String cpf) {
        this.nome = nome;
        if (Cpf.isCpf(cpf)==false) {
            throw new IllegalArgumentException("CPF Inválido");
        }
        this.cpf = cpf;
    }

    public Cliente(String nome, String cpf, String endereco) {
        this.nome = nome;

        if (Cpf.isCpf(cpf)==false) {
            throw new IllegalArgumentException("CPF Inválido");
        }
        this.cpf = cpf;
        this.endereco = endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return this.nome;
    }

    @Override
    public boolean equals(Object obj) {
        Cliente cliente = (Cliente) obj;
        if (obj==null) return false;
        return cliente.getCpf().equals(this.cpf);
    }

}
