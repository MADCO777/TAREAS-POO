package controller;

import model.*;
import view.Vista;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SistemaDeTickets {
    private Evento evento;
    private int correlativo;

    public SistemaDeTickets(Evento evento) {
        this.evento = evento;
        this.correlativo = 1;
    }

    public void comprarBoletos(Comprador comprador, String localidadNombre) {
        Localidad localidad = evento.getLocalidadPorNombre(localidadNombre);

        if (localidad == null) {
            Vista.mostrarMensaje("La localidad no existe.");
            return;
        }

        // Validar presupuesto
        if (comprador.getPresupuesto() < localidad.getPrecio() * comprador.getBoletosComprados()) {
            Vista.mostrarMensaje("Presupuesto insuficiente para esta compra.");
            return;
        }

        // Validar disponibilidad
        int boletosDisponibles = localidad.getDisponibles();
        int boletosSolicitados = Math.min(comprador.getBoletosComprados(), evento.getLocalidades().length);

        if (boletosDisponibles < boletosSolicitados) {
            Vista.mostrarMensaje(String.format("Solo hay %d boletos disponibles en %s. Compra ajustada a la disponibilidad.", boletosDisponibles, localidad.getNombre()));
            boletosSolicitados = boletosDisponibles;
        }

        // Validar límite de boletos por compra
        if (boletosSolicitados > evento.getLocalidades().length) {
            Vista.mostrarMensaje(String.format("El máximo de boletos permitidos por compra es %d. ", evento.getLocalidades().length));
            boletosSolicitados = evento.getLocalidades().length;
        }

        // Realizar la venta
        if (localidad.venderBoletos(boletosSolicitados)) {
            double totalVenta = boletosSolicitados * localidad.getPrecio();
            evento.incrementarVentas(totalVenta);
            String numeroTicket = generarNumeroTicket();
            Vista.mostrarMensaje(String.format("Compra exitosa. Ticket número: %s", numeroTicket));
            correlativo++;
        } else {
            Vista.mostrarMensaje("No se pudo completar la venta.");
        }
    }

    private String generarNumeroTicket() {
        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
        String fechaActual = sdf.format(new Date());
        return fechaActual + correlativo;
    }

    public void consultarDisponibilidad() {
        for (Localidad localidad : evento.getLocalidades()) {
            Vista.mostrarMensaje(String.format("%s - Disponibles: %d", localidad.getNombre(), localidad.getDisponibles()));
        }
    }

    public void consultarDisponibilidadIndividual(String localidadNombre) {
        Localidad localidad = evento.getLocalidadPorNombre(localidadNombre);

        if (localidad != null) {
            Vista.mostrarMensaje(String.format("%s - Disponibles: %d", localidad.getNombre(), localidad.getDisponibles()));
        } else {
            Vista.mostrarMensaje("Localidad no encontrada.");
        }
    }

    public void reporteDeCaja() {
        Vista.mostrarMensaje(String.format("Total de ventas: Q%.2f", evento.getTotalVentas()));
    }

    public void salir() {
        Vista.mostrarMensaje("Gracias por utilizar el sistema de tickets.");
        System.exit(0);
    }
}
