package excepciones;

public class NullListInvoicesException extends Exception {
    public NullListInvoicesException() {
        super("No hay facturas para este cliente");
    }
}
