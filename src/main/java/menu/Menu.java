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
    public static void main(String [] args) {
        Scanner scanner = new Scanner(System.in);
        Gestor gestor = new Gestor();
        gestor.cargarDatos();
        HashMap<String, Cliente> clientes;
        Set<Llamada> llamadas;
        HashMap<String, Factura> facturas;
        OpcionMenu opcionMenu;
        // TODO: Es mejor separar la lógica del menú, así es inmanejable
        // lo mejoraremos para la próxima entrega
        do {
            System.out.println(OpcionMenu.menu());
            String opcion = input("Elige una opcion");
            System.out.print("\n");
            opcionMenu = OpcionMenu.obtenerOpcion(Integer.parseInt(opcion));
            String nif, codigo;
            double tarifa;
            String tarifaDomingos;
            String tarifaTardes;
            Tarifa tar;
            int year, month, day, hour, minute, second, duracion, yearI, yearF, monthI, monthF, dayI, dayF;
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
                    month -= 1; //Porque queremos que el mes 1 sea Enero y en GregorianCalendar Enero es el mes 0.
                    day = parseInt(input("Dia: "));
                    Cliente c;
                    tar = null;
                    tarifa = parseDouble(input("Precio por minutos de la tarifa básica: "));
                    tarifaDomingos = input("Precio por minutos de la tarifa los domingos(si es una cadena vacía no tiene tarifa especial los domingos): ");
                    tarifaTardes = input("Precio por minutos de la tarifa por la tarde(si es una cadena vacía no tiene tarifa especial por la tarde): ");
                    tar = TarifaFactory.crearTarifa(0, tar, tarifa);
                    if (!tarifaDomingos.equals("")) {
                        tar = TarifaFactory.crearTarifa(1, tar, parseDouble(tarifaDomingos));
                    }
                    if (!tarifaTardes.equals("")) {
                        tar = TarifaFactory.crearTarifa(2, tar, parseDouble(tarifaTardes));
                    }
                    if (apellido.equals("")) {
                        c = ClienteFactory.crearCliente(ClienteFactory.EMPRESA,nombre,apellido,nif,new Direccion(cp,provincia,poblacion),email,tar,new GregorianCalendar(year, month, day));
                    } else {
                        c = ClienteFactory.crearCliente(ClienteFactory.PARTICULAR,nombre,apellido,nif,new Direccion(cp,provincia,poblacion),email,tar,new GregorianCalendar(year, month, day));
                    }
                    try{
                        gestor.addCliente(c);
                    }
                    catch (ExistingClientException e){
                    }
                    break;
                case BORRAR_CLIENTE:
                    nif = input("NIF: ");
                    try{
                        gestor.removeCliente(nif);
                    }
                    catch (NotExistingClientException e){
                    }
                    break;
                case CAMBIAR_TARIFA:
                    nif = input("NIF: ");
                    tar = null;
                    tarifa = parseDouble(input("Precio por minutos de la tarifa básica: "));
                    tarifaDomingos = input("Precio por minutos de la tarifa los domings(si es una cadena vacía no tiene tarifa especial los domingos): ");
                    tarifaTardes = input("Precio por minutos de la tarifa por la tarde(si es una cadena vacía no tiene tarifa especial por la tarde): ");
                    tar = TarifaFactory.crearTarifa(0, tar, tarifa);
                    if (!tarifaDomingos.equals("")) {
                        tar = TarifaFactory.crearTarifa(1, tar, parseDouble(tarifaDomingos));
                    }
                    if (!tarifaTardes.equals("")) {
                        tar = TarifaFactory.crearTarifa(2, tar, parseDouble(tarifaTardes));
                    }
                    try{
                        gestor.setTarifa(nif, tar);
                    }
                    catch (NotExistingClientException e){
                    }
                    break;
                case DATOS_CLIENTE:
                    nif = input("NIF: ");
                    try{
                        System.out.println(gestor.listarDatos(nif) + "\n");
                    }
                    catch (NotExistingClientException e){
                    }
                    break;
                case LISTA_CLIENTE:
                    try {
                        clientes = gestor.listaClientes();
                        for (String cliente : clientes.keySet()) {
                            System.out.println(clientes.get(cliente).toString());
                        }
                    } catch (NullListClientsException e) {
                    }
                    System.out.println();
                    break;
                case NUEVA_LLAMADA:
                    nif = input("NIF: ");
                    String telefono = input("Telefono: ");
                    year = parseInt(input("Año: "));
                    month = parseInt(input("Mes: "));
                    month -= 1; //Porque queremos que el mes 1 sea Enero y en GregorianCalendar Enero es el mes 0.
                    day = parseInt(input("Dia: "));
                    hour = parseInt(input("Hora: "));
                    minute = parseInt(input("Minutos: "));
                    second = parseInt(input("Segundos: "));
                    duracion = parseInt(input("Duracion llamada en segundos: "));
                    try {
                        gestor.addLlamada(new Llamada(telefono, new GregorianCalendar(year, month, day, hour, minute, second), duracion), nif);
                    } catch (NotExistingClientException e) {
                    }
                    break;
                case LISTADO_LLAMADAS:
                    nif = input("NIF: ");
                    try {
                        llamadas = gestor.listaLlamadas(nif);
                        for (Llamada llamada : llamadas) {
                            System.out.println(llamada.toString());
                        }
                    } catch (NotExistingClientException e) {
                    } catch (NullListCallException e) {
                    }
                    break;
                case NUEVA_FACTURA:
                    nif = input("NIF: ");
                    codigo = input("Codigo: ");
                    yearI = parseInt(input("Año inicio: "));
                    monthI = parseInt(input("Mes inicio: "));
                    monthI -= 1; //Porque queremos que el mes 1 sea Enero y en GregorianCalendar Enero es el mes 0.
                    dayI = parseInt(input("Dia inicio: "));
                    yearF = parseInt(input("Año final: "));
                    monthF = parseInt(input("Mes final: "));
                    monthF -= 1; //Porque queremos que el mes 1 sea Enero y en GregorianCalendar Enero es el mes 0.
                    dayF = parseInt(input("Dia final: "));
                    try {
                        gestor.emitirFactura(codigo, nif, new GregorianCalendar(yearI, monthI, dayI), new GregorianCalendar(yearF, monthF, dayF));
                    } catch (NotExistingClientException e) {
                    } catch (IllegalPeriodException e) {
                    } catch (ExistingInvoiceException e) {
                    }
                    break;
                case DATOS_FACTURA:
                    nif = input("NIF: ");
                    codigo = input("Codigo: ");
                    try {
                        System.out.println(gestor.facturaDatos(nif, codigo));
                    } catch (NotExistingClientException e) {
                    } catch (NotExistingInvoceException e) {
                    }
                    break;
                case MOSTRAR_FACTURAS:
                    nif = input("NIF: ");
                    try {
                        facturas = gestor.listaFacturas(nif);
                        for (String factura : facturas.keySet()) {
                            System.out.println(facturas.get(factura).toString());
                        }
                    } catch (NotExistingClientException e) {
                    } catch (NullListInvoicesException e) {
                    }

                    break;
                case FECHAS_ALTA_CLIENTE:
                    yearI = parseInt(input("Año inicio: "));
                    monthI = parseInt(input("Mes inicio: "));
                    monthI -= 1; //Porque queremos que el mes 1 sea Enero y en GregorianCalendar Enero es el mes 0.
                    dayI = parseInt(input("Dia inicio: "));
                    yearF = parseInt(input("Año final: "));
                    monthF = parseInt(input("Mes final: "));
                    monthF -= 1; //Porque queremos que el mes 1 sea Enero y en GregorianCalendar Enero es el mes 0.
                    dayF = parseInt(input("Dia final: "));
                    Collection<Cliente> listaClientes = null;
                    try {
                        listaClientes = gestor.mostrarListaClientesEntreFechas(new GregorianCalendar(yearI, monthI, dayI), new GregorianCalendar(yearF, monthF, dayF));
                        for(Cliente cliente : listaClientes){
                            System.out.println(cliente.toString());
                        }
                    } catch (IllegalPeriodException e) {
                    } catch (NullListClientsException e) {
                    }
                    break;
                case FECHAS_LLAMADAS:
                    nif = input("NIF: ");
                    yearI = parseInt(input("Año inicio: "));
                    monthI = parseInt(input("Mes inicio: "));
                    monthI -= 1; //Porque queremos que el mes 1 sea Enero y en GregorianCalendar Enero es el mes 0.
                    dayI = parseInt(input("Dia inicio: "));
                    yearF = parseInt(input("Año final: "));
                    monthF = parseInt(input("Mes final: "));
                    monthF -= 1; //Porque queremos que el mes 1 sea Enero y en GregorianCalendar Enero es el mes 0.
                    dayF = parseInt(input("Dia final: "));
                    Collection<Llamada> listaLlamadas = null;
                    try {
                        listaLlamadas = gestor.mostrarListaLlamadasEntreFechas(nif,new GregorianCalendar(yearI, monthI, dayI), new GregorianCalendar(yearF, monthF, dayF));
                        for(Llamada llamada : listaLlamadas){
                            System.out.println(llamada.toString());
                        }
                    } catch (NullListCallException e) {
                    } catch (IllegalPeriodException e) {
                    } catch (NotExistingClientException e) {
                    }

                    break;
                case FECHAS_FACTURAS:
                    nif = input("NIF: ");
                    yearI = parseInt(input("Año inicio: "));
                    monthI = parseInt(input("Mes inicio: "));
                    monthI -= 1; //Porque queremos que el mes 1 sea Enero y en GregorianCalendar Enero es el mes 0.
                    dayI = parseInt(input("Dia inicio: "));
                    yearF = parseInt(input("Año final: "));
                    monthF = parseInt(input("Mes final: "));
                    monthF -= 1; //Porque queremos que Enero sea el mes 1 y en GregorianCalendar Enero es el mes 0.
                    dayF = parseInt(input("Dia final: "));
                    Collection<Factura> listaFacturas = null;
                    try {
                        listaFacturas = gestor.mostrarListaFacturasEntreFechas(nif,new GregorianCalendar(yearI, monthI, dayI), new GregorianCalendar(yearF, monthF, dayF));
                        for(Factura factura: listaFacturas){
                            System.out.println(factura.toString());
                        }
                    } catch (IllegalPeriodException e) {
                    } catch (NotExistingClientException e) {
                    } catch (NullListInvoicesException e) {
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
