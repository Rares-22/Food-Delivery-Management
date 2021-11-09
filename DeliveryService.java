package bll;

import dataLayer.FileWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

/**
 * In aceasta clasa se implementeaza metodele din IDeliveryServiceProcessing
 */
public class DeliveryService extends Observable implements IDeliveryServiceProcessing, Serializable {
    private HashMap<Order, ArrayList<MenuItem>> hmap;
    private ArrayList<MenuItem> menu;
    private FileWriter fileW = new FileWriter();

    public DeliveryService() {
        this.hmap = new HashMap<Order, ArrayList<MenuItem>>();
        this.menu = new ArrayList<MenuItem>();
        citireCSV();
    }

    private void citireCSV() {
        Scanner fileR = null;
        try {
            fileR = new Scanner(new File("Produse.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (fileR.hasNextLine()) {
            String[] values = new String[7];
            values[0] = fileR.nextLine();
            values[1] = fileR.nextLine();
            values[2] = fileR.nextLine();
            values[3] = fileR.nextLine();
            values[4] = fileR.nextLine();
            values[5] = fileR.nextLine();
            values[6] = fileR.nextLine();

            MenuItem m = new MenuItem(values[0], Float.valueOf(Float.parseFloat(values[1])), Integer.valueOf(Integer.parseInt(values[2])), Integer.valueOf(Integer.parseInt(values[3])), Integer.valueOf(Integer.parseInt(values[4])), Integer.valueOf(Integer.parseInt(values[5])), Float.valueOf(Float.parseFloat(values[6])));
            menu.add(m);
        }
        fileR.close();
    }

    public ArrayList<MenuItem> getMenu() {
        return menu;
    }

    /**
     * cauta
     * @param nume
     * @return
     */
    public MenuItem search(String nume) {
        for (MenuItem m : menu) {
            if (nume.equals(m.getTitlu()))
                return m;
        }
        return null;
    }

    /**
     * adauga
     * @param produs
     */
    @Override
    public void addProdus(MenuItem produs) {
        menu.add(produs);
        fileW.scriereF(menu, "Produse.txt");
    }

    /**
     * sterge
     * @param nume
     */
    @Override
    public void deleteProdus(String nume) {
        for (MenuItem m : menu)
            if (nume.equals(m.getTitlu()))
        menu.remove(m);
        fileW.scriereF(menu, "Produse.txt");
    }

    /**
     * face update la produs
     * @param nume
     * @param pret
     */
    @Override
    public void updateProdus(String nume, float pret) {
        MenuItem q = null;
        for (int i = 0; i < menu.size(); i++) {
            if (nume.equals(menu.get(i).getTitlu())) {
                q = menu.get(i);
                q.setPret(pret);
                menu.set(i, q);
            }
        }
        fileW.scriereF(menu, "Produse.txt");
    }

    /**
     * creaza un produs composit
     * @param title
     * @param produs1
     * @param produs2
     */
    @Override
    public void creareCompositeProduct(String title, String produs1, String produs2) {
        MenuItem m = search(produs1);
        MenuItem m1 = search(produs2);
        CompositeProduct compositeP = null;
        if (m != null && m1 != null) {
            ArrayList<MenuItem> list = new ArrayList<>();
            list.add(m);
            list.add(m1);
            compositeP = new CompositeProduct(title, list);
            MenuItem menn = new MenuItem(title, compositeP.computeRaiting(), compositeP.computeCalorii(), compositeP.computeProteine(), compositeP.computeGrasimi(), compositeP.computeSodiu(), compositeP.computePrice());
            menu.add(menn);
            fileW.scriereF(menu, "Produse.txt");
        }
    }

    /**
     * genereaza raport 1
     * @param o1
     * @param o2
     * @return
     * @throws IOException
     */
    @Override
    public ArrayList<Order> generareRaport1(int o1, int o2) throws IOException {
        Order o = new Order();
        ArrayList<Order> ord;
        ArrayList<Order> comenzi = o.getOrder();
        ord = (ArrayList<Order>) comenzi.stream().filter(cmd -> cmd.getOra() >= o1 && cmd.getOra() < o2).collect(Collectors.toList());

        return ord;
    }

    /**
     * genereaza raport 2
     * @param nr
     * @return
     * @throws IOException
     */
    @Override
    public ArrayList<MenuItem> generareRaport2(int nr) throws IOException {
        Order o = new Order();
        ArrayList<Order> comenzi = o.getOrder();

        ArrayList<MenuItem> allProducts = new ArrayList<>();
        comenzi.stream().map(Order::getComandaText).forEach(allProducts::addAll);


        Map<MenuItem, Long> map = allProducts.stream().collect(Collectors.groupingBy(p -> p,
                Collectors.counting()));

        ArrayList<MenuItem> produseFinale = (ArrayList<MenuItem>) map.keySet().stream().filter(menuitem -> map.get(menuitem) >= nr).collect(Collectors.toList());


        return produseFinale;
    }

    /**
     * genereaza raport 3
     * @param nrComenzi
     * @param suma
     * @return
     * @throws IOException
     */
    @Override
    public ArrayList<String> generareRaport3(int nrComenzi, int suma) throws IOException {
        Order o = new Order();
        ArrayList<Order> comenzi = o.getOrder();

        ArrayList<Order> comenziRamaseS = (ArrayList<Order>) comenzi.stream().filter(cmd -> cmd.getPretTotal() >= suma).collect(Collectors.toList());

        Map<String, Long> map = comenziRamaseS.stream().collect(Collectors.groupingBy(p -> p.getNumeClient(),
                Collectors.counting()));


        ArrayList<String> allClienti = (ArrayList<String>) map.keySet().stream().filter(numeClient -> map.get(numeClient) >= nrComenzi).collect(Collectors.toList());


        return allClienti;
    }

    /**
     * genereaza raport 4
     * @param nrZi
     * @return
     * @throws IOException
     */
    @Override
    public ArrayList<Order> generareRaport4(int nrZi) throws IOException {
        Order o = new Order();
        ArrayList<Order> comenzi = o.getOrder();

        ArrayList<Order> listOrder= (ArrayList<Order>) comenzi.stream().filter(order->order.getZi()==nrZi).collect(Collectors.toList());

        return listOrder;
    }

    @Override
    public float computeOrderPrice(ArrayList<MenuItem> comanda) {
        float pret = 0;
        for (MenuItem m : comanda)
            pret += m.getPret();
        return pret;
    }

    /**
     * cauta dupa nume
     * @param titlu
     * @return
     */
    @Override
    public ArrayList<MenuItem> searchTitlu(String titlu) {
        ArrayList<MenuItem> product;
        product = (ArrayList<MenuItem>) menu.stream().filter(p -> p.getTitlu().contains(titlu) == true).collect(Collectors.toList());

        return product;
    }

    /**
     * cauta dupa titlu si calorii
     * @param titlu
     * @param calorii
     * @return
     */
    @Override
    public ArrayList<MenuItem> searchTitluSiCalorii(String titlu, int calorii) {
        ArrayList<MenuItem> product;
        product = (ArrayList<MenuItem>) menu.stream().filter(p -> p.getTitlu().contains(titlu) == true && p.getCalorii() == calorii).collect(Collectors.toList());
        return product;
    }


}
