package sistemaventasteatromoro;

import java.util.ArrayList;
import java.util.List;

/**
 * Gestiona la colección de ventas de entradas realizadas. Utiliza una lista
 * dinámica (ArrayList) para almacenar las ventas y se encarga de registrar
 * nuevas ventas, aplicando los descuentos correspondientes basados en el tipo
 * de cliente, utilizando una instancia de la clase GestionDescuentos.
 */
public class GestionVentas {

    // Atributos
    private List<Venta> ventas;
    private int nextIdVenta;
    private GestionDescuentos gestionDescuentos;

    // Constructor
    public GestionVentas(GestionDescuentos gestionDescuentos) {
        // Inicializa la lista de ventas
        this.ventas = new ArrayList<>();
        // Inicializa el ID de la próxima venta
        this.nextIdVenta = 1;
        // Recibe la instancia de GestionDescuentos
        this.gestionDescuentos = gestionDescuentos;
    }

    // Método para registrar una nueva venta
    public Venta registrarVenta(Asiento asiento, Cliente cliente, double precioBase) {
        // Calcula el precio final aplicando descuentos
        double precioFinal = precioBase;
        if (cliente != null) {
            Descuento descuento = gestionDescuentos.obtenerDescuento(cliente.getTipo());
            if (descuento != null) {
                precioFinal = precioBase * (1 - descuento.getPorcentaje());
            }
        }

        // Crea un nuevo objeto Venta
        Venta nuevaVenta = new Venta(this.nextIdVenta++, asiento, cliente, precioFinal);
        // Agrega la venta a la lista
        this.ventas.add(nuevaVenta);
        // Retorna la nueva venta creada
        return nuevaVenta;
    }

    // Listar todas las ventas con formato legible
    public List<String> listarVentasFormateadas() {
        List<String> listaVentasFormateadas = new ArrayList<>();
        for (Venta venta : this.ventas) {
            String clienteInfo = (venta.getCliente() != null)
                    ? "Cliente: " + venta.getCliente().getNombre() + " (ID: " + venta.getCliente().getIdCliente() + ")"
                    : "Cliente: No se aplicó cliente";
            listaVentasFormateadas.add("ID de Venta: " + venta.getIdVenta()
                    + ", Asiento: " + venta.getAsiento().getFila() + venta.getAsiento().getNumero()
                    + ", " + clienteInfo
                    + ", Precio: $" + String.format("%.2f", venta.getPrecio()));
        }
        return listaVentasFormateadas;
    }

    // Listar todas las ventas (sin formato, para uso interno si es necesario)
    public List<Venta> listarVentas() {
        return new ArrayList<>(this.ventas); // Devuelve una copia de la lista
    }
}
