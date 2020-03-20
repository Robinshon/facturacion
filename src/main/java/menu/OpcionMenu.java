package menu;

public enum OpcionMenu {
    DAR_DE_ALTA_CLIENTE("Dar de alta nuevo cliente"),
    BORRAR_CLIENTE("Borrar cliente"),
    CAMBIAR_TARIFA("Cambiar tarifa"),
    DATOS_CLIENTE("Datos cliente"),
    LISTA_CLIENTE("Lista clientes"),
    NUEVA_LLAMADA("Nueva llamada"),
    LISTADO_LLAMADAS("Listado llamadas"),
    NUEVA_FACTURA("Nueva Factura"),
    DATOS_FACTURA("Datos factura"),
    MOSTRAR_FACTURA("Mostrar factura"),
    BETWEEN_ALTA_CLIENTE("Consultar  listado de clientes que fueron dados de alta entre dos fechas"),
    BETWEEN_LLAMADAS("Consultar listado de llamadas de un cliente que fueron realizadas entre dos fechas"),
    BETWEEN_FACTURAS("Consultar listado de facturas de un cliente emitidas entre dos fechas"),
    SALIR("Salir");

    private String descripcion;

    OpcionMenu(String descripcion) {
        this.descripcion = descripcion;
    }

    public static String menu() {
        StringBuffer sb = new StringBuffer();
        for(OpcionMenu opcion: OpcionMenu.values()) {
            sb.append(opcion.ordinal());
            sb.append(".- ");
            sb.append(opcion.descripcion);
            sb.append("\n");
        }
        return sb.toString();
    }

    public static OpcionMenu obtenerOpcion(int i) {
        return values()[i];
    }
}
