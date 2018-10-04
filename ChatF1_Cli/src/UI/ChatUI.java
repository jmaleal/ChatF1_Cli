/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Comunicacao.presencesClient;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

public class ChatUI extends JFrame {

    JPanel topPanel;
    JTextPane tPane;
    JTextField tfield = new JTextField();
    int counter = 0;
    Color[] colours = {Color.RED, Color.BLUE,};
    presencesClient client;

    public void createAndDisplayGUI(presencesClient client) {

        //inicia cliente
        this.client = client;
        iniciaCliente();

        //componente da janela + inicialização
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tPane = new JTextPane();
        JScrollPane scroller = new JScrollPane();
        scroller.setViewportView(tPane);

        tfield.addActionListener(e -> {
            String text = tfield.getText() + "\n";
            AdicionarMsgEnviada(tPane, text, Color.RED);
        });

        getContentPane().add(scroller, BorderLayout.CENTER);
        getContentPane().add(tfield, BorderLayout.PAGE_END);

        setSize(800, 400);
        setVisible(true);
        tfield.requestFocusInWindow();
        tfield.setText("hi enter to start");
        this.setResizable(false);
        this.setLocationRelativeTo(null);  // <<--- plain and simple
    }

    private void iniciaCliente() {
        /* String[] argsCli = new String[1];
        argsCli[0] = "8082";
        client.startCli(argsCli);*/
    }

    private void AdicionarMsgEnviada(JTextPane tp, String msg, Color c) {
        StyleContext sc = StyleContext.getDefaultStyleContext();
        AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY,
                StyleConstants.Foreground, c);

        aset = sc.addAttribute(aset, StyleConstants.FontFamily, "Lucida Console");
        aset = sc.addAttribute(aset, StyleConstants.Alignment,
                StyleConstants.ALIGN_JUSTIFIED);

        int len = tp.getDocument().getLength();
        tp.setCaretPosition(len);
        tp.setCharacterAttributes(aset, false);
        tp.replaceSelection(msg);

    }

    private void AdicionarMsgRecebida(JTextPane tp, String msg, Color c) {
        StyleContext sc = StyleContext.getDefaultStyleContext();
        AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY,
                StyleConstants.Foreground, c);

        aset = sc.addAttribute(aset, StyleConstants.FontFamily, "Lucida Console");
        aset = sc.addAttribute(aset, StyleConstants.Alignment,
                StyleConstants.ALIGN_JUSTIFIED);

        int len = tp.getDocument().getLength();
        tp.setCaretPosition(len);
        tp.setCharacterAttributes(aset, false);
        tp.replaceSelection(msg);

    }
}
