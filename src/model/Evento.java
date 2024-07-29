package model;

import java.util.Date;

public class Evento {
    private String nombre;
    private Date fecha;
    private Localidad[] localidades;
    private double totalVentas;
    private int maxBoletosPorCompra;

    public Evento(String nombre, Date fecha, Localidad[] localidades, int maxBoletosPorCompra) {
        this.nombre = nombre;
        this.fecha = fecha;
        this.localidades = localidades;
        this.totalVentas = 0;
        this.maxBoletosPorCompra = maxBoletosPorCompra;
    }

    public Localidad[] getLocalidades() {
        return localidades;
    }

    public Localidad getLocalidadPorNombre(String nombre) {
        for (Localidad localidad : localidades) {
            if (localidad.getNombre().equalsIgnoreCase(nombre)) {
                return localidad;
            }
        }
        return null;
    }

    public double getTotalVentas() {
        return totalVentas;
    }

    public void incrementarVentas(double monto) {
        totalVentas += monto;
    }
}
