package gestor;

import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import cliente.*;


public class Gestor {
    private HashMap<String,Cliente> clientes;
    public Gestor(){
        clientes = new HashMap<String,Cliente>();
    }
    public boolean addCliente(Cliente cliente){
        if(clientes.containsKey(cliente.getNIF())){
            return false;
        }
        clientes.put(cliente.getNIF(),cliente);
        return true;
    }
    public boolean removeCliente(String nif){
        clientes.remove(nif);
        return true;
    }

    public boolean setTarifa(String nif, Tarifa tarifa){
        Cliente cliente = clientes.get(nif);
        return cliente.setTarifa(tarifa);
    }
    public String listarDatos(String nif){
        Cliente cliente = clientes.get(nif);
        return cliente.toString();

    }
    public HashMap<String,Cliente> listaClientes(){
        return clientes;
    }

    public boolean addLlamada(Llamada llamada, String nif){
        Cliente cliente = clientes.get(nif);
        return cliente.addLlamada(llamada);

    }
    public Set<Llamada> listaLlamadas(String nif){
        Cliente cliente = clientes.get(nif);
        return cliente.listaLlamadas();
    }
    public boolean emitirFactura(String codigo, String nif, Calendar fechaInicio, Calendar fechaFin){
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
    public String facturaDatos(String nif, String codigo){
        Cliente cliente = clientes.get(nif);
        return cliente.buscaFactura(codigo).toString();
    }

    public HashMap<String,Factura> listaFacturas(String nif){
        Cliente cliente = clientes.get(nif);
        return cliente.listaFacturas();

    }
}
