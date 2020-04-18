package tarifas;

import llamadas.Llamada;

public class TarifaBasica extends Tarifa {
    private static final long serialVersionUID = 42L;

    public TarifaBasica(double precioPorMinuto){
        super(precioPorMinuto);
    }

    public String descripcion() {
        return "Básica " + getPrecioPorMinuto();
    }

    @Override
    public double calcularImporte(Llamada llamada) {
        return llamada.getDuracion() * getPrecioPorMinuto()/60;
    }
}
