package br.edu.femass.gui;

import br.edu.femass.dao.DaoCliente;
import br.edu.femass.dao.DaoConta;
import br.edu.femass.model.Cliente;
import br.edu.femass.model.Conta;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class GuiConta {
    private JPanel jPanel;
    private JList lstConta;
    private JTextField TxtNumero;
    private JComboBox cboCliente;
    private JTextField txtEspecial;
    private JTextField txtSaldo;
    private JButton btnSalvar;
    private JButton btnCliente;

    public GuiConta() {
        lstConta.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                Conta conta = (Conta) lstConta.getSelectedValue();

                if (conta==null) return;

                txtEspecial.setText(conta.getEspecial().toString());
                txtSaldo.setText(conta.getSaldo().toString());
                TxtNumero.setText(conta.getNumero().toString());
                cboCliente.setSelectedItem(conta.getCliente());

            }
        });
        btnCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GuiCliente().abrirTelaModal();
                preencherListaCliente();
            }
        });
        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Conta conta = new Conta(
                            Double.parseDouble(txtEspecial.getText()),
                            (Cliente) cboCliente.getSelectedItem()
                    );
                    new DaoConta().save(conta);
                    preencherLista();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }

            }
        });
    }

    private void preencherLista() {
        try {
            lstConta.setListData(
                    new DaoConta().getAll().toArray()
            );
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    private void preencherListaCliente() {
        cboCliente.removeAllItems();
        try {
            List<Cliente> clientes = new DaoCliente().getAll();
            for (Cliente cliente: clientes) {
                cboCliente.addItem(cliente);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public void abrirTela() {
        JFrame frame = new JFrame();
        GuiConta gui = new GuiConta();
        gui.preencherLista();
        gui.preencherListaCliente();
        frame.setContentPane(gui.jPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Cadastro de Contas de Clientes");
        frame.pack();
        frame.setVisible(true);
    }
}
