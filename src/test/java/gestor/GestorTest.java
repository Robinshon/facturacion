package gestor;

import excepciones.*;
import facturas.Factura;
import clientes.*;

import gestor.Gestor;
import llamadas.Llamada;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tarifas.Tarifa;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class GestorTest {

    Gestor gestor = new Gestor();
    Cliente c = new Particular("Pepe", "Sancho", "x212331", new Direccion(123, "Castellon", "Oropesa"), "hola@lo", new Tarifa(1), new GregorianCalendar(2019, 2, 20));
    Cliente c1 = new Particular("Juancho", "Pepito", "x212331", new Direccion(121, "Castellon", "Benicassim"), "ha@lo", new Tarifa(1), new GregorianCalendar(2020, 1, 10));
    Cliente c2 = new Particular("Pepe", "Sancho", "x212323112", new Direccion(123, "Castellon", "Oropesa"), "hola@lo", new Tarifa(1), new GregorianCalendar(2020, 1, 10));
    Cliente c3 = new Particular("Robert", "Pan", "x5218849B", new Direccion(123, "Castellon", "Oropesa"), "hola@lo", new Tarifa(1), new GregorianCalendar(2020, 1, 20));
    Cliente c4 = new Particular("Juan", "Pino", "12345678Q", new Direccion(123, "Castellon", "Oropesa"), "hola@lo", new Tarifa(1), new GregorianCalendar(2020, 6, 20));
    Tarifa tarifa = new Tarifa(1);
    Tarifa tarifa2 = new Tarifa(1);
    Llamada llamada = new Llamada("642609113", new GregorianCalendar(2020, 1, 2, 12, 00, 00), 200);
    Llamada llamada2 = new Llamada("642609113", new GregorianCalendar(2020, 1, 3, 12, 00, 00), 100);
    Llamada llamada3 = new Llamada("642309113", new GregorianCalendar(2020, 1, 4, 12, 00, 00), 500);
    Llamada llamada4 = new Llamada("642309113", new GregorianCalendar(2021, 1, 4, 12, 00, 00), 500);
    Factura factura = new Factura("1",tarifa2, Calendar.getInstance(), new GregorianCalendar(2019,10,10),new GregorianCalendar(2020,2,1),100);
    Factura factura2 = new Factura("2",tarifa2, Calendar.getInstance(), new GregorianCalendar(2019,10,10),new GregorianCalendar(2020,2,1),600);
    Factura factura3 = new Factura("3",tarifa2, Calendar.getInstance(), new GregorianCalendar(2019,10,10),new GregorianCalendar(2020,2,1),600);
    Factura factura4 = new Factura("4",tarifa2, Calendar.getInstance(), new GregorianCalendar(2019,10,10),new GregorianCalendar(2021,10,1),1100);
    Factura facturaEmitidaTarde = new Factura("5",tarifa2, new GregorianCalendar(2020,4,1), new GregorianCalendar(2019,10,10),new GregorianCalendar(2020,5,1),200);
    Collection<Cliente> clientes;
    Collection<Llamada> llamadas;
    Collection<Factura> facturas;
    @BeforeEach
    public void prepara() throws ExistingClientException, NotExistingClientException, IllegalPeriodException, ExistingInvoiceException {
        gestor.addCliente(c2);
        gestor.addCliente(c3);
        gestor.addCliente(c4);
        gestor.addLlamada(llamada2,"x212323112");
        gestor.addLlamada(llamada3,"x212323112");
        gestor.addLlamada(llamada4,"x212323112");
        gestor.emitirFactura("2","x212323112",new GregorianCalendar(2019,10,10),new GregorianCalendar(2020,2,1));
        gestor.emitirFactura("3","x212323112",new GregorianCalendar(2019,10,10),new GregorianCalendar(2020,2,1));
        gestor.emitirFactura("4","x212323112",new GregorianCalendar(2019,10,10),new GregorianCalendar(2021,10,1));
        //se usara la lista para comprobar que lo esperado es igual que lo obtenido
        clientes = new LinkedList<Cliente>();
        clientes.add(c2);
        clientes.add(c3);
        llamadas = new LinkedList<Llamada>();
        llamadas.add(llamada2);
        llamadas.add(llamada3);
        facturas = new LinkedList<Factura>();
        facturas.add(factura2);
        facturas.add(factura3);
        facturas.add(factura4);


    }


    @Test
    void addCliente() {
        try{
            assertTrue(gestor.addCliente(c));
            assertFalse(gestor.addCliente(c1));
            assertEquals(gestor.listarDatos("x212331"),c);
        }catch (ExistingClientException e){

        }catch (NotExistingClientException e){

        }


    }

    @Test
    void removeCliente() {
        try{
            assertEquals(gestor.listarDatos("x212323112"),c2.toString());
            assertTrue(gestor.removeCliente("x212323112"));
            assertNotEquals(gestor.listarDatos("x212323112"),c2.toString());
            //borrar cliente que no esta
            assertFalse(gestor.removeCliente("x212331"));
        }catch (NotExistingClientException e){

        }

    }

    @Test
    void setTarifa(){
        try{
            assertTrue(gestor.setTarifa("x212323112", tarifa));
            //el cliente no esta
            assertFalse(gestor.setTarifa("x212331", tarifa));
        }catch (NotExistingClientException e){

        }

    }

    @Test
    void addLlamada() {
        try{
            assertTrue(gestor.addLlamada(llamada, "x212323112"));
            //el cliente no existe
            assertFalse(gestor.addLlamada(llamada, "x212331"));
        }catch (NotExistingClientException e){

        }

    }


    @Test
    void emitirFactura() {
        try{
            assertTrue(gestor.emitirFactura("1","x212323112",new GregorianCalendar(2019,10,10),new GregorianCalendar(2020,2,1)));
            //si intentamos usar el mismo codigo para otra factura error
            assertFalse(gestor.emitirFactura("1","x212323112",new GregorianCalendar(2019,10,10),new GregorianCalendar(2020,2,1)));
            //si intentamos emitir una factura a un cliente que no esta error
            assertFalse(gestor.emitirFactura("1","x212331",new GregorianCalendar(2020,10,10),new GregorianCalendar(2020,2,1)));

        }catch(NotExistingClientException e){

        }catch (IllegalPeriodException e){

        }catch (ExistingInvoiceException e){
        }


    }

    @Test
    void listarDatos() {
        //comprobamos que los datos son los mismos en la base de datos a los que tenemos en c2
        try{
            assertEquals(gestor.listarDatos("x212323112"),c2.toString());
        }catch (NotExistingClientException e){

        }

    }

    @Test
    void facturaDatos() {
        try{
            //comprobamos que los datos son los mismos en la base de datos a los que tenemos en factura2
            assertEquals(gestor.facturaDatos("x212323112","2"),factura2.toString());
        }catch(NotExistingClientException e){

        }catch (NotExistingInvoceException e){

        }

    }

    @Test
    void mostrarListaClientesEntreFechas(){
        try{
            //si la lista tiene a c2 y c3
            assertTrue(gestor.mostrarListaClientesEntreFechas(new GregorianCalendar(2019,12,1),new GregorianCalendar(2020,3,1)).contains(c2));
            assertTrue(gestor.mostrarListaClientesEntreFechas(new GregorianCalendar(2019,12,1),new GregorianCalendar(2020,3,1)).contains(c3));
            //la lista no contiene a c4 porque no esta en el rango ni a c1 porque no esta preparado
            assertFalse(gestor.mostrarListaClientesEntreFechas(new GregorianCalendar(2019,12,10),new GregorianCalendar(2020,3,1)).contains(c4));
            assertFalse(gestor.mostrarListaClientesEntreFechas(new GregorianCalendar(2019,12,10),new GregorianCalendar(2020,3,1)).contains(c1));
            //comprobamos la lista preparada clientes
            assertEquals(gestor.mostrarListaClientesEntreFechas(new GregorianCalendar(2019,12,1),new GregorianCalendar(2020,3,1)),clientes);
        }catch (IllegalPeriodException e){

        }catch (NullListClientsException e){

        }

    }
    @Test
    void mostrarListaLlamadasEntreFechas() {
        try{
            assertTrue(gestor.mostrarListaLlamadasEntreFechas("x212323112",new GregorianCalendar(2019,12,1),new GregorianCalendar(2020,3,1)).contains(llamada2));
            assertTrue(gestor.mostrarListaLlamadasEntreFechas("x212323112",new GregorianCalendar(2019,12,1),new GregorianCalendar(2020,3,1)).contains(llamada3));
            assertFalse(gestor.mostrarListaLlamadasEntreFechas("x212323112",new GregorianCalendar(2019,12,10),new GregorianCalendar(2020,3,1)).contains(llamada4));
            assertEquals(gestor.mostrarListaLlamadasEntreFechas("x212323112",new GregorianCalendar(2019,10,10),new GregorianCalendar(2020,2,1)),llamadas);
        }catch (IllegalPeriodException e){
        }catch (NotExistingClientException e) {
        }catch (NullListCallException e) {
        }
    }
    @Test
    void mostrarListaFacturasEntreFechas() {
        try{
            //compruebo que los datos de las facturas concuerdan con lo esperado
            assertTrue(gestor.mostrarListaFacturasEntreFechas("x212323112",new GregorianCalendar(2019,1,1),new GregorianCalendar(2020,3,1)).toString().contains(factura2.toString()));
            assertTrue(gestor.mostrarListaFacturasEntreFechas("x212323112",new GregorianCalendar(2019,1,1),new GregorianCalendar(2020,3,1)).toString().contains(factura3.toString()));
            assertTrue(gestor.mostrarListaFacturasEntreFechas("x212323112",new GregorianCalendar(2019,1,1),new GregorianCalendar(2020,3,1)).toString().contains(factura4.toString()));
            assertFalse(gestor.mostrarListaFacturasEntreFechas("x212323112",new GregorianCalendar(2019,1,1),new GregorianCalendar(2020,3,1)).toString().contains(facturaEmitidaTarde.toString()));
            assertEquals(gestor.mostrarListaFacturasEntreFechas("x212323112",new GregorianCalendar(2019,1,1),new GregorianCalendar(2020,3,1)).toString(),facturas.toString());
        }catch (IllegalPeriodException e){

        }catch(NotExistingClientException e){

        }catch (NullListInvoicesException e){

        }



    }
}

