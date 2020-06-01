import java.time.DayOfWeek;
import java.util.Calendar;
import static org.junit.jupiter.api.Assertions.*;

import clientes.*;
import org.junit.jupiter.api.*;
import tarifas.*;


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


    @BeforeEach
    public void init() {
        empresa = new Empresa(nombre, nif, dir, correo, tarifa, fecha);
        particular = new Particular(nombre, apellidos, nif, dir, correo, tarifa, fecha);

    }

    @AfterEach
    public void finish() {
        empresa = null;
        particular = null;
        tarifa = null;
    }

    @Test
    public void testCliente() {
        Empresa empresa2 = (Empresa) ClienteFactory.crearCliente(TipoCliente.EMPRESA, nombre, apellidos, nif, dir, correo, tarifa, fecha);
        assertEquals(empresa.getNombre(), empresa2.getNombre());
        assertEquals(empresa.getEmail(), empresa2.getEmail());
        assertEquals(empresa.getNIF(), empresa2.getNIF());

        Particular particular2 = (Particular) ClienteFactory.crearCliente(TipoCliente.PARTICULAR, nombre, apellidos, nif, dir, correo, tarifa ,fecha);
        assertEquals(particular.getNombre(), particular2.getNombre());
        assertEquals(particular.getApellido(), particular2.getApellido());
        assertEquals(particular.getEmail(), particular2.getEmail());

    }


    @Test
    public void testTarifa() {
        TarifaBasica tarifaB = null;
        tarifaB = (TarifaBasica) TarifaFactory.crearTarifa(TipoTarifa.BASICA, tarifaB, 5,null,null,null);
        assertEquals(tarifa.getPrecioPorMinuto(), tarifaB.getPrecioPorMinuto());

        TarifaDia tarifaD = (TarifaDia) TarifaFactory.crearTarifa(TipoTarifa.DIA, tarifaB, 2,DayOfWeek.SUNDAY,null,null);
        tarifa = new TarifaDia(tarifa, 2, DayOfWeek.SUNDAY);
        assertEquals(tarifa.getPrecioPorMinuto(), tarifaD.getPrecioPorMinuto());

        TarifaHoras tarifaT = (TarifaHoras) TarifaFactory.crearTarifa(TipoTarifa.HORAS, tarifaB, 3,null,16,20);
        tarifa = new TarifaHoras(tarifa, 3,16,20);
        assertEquals(tarifa.getPrecioPorMinuto(), tarifaT.getPrecioPorMinuto());

    }
}
