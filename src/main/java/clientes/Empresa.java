package clientes;

import tarifas.Tarifa;

import java.util.Calendar;

public class Empresa extends Cliente {
    public Empresa(String nombre, String NIF, Direccion direccion, String email, Tarifa tarifa, Calendar fechaAlta) {
        super(nombre, NIF, direccion, email, tarifa, fechaAlta);
    }
}