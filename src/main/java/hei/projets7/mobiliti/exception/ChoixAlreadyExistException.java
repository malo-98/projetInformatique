package hei.projets7.mobiliti.exception;

import hei.projets7.mobiliti.pojos.Choix;

public class ChoixAlreadyExistException extends Exception {

    private static final long serialVersionUID = 1L;

    public ChoixAlreadyExistException(Choix choix) {
        super("choix with id :  "+choix.getId_choix()+" already exists");
    }
}
