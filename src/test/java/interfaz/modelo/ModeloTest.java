package interfaz.modelo;

import excepciones.*;
import facturas.Factura;
import clientes.*;

import interfaz.modelo.ImplementacionModelo;
import interfaz.modelo.Modelo;
import llamadas.Llamada;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tarifas.Tarifa;
import tarifas.TarifaBasica;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
class ModeloTest {

    Modelo modelo = new ImplementacionModelo();
    Cliente c = new Particular("Pepe", "Sancho", "x212331", new Direccion(123, "Castellon", "Oropesa"), "hola@lo", new TarifaBasica(1), new GregorianCalendar(2019, 2, 20));
    Cliente c1 = new Particular("Juancho", "Pepito", "x212331", new Direccion(121, "Castellon", "Benicassim"), "ha@lo", new TarifaBasica(1), new GregorianCalendar(2020, 1, 10));
    Cliente c2 = new Particular("Pepe", "Sancho", "x212323112", new Direccion(123, "Castellon", "Oropesa"), "hola@lo", new TarifaBasica(1), new GregorianCalendar(2020, 1, 10));
    Cliente c3 = new Particular("Robert", "Pan", "x5218849B", new Direccion(123, "Castellon", "Oropesa"), "hola@lo", new TarifaBasica(1), new GregorianCalendar(2020, 1, 20));
    Cliente c4 = new Particular("Juan", "Pino", "12345678Q", new Direccion(123, "Castellon", "Oropesa"), "hola@lo", new TarifaBasica(1), new GregorianCalendar(2020, 6, 20));
    Tarifa tarifa = new TarifaBasica(1);
    Tarifa tarifa2 = new TarifaBasica(1);
    Llamada llamada = new Llamada( "642609113", new GregorianCalendar(2020, 1, 2, 12, 00, 00), 200);
    Llamada llamada2 = new Llamada("642609113", new GregorianCalendar(2020, 1, 3, 12, 00, 00), 100);
    Llamada llamada3 = new Llamada("642309113", new GregorianCalendar(2020, 1, 4, 12, 00, 00), 500);
    Llamada llamada4 = new Llamada("642309113", new GregorianCalendar(2021, 1, 4, 12, 00, 00), 600);
    Factura factura = new Factura("1",tarifa2, Calendar.getInstance(), new GregorianCalendar(2019,10,10),new GregorianCalendar(2020,2,1),10);
    Factura factura2 = new Factura("2",tarifa2, Calendar.getInstance(), new GregorianCalendar(2019,10,10),new GregorianCalendar(2020,2,1),10);
    Factura factura3 = new Factura("3",tarifa2, Calendar.getInstance(), new GregorianCalendar(2019,10,10),new GregorianCalendar(2020,2,1),10);
    Factura factura4 = new Factura("4",tarifa2, Calendar.getInstance(), new GregorianCalendar(2019,10,10),new GregorianCalendar(2021,10,1),20);
    Factura facturaEmitidaTarde = new Factura("5",tarifa2, new GregorianCalendar(2020,4,1), new GregorianCalendar(2019,10,10),new GregorianCalendar(2020,5,1),200);

    Collection<Cliente> clientes;
    Collection<Llamada> llamadas;
    Collection<Factura> facturas;
    @BeforeEach
    public void prepara() throws ExistingClientException, NotExistingClientException, IllegalPeriodException, ExistingInvoiceException {
        modelo.addCliente(c2);
        modelo.addCliente(c3);
        modelo.addCliente(c4);
        modelo.addLlamada(llamada2,"x212323112");
        modelo.addLlamada(llamada3,"x212323112");
        modelo.addLlamada(llamada4,"x212323112");
        modelo.emitirFactura("2","x212323112",new GregorianCalendar(2019,10,10),new GregorianCalendar(2020,2,1));
        modelo.emitirFactura("3","x212323112",new GregorianCalendar(2019,10,10),new GregorianCalendar(2020,2,1));
        modelo.emitirFactura("4","x212323112",new GregorianCalendar(2019,10,10),new GregorianCalendar(2021,10,1));
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
        try {
            modelo.listarDatos("x212331");
            fail("El cliente no existe se esperaba NotExistingClientException");
        } catch (NotExistingClientException e) {
        }
        try {
            assertTrue(modelo.addCliente(c));
        } catch (ExistingClientException e) {
        }
        try {
            modelo.addCliente(c2);
            fail("El cliente ya esxiste se esperaba ExistingClientException");
        } catch (ExistingClientException e) {
        }
        try {
            assertEquals(modelo.listarDatos("x212331"),c.toString());
        } catch (NotExistingClientException e) {
        }


    }

