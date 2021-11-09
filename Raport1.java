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
 * Pentru interfata grafica RAp1
 */
public class Raport1 {

    private JFrame f;
    private JTextField textOra1;
    private JTextField textOra2;
    private JTextArea textArea;
    private JButton butonGenereaza;
    private DeliveryService deliveryS =new DeliveryService();

    private Order o;

    public Raport1() {
        initialize();
        initialize1();
        actionBtn();
        o=new Order();
    }

    private void actionBtn() {
        butonGenereaza = new JButton("Genereaza");
        butonGenereaza.setForeground(Color.BLACK);
        butonGenereaza.setFont(new Font("Times New Roman", Font.BOLD, 12));
        butonGenereaza.setBounds(37, 272, 92, 27);
        f.getContentPane().add(butonGenereaza);

        butonGenereaza.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int a=Integer.parseInt(textOra1.getText());
                int b=Integer.parseInt(textOra2.getText());
                ArrayList<Order> or =null;
                try {
                    or = deliveryS.generareRaport1(a,b);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                String str =new String();
                for(Order o: or){
                    str +=o.toString();
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

        JLabel lblOra1 = new JLabel("Ora 1");
        lblOra1.setHorizontalAlignment(SwingConstants.CENTER);
        lblOra1.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        lblOra1.setForeground(Color.BLACK);
        lblOra1.setBounds(10, 150, 148, 27);
        f.getContentPane().add(lblOra1);
        textOra1 = new JTextField();
        textOra1.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        textOra1.setForeground(Color.BLACK);
        textOra1.setBounds(10, 173, 148, 19);
        f.getContentPane().add(textOra1);
        textOra1.setColumns(10);

        JLabel lblOra2 = new JLabel("Ora 2");
        lblOra2.setHorizontalAlignment(SwingConstants.CENTER);
        lblOra2.setForeground(Color.BLACK);
        lblOra2.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        lblOra2.setBounds(10, 200, 148, 27);
        f.getContentPane().add(lblOra2);
        textOra2 = new JTextField();
        textOra2.setForeground(Color.BLACK);
        textOra2.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        textOra2.setColumns(10);
        textOra2.setBounds(10, 223, 148, 19);
        f.getContentPane().add(textOra2);
    }

    private void initialize() {
        f = new JFrame();
        f.setTitle("Raport1");
        f.setBounds(100, 100, 861, 524);
        f.setVisible(true);
        f.getContentPane().setLayout(null);
        f.setResizable(false);

    }

}

