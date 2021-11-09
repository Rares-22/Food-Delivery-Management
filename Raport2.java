package presentationLayer;

import bll.DeliveryService;
import bll.MenuItem;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;
/**
 * Pentru interfata grafica raport2
 */
public class Raport2 {

    private JFrame f;
    private JTextField textNumarProdus;
    private JTextArea textArea;
    private JButton butonGenereaza;
    private DeliveryService deliveryS = new DeliveryService();

    public Raport2() {
        initialize();
        initialize1();
        action();
    }

    private void action() {
        butonGenereaza.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int a = Integer.parseInt(textNumarProdus.getText());

                ArrayList<MenuItem> product = null;
                try {
                    product = deliveryS.generareRaport2(a);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                String s = new String();
                if(product ==null)
                    product =new ArrayList<>();
                for (MenuItem p : product) {
                    s += p.getTitlu()+"\n";
                }
                textArea.setText("");
                textArea.append(s);
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

        JLabel lblOra2 = new JLabel("Numar Produse");
        lblOra2.setHorizontalAlignment(SwingConstants.CENTER);
        lblOra2.setForeground(Color.BLACK);
        lblOra2.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        lblOra2.setBounds(10, 200, 148, 27);
        f.getContentPane().add(lblOra2);

        textNumarProdus = new JTextField();
        textNumarProdus.setForeground(Color.BLACK);
        textNumarProdus.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        textNumarProdus.setColumns(10);
        textNumarProdus.setBounds(10, 223, 148, 19);
        f.getContentPane().add(textNumarProdus);

        butonGenereaza = new JButton("Genereaza");
        butonGenereaza.setForeground(Color.BLACK);
        butonGenereaza.setFont(new Font("Times New Roman", Font.BOLD, 12));
        butonGenereaza.setBounds(37, 272, 92, 27);
        f.getContentPane().add(butonGenereaza);
    }

    private void initialize() {
        f = new JFrame();
        f.setTitle("Raport2");
        f.setBounds(100, 100, 861, 524);
        f.getContentPane().setLayout(null);
        f.setVisible(true);
        f.setResizable(false);
    }

}

