package facturas;

import fechas.EntreFechas;
import tarifas.Tarifa;

import java.io.Serializable;
import java.util.Calendar;

public class Factura extends EntreFechas implements Serializable {
    private static final long serialVersionUID = 42L;
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

    @Override
    public Calendar dameFecha(){
        return fechaEmision;
    }

    public String getFecha() {
        return fechaEmision.get(Calendar.DAY_OF_MONTH) + "#" + fechaEmision.get(Calendar.MONTH) + "#" + fechaEmision.get(Calendar.YEAR);
    }



    public String getFechaInicio() {
        return fechaInicio.get(Calendar.DAY_OF_MONTH) + "#" + fechaInicio.get(Calendar.MONTH) + "#" + fechaInicio.get(Calendar.YEAR);
    }


    public String getFechaFin() {
        return fechaFin.get(Calendar.DAY_OF_MONTH) + "#" + fechaFin.get(Calendar.MONTH) + "#" + fechaFin.get(Calendar.YEAR);
    }

    public double getImporte() {
        return importe;
    }

    @Override
    public String toString(){
        return "Codigo factura: " + getCodigo() + " / " + "Tarifa: " + tarifa.getPrecioPorSegundo() + " / " + "Fecha emision: " + getFecha() + " / " + "Fecha inicio: " + getFechaInicio() + " / " + "Fecha final: " + getFechaFin() + " / " + "Importe: " + getImporte();
    }



}
