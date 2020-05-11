package tarifas;
// TODO: Como en la ClienteFactory, mejor una enumeración
public class TarifaFactory {
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
