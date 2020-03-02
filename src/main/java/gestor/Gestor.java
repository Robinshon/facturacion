package gestor;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;
import cliente.*;


public class Gestor {
    private Set<Cliente> clientes;
    public Gestor(){
        clientes = new HashSet<Cliente>();
    }
    public boolean addCliente(Cliente cliente){
        for(Cliente client : clientes){
            if(client.getNIF().equals(cliente.getNIF())){
                return false;
            }
        }
        return clientes.add(cliente);
    }
    public boolean removeCliente(String nif){
        for ( Cliente cliente : clientes){
            if(cliente.getNIF().equals(nif)){
                return clientes.remove(cliente);
            }
        }
        return false;
    }

    public boolean setTarifa(String nif, Tarifa tarifa){
        for(Cliente client : clientes){
            if(client.getNIF().equals(nif)){
                return client.setTarifa(tarifa);
            }
        }
        return false;
    }
    public String listarDatos(String nif){
        for(Cliente client : clientes){
            if(client.getNIF().equals(nif)){
                return client.getCliente();
            }
        }
        return null;
    }
    public Set listaClientes(){
        return clientes;
    }

    public boolean addLlamada(Llamada llamada, String nif){
        for(Cliente client : clientes){
            if(client.getNIF().equals(nif)){
                return client.addLlamada(llamada);
            }
        }
        return false;
    }
    public Set listaLlamadas(String nif){
        for(Cliente client : clientes ){
            if(client.getNIF().equals(nif)){
                return client.listaLlamadas();
            }
        }
        return null;
    }
    public boolean emitirFactura(String codigo, String nif, Calendar fechaInicio, Calendar fechaFin){
        Factura newFactura = null;
        double importe = 0.0;
        Set<Llamada> llamadasRango;
        for(Cliente client : clientes){
            if(client.getNIF().equals(nif)){
                llamadasRango = client.llamadasRango(fechaInicio,fechaFin);
                for(Llamada llamada : llamadasRango){
                    importe += (llamada.getDuracion() * client.getTarifa().getPrecioPorSegundo());
                }
                newFactura = new Factura(codigo,client.getTarifa(),Calendar.getInstance(),fechaInicio,fechaFin,importe);
                return client.addFactura(newFactura);
            }
        }
        return false;
    }
    public String facturaDatos(String nif, String codigo){
        for(Cliente client : clientes){
            if(client.getNIF().equals(nif)){
                return client.buscaFactura(codigo).getFactura();
            }
        }
        return null;
    }

    public Set listaFacturas(String nif){
        for(Cliente client : clientes){
            if(client.getNIF().equals(nif)){
                return client.listaFacturas();
            }
        }
        return null;
    }
}
