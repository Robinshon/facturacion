package tarifas;

import llamadas.Llamada;

import java.util.Calendar;
// TODO: Debería ser posible elegir el día de la semana
// lo haremos para la entrega final
public class TarifaDomingo extends TarifaExtra{
    private static final long serialVersionUID = 42L;

    public TarifaDomingo(Tarifa tarifa, double importeExtra) {
        super(tarifa,importeExtra);
    }
    @Override
    public double calcularImporte(Llamada llamada) {
        double importeBase = llamada.getDuracion() * getPrecioPorMinuto()/60;

        if(llamada.dameFecha().get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY && importeBase < super.calcularImporte(llamada)) {
            return importeBase;
        }
        return super.calcularImporte(llamada);
    }
    @Override
    public String descripcion() {
        return super.descripcion() + ", Domingo " + getPrecioPorMinuto();
    }
}
