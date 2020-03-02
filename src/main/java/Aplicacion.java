import java.util.GregorianCalendar;
import java.util.Scanner;
import java.util.Set;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class Aplicacion {
    static Scanner scanner = new Scanner(System.in);
    static boolean terminar = false;
    static Gestor gestor = new Gestor();
    public static void main(String [] args) {

        menuPrincipal();
        scanner.close();

    }
    public static void menuPrincipal(){
        while (!terminar) {
            System.out.println("¡Inserta el numero de operacion!");
            System.out.println("1. Operaciones con cliente");
            System.out.println("2. Operaciones con llamada");
            System.out.println("3. Operaciones con factura");
            System.out.println("0. Salir \n");
            int operacion = parseInt(scanner.nextLine());
            switch(operacion){
                case 1:
                    operacionCliente();
                    break;

                case 2:
                    operacionLlamada();
                    break;

                case 3:
                    operacionFactura();
                    break;

                case 0:
                    terminar = true;
                    break;
            }
        }
    }
    public static void operacionCliente(){
        while (!terminar) {
            System.out.println("!Elija la operacion¡");
            System.out.println("1. Añadir cliente");
            System.out.println("2. Borrar cliente");
            System.out.println("3. Cambiar la tarifa de un cliente");
            System.out.println("4. Recuperar datos de un cliente");
            System.out.println("5. Listar clientes");
            System.out.println("0. Volver al menu principal \n");
            int operacion = parseInt(scanner.nextLine());
            String nif;
            double tarifa;
            switch(operacion){
                case 1:
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
                    int year = parseInt(scanner.nextLine());
                    System.out.println("Mes: ");
                    int month = parseInt(scanner.nextLine());
                    System.out.println("Dia: ");
                    int day = parseInt(scanner.nextLine());
                    Cliente c;
                    if(apellido.equals("")){
                        c = new Empresa(nombre,nif,new Direccion(cp,provincia,poblacion),email, new Tarifa(tarifa),new GregorianCalendar(year,month,day));
                    }
                    else{
                        c = new Particulares(nombre,apellido,nif,new Direccion(cp,provincia,poblacion),email, new Tarifa(tarifa),new GregorianCalendar(year,month,day));
                    }
                    gestor.addCliente(c);
                    break;

                case 2:
                    System.out.println("NIF: ");
                    nif = scanner.nextLine();
                    gestor.removeCliente(nif);
                    break;

                case 3:
                    System.out.println("NIF: ");
                    nif = scanner.nextLine();
                    System.out.println("Nueva tarifa: ");
                    tarifa = parseDouble(scanner.nextLine());
                    gestor.setTarifa(nif,new Tarifa(tarifa));
                    break;

                case 4:
                    System.out.println("NIF: ");
                    nif = scanner.nextLine();
                    System.out.println(gestor.listarDatos(nif) + "\n");
                    break;

                case 5:
                    Set<Cliente> clientes = gestor.listaClientes();
                    for (Cliente cliente : clientes){
                        System.out.println(cliente.getCliente());
                    }
                    System.out.println();
                    break;


                case 0:
                    menuPrincipal();
                    break;
            }
        }
    }
    public static void operacionLlamada() {
        while (!terminar) {
            System.out.println("!Elija la operacion¡");
            System.out.println("1. Añadir llamada");
            System.out.println("2. Listar llamadas");
            System.out.println("0. Volver al menu principal \n");
            int operacion = parseInt(scanner.nextLine());
            String nif;
            switch (operacion) {
                case 1:
                    System.out.println("NIF: ");
                    nif = scanner.nextLine();
                    System.out.println("Telefono: ");
                    String telefono = scanner.nextLine();
                    System.out.println("Año: ");
                    int year = parseInt(scanner.nextLine());
                    System.out.println("Mes: ");
                    int month = parseInt(scanner.nextLine());
                    System.out.println("Dia: ");
                    int day = parseInt(scanner.nextLine());
                    System.out.println("Hora: ");
                    int hour = parseInt(scanner.nextLine());
                    System.out.println("Minutos: ");
                    int minute = parseInt(scanner.nextLine());
                    System.out.println("Segundos: ");
                    int second = parseInt(scanner.nextLine());
                    System.out.println("Duracion llamada en segundos: ");
                    int duracion = parseInt(scanner.nextLine());
                    gestor.addLlamada(new Llamada(telefono, new GregorianCalendar(year,month,day,hour,minute,second),duracion),nif);
                    break;

                case 2:
                    System.out.println("NIF: ");
                    nif = scanner.nextLine();
                    Set<Llamada> llamadas = gestor.listaLlamadas(nif);
                    for(Llamada llamada : llamadas){
                        System.out.println(llamada.getLlamada());
                    }
                    break;

                case 0:
                    menuPrincipal();
                    break;
            }

        }
    }
    public static void operacionFactura(){
        while (!terminar) {
            System.out.println("!Elija la operacion¡");
            System.out.println("1. Emitir factura");
            System.out.println("2. Recuperar factura");
            System.out.println("3. Recuperar facturas de un cliente");
            System.out.println("0. Volver al menu principal \n");
            int operacion = parseInt(scanner.nextLine());
            String nif;
            String codigo;
            switch (operacion) {
                case 1:
                    System.out.println("NIF: ");
                    nif = scanner.nextLine();
                    System.out.println("Codigo: ");
                    codigo = scanner.nextLine();
                    System.out.println("Año inicio: ");
                    int yearI = parseInt(scanner.nextLine());
                    System.out.println("Mes inicio: ");
                    int monthI = parseInt(scanner.nextLine());
                    System.out.println("Dia inicio: ");
                    int dayI = parseInt(scanner.nextLine());
                    System.out.println("Año final: ");
                    int yearF = parseInt(scanner.nextLine());
                    System.out.println("Mes final: ");
                    int monthF = parseInt(scanner.nextLine());
                    System.out.println("Dia final: ");
                    int dayF = parseInt(scanner.nextLine());
                    gestor.emitirFactura(codigo,nif,new GregorianCalendar(yearI,monthI,dayI),new GregorianCalendar(yearF,monthF,dayF));
                    break;

                case 2:
                    System.out.println("NIF: ");
                    nif = scanner.nextLine();
                    System.out.println("Codigo: ");
                    codigo = scanner.nextLine();
                    System.out.println(gestor.facturaDatos(nif,codigo));
                    break;

                case 3:
                    System.out.println("NIF: ");
                    nif = scanner.nextLine();
                    Set<Factura> facturas = gestor.listaFacturas(nif);
                    for(Factura factura : facturas){
                        System.out.println(factura.getFactura());
                    }
                    break;

                case 0:
                    menuPrincipal();
                    break;
            }
        }
    }
}
