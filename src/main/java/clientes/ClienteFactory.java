package clientes;

import tarifas.Tarifa;

import java.util.Calendar;
// TODO: mejor que usar constantes de tipo int es crear una enumeración
//   public enum TipoCliente { EMPRESA, PARTICULAR; }
public class ClienteFactory {
    public static int EMPRESA = 0;
    public static int PARTICULAR = 1;
    public static Cliente crearCliente(int tipo, String nombre, String apellidos, String nif, Direccion dir, String correo, Tarifa tarifa, Calendar fecha) {
        Cliente cliente = null;

        switch(tipo) {
            case 0:
                cliente = new Empresa(nombre, nif, dir, correo, tarifa, fecha);
                break;
            case 1:
                cliente = new Particular(nombre, apellidos, nif, dir, correo, tarifa, fecha);
                break;
        }
        return cliente;
    }
}
