package interfaz.vista;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.util.Calendar;
import java.util.Collection;
import java.util.Locale;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import clientes.Cliente;
import clientes.Direccion;
import excepciones.*;
import facturas.Factura;
import interfaz.controlador.Controlador;
import interfaz.modelo.Modelo;
import llamadas.Llamada;
import tarifas.Tarifa;
import tarifas.TarifaFactory;

public class ImplementacionVista implements Vista {
    private Controlador controlador;
    private Modelo modelo;
    private JFrame tituloVentana = null;
    Container contenedor = null;
    JPanel panelCentral = null;
    JPanel panelAbajo = null;
    JPanel panelFinal = new JPanel();
    JTextField nif = null;
    JTextField nombre = null;
    JTextField apellido = null;
    JTextField codPos = null;
    JTextField pob = null;
    JTextField prov = null;
    JTextField email = null;
    JTextField day = null;
    JTextField month = null;
    JTextField year = null;
    JTextField tarifa = null;
    JTextField day2 = null;
    JTextField month2 = null;
    JTextField year2 = null;
    JTextField telf = null;
    JTextField codFac = null;
    JTextField hora = null;
    JTextField minuto = null;
    JTextField dur = null;
    JButton submit = null;
    int tipo;
    JTextField tipoTar;

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }

    public void setControlador(Controlador controlador) {
        this.controlador = controlador;
    }

    public ImplementacionVista() {
    }

    // GUI PRINCIPAL
    private void GUI() {
        tituloVentana = new JFrame("Empresa de Telofonía");
        contenedor = tituloVentana.getContentPane();
        EscuchadorPrincipal escuchador = new EscuchadorPrincipal();
        JPanel panelArriba = new JPanel();
        JButton boton = new JButton("Gestión clientes");
        boton.addActionListener(escuchador);
        panelArriba.add(boton);
        boton = new JButton("Gestión llamadas");
        boton.addActionListener(escuchador);
        panelArriba.add(boton);
        boton = new JButton("Gestión facturas");
        boton.addActionListener(escuchador);
        panelArriba.add(boton);
        contenedor.add(panelArriba, BorderLayout.NORTH);
        panelCentral = new JPanel();
        panelCentral.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        panelArriba.setPreferredSize(new Dimension(1080, 40));
        contenedor.add(panelCentral, BorderLayout.CENTER);
        tituloVentana.setSize(1150, 500);
        tituloVentana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tituloVentana.setVisible(true);
    }

    // GUI MENU
    private void GUICliente() {
        JPanel menu = new JPanel();
        panelCentral.removeAll();
        EscuchadorCliente escuchador = new EscuchadorCliente();
        menu.setLayout(new BoxLayout(menu, BoxLayout.LINE_AXIS));
        JButton boton = new JButton("Nuevo cliente");
        boton.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        boton.addActionListener(escuchador);
        menu.add(boton);
        boton = new JButton("Borrar cliente");
        boton.addActionListener(escuchador);
        menu.add(boton);
        boton = new JButton("Cambiar tarifa");
        boton.addActionListener(escuchador);
        menu.add(boton);
        boton = new JButton("Recuperar datos cliente");
        boton.addActionListener(escuchador);
        menu.add(boton);
        boton = new JButton("Recuperar listado clientes");
        boton.addActionListener(escuchador);
        menu.add(boton);
        boton = new JButton("Mostrar listado clientes entre fechas");
        boton.addActionListener(escuchador);
        menu.add(boton);


        JPanel panelEspacio = new JPanel();
        JLabel espacioLabel = new JLabel(
                "                                                                                                                                                                                                                "
                        + "                                                                                                                                                                                                                                                                                 ");
        panelEspacio.add(espacioLabel);

        panelCentral.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        panelCentral.add(menu, BorderLayout.NORTH);
        panelCentral.add(panelEspacio);
        panelAbajo = new JPanel();
        panelAbajo.setPreferredSize(new Dimension(1200, 600));
        panelCentral.add(panelAbajo, BorderLayout.CENTER);
        panelCentral.updateUI();
    }

    private void GUILlamada() {
        JPanel menu = new JPanel();
        panelCentral.removeAll();
        EscuchadorLlamada escuchador = new EscuchadorLlamada();
        menu.setLayout(new BoxLayout(menu, BoxLayout.LINE_AXIS));
        JButton boton = new JButton("Dar alta llamada");
        boton.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        boton.addActionListener(escuchador);
        menu.add(boton);
        boton = new JButton("Listar llamadas cliente");
        boton.addActionListener(escuchador);
        menu.add(boton);
        boton = new JButton("Listar llamadas cliente entre fechas");

        boton.addActionListener(escuchador);
        menu.add(boton);

        JPanel panelEspacio = new JPanel();
        JLabel espacioLabel = new JLabel(
                "                                                                                                                                                                                                                "
                        + "                                                                                                                                                                                                                                                                                 ");
        panelEspacio.add(espacioLabel);

        panelCentral.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        panelCentral.add(menu, BorderLayout.NORTH);
        panelCentral.add(panelEspacio);
        panelAbajo = new JPanel();
        panelAbajo.setPreferredSize(new Dimension(1200, 600));
        panelCentral.add(panelAbajo, BorderLayout.CENTER);
        panelCentral.updateUI();
    }

    private void GUIFactura() {
        JPanel menu = new JPanel();
        panelCentral.removeAll();
        menu.setLayout(new BoxLayout(menu, BoxLayout.LINE_AXIS));
        EscuchadorFactura escuchador = new EscuchadorFactura();
        JButton boton = new JButton("Emitir factura");
        boton.addActionListener(escuchador);
        menu.add(boton);
        boton = new JButton("Recuperar datos factura");
        boton.addActionListener(escuchador);
        menu.add(boton);
        boton = new JButton("Recuperar todas las facturas");
        boton.addActionListener(escuchador);
        menu.add(boton);
        boton = new JButton("Recuperar listado facturas entre fechas");
        boton.addActionListener(escuchador);
        menu.add(boton);

        JPanel panelEspacio = new JPanel();
        JLabel espacioLabel = new JLabel(
                "                                                                                                                                                                                                                "
                        + "                                                                                                                                                                                                                                                                                 ");
        panelEspacio.add(espacioLabel);

        panelCentral.add(menu, BorderLayout.NORTH);
        panelCentral.add(panelEspacio);
        panelAbajo = new JPanel();
        panelAbajo.setPreferredSize(new Dimension(1200, 600));
        panelCentral.add(panelAbajo, BorderLayout.CENTER);
        panelCentral.updateUI();
    }

    // GUI CLIENTE
    private void GUIDarAlta() {
        panelAbajo.removeAll();

        JPanel panelEmpresa = new JPanel();
        tipo = 1;
        JRadioButton si = new JRadioButton("si");
        JRadioButton no = new JRadioButton("no");
        si.setActionCommand("si");
        si.addItemListener(e -> {
            switch (e.getStateChange()) {
                case ItemEvent.SELECTED:
                    tipo = 0;
                    break;
                case ItemEvent.DESELECTED:
                    tipo = 1;
                    break;
            }
        });
        panelEmpresa.add(new JLabel("¿Eres una empresa?"));
        panelEmpresa.add(si);
        panelEmpresa.add(no);
        ButtonGroup grupo = new ButtonGroup();
        grupo.add(si);
        grupo.add(no);

        panelAbajo.add(panelEmpresa);

        JPanel panelNombre = new JPanel();
        nombre = new JTextField(20);
        JLabel nombreLabel = new JLabel("Nombre: ");
        panelNombre.add(nombreLabel);
        panelNombre.add(nombre);

        panelAbajo.add(panelNombre);

        JPanel panelApellido = new JPanel();
        apellido = new JTextField(20);
        JLabel apellidoLabel = new JLabel("Apellido (Sólo si eres un particular): ");
        panelApellido.add(apellidoLabel);
        panelApellido.add(apellido);

        panelAbajo.add(panelApellido);

        JPanel panelNif = new JPanel();
        nif = new JTextField(9);
        JLabel nifLabel = new JLabel("NIF: ");
        panelNif.add(nifLabel);
        panelNif.add(nif);

        panelAbajo.add(panelNif);

        JPanel panelDireccion = new JPanel();
        codPos = new JTextField(5);
        prov = new JTextField(20);
        pob = new JTextField(30);
        JLabel direccionLabel = new JLabel("Dirección: ");
        JLabel codPosLabel = new JLabel("Código Postal: ");
        JLabel provLabel = new JLabel("Provincia: ");
        JLabel pobLabel = new JLabel("Población: ");

        panelDireccion.add(direccionLabel);
        panelDireccion.add(codPosLabel);
        panelDireccion.add(codPos);
        panelDireccion.add(provLabel);
        panelDireccion.add(prov);
        panelDireccion.add(pobLabel);
        panelDireccion.add(pob);

        panelAbajo.add(panelDireccion);

        JPanel panelCorreo = new JPanel();
        email = new JTextField(30);
        JLabel correoLabel = new JLabel("Correo: ");
        panelCorreo.add(correoLabel);
        panelCorreo.add(email);

        panelAbajo.add(panelCorreo);

        JPanel panelFecha = new JPanel();
        year = new JTextField(4);
        month = new JTextField(2);
        day = new JTextField(2);
        JLabel fechaLabel = new JLabel("Fecha de alta: ");
        JLabel anoLabel = new JLabel("Año: ");
        JLabel mesLabel = new JLabel("Mes(numérico): ");
        JLabel diaLabel = new JLabel("Día: ");
        panelFecha.add(fechaLabel);
        panelFecha.add(anoLabel);
        panelFecha.add(year);
        panelFecha.add(mesLabel);
        panelFecha.add(month);
        panelFecha.add(diaLabel);
        panelFecha.add(day);

        panelAbajo.add(panelFecha);

        JPanel panelTarifa = new JPanel();
        tarifa = new JTextField(5);
        JLabel tarifaLabel = new JLabel("Importe tarifa: ");
        panelTarifa.add(tarifaLabel);
        panelTarifa.add(tarifa);

        panelAbajo.add(panelTarifa);

        JPanel panelEspacio = new JPanel();
        JLabel espacioLabel = new JLabel(
                "                                                                                                                                                                                                                "
                        + "                                                                                                                                                                                                                                                                                 ");
        panelEspacio.add(espacioLabel);

        panelAbajo.add(panelEspacio);

        EscuchadorDarAlta escuchadorDarAlta = new EscuchadorDarAlta();
        JPanel panelSubmit = new JPanel();
        submit = new JButton("Enviar");
        submit.addActionListener(escuchadorDarAlta);
        panelSubmit.add(submit);

        panelAbajo.add(submit);

        JPanel panelEspacio2 = new JPanel();
        JLabel espacioLabel2 = new JLabel(
                "                                                                                                                                                                                                                "
                        + "                                                                                                                                                                                                                                                                                 ");
        panelEspacio2.add(espacioLabel2);

        panelAbajo.add(panelEspacio2);

        panelEspacio.updateUI();
        panelSubmit.updateUI();
        panelTarifa.updateUI();
        panelFecha.updateUI();
        panelCorreo.updateUI();
        panelDireccion.updateUI();
        panelApellido.updateUI();
        panelNombre.updateUI();
        panelNif.updateUI();
        panelEmpresa.updateUI();
        panelAbajo.updateUI();
    }

    private void GUIBorrarCliente() {
        panelAbajo.removeAll();

        JPanel panelNif = new JPanel();
        nif = new JTextField(9);
        JLabel nifLabel = new JLabel("NIF: ");
        panelNif.add(nifLabel);
        panelNif.add(nif);

        panelAbajo.add(panelNif);

        JPanel panelEspacio = new JPanel();
        JLabel espacioLabel = new JLabel(
                "                                                                                                                                                                                                                "
                        + "                                                                                                                                                                                                                                                                                 ");
        panelEspacio.add(espacioLabel);

        panelAbajo.add(panelEspacio);

        JPanel panelSubmit = new JPanel();
        submit = new JButton("Enviar");
        EscuchadorBorrarCliente escuchadorBorrarCliente = new EscuchadorBorrarCliente();
        submit.addActionListener(escuchadorBorrarCliente);
        panelSubmit.add(submit);

        panelAbajo.add(submit);

        JPanel panelEspacio2 = new JPanel();
        JLabel espacioLabel2 = new JLabel(
                "                                                                                                                                                                                                                "
                        + "                                                                                                                                                                                                                                                                                 ");
        panelEspacio2.add(espacioLabel2);

        panelAbajo.add(panelEspacio2);

        panelEspacio.updateUI();
        panelSubmit.updateUI();
        panelNif.updateUI();
        panelAbajo.updateUI();

    }

    private void GUICambiarTarifa() {
        panelAbajo.removeAll();

        JPanel panelNif = new JPanel();
        nif = new JTextField(9);
        JLabel nifLabel = new JLabel("NIF: ");
        panelNif.add(nifLabel);
        panelNif.add(nif);

        panelAbajo.add(panelNif);

        JPanel panelTipoTar = new JPanel();
        JLabel tipoTarLabel = new JLabel("Tarifa	(0 Básica) (1 Domingo) (2 Tardes): ");
        tipoTar = new JTextField(5);
        panelTipoTar.add(tipoTarLabel);
        panelTipoTar.add(tipoTar);

        panelAbajo.add(panelTipoTar);

        JPanel panelTarifa = new JPanel();
        JLabel tarifaLabel = new JLabel("Importe: ");
        tarifa = new JTextField(5);
        panelTarifa.add(tarifaLabel);
        panelTarifa.add(tarifa);

        panelAbajo.add(panelTarifa);

        JPanel panelEspacio = new JPanel();
        JLabel espacioLabel = new JLabel(
                "                                                                                                                                                                                                                "
                        + "                                                                                                                                                                                                                                                                                 ");
        panelEspacio.add(espacioLabel);

        panelAbajo.add(panelEspacio);

        JPanel panelSubmit = new JPanel();
        submit = new JButton("Enviar");
        EscuchadorCambiarTarifa escuchadorCambiarTarifa = new EscuchadorCambiarTarifa();
        submit.addActionListener(escuchadorCambiarTarifa);
        panelSubmit.add(submit);

        panelAbajo.add(submit);

        panelTipoTar.updateUI();
        panelEspacio.updateUI();
        panelSubmit.updateUI();
        panelTarifa.updateUI();
        panelNif.updateUI();
        panelAbajo.updateUI();
    }

    private void GUIRecuperarCliente() {
        panelAbajo.removeAll();

        JPanel panelNif = new JPanel();
        nif = new JTextField(9);
        JLabel nifLabel = new JLabel("NIF: ");
        panelNif.add(nifLabel);
        panelNif.add(nif);

        panelAbajo.add(panelNif);

        JPanel panelEspacio = new JPanel();
        JLabel espacioLabel = new JLabel(
                "                                                                                                                                                                                                                "
                        + "                                                                                                                                                                                                                                                                                 ");
        panelEspacio.add(espacioLabel);

        panelAbajo.add(panelEspacio);

        JPanel panelSubmit = new JPanel();
        submit = new JButton("Enviar");
        EscuchadorRecuperarCliente escuchadorRecuperarCliente = new EscuchadorRecuperarCliente();
        submit.addActionListener(escuchadorRecuperarCliente);
        panelSubmit.add(submit);

        panelAbajo.add(submit);

        JPanel panelEspacio2 = new JPanel();
        JLabel espacioLabel2 = new JLabel(
                "                                                                                                                                                                                                                "
                        + "                                                                                                                                                                                                                                                                                 ");
        panelEspacio2.add(espacioLabel2);

        panelAbajo.add(panelEspacio2);

        panelSubmit.updateUI();
        panelEspacio.updateUI();
        panelNif.updateUI();
        panelAbajo.updateUI();
    }

    private void GUIRecuperarListado() {
        panelAbajo.removeAll();

        JPanel panelEspacio = new JPanel();
        JLabel espacioLabel = new JLabel(
                "                                                                                                                                                                                                                "
                        + "                                                                                                                                                                                                                                                                                 ");
        panelEspacio.add(espacioLabel);

        panelAbajo.add(panelEspacio);

        JPanel panelSubmit = new JPanel();
        submit = new JButton("Recuperar");
        EscuchadorRecuperarTodos escuchadorTodos = new EscuchadorRecuperarTodos();
        submit.addActionListener(escuchadorTodos);
        panelSubmit.add(submit);

        panelAbajo.add(submit);

        JPanel panelEspacio2 = new JPanel();
        JLabel espacioLabel2 = new JLabel(
                "                                                                                                                                                                                                                "
                        + "                                                                                                                                                                                                                                                                                 ");
        panelEspacio2.add(espacioLabel2);

        panelAbajo.add(panelEspacio2);

        panelEspacio.updateUI();
        panelSubmit.updateUI();
        panelAbajo.updateUI();
    }

    private void GUIRecuperarListadoEntreFechas() {
        panelAbajo.removeAll();

        JPanel panelFecha = new JPanel();
        year = new JTextField(4);
        month = new JTextField(2);
        day = new JTextField(2);
        JLabel fechaLabel = new JLabel("Fecha de inicio: ");
        JLabel anoLabel = new JLabel("Año: ");
        JLabel mesLabel = new JLabel("Mes(numérico): ");
        JLabel diaLabel = new JLabel("Día: ");
        panelFecha.add(fechaLabel);
        panelFecha.add(anoLabel);
        panelFecha.add(year);
        panelFecha.add(mesLabel);
        panelFecha.add(month);
        panelFecha.add(diaLabel);
        panelFecha.add(day);

        panelAbajo.add(panelFecha);

        JPanel panelFecha2 = new JPanel();
        year2 = new JTextField(4);
        month2 = new JTextField(2);
        day2 = new JTextField(2);
        JLabel fechaLabel2 = new JLabel("Fecha de fin: ");
        JLabel anoLabel2 = new JLabel("Año: ");
        JLabel mesLabel2 = new JLabel("Mes(numérico): ");
        JLabel diaLabel2 = new JLabel("Día: ");
        panelFecha2.add(fechaLabel2);
        panelFecha2.add(anoLabel2);
        panelFecha2.add(year2);
        panelFecha2.add(mesLabel2);
        panelFecha2.add(month2);
        panelFecha2.add(diaLabel2);
        panelFecha2.add(day2);

        panelAbajo.add(panelFecha2);

        JPanel panelEspacio = new JPanel();
        JLabel espacioLabel = new JLabel(
                "                                                                                                                                                                                                                "
                        + "                                                                                                                                                                                                                                                                                 ");
        panelEspacio.add(espacioLabel);

        panelAbajo.add(panelEspacio);

        JPanel panelSubmit = new JPanel();
        submit = new JButton("Enviar");
        EscuchadorRecuperarTodosEntreFechas escuchadorFechas = new EscuchadorRecuperarTodosEntreFechas();
        submit.addActionListener(escuchadorFechas);
        panelSubmit.add(submit);

        panelAbajo.add(submit);

        JPanel panelEspacio2 = new JPanel();
        JLabel espacioLabel2 = new JLabel(
                "                                                                                                                                                                                                                "
                        + "                                                                                                                                                                                                                                                                                 ");
        panelEspacio2.add(espacioLabel2);

        panelAbajo.add(panelEspacio2);

        panelEspacio.updateUI();
        panelSubmit.updateUI();
        panelFecha.updateUI();
        panelFecha2.updateUI();
        panelAbajo.updateUI();
    }

    // GUI LLAMADAS

    private void GUIDarAltaLlamada() {
        panelAbajo.removeAll();

        JPanel panelNif = new JPanel();
        nif = new JTextField(9);
        JLabel nifLabel = new JLabel("NIF: ");
        panelNif.add(nifLabel);
        panelNif.add(nif);

        panelAbajo.add(panelNif);

        JPanel panelTelf = new JPanel();
        telf = new JTextField(12);
        JLabel telfLabel = new JLabel("Teléfono: ");
        panelTelf.add(telfLabel);
        panelTelf.add(telf);

        panelAbajo.add(panelTelf);

        JPanel panelFecha = new JPanel();
        year = new JTextField(4);
        month = new JTextField(2);
        day = new JTextField(2);
        hora = new JTextField(6);
        minuto = new JTextField(6);
        JLabel fechaLabel = new JLabel("Fecha de alta: ");
        JLabel anoLabel = new JLabel("Año: ");
        JLabel mesLabel = new JLabel("Mes(numérico): ");
        JLabel diaLabel = new JLabel("Día: ");
        JLabel horaLabel = new JLabel("Hora: ");
        JLabel minutoLabel = new JLabel("Minuto: ");
        panelFecha.add(fechaLabel);
        panelFecha.add(anoLabel);
        panelFecha.add(year);
        panelFecha.add(mesLabel);
        panelFecha.add(month);
        panelFecha.add(diaLabel);
        panelFecha.add(day);
        panelFecha.add(horaLabel);
        panelFecha.add(hora);
        panelFecha.add(minutoLabel);
        panelFecha.add(minuto);

        panelAbajo.add(panelFecha);

        JPanel panelDur = new JPanel();
        dur = new JTextField(5);
        JLabel durLabel = new JLabel("Duración: ");
        panelDur.add(durLabel);
        panelDur.add(dur);

        panelAbajo.add(panelDur);

        JPanel panelEspacio = new JPanel();
        JLabel espacioLabel = new JLabel(
                "                                                                                                                                                                                                                "
                        + "                                                                                                                                                                                                                                                                                 ");
        panelEspacio.add(espacioLabel);

        panelAbajo.add(panelEspacio);

        JPanel panelSubmit = new JPanel();
        submit = new JButton("Enviar");
        EscuchadorDarAltaLlamada escuchadorAltaLlamada = new EscuchadorDarAltaLlamada();
        submit.addActionListener(escuchadorAltaLlamada);
        panelSubmit.add(submit);

        panelAbajo.add(submit);

        JPanel panelEspacio2 = new JPanel();
        JLabel espacioLabel2 = new JLabel(
                "                                                                                                                                                                                                                "
                        + "                                                                                                                                                                                                                                                                                 ");
        panelEspacio2.add(espacioLabel2);

        panelAbajo.add(panelEspacio2);

        panelEspacio.updateUI();
        panelSubmit.updateUI();
        panelDur.updateUI();
        panelFecha.updateUI();
        panelTelf.updateUI();
        panelNif.updateUI();
        panelAbajo.updateUI();

    }

    private void GUIListarLlamadas() {
        panelAbajo.removeAll();

        JPanel panelNif = new JPanel();
        nif = new JTextField(9);
        JLabel nifLabel = new JLabel("NIF: ");
        panelNif.add(nifLabel);
        panelNif.add(nif);

        panelAbajo.add(panelNif);

        JPanel panelEspacio = new JPanel();
        JLabel espacioLabel = new JLabel(
                "                                                                                                                                                                                                                "
                        + "                                                                                                                                                                                                                                                                                 ");
        panelEspacio.add(espacioLabel);

        panelAbajo.add(panelEspacio);

        JPanel panelSubmit = new JPanel();
        submit = new JButton("Enviar");
        EscuchadorRecuperarTodasLlamadas escuchadorRecuperarTodasLlamadas = new EscuchadorRecuperarTodasLlamadas();
        submit.addActionListener(escuchadorRecuperarTodasLlamadas);
        panelSubmit.add(submit);

        panelAbajo.add(submit);

        JPanel panelEspacio2 = new JPanel();
        JLabel espacioLabel2 = new JLabel(
                "                                                                                                                                                                                                                "
                        + "                                                                                                                                                                                                                                                                                 ");
        panelEspacio2.add(espacioLabel2);

        panelAbajo.add(panelEspacio2);

        panelEspacio.updateUI();
        panelSubmit.updateUI();
        panelNif.updateUI();
        panelAbajo.updateUI();
    }

    private void GUIListarLlamadasEntreFechas() {
        panelAbajo.removeAll();

        JPanel panelNif = new JPanel();
        nif = new JTextField(9);
        JLabel nifLabel = new JLabel("NIF: ");
        panelNif.add(nifLabel);
        panelNif.add(nif);

        panelAbajo.add(panelNif);

        JPanel panelFecha = new JPanel();
        year = new JTextField(4);
        month = new JTextField(2);
        day = new JTextField(2);
        JLabel fechaLabel = new JLabel("Fecha de inicio: ");
        JLabel anoLabel = new JLabel("Año: ");
        JLabel mesLabel = new JLabel("Mes(numérico): ");
        JLabel diaLabel = new JLabel("Día: ");
        panelFecha.add(fechaLabel);
        panelFecha.add(anoLabel);
        panelFecha.add(year);
        panelFecha.add(mesLabel);
        panelFecha.add(month);
        panelFecha.add(diaLabel);
        panelFecha.add(day);

        panelAbajo.add(panelFecha);

        JPanel panelFecha2 = new JPanel();
        year2 = new JTextField(4);
        month2 = new JTextField(2);
        day2 = new JTextField(2);
        JLabel fechaLabel2 = new JLabel("Fecha de fin: ");
        JLabel anoLabel2 = new JLabel("Año: ");
        JLabel mesLabel2 = new JLabel("Mes(numérico): ");
        JLabel diaLabel2 = new JLabel("Día: ");
        panelFecha2.add(fechaLabel2);
        panelFecha2.add(anoLabel2);
        panelFecha2.add(year2);
        panelFecha2.add(mesLabel2);
        panelFecha2.add(month2);
        panelFecha2.add(diaLabel2);
        panelFecha2.add(day2);

        panelAbajo.add(panelFecha2);

        JPanel panelEspacio = new JPanel();
        JLabel espacioLabel = new JLabel(
                "                                                                                                                                                                                                                "
                        + "                                                                                                                                                                                                                                                                                 ");
        panelEspacio.add(espacioLabel);

        panelAbajo.add(panelEspacio);

        JPanel panelSubmit = new JPanel();
        submit = new JButton("Enviar");
        EscuchadorRecuperarTodasLlamadasEntreFechas escRTL = new EscuchadorRecuperarTodasLlamadasEntreFechas();
        submit.addActionListener(escRTL);
        panelSubmit.add(submit);

        panelAbajo.add(submit);

        JPanel panelEspacio2 = new JPanel();
        JLabel espacioLabel2 = new JLabel(
                "                                                                                                                                                                                                                "
                        + "                                                                                                                                                                                                                                                                                 ");
        panelEspacio2.add(espacioLabel2);

        panelAbajo.add(panelEspacio2);

        panelNif.updateUI();
        panelEspacio.updateUI();
        panelSubmit.updateUI();
        panelFecha.updateUI();
        panelFecha2.updateUI();
        panelAbajo.updateUI();
    }

    // GUI FACTURAS

    private void GUIEmitirFactura() {
        panelAbajo.removeAll();
        JPanel panelcodFac = new JPanel();
        codFac = new JTextField(20);
        JLabel codFacLabel = new JLabel("Código de factura: ");
        panelcodFac.add(codFacLabel);
        panelcodFac.add(codFac);

        panelAbajo.add(panelcodFac);

        JPanel panelNif = new JPanel();
        nif = new JTextField(9);
        JLabel nifLabel = new JLabel("NIF: ");
        panelNif.add(nifLabel);
        panelNif.add(nif);

        panelAbajo.add(panelNif);

        JPanel panelFecha = new JPanel();
        year = new JTextField(4);
        month = new JTextField(2);
        day = new JTextField(2);
        year2 = new JTextField(4);
        month2 = new JTextField(2);
        day2 = new JTextField(2);
        JLabel fechaLabel = new JLabel("Fecha Inicio: ");
        JLabel anoLabel = new JLabel("Año: ");
        JLabel mesLabel = new JLabel("Mes(numérico): ");
        JLabel diaLabel = new JLabel("Día: ");
        JLabel fecha2Label = new JLabel("Fecha Final: ");
        JLabel ano2Label = new JLabel("Año: ");
        JLabel mes2Label = new JLabel("Mes(numérico): ");
        JLabel dia2Label = new JLabel("Día: ");

        panelFecha.add(fechaLabel);
        panelFecha.add(anoLabel);
        panelFecha.add(year);
        panelFecha.add(mesLabel);
        panelFecha.add(month);
        panelFecha.add(diaLabel);
        panelFecha.add(day);
        panelFecha.add(fecha2Label);
        panelFecha.add(ano2Label);
        panelFecha.add(year2);
        panelFecha.add(mes2Label);
        panelFecha.add(month2);
        panelFecha.add(dia2Label);
        panelFecha.add(day2);


        panelAbajo.add(panelFecha);

        JPanel panelEspacio = new JPanel();
        JLabel espacioLabel = new JLabel(
                "                                                                                                                                                                                                                "
                        + "                                                                                                                                                                                                                                                                                 ");
        panelEspacio.add(espacioLabel);

        panelAbajo.add(panelEspacio);

        JPanel panelSubmit = new JPanel();
        submit = new JButton("Enviar");
        EscuchadorEmitirFactura escEF = new EscuchadorEmitirFactura();
        submit.addActionListener(escEF);
        panelSubmit.add(submit);

        panelAbajo.add(submit);

        JPanel panelEspacio2 = new JPanel();
        JLabel espacioLabel2 = new JLabel(
                "                                                                                                                                                                                                                "
                        + "                                                                                                                                                                                                                                                                                 ");
        panelEspacio2.add(espacioLabel2);

        panelAbajo.add(panelEspacio2);

        panelEspacio.updateUI();
        panelSubmit.updateUI();
        panelFecha.updateUI();
        panelNif.updateUI();
        panelAbajo.updateUI();

    }

    private void GUIRecuperarDatosFactura() {
        panelAbajo.removeAll();

        JPanel panelNif = new JPanel();
        nif = new JTextField(9);
        JLabel nifLabel = new JLabel("NIF: ");
        panelNif.add(nifLabel);
        panelNif.add(nif);

        panelAbajo.add(panelNif);

        JPanel panelcodFac = new JPanel();
        codFac = new JTextField(20);
        JLabel codFacLabel = new JLabel("Código de factura: ");
        panelcodFac.add(codFacLabel);
        panelcodFac.add(codFac);

        panelAbajo.add(panelcodFac);

        JPanel panelEspacio = new JPanel();
        JLabel espacioLabel = new JLabel(
                "                                                                                                                                                                                                                "
                        + "                                                                                                                                                                                                                                                                                 ");
        panelEspacio.add(espacioLabel);

        panelAbajo.add(panelEspacio);

        JPanel panelSubmit = new JPanel();
        submit = new JButton("Enviar");
        EscuchadorRecuperarFacturas escRF = new EscuchadorRecuperarFacturas();
        submit.addActionListener(escRF);
        panelSubmit.add(submit);

        panelAbajo.add(submit);

        JPanel panelEspacio2 = new JPanel();
        JLabel espacioLabel2 = new JLabel(
                "                                                                                                                                                                                                                "
                        + "                                                                                                                                                                                                                                                                                 ");
        panelEspacio2.add(espacioLabel2);

        panelAbajo.add(panelEspacio2);

        panelSubmit.updateUI();
        panelEspacio.updateUI();
        panelcodFac.updateUI();
        panelAbajo.updateUI();
    }

    private void GUIRecuperarTodasFacturas() {
        panelAbajo.removeAll();

        JPanel panelNif = new JPanel();
        nif = new JTextField(9);
        JLabel nifLabel = new JLabel("NIF: ");
        panelNif.add(nifLabel);
        panelNif.add(nif);

        panelAbajo.add(panelNif);

        JPanel panelEspacio = new JPanel();
        JLabel espacioLabel = new JLabel(
                "                                                                                                                                                                                                                "
                        + "                                                                                                                                                                                                                                                                                 ");
        panelEspacio.add(espacioLabel);

        panelAbajo.add(panelEspacio);

        JPanel panelSubmit = new JPanel();
        submit = new JButton("Enviar");
        EscuchadorRecuperarTodasFacturas escRTF = new EscuchadorRecuperarTodasFacturas();
        submit.addActionListener(escRTF);
        panelSubmit.add(submit);

        panelAbajo.add(submit);

        JPanel panelEspacio2 = new JPanel();
        JLabel espacioLabel2 = new JLabel(
                "                                                                                                                                                                                                                "
                        + "                                                                                                                                                                                                                                                                                 ");
        panelEspacio2.add(espacioLabel2);

        panelAbajo.add(panelEspacio2);

        panelSubmit.updateUI();
        panelEspacio.updateUI();
        panelNif.updateUI();
        panelAbajo.updateUI();
    }

    private void GUIRecuperarFacturasEntreFechas() {
        panelAbajo.removeAll();

        JPanel panelNif = new JPanel();
        nif = new JTextField(9);
        JLabel nifLabel = new JLabel("NIF: ");
        panelNif.add(nifLabel);
        panelNif.add(nif);

        panelAbajo.add(panelNif);

        JPanel panelFecha = new JPanel();
        year = new JTextField(4);
        month = new JTextField(2);
        day = new JTextField(2);
        JLabel fechaLabel = new JLabel("Fecha de inicio: ");
        JLabel anoLabel = new JLabel("Año: ");
        JLabel mesLabel = new JLabel("Mes(numérico): ");
        JLabel diaLabel = new JLabel("Día: ");
        panelFecha.add(fechaLabel);
        panelFecha.add(anoLabel);
        panelFecha.add(year);
        panelFecha.add(mesLabel);
        panelFecha.add(month);
        panelFecha.add(diaLabel);
        panelFecha.add(day);

        panelAbajo.add(panelFecha);

        JPanel panelFecha2 = new JPanel();
        year2 = new JTextField(4);
        month2 = new JTextField(2);
        day2 = new JTextField(2);
        JLabel fechaLabel2 = new JLabel("Fecha de fin: ");
        JLabel anoLabel2 = new JLabel("Año: ");
        JLabel mesLabel2 = new JLabel("Mes(numérico): ");
        JLabel diaLabel2 = new JLabel("Día: ");
        panelFecha2.add(fechaLabel2);
        panelFecha2.add(anoLabel2);
        panelFecha2.add(year2);
        panelFecha2.add(mesLabel2);
        panelFecha2.add(month2);
        panelFecha2.add(diaLabel2);
        panelFecha2.add(day2);

        panelAbajo.add(panelFecha2);

        JPanel panelEspacio = new JPanel();
        JLabel espacioLabel = new JLabel(
                "                                                                                                                                                                                                                "
                        + "                                                                                                                                                                                                                                                                                 ");
        panelEspacio.add(espacioLabel);

        panelAbajo.add(panelEspacio);

        JPanel panelSubmit = new JPanel();
        submit = new JButton("Enviar");
        EscuchadorRecuperarTodasFacturasEntreFechas escTFEF = new EscuchadorRecuperarTodasFacturasEntreFechas();
        submit.addActionListener(escTFEF);
        panelSubmit.add(submit);

        panelAbajo.add(submit);

        JPanel panelEspacio2 = new JPanel();
        JLabel espacioLabel2 = new JLabel(
                "                                                                                                                                                                                                                "
                        + "                                                                                                                                                                                                                                                                                 ");
        panelEspacio2.add(espacioLabel2);

        panelAbajo.add(panelEspacio2);

        panelNif.updateUI();
        panelEspacio.updateUI();
        panelSubmit.updateUI();
        panelFecha.updateUI();
        panelFecha2.updateUI();
        panelAbajo.updateUI();
    }

    // CREAR GUI

    public void creaGUI() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                GUI();
            }
        });
    }

    class EscuchadorFactura implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JButton boton = (JButton) e.getSource();
            String texto = boton.getText();
            if (texto.equals("Emitir factura")) {
                GUIEmitirFactura();
            }
            if (texto.equals("Recuperar datos factura")) {
                GUIRecuperarDatosFactura();
            }
            if (texto.equals("Recuperar todas las facturas")) {
                GUIRecuperarTodasFacturas();
            }
            if (texto.equals("Recuperar listado facturas entre fechas")) {
                GUIRecuperarFacturasEntreFechas();
            }
        }
    }

    class EscuchadorLlamada implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JButton boton = (JButton) e.getSource();
            String texto = boton.getText();
            if (texto.equals("Dar alta llamada")) {
                GUIDarAltaLlamada();
            }
            if (texto.equals("Listar llamadas cliente")) {
                GUIListarLlamadas();
            }
            if (texto.equals("Listar llamadas cliente entre fechas")) {
                GUIListarLlamadasEntreFechas();
            }
        }
    }

    // ESCUCHADORES CLIENTE

    class EscuchadorCliente implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JButton boton = (JButton) e.getSource();
            String texto = boton.getText();
            if (texto.equals("Nuevo cliente")) {
                GUIDarAlta();
            }
            if (texto.equals("Borrar cliente")) {
                GUIBorrarCliente();
            }
            if (texto.equals("Cambiar tarifa")) {
                GUICambiarTarifa();
            }
            if (texto.equals("Recuperar datos cliente")) {
                GUIRecuperarCliente();
            }
            if (texto.equals("Recuperar listado clientes")) {
                GUIRecuperarListado();
            }
            if (texto.equals("Mostrar listado clientes entre fechas")) {
                GUIRecuperarListadoEntreFechas();
            }
        }
    }

    class EscuchadorDarAlta implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            submit = (JButton) e.getSource();
            String texto = submit.getText();
            if (texto.equals("Enviar")) {
                panelFinal.removeAll();
                Calendar fecha = Calendar.getInstance();
                int anoLocal = Integer.parseInt(year.getText().trim());
                int mesLocal = Integer.parseInt(month.getText().trim());
                int diaLocal = Integer.parseInt(day.getText().trim());
                fecha.set(anoLocal, mesLocal, diaLocal);
                int codPosLocal = Integer.parseInt(codPos.getText().trim());
                Direccion dir = new Direccion(codPosLocal, prov.getText(), pob.getText());
                Tarifa tarifaLocal = null;
                tarifaLocal = TarifaFactory.crearTarifa(0, tarifaLocal, Double.parseDouble(tarifa.getText().trim()));
                try {
                    controlador.creaCliente(tipo, nombre.getText(), apellido.getText(), nif.getText(), dir,
                            email.getText(), fecha, tarifaLocal);
                    modelo.guardarDatos();

                    JLabel clienteRegistrado = new JLabel("Cliente registrado con éxito");
                    panelFinal.add(clienteRegistrado);
                    panelAbajo.add(panelFinal,BorderLayout.CENTER);
                    panelFinal.updateUI();
                    panelAbajo.updateUI();
                } catch (ExistingClientException e1) {
                    JLabel clienteNoRegistrado = new JLabel("Cliente ya existente");
                    panelFinal.add(clienteNoRegistrado);
                    panelAbajo.add(panelFinal);
                    panelFinal.updateUI();
                    panelAbajo.updateUI();
                }
            }
        }
    }

    class EscuchadorBorrarCliente implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            submit = (JButton) e.getSource();
            String texto = submit.getText();
            if (texto.equals("Enviar")) {
                panelFinal.removeAll();
                try {
                    controlador.borrarCliente(nif.getText());
                    modelo.guardarDatos();
                    JLabel clienteBorrado = new JLabel("Cliente borrado con éxito");
                    panelFinal.add(clienteBorrado);
                    panelAbajo.add(panelFinal);
                    panelFinal.updateUI();
                    panelAbajo.updateUI();
                } catch (NotExistingClientException e1) {
                    JLabel clienteNoEncontrado = new JLabel("Cliente no encontrado");
                    panelFinal.add(clienteNoEncontrado);
                    panelAbajo.add(panelFinal);
                    panelFinal.updateUI();
                    panelAbajo.updateUI();
                }
            }
        }
    }

    class EscuchadorCambiarTarifa implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            submit = (JButton) e.getSource();
            String texto = submit.getText();
            Tarifa tarifaLocal = null;
            tarifaLocal = TarifaFactory.crearTarifa(Integer.parseInt(tipoTar.getText()), tarifaLocal,
                    Double.parseDouble(tarifa.getText().trim()));
            if (texto.equals("Enviar")) {
                panelFinal.removeAll();
                try {
                    controlador.cambiarTarifa(nif.getText(), tarifaLocal);
                } catch (NotExistingClientException e1) {
                    e1.printStackTrace();
                }
                modelo.guardarDatos();
                panelFinal = new JPanel();
                JLabel tarifaCambiada = new JLabel("Tarifa cambiada con éxito");
                panelFinal.add(tarifaCambiada);
                panelAbajo.add(panelFinal);
                panelFinal.updateUI();
                panelAbajo.updateUI();
            }
        }
    }

    class EscuchadorRecuperarCliente implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            submit = (JButton) e.getSource();
            String texto = submit.getText();
            if (texto.equals("Enviar")) {
                panelFinal.removeAll();
                try {
                    JLabel cliente = new JLabel(controlador.recuperarDatosCliente(nif.getText()));
                    JScrollPane scroll = new JScrollPane(cliente);
                    scroll.setPreferredSize(new Dimension(1080, 100));
                    panelFinal.add(scroll);
                    panelAbajo.add(panelFinal);
                    panelFinal.updateUI();
                    panelAbajo.updateUI();
                } catch (NotExistingClientException e1) {
                    JLabel clienteNoEncontrado = new JLabel("Cliente no encontrado");
                    panelFinal.add(clienteNoEncontrado);
                    panelAbajo.add(panelFinal);
                    panelFinal.updateUI();
                    panelAbajo.updateUI();
                }
            }
        }
    }

    class EscuchadorRecuperarTodosEntreFechas implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            submit = (JButton) e.getSource();
            String texto = submit.getText();
            Calendar fechaInicio = Calendar.getInstance();
            int anoLocal = Integer.parseInt(year.getText().trim());
            int mesLocal = Integer.parseInt(month.getText().trim());
            int diaLocal = Integer.parseInt(day.getText().trim());
            fechaInicio.set(anoLocal, mesLocal, diaLocal);
            Calendar fechaFin = Calendar.getInstance();
            int anoLocal2 = Integer.parseInt(year2.getText().trim());
            int mesLocal2 = Integer.parseInt(month2.getText().trim());
            int diaLocal2 = Integer.parseInt(day2.getText().trim());
            fechaFin.set(anoLocal2, mesLocal2, diaLocal2);
            if (texto.equals("Enviar")) {
                panelFinal.removeAll();
                try {
                    Collection<Cliente> col = controlador.recuperaListadoClientesEntreFechas(fechaInicio, fechaFin);
                    DefaultListModel<String> datos = new DefaultListModel<>();
                    for (Cliente cliente : col) {
                        datos.addElement(cliente.toString());
                    }
                    JList<String> clientes = new JList<String>(datos);
                    JScrollPane scroll = new JScrollPane(clientes);
                    scroll.setPreferredSize(new Dimension(1080, 400));
                    clientes.setVisibleRowCount(30);
                    panelFinal.add(scroll);
                    panelAbajo.add(panelFinal);
                    scroll.updateUI();
                    panelFinal.updateUI();
                    panelAbajo.updateUI();
                } catch (NullListClientsException e1) {
                    JLabel clientesNoEncontrados = new JLabel("Clientes no encontrados");
                    panelFinal.add(clientesNoEncontrados);
                    panelAbajo.add(panelFinal);
                    panelFinal.updateUI();
                    panelAbajo.updateUI();
                } catch (IllegalPeriodException e1) {
                    JLabel clientesNoEncontrados = new JLabel("Fecha no válida");
                    panelFinal.add(clientesNoEncontrados);
                    panelAbajo.add(panelFinal);
                    panelFinal.updateUI();
                    panelAbajo.updateUI();
                }
            }
        }
    }

    class EscuchadorRecuperarTodos implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            submit = (JButton) e.getSource();
            String texto = submit.getText();
            if (texto.equals("Recuperar")) {
                panelFinal.removeAll();
                try {
                    Collection<Cliente> col = controlador.recuperaListadoClientes().values();
                    DefaultListModel<String> datos = new DefaultListModel<>();
                    for (Cliente cliente : col) {
                        datos.addElement(cliente.toString());
                    }
                    JList<String> clientes = new JList<String>(datos);
                    JScrollPane scroll = new JScrollPane(clientes);
                    scroll.setPreferredSize(new Dimension(1080, 400));
                    clientes.setVisibleRowCount(30);
                    panelFinal.add(scroll);
                    panelAbajo.add(panelFinal);
                    scroll.updateUI();
                    panelFinal.updateUI();
                    panelAbajo.updateUI();
                } catch (NullListClientsException e1) {
                    JLabel clientesNoEncontrados = new JLabel("Clientes no encontrados");
                    panelFinal.add(clientesNoEncontrados);
                    panelAbajo.add(panelFinal);
                    panelFinal.updateUI();
                    panelAbajo.updateUI();
                }
            }
        }
    }

    class EscuchadorDarAltaLlamada implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            submit = (JButton) e.getSource();
            String texto = submit.getText();
            if (texto.equals("Enviar")) {
                panelFinal.removeAll();
                Calendar fecha_llamada = Calendar.getInstance();
                int anoLocal = Integer.parseInt(year.getText().trim());
                int mesLocal = Integer.parseInt(month.getText().trim());
                int diaLocal = Integer.parseInt(day.getText().trim());
                int horaLocal = Integer.parseInt(hora.getText().trim());
                int minLocal = Integer.parseInt(minuto.getText().trim());
                fecha_llamada.set(anoLocal, mesLocal, diaLocal, horaLocal, minLocal);
                String telefono = telf.getText().trim();
                int dura = Integer.parseInt(dur.getText().trim());

                Llamada llamada = new Llamada(telefono, fecha_llamada, dura);
                try {
                    controlador.darDeAltaLlamada(llamada, nif.getText());
                    modelo.guardarDatos();

                    JLabel llamadaRegistrada = new JLabel("Llamada registrada con éxito");
                    panelFinal.add(llamadaRegistrada);
                    panelAbajo.add(panelFinal);
                    panelFinal.updateUI();
                    panelAbajo.updateUI();
                } catch (NotExistingClientException e1) {
                    JLabel clienteNoEncontrado = new JLabel("Cliente no encontrado");
                    panelFinal.add(clienteNoEncontrado);
                    panelAbajo.add(panelFinal);
                    panelFinal.updateUI();
                    panelAbajo.updateUI();
                }
            }
        }
    }

    class EscuchadorRecuperarTodasLlamadas implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            submit = (JButton) e.getSource();
            String texto = submit.getText();
            if (texto.equals("Enviar")) {
                panelFinal.removeAll();
                try {
                    Collection<Llamada> col = controlador.listarLlamadasCliente(nif.getText());
                    DefaultListModel<String> datos = new DefaultListModel<>();
                    for (Llamada llamada : col) {
                        datos.addElement(llamada.toString());
                    }
                    JList<String> llamadas = new JList<String>(datos);
                    JScrollPane scroll = new JScrollPane(llamadas);
                    scroll.setPreferredSize(new Dimension(1080, 400));
                    llamadas.setVisibleRowCount(30);
                    panelFinal.add(scroll);
                    panelAbajo.add(panelFinal);
                    scroll.updateUI();
                    panelFinal.updateUI();
                    panelAbajo.updateUI();
                } catch (NotExistingClientException | NullListCallException e1) {
                    JLabel clienteNoEncontrado = new JLabel("Cliente no encontrado");
                    panelFinal.add(clienteNoEncontrado);
                    panelAbajo.add(panelFinal);
                    panelFinal.updateUI();
                    panelAbajo.updateUI();
                }
            }
        }
    }

    class EscuchadorRecuperarTodasLlamadasEntreFechas implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            submit = (JButton) e.getSource();
            String texto = submit.getText();
            Calendar fechaInicio = Calendar.getInstance();
            int anoLocal = Integer.parseInt(year.getText().trim());
            int mesLocal = Integer.parseInt(month.getText().trim());
            int diaLocal = Integer.parseInt(day.getText().trim());
            fechaInicio.set(anoLocal, mesLocal, diaLocal);
            Calendar fechaFin = Calendar.getInstance();
            int anoLocal2 = Integer.parseInt(year2.getText().trim());
            int mesLocal2 = Integer.parseInt(month2.getText().trim());
            int diaLocal2 = Integer.parseInt(day2.getText().trim());
            fechaFin.set(anoLocal2, mesLocal2, diaLocal2);
            if (texto.equals("Enviar")) {
                panelFinal.removeAll();
                try {
                    Collection<Llamada> col = controlador.mostrarListadoLlamadasFechas(nif.getText(), fechaInicio, fechaFin);
                    DefaultListModel<String> datos = new DefaultListModel<>();
                    for (Llamada cliente : col) {
                        datos.addElement(cliente.toString());
                    }
                    JList<String> clientes = new JList<String>(datos);
                    JScrollPane scroll = new JScrollPane(clientes);
                    scroll.setPreferredSize(new Dimension(1080, 400));
                    clientes.setVisibleRowCount(30);
                    panelFinal.add(scroll);
                    panelAbajo.add(panelFinal);
                    scroll.updateUI();
                    panelFinal.updateUI();
                    panelAbajo.updateUI();
                } catch (IllegalPeriodException e1) {
                    JLabel fechaNoValida = new JLabel("Fecha no válida");
                    panelFinal.add(fechaNoValida);
                    panelAbajo.add(panelFinal);
                    panelFinal.updateUI();
                    panelAbajo.updateUI();
                } catch (NullListCallException e1) {
                    JLabel listaVacia = new JLabel("Lista vacía");
                    panelFinal.add(listaVacia);
                    panelAbajo.add(panelFinal);
                    panelFinal.updateUI();
                    panelAbajo.updateUI();
                } catch (NotExistingClientException e1) {
                    JLabel clienteNoEncontrado = new JLabel("Cliente no encontrado");
                    panelFinal.add(clienteNoEncontrado);
                    panelAbajo.add(panelFinal);
                    panelFinal.updateUI();
                    panelAbajo.updateUI();
                }
            }
        }
    }

    class EscuchadorEmitirFactura implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            submit = (JButton) e.getSource();
            String texto = submit.getText();
            if (texto.equals("Enviar")) {
                panelFinal.removeAll();
                Calendar fechaInicio = Calendar.getInstance();
                int anoLocal = Integer.parseInt(year.getText().trim());
                int mesLocal = Integer.parseInt(month.getText().trim());
                int diaLocal = Integer.parseInt(day.getText().trim());
                fechaInicio.set(anoLocal, mesLocal, diaLocal);
                Calendar fechaFin = Calendar.getInstance();
                int ano2Local = Integer.parseInt(year2.getText().trim());
                int mes2Local = Integer.parseInt(month2.getText().trim());
                int dia2Local = Integer.parseInt(day2.getText().trim());
                fechaInicio.set(ano2Local, mes2Local, dia2Local);

                try {
                    controlador.emitirFactura(codFac.getText(),nif.getText(),fechaInicio, fechaFin);
                    modelo.guardarDatos();

                    JLabel facturaRegistrada = new JLabel("Factura registrada con éxito");
                    panelFinal.add(facturaRegistrada);
                    panelAbajo.add(panelFinal);
                    panelFinal.updateUI();
                    panelAbajo.updateUI();
                } catch (NotExistingClientException e1) {
                    JLabel clienteNoEncontrado = new JLabel("Cliente no encontrado");
                    panelFinal.add(clienteNoEncontrado);
                    panelAbajo.add(panelFinal);
                    panelFinal.updateUI();
                    panelAbajo.updateUI();
                } catch (IllegalPeriodException e1) {
                    JLabel fechaNoValida = new JLabel("Fecha no válida");
                    panelFinal.add(fechaNoValida);
                    panelAbajo.add(panelFinal);
                    panelFinal.updateUI();
                    panelAbajo.updateUI();
                } catch (ExistingInvoiceException e1) {
                    JLabel facturaYaExiste = new JLabel("Factura ya existe");
                    panelFinal.add(facturaYaExiste);
                    panelAbajo.add(panelFinal);
                    panelFinal.updateUI();
                    panelAbajo.updateUI();
                }
            }
        }
    }

    class EscuchadorRecuperarFacturas implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            submit = (JButton) e.getSource();
            String texto = submit.getText();
            if (texto.equals("Enviar")) {
                panelFinal.removeAll();
                try {
                    JLabel fac = new JLabel(controlador.recuperarDatosFacturaCodigo(codFac.getText(),nif.getText()));
                    JScrollPane scroll = new JScrollPane(fac);
                    scroll.setPreferredSize(new Dimension(1080, 100));
                    panelFinal.add(scroll);
                    panelAbajo.add(panelFinal);
                    panelFinal.updateUI();
                    panelAbajo.updateUI();
                } catch (NotExistingInvoceException e1) {
                    JLabel facturaNoEncontrada = new JLabel("Factura no encontrada");
                    panelFinal.add(facturaNoEncontrada);
                    panelAbajo.add(panelFinal);
                    panelFinal.updateUI();
                    panelAbajo.updateUI();
                } catch (NotExistingClientException e1) {
                    JLabel clienteNoEncontrado = new JLabel("Cliente no encontrado");
                    panelFinal.add(clienteNoEncontrado);
                    panelAbajo.add(panelFinal);
                    panelFinal.updateUI();
                    panelAbajo.updateUI();
                }
            }
        }
    }

    class EscuchadorRecuperarTodasFacturas implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            submit = (JButton) e.getSource();
            String texto = submit.getText();
            if (texto.equals("Enviar")) {
                panelFinal.removeAll();
                try {
                    Collection<Factura> col = controlador.recuperarFacturas(nif.getText()).values();
                    DefaultListModel<String> datos = new DefaultListModel<>();
                    for (Factura factura : col) {
                        datos.addElement(factura.toString());
                    }
                    JList<String> facturas = new JList<String>(datos);
                    JScrollPane scroll = new JScrollPane(facturas);
                    scroll.setPreferredSize(new Dimension(1080, 400));
                    facturas.setVisibleRowCount(30);
                    panelFinal.add(scroll);
                    panelAbajo.add(panelFinal);
                    scroll.updateUI();
                    panelFinal.updateUI();
                    panelAbajo.updateUI();
                } catch (NullListCallException e1) {
                    JLabel listaVacia = new JLabel("Lista vacía");
                    panelFinal.add(listaVacia);
                    panelAbajo.add(panelFinal);
                    panelFinal.updateUI();
                    panelAbajo.updateUI();
                } catch (NotExistingClientException e1) {
                    JLabel clienteNoEncontrado = new JLabel("Cliente no encontrado");
                    panelFinal.add(clienteNoEncontrado);
                    panelAbajo.add(panelFinal);
                    panelFinal.updateUI();
                    panelAbajo.updateUI();
                } catch (NullListInvoicesException e1) {
                    JLabel listaVacia = new JLabel("Lista de facturas vacía");
                    panelFinal.add(listaVacia);
                    panelAbajo.add(panelFinal);
                    panelFinal.updateUI();
                    panelAbajo.updateUI();
                }
            }
        }
    }

    class EscuchadorRecuperarTodasFacturasEntreFechas implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            submit = (JButton) e.getSource();
            String texto = submit.getText();
            Calendar fechaInicio = Calendar.getInstance();
            int anoLocal = Integer.parseInt(year.getText().trim());
            int mesLocal = Integer.parseInt(month.getText().trim());
            int diaLocal = Integer.parseInt(day.getText().trim());
            fechaInicio.set(anoLocal, mesLocal, diaLocal);
            Calendar fechaFin = Calendar.getInstance();
            int anoLocal2 = Integer.parseInt(year2.getText().trim());
            int mesLocal2 = Integer.parseInt(month2.getText().trim());
            int diaLocal2 = Integer.parseInt(day2.getText().trim());
            fechaFin.set(anoLocal2, mesLocal2, diaLocal2);
            if (texto.equals("Enviar")) {
                panelFinal.removeAll();
                try {
                    Collection<Factura> col = controlador.mostrarListadoFacturasFechas(nif.getText(), fechaInicio, fechaFin);
                    DefaultListModel<String> datos = new DefaultListModel<>();
                    for (Factura fac : col) {
                        datos.addElement(fac.toString());
                    }
                    JList<String> facturas = new JList<String>(datos);
                    JScrollPane scroll = new JScrollPane(facturas);
                    scroll.setPreferredSize(new Dimension(1080, 400));
                    facturas.setVisibleRowCount(30);
                    panelFinal.add(scroll);
                    panelAbajo.add(panelFinal);
                    scroll.updateUI();
                    panelFinal.updateUI();
                    panelAbajo.updateUI();
                } catch (IllegalPeriodException e1) {
                    JLabel fechaNoValida = new JLabel("Fecha no válida");
                    panelFinal.add(fechaNoValida);
                    panelAbajo.add(panelFinal);
                    panelFinal.updateUI();
                    panelAbajo.updateUI();
                } catch (NotExistingClientException e1) {
                    JLabel clienteNoEncontrado = new JLabel("Cliente no encontrado");
                    panelFinal.add(clienteNoEncontrado);
                    panelAbajo.add(panelFinal);
                    panelFinal.updateUI();
                    panelAbajo.updateUI();
                } catch (NullListCallException e1) {
                    JLabel listaVacia = new JLabel("Lista de llamadas vacía");
                    panelFinal.add(listaVacia);
                    panelAbajo.add(panelFinal);
                    panelFinal.updateUI();
                    panelAbajo.updateUI();
                } catch (NullListInvoicesException e1) {
                    JLabel listaVacia = new JLabel("Lista de facturas vacía");
                    panelFinal.add(listaVacia);
                    panelAbajo.add(panelFinal);
                    panelFinal.updateUI();
                    panelAbajo.updateUI();
                }
            }
        }
    }

    class EscuchadorPrincipal implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JButton boton = (JButton) e.getSource();
            String texto = boton.getText();
            if (texto.equals("Gestión clientes")) {
                GUICliente();
            }
            if (texto.equals("Gestión llamadas")) {
                GUILlamada();
            }
            if (texto.equals("Gestión facturas")) {
                GUIFactura();
            }
        }
    }

}
