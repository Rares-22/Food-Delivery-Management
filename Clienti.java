package bll;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Aceasta clasa contine clientii
 */
public class Clienti {
    private String nume;
    private String email;
    private String username;
    private String password;

    public Clienti() {
    }

    public Clienti(String nume, String email, String username, String password) {
        this.nume = nume;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<Clienti> getClienti() {
        ArrayList<Clienti> clienti = new ArrayList<>();
        File f = new File("Clienti.txt");
        Scanner fRead = null;
        try {
            fRead = new Scanner(f);
        } catch (Exception e) {
            try {
                throw new FileNotFoundException("Fisierul nu exista!");
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        }

        while (fRead.hasNextLine()) {
            String nume = fRead.nextLine();
            String email = fRead.nextLine();
            String username = fRead.nextLine();
            String pass = fRead.nextLine();
            clienti.add(new Clienti(nume, email, username, pass));
        }

        fRead.close();

        return clienti;
    }

    @Override
    public String toString() {
        return
                nume +
                        "\n" + email +
                        "\n" + username +
                        "\n" + password;
    }
}
