package tarifas;

import llamadas.Llamada;

import java.io.Serializable;

public abstract class Tarifa implements Serializable {
    private static final long serialVersionUID = 42L;
    private double precioPorSegundo;

    public Tarifa() {
        super();
    }
    public Tarifa(double precioPorSegundo){
        this.precioPorSegundo = precioPorSegundo;
    }
    public double getPrecioPorSegundo() {
        return  precioPorSegundo;
    }
    public void setPrecioPorSegundo(double precioPorSegundo) {
        this.precioPorSegundo = precioPorSegundo;
    }
    public abstract double calcularImporte(Llamada llamada);
}
