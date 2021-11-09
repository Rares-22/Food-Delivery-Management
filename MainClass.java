package start;


import bll.MenuItem;
import presentationLayer.*;
import java.io.*;
import java.util.ArrayList;

/**
 * Clasa de la care pornim
 */
public class MainClass {

    private static boolean existaProdus(ArrayList<MenuItem> list, String nume) {
        for (MenuItem m : list) {
            if (m.getTitlu().equals(nume))
                return true;
        }
        return false;
    }

    private static ArrayList<MenuItem> citireCSV() {
        ArrayList<MenuItem> lista = new ArrayList<>();
        String line;
        try {
            BufferedReader b = new BufferedReader(new FileReader("products.csv"));
            b.readLine();
            while ((line = b.readLine()) != null) {
                String[] values = line.split(",");
                if (existaProdus(lista, values[0]) == false) {
                    MenuItem m = new MenuItem(values[0], Float.valueOf(Float.parseFloat(values[1])), Integer.valueOf(Integer.parseInt(values[2])), Integer.valueOf(Integer.parseInt(values[3])), Integer.valueOf(Integer.parseInt(values[4])), Integer.valueOf(Integer.parseInt(values[5])), Float.valueOf(Float.parseFloat(values[6])));
                    lista.add(m);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lista;
    }

    private static void scriereFisier(ArrayList<MenuItem> list) {
        FileWriter fileW = null;
        try {
            fileW = new FileWriter("Produse.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        for(MenuItem mn:list){
            try {
                fileW.write(mn.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            fileW.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        ArrayList<MenuItem> lista = citireCSV();
        File fileW = new File("Produse.txt");
        fileW.createNewFile();
        scriereFisier(lista);

        PaginaPrincipala p = new PaginaPrincipala();
        Controller c = new Controller(p);

        p.setVisible(true);
    }
}
