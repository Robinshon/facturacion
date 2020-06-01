package tarifas;

import llamadas.Llamada;

import java.time.DayOfWeek;

public abstract class TarifaExtra extends TarifaBasica {
    private Tarifa base;
    public TarifaExtra(Tarifa tarifa, double importeExtra) {
        super(importeExtra);
        this.base = tarifa;
    }
    @Override
    public double calcularImporte(Llamada llamada) {
        return base.calcularImporte(llamada);
    }

    @Override
    public String descripcion() {
        return base.descripcion();
    }

}