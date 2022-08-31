package br.edu.femass.dao;

import br.edu.femass.model.Cliente;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class DaoCliente {

    private static List<Cliente> clientes = new ArrayList<Cliente>();

    public void gravar(Cliente cliente) throws Exception {
        //1o) Adiciona o objeto a lista
        clientes.add(cliente);

        //2o) Gera o JSON
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(clientes);
        System.out.println(json);

        //3o) Grava o arquivo em disco
        FileOutputStream out = new FileOutputStream("cliente.json");
        out.write(json.getBytes());
        out.close();
    }

    public List<Cliente> getClientes() throws Exception{

        //1o) Ler o arquivo
        FileInputStream in = new FileInputStream("cliente.json");
        String json = new String(in.readAllBytes());

        //2o) Converter o conteudo do arquivo em objeto
        ObjectMapper objectMapper = new ObjectMapper();
        clientes = objectMapper.readValue(json, new TypeReference<List<Cliente>>(){});

        //3o) Devolver a lista de objetos
        return clientes;



    }


}
