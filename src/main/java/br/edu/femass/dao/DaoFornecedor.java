package br.edu.femass.dao;

import br.edu.femass.model.Cliente;
import br.edu.femass.model.Fornecedor;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class DaoFornecedor extends Persistencia<Fornecedor> implements Dao<Fornecedor>{
    private final static String NOMEARQUIVO = "fornecedores.json";


    public void save(Fornecedor fornecedor) throws Exception {
        List<Fornecedor> fornecedores = getAll();
        fornecedores.add(fornecedor);
        String json = getObjectmapper().writerWithDefaultPrettyPrinter().writeValueAsString(fornecedores);

        FileOutputStream out = new FileOutputStream(NOMEARQUIVO);
        out.write(json.getBytes());
        out.close();
    }

    public List<Fornecedor> getAll() throws Exception{
        try {
            FileInputStream in = new FileInputStream(NOMEARQUIVO);
            String json = new String(in.readAllBytes());
            List<Fornecedor> fornecedores = getObjectmapper().readValue(json, new TypeReference<List<Fornecedor>>(){});
            return fornecedores;
        } catch (FileNotFoundException f) {
            return new ArrayList();
        }
    }
}