    @Test
    void removeCliente() {
        try{
            modelo.removeCliente("x212331");
            fail("El cliente no existe se esperaba NotExistingClientException");
        }catch (NotExistingClientException e){
        }
        try{
            assertEquals(modelo.listarDatos("x212323112"),c2.toString());
            assertTrue(modelo.removeCliente("x212323112"));
        }catch (NotExistingClientException e){
        }

    }

    @Test
    void setTarifa(){
        try {
            modelo.setTarifa("x212331", tarifa);
            fail("El cliente no existe se esperaba NotExistingClientException");
        } catch (NotExistingClientException e) {
        }
        try {
            assertTrue(modelo.setTarifa("x212323112", tarifa));
        } catch (NotExistingClientException e) {
        }
        try {
            assertEquals(modelo.listaClientes().get("x212323112").getTarifa().getPrecioPorMinuto(),tarifa.getPrecioPorMinuto());
        } catch (NullListClientsException e) {
        }

    }

    @Test
    void addLlamada() {

        try {
            modelo.addLlamada(llamada, "x212331");
            fail("El cliente no existe se esperaba NotExistingClientException");
        } catch (NotExistingClientException e) {
        }
        try {
            assertTrue(modelo.addLlamada(llamada, "x212323112"));
        } catch (NotExistingClientException e) {
        }
        try {
            modelo.listaLlamadas("x5218849B").toString();
            fail("La lista de llamadas del cliente esta vacia se esperaba NullListCallException");
        } catch (NotExistingClientException e) {
        } catch (NullListCallException e) {
        }
        try {
            assertTrue(modelo.listaLlamadas("x212323112").toString().contains(llamada.toString()));
        } catch (NotExistingClientException e) {
        } catch (NullListCallException e) {
        }


    }


    @Test
    void emitirFactura() {
        try {
            modelo.emitirFactura("1","x212331",new GregorianCalendar(2019,10,10),new GregorianCalendar(2020,2,1));
            fail("El cliente no existe se esperaba NotExistingClientException");
        } catch (NotExistingClientException e) {
        } catch (IllegalPeriodException e) {
        } catch (ExistingInvoiceException e) {
        }
        try {
            modelo.emitirFactura("1","x212323112",new GregorianCalendar(2022,10,10),new GregorianCalendar(2020,2,1));
            fail("La fecha inicio es mayor que la final se esperaba IllegalPeriodException");
        } catch (NotExistingClientException e) {
        } catch (IllegalPeriodException e) {
        } catch (ExistingInvoiceException e) {
        }
        try {
            modelo.emitirFactura("2","xx212323112",new GregorianCalendar(2019,10,10),new GregorianCalendar(2020,2,1));
            fail("La factura ya existe se esperaba ExistingInvoiceException");
        } catch (NotExistingClientException e) {
        } catch (IllegalPeriodException e) {
        } catch (ExistingInvoiceException e) {
        }

        try {
            assertTrue(modelo.emitirFactura("1","x212323112",new GregorianCalendar(2019,10,10),new GregorianCalendar(2020,2,1)));
        } catch (NotExistingClientException e) {
        } catch (IllegalPeriodException e) {
        } catch (ExistingInvoiceException e) {
        }
        try {
            assertEquals(modelo.facturaDatos("x212323112","1"),factura.toString());
        } catch (NotExistingClientException e) {
        } catch (NotExistingInvoceException e) {
        }


    }

