package excepciones;

public class NotExistingClientException extends Exception{
    public NotExistingClientException(){
        super("El cliente no existe");
    }
}
