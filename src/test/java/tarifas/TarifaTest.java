package tarifas;

import llamadas.Llamada;
import org.junit.jupiter.api.*;

import java.util.Calendar;
import static org.junit.jupiter.api.Assertions.*;

class TarifaTest {

    Llamada llamada;
    Llamada llamada2;
    TarifaBasica base;
    Tarifa tarifa;

    @BeforeEach
    public void init() {
        Calendar fecha = Calendar.getInstance();
        fecha.set(Calendar.DAY_OF_WEEK,1);
        fecha.set(Calendar.HOUR_OF_DAY,12);
        llamada = new Llamada("655847852",fecha,540);
        Calendar fecha2 = Calendar.getInstance();
        fecha2.set(Calendar.DAY_OF_WEEK,2);
        fecha2.set(Calendar.HOUR_OF_DAY,17);
        llamada2 = new Llamada("666445874",fecha2,600);
        base = new TarifaBasica(10);
    }

    @AfterEach
    public void finish() {
        llamada = null;
        llamada2 = null;
        base = null;
    }

    @Test
    public void testDomingo() {
        tarifa = new TarifaDomingo(base, 0);
        assertEquals(100,tarifa.calcularImporte(llamada2));
        assertEquals(0,tarifa.calcularImporte(llamada));
    }

    @Test void testTarde() {
        tarifa = new TarifaTarde(base,3);
        assertEquals(30,tarifa.calcularImporte(llamada2));
        assertEquals(90,tarifa.calcularImporte(llamada));
    }

    @Test void testTodasPrimeroDomigo() {
        tarifa = new TarifaDomingo(base,0);
        tarifa = new TarifaTarde(tarifa, 3);
        assertEquals(30,tarifa.calcularImporte(llamada2));
        assertEquals(0,tarifa.calcularImporte(llamada));
    }

    @Test void testTodasPrimeroTarde() {
        tarifa = new TarifaTarde(base,3);
        tarifa = new TarifaDomingo(tarifa, 0);
        assertEquals(30,tarifa.calcularImporte(llamada2));
        assertEquals(0,tarifa.calcularImporte(llamada));
    }

    @Test
    public void testCalcularImporte() {
        tarifa = new TarifaDomingo(base, 0);
        assertEquals(100,tarifa.calcularImporte(llamada2));
        assertEquals(0,tarifa.calcularImporte(llamada));
        tarifa = new TarifaTarde(tarifa,3);
        assertEquals(30,tarifa.calcularImporte(llamada2));
        assertEquals(0,tarifa.calcularImporte(llamada));
        tarifa = new TarifaTarde(tarifa,2);
        assertEquals(20,tarifa.calcularImporte(llamada2));
        assertEquals(0,tarifa.calcularImporte(llamada));

    }
}