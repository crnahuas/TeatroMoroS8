package sistemaventasteatromoro;

import java.util.ArrayList;
import java.util.List;

/**
 * Gestiona la colección de reservas de asientos realizadas por los clientes.
 * Utiliza una lista dinámica (ArrayList) para almacenar las reservas y proporciona
 * funcionalidades para crear nuevas reservas, cancelar reservas por su ID,
 * buscar reservas por su ID y (opcionalmente) listar todas las reservas.
 */

public class GestionReservas {
       // Atributos
    private List<Reserva> reservas;
    private int nextIdReserva;

    // Constructor
    public GestionReservas() {
        // Inicializa la lista de reservas
        this.reservas = new ArrayList<>();
        // Inicializa el ID de la próxima reserva
        this.nextIdReserva = 1;
    }

    // Método para crear una nueva reserva
    public void crearReserva(Cliente cliente, Asiento asiento) {
        // Crea un nuevo objeto Reserva con el siguiente ID disponible
        Reserva nuevaReserva = new Reserva(this.nextIdReserva++, cliente, asiento);
        // Agrega la nueva reserva a la lista
        this.reservas.add(nuevaReserva);
    }

    // Método para cancelar una reserva por su ID
    public void cancelarReserva(int idReserva) {
        // Itera sobre la lista y elimina la reserva con el ID coincidente
        this.reservas.removeIf(reserva -> reserva.getIdReserva() == idReserva);
    }

    // Método para buscar una reserva por su ID
    public Reserva buscarReserva(int idReserva) {
        // Itera sobre la lista y devuelve la reserva con el ID coincidente (si se encuentra)
        for (Reserva reserva : this.reservas) {
            if (reserva.getIdReserva() == idReserva) {
                return reserva;
            }
        }
        return null; // No se encontró ninguna reserva con ese ID
    }

    // (Opcional por ahora) Método para listar todas las reservas
    public List<Reserva> listarReservas() {
        return new ArrayList<>(this.reservas); // Devuelve una copia de la lista
    } 
}
