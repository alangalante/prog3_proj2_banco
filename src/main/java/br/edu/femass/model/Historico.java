package br.edu.femass.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Historico {
    private LocalDateTime data;
    private Double valor;
    private TipoLancamento tipoLancamento;

    public Historico(Double valor, TipoLancamento tipoLancamento) {
        this.data = LocalDateTime.now();
        this.valor = valor;
        this.tipoLancamento = tipoLancamento;
    }

    public Double getValor() {
        return valor;
    }

    public LocalDateTime getData() {
        return data;
    }

    public TipoLancamento getTipoLancamento() {
        return tipoLancamento;
    }

    @Override
    public String toString() {
        return data.format(
                DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"))
                + " - Valor: "
                + this.valor.toString()
                + " - " + this.tipoLancamento.valor ;
    }
}
