package llamadas;

import fechas.EntreFechas;

import java.io.Serializable;
import java.util.Calendar;

public class Llamada extends EntreFechas implements Serializable {
    private static final long serialVersionUID = 42L;
    private String telefono;
    private Calendar fechaYHora;
    private int duracion;
    public Llamada(String telefono, Calendar fechaYHora, int duracion){
        this.telefono = telefono;
        this.fechaYHora = fechaYHora;
        this.duracion = duracion;

    }
    public int getDuracion() {
        return duracion;
    }

    @Override
    public Calendar dameFecha(){
        return fechaYHora;
    }

    public String getFecha(){
        return fechaYHora.get(Calendar.DAY_OF_MONTH) + "#" + fechaYHora.get(Calendar.MONTH) + "#" + fechaYHora.get(Calendar.YEAR) + "#" + fechaYHora.get(Calendar.HOUR_OF_DAY) + "#" +  fechaYHora.get(Calendar.MINUTE) + "#" + fechaYHora.get(Calendar.SECOND);
    }
    public String getTelefono() {
        return telefono;
    }

    @Override
    public String toString(){
        return "Telefono: " + getTelefono() + " / " + "Fecha llamada: " + getFecha() + " / " + "Duracion: " + getDuracion();
    }
}
