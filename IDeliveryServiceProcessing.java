package bll;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * aceasta interfata este implementata de catre DeliveryService
 */
public interface IDeliveryServiceProcessing {

     void addProdus(MenuItem produs);
     void deleteProdus(String nume);
     void updateProdus(String nume, float pret);
     void creareCompositeProduct(String title, String produs1, String produs2);
    ArrayList<Order> generareRaport1(int o1, int o2) throws IOException;
    ArrayList<MenuItem> generareRaport2(int nr) throws IOException;
    ArrayList<String> generareRaport3(int nrComenzi, int suma) throws IOException;
    ArrayList<Order> generareRaport4(int nrZi) throws IOException;


    float computeOrderPrice(ArrayList<MenuItem> comanda);
    ArrayList<MenuItem> searchTitlu(String titlu);
    ArrayList<MenuItem> searchTitluSiCalorii(String titlu, int calorii);


}
