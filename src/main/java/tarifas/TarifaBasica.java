package tarifas;

import llamadas.Llamada;

public class TarifaBasica extends Tarifa {
    private static final long serialVersionUID = 42L;

    public TarifaBasica(double precioPorSegundos){
        super(precioPorSegundos);
    }

    @Override
    public double calcularImporte(Llamada llamada) {
        return llamada.getDuracion() * getPrecioPorSegundo()/60;
    }
}
