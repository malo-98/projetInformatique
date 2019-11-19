package hei.projets7.mobiliti.exception;

import hei.projets7.mobiliti.pojos.Destination;
import hei.projets7.mobiliti.pojos.Eleve;

public class DestinationAlreadyExistException extends Exception {


    private static final long serialVersionUID = 1L;

    public DestinationAlreadyExistException(Destination destination) {
        super("destination with name :  "+destination.getName()+" already exists");
    }
}
