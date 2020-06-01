package interfaz.vista;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.DayOfWeek;
import java.util.Calendar;
import java.util.Collection;
import javax.swing.*;
import javax.swing.border.Border;

import clientes.Cliente;
import clientes.Direccion;
import clientes.TipoCliente;
import excepciones.*;
import facturas.Factura;
import interfaz.controlador.Controlador;
import interfaz.modelo.Modelo;
import llamadas.Llamada;
import tarifas.Tarifa;
import tarifas.TarifaFactory;
import tarifas.TipoTarifa;

public class ImplementacionVista implements Vista {
    private Controlador controlador;
    private Modelo modelo;
    private JFrame app;
    Container container;
    JPanel panelArriba, panelCentral;
    JPanel panelAbajo = new JPanel();
    JPanel panelFinal = new JPanel();
    JTextField nif, nombre, apellido, codPos, pob, prov, email, day, month, year, tarifa, day2, month2, year2, telf, codFac, hora, minuto, segundo, dur;
    JButton submit;
    TipoCliente tipo;
    TipoTarifa tipoTar;
    JRadioButton empresa,particular,basica,dia,horas;
    Choice dias,horaInit,horaFin;
    JFrame pop = new JFrame();


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
        app = new JFrame("EI1017");
        container = app.getContentPane();
        JMenuBar menu = new JMenuBar();
        JMenu clientes = new JMenu("Gestión clientes");
        JMenuItem addClient = new JMenuItem("Nuevo cliente");
        addClient.setPreferredSize(new Dimension(248,30));
        JMenuItem removeClient = new JMenuItem("Borrar cliente");
        removeClient.setPreferredSize(new Dimension(248,30));
        JMenuItem setTarifa = new JMenuItem("Cambiar tarifa");
        setTarifa.setPreferredSize(new Dimension(248,30));
        JMenuItem dataClient = new JMenuItem("Recuperar datos cliente");
        dataClient.setPreferredSize(new Dimension(248,30));
        JMenuItem listClient = new JMenuItem("Recuperar listado clientes");
        listClient.setPreferredSize(new Dimension(248,30));
        JMenuItem listClientDates = new JMenuItem("Mostrar listado clientes entre fechas");
        listClientDates.setPreferredSize(new Dimension(248,30));
        clientes.add(addClient);
        clientes.addSeparator();
        clientes.add(removeClient);
        clientes.addSeparator();
        clientes.add(setTarifa);
        clientes.addSeparator();
        clientes.add(dataClient);
        clientes.addSeparator();
        clientes.add(listClient);
        clientes.addSeparator();
        clientes.add(listClientDates);
        EscuchadorCliente escuchadorCliente = new EscuchadorCliente();
        addClient.addActionListener(escuchadorCliente);
        removeClient.addActionListener(escuchadorCliente);
        setTarifa.addActionListener(escuchadorCliente);
        dataClient.addActionListener(escuchadorCliente);
        listClient.addActionListener(escuchadorCliente);
        listClientDates.addActionListener(escuchadorCliente);
        clientes.setPreferredSize(new Dimension(250,30));
        Border border = BorderFactory.createLineBorder(Color.BLACK,1);
        clientes.setBorder(border);

        JMenu llamadas = new JMenu("Gestión llamadas");
        JMenuItem addCall = new JMenuItem("Dar alta llamada");
        addCall.setPreferredSize(new Dimension(248,30));
        JMenuItem listCall = new JMenuItem("Listar llamadas cliente");
        listCall.setPreferredSize(new Dimension(248,30));
        JMenuItem listCallDates = new JMenuItem("Listar llamadas cliente entre fechas");
        listCallDates.setPreferredSize(new Dimension(248,30));
        llamadas.add(addCall);
        llamadas.addSeparator();
        llamadas.add(listCall);
        llamadas.addSeparator();
        llamadas.add(listCallDates);
        EscuchadorLlamada escuchadorLlamada = new EscuchadorLlamada();
        addCall.addActionListener(escuchadorLlamada);
        listCall.addActionListener(escuchadorLlamada);
        listCallDates.addActionListener(escuchadorLlamada);
        llamadas.setPreferredSize(new Dimension(250,30));
        llamadas.setBorder(border);

        JMenu facturas = new JMenu("Gestión facturas");
        JMenuItem addInvoice = new JMenuItem("Emitir factura");
        addInvoice.setPreferredSize(new Dimension(248,30));
        JMenuItem dataInvoice = new JMenuItem("Recuperar datos factura de cliente");
        dataInvoice.setPreferredSize(new Dimension(248,30));
        JMenuItem listInvoice = new JMenuItem("Recuperar todas las facturas de cliente");
        listInvoice.setPreferredSize(new Dimension(248,30));
        JMenuItem listInvoiceDates = new JMenuItem("Recuperar listado facturas entre fechas");
        listInvoiceDates.setPreferredSize(new Dimension(248,30));
        facturas.add(addInvoice);
        facturas.addSeparator();
        facturas.add(dataInvoice);
        facturas.addSeparator();
        facturas.add(listInvoice);
        facturas.addSeparator();
        facturas.add(listInvoiceDates);
        EscuchadorFactura escuchadorFactura = new EscuchadorFactura();
        addInvoice.addActionListener(escuchadorFactura);
        dataInvoice.addActionListener(escuchadorFactura);
        listInvoice.addActionListener(escuchadorFactura);
        listInvoiceDates.addActionListener(escuchadorFactura);
        facturas.setPreferredSize(new Dimension(250,30));
        facturas.setBorder(border);

