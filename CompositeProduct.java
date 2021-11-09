package bll;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Aceasta clasa contine un meniu format din mai multe produse
 *
 */
public class CompositeProduct extends MenuItem {

    private String numeCompo;
    private ArrayList<MenuItem> lst;

    public CompositeProduct() {
        super();
        this.lst = new ArrayList<>();

    }

    public CompositeProduct(String title, ArrayList<MenuItem> list) {
        this.numeCompo = title;
        this.lst = list;
    }

    public float computeRaiting() {
        float r = 0;
        Iterator<MenuItem> menuItemIterator = lst.iterator();
        while (menuItemIterator.hasNext()) {
            MenuItem m = menuItemIterator.next();
            r += m.getRating();
        }
        float medie = r / lst.size();
        super.setRating(medie);
        return medie;
    }

    public int computeCalorii() {
        int c = 0;
        Iterator<MenuItem> menuItemIterator = lst.iterator();
        while (menuItemIterator.hasNext()) {
            MenuItem m = menuItemIterator.next();
            c += m.getCalorii();
        }
        super.setCalorii(c);
        return c;
    }

    public int computeProteine() {
        int p = 0;
        Iterator<MenuItem> menuItemIterator = lst.iterator();
        while (menuItemIterator.hasNext()) {
            MenuItem m = menuItemIterator.next();
            p += m.getProteine();
        }
        super.setProteine(p);
        return p;
    }

    public int computeGrasimi() {
        int g = 0;
        Iterator<MenuItem> menuItemIterator = lst.iterator();
        while (menuItemIterator.hasNext()) {
            MenuItem m = menuItemIterator.next();
            g += m.getGrasimi();
        }
        super.setGrasimi(g);
        return g;
    }

    public int computeSodiu() {
        int s = 0;
        Iterator<MenuItem> menuItemIterator = lst.iterator();
        while (menuItemIterator.hasNext()) {
            MenuItem m = menuItemIterator.next();
            s += m.getSodiu();
        }
        super.setSodiu(s);
        return s;
    }

    public float computePrice() {
        float cp = 0;
        Iterator<MenuItem> menuItemIterator = lst.iterator();
        while (menuItemIterator.hasNext()) {
            MenuItem m = menuItemIterator.next();
            cp += m.getPret();
        }
        super.setPret(cp);
        return cp;
    }


}
