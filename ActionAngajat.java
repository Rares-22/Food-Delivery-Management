package presentationLayer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
/**
 * Pentru interfata grafica
 */
public class ActionAngajat implements ActionListener {
    Controller c;
    PaginaPrincipala p;

    public ActionAngajat(Controller c, PaginaPrincipala p) {
        this.c = c;
        this.p = p;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Employee panelAngajat = null;
        try {
            panelAngajat = new Employee();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        c.backListenerEmployee(panelAngajat, new AngajatListener(c, p));
        p.adaugaPanel(panelAngajat);
        p.revalidate();
        p.repaint();
    }
}
