package tarifas;

import llamadas.Llamada;

public abstract class TarifaExtra extends TarifaBasica {
    private Tarifa base;
    public TarifaExtra(Tarifa tarifa, double importeExtra) {
        super(importeExtra);
        this.base = tarifa;
        System.out.println(base.getClass());
    }
    @Override
    public double calcularImporte(Llamada llamada) {
        return base.calcularImporte(llamada);
    }

}
