package excepciones;

public class ExistingClientException extends Exception {
    public ExistingClientException(){
        super("El cliente ya esta en la base de datos");
    }
}
