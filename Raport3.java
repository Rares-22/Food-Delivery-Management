package presentationLayer;

import bll.DeliveryService;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;
/**
 * Pentru interfata grafica raport 3
 */
public class Raport3 {

    private JFrame f;
    private JTextArea textArea;
    private JTextField textNrComenzi;
    private JTextField textSuma;
    private JButton butonGenereaza;
    private DeliveryService deliveryS = new DeliveryService();

    public Raport3() {
        initialize();
        initialize1();
        actionButon();
    }

    private void actionButon() {
        butonGenereaza = new JButton("Genereaza");
        butonGenereaza.setForeground(Color.BLACK);
        butonGenereaza.setFont(new Font("Times New Roman", Font.BOLD, 12));
        butonGenereaza.setBounds(34, 237, 92, 27);
        f.getContentPane().add(butonGenereaza);

        butonGenereaza.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int a = Integer.parseInt(textNrComenzi.getText());
                int b = Integer.parseInt(textSuma.getText());
                ArrayList<String> clients = null;
                try {
                    clients = deliveryS.generareRaport3(a, b);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                String str=new String();
                for(String s: clients){
                    str+=s+"\n";
                }
                textArea.setText("");
                textArea.append(str);
            }
        });

    }

    private void initialize1() {
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(163, 10, 674, 467);
        f.getContentPane().add(scrollPane);

        textArea = new JTextArea();
        textArea.setEditable(false);
        scrollPane.setViewportView(textArea);

        textNrComenzi = new JTextField();
        textNrComenzi.setForeground(Color.BLACK);
        textNrComenzi.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        textNrComenzi.setColumns(10);
        textNrComenzi.setBounds(10, 142, 148, 19);
        f.getContentPane().add(textNrComenzi);

        JLabel labelNumar = new JLabel("Numar Comenzi");
        labelNumar.setHorizontalAlignment(SwingConstants.CENTER);
        labelNumar.setForeground(Color.BLACK);
        labelNumar.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        labelNumar.setBounds(10, 119, 148, 27);
        f.getContentPane().add(labelNumar);

        JLabel labelSuma = new JLabel("Suma ");
        labelSuma.setHorizontalAlignment(SwingConstants.CENTER);
        labelSuma.setForeground(Color.BLACK);
        labelSuma.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        labelSuma.setBounds(10, 171, 148, 27);
        f.getContentPane().add(labelSuma);

        textSuma = new JTextField();
        textSuma.setForeground(Color.BLACK);
        textSuma.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        textSuma.setColumns(10);
        textSuma.setBounds(10, 194, 148, 19);
        f.getContentPane().add(textSuma);
    }

    private void initialize() {
        f = new JFrame();
        f.setTitle("Raport3");
        f.setBounds(100, 100, 861, 524);
        f.setVisible(true);
        f.setResizable(false);
        f.getContentPane().setLayout(null);
    }

}

