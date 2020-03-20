package gestor;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Set;

import excepciones.*;
import facturas.Factura;
import clientes.*;
import llamadas.Llamada;
import tarifas.Tarifa;


public class Gestor {
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
    public boolean emitirFactura(String codigo, String nif, Calendar fechaInicio, Calendar fechaFin) throws NotExistingClientException{
        if(!clientes.containsKey(nif)){
            throw new NotExistingClientException();
        }
        Factura newFactura = null;
        double importe = 0.0;
        Set<Llamada> llamadasRango;
        Cliente cliente = clientes.get(nif);
        if(cliente != null){
            llamadasRango = cliente.llamadasRango(fechaInicio,fechaFin);
            for(Llamada llamada : llamadasRango){
                importe += (llamada.getDuracion() * cliente.getTarifa().getPrecioPorSegundo());
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

    public HashMap<String,Factura> listaFacturas(String nif){
        Cliente cliente = clientes.get(nif);
        return cliente.listaFacturas();

    }
}
