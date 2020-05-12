package interfaz.controlador;

import java.util.*;

import clientes.Cliente;
import clientes.ClienteFactory;
import clientes.Direccion;
import excepciones.*;
import facturas.Factura;
import interfaz.modelo.Modelo;
import llamadas.Llamada;
import tarifas.Tarifa;


public class ImplementacionControlador implements Controlador{

    private Modelo modelo;


    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }

    public boolean creaCliente(int tipo, String nombre, String apellidos, String nif, Direccion dir, String correo, Calendar fecha, Tarifa tarifa) throws ExistingClientException {
        Cliente cliente = ClienteFactory.crearCliente(tipo, nombre, apellidos, nif, dir, correo, tarifa, fecha);
        return modelo.addCliente(cliente);

    }

    public boolean borrarCliente(String nif) throws NotExistingClientException {
        return modelo.removeCliente(nif);
    }

    public boolean cambiarTarifa(String nif, Tarifa tarifa) throws NotExistingClientException {
        return modelo.setTarifa(nif, tarifa);
    }

    public String recuperarDatosCliente(String nif) throws NotExistingClientException {
        return modelo.listarDatos(nif);

    }

    public HashMap<String, Cliente> recuperaListadoClientes() throws NullListClientsException {
        return modelo.listaClientes();

    }

    public Collection<Cliente> recuperaListadoClientesEntreFechas(Calendar fechaInicio, Calendar fechaFin) throws NullListClientsException, IllegalPeriodException{
        return modelo.mostrarListaClientesEntreFechas(fechaInicio, fechaFin);
    }

    public boolean darDeAltaLlamada(Llamada llamada, String nif) throws NotExistingClientException{
        return modelo.addLlamada(llamada, nif);
    }

    public Set<Llamada> listarLlamadasCliente(String nif) throws NotExistingClientException, NullListCallException {
        return modelo.listaLlamadas(nif);
    }

    public Collection<Llamada> mostrarListadoLlamadasFechas(String nif, Calendar fechaInicio, Calendar fechaFin) throws NullListCallException, IllegalPeriodException, NotExistingClientException{
        return modelo.mostrarListaLlamadasEntreFechas(nif, fechaInicio, fechaFin);
    }

    public boolean emitirFactura(String codigo, String nif, Calendar fechaInicio, Calendar fechaFin) throws NotExistingClientException, ExistingInvoiceException, IllegalPeriodException {
        return modelo.emitirFactura(codigo,nif, fechaInicio, fechaFin);
    }

    public String recuperarDatosFacturaCodigo(String nif,String codigo) throws NotExistingInvoceException, NotExistingClientException {
        return modelo.facturaDatos(nif,codigo);
    }

    public HashMap<String,Factura> recuperarFacturas(String nif) throws NotExistingClientException, NullListInvoicesException {
        return modelo.listaFacturas(nif);
    }

    public Collection<Factura> mostrarListadoFacturasFechas(String nif, Calendar fechaInicio, Calendar fechaFin) throws NotExistingClientException, NullListCallException, IllegalPeriodException, NullListInvoicesException {
        return modelo.mostrarListaFacturasEntreFechas(nif, fechaInicio, fechaFin);
    }


}