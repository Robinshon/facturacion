import excepciones.*;
import facturas.Factura;
import clientes.*;

import gestor.Gestor;
import llamadas.Llamada;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tarifas.Tarifa;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

class GestorTest {

    Gestor gestor = new Gestor();
    Cliente c = new Particular("Pepe", "Sancho", "x212331", new Direccion(123, "Castellon", "Oropesa"), "hola@lo", new Tarifa(1), new GregorianCalendar(2019, 2, 20));
    Cliente c1 = new Particular("Juancho", "Pepito", "x212331", new Direccion(121, "Castellon", "Benicassim"), "ha@lo", new Tarifa(1), new GregorianCalendar(2020, 1, 10));
    Cliente c2 = new Particular("Pepe", "Sancho", "x212323112", new Direccion(123, "Castellon", "Oropesa"), "hola@lo", new Tarifa(1), new GregorianCalendar(2019, 2, 20));
    Tarifa tarifa = new Tarifa(1);
    Tarifa tarifa2 = new Tarifa(1);
    Llamada llamada = new Llamada("642609113", new GregorianCalendar(2020, 1, 1, 12, 00, 00), 200);
    Llamada llamada2 = new Llamada("642609113", new GregorianCalendar(2020, 1, 1, 12, 00, 00), 100);;
    Factura factura = new Factura("1",tarifa2, Calendar.getInstance(), new GregorianCalendar(2019,10,10),new GregorianCalendar(2020,2,1),100);
    Factura factura2 = new Factura("2",tarifa2, Calendar.getInstance(), new GregorianCalendar(2019,10,10),new GregorianCalendar(2020,2,1),100);

    @BeforeEach
    public void prepara() throws ExistingClientException, NotExistingClientException, IllegalPeriodException {
        gestor.addCliente(c2);
        gestor.addLlamada(llamada2,"x212323112");
        gestor.emitirFactura("2","x212323112",new GregorianCalendar(2019,10,10),new GregorianCalendar(2020,2,1));

    }


    @Test
    void addCliente() throws ExistingClientException, NotExistingClientException {
        assertTrue(gestor.addCliente(c));
        assertFalse(gestor.addCliente(c1));
        assertEquals(gestor.listarDatos("x212331"),c);
    }

    @Test
    void removeCliente() throws NotExistingClientException {
        assertEquals(gestor.listarDatos("x212323112"),c2);
        assertTrue(gestor.removeCliente("x212323112"));
        assertNotEquals(gestor.listarDatos("x212323112"),c2);
        //borrar cliente que no esta
        assertFalse(gestor.removeCliente("x212331"));
    }

    @Test
    void setTarifa() throws NotExistingClientException {
        assertTrue(gestor.setTarifa("x212323112", tarifa));
        //el cliente no esta
        assertFalse(gestor.setTarifa("x212331", tarifa));
    }

    @Test
    void addLlamada() throws NotExistingClientException {
        assertTrue(gestor.addLlamada(llamada, "x212323112"));
        //el cliente no existe
        assertFalse(gestor.addLlamada(llamada, "x212331"));
    }


    @Test
    void emitirFactura() throws NotExistingClientException, IllegalPeriodException {
        assertTrue(gestor.emitirFactura("1","x212323112",new GregorianCalendar(2019,10,10),new GregorianCalendar(2020,2,1)));
        //si intentamos usar el mismo codigo para otra factura error
        assertFalse(gestor.emitirFactura("1","x212323112",new GregorianCalendar(2019,10,10),new GregorianCalendar(2020,2,1)));
        //si intentamos emitir una factura a un cliente que no esta error
        assertFalse(gestor.emitirFactura("1","x212331",new GregorianCalendar(2019,10,10),new GregorianCalendar(2020,2,1)));


    }

    @Test
    void listarDatos() throws NotExistingClientException {
        //comprobamos que los datos son los mismos en la base de datos a los que tenemos en c2
        assertEquals(gestor.listarDatos("x212323112"),c2.toString());
    }

    @Test
    void facturaDatos() throws NotExistingClientException, NotExistingInvoceException {
        //comprobamos que los datos son los mismos en la base de datos a los que tenemos en factura2
        assertEquals(gestor.facturaDatos("x212323112","2"),factura2.toString());
    }

    @Test
    void mostrarListaClientesEntreFechas() throws IllegalPeriodException, NullListClientsException {

    }
}

