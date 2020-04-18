package clientes;

import tarifas.Tarifa;

import java.io.Serializable;
import java.util.Calendar;

public class Particular extends Cliente implements Serializable {
    private static final long serialVersionUID = 42L;
    private String apellido;
    public Particular(String nombre, String apellido, String NIF, Direccion direccion, String email, Tarifa tarifa, Calendar fechaAlta){
        super(nombre, NIF, direccion, email, tarifa, fechaAlta);
        this.apellido = apellido;
    }
    public String getApellido(){
        return  apellido;
    }

    @Override
    public String toString() {
        return "Nombre: " + super.getNombre() +  " / " + "Apellido: " + getApellido() + " / " + "NIF: " + super.getNIF() + " / " + "Direccion: " + super.getDireccion().toString() + " / " + "Fecha de alta: " + super.getFecha() + " / " + "Email: " + super.getEmail() + " / " + "Tarifa: " + super.getTarifa().descripcion() ;

    }
}
