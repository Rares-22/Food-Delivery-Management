package presentationLayer;

import bll.DeliveryService;
import bll.MenuItem;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * clasa corespunzatoare interfetei administratorului
 */
public class Administrator extends JPanel {
    private int k = 0;
    private DeliveryService dvs = new DeliveryService();
    private JButton back;
    private JButton butonNewProd;
    private JButton butonEdit;
    private JButton butonDelete;
    private JButton butonAdauga;

    private JTable table;
    private DefaultTableModel model;

    private JTextField textNumeProdus;
    private JTextField textRating;
    private JTextField textCalorii;
    private JTextField textProteine;
    private JTextField textPret;
    private JTextField textGrarsimi;
    private JTextField textSodiu;
    private JTextField textNumeComposite;
    private JTextField textProdus1;
    private JTextField textProdus2;

    public Administrator() {
        this.setLayout(null);
        this.setBounds(0, 0, 860, 568);
        back = new JButton("Back");
        back.setBounds(5, 5, 65, 25);

        mProduct();
        butonAdmin();
        lblAdmin();
        textAdmin();
        actionButon();
        compositeProduct();
        generateRP();

        this.add(back);
        this.revalidate();
        this.repaint();
    }

    public void generateRP() {
        JComboBox comboBox = new JComboBox();
        comboBox.setModel(new DefaultComboBoxModel(new String[]{"Selectati", "Raport 1", "Raport 2", "Raport 3", "Raport 4"}));
        comboBox.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        comboBox.setForeground(Color.BLACK);
        comboBox.setBounds(338, 409, 155, 32);
        this.add(comboBox);

        JButton btnNewButton = new JButton("Generare");
        btnNewButton.setForeground(Color.BLACK);
        btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 14));
        btnNewButton.setBounds(529, 409, 113, 32);
        this.add(btnNewButton);

        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String s = (String) comboBox.getSelectedItem();
                if (s.equals("Raport 1")) {
                    new Raport1();
                } else if (s.equals("Raport 2")) {
                    new Raport2();
                } else if (s.equals("Raport 3")) {
                    new Raport3();
                } else if (s.equals("Raport 4")) {
                    new Raport4();
                }


            }
        });
    }

    public void addListener(ActionListener e) {
        back.addActionListener(e);
    }

    private void tabel(int nr) {
        model = new DefaultTableModel();
        Object[] column = {"Title", "Raiting", "Calories", "Protein", "Fat", "Sodium", "Price"};
        Object[] row = new Object[7];

        model.setColumnIdentifiers(column);
        table.setModel(model);

        ArrayList<MenuItem> lista = null;
        DeliveryService produs = new DeliveryService();
        lista = produs.getMenu();
        lista.remove(lista.size() - 1);

        for (MenuItem m : lista) {
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

    private void butonAdmin() {
        butonNewProd = new JButton("Add New");
        butonNewProd.setFont(new Font("Times New Roman", Font.BOLD, 12));
        butonNewProd.setForeground(Color.BLACK);
        butonNewProd.setBounds(10, 201, 91, 21);
        this.add(butonNewProd);

        butonEdit = new JButton("Edit");
        butonEdit.setForeground(Color.BLACK);
        butonEdit.setFont(new Font("Times New Roman", Font.BOLD, 12));
        butonEdit.setBounds(153, 201, 91, 21);
        this.add(butonEdit);

        butonDelete = new JButton("Delete");
        butonDelete.setForeground(Color.BLACK);
        butonDelete.setFont(new Font("Times New Roman", Font.BOLD, 12));
        butonDelete.setBounds(86, 232, 85, 21);
        this.add(butonDelete);

        butonAdauga = new JButton("Adauga");
        butonAdauga.setForeground(Color.BLACK);
        butonAdauga.setFont(new Font("Times New Roman", Font.BOLD, 12));
        butonAdauga.setBounds(75, 421, 85, 21);
        this.add(butonAdauga);
    }

    private void actionButon() {
        butonNewProd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MenuItem produs = new MenuItem(textNumeProdus.getText(), Float.valueOf(Float.parseFloat(textRating.getText())),
                        Integer.valueOf(Integer.parseInt(textCalorii.getText())), Integer.valueOf(Integer.parseInt(textProteine.getText())),
                        Integer.valueOf(Integer.parseInt(textGrarsimi.getText())), Integer.valueOf(Integer.parseInt(textSodiu.getText())),
                        Float.valueOf(Float.parseFloat(textPret.getText())));
                dvs.addProdus(produs);
                tabel(0);
            }
        });
        butonEdit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dvs.updateProdus(textNumeProdus.getText(), Float.valueOf((Float.parseFloat(textPret.getText()))));
                tabel(0);
            }
        });
        butonDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dvs.deleteProdus(textNumeProdus.getText());
                tabel(0);
            }
        });
        butonAdauga.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dvs.creareCompositeProduct(textNumeComposite.getText(), textProdus1.getText(), textProdus2.getText());
                tabel(0);

            }
        });
    }

    private void lblAdmin() {
        JLabel lblNumeProd = new JLabel("Nume Produs");
        lblNumeProd.setHorizontalAlignment(SwingConstants.CENTER);
        lblNumeProd.setForeground(Color.BLACK);
        lblNumeProd.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        lblNumeProd.setBounds(10, 27, 234, 23);
        this.add(lblNumeProd);

        JLabel lblRaiting = new JLabel("Rating");
        lblRaiting.setHorizontalAlignment(SwingConstants.CENTER);
        lblRaiting.setForeground(Color.BLACK);
        lblRaiting.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        lblRaiting.setBounds(10, 81, 70, 23);
        this.add(lblRaiting);

        JLabel lblCalorii = new JLabel("Calorii");
        lblCalorii.setHorizontalAlignment(SwingConstants.CENTER);
        lblCalorii.setForeground(Color.BLACK);
        lblCalorii.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        lblCalorii.setBounds(90, 81, 70, 23);
        this.add(lblCalorii);

        JLabel lblProteine = new JLabel("Proteine");
        lblProteine.setHorizontalAlignment(SwingConstants.CENTER);
        lblProteine.setForeground(Color.BLACK);
        lblProteine.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        lblProteine.setBounds(174, 81, 70, 23);
        this.add(lblProteine);

        JLabel lblSodiu = new JLabel("Sodiu");
        lblSodiu.setHorizontalAlignment(SwingConstants.CENTER);
        lblSodiu.setForeground(Color.BLACK);
        lblSodiu.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        lblSodiu.setBounds(90, 141, 70, 23);
        this.add(lblSodiu);

        JLabel lblPret = new JLabel("Pret");
        lblPret.setHorizontalAlignment(SwingConstants.CENTER);
        lblPret.setForeground(Color.BLACK);
        lblPret.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        lblPret.setBounds(174, 141, 70, 23);
        this.add(lblPret);

        JLabel lblGrasimi = new JLabel("Grasimi");
        lblGrasimi.setHorizontalAlignment(SwingConstants.CENTER);
        lblGrasimi.setForeground(Color.BLACK);
        lblGrasimi.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        lblGrasimi.setBounds(10, 141, 70, 23);
        this.add(lblGrasimi);
    }

    private void textAdmin() {
        textNumeProdus = new JTextField();
        textNumeProdus.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        textNumeProdus.setBounds(10, 54, 234, 23);
        this.add(textNumeProdus);
        textNumeProdus.setColumns(10);

        textRating = new JTextField();
        textRating.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        textRating.setColumns(10);
        textRating.setBounds(10, 108, 70, 23);
        this.add(textRating);

        textCalorii = new JTextField();
        textCalorii.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        textCalorii.setColumns(10);
        textCalorii.setBounds(90, 108, 70, 23);
        this.add(textCalorii);

        textProteine = new JTextField();
        textProteine.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        textProteine.setColumns(10);
        textProteine.setBounds(174, 108, 70, 23);
        this.add(textProteine);

        textPret = new JTextField();
        textPret.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        textPret.setColumns(10);
        textPret.setBounds(174, 168, 70, 23);
        this.add(textPret);

        textGrarsimi = new JTextField();
        textGrarsimi.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        textGrarsimi.setColumns(10);
        textGrarsimi.setBounds(10, 168, 70, 23);
        this.add(textGrarsimi);

        textSodiu = new JTextField();
        textSodiu.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        textSodiu.setColumns(10);
        textSodiu.setBounds(90, 168, 70, 23);
        this.add(textSodiu);
    }

    private void selectTable(ListSelectionEvent listSelectionEvent) {
        if (listSelectionEvent.getValueIsAdjusting())
            return;

        DefaultListSelectionModel t = (DefaultListSelectionModel) listSelectionEvent.getSource();
        int selectI = t.getAnchorSelectionIndex();
        if (selectI == -1)
            return;

        textNumeProdus.setText(table.getValueAt(selectI, 0).toString());
        textRating.setText(table.getValueAt(selectI, 1).toString());
        textCalorii.setText(table.getValueAt(selectI, 2).toString());
        textProteine.setText(table.getValueAt(selectI, 3).toString());
        textGrarsimi.setText(table.getValueAt(selectI, 4).toString());
        textSodiu.setText(table.getValueAt(selectI, 5).toString());
        textPret.setText(table.getValueAt(selectI, 6).toString());
        if (k % 2 == 0) {
            k++;
            textProdus1.setText(table.getValueAt(selectI, 0).toString());
        } else {
            k++;
            textProdus2.setText(table.getValueAt(selectI, 0).toString());
        }
    }

    private void compositeProduct() {
        textNumeComposite = new JTextField();
        textNumeComposite.setForeground(Color.BLACK);
        textNumeComposite.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        textNumeComposite.setColumns(10);
        textNumeComposite.setBounds(10, 290, 234, 23);
        this.add(textNumeComposite);

        JLabel lblNumeComposite = new JLabel("Nume Composite");
        lblNumeComposite.setHorizontalAlignment(SwingConstants.CENTER);
        lblNumeComposite.setForeground(Color.BLACK);
        lblNumeComposite.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        lblNumeComposite.setBounds(10, 263, 234, 23);
        this.add(lblNumeComposite);

        JLabel lblProdus = new JLabel("Produs1");
        lblProdus.setHorizontalAlignment(SwingConstants.CENTER);
        lblProdus.setForeground(Color.BLACK);
        lblProdus.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        lblProdus.setBounds(10, 312, 234, 23);
        this.add(lblProdus);

        textProdus1 = new JTextField();
        textProdus1.setForeground(Color.BLACK);
        textProdus1.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        textProdus1.setColumns(10);
        textProdus1.setBounds(10, 339, 234, 23);
        this.add(textProdus1);

        JLabel lblProdus_1 = new JLabel("Produs2");
        lblProdus_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblProdus_1.setForeground(Color.BLACK);
        lblProdus_1.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        lblProdus_1.setBounds(10, 361, 234, 23);
        this.add(lblProdus_1);

        textProdus2 = new JTextField();
        textProdus2.setForeground(Color.BLACK);
        textProdus2.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        textProdus2.setColumns(10);
        textProdus2.setBounds(10, 388, 234, 23);
        this.add(textProdus2);
    }

    private void mProduct() {
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(254, 26, 596, 365);
        this.add(scrollPane);

        table = new JTable();
        table.setForeground(Color.BLACK);
        table.setFont(new Font("Times New Roman", Font.PLAIN, 10));
        scrollPane.setViewportView(table);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        ListSelectionModel selectTb = table.getSelectionModel();
        selectTb.addListSelectionListener(this::selectTable);
        tabel(0);

        JLabel lblProd = new JLabel("Produse");
        lblProd.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 17));
        lblProd.setForeground(Color.BLACK);
        lblProd.setHorizontalAlignment(SwingConstants.CENTER);
        lblProd.setBounds(256, 0, 594, 23);
        this.add(lblProd);
    }
}

