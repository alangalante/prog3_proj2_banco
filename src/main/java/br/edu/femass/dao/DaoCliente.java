package br.edu.femass.dao;

import br.edu.femass.model.Cliente;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class DaoCliente extends Persistencia<Cliente> implements Dao<Cliente> {
    private final static String NOMEARQUIVO = "clientes.json";

    public void save(Cliente cliente) throws Exception {
        List<Cliente> clientes = getAll();
        clientes.add(cliente);
        String json = getObjectmapper().writerWithDefaultPrettyPrinter().writeValueAsString(clientes);

        FileOutputStream out = new FileOutputStream(NOMEARQUIVO);
        out.write(json.getBytes());
        out.close();
    }

    public List<Cliente> getAll() throws Exception{
        try {
            FileInputStream in = new FileInputStream(NOMEARQUIVO);
            String json = new String(in.readAllBytes());
            List<Cliente> clientes = getObjectmapper().readValue(json, new TypeReference<List<Cliente>>(){});
            return clientes;
        } catch (FileNotFoundException f) {
            return new ArrayList();
        }
    }


}
