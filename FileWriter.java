package dataLayer;

import bll.MenuItem;

import java.io.IOException;
import java.util.ArrayList;

public class FileWriter {
    public FileWriter(){}

    /**
     * aici scriem in fisier
     * @param list
     * @param fileName
     */
    public static void scriereF(ArrayList<MenuItem> list, String fileName) {
        java.io.FileWriter fileW = null;
        try {
            fileW = new java.io.FileWriter(fileName);
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
}
