package menu;

import excepciones.*;
import facturas.Factura;
import gestor.Gestor;

import java.util.*;

import clientes.*;
import llamadas.Llamada;
import tarifas.Tarifa;
import tarifas.TarifaFactory;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class Menu {

    private static String input(String text) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(text);
        return scanner.nextLine();
    }
    // TODO: main no debe lanzar excepciones, tenéis que capturarlas con un try catch
    public static void main(String [] args) throws NotExistingClientException, ExistingClientException, NullListCallException, NotExistingInvoceException, NullListClientsException, NullListInvoicesException, IllegalPeriodException, ExistingInvoiceException {
        Scanner scanner = new Scanner(System.in);
        Gestor gestor = new Gestor();
        //gestor.cargarDatos();
        HashMap<String, Cliente> clientes;
        Set<Llamada> llamadas;
        HashMap<String, Factura> facturas;
        OpcionMenu opcionMenu;
        // TODO: Es mejor separar la lógica del menú, así es inmanejable
        do {
            System.out.println(OpcionMenu.menu());
            String opcion = input("Elige una opcion");
            System.out.print("\n");
            opcionMenu = OpcionMenu.obtenerOpcion(Integer.parseInt(opcion));
            String nif, codigo;
            double tarifa;
            Tarifa tar;
            int year, month, day, hour, minute, second, duracion, yearI, yearF, monthI, monthF, dayI, dayF ,tipo;
            switch (opcionMenu) {
                case DAR_DE_ALTA_CLIENTE:
                    String nombre = input("Nombre: ");
                    String apellido = input("Apellido(si es una cadena vacia es una empresa): ");
                    nif = input("NIF: ");
                    int cp = parseInt(input("Codigo postal: "));
                    String provincia = input("Provincia: ");
                    String poblacion = input("Poblacion: ");
                    String email = input("Email: ");
                    year = parseInt(input("Año: "));
                    month = parseInt(input("Mes: "));
                    day = parseInt(input("Dia: "));
                    Cliente c;
                    tar = null;
                    tipo = parseInt(input("Tipo de tarifa '0' Tarifa Basica '1' Tarifa Domingo '2' Tarifa Tardes"));
                    tarifa = parseDouble(input("Tarifa precio por segundos: "));
                    tar = TarifaFactory.crearTarifa(tipo, tar, tarifa);
                    if (apellido.equals("")) {
                        c = ClienteFactory.crearCliente(0,nombre,apellido,nif,new Direccion(cp,provincia,poblacion),email,tar,new GregorianCalendar(year, month, day));
                    } else {
                        c = ClienteFactory.crearCliente(1,nombre,apellido,nif,new Direccion(cp,provincia,poblacion),email,tar,new GregorianCalendar(year, month, day));
                    }
                    gestor.addCliente(c);
                    break;
                case BORRAR_CLIENTE:
                    nif = input("NIF: ");
                    gestor.removeCliente(nif);
                    break;
                case CAMBIAR_TARIFA:
                    nif = input("NIF: ");
                    tar = null;
                    tarifa = parseDouble(input("Tarifa precio por segundos: "));
                    tar = TarifaFactory.crearTarifa(0, tar, tarifa);
                    gestor.setTarifa(nif, tar);
                    break;
                case DATOS_CLIENTE:
                    nif = input("NIF: ");
                    System.out.println(gestor.listarDatos(nif) + "\n");
                    break;
                case LISTA_CLIENTE:
                    clientes = gestor.listaClientes();
                    for (String cliente : clientes.keySet()) {
                        System.out.println(clientes.get(cliente).toString());
                    }
                    System.out.println();
                    break;
                case NUEVA_LLAMADA:
                    nif = input("NIF: ");
                    String telefono = input("Telefono: ");
                    year = parseInt(input("Año: "));
                    month = parseInt(input("Mes: "));
                    day = parseInt(input("Dia: "));
                    hour = parseInt(input("Hora: "));
                    minute = parseInt(input("Minutos: "));
                    second = parseInt(input("Segundos: "));
                    duracion = parseInt(input("Duracion llamada en segundos: "));
                    gestor.addLlamada(new Llamada(telefono, new GregorianCalendar(year, month, day, hour, minute, second), duracion), nif);
                    break;
                case LISTADO_LLAMADAS:
                    nif = input("NIF: ");
                    llamadas = gestor.listaLlamadas(nif);
                    for (Llamada llamada : llamadas) {
                        System.out.println(llamada.toString());
                    }
                    break;
                case NUEVA_FACTURA:
                    nif = input("NIF: ");
                    codigo = input("Codigo: ");
                    yearI = parseInt(input("Año inicio: "));
                    monthI = parseInt(input("Mes inicio: "));
                    dayI = parseInt(input("Dia inicio: "));
                    yearF = parseInt(input("Año final: "));
                    monthF = parseInt(input("Mes final: "));
                    dayF = parseInt(input("Dia final: "));
                    gestor.emitirFactura(codigo, nif, new GregorianCalendar(yearI, monthI, dayI), new GregorianCalendar(yearF, monthF, dayF));
                    break;
                case DATOS_FACTURA:
                    nif = input("NIF: ");
                    codigo = input("Codigo: ");
                    System.out.println(gestor.facturaDatos(nif, codigo));
                    break;
                case MOSTRAR_FACTURAS:
                    nif = input("NIF: ");
                    facturas = gestor.listaFacturas(nif);
                    for (String factura : facturas.keySet()) {
                        System.out.println(facturas.get(factura).toString());
                    }
                    break;
                case FECHAS_ALTA_CLIENTE:
                    yearI = parseInt(input("Año inicio: "));
                    monthI = parseInt(input("Mes inicio: "));
                    dayI = parseInt(input("Dia inicio: "));
                    yearF = parseInt(input("Año final: "));
                    monthF = parseInt(input("Mes final: "));
                    dayF = parseInt(input("Dia final: "));
                    Collection<Cliente> listaClientes = gestor.mostrarListaClientesEntreFechas(new GregorianCalendar(yearI, monthI, dayI), new GregorianCalendar(yearF, monthF, dayF));
                    for(Cliente cliente : listaClientes){
                        System.out.println(cliente.toString());
                    }
                    break;
                case FECHAS_LLAMADAS:
                    nif = input("NIF: ");
                    yearI = parseInt(input("Año inicio: "));
                    monthI = parseInt(input("Mes inicio: "));
                    dayI = parseInt(input("Dia inicio: "));
                    yearF = parseInt(input("Año final: "));
                    monthF = parseInt(input("Mes final: "));
                    dayF = parseInt(input("Dia final: "));
                    Collection<Llamada> listaLlamadas = gestor.mostrarListaLlamadasEntreFechas(nif,new GregorianCalendar(yearI, monthI, dayI), new GregorianCalendar(yearF, monthF, dayF));
                    for(Llamada llamada : listaLlamadas){
                        System.out.println(llamada.toString());
                    }
                    break;
                case FECHAS_FACTURAS:
                    nif = input("NIF: ");
                    yearI = parseInt(input("Año inicio: "));
                    monthI = parseInt(input("Mes inicio: "));
                    dayI = parseInt(input("Dia inicio: "));
                    yearF = parseInt(input("Año final: "));
                    monthF = parseInt(input("Mes final: "));
                    dayF = parseInt(input("Dia final: "));
                    Collection<Factura> listaFacturas = gestor.mostrarListaFacturasEntreFechas(nif,new GregorianCalendar(yearI, monthI, dayI), new GregorianCalendar(yearF, monthF, dayF));
                    for(Factura factura: listaFacturas){
                        System.out.println(factura.toString());
                    }
                    break;
                case SALIR:
                    gestor.guardarDatos();
                    scanner.close();
                    break;
            }
        } while (opcionMenu != OpcionMenu.SALIR);

    }
}
