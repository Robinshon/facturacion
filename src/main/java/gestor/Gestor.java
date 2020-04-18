package gestor;

import java.io.*;
import java.util.*;

import excepciones.*;
import facturas.Factura;
import clientes.*;
import llamadas.Llamada;
import tarifas.Tarifa;

import static fechas.EntreFechas.*;


public class Gestor implements Serializable{
    private static final long serialVersionUID = 42L;
    private HashMap<String,Cliente> clientes;
    public Gestor(){

        clientes = new HashMap<String,Cliente>();
    }


    public boolean addCliente(Cliente cliente) throws ExistingClientException {
        if(clientes.containsKey(cliente.getNIF())){
            throw new ExistingClientException();
        }
        clientes.put(cliente.getNIF(),cliente);
        return true;
    }

    public boolean removeCliente(String nif) throws NotExistingClientException {
        if(clientes.containsKey(nif)){
            clientes.remove(nif);
            return true;
        }
        throw new NotExistingClientException();
    }

    public boolean setTarifa(String nif, Tarifa tarifa) throws NotExistingClientException{
        if(clientes.containsKey(nif)){
            Cliente cliente = clientes.get(nif);
            return cliente.setTarifa(tarifa);
        }
        throw new NotExistingClientException();
    }

    public String listarDatos(String nif) throws NotExistingClientException {
        if(clientes.containsKey(nif)){
            Cliente cliente = clientes.get(nif);
            return cliente.toString();
        }
        throw new NotExistingClientException();

    }

    public HashMap<String,Cliente> listaClientes() throws NullListClientsException {
        if(clientes.isEmpty()){
            throw new NullListClientsException();
        }
        return clientes;
    }


    public boolean addLlamada(Llamada llamada, String nif) throws NotExistingClientException{
        if(clientes.containsKey(nif)){
            Cliente cliente = clientes.get(nif);
            return cliente.addLlamada(llamada);
        }
        throw new NotExistingClientException();


    }

    public Set<Llamada> listaLlamadas(String nif) throws NotExistingClientException, NullListCallException {
        if(clientes.containsKey(nif)){
            Cliente cliente = clientes.get(nif);
            if(cliente.listaLlamadas().isEmpty()){
                throw new NullListCallException();
            }
            return cliente.listaLlamadas();
        }
        throw new NotExistingClientException();
    }


    public boolean emitirFactura(String codigo, String nif, Calendar fechaInicio, Calendar fechaFin) throws NotExistingClientException, IllegalPeriodException, ExistingInvoiceException {
        if(fechaInicio.after(fechaFin)){
            throw new IllegalPeriodException();
        }
        if(!clientes.containsKey(nif)){
            throw new NotExistingClientException();
        }

        if(clientes.get(nif).estaFactura(codigo)){
            throw new ExistingInvoiceException();
        }

        Factura newFactura;
        double importe = 0.0;
        Set<Llamada> llamadasRango;
        Cliente cliente = clientes.get(nif);
        if(cliente != null){
            llamadasRango = cliente.llamadasRango(fechaInicio,fechaFin);
            for(Llamada llamada : llamadasRango){
                importe += (llamada.getDuracion() * cliente.getTarifa().getPrecioPorMinuto());
            }
            newFactura = new Factura(codigo,cliente.getTarifa(),Calendar.getInstance(),fechaInicio,fechaFin,importe);
            return cliente.addFactura(newFactura);
        }
        return false;
    }

    public String facturaDatos(String nif, String codigo) throws NotExistingClientException, NotExistingInvoceException {
        if(clientes.containsKey(nif)){
            Cliente cliente = clientes.get(nif);
            return cliente.buscaFactura(codigo).toString();
        }
       throw new NotExistingClientException();
    }

    public HashMap<String,Factura> listaFacturas(String nif) throws NotExistingClientException, NullListInvoicesException{
        if(clientes.containsKey(nif)){
            Cliente cliente = clientes.get(nif);
            return cliente.listaFacturas();
        }
        throw new NotExistingClientException();

    }


    public Collection<Cliente> mostrarListaClientesEntreFechas(Calendar fechaInicio, Calendar fechaFin) throws IllegalPeriodException, NullListClientsException{
        if(fechaInicio.after(fechaFin)) {
            throw new IllegalPeriodException();
        }
        Collection<Cliente> clientes = this.clientes.values();
        clientes = listaEntreFechas(clientes, fechaInicio, fechaFin);

        if(clientes.isEmpty()){
            throw new NullListClientsException();
        }
        return clientes;
    }

    public Collection<Llamada> mostrarListaLlamadasEntreFechas(String nif, Calendar fechaInicio, Calendar fechaFin) throws NullListCallException, IllegalPeriodException, NotExistingClientException{
        if(!clientes.containsKey(nif)) {
            throw new NotExistingClientException();
        }
        if(fechaInicio.after(fechaFin)) {
            throw new IllegalPeriodException();
        }
        Collection<Llamada> llamadas = clientes.get(nif).listaLlamadas();
        llamadas = listaEntreFechas(llamadas, fechaInicio, fechaFin);
        if(llamadas.isEmpty()) {
            throw new NullListCallException();
        }
        return llamadas;
    }

    public Collection<Factura> mostrarListaFacturasEntreFechas(String nif, Calendar fechaInicio, Calendar fechaFin) throws IllegalPeriodException, NotExistingClientException, NullListInvoicesException {
        if(!clientes.containsKey(nif)) {
            throw new NotExistingClientException();
        }
        if(fechaInicio.after(fechaFin)) {
            throw new IllegalPeriodException();
        }
        Collection<Factura> facturas = clientes.get(nif).listaFacturas().values();
        facturas = listaEntreFechas(facturas, fechaInicio, fechaFin);

        if(facturas.isEmpty()) {
            throw new NullListInvoicesException();
        }
        return facturas;
    }



    public void guardarDatos(){
        FileOutputStream archive;
        ObjectOutputStream object;
        try {
            archive = new FileOutputStream("data/clientes.bin");
            object = new ObjectOutputStream(archive);
            object.writeObject(clientes);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void cargarDatos(){
        FileInputStream archive;
        ObjectInputStream object;
        try {
            archive = new FileInputStream("data/clientes.bin");
            object = new ObjectInputStream(archive);
            clientes = (HashMap<String,Cliente>) object.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
