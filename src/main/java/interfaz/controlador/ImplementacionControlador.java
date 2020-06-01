package interfaz.controlador;

import java.util.*;

import clientes.Cliente;
import clientes.ClienteFactory;
import clientes.Direccion;
import clientes.TipoCliente;
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

    public boolean addCliente(TipoCliente tipo, String nombre, String apellidos, String nif, Direccion dir, String correo, Calendar fecha, Tarifa tarifa) throws ExistingClientException {
        Cliente cliente = ClienteFactory.crearCliente(tipo, nombre, apellidos, nif, dir, correo, tarifa, fecha);
        return modelo.addCliente(cliente);

    }

    public boolean removeCliente(String nif) throws NotExistingClientException {
        return modelo.removeCliente(nif);
    }

    public boolean setTarifa(String nif, Tarifa tarifa) throws NotExistingClientException {
        return modelo.setTarifa(nif, tarifa);
    }

    public Cliente listarDatos(String nif) throws NotExistingClientException {
        return modelo.listarDatos(nif);

    }

    public HashMap<String, Cliente> listaClientes() throws NullListClientsException {
        return modelo.listaClientes();

    }

    public Collection<Cliente> mostrarListaClientesEntreFechas(Calendar fechaInicio, Calendar fechaFin) throws NullListClientsException, IllegalPeriodException{
        return modelo.mostrarListaClientesEntreFechas(fechaInicio, fechaFin);
    }

    public boolean addLlamada(Llamada llamada, String nif) throws NotExistingClientException{
        return modelo.addLlamada(llamada, nif);
    }

    public Set<Llamada> listaLlamadas(String nif) throws NotExistingClientException, NullListCallException {
        return modelo.listaLlamadas(nif);
    }

    public Collection<Llamada> mostrarListaLlamadasEntreFechas(String nif, Calendar fechaInicio, Calendar fechaFin) throws NullListCallException, IllegalPeriodException, NotExistingClientException{
        return modelo.mostrarListaLlamadasEntreFechas(nif, fechaInicio, fechaFin);
    }

    public boolean emitirFactura(String codigo, String nif, Calendar fechaInicio, Calendar fechaFin) throws NotExistingClientException, ExistingInvoiceException, IllegalPeriodException {
        return modelo.emitirFactura(codigo,nif, fechaInicio, fechaFin);
    }

    public String facturaDatos(String nif,String codigo) throws NotExistingInvoceException, NotExistingClientException {
        return modelo.facturaDatos(nif,codigo);
    }

    public HashMap<String,Factura> listaFacturas(String nif) throws NotExistingClientException, NullListInvoicesException {
        return modelo.listaFacturas(nif);
    }

    public Collection<Factura> mostrarListaFacturasEntreFechas(String nif, Calendar fechaInicio, Calendar fechaFin) throws NotExistingClientException, NullListCallException, IllegalPeriodException, NullListInvoicesException {
        return modelo.mostrarListaFacturasEntreFechas(nif, fechaInicio, fechaFin);
    }


}