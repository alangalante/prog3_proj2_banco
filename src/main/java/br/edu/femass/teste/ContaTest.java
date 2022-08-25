package br.edu.femass.teste;

import br.edu.femass.model.Cliente;
import br.edu.femass.model.Conta;

public class ContaTest {

    public static void main(String[] args) {
        Cliente cliente = new Cliente(
                "João da Silva",
                "65006006064" );
        System.out.println("Imprimindo dados do cliente: "
                + cliente.toString());

        cliente.criarConta(0D);
        cliente.criarConta(5000D);

        System.out.println("Imprimindo as contas do cliente");
        for (Conta conta: cliente.getContas()) {
            System.out.println("Número: "
                    + conta.toString());
        }

        Conta conta = cliente.getContas().get(1);
        try {
            conta.creditar(1000.0);
            conta.debitar(120.0);
            conta.debitar(3000.0);
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println("Saldo da conta: "
                + conta.getSaldo());
        System.out.println("Histórico de operações:\n"
                + conta.getHistorico());





    }
}
