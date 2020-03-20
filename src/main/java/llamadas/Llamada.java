package llamadas;

import java.util.Calendar;

public class Llamada {
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
    public Calendar getFecha() {
        return fechaYHora;
    }
    public String getFechaNormal(){
        return fechaYHora.get(Calendar.DAY_OF_MONTH) + "#" + fechaYHora.get(Calendar.MONTH) + "#" + fechaYHora.get(Calendar.YEAR) + "#" + fechaYHora.get(Calendar.HOUR_OF_DAY) + "#" +  fechaYHora.get(Calendar.MINUTE) + "#" + fechaYHora.get(Calendar.SECOND);
    }
    public String getTelefono() {
        return telefono;
    }

    public String toString(){
        return "Telefono: " + getTelefono() + " / " + "Fecha llamada: " + getFechaNormal() + " / " + "Duracion: " + getDuracion();
    }
}
