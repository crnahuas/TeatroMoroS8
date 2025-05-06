package sistemaventasteatromoro;

/**
 * Representa una reserva de un asiento realizada por un cliente.
 * Contiene información sobre el ID de la reserva, el cliente que reservó
 * y el asiento que fue reservado.
 */

public class Reserva {
    // Atributos
    private int idReserva;
    private Cliente cliente;
    private Asiento asiento;
    
    // Constructor
    public Reserva(int idReserva, Cliente cliente, Asiento asiento) {
       this.idReserva=idReserva;
       this.cliente=cliente;
       this.asiento=asiento;
    }
    
    // Métodos getter
    public int getIdReserva() {
        return idReserva;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Asiento getAsiento() {
        return asiento;
    }

    // Método toString    
    @Override
    public String toString() {
        return "Reserva{" + "idReserva=" + idReserva + ", cliente=" + cliente + ", asiento=" + asiento + '}';
    }
    
    
}
