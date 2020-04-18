package tarifas;

import llamadas.Llamada;

import java.io.Serializable;

public abstract class Tarifa implements Serializable {
    private static final long serialVersionUID = 42L;
    private double precioPorMinuto;

    public Tarifa() {
        super();
    }
    public Tarifa(double precioPorMinuto){
        this.precioPorMinuto = precioPorMinuto;
    }
    public double getPrecioPorMinuto() {
        return precioPorMinuto;
    }
    public abstract String descripcion();
    public void setPrecioPorMinuto(double precioPorMinuto) {
        this.precioPorMinuto = precioPorMinuto;
    }
    public abstract double calcularImporte(Llamada llamada);
}
