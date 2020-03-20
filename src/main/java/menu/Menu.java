package menu;

import excepciones.*;
import facturas.Factura;
import gestor.Gestor;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;
import clientes.*;
import llamadas.Llamada;
import tarifas.Tarifa;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class Menu {
    public static void main(String [] args) throws NotExistingClientException, ExistingClientException, NullListCallException, NotExistingInvoceException, NullListClientsException {
        Scanner scanner = new Scanner(System.in);
        Gestor gestor = new Gestor();
        OpcionMenu opcionMenu;
        do {
            System.out.println(OpcionMenu.menu());
            System.out.print("Elige una opción: ");
            byte opcion = scanner.nextByte();
            System.out.print("\n");
            opcionMenu = OpcionMenu.obtenerOpcion(opcion);
            String nif, codigo;
            double tarifa;
            int year, month, day, hour, minute, second, duracion, yearI, yearF, monthI, monthF, dayI, dayF;
            scanner.nextLine();
            switch (opcionMenu) {
                case DAR_DE_ALTA_CLIENTE:
                    System.out.println("Nombre: ");
                    String nombre = scanner.nextLine();
                    System.out.println("Apellido(si es una cadena vacia es una empresa): ");
                    String apellido = scanner.nextLine();
                    System.out.println("NIF: ");
                    nif = scanner.nextLine();
                    System.out.println("Codigo postal: ");
                    int cp = parseInt(scanner.nextLine());
                    System.out.println("Provincia: ");
                    String provincia = scanner.nextLine();
                    System.out.println("Poblacion: ");
                    String poblacion = scanner.nextLine();
                    System.out.println("Email: ");
                    String email = scanner.nextLine();
                    System.out.println("Tarifa: ");
                    tarifa = parseDouble(scanner.nextLine());
                    System.out.println("Año: ");
                    year = parseInt(scanner.nextLine());
                    System.out.println("Mes: ");
                    month = parseInt(scanner.nextLine());
                    System.out.println("Dia: ");
                    day = parseInt(scanner.nextLine());
                    Cliente c;
                    if (apellido.equals("")) {
                        c = new Empresa(nombre, nif, new Direccion(cp, provincia, poblacion), email, new Tarifa(tarifa), new GregorianCalendar(year, month, day));
                    } else {
                        c = new Particular(nombre, apellido, nif, new Direccion(cp, provincia, poblacion), email, new Tarifa(tarifa), new GregorianCalendar(year, month, day));
                    }
                    gestor.addCliente(c);
                    break;
                case BORRAR_CLIENTE:
                    System.out.println("NIF: ");
                    nif = scanner.nextLine();
                    gestor.removeCliente(nif);
                    break;
                case CAMBIAR_TARIFA:
                    System.out.println("NIF: ");
                    nif = scanner.nextLine();
                    System.out.println("Nueva tarifa: ");
                    tarifa = parseDouble(scanner.nextLine());
                    gestor.setTarifa(nif, new Tarifa(tarifa));
                    break;
                case DATOS_CLIENTE:
                    System.out.println("NIF: ");
                    nif = scanner.nextLine();
                    System.out.println(gestor.listarDatos(nif) + "\n");
                    break;
                case LISTA_CLIENTE:
                    HashMap<String, Cliente> clientes = gestor.listaClientes();
                    for (String cliente : clientes.keySet()) {
                        System.out.println(clientes.get(cliente).toString());
                    }
                    System.out.println();
                    break;
                case NUEVA_LLAMADA:
                    System.out.println("NIF: ");
                    nif = scanner.nextLine();
                    System.out.println("Telefono: ");
                    String telefono = scanner.nextLine();
                    System.out.println("Año: ");
                    year = parseInt(scanner.nextLine());
                    System.out.println("Mes: ");
                    month = parseInt(scanner.nextLine());
                    System.out.println("Dia: ");
                    day = parseInt(scanner.nextLine());
                    System.out.println("Hora: ");
                    hour = parseInt(scanner.nextLine());
                    System.out.println("Minutos: ");
                    minute = parseInt(scanner.nextLine());
                    System.out.println("Segundos: ");
                    second = parseInt(scanner.nextLine());
                    System.out.println("Duracion llamada en segundos: ");
                    duracion = parseInt(scanner.nextLine());
                    gestor.addLlamada(new Llamada(telefono, new GregorianCalendar(year, month, day, hour, minute, second), duracion), nif);
                    break;
                case LISTADO_LLAMADAS:
                    System.out.println("NIF: ");
                    nif = scanner.nextLine();
                    Set<Llamada> llamadas = gestor.listaLlamadas(nif);
                    for (Llamada llamada : llamadas) {
                        System.out.println(llamada.toString());
                    }
                    break;
                case NUEVA_FACTURA:
                    System.out.println("NIF: ");
                    nif = scanner.nextLine();
                    System.out.println("Codigo: ");
                    codigo = scanner.nextLine();
                    System.out.println("Año inicio: ");
                    yearI = parseInt(scanner.nextLine());
                    System.out.println("Mes inicio: ");
                    monthI = parseInt(scanner.nextLine());
                    System.out.println("Dia inicio: ");
                    dayI = parseInt(scanner.nextLine());
                    System.out.println("Año final: ");
                    yearF = parseInt(scanner.nextLine());
                    System.out.println("Mes final: ");
                    monthF = parseInt(scanner.nextLine());
                    System.out.println("Dia final: ");
                    dayF = parseInt(scanner.nextLine());
                    gestor.emitirFactura(codigo, nif, new GregorianCalendar(yearI, monthI, dayI), new GregorianCalendar(yearF, monthF, dayF));
                    break;
                case DATOS_FACTURA:
                    System.out.println("NIF: ");
                    nif = scanner.nextLine();
                    System.out.println("Codigo: ");
                    codigo = scanner.nextLine();
                    System.out.println(gestor.facturaDatos(nif, codigo));
                    break;
                case MOSTRAR_FACTURA:
                    System.out.println("NIF: ");
                    nif = scanner.nextLine();
                    HashMap<String, Factura> facturas = gestor.listaFacturas(nif);
                    for (String factura : facturas.keySet()) {
                        System.out.println(facturas.get(factura).toString());
                    }
                    break;
                case BETWEEN_ALTA_CLIENTE:

                    break;
                case BETWEEN_LLAMADAS:

                    break;
                case BETWEEN_FACTURAS:

                    break;
                case SALIR:
                    scanner.close();
                    break;
            }
        } while (opcionMenu != OpcionMenu.SALIR);
    }
}
