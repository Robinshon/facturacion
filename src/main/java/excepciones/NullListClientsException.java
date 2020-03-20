package excepciones;

public class NullListClientsException extends Exception {
    public NullListClientsException(){
        super("No hay clientes en la base de datos");
    }
}
