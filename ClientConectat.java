package presentationLayer;

import bll.*;
import bll.MenuItem;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

/**
 * se poate efectua comanda de catre client
 */
public class ClientConectat extends JPanel {
    private JButton back;
    private PaginaPrincipala p;
    private Clienti client;

    private JTable table;
    private JTextField textNume;
    private DefaultTableModel model;
    private DefaultTableModel modelComanda;
    private JTextField textNumeProdus;
    private JTable tableComanda;
    private JButton butonAdauga;
    private JButton butonComanda;
    private DeliveryService deliveryS = new DeliveryService();
    private ArrayList<MenuItem> comanda = new ArrayList<>();
    private JTextField textNumeProdusCautat;
    private JTextField textNumarCalorii;
    private JButton butonCauta;

    public ClientConectat(PaginaPrincipala p, Clienti client) {
        this.p = p;
        this.client = client;

        this.setLayout(null);
        this.setBounds(0, 0, 860, 568);

        back = new JButton("Back");
        back.setBounds(5, 5, 65, 25);

        actionBack();
        dProduct();
        comandaClient();
        actionCommand();
        jLabelCommand();
        cautaC2();
        jButon();

        this.add(back);
        this.revalidate();
        this.repaint();
    }

    private void cautaC2(){
        JLabel lblNumeProdusCautat = new JLabel("Nume Produs");
        lblNumeProdusCautat.setHorizontalAlignment(SwingConstants.CENTER);
        lblNumeProdusCautat.setForeground(Color.BLACK);
        lblNumeProdusCautat.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        lblNumeProdusCautat.setBounds(294, 310, 234, 23);
        this.add(lblNumeProdusCautat);

        textNumeProdusCautat = new JTextField();
        textNumeProdusCautat.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        textNumeProdusCautat.setColumns(10);
        textNumeProdusCautat.setBounds(294, 333, 234, 23);
        this.add(textNumeProdusCautat);

        textNumarCalorii = new JTextField();
        textNumarCalorii.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        textNumarCalorii.setColumns(10);
        textNumarCalorii.setBounds(348, 383, 125, 23);
        this.add(textNumarCalorii);

        JLabel lblNumarCalorii = new JLabel("Numar Calorii");
        lblNumarCalorii.setHorizontalAlignment(SwingConstants.CENTER);
        lblNumarCalorii.setForeground(Color.BLACK);
        lblNumarCalorii.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        lblNumarCalorii.setBounds(294, 360, 234, 23);
        this.add(lblNumarCalorii);

        butonCauta = new JButton("Cauta");
        butonCauta.setForeground(Color.BLACK);
        butonCauta.setFont(new Font("Times New Roman", Font.ITALIC, 14));
        butonCauta.setBounds(371, 416, 85, 21);
        this.add(butonCauta);
    }

