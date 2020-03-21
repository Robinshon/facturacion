package clientes;

import tarifas.Tarifa;

import java.io.Serializable;
import java.util.Calendar;

public class Empresa extends Cliente implements Serializable {
    public Empresa(String nombre, String NIF, Direccion direccion, String email, Tarifa tarifa, Calendar fechaAlta) {
        super(nombre, NIF, direccion, email, tarifa, fechaAlta);
    }
}
