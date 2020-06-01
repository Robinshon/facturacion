package tarifas;

import llamadas.Llamada;

import java.time.DayOfWeek;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class TarifaDia extends TarifaExtra{
    private static final long serialVersionUID = 42L;
    private DayOfWeek dia;
    private String valorDia;
    public TarifaDia(Tarifa tarifa, double importeExtra, DayOfWeek dia) {
        super(tarifa,importeExtra);
        this.dia = dia;
    }
    @Override
    public double calcularImporte(Llamada llamada) {
        double importeBase = llamada.getDuracion() * getPrecioPorMinuto()/60;

        if(llamada.dameFecha().get(Calendar.DAY_OF_WEEK) == dia.getValue() && importeBase < super.calcularImporte(llamada)) {
            return importeBase;
        }
        return super.calcularImporte(llamada);
    }
    @Override
    public String descripcion() {
        switch (dia) {
            case MONDAY:
                valorDia = "Lunes";
                break;
            case TUESDAY:
                valorDia = "Martes";
                break;
            case WEDNESDAY:
                valorDia = "Miércoles";
                break;
            case THURSDAY:
                valorDia = "Jueves";
                break;
            case FRIDAY:
                valorDia = "Viernes";
                break;
            case SATURDAY:
                valorDia = "Sábado";
                break;
            case SUNDAY:
                valorDia = "Domingo";
                break;
        }
        return super.descripcion() + ", El día " + valorDia + ": " + getPrecioPorMinuto();
    }
}
