package br.edu.femass.model;

import br.edu.femass.utils.Cpf;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ClienteTest {

    @Test
    void criarClienteCpfValido() {

        Assertions.assertTrue(Cpf.isCpf("32861458666"));
    }

    @Test
    void criarClienteCpfInvalido() {

        Assertions.assertFalse(Cpf.isCpf("12345"));
    }

    @Test
    void criarContaGerandoNumero() {

    }


}
