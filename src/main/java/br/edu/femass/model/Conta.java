package br.edu.femass.model;


import java.util.List;

public class Conta {
    private Long numero;
    private Double saldo;
    private Double especial;

    private Cliente cliente;
    private static Long proximoNumero = 1L;

    public Conta() {

    }

    public Conta(Double especial, Cliente cliente) {
        this.numero = proximoNumero;
        proximoNumero++;
        this.saldo = 0D;
        this.especial = especial;
        this.cliente = cliente;
    }

    public Double getEspecial() {
        return especial;
    }

    public Double getSaldo() {
        return saldo;
    }


    public Long getNumero() {
        return numero;
    }

    public void setEspecial(Double especial) {
        this.especial = especial;
    }

    public Cliente getCliente() {
        return cliente;
    }

    @Override
    public String toString() {
        return "Número: " + this.numero.toString() + " - Cliente: " +  this.cliente.getNome();
    }

    @Override
    public boolean equals(Object obj) {
        Conta conta = (Conta) obj;
        return this.numero.equals(conta.getNumero());
    }


    public static void atualizarProximoNumero(List<Conta> contas) {
        for (Conta conta: contas) {
            if (conta.getNumero()>proximoNumero) {
                proximoNumero = conta.getNumero()+1;
            }
        }
    }




}
