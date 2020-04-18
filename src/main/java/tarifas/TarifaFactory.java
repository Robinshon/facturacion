package tarifas;

import java.util.List;

public class TarifaFactory {
    public static Tarifa crearTarifa(List<Integer> tipos) {
        Tarifa tarifa = new TarifaBasica(0.15);
        for (int tipo : tipos) {

        }
        return tarifa;
    }

    public static Tarifa crearTarifa(int tipo,Tarifa tarifa, double importe) {

        switch(tipo) {
            case 0:
                tarifa = new TarifaBasica(importe);
                break;
            case 1:
                tarifa = new TarifaDomingo(tarifa, importe);
                break;
            case 2:
                tarifa = new TarifaTarde(tarifa, importe);
                break;
        }

        return tarifa;
    }
}
