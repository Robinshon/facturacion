package cliente;

import java.util.Calendar;
import java.util.Date;

public class Factura {
    private String codigo;
    private Tarifa tarifa;
    private Calendar fechaEmision;
    private Calendar fechaInicio;
    private Calendar fechaFin;
    private double importe;

    public Factura(String codigo, Tarifa tarifa, Calendar fechaEmision, Calendar fechaInicio, Calendar fechaFin, double importe){
        this.codigo = codigo;
        this.tarifa = tarifa;
        this.fechaEmision = fechaEmision;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.importe = importe;
    }
    public String getCodigo() {
        return codigo;
    }
    public Calendar getFecha(){
        return fechaEmision;
    }

    public String getFechaEmisionNormal() {
        return fechaEmision.get(Calendar.DAY_OF_MONTH) + "#" + fechaEmision.get(Calendar.MONTH) + "#" + fechaEmision.get(Calendar.YEAR);
    }


    public Calendar getFechaInicio() {
        return fechaInicio;
    }
    public String getFechaInicioNormal() {
        return fechaInicio.get(Calendar.DAY_OF_MONTH) + "#" + fechaInicio.get(Calendar.MONTH) + "#" + fechaInicio.get(Calendar.YEAR);
    }

    public Calendar getFechaFin() {
        return fechaFin;
    }
    public String getFechaFinNormal() {
        return fechaFin.get(Calendar.DAY_OF_MONTH) + "#" + fechaFin.get(Calendar.MONTH) + "#" + fechaFin.get(Calendar.YEAR);
    }

    public double getImporte() {
        return importe;
    }
    public String getFactura(){
        return "Codigo factura: " + getCodigo() + " / " + "Tarifa: " + tarifa.getPrecioPorSegundo() + " / " + "Fecha emision: " + getFechaEmisionNormal() + " / " + "Fecha inicio: " + getFechaInicioNormal() + " / " + "Fecha final: " + getFechaFinNormal() + " / " + "Importe: " + getImporte();
    }



}
