package tarifas;

import java.time.DayOfWeek;

public class TarifaFactory {
    public static Tarifa crearTarifa(TipoTarifa tipo, Tarifa tarifa, double importe, DayOfWeek dia, Integer horaInit, Integer horaFin) {
        switch(tipo) {
            case BASICA:
                tarifa = new TarifaBasica(importe);
                break;
            case DIA:
                tarifa = new TarifaDia(tarifa, importe, dia);
                break;
            case HORAS:
                tarifa = new TarifaHoras(tarifa, importe, horaInit, horaFin);
                break;
        }

        return tarifa;
    }
}
