package presentationLayer;

import bll.DeliveryService;
import bll.Order;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;
/**
 * Pentru interfata grafica Raport4
 */
public class Raport4 {

    private JFrame f;
    private JTextArea textArea;
    private JComboBox comboB;
    private JButton butonGenereaza;
    private DeliveryService deliveryS = new DeliveryService();

    public Raport4() {
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
                int numarZi = comboB.getSelectedIndex();
                if (numarZi == 0)
                    numarZi = -1;
                else if (numarZi == 7)
                    numarZi = 0;
                if (numarZi != -1) {
                    ArrayList<Order> listO = null;
                    try {
                        listO = deliveryS.generareRaport4(numarZi);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                    String s = new String();
                    for (Order ord : listO) {
                        s += ord.toString();
                    }
                    textArea.setText("");
                    textArea.append(s);
                }
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

        JLabel labelNumar = new JLabel("Numar Zi");
        labelNumar.setHorizontalAlignment(SwingConstants.CENTER);
        labelNumar.setForeground(Color.BLACK);
        labelNumar.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        labelNumar.setBounds(10, 183, 148, 27);
        f.getContentPane().add(labelNumar);

        comboB = new JComboBox();
        comboB.setModel(new DefaultComboBoxModel(new String[]{"Alegeti zi", "Luni", "Marti", "Miercuri", "Joi", "Vineri", "Sambata", "Duminica"}));
        comboB.setForeground(Color.BLACK);
        comboB.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        comboB.setBounds(10, 200, 143, 27);
        f.getContentPane().add(comboB);
    }

    private void initialize() {
        f = new JFrame();
        f.setTitle("Raport 4");
        f.setBounds(100, 100, 861, 524);
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
        f.setResizable(false);
        f.getContentPane().setLayout(null);
    }

}

