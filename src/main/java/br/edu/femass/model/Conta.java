package br.edu.femass.model;

import java.util.ArrayList;
import java.util.List;

public class Conta {
    private Long numero;
    private Double saldo;
    private Double especial;
    private List<Historico> historicos;
    private static Long proximoNumero = 1L;

    public Conta(Double especial) {
        this.numero = proximoNumero;
        proximoNumero++;
        this.saldo = 0D;
        this.especial = especial;
        this.historicos = new ArrayList<Historico>();
    }

    public Double getEspecial() {
        return especial;
    }

    public Double getSaldo() {
        return saldo;
    }

    public List<Historico> getHistoricos() {
        return historicos;
    }

    public Long getNumero() {
        return numero;
    }

    public void setEspecial(Double especial) {
        this.especial = especial;
    }

    @Override
    public String toString() {
        return this.numero.toString();
    }

    @Override
    public boolean equals(Object obj) {
        Conta conta = (Conta) obj;
        return this.numero.equals(conta.getNumero());
    }

    public void creditar(Double valor) {
        this.saldo+=valor;
        Historico historico = new Historico(valor, TipoLancamento.CREDITO);
        this.historicos.add(historico);
    }

    public void debitar(Double valor) {
        if (valor>this.saldo+ this.especial) {
            throw new IllegalArgumentException("Saldo Insuficiente");
        }
        this.saldo-=valor;
        Historico historico = new Historico(valor, TipoLancamento.DEBITO);
        this.historicos.add(historico);
    }

    public String getHistorico() {
        String resp = "";
        for (Historico historico: historicos) {
            resp+=historico.toString() + "\n";
        }

        return resp;
    }

}
