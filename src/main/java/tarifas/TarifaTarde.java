package tarifas;

import llamadas.Llamada;

import java.util.Calendar;

public class TarifaTarde extends TarifaExtra{
    private static final long serialVersionUID = 42L;
    public TarifaTarde(Tarifa tarifa, double importeExtra) {
        super(tarifa,importeExtra);
    }
    @Override
    public double calcularImporte(Llamada llamada){
        double importeBase = llamada.getDuracion() * getPrecioPorMinuto()/60;

        if(llamada.dameFecha().get(Calendar.HOUR) >= 16 && llamada.dameFecha().get(Calendar.HOUR) < 20 && importeBase < super.calcularImporte(llamada))
            return importeBase;

        return super.calcularImporte(llamada);
    }

}
