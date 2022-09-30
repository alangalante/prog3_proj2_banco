package br.edu.femass.dao;

import br.edu.femass.model.Conta;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class DaoConta extends Persistencia<Conta> implements Dao<Conta> {
    private final static String NOMEARQUIVO = "contas.json";

    public void save(Conta conta) throws Exception {
        List<Conta> contas = getAll();
        contas.add(conta);
        String json = getObjectmapper().writerWithDefaultPrettyPrinter().writeValueAsString(contas);

        FileOutputStream out = new FileOutputStream(NOMEARQUIVO);
        out.write(json.getBytes());
        out.close();
    }

    public List<Conta> getAll() throws Exception{
        try {
            FileInputStream in = new FileInputStream(NOMEARQUIVO);
            String json = new String(in.readAllBytes());
            List<Conta> contas = getObjectmapper().readValue(json, new TypeReference<List<Conta>>(){});
            Conta.atualizarProximoNumero(contas);
            return contas;
        } catch (FileNotFoundException f) {
            return new ArrayList();
        }
    }


}
