package interfaz.modelo;

import java.util.*;

import clientes.Cliente;
import excepciones.*;
import facturas.Factura;
import llamadas.Llamada;
import tarifas.Tarifa;

public interface Modelo {

    boolean addCliente(Cliente cliente) throws ExistingClientException;
    boolean removeCliente(String nif) throws NotExistingClientException;
    boolean setTarifa(String nif, Tarifa tarifa) throws NotExistingClientException ;
    Cliente listarDatos(String nif) throws NotExistingClientException;
    HashMap<String, Cliente> listaClientes() throws NullListClientsException;
    Collection<Cliente> mostrarListaClientesEntreFechas(Calendar fechaInicio, Calendar fechaFin) throws IllegalPeriodException, NullListClientsException;
    boolean addLlamada(Llamada llamada, String nif) throws NotExistingClientException ;
    Set<Llamada> listaLlamadas(String nif) throws NotExistingClientException,NullListCallException ;
    Collection<Llamada> mostrarListaLlamadasEntreFechas(String nif, Calendar fechaInicio, Calendar fechaFin) throws NullListCallException, IllegalPeriodException, NotExistingClientException;
    boolean emitirFactura(String codigo, String nif, Calendar fechaInicio, Calendar fechaFin) throws NotExistingClientException, IllegalPeriodException, ExistingInvoiceException;
    String facturaDatos(String nif, String codigo) throws NotExistingClientException, NotExistingInvoceException;
    HashMap<String,Factura> listaFacturas(String nif) throws NotExistingClientException, NullListInvoicesException;
    Collection<Factura> mostrarListaFacturasEntreFechas(String nif, Calendar fechaInicio, Calendar fechaFin) throws NotExistingClientException, IllegalPeriodException, NullListInvoicesException ;

    // Datos
    void guardarDatos() ;
    void cargarDatos() ;
}