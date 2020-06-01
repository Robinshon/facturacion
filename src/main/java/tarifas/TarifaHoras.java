package tarifas;

import llamadas.Llamada;

import java.util.Calendar;

public class TarifaHoras extends TarifaExtra{
    private static final long serialVersionUID = 42L;
    private int horaInit;
    private  int horaFin;
    public TarifaHoras(Tarifa tarifa, double importeExtra, int horaInit, int horaFin) {
        super(tarifa,importeExtra);
        this.horaInit = horaInit;
        this.horaFin = horaFin;
    }
    @Override
    public double calcularImporte(Llamada llamada){
        double importeBase = llamada.getDuracion() * getPrecioPorMinuto()/60;

        if(llamada.dameFecha().get(Calendar.HOUR_OF_DAY) >= 16 && llamada.dameFecha().get(Calendar.HOUR_OF_DAY) < 20 && importeBase < super.calcularImporte(llamada))
            return importeBase;

        return super.calcularImporte(llamada);
    }

    @Override
    public String descripcion() {
        return super.descripcion() + ", De" + horaInit + "a " + horaFin + ": " + getPrecioPorMinuto();
    }

}
