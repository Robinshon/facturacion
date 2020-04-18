package tarifas;

import llamadas.Llamada;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.Calendar;
import static org.junit.jupiter.api.Assertions.*;

class TarifaTest {

    Llamada llamada;
    Llamada llamada2;
    TarifaBasica tarifa;

    @BeforeAll
    public void init() {
        Calendar fecha = Calendar.getInstance();
        fecha.set(Calendar.DAY_OF_WEEK,1);
        llamada = new Llamada("655847852",fecha,500);
        Calendar fecha2 = Calendar.getInstance();
        fecha2.set(Calendar.HOUR_OF_DAY,17);
        llamada2 = new Llamada("666445874",fecha2,600);
        Calendar fecha3 = Calendar.getInstance();
        fecha3.set(Calendar.DAY_OF_WEEK,1);
        fecha3.set(Calendar.HOUR_OF_DAY, 18);

        Calendar fecha4 = Calendar.getInstance();
        fecha4.set(Calendar.DAY_OF_WEEK,6);

        tarifa = new TarifaBasica(10);
    }

    @AfterAll
    public void finish() {
        llamada = null;
        llamada2 = null;
        tarifa=null;
    }

    @Test
    public void testCalcularImporte() {
        tarifa = new TarifaDomingo(tarifa, 0);
        assertEquals(0,tarifa.calcularImporte(llamada), -1);
        tarifa = new TarifaTarde(tarifa,5);
        assertEquals(3000,tarifa.calcularImporte(llamada2),-1);
    }
}