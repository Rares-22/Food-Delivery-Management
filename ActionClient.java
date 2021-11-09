package presentationLayer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
/**
 * Pentru interfata grafica
 */
public class ActionClient implements ActionListener {
    Controller c;
    PaginaPrincipala p;
    public ActionClient(Controller c,PaginaPrincipala p){
        this.c = c;
        this.p = p;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Client panelClient = null;
        try {
            panelClient = new Client(p);
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }
        p.adaugaPanel(panelClient);
        p.revalidate();
        p.repaint();
    }
}
