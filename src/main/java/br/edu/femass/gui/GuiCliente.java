package br.edu.femass.gui;

import br.edu.femass.dao.DaoCliente;
import br.edu.femass.model.Cliente;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.MaskFormatter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.ParseException;
import java.util.List;

public class GuiCliente {
    private JTextField txtNome;
    private JTextField txtEndereco;
    private JButton btnSalvar;
    private JPanel jPanel;
    private JFormattedTextField txtCpf;
    private JList lstClientes;
    private JComboBox cboClientes;

    public GuiCliente() {
        try {
            MaskFormatter mascara = new MaskFormatter("###.###.###-##");
            mascara.install(txtCpf);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        updateList();
        updateCombo();

        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    Cliente cliente = new Cliente(txtNome.getText(), txtCpf.getText(), txtEndereco.getText());
                    new DaoCliente().save(cliente);
                    updateList();
                    updateCombo();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });
        lstClientes.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent listSelectionEvent) {
                Cliente cliente = (Cliente) lstClientes.getSelectedValue();
                if (cliente==null) return;
                txtCpf.setText(cliente.getCpf());
                txtEndereco.setText(cliente.getEndereco());
                txtNome.setText(cliente.getNome());
            }
        });
        cboClientes.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent itemEvent) {
                Cliente cliente = (Cliente) cboClientes.getSelectedItem();
                if (cliente==null) return;
                txtCpf.setText(cliente.getCpf());
                txtEndereco.setText(cliente.getEndereco());
                txtNome.setText(cliente.getNome());
            }
        });
    }

    public JPanel getjPanel() {
        return jPanel;
    }

    private void updateList() {
        try {
            List<Cliente> clientes = new DaoCliente().getAll();
            lstClientes.setListData(clientes.toArray());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void updateCombo() {
        try {
            List<Cliente> clientes = new DaoCliente().getAll();
            for (Cliente cliente: clientes) {
                cboClientes.addItem(cliente);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
