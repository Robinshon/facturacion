package cliente;

import java.util.*;

public abstract class Cliente {
    private String nombre;
    private String NIF;
    private Direccion direccion;
    private String email;
    private Calendar fechaAlta;
    private Tarifa tarifa;
    private Set<Llamada> llamadas;
    private HashMap<String,Factura> facturas;

    public Cliente(String nombre, String NIF, Direccion direccion, String email, Tarifa tarifa, Calendar fechaAlta){
        this.nombre = nombre;
        this.NIF = NIF;
        this. direccion = direccion;
        this.email = email;
        this.tarifa = tarifa;
        this.fechaAlta = fechaAlta;
        this.llamadas = new HashSet<Llamada>();
        this.facturas = new HashMap<String,Factura>();
    }
    public boolean addLlamada(Llamada llamada){
        return llamadas.add(llamada);
    }
    public Set<Llamada> listaLlamadas(){
        return llamadas;
    }
    public Set<Llamada> llamadasRango(Calendar fechaInicio, Calendar fechaFin){
        Set<Llamada> llamadasRango = new HashSet<Llamada>();
        for(Llamada llamada : llamadas){
            if(llamada.getFecha().after(fechaInicio) && llamada.getFecha().before(fechaFin)){
                llamadasRango.add(llamada);
            }
        }
        return llamadasRango;
    }
    public boolean addFactura(Factura factura){
        if(facturas.containsKey(factura.getCodigo())){
            return false;
        }
        facturas.put(factura.getCodigo(),factura);
        return true;
    }
    public HashMap<String,Factura> listaFacturas(){
        return facturas;
    }

    public Factura buscaFactura(String codigo){
        return facturas.get(codigo);
    }

    public String toString(){
        return "Nombre: " + getNombre() + " / " + "NIF: " + getNIF() + " / " + "Direccion: " + direccion.toString() + " / " + "Fecha de alta: " + getFecha() + " / " + "Email: " + getEmail() + " / " + "Tarifa: " + tarifa.getPrecioPorSegundo() ;
    }

    public String getNombre() {
        return nombre;
    }

    public String getNIF() {
        return NIF;
    }

    public String getEmail() {
        return email;
    }

    public String getFecha() {
        return fechaAlta.get(Calendar.DAY_OF_MONTH) + "#" + fechaAlta.get(Calendar.MONTH) + "#" + fechaAlta.get(Calendar.YEAR);
    }

    public boolean setTarifa(Tarifa newTarifa){
        tarifa = newTarifa;
        return true;
    }
    public Direccion getDireccion(){
        return direccion;
    }

    public Tarifa getTarifa() {
        return tarifa;
    }
}

