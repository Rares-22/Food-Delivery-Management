package presentationLayer;

import java.awt.event.ActionListener;
/**
 * Pentru interfata grafica
 */
public class Controller {
    public Controller(PaginaPrincipala p) {

        ActionAngajat aaction = new ActionAngajat(this, p);
        p.addListener(aaction);

        ActionAdmin aadmin = new ActionAdmin(this, p);
        p.addListenerAdmin(aadmin);

        ActionClient aclient = new ActionClient(this, p);
        p.addListenerClient(aclient);
    }


    public void backListenerEmployee(Employee angajat, ActionListener e) {
        angajat.addListener(e);
    }

    public void backListenerAdm(Administrator admin, ActionListener e) {
        admin.addListener(e);
    }
}
