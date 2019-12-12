package hei.projets7.mobiliti.exception;

public class DestinationNotFoundException extends Exception {
    private static final long serialVersionUID = 1L;

    public DestinationNotFoundException(Integer id) {

        super("eleve with email :  "+id+" does not exist !");
    }
}
