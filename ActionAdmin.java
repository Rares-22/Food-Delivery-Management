package presentationLayer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Pentru interfata grafica
 */
public class ActionAdmin implements ActionListener {
    Controller c;
    PaginaPrincipala p;
    public ActionAdmin(Controller c,PaginaPrincipala pg) {
        this.c = c;
        p = pg;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Administrator panelAdmin = new Administrator();
        c.backListenerAdm(panelAdmin, new AngajatListener(c, p));
        p.adaugaPanel(panelAdmin);
        p.revalidate();
        p.repaint();
    }
}
