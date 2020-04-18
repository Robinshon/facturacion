import java.util.Calendar;
import static org.junit.jupiter.api.Assertions.*;

import clientes.ClienteFactory;
import clientes.Direccion;
import clientes.Empresa;
import clientes.Particular;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import tarifas.TarifaBasica;
import tarifas.TarifaDomingo;
import tarifas.TarifaFactory;
import tarifas.TarifaTarde;


class FactoryTest {

    Empresa empresa;
    Particular particular;
    TarifaBasica tarifa = new TarifaBasica(5);
    String nombre = "Pepe";
    String nif = "2031";
    int cod = 1;
    String prov = "Castellon";
    String pob = "cs";
    Direccion dir = new Direccion(cod, prov, pob);
    String correo = "jaja";
    Calendar fecha = Calendar.getInstance();
    String apellidos = "Tenas";


    @BeforeAll
    public void init() {
        empresa = new Empresa(nombre, nif, dir, correo, tarifa, fecha);
        particular = new Particular(nombre, apellidos, nif, dir, correo, tarifa, fecha);

    }

    @AfterAll
    public void finish() {
        empresa = null;
        particular = null;
        tarifa = null;
    }

    @Test
    public void testCliente() {
        Empresa empresa2 = (Empresa) ClienteFactory.crearCliente(0, nombre, apellidos, nif, dir, correo, tarifa, fecha);
        assertEquals(empresa.getNombre(), empresa2.getNombre());
        assertEquals(empresa.getEmail(), empresa2.getEmail());
        assertEquals(empresa.getNIF(), empresa2.getNIF());

        Particular particular2 = (Particular) ClienteFactory.crearCliente(1, nombre, apellidos, nif, dir, correo, tarifa ,fecha);
        assertEquals(particular.getNombre(), particular2.getNombre());
        assertEquals(particular.getApellido(), particular2.getApellido());
        assertEquals(particular.getEmail(), particular2.getEmail());

    }


    @Test
    public void testTarifa() {
        TarifaBasica tarifaB = null;
        tarifaB = (TarifaBasica) TarifaFactory.crearTarifa(0, tarifaB, 5);
        assertEquals(tarifa.getPrecioPorSegundo(), tarifaB.getPrecioPorSegundo());

        TarifaDomingo tarifaD = (TarifaDomingo) TarifaFactory.crearTarifa(1, tarifaB, 2);
        tarifa = new TarifaDomingo(tarifa, 2);
        assertEquals(tarifa.getPrecioPorSegundo(), tarifaD.getPrecioPorSegundo());

        TarifaTarde tarifaT = (TarifaTarde) TarifaFactory.crearTarifa(2, tarifaB, 3);
        tarifa = new TarifaTarde(tarifa, 3);
        assertEquals(tarifa.getPrecioPorSegundo(), tarifaT.getPrecioPorSegundo());

    }
}
