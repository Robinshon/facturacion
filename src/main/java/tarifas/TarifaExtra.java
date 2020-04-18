package tarifas;

import llamadas.Llamada;

public abstract class TarifaExtra extends TarifaBasica {
    private  Tarifa base;
    public TarifaExtra(Tarifa tarifa, double importeExtra) {
        super(importeExtra);
        this.base = tarifa;
    }
    @Override
    public double calcularImporte(Llamada llamada) {
        return llamada.getDuracion() * base.getPrecioPorSegundo()/60;
    }

}
