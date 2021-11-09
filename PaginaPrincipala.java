package presentationLayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class PaginaPrincipala extends JFrame {
    private JPanel panelPrincipal = new JPanel();
    private JButton butonAngajat;
    private JButton butonAdmin;
    private JButton butonClient;
    private JPanel content;
    /**
     * Interfata grafica pentru pagina principala
     */
    public PaginaPrincipala() {
        this.setBounds(300, 100, 860, 568);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        initialize2();
    }

    public void adaugaPanel(JPanel panelNou) {
        panelPrincipal.removeAll();
        panelPrincipal.add(panelNou);
        panelPrincipal.revalidate();
        panelPrincipal.repaint();
    }

    private void initialize2() {
        content = new JPanel();
        content.setBackground(Color.lightGray);
        this.setContentPane(content);

        JLabel labelTitlu = new JLabel("Food Delivery Management System\r\n");
        labelTitlu.setHorizontalAlignment(SwingConstants.CENTER);
        labelTitlu.setForeground(Color.BLACK);
        labelTitlu.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 44));
        labelTitlu.setBounds(0, 0, 860, 70);
        content.add(labelTitlu);

        content.setLayout(null);

        panelPrincipal = new JPanel();
        panelPrincipal.setLayout(null);
        panelPrincipal.setBounds(0, 70, 860, 568);

        content.add(panelPrincipal);

        butonAdmin = new JButton("Admin");
        butonAdmin.setForeground(Color.BLACK);
        butonAdmin.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 30));
        butonAdmin.setBounds(323, 123, 196, 48);
        panelPrincipal.add(butonAdmin);

        butonClient = new JButton("Client");
        butonClient.setForeground(Color.BLACK);
        butonClient.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 30));
        butonClient.setBounds(323, 207, 196, 48);
        panelPrincipal.add(butonClient);

        butonAngajat = new JButton("Angajat");
        butonAngajat.setForeground(Color.BLACK);
        butonAngajat.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 30));
        butonAngajat.setBounds(323, 286, 196, 48);
        panelPrincipal.add(butonAngajat);

        content.revalidate();
        content.repaint();
    }

    public void addListener(ActionListener e) {
        butonAngajat.addActionListener(e);
    }
    public void addListenerAdmin(ActionListener e) {
        butonAdmin.addActionListener(e);
    }
    public void addListenerClient(ActionListener e) {
        butonClient.addActionListener(e);
    }

}
