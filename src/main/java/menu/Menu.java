package menu;

import java.util.*;

import interfaz.controlador.ImplementacionControlador;
import interfaz.modelo.ImplementacionModelo;
import interfaz.vista.ImplementacionVista;

import static java.lang.Integer.parseInt;

public class Menu {

    private static String input(String text) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(text);
        return scanner.nextLine();
    }
    public static void main(String [] args) {
        ImplementacionControlador controlador = new ImplementacionControlador();
        ImplementacionVista vista = new ImplementacionVista();
        ImplementacionModelo modelo = new ImplementacionModelo();

        controlador.setModelo(modelo);
        vista.setModelo(modelo);
        vista.setControlador(controlador);
        modelo.cargarDatos();
        vista.creaGUI();
    }

}
