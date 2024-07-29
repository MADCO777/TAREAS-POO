package model;

public class Comprador {
    private String nombre;
    private String email;
    private int boletosComprados;
    private double presupuesto;

    public Comprador(String nombre, String email, int boletosComprados, double presupuesto) {
        this.nombre = nombre;
        this.email = email;
        this.boletosComprados = boletosComprados;
        this.presupuesto = presupuesto;
    }

    public int getBoletosComprados() {
        return boletosComprados;
    }

    public double getPresupuesto() {
        return presupuesto;
    }
}
