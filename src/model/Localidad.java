package model;

public class Localidad {
    private String nombre;
    private double precio;
    private int capacidad;
    private int vendidos;

    public Localidad(String nombre, double precio, int capacidad) {
        this.nombre = nombre;
        this.precio = precio;
        this.capacidad = capacidad;
        this.vendidos = 0;
    }

    public int getDisponibles() {
        return capacidad - vendidos;
    }

    public boolean venderBoletos(int cantidad) {
        if (cantidad <= getDisponibles()) {
            vendidos += cantidad;
            return true;
        }
        return false;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }
}
