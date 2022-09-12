package br.edu.femass.gui;

import br.edu.femass.dao.DaoCliente;
import br.edu.femass.model.Cliente;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.List;

public class GuiCliente {
    private JTextField txtNome;
    private JTextField txtEndereco;
    private JButton btnSalvar;
    private JPanel jPanel;
    private JFormattedTextField txtCpf;
    private JList lstClientes;

    public GuiCliente() {
        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    Cliente cliente = new Cliente(txtNome.getText(), txtCpf.getText(), txtEndereco.getText());
                    new DaoCliente().save(cliente);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });
    }

    public static void main(String[] args) {
        GuiCliente guiCliente = new GuiCliente();

        JFrame frame = new JFrame("Aula de Prog 3");
        frame.setContentPane(guiCliente.jPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        try {
            MaskFormatter mascara = new MaskFormatter("###.###.###-##");
            mascara.install(guiCliente.txtCpf);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        try {
            List<Cliente> clientes = new DaoCliente().getAll();
            guiCliente.lstClientes.setListData(clientes.toArray());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        frame.pack();

        frame.setVisible(true);

    }
}
