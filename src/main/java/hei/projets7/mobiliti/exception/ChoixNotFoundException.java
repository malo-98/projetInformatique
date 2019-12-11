package hei.projets7.mobiliti.exception;

public class ChoixNotFoundException extends Exception{
    private static final long serialVersionUID = 1L;

    public ChoixNotFoundException(Integer id) {

        super("choix with id :  "+id+" does not exist !");
    }
}
