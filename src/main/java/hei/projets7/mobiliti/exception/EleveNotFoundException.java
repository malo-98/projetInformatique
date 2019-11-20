package hei.projets7.mobiliti.exception;

import hei.projets7.mobiliti.pojos.Eleve;

public class EleveNotFoundException extends Exception {
    private static final long serialVersionUID = 1L;

    public EleveNotFoundException(String email) {

        super("eleve with email :  "+email+" does not exist !");
    }
}

