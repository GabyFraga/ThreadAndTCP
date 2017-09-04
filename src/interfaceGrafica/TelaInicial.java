package interfaceGrafica;

import clienteEServidor.Cliente;
import clienteEServidor.Servidor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.UnknownHostException;

public class TelaInicial {



    public static void criarTela(){

        JFrame frame = new JFrame("Sem Nome");
        JLabel emptyLabel = new JLabel();
        JPanel pnlCriarSala = new JPanel();
        JPanel pnlEntrarSala = new JPanel();
        JButton criarSala = new JButton("Abrir sala");
        JButton entrarSala = new JButton("Entrar em uma sala");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(emptyLabel, BorderLayout.CENTER);
        frame.setPreferredSize(new Dimension(240, 320));

        pnlCriarSala.setPreferredSize(new Dimension(200, 100));
        pnlEntrarSala.setPreferredSize(new Dimension(200, 100));

        criarSala.setVerticalTextPosition(AbstractButton.CENTER);
        criarSala.setHorizontalTextPosition(AbstractButton.LEADING);
        criarSala.setMnemonic(KeyEvent.VK_D);
        criarSala.setActionCommand("disable");

        entrarSala.setVerticalTextPosition(AbstractButton.CENTER);
        entrarSala.setHorizontalTextPosition(AbstractButton.LEADING);
        entrarSala.setMnemonic(KeyEvent.VK_D);
        entrarSala.setActionCommand("disable");

        frame.add(pnlCriarSala, BorderLayout.NORTH);
        frame.add(pnlEntrarSala, BorderLayout.SOUTH);
        pnlCriarSala.add(criarSala);
        pnlEntrarSala.add(entrarSala);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        criarSala.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                Servidor server = new Servidor();
                server.start();

            }
        });

        entrarSala.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                Cliente client = new Cliente();

                try{
                    client.connect("127.0.0.1", (short) 8080);
                } catch (UnknownHostException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

            }
        });
    }


    public static void main (String[] args){

        criarTela();

    }

}
