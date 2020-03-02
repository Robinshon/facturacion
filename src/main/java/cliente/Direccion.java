package cliente;

public class Direccion {
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
    public String getDireccion(){
        return "Codigo postal: " + getCodPostal() + " / " + "Provincia: " + getProvincia() + " / " + "Poblacion: " + getPoblacion();
    }
}
