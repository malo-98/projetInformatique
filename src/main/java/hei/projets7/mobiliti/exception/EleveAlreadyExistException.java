package hei.projets7.mobiliti.exception;

import hei.projets7.mobiliti.pojos.Eleve;

public class EleveAlreadyExistException extends Exception {


        private static final long serialVersionUID = 1L;

        public EleveAlreadyExistException(Eleve eleve) {
            super("eleve with email :  "+eleve.getEmail()+" already exists");
        }
}
