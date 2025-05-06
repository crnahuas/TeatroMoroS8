package sistemaventasteatromoro;

/**
 * Representa una venta de una entrada para el teatro.
 * Contiene información sobre el ID de la venta, el asiento vendido,
 * el cliente que realizó la compra y el precio de la entrada.
 */

public class Venta {
     // Atributos
    private int idVenta;
    private Asiento asiento;
    private Cliente cliente;
    private double precio; 
    
    // Constructor
    public Venta(int idVenta, Asiento asiento, Cliente cliente, double precio){
        this.idVenta=idVenta;
        this.asiento=asiento;
        this.cliente=cliente;
        this.precio=precio;           
    }

    // Métodos getter    
    public int getIdVenta() {
        return idVenta;
    }

    public Asiento getAsiento() {
        return asiento;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public double getPrecio() {
        return precio;
    }

     // Método setter para el tipo    
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    // Método toString
    @Override
    public String toString() {
        return "Venta{" + "idVenta=" + idVenta + ", asiento=" + asiento + ", cliente=" + cliente + ", precio=" + precio + '}';
    }
 
}
