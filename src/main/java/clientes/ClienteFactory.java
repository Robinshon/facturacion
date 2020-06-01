package clientes;

import tarifas.Tarifa;

import java.util.Calendar;
public class ClienteFactory {
    public static Cliente crearCliente(TipoCliente tipo, String nombre, String apellidos, String nif, Direccion dir, String correo, Tarifa tarifa, Calendar fecha) {
        Cliente cliente = null;

        switch(tipo) {
            case EMPRESA:
                cliente = new Empresa(nombre, nif, dir, correo, tarifa, fecha);
                break;
            case PARTICULAR:
                cliente = new Particular(nombre, apellidos, nif, dir, correo, tarifa, fecha);
                break;
        }
        return cliente;
    }
}