        menu.add(clientes);
        menu.add(llamadas);
        menu.add(facturas);
        panelArriba = new JPanel();
        panelArriba.setBackground(Color.LIGHT_GRAY);
        panelArriba.add(menu);

        container.add(panelArriba, BorderLayout.NORTH);
        panelCentral = new JPanel();
        panelCentral.setPreferredSize(new Dimension(1200, 300));
        panelAbajo.setPreferredSize(new Dimension(1200, 300));
        panelCentral.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        border = BorderFactory.createLineBorder(Color.BLACK,2);
        panelCentral.setBorder(border);
        panelCentral.setBackground(Color.WHITE);
        panelAbajo.setBackground(Color.WHITE);
        panelAbajo.setLayout(new GridLayout(0,1));
        panelArriba.setPreferredSize(new Dimension(1080, 40));
        container.add(panelCentral, BorderLayout.CENTER);
        app.setSize(1200, 400);
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.setVisible(true);
    }

    private void GUIDarAlta() {
        panelCentral.add(panelAbajo);
        panelCentral.updateUI();
        panelAbajo.removeAll();
        JPanel panelTipoCliente = new JPanel();
        empresa = new JRadioButton("Empresa");
        particular = new JRadioButton("Particular");
        empresa.setSelected(true);
        ButtonGroup grupo = new ButtonGroup();
        grupo.add(empresa);
        grupo.add(particular);
        panelTipoCliente.add(empresa);
        panelTipoCliente.add(particular);
        panelAbajo.add(panelTipoCliente);

        JPanel panelNombreCliente = new JPanel();
        nombre = new JTextField(20);
        JLabel nombreLabel = new JLabel("Nombre: ");
        panelNombreCliente.add(nombreLabel);
        panelNombreCliente.add(nombre);

        panelAbajo.add(panelNombreCliente);

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

        EscuchadorDarAlta escuchadorDarAlta = new EscuchadorDarAlta();
        JPanel panelSubmit = new JPanel();
        submit = new JButton("Enviar");
        submit.addActionListener(escuchadorDarAlta);
        panelSubmit.add(submit);

        panelAbajo.add(submit);

        panelSubmit.updateUI();
        panelTarifa.updateUI();
        panelFecha.updateUI();
        panelCorreo.updateUI();
        panelDireccion.updateUI();
        panelApellido.updateUI();
        panelNombreCliente.updateUI();
        panelNif.updateUI();
        panelTipoCliente.updateUI();
        panelAbajo.updateUI();
    }

    private void GUIBorrarCliente() {
        panelCentral.add(panelAbajo);
        panelCentral.updateUI();
        panelAbajo.removeAll();

        JPanel panelNif = new JPanel();
        nif = new JTextField(9);
        JLabel nifLabel = new JLabel("NIF: ");
        panelNif.add(nifLabel);
        panelNif.add(nif);

        panelAbajo.add(panelNif);


        JPanel panelSubmit = new JPanel();
        submit = new JButton("Enviar");
        EscuchadorBorrarCliente escuchadorBorrarCliente = new EscuchadorBorrarCliente();
        submit.addActionListener(escuchadorBorrarCliente);
        panelSubmit.add(submit);

        panelAbajo.add(submit);

        JPanel panelEspacio2 = new JPanel();

        panelSubmit.updateUI();
        panelNif.updateUI();
        panelAbajo.updateUI();

    }

    private void GUICambiarTarifa() {
        panelCentral.add(panelAbajo);
        panelCentral.updateUI();
        panelAbajo.removeAll();

        JPanel panelNif = new JPanel();
        nif = new JTextField(9);
        JLabel nifLabel = new JLabel("NIF: ");
        panelNif.add(nifLabel);
        panelNif.add(nif);

        panelAbajo.add(panelNif);

        JPanel panelTipoTar = new JPanel();
        basica = new JRadioButton("Básica");
        basica.setSelected(true);
        dia = new JRadioButton("Día");
        horas = new JRadioButton("Horas");
        ButtonGroup grupo = new ButtonGroup();
        grupo.add(basica);
        grupo.add(dia);
        grupo.add(horas);
        panelTipoTar.add(basica);
        panelTipoTar.add(dia);
        panelTipoTar.add(horas);

        panelAbajo.add(panelTipoTar);

        JPanel panelTarifa = new JPanel();
        JLabel tarifaLabel = new JLabel("Importe: ");
        tarifa = new JTextField(5);
        panelTarifa.add(tarifaLabel);
        panelTarifa.add(tarifa);

        panelAbajo.add(panelTarifa);

        JPanel panelTarifaDia = new JPanel();
        JLabel tarifaLabelDia = new JLabel("(Solo si seleccionas Día)");
        dias = new Choice();
        dias.addItem("Lunes");
        dias.addItem("Martes");
        dias.addItem("Miércoles");
        dias.addItem("Jueves");
        dias.addItem("Viernes");
        dias.addItem("Sábado");
        dias.addItem("Domingo");
        panelTarifaDia.add(tarifaLabelDia);
        panelTarifaDia.add(dias);
        panelAbajo.add(panelTarifaDia);

        JPanel panelTarifaHoras = new JPanel();
        JLabel tarifaLabelHoras = new JLabel("(Solo si seleccionas Horas)");
        horaInit = new Choice();
        horaFin = new Choice();
        horaInit.addItem("00");
        horaInit.addItem("01");
        horaInit.addItem("02");
        horaInit.addItem("03");
        horaInit.addItem("04");
        horaInit.addItem("05");
        horaInit.addItem("06");
        horaInit.addItem("07");
        horaInit.addItem("08");
        horaInit.addItem("09");
        horaInit.addItem("10");
        horaInit.addItem("11");
        horaInit.addItem("12");
        horaInit.addItem("13");
        horaInit.addItem("14");
        horaInit.addItem("15");
        horaInit.addItem("16");
        horaInit.addItem("17");
        horaInit.addItem("18");
        horaInit.addItem("19");
        horaInit.addItem("20");
        horaInit.addItem("21");
        horaInit.addItem("22");
        horaInit.addItem("23");
        horaInit.select(0);

        horaFin.addItem("01");
        horaFin.addItem("02");
        horaFin.addItem("03");
        horaFin.addItem("04");
        horaFin.addItem("05");
        horaFin.addItem("06");
        horaFin.addItem("07");
        horaFin.addItem("08");
        horaFin.addItem("09");
        horaFin.addItem("10");
        horaFin.addItem("11");
        horaFin.addItem("12");
        horaFin.addItem("13");
        horaFin.addItem("14");
        horaFin.addItem("15");
        horaFin.addItem("16");
        horaFin.addItem("17");
        horaFin.addItem("18");
        horaFin.addItem("19");
        horaFin.addItem("20");
        horaFin.addItem("21");
        horaFin.addItem("22");
        horaFin.addItem("23");
        horaFin.addItem("24");
        horaFin.select(0);

        panelTarifaHoras.add(tarifaLabelHoras);
        panelTarifaHoras.add(horaInit);
        panelTarifaHoras.add(horaFin);
        panelAbajo.add(panelTarifaHoras);


        JPanel panelSubmit = new JPanel();
        submit = new JButton("Enviar");
        EscuchadorCambiarTarifa escuchadorCambiarTarifa = new EscuchadorCambiarTarifa();
        submit.addActionListener(escuchadorCambiarTarifa);
        panelSubmit.add(submit);

        panelAbajo.add(submit);

        panelTipoTar.updateUI();
        panelTarifaDia.updateUI();
        panelTarifaHoras.updateUI();
        panelSubmit.updateUI();
        panelTarifa.updateUI();
        panelNif.updateUI();
        panelAbajo.updateUI();
    }

    private void GUIRecuperarCliente() {
        panelCentral.add(panelAbajo);
        panelCentral.updateUI();
        panelAbajo.removeAll();

        JPanel panelNif = new JPanel();
        nif = new JTextField(9);
        JLabel nifLabel = new JLabel("NIF: ");
        panelNif.add(nifLabel);
        panelNif.add(nif);

        panelAbajo.add(panelNif);


        JPanel panelSubmit = new JPanel();
        submit = new JButton("Enviar");
        EscuchadorRecuperarCliente escuchadorRecuperarCliente = new EscuchadorRecuperarCliente();
        submit.addActionListener(escuchadorRecuperarCliente);
        panelSubmit.add(submit);

        panelAbajo.add(submit);

        panelSubmit.updateUI();
        panelNif.updateUI();
        panelAbajo.updateUI();
    }

    private void GUIRecuperarListado() {
        panelCentral.add(panelAbajo);
        panelCentral.updateUI();
        panelAbajo.removeAll();

        JPanel panelSubmit = new JPanel();
        submit = new JButton("Recuperar");
        EscuchadorRecuperarTodos escuchadorTodos = new EscuchadorRecuperarTodos();
        submit.addActionListener(escuchadorTodos);
        panelSubmit.add(submit);

        panelAbajo.add(submit);

        panelSubmit.updateUI();
        panelAbajo.updateUI();
    }

    private void GUIRecuperarListadoEntreFechas() {
        panelCentral.add(panelAbajo);
        panelCentral.updateUI();
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

        JPanel panelSubmit = new JPanel();
        submit = new JButton("Enviar");
        EscuchadorRecuperarTodosEntreFechas escuchadorFechas = new EscuchadorRecuperarTodosEntreFechas();
        submit.addActionListener(escuchadorFechas);
        panelSubmit.add(submit);

        panelAbajo.add(submit);

        panelSubmit.updateUI();
        panelFecha.updateUI();
        panelFecha2.updateUI();
        panelAbajo.updateUI();
    }

    private void GUIDarAltaLlamada() {
        panelCentral.add(panelAbajo);
        panelCentral.updateUI();
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
        segundo = new JTextField(6);
        JLabel fechaLabel = new JLabel("Fecha de alta: ");
        JLabel anoLabel = new JLabel("Año: ");
        JLabel mesLabel = new JLabel("Mes(numérico): ");
        JLabel diaLabel = new JLabel("Día: ");
        JLabel horaLabel = new JLabel("Hora: ");
        JLabel minutoLabel = new JLabel("Minutos: ");
        JLabel segundoLabel = new JLabel("Segundos: ");
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
        panelFecha.add(segundoLabel);
        panelFecha.add(segundo);

        panelAbajo.add(panelFecha);

        JPanel panelDur = new JPanel();
        dur = new JTextField(5);
        JLabel durLabel = new JLabel("Duración: ");
        panelDur.add(durLabel);
        panelDur.add(dur);

        panelAbajo.add(panelDur);


        JPanel panelSubmit = new JPanel();
        submit = new JButton("Enviar");
        EscuchadorDarAltaLlamada escuchadorAltaLlamada = new EscuchadorDarAltaLlamada();
        submit.addActionListener(escuchadorAltaLlamada);
        panelSubmit.add(submit);

        panelAbajo.add(submit);

        panelSubmit.updateUI();
        panelFecha.updateUI();
        panelNif.updateUI();
        panelDur.updateUI();
        panelTelf.updateUI();
        panelAbajo.updateUI();

    }

    private void GUIListarLlamadas() {
        panelCentral.add(panelAbajo);
        panelCentral.updateUI();
        panelAbajo.removeAll();

        JPanel panelNif = new JPanel();
        nif = new JTextField(9);
        JLabel nifLabel = new JLabel("NIF: ");
        panelNif.add(nifLabel);
        panelNif.add(nif);

        panelAbajo.add(panelNif);

        JPanel panelSubmit = new JPanel();
        submit = new JButton("Enviar");
        EscuchadorRecuperarTodasLlamadas escuchadorRecuperarTodasLlamadas = new EscuchadorRecuperarTodasLlamadas();
        submit.addActionListener(escuchadorRecuperarTodasLlamadas);
        panelSubmit.add(submit);

        panelAbajo.add(submit);

        panelSubmit.updateUI();
        panelNif.updateUI();
        panelAbajo.updateUI();
    }

    private void GUIListarLlamadasEntreFechas() {
        panelCentral.add(panelAbajo);
        panelCentral.updateUI();
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


        JPanel panelSubmit = new JPanel();
        submit = new JButton("Enviar");
        EscuchadorRecuperarTodasLlamadasEntreFechas escRTL = new EscuchadorRecuperarTodasLlamadasEntreFechas();
        submit.addActionListener(escRTL);
        panelSubmit.add(submit);

        panelAbajo.add(submit);

        panelNif.updateUI();
        panelSubmit.updateUI();
        panelFecha.updateUI();
        panelFecha2.updateUI();
        panelAbajo.updateUI();
    }


    private void GUIEmitirFactura() {
        panelCentral.add(panelAbajo);
        panelCentral.updateUI();
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

        JPanel panelSubmit = new JPanel();
        submit = new JButton("Enviar");
        EscuchadorEmitirFactura escEF = new EscuchadorEmitirFactura();
        submit.addActionListener(escEF);
        panelSubmit.add(submit);

        panelAbajo.add(submit);

        panelSubmit.updateUI();
        panelFecha.updateUI();
        panelNif.updateUI();
        panelAbajo.updateUI();

    }

    private void GUIRecuperarDatosFactura() {
        panelCentral.add(panelAbajo);
        panelCentral.updateUI();
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

        JPanel panelSubmit = new JPanel();
        submit = new JButton("Enviar");
        EscuchadorRecuperarFacturas escRF = new EscuchadorRecuperarFacturas();
        submit.addActionListener(escRF);
        panelSubmit.add(submit);

        panelAbajo.add(submit);

        panelSubmit.updateUI();
        panelcodFac.updateUI();
        panelAbajo.updateUI();
    }

    private void GUIRecuperarTodasFacturas() {
        panelCentral.add(panelAbajo);
        panelCentral.updateUI();
        panelAbajo.removeAll();

        JPanel panelNif = new JPanel();
        nif = new JTextField(9);
        JLabel nifLabel = new JLabel("NIF: ");
        panelNif.add(nifLabel);
        panelNif.add(nif);

        panelAbajo.add(panelNif);


        JPanel panelSubmit = new JPanel();
        submit = new JButton("Enviar");
        EscuchadorRecuperarTodasFacturas escRTF = new EscuchadorRecuperarTodasFacturas();
        submit.addActionListener(escRTF);
        panelSubmit.add(submit);

        panelAbajo.add(submit);

        panelSubmit.updateUI();
        panelNif.updateUI();
        panelAbajo.updateUI();
    }

    private void GUIRecuperarFacturasEntreFechas() {
        panelCentral.add(panelAbajo);
        panelCentral.updateUI();
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

        JPanel panelSubmit = new JPanel();
        submit = new JButton("Enviar");
        EscuchadorRecuperarTodasFacturasEntreFechas escTFEF = new EscuchadorRecuperarTodasFacturasEntreFechas();
        submit.addActionListener(escTFEF);
        panelSubmit.add(submit);

        panelAbajo.add(submit);

        panelNif.updateUI();
        panelSubmit.updateUI();
        panelFecha.updateUI();
        panelFecha2.updateUI();
        panelAbajo.updateUI();
    }


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
            if (e.getActionCommand().equals("Emitir factura")) {
                GUIEmitirFactura();
            }
            else if (e.getActionCommand().equals("Recuperar datos factura de cliente")) {
                GUIRecuperarDatosFactura();
            }
            else if (e.getActionCommand().equals("Recuperar todas las facturas de cliente")) {
                GUIRecuperarTodasFacturas();
            }
            else if (e.getActionCommand().equals("Recuperar listado facturas entre fechas")) {
                GUIRecuperarFacturasEntreFechas();
            }
        }
    }

    class EscuchadorLlamada implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("Dar alta llamada")) {
                GUIDarAltaLlamada();
            }
            else if (e.getActionCommand().equals("Listar llamadas cliente")) {
                GUIListarLlamadas();
            }
            else if (e.getActionCommand().equals("Listar llamadas cliente entre fechas")) {
                GUIListarLlamadasEntreFechas();
            }
        }
    }

    class EscuchadorCliente implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("Nuevo cliente")) {
                GUIDarAlta();
            }
            else if (e.getActionCommand().equals("Borrar cliente")) {
                GUIBorrarCliente();
            }
            else if (e.getActionCommand().equals("Cambiar tarifa")) {
                GUICambiarTarifa();
            }
            else if (e.getActionCommand().equals("Recuperar datos cliente")) {
                GUIRecuperarCliente();
            }
            else if (e.getActionCommand().equals("Recuperar listado clientes")) {
                GUIRecuperarListado();
            }
            else if (e.getActionCommand().equals("Mostrar listado clientes entre fechas")) {
                GUIRecuperarListadoEntreFechas();
            }
        }
    }

    class EscuchadorDarAlta implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("Enviar")) {
                if(empresa.isSelected()){
                    tipo = TipoCliente.EMPRESA;
                }
                else{
                    tipo = TipoCliente.PARTICULAR;
                }
                Calendar fecha = Calendar.getInstance();
                int anoLocal = Integer.parseInt(year.getText().trim());
                int mesLocal = Integer.parseInt(month.getText().trim());
                int diaLocal = Integer.parseInt(day.getText().trim());
                fecha.set(anoLocal, mesLocal - 1, diaLocal);
                int codPosLocal = Integer.parseInt(codPos.getText().trim());
                Direccion dir = new Direccion(codPosLocal, prov.getText().trim(), pob.getText().trim());
                Tarifa tarifaLocal = null;
                tarifaLocal = TarifaFactory.crearTarifa(TipoTarifa.BASICA, tarifaLocal, Double.parseDouble(tarifa.getText().trim()),null,null,null);
                try {
                    controlador.addCliente(tipo, nombre.getText().trim(), apellido.getText().trim(), nif.getText().trim(), dir,
                            email.getText(), fecha, tarifaLocal);
                    modelo.guardarDatos();
                    JOptionPane.showMessageDialog(pop,"Cliente registrado con éxito","Éxito",JOptionPane.PLAIN_MESSAGE);
                } catch (ExistingClientException e1) {
                    JOptionPane.showMessageDialog(pop,"Cliente ya existente","Aviso",JOptionPane.WARNING_MESSAGE);
                }
            }
        }
    }

    class EscuchadorBorrarCliente implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("Enviar")) {
                try {
                    controlador.removeCliente(nif.getText().trim());
                    modelo.guardarDatos();
                    JOptionPane.showMessageDialog(pop,"Cliente borrado con éxito","Éxito",JOptionPane.PLAIN_MESSAGE);
                } catch (NotExistingClientException e1) {
                    JOptionPane.showMessageDialog(pop,"Cliente no encontrado","Aviso",JOptionPane.WARNING_MESSAGE);
                }
            }
        }
    }

    class EscuchadorCambiarTarifa implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Tarifa tarifaBase = null;
            try {
                tarifaBase = controlador.listarDatos(nif.getText().trim()).getTarifa();
            } catch (NotExistingClientException e1) {
                JOptionPane.showMessageDialog(pop,"Cliente no encontrado","Aviso",JOptionPane.WARNING_MESSAGE);
                panelAbajo.updateUI();
            }
            if(basica.isSelected()){
                tipoTar = TipoTarifa.BASICA;
            }
            else if(dia.isSelected()){
                tipoTar = TipoTarifa.DIA;
            }
            else{
                tipoTar = TipoTarifa.HORAS;
            }
            tarifaBase = TarifaFactory.crearTarifa(tipoTar, tarifaBase,
                    Double.parseDouble(tarifa.getText().trim()), DayOfWeek.of(dias.getSelectedIndex() + 1),Integer.parseInt(horaInit.getSelectedItem()),Integer.parseInt(horaFin.getSelectedItem()));

            if (e.getActionCommand().equals("Enviar")) {
                try {
                    controlador.setTarifa(nif.getText().trim(), tarifaBase);
                } catch (NotExistingClientException e1) {
                    JOptionPane.showMessageDialog(pop,"Cliente no encontrado","Aviso",JOptionPane.WARNING_MESSAGE);
                }
                modelo.guardarDatos();
                JOptionPane.showMessageDialog(pop,"Tarifa cambiada con éxito","Éxito",JOptionPane.PLAIN_MESSAGE);
            }
        }
    }

    class EscuchadorRecuperarCliente implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("Enviar")) {
                panelFinal.removeAll();
                try {
                    JLabel cliente = new JLabel(controlador.listarDatos(nif.getText().trim()).toString());
                    JScrollPane scroll = new JScrollPane(cliente);
                    scroll.setPreferredSize(new Dimension(1000, 100));
                    panelFinal.add(scroll);
                    panelAbajo.add(panelFinal);
                    panelFinal.updateUI();
                    panelAbajo.updateUI();
                } catch (NotExistingClientException e1) {
                    JOptionPane.showMessageDialog(pop,"Cliente no encontrado","Aviso",JOptionPane.WARNING_MESSAGE);
                }
            }
        }
    }
    class EscuchadorRecuperarTodos implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("Recuperar")) {
                panelFinal.removeAll();
                try {
                    Collection<Cliente> col = controlador.listaClientes().values();
                    DefaultListModel<String> datos = new DefaultListModel<>();
                    for (Cliente cliente : col) {
                        datos.addElement(cliente.toString());
                    }
                    JList<String> clientes = new JList<String>(datos);
                    JScrollPane scroll = new JScrollPane(clientes);
                    scroll.setPreferredSize(new Dimension(1000, 100));
                    clientes.setVisibleRowCount(4);
                    panelFinal.add(scroll);
                    panelAbajo.add(panelFinal);
                    scroll.updateUI();
                    panelFinal.updateUI();
                    panelAbajo.updateUI();
                } catch (NullListClientsException e1) {
                    JOptionPane.showMessageDialog(pop,"Clientes no encontrados","Aviso",JOptionPane.WARNING_MESSAGE);
                }
            }
        }
    }

    class EscuchadorRecuperarTodosEntreFechas implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Calendar fechaInicio = Calendar.getInstance();
            int anoLocal = Integer.parseInt(year.getText().trim());
            int mesLocal = Integer.parseInt(month.getText().trim());
            int diaLocal = Integer.parseInt(day.getText().trim());
            fechaInicio.set(anoLocal, mesLocal - 1, diaLocal);
            Calendar fechaFin = Calendar.getInstance();
            int anoLocal2 = Integer.parseInt(year2.getText().trim());
            int mesLocal2 = Integer.parseInt(month2.getText().trim());
            int diaLocal2 = Integer.parseInt(day2.getText().trim());
            fechaFin.set(anoLocal2, mesLocal2 - 1, diaLocal2);
            if (e.getActionCommand().equals("Enviar")) {
                panelFinal.removeAll();
                try {
                    Collection<Cliente> col = controlador.mostrarListaClientesEntreFechas(fechaInicio, fechaFin);
                    DefaultListModel<String> datos = new DefaultListModel<>();
                    for (Cliente cliente : col) {
                        datos.addElement(cliente.toString());
                    }
                    JList<String> clientes = new JList<String>(datos);
                    JScrollPane scroll = new JScrollPane(clientes);
                    scroll.setPreferredSize(new Dimension(1000, 100));
                    clientes.setVisibleRowCount(4);
                    panelFinal.add(scroll);
                    panelAbajo.add(panelFinal);
                    scroll.updateUI();
                    panelFinal.updateUI();
                    panelAbajo.updateUI();
                } catch (NullListClientsException e1) {
                    JOptionPane.showMessageDialog(pop,"Cliente no encontrados","Aviso",JOptionPane.WARNING_MESSAGE);
                } catch (IllegalPeriodException e1) {
                    JOptionPane.showMessageDialog(pop,"Fecha no válida","Error",JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    class EscuchadorDarAltaLlamada implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("Enviar")) {
                Calendar fecha_llamada = Calendar.getInstance();
                int anoLocal = Integer.parseInt(year.getText().trim());
                int mesLocal = Integer.parseInt(month.getText().trim());
                int diaLocal = Integer.parseInt(day.getText().trim());
                int horaLocal = Integer.parseInt(hora.getText().trim());
                int minLocal = Integer.parseInt(minuto.getText().trim());
                int segLocal = Integer.parseInt(segundo.getText().trim());
                fecha_llamada.set(anoLocal, mesLocal - 1, diaLocal, horaLocal, minLocal,segLocal);
                String telefono = telf.getText().trim();
                int dura = Integer.parseInt(dur.getText().trim());

                Llamada llamada = new Llamada(telefono, fecha_llamada, dura);
                try {
                    controlador.addLlamada(llamada, nif.getText());
                    modelo.guardarDatos();
                    JOptionPane.showMessageDialog(pop,"Llamada registrada con éxito","Éxito",JOptionPane.PLAIN_MESSAGE);
                } catch (NotExistingClientException e1) {
                    JOptionPane.showMessageDialog(pop,"Cliente no encontrado","Aviso",JOptionPane.WARNING_MESSAGE);
                }
            }
        }
    }

    class EscuchadorRecuperarTodasLlamadas implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("Enviar")) {
                panelFinal.removeAll();
                try {
                    Collection<Llamada> col = controlador.listaLlamadas(nif.getText().trim());
                    DefaultListModel<String> datos = new DefaultListModel<>();
                    for (Llamada llamada : col) {
                        datos.addElement(llamada.toString());
                    }
                    JList<String> llamadas = new JList<String>(datos);
                    JScrollPane scroll = new JScrollPane(llamadas);
                    scroll.setPreferredSize(new Dimension(1000, 100));
                    llamadas.setVisibleRowCount(4);
                    panelFinal.add(scroll);
                    panelAbajo.add(panelFinal);
                    scroll.updateUI();
                    panelFinal.updateUI();
                    panelAbajo.updateUI();
                } catch (NotExistingClientException | NullListCallException e1) {
                    JOptionPane.showMessageDialog(pop,"Cliente no encontrado","Aviso",JOptionPane.WARNING_MESSAGE);
                }
            }
        }
    }

    class EscuchadorRecuperarTodasLlamadasEntreFechas implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Calendar fechaInicio = Calendar.getInstance();
            int anoLocal = Integer.parseInt(year.getText().trim());
            int mesLocal = Integer.parseInt(month.getText().trim());
            int diaLocal = Integer.parseInt(day.getText().trim());
            fechaInicio.set(anoLocal, mesLocal - 1, diaLocal);
            Calendar fechaFin = Calendar.getInstance();
            int anoLocal2 = Integer.parseInt(year2.getText().trim());
            int mesLocal2 = Integer.parseInt(month2.getText().trim());
            int diaLocal2 = Integer.parseInt(day2.getText().trim());
            fechaFin.set(anoLocal2, mesLocal2 - 1, diaLocal2);
            if (e.getActionCommand().equals("Enviar")) {
                panelFinal.removeAll();
                try {
                    Collection<Llamada> col = controlador.mostrarListaLlamadasEntreFechas(nif.getText().trim(), fechaInicio, fechaFin);
                    DefaultListModel<String> datos = new DefaultListModel<>();
                    for (Llamada cliente : col) {
                        datos.addElement(cliente.toString());
                    }
                    JList<String> clientes = new JList<String>(datos);
                    JScrollPane scroll = new JScrollPane(clientes);
                    scroll.setPreferredSize(new Dimension(1000, 100));
                    clientes.setVisibleRowCount(4);
                    panelFinal.add(scroll);
                    panelAbajo.add(panelFinal);
                    scroll.updateUI();
                    panelFinal.updateUI();
                    panelAbajo.updateUI();
                } catch (IllegalPeriodException e1) {
                    JOptionPane.showMessageDialog(pop,"Fecha no válida","Error",JOptionPane.ERROR_MESSAGE);
                } catch (NullListCallException e1) {
                    JOptionPane.showMessageDialog(pop,"Lista vacía","Aviso",JOptionPane.WARNING_MESSAGE);
                } catch (NotExistingClientException e1) {
                    JOptionPane.showMessageDialog(pop,"Cliente no encontrado","Aviso",JOptionPane.WARNING_MESSAGE);
                }
            }
        }
    }

    class EscuchadorEmitirFactura implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("Enviar")) {
                Calendar fechaInicio = Calendar.getInstance();
                int anoLocal = Integer.parseInt(year.getText().trim());
                int mesLocal = Integer.parseInt(month.getText().trim());
                int diaLocal = Integer.parseInt(day.getText().trim());
                fechaInicio.set(anoLocal, mesLocal - 1, diaLocal);
                Calendar fechaFin = Calendar.getInstance();
                int ano2Local = Integer.parseInt(year2.getText().trim());
                int mes2Local = Integer.parseInt(month2.getText().trim());
                int dia2Local = Integer.parseInt(day2.getText().trim());
                fechaFin.set(ano2Local, mes2Local - 1, dia2Local);

                try {
                    controlador.emitirFactura(codFac.getText().trim(),nif.getText().trim(),fechaInicio, fechaFin);
                    modelo.guardarDatos();
                    JOptionPane.showMessageDialog(pop,"Factura registrada con éxito","Éxito",JOptionPane.PLAIN_MESSAGE);
                } catch (NotExistingClientException e1) {
                    JOptionPane.showMessageDialog(pop,"Cliente no encontrado","Aviso",JOptionPane.WARNING_MESSAGE);
                } catch (IllegalPeriodException e1) {
                    JOptionPane.showMessageDialog(pop,"Fecha no válida","Error",JOptionPane.ERROR_MESSAGE);
                } catch (ExistingInvoiceException e1) {
                    JOptionPane.showMessageDialog(pop,"Factura ya existe","Aviso",JOptionPane.WARNING_MESSAGE);
                }
            }
        }
    }

    class EscuchadorRecuperarFacturas implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("Enviar")) {
                panelFinal.removeAll();
                try {
                    JLabel fac = new JLabel(controlador.facturaDatos(nif.getText().trim(),codFac.getText().trim()));
                    JScrollPane scroll = new JScrollPane(fac);
                    scroll.setPreferredSize(new Dimension(1000, 100));
                    panelFinal.add(scroll);
                    panelAbajo.add(panelFinal);
                    panelFinal.updateUI();
                    panelAbajo.updateUI();
                } catch (NotExistingInvoceException e1) {
                    JOptionPane.showMessageDialog(pop,"Factura no encontrada","Aviso",JOptionPane.WARNING_MESSAGE);
                } catch (NotExistingClientException e1) {
                    JOptionPane.showMessageDialog(pop,"Cliente no encontrado","Aviso",JOptionPane.WARNING_MESSAGE);
                }
            }
        }
    }

    class EscuchadorRecuperarTodasFacturas implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("Enviar")) {
                panelFinal.removeAll();
                try {
                    Collection<Factura> col = controlador.listaFacturas(nif.getText().trim()).values();
                    DefaultListModel<String> datos = new DefaultListModel<>();
                    for (Factura factura : col) {
                        datos.addElement(factura.toString());
                    }
                    JList<String> facturas = new JList<String>(datos);
                    JScrollPane scroll = new JScrollPane(facturas);
                    scroll.setPreferredSize(new Dimension(1000, 100));
                    facturas.setVisibleRowCount(4);
                    panelFinal.add(scroll);
                    panelAbajo.add(panelFinal);
                    scroll.updateUI();
                    panelFinal.updateUI();
                    panelAbajo.updateUI();
                } catch (NullListCallException e1) {
                    JOptionPane.showMessageDialog(pop,"Lista vacía","Aviso",JOptionPane.WARNING_MESSAGE);
                } catch (NotExistingClientException e1) {
                    JOptionPane.showMessageDialog(pop,"Cliente no encontrado","Aviso",JOptionPane.WARNING_MESSAGE);
                } catch (NullListInvoicesException e1) {
                    JOptionPane.showMessageDialog(pop,"Lista de facturas vacía","Aviso",JOptionPane.WARNING_MESSAGE);
                }
            }
        }
    }

    class EscuchadorRecuperarTodasFacturasEntreFechas implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Calendar fechaInicio = Calendar.getInstance();
            int anoLocal = Integer.parseInt(year.getText().trim());
            int mesLocal = Integer.parseInt(month.getText().trim());
            int diaLocal = Integer.parseInt(day.getText().trim());
            fechaInicio.set(anoLocal, mesLocal - 1, diaLocal);
            Calendar fechaFin = Calendar.getInstance();
            int anoLocal2 = Integer.parseInt(year2.getText().trim());
            int mesLocal2 = Integer.parseInt(month2.getText().trim());
            int diaLocal2 = Integer.parseInt(day2.getText().trim());
            fechaFin.set(anoLocal2, mesLocal2 - 1, diaLocal2);
            if (e.getActionCommand().equals("Enviar")) {
                panelFinal.removeAll();
                try {
                    Collection<Factura> col = controlador.mostrarListaFacturasEntreFechas(nif.getText().trim(), fechaInicio, fechaFin);
                    DefaultListModel<String> datos = new DefaultListModel<>();
                    for (Factura fac : col) {
                        datos.addElement(fac.toString());
                    }
                    JList<String> facturas = new JList<String>(datos);
                    JScrollPane scroll = new JScrollPane(facturas);
                    scroll.setPreferredSize(new Dimension(1000, 100));
                    facturas.setVisibleRowCount(4);
                    panelFinal.add(scroll);
                    panelAbajo.add(panelFinal);
                    scroll.updateUI();
                    panelFinal.updateUI();
                    panelAbajo.updateUI();
                } catch (IllegalPeriodException e1) {
                    JOptionPane.showMessageDialog(pop,"Fecha no válida","Error",JOptionPane.ERROR_MESSAGE);
                } catch (NotExistingClientException e1) {
                    JOptionPane.showMessageDialog(pop,"Cliente no encontrado","Aviso",JOptionPane.WARNING_MESSAGE);
                } catch (NullListCallException e1) {
                    JOptionPane.showMessageDialog(pop,"Lista de llamadas vacía","Aviso",JOptionPane.WARNING_MESSAGE);
                } catch (NullListInvoicesException e1) {
                    JOptionPane.showMessageDialog(pop,"Lista de facturas vacía","Aviso",JOptionPane.WARNING_MESSAGE);
                }
            }
        }
    }

}
