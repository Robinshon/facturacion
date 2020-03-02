package cliente;

import cliente.Cliente;

import java.util.Calendar;

public class Particulares extends Cliente {
    private String apellido;
    public Particulares(String nombre, String apellido, String NIF, Direccion direccion, String email, Tarifa tarifa, Calendar fechaAlta){
        super(nombre, NIF, direccion, email, tarifa, fechaAlta);
        this.apellido = apellido;
    }
    public String getApellido(){
        return  apellido;
    }

    @Override

    public String getCliente() {
        return "Nombre: " + super.getNombre() +  " / " + "Apellido: " + getApellido() + " / " + "NIF: " + super.getNIF() + " / " + "Direccion: " + super.getDireccion().getDireccion() + " / " + "Fecha de alta: " + super.getFechaAltaNormal() + " / " + "Email: " + super.getEmail() + " / " + "Tarifa: " + super.getTarifa().getPrecioPorSegundo() ;

    }
}
