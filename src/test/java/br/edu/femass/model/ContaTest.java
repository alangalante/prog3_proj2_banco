package br.edu.femass.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

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

        Historico esperado = "";

        for (Historico historico: historicos) {
            esperado+=historico.toString() + "\n";
        }

        String esperado = "27/08/2022 21:26:41 - Valor: 100.0 - D�bito\n" +
                "27/08/2022 21:26:41 - Valor: 300.0 - Cr�dito\n" +
                "27/08/2022 21:26:41 - Valor: 200.0 - Cr�dito";

        Assertions.assertEquals(esperado, conta.getHistorico());
    }

}