    private void jLabelCommand() {
        JLabel lblProdus = new JLabel("Produse");
        lblProdus.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 17));
        lblProdus.setForeground(Color.BLACK);
        lblProdus.setHorizontalAlignment(SwingConstants.CENTER);
        lblProdus.setBounds(256, 0, 594, 23);
        this.add(lblProdus);

        JLabel lblNumeProdus = new JLabel("Nume Produs");
        lblNumeProdus.setHorizontalAlignment(SwingConstants.CENTER);
        lblNumeProdus.setForeground(Color.BLACK);
        lblNumeProdus.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        lblNumeProdus.setBounds(10, 27, 234, 23);
        this.add(lblNumeProdus);
    }

    private void actionCommand() {
        butonAdauga.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                comanda.add(deliveryS.search(textNumeProdus.getText()));
                tableComanda();
                textNumeProdus.setText("");
            }
        });

        butonComanda.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                Date date =new Date();

                Order or = new Order(client.getNume(), comanda, date, deliveryS.computeOrderPrice(comanda));

                comanda = new ArrayList<>();

                ArrayList<Order> list =null;
                try {
                    list = or.getOrder();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                if(list ==null)
                    list =new ArrayList<>();
                list.add(or);
                try {
                    or.writeOrder(list);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                tableComanda();
                showAlert("Comanda efectuata. Va multumim!");
            }
        });
    }

    private void tableComanda() {
        modelComanda = new DefaultTableModel();
        Object[] column = {"Comanda"};
        Object[] row = new Object[2];

        modelComanda.setColumnIdentifiers(column);
        tableComanda.setModel(modelComanda);

        for (MenuItem m : comanda) {
            row[0] = m.getTitlu();
            modelComanda.addRow(row);
        }
        row[0] = "Pretul total: " + deliveryS.computeOrderPrice(comanda) + " Ron";
        modelComanda.addRow(row);
    }

    private void comandaClient() {
        JScrollPane scrollPane1 = new JScrollPane();
        scrollPane1.setBounds(10, 123, 234, 200);
        this.add(scrollPane1);

        tableComanda = new JTable();
        tableComanda.setForeground(Color.BLACK);
        tableComanda.setFont(new Font("Times New Roman", Font.PLAIN, 10));
        scrollPane1.setViewportView(tableComanda);
        tableComanda();

        textNumeProdus = new JTextField();
        textNumeProdus.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        textNumeProdus.setBounds(10, 54, 234, 23);
        this.add(textNumeProdus);
        textNumeProdus.setColumns(10);

        butonAdauga = new JButton("Adauga");
        butonAdauga.setFont(new Font("Times New Roman", Font.ITALIC, 14));
        butonAdauga.setForeground(Color.BLACK);
        butonAdauga.setBounds(85, 87, 85, 21);
        this.add(butonAdauga);

        butonComanda = new JButton("Comanda");
        butonComanda.setForeground(Color.BLACK);
        butonComanda.setFont(new Font("Times New Roman", Font.ITALIC, 14));
        butonComanda.setBounds(73, 333, 97, 21);
        this.add(butonComanda);
    }

    private void actionBack() {
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Client c = null;
                try {
                    c = new Client(p);
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }
                p.adaugaPanel(c);
            }
        });
    }

    private void tabel(int nr) {
        model = new DefaultTableModel();
        Object[] column = {"Title", "Rating", "Calories", "Protein", "Fat", "Sodium", "Price"};
        Object[] row = new Object[7];

        model.setColumnIdentifiers(column);
        table.setModel(model);

        ArrayList<MenuItem> l = null;
        if (nr == 0) {
            DeliveryService prd = new DeliveryService();
            l = prd.getMenu();
            l.remove(l.size() - 1);
        } else if(nr==1) {
            l = deliveryS.searchTitlu(textNume.getText());
        }else if(nr==2){
            l = deliveryS.searchTitluSiCalorii(textNumeProdusCautat.getText(),Integer.valueOf(Integer.parseInt(textNumarCalorii.getText())));
        }
        for (MenuItem m : l) {
            row[0] = m.getTitlu();
            row[1] = m.getRating();
            row[2] = m.getCalorii();
            row[3] = m.getProteine();
            row[4] = m.getGrasimi();
            row[5] = m.getSodiu();
            row[6] = m.getPret();
            model.addRow(row);
        }
    }

    private void jButon() {
        JButton butonViewAll = new JButton("View all");
        butonViewAll.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tabel(0);
            }
        });
        butonViewAll.setFont(new Font("Times New Roman", Font.BOLD, 12));
        butonViewAll.setForeground(Color.BLACK);
        butonViewAll.setBounds(711, 317, 85, 21);
        this.add(butonViewAll);

        JButton butonViewNume = new JButton("View ");
        butonViewNume.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tabel(1);
            }
        });
        butonViewNume.setForeground(Color.BLACK);
        butonViewNume.setFont(new Font("Times New Roman", Font.BOLD, 12));
        butonViewNume.setBounds(711, 417, 85, 21);
        this.add(butonViewNume);

        butonCauta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tabel(2);
            }
        });
    }

    private void dProduct() {
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(254, 26, 596, 263);
        this.add(scrollPane);

        table = new JTable();
        table.setForeground(Color.BLACK);
        table.setFont(new Font("Times New Roman", Font.PLAIN, 10));
        scrollPane.setViewportView(table);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        ListSelectionModel selectT = table.getSelectionModel();
        selectT.addListSelectionListener(this::selectTable);

        tabel(0);

        JLabel lblProdus = new JLabel("Produse");
        lblProdus.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 17));
        lblProdus.setForeground(Color.BLACK);
        lblProdus.setHorizontalAlignment(SwingConstants.CENTER);
        lblProdus.setBounds(256, 0, 594, 23);
        this.add(lblProdus);

        JLabel labelNume = new JLabel("Nume");
        labelNume.setHorizontalAlignment(SwingConstants.CENTER);
        labelNume.setFont(new Font("Times New Roman", Font.PLAIN, 10));
        labelNume.setForeground(Color.BLACK);
        labelNume.setBounds(656, 363, 194, 21);
        this.add(labelNume);

        textNume = new JTextField();
        textNume.setBounds(656, 382, 192, 23);
        this.add(textNume);
        textNume.setColumns(10);
    }
    public static void showAlert(String msg) {
        JLabel allertM = new JLabel(msg);
        allertM.setFont(new Font("Tahoma", Font.PLAIN, 20));
        JOptionPane.showMessageDialog(null, allertM);
    }
    private void selectTable(ListSelectionEvent listSelectionEvent) {
        if (listSelectionEvent.getValueIsAdjusting())
            return;

        DefaultListSelectionModel mdl = (DefaultListSelectionModel) listSelectionEvent.getSource();
        int selectI = mdl.getAnchorSelectionIndex();
        if (selectI == -1)
            return;

        textNumeProdus.setText(table.getValueAt(selectI, 0).toString());
    }

}
