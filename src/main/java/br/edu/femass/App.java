package br.edu.femass;

import br.edu.femass.gui.GuiCliente;


import javax.swing.*;

public class App {

    public static void main(String[] args) {
        GuiCliente guiCliente = new GuiCliente();

        JFrame frame = new JFrame("Aula de Prog 3");
        frame.setContentPane(guiCliente.getjPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        frame.pack();

        frame.setVisible(true);


    }
}
