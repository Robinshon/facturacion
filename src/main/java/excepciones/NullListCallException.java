package excepciones;

public class NullListCallException extends Exception {
    public NullListCallException(){
        super("No hay llamadas de este cliente");
    }
}
