package presentationLayer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * Pentru interfata grafica
 */
public class AngajatListener implements ActionListener {
    Controller c;
    PaginaPrincipala p;
    public AngajatListener(Controller c,PaginaPrincipala p) {
        this.c = c;
        this.p = p;
    }
    @Override
    public void actionPerformed(ActionEvent e) {

        p.setVisible(false);
        PaginaPrincipala pg = new PaginaPrincipala();
        Controller x = new Controller(pg);
        pg.setVisible(true);

    }
}
