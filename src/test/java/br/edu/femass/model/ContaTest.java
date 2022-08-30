package br.edu.femass.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class ContaTest {

    @Test
    void creditarValorConta() {
        Conta conta = new Conta(100.0);
        conta.creditar(200.0);
        Double esperado = 200.0;

        Assertions.assertEquals(esperado, conta.getSaldo());
    }

    @Test
    void debitarValorContaComSaldo() {
        Conta conta = new Conta(100.0);
        conta.creditar(200.0);
        conta.debitar(100.0);
        Double esperado = 100.0;

        Assertions.assertEquals(esperado, conta.getSaldo());
    }

    @Test
    void debitarValorContaSemSaldo() {
        Conta conta = new Conta(100.0);
        conta.debitar(100.0);
        Double esperado = -100.0;

        Assertions.assertEquals(esperado, conta.getSaldo());
    }

    @Test
    void validarHistoricoLancamentos() {
        Conta conta = new Conta(100.0);
        conta.debitar(100.0);
        conta.creditar(300.0);
        conta.creditar(200.0);

        List<Historico> esperado = new ArrayList<Historico>();

        for(int i=0; i<3; i++){
            esperado = conta.getHistoricos();
        }

        Assertions.assertEquals(esperado, conta.getHistoricos());
    }

}