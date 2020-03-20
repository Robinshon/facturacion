package excepciones;

public class NotExistingInvoceException extends Exception {
    public  NotExistingInvoceException(){
        super("La factura no existe");
    }
}
