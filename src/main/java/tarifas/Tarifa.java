package tarifas;

import java.io.Serializable;

public class Tarifa implements Serializable {
    private static final long serialVersionUID = 42L;
    private double precioPorSegundo;
    public Tarifa(double precioPorSegundo){
        this.precioPorSegundo = precioPorSegundo;
    }
    public double getPrecioPorSegundo() {
        return  precioPorSegundo;
    }
}
