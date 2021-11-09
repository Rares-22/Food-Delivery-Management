package presentationLayer;

import bll.Clienti;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import static java.nio.file.StandardOpenOption.*;

/**
 * <p>se poate face inregistrarea si logarea de catre client</p>
 */
public class Client extends JPanel {
    private JButton back;
    private JButton butonInregistrare;
    private JButton butonConectare;

    private JTextField textNume;
    private JTextField textEmail;
    private JTextField textUsername;
    private JTextField textUsernameC;
    private JPasswordField passwordI;
    private JPasswordField passwordC;

    private ArrayList<Clienti> clienti;
    private PaginaPrincipala p;

    public Client(PaginaPrincipala p) throws FileNotFoundException {
        this.p =p;
        this.setLayout(null);
        this.setBounds(0, 0, 860, 568);

        back = new JButton("Back");
        back.setBounds(5, 5, 65, 25);

        array();
        initialize();
        initializeButon();
        initializeLabel();

        butonActionI();
        butonActionC();
        butonActionB();

        this.add(back);
        this.revalidate();
        this.repaint();
    }

    private void initializeButon() {
        butonInregistrare = new JButton("Inregistrare");
        butonInregistrare.setForeground(Color.BLACK);
        butonInregistrare.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
        butonInregistrare.setBounds(78, 285, 116, 30);
        this.add(butonInregistrare);

        butonConectare = new JButton("Conectare");
        butonConectare.setForeground(Color.BLACK);
        butonConectare.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
        butonConectare.setBounds(602, 231, 116, 30);
        this.add(butonConectare);
    }

    private void initializeLabel() {
        JLabel labelNewLabel = new JLabel("Inregistrare");
        labelNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        labelNewLabel.setForeground(Color.BLACK);
        labelNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
        labelNewLabel.setBounds(10, 72, 283, 24);
        this.add(labelNewLabel);

        JLabel labelNume = new JLabel("Nume:");
        labelNume.setHorizontalAlignment(SwingConstants.LEFT);
        labelNume.setForeground(Color.BLACK);
        labelNume.setFont(new Font("Times New Roman", Font.BOLD, 15));
        labelNume.setBounds(0, 125, 59, 24);
        this.add(labelNume);

        JLabel labelEmail = new JLabel("Email:");
        labelEmail.setHorizontalAlignment(SwingConstants.LEFT);
        labelEmail.setForeground(Color.BLACK);
        labelEmail.setFont(new Font("Times New Roman", Font.BOLD, 15));
        labelEmail.setBounds(0, 159, 59, 24);
        this.add(labelEmail);

        JLabel labelUsername = new JLabel("Username:");
        labelUsername.setHorizontalAlignment(SwingConstants.LEFT);
        labelUsername.setForeground(Color.BLACK);
        labelUsername.setFont(new Font("Times New Roman", Font.BOLD, 15));
        labelUsername.setBounds(0, 193, 81, 24);
        this.add(labelUsername);

        JLabel labelParola = new JLabel("Parola:");
        labelParola.setHorizontalAlignment(SwingConstants.LEFT);
        labelParola.setForeground(Color.BLACK);
        labelParola.setFont(new Font("Times New Roman", Font.BOLD, 15));
        labelParola.setBounds(0, 228, 59, 24);
        this.add(labelParola);

        JLabel labelConectare = new JLabel("Conectare");
        labelConectare.setHorizontalAlignment(SwingConstants.CENTER);
        labelConectare.setForeground(Color.BLACK);
        labelConectare.setFont(new Font("Times New Roman", Font.BOLD, 20));
        labelConectare.setBounds(511, 89, 283, 24);
        this.add(labelConectare);

        JLabel labelUsernameC = new JLabel("Username:");
        labelUsernameC.setHorizontalAlignment(SwingConstants.LEFT);
        labelUsernameC.setForeground(Color.BLACK);
        labelUsernameC.setFont(new Font("Times New Roman", Font.BOLD, 15));
        labelUsernameC.setBounds(524, 142, 81, 24);
        this.add(labelUsernameC);

        JLabel labelParolaC = new JLabel("Parola:");
        labelParolaC.setHorizontalAlignment(SwingConstants.LEFT);
        labelParolaC.setForeground(Color.BLACK);
        labelParolaC.setFont(new Font("Times New Roman", Font.BOLD, 15));
        labelParolaC.setBounds(524, 177, 59, 24);
        this.add(labelParolaC);
    }

    private void initialize() {
        textNume = new JTextField("");
        textNume.setForeground(Color.BLACK);
        textNume.setBounds(49, 127, 221, 23);
        this.add(textNume);
        textNume.setColumns(10);

        textEmail = new JTextField("");
        textEmail.setForeground(Color.BLACK);
        textEmail.setColumns(10);
        textEmail.setBounds(49, 161, 221, 23);
        this.add(textEmail);

        textUsername = new JTextField("");
        textUsername.setForeground(Color.BLACK);
        textUsername.setColumns(10);
        textUsername.setBounds(78, 195, 192, 23);
        this.add(textUsername);

        passwordI = new JPasswordField("");
        passwordI.setEchoChar('*');
        passwordI.setBounds(58, 228, 212, 23);
        this.add(passwordI);

        textUsernameC = new JTextField("");
        textUsernameC.setForeground(Color.BLACK);
        textUsernameC.setColumns(10);
        textUsernameC.setBounds(602, 144, 192, 23);
        this.add(textUsernameC);

        passwordC = new JPasswordField("");
        passwordC.setEchoChar('*');
        passwordC.setBounds(582, 176, 212, 23);
        this.add(passwordC);
    }

    public static void showAlert(String msg) {
        JLabel allertM = new JLabel(msg);
        allertM.setFont(new Font("Tahoma", Font.PLAIN, 20));
        JOptionPane.showMessageDialog(null, allertM);
    }

    private void butonActionI() {
        butonInregistrare.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (textNume.getText().equals("") || textEmail.getText().equals("") || textUsername.getText().equals("") || passwordI.getText().equals("")) {
                    showAlert("Introduceti date in toate campurile!");
                }
                else{
                    Clienti c = new Clienti(textNume.getText(), textEmail.getText(), textUsername.getText(), passwordI.getText());
                    clienti.add(c);
                    OutputStream out = null;
                    try {
                        out = Files.newOutputStream(Paths.get("Clienti.txt"),APPEND);

                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                    PrintWriter print = new PrintWriter(out);
                    print.println(c.toString());
                    textEmail.setText("");
                    textNume.setText("");
                    textUsername.setText("");
                    passwordI.setText("");
                    showAlert("S-a creat cu succes!");
                    print.close();
                }

            }
        });
    }
    private void array() {
        Clienti c=new Clienti();
        clienti=c.getClienti();
    }
    private void butonActionB(){
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Client.this.p.setVisible(false);
                PaginaPrincipala p =new PaginaPrincipala();
                Controller c=new Controller(p);
                p.setVisible(true);
            }
        });
    }
    private void butonActionC(){
        butonConectare.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Clienti cient = null;
                int ok=0;
                for(Clienti c: clienti){
                    if(c.getUsername().equals(textUsernameC.getText())&&c.getPassword().equals(passwordC.getText()))
                    {
                        ok=1;
                        cient =c;
                        break;
                    }
                }
                if(ok==1){
                    ClientConectat client=new ClientConectat(p, cient);
                    p.adaugaPanel(client);
                    showAlert("S-a conectat cu succes!");
                }
                else{
                    showAlert("Username sau Parola gresite!");
                }
            }
        });
    }
}
