import model.*;
import controller.*;
import view.Vista;

import java.util.Date;

public class Main {
    public static void main(String[] args) {
        // Crear localidades
        Localidad balcon2 = new Localidad("Balcón 2", 300.0, 100);
        Localidad platea = new Localidad("Platea", 600.0, 100);
        Localidad balcon1 = new Localidad("Balcón 1 o VIP", 1800.0, 100);

        Localidad[] localidades = {balcon2, platea, balcon1};

        // Configurar evento
        Evento evento = new Evento("Concierto Internacional", new Date(), localidades, 6);

        // Inicializar sistema de tickets
        SistemaDeTickets sistema = new SistemaDeTickets(evento);

        // Interacción con el usuario
        while (true) {
            Vista.mostrarMenu();
            int opcion = Vista.solicitarEntero();
            Vista.limpiarBuffer();

            switch (opcion) {
                case 1:
                    Vista.mostrarMensaje("Ingrese su nombre: ");
                    String nombre = Vista.solicitarEntrada();

                    Vista.mostrarMensaje("Ingrese su email: ");
                    String email = Vista.solicitarEntrada();

                    Vista.mostrarMensaje("Cantidad de boletos a comprar: ");
                    int boletos = Vista.solicitarEntero();

                    Vista.mostrarMensaje("Presupuesto máximo: ");
                    double presupuesto = Vista.solicitarDouble();
                    Vista.limpiarBuffer();

                    Comprador comprador = new Comprador(nombre, email, boletos, presupuesto);

                    Vista.mostrarMensaje("Seleccione la localidad (Balcón 2, Platea, Balcón 1 o VIP): ");
                    String localidad = Vista.solicitarEntrada();

                    sistema.comprarBoletos(comprador, localidad);
                    break;

                case 2:
                    sistema.consultarDisponibilidad();
                    break;

                case 3:
                    Vista.mostrarMensaje("Ingrese la localidad a consultar: ");
                    String locConsulta = Vista.solicitarEntrada();
                    sistema.consultarDisponibilidadIndividual(locConsulta);
                    break;

                case 4:
                    sistema.reporteDeCaja();
                    break;

                case 5:
                    sistema.salir();
                    break;

                default:
                    Vista.mostrarMensaje("Opción no válida. Intente de nuevo.");
                    break;
            }
        }
    }
}
