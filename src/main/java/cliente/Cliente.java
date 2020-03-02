package cliente;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public abstract class Cliente {
    private String nombre;
    private String NIF;
    private Direccion direccion;
    private String email;
    private Calendar fechaAlta;
    private Tarifa tarifa;
    private Set<Llamada> llamadas;
    private Set<Factura> facturas;

    public Cliente(String nombre, String NIF, Direccion direccion, String email, Tarifa tarifa, Calendar fechaAlta){
        this.nombre = nombre;
        this.NIF = NIF;
        this. direccion = direccion;
        this.email = email;
        this.tarifa = tarifa;
        this.fechaAlta = fechaAlta;
        this.llamadas = new HashSet<Llamada>();
        this.facturas = new HashSet<Factura>();
    }
    public Calendar getFecha(){
        return fechaAlta;
    }

    public boolean addLlamada(Llamada llamada){
        return llamadas.add(llamada);
    }
    public Set listaLlamadas(){
        return llamadas;
    }
    public Set llamadasRango(Calendar fechaInicio, Calendar fechaFin){
        Set<Llamada> llamadasRango = new HashSet<Llamada>();
        for(Llamada llamada : llamadas){
            if(llamada.getFecha().after(fechaInicio) && llamada.getFecha().before(fechaFin)){
                llamadasRango.add(llamada);
            }
        }
        return llamadasRango;
    }
    public boolean addFactura(Factura factura){
        for(Factura fact : facturas){
            if(fact.getCodigo().equals(factura.getCodigo())){
                return false;
            }
        }
        return facturas.add(factura);
    }
    public Set listaFacturas(){
        return facturas;
    }

    public Factura buscaFactura(String codigo){
        for(Factura factura: facturas){
            if(factura.getCodigo().equals(codigo)){
                return factura;
            }
        }
        return null;
    }

    public String getCliente(){
        return "Nombre: " + getNombre() + " / " + "NIF: " + getNIF() + " / " + "Direccion: " + direccion.getDireccion() + " / " + "Fecha de alta: " + getFechaAltaNormal() + " / " + "Email: " + getEmail() + " / " + "Tarifa: " + tarifa.getPrecioPorSegundo() ;
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

    public Calendar getFechaAlta() {
        return fechaAlta;
    }
    public String getFechaAltaNormal() {
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

