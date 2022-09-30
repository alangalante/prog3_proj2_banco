package br.edu.femass.gui;

import br.edu.femass.dao.DaoCliente;
import br.edu.femass.model.Cliente;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuiCliente {
    private JPanel jPanel;
    private JList lstClientes;
    private JTextField txtNome;
    private JTextField txtCpf;
    private JTextField txtEndereco;
    private JButton btnSalvar;

    public GuiCliente() {
        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Cliente cliente = new Cliente(
                            txtNome.getText(),
                            txtCpf.getText(),
                            txtEndereco.getText()
                    );
                    new DaoCliente().save(cliente);
                    preencherLista();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }

            }
        });
        lstClientes.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {

                Cliente cliente = (Cliente) lstClientes.getSelectedValue();

                if (!(cliente==null)) {
                    txtCpf.setText(cliente.getCpf());
                    txtEndereco.setText(cliente.getEndereco());
                    txtNome.setText(cliente.getNome());
                }
            }
        });
    }

    private void preencherLista() {
        try {
            lstClientes.setListData(
                    new DaoCliente().getAll().toArray()
            );
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public void abrirTela() {
        JFrame frame = new JFrame();
        GuiCliente gui = new GuiCliente();
        gui.preencherLista();
        frame.setContentPane(gui.jPanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setTitle("Cadastro de Clientes");
        frame.pack();
        frame.setVisible(true);
    }

    public void abrirTelaModal() {
        JDialog frame = new JDialog(new Frame(), true);
        GuiCliente gui = new GuiCliente();
        gui.preencherLista();
        frame.setContentPane(gui.jPanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setTitle("Cadastro de Clientes");
        frame.pack();
        frame.setVisible(true);
    }

}
