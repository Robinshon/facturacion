package excepciones;

public class InvalidArgumentException extends Exception {
    public InvalidArgumentException() {
        super("Respuesta no valida");
    }
}
