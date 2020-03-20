package excepciones;

public class ExistingInvoiceException extends Exception {
    public ExistingInvoiceException(){
        super("La factura existe en la base de datos");
    }
}
