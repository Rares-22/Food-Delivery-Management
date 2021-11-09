package bll;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import static java.nio.file.StandardOpenOption.APPEND;

/**
 *
 * aceasta clasa contine comenzile facute de catre clienti
 */
public class Order implements Serializable {
    @Serial
    private static final long serialVersion = 4430233860839576402L;
    private String numeClient;
    private ArrayList<MenuItem> comanda;
    private Date orederDate;
    private float pretTotal;

    public Order() {
    }

    public Order(String numeClient, ArrayList<MenuItem> comanda, Date orederDate, float pretTotal) {
        this.numeClient = numeClient;
        this.comanda = comanda;
        this.orederDate = orederDate;
        this.pretTotal = pretTotal;
        writeFile(toString());
    }

    public int getOra() {
        return orederDate.getHours();
    }

    public String getNumeClient() {
        return numeClient;
    }

    public float getPretTotal() {
        return pretTotal;
    }

    public int getZi(){
        return orederDate.getDay();
    }


    public ArrayList<Order> getOrder() throws IOException {
        ArrayList<Order> o = null;
        File fileCmd = new File("Comanda.ser");
        if (fileCmd.exists()) {
            FileInputStream file = new FileInputStream(fileCmd);
            ObjectInputStream in = new ObjectInputStream(file);
            try {
                o = (ArrayList<Order>) in.readObject();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            in.close();
            file.close();
        }
        if (o == null)
            o = new ArrayList<>();
        return o;
    }

    public void writeOrder(ArrayList<Order> ord) throws IOException {
        FileOutputStream f = new FileOutputStream("Comanda.ser");
        ObjectOutputStream out = new ObjectOutputStream(f);
        out.writeObject(ord);
        out.close();
        f.close();
    }

    public ArrayList<MenuItem> getComandaText() {
        return comanda;
    }

    private void writeFile(String str) {
        OutputStream out = null;
        try {
            out = Files.newOutputStream(Paths.get("Comenzi.txt"), APPEND);

        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        PrintWriter print = new PrintWriter(out);
        print.println(str);


        print.close();
    }

    @Override
    public String toString() {
        SimpleDateFormat s1 = new SimpleDateFormat("HH:mm:ss", new Locale("en", "EN"));
        SimpleDateFormat s2 = new SimpleDateFormat("dd/MM/yyyy", new Locale("en", "EN"));
        String s = "Comanda facuta de: " +
                numeClient + "\nLa data de:\n" + s2.format(orederDate) + "\nLa ora:\n" + s1.format(orederDate) + "\n ";
        for (MenuItem m : comanda) {
            s += "-" + m.getTitlu() + ", Pret: " + m.getPret() + " Ron\n  ";
        }
        s += "    Pretul total este: " + pretTotal + " Ron\n\n";
        return s;
    }
}
