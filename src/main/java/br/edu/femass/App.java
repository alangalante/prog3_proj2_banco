package br.edu.femass;

import br.edu.femass.dao.Dao;
import br.edu.femass.dao.DaoCliente;
import br.edu.femass.dao.DaoFornecedor;
import br.edu.femass.model.Cliente;
import br.edu.femass.model.Conta;
import br.edu.femass.model.Fornecedor;

import java.util.List;

public class App {

    public static void main(String[] args) {
        gerarFornecedor();


    }

    private static void gerarFornecedor() {
        Fornecedor f = new Fornecedor();
        f.setCnpj("123");
        f.setNome("FeMASS Ltda");

        try {
            new DaoFornecedor().save(f);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private static void lerClientes() {
        Dao<Cliente> dao = new DaoCliente();

        try {
            List<Cliente> clientes = new DaoCliente().getAll();
            for (Cliente cliente: clientes) {
                System.out.println(cliente);
                for (Conta conta: cliente.getContas()) {
                    System.out.println(conta);
                    System.out.println(conta.getHistorico());
                }
             }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private static void gerarClientes() {
        Cliente cliente = new Cliente("João da Silva", "50327237031");
        DaoCliente dao = new DaoCliente();
        try {
            dao.save(cliente);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        Cliente cliente1 = new Cliente("Maria", "25936400069", "Rua A");
        cliente1.criarConta(0.0);
        cliente1.criarConta(1000.0);

        cliente1.getContas().get(0).creditar(10000.0);
        cliente1.getContas().get(0).creditar(100.0);
        cliente1.getContas().get(0).debitar(500.0);

        try {
            dao.save(cliente1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
