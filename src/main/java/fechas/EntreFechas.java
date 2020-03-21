package fechas;

import excepciones.IllegalPeriodException;

import java.util.Calendar;
import java.util.Collection;
import java.util.LinkedList;

public class EntreFechas {

    private Calendar fecha;
    public Calendar dameFecha() {
        return this.fecha;
    }

    public static <T extends EntreFechas> Collection<T> listaEntreFechas(Collection<T> datos, Calendar fechaInicio, Calendar fechaFin) throws IllegalPeriodException {
        if(fechaInicio.after(fechaFin)) {
            throw new IllegalPeriodException();
        }
        Collection<T> res = new LinkedList<T>();
        for(T dato : datos) {
            if (fechaInicio.before(dato.dameFecha()) && fechaFin.after(dato.dameFecha())) {
                res.add(dato);
            }
        }
        return res;
    }
}
