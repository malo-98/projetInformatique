package hei.projets7.mobiliti.exception;

public class PasswordIllegalFormatException  extends Exception  {

    public PasswordIllegalFormatException(){
        super("mot de passe pas dans les bons formats ");
    }
}