    @Test
    void listarDatos() {
        //comprobamos que los datos son los mismos en la base de datos a los que tenemos en c2
        try{
            assertEquals(modelo.listarDatos("x212323112"),c2.toString());
        }catch (NotExistingClientException e){

        }

    }

    @Test
    void facturaDatos() {
        try{
            modelo.facturaDatos("x212331","1");
            fail("El cliente no existe se esperaba NotExistingClientException");
        }catch(NotExistingClientException e){
        }catch (NotExistingInvoceException e){
        }
        try{
            modelo.facturaDatos("x212323112","10");
            fail("La factura no existe se esperaba NotExistingInvoiceException");
        }catch(NotExistingClientException e){
        }catch (NotExistingInvoceException e){
        }
        try{
            //comprobamos que los datos son los mismos en la base de datos a los que tenemos en factura2
            assertEquals(modelo.facturaDatos("x212323112","2"),factura2.toString());
        }catch(NotExistingClientException e){
        }catch (NotExistingInvoceException e){
        }

    }

    @Test
    void mostrarListaClientesEntreFechas(){
        try{
            modelo.mostrarListaClientesEntreFechas(new GregorianCalendar(2022,12,1),new GregorianCalendar(2020,3,1));
            fail("La fecha inicio es mayor que la final se esperaba IllegalPeriodException");
        } catch (IllegalPeriodException e) {
        } catch (NullListClientsException e) {
        }
        try{
            modelo.mostrarListaClientesEntreFechas(new GregorianCalendar(2030,12,1),new GregorianCalendar(2040,3,1));
            fail("La lista de clientes entre esas fechas esta vacia se esperaba NullListClientException");
        } catch (IllegalPeriodException e) {
        } catch (NullListClientsException e) {
        }
        try{
            //si la lista tiene a c2 y c3
            assertTrue(modelo.mostrarListaClientesEntreFechas(new GregorianCalendar(2019,12,1),new GregorianCalendar(2020,3,1)).contains(c2));
            assertTrue(modelo.mostrarListaClientesEntreFechas(new GregorianCalendar(2019,12,1),new GregorianCalendar(2020,3,1)).contains(c3));
            //la lista no contiene a c4 porque no esta en el rango ni a c1 porque no esta preparado
            assertFalse(modelo.mostrarListaClientesEntreFechas(new GregorianCalendar(2019,12,10),new GregorianCalendar(2020,3,1)).contains(c4));
            assertFalse(modelo.mostrarListaClientesEntreFechas(new GregorianCalendar(2019,12,10),new GregorianCalendar(2020,3,1)).contains(c1));
            //comprobamos la lista preparada clientes
            assertEquals(modelo.mostrarListaClientesEntreFechas(new GregorianCalendar(2019,12,1),new GregorianCalendar(2020,3,1)),clientes);
        }catch (IllegalPeriodException e){
        }catch (NullListClientsException e){
        }

    }
    @Test
    void mostrarListaLlamadasEntreFechas() {
        try{
            modelo.mostrarListaLlamadasEntreFechas("x212323112",new GregorianCalendar(2022,12,1),new GregorianCalendar(2020,3,1));
            fail("La fecha inicio es mayor que la final se esperaba IllegalPeriodException");
        } catch (IllegalPeriodException e) {
        } catch (NotExistingClientException e) {
        } catch (NullListCallException e) {
        }
        try{
            modelo.mostrarListaLlamadasEntreFechas("x212331",new GregorianCalendar(2019,12,1),new GregorianCalendar(2020,3,1));
            fail("El cliente no existe se esperaba NotExistingClientException");
        } catch (IllegalPeriodException e) {
        } catch (NotExistingClientException e) {
        } catch (NullListCallException e) {
        }
        try{
            modelo.mostrarListaLlamadasEntreFechas("x212323112",new GregorianCalendar(2030,12,1),new GregorianCalendar(2040,3,1));
            fail("La lista de llamadas entre esas fechas esta vacia se esperaba NullListCallException");
        } catch (IllegalPeriodException e) {
        } catch (NotExistingClientException e) {
        } catch (NullListCallException e) {
        }
        try{
            assertTrue(modelo.mostrarListaLlamadasEntreFechas("x212323112",new GregorianCalendar(2019,12,1),new GregorianCalendar(2020,3,1)).contains(llamada2));
            assertTrue(modelo.mostrarListaLlamadasEntreFechas("x212323112",new GregorianCalendar(2019,12,1),new GregorianCalendar(2020,3,1)).contains(llamada3));
            assertFalse(modelo.mostrarListaLlamadasEntreFechas("x212323112",new GregorianCalendar(2019,12,10),new GregorianCalendar(2020,3,1)).contains(llamada4));
            assertTrue(modelo.mostrarListaLlamadasEntreFechas("x212323112",new GregorianCalendar(2019,10,10),new GregorianCalendar(2020,2,1)).containsAll(llamadas));
        }catch (IllegalPeriodException e){
        }catch (NotExistingClientException e) {
        }catch (NullListCallException e) {
        }
    }
    @Test
    void mostrarListaFacturasEntreFechas() {
        try{
            modelo.mostrarListaFacturasEntreFechas("x212323112",new GregorianCalendar(2022,1,1),new GregorianCalendar(2020,3,1));
            fail("La fecha inicio es mayor que la final se esperaba IllegalPeriodException");
        } catch (IllegalPeriodException e) {
        } catch (NotExistingClientException e) {
        } catch (NullListInvoicesException e) {
        }
        try{
            modelo.mostrarListaFacturasEntreFechas("x212331",new GregorianCalendar(2019,1,1),new GregorianCalendar(2020,3,1));
            fail("El cliente no existe se esperaba NotExistingClientException");
        } catch (IllegalPeriodException e) {
        } catch (NotExistingClientException e) {
        } catch (NullListInvoicesException e) {
        }
        try{
            modelo.mostrarListaLlamadasEntreFechas("x212323112",new GregorianCalendar(2030,12,1),new GregorianCalendar(2040,3,1));
            fail("La lista de facturas entre esas fechas esta vacia se esperaba NullListCallException");
        } catch (IllegalPeriodException e) {
        } catch (NotExistingClientException e) {
        } catch (NullListCallException e) {
        }
        try{
            //compruebo que los datos de las facturas concuerdan con lo esperado
            assertTrue(modelo.mostrarListaFacturasEntreFechas("x212323112",new GregorianCalendar(2019,1,1),new GregorianCalendar(2020,3,1)).toString().contains(factura2.toString()));
            assertTrue(modelo.mostrarListaFacturasEntreFechas("x212323112",new GregorianCalendar(2019,1,1),new GregorianCalendar(2020,3,1)).toString().contains(factura3.toString()));
            assertTrue(modelo.mostrarListaFacturasEntreFechas("x212323112",new GregorianCalendar(2019,1,1),new GregorianCalendar(2020,3,1)).toString().contains(factura4.toString()));
            assertFalse(modelo.mostrarListaFacturasEntreFechas("x212323112",new GregorianCalendar(2019,1,1),new GregorianCalendar(2020,3,1)).toString().contains(facturaEmitidaTarde.toString()));
            assertEquals(modelo.mostrarListaFacturasEntreFechas("x212323112",new GregorianCalendar(2019,1,1),new GregorianCalendar(2020,3,1)).toString(),facturas.toString());
        }catch (IllegalPeriodException e){
        }catch(NotExistingClientException e){
        }catch (NullListInvoicesException e){
        }



    }
}

