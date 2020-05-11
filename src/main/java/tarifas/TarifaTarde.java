package tarifas;

import llamadas.Llamada;

import java.util.Calendar;
// TODO: sería mejor que las horas fueran parámetros para poder elegirlas
public class TarifaTarde extends TarifaExtra{
    private static final long serialVersionUID = 42L;
    public TarifaTarde(Tarifa tarifa, double importeExtra) {
        super(tarifa,importeExtra);
    }
    @Override
    public double calcularImporte(Llamada llamada){
        double importeBase = llamada.getDuracion() * getPrecioPorMinuto()/60;

        if(llamada.dameFecha().get(Calendar.HOUR_OF_DAY) >= 16 && llamada.dameFecha().get(Calendar.HOUR_OF_DAY) < 20 && importeBase < super.calcularImporte(llamada))
            return importeBase;

        return super.calcularImporte(llamada);
    }

    @Override
    public String descripcion() {
        return super.descripcion() + ", Tarde " + getPrecioPorMinuto();
    }

}
