package interfaz.controlador;

import clientes.Cliente;
import clientes.Direccion;
import excepciones.*;
import facturas.Factura;
import llamadas.Llamada;
import tarifas.Tarifa;

import java.util.*;

public interface Controlador {
    boolean creaCliente(int tipo, String nombre, String apellidos, String nif, Direccion dir, String correo, Calendar fecha, Tarifa tarifa) throws ExistingClientException;
    boolean borrarCliente(String nif) throws NotExistingClientException;
    boolean cambiarTarifa(String nif, Tarifa tarifa) throws NotExistingClientException;
    String recuperarDatosCliente(String nif) throws NotExistingClientException;
    HashMap<String, Cliente> recuperaListadoClientes() throws NullListClientsException;
    Collection<Cliente> recuperaListadoClientesEntreFechas(Calendar fechaInicio, Calendar fechaFin) throws NullListClientsException, IllegalPeriodException;
    boolean darDeAltaLlamada(Llamada llamada, String nif) throws NotExistingClientException;
    Set<Llamada> listarLlamadasCliente(String nif) throws NotExistingClientException, NullListCallException;
    Collection<Llamada> mostrarListadoLlamadasFechas(String nif, Calendar fechaInicio, Calendar fechaFin) throws NullListCallException, IllegalPeriodException, NotExistingClientException;
    boolean emitirFactura(String codigo, String nif, Calendar fechaInicio, Calendar fechaFin) throws NotExistingClientException,ExistingInvoiceException, IllegalPeriodException;
    String recuperarDatosFacturaCodigo(String nif,String codigo) throws NotExistingInvoceException,NotExistingClientException ;
    HashMap<String,Factura> recuperarFacturas(String nif) throws NotExistingClientException, NullListCallException, NullListInvoicesException;
    Collection<Factura> mostrarListadoFacturasFechas(String nif, Calendar fechaInicio, Calendar fechaFin) throws NotExistingClientException, NullListCallException, IllegalPeriodException, NullListInvoicesException;
}
