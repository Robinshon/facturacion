package clientes;

import java.io.Serializable;

public class Direccion implements Serializable {
    private static final long serialVersionUID = 42L;
    private int codPostal;
    private String provincia;
    private String poblacion;

    public Direccion(int codPostal, String provincia, String poblacion){
        this.codPostal = codPostal;
        this.provincia = provincia;
        this.poblacion = poblacion;
    }


    public int getCodPostal() {
        return codPostal;
    }

    public String getProvincia() {
        return provincia;
    }

    public String getPoblacion() {
        return poblacion;
    }
    public String toString(){
        return "Codigo postal: " + getCodPostal() + " / " + "Provincia: " + getProvincia() + " / " + "Poblacion: " + getPoblacion();
    }
}
