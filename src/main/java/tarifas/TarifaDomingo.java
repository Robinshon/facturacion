package tarifas;

import llamadas.Llamada;

import java.util.Calendar;

public class TarifaDomingo extends TarifaExtra{
    private static final long serialVersionUID = 42L;
    public TarifaDomingo(Tarifa tarifa, double importeExtra) {
        super(tarifa,importeExtra);
    }
    @Override
    public double calcularImporte(Llamada llamada) {
        double importeBase = llamada.getDuracion() * getPrecioPorSegundo()/60;

        if(llamada.dameFecha().get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)
            return importeBase;
        return super.calcularImporte(llamada);
    }
}
