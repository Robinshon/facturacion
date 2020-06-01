package interfaz.controlador;

import clientes.Cliente;
import clientes.Direccion;
import clientes.TipoCliente;
import excepciones.*;
import facturas.Factura;
import llamadas.Llamada;
import tarifas.Tarifa;

import java.util.*;

public interface Controlador {
    boolean addCliente(TipoCliente tipo, String nombre, String apellidos, String nif, Direccion dir, String correo, Calendar fecha, Tarifa tarifa) throws ExistingClientException;
    boolean removeCliente(String nif) throws NotExistingClientException;
    boolean setTarifa(String nif, Tarifa tarifa) throws NotExistingClientException;
    Cliente listarDatos(String nif) throws NotExistingClientException;
    HashMap<String, Cliente> listaClientes() throws NullListClientsException;
    Collection<Cliente> mostrarListaClientesEntreFechas(Calendar fechaInicio, Calendar fechaFin) throws NullListClientsException, IllegalPeriodException;
    boolean addLlamada(Llamada llamada, String nif) throws NotExistingClientException;
    Set<Llamada> listaLlamadas(String nif) throws NotExistingClientException, NullListCallException;
    Collection<Llamada> mostrarListaLlamadasEntreFechas(String nif, Calendar fechaInicio, Calendar fechaFin) throws NullListCallException, IllegalPeriodException, NotExistingClientException;
    boolean emitirFactura(String codigo, String nif, Calendar fechaInicio, Calendar fechaFin) throws NotExistingClientException,ExistingInvoiceException, IllegalPeriodException;
    String facturaDatos(String nif,String codigo) throws NotExistingInvoceException,NotExistingClientException ;
    HashMap<String,Factura> listaFacturas(String nif) throws NotExistingClientException, NullListCallException, NullListInvoicesException;
    Collection<Factura> mostrarListaFacturasEntreFechas(String nif, Calendar fechaInicio, Calendar fechaFin) throws NotExistingClientException, NullListCallException, IllegalPeriodException, NullListInvoicesException;
}
