package sistemaventasteatromoro;

import java.util.ArrayList;
import java.util.List;

/**
 * Gestiona la colección de descuentos y promociones aplicables a las entradas.
 * Utiliza una lista dinámica (ArrayList) para almacenar los descuentos y proporciona
 * funcionalidades para agregar nuevos descuentos, obtener un descuento basado en el
 * tipo de cliente y (opcionalmente) listar todos los descuentos disponibles.
 */

public class GestionDescuentos {
   // Atributos
    private List<Descuento> descuentos;

    // Constructor
    public GestionDescuentos() {
        // Inicializa la lista de descuentos
        this.descuentos = new ArrayList<>();
        // Agrega los descuentos predefinidos
        inicializarDescuentos();
    }

    private void inicializarDescuentos() {
        this.descuentos.add(new Descuento("Estudiante", 0.10));
        this.descuentos.add(new Descuento("Tercera Edad", 0.15));
    }

    // Método para agregar un nuevo descuento
    public void agregarDescuento(String tipo, double porcentaje) {
        this.descuentos.add(new Descuento(tipo, porcentaje));
    }

    // Método para obtener un descuento basado en el tipo de cliente
    public Descuento obtenerDescuento(String tipoCliente) {
        for (Descuento descuento : this.descuentos) {
            if (descuento.getTipo().equalsIgnoreCase(tipoCliente)) {
                return descuento;
            }
        }
        return null; // No se encontró descuento para este tipo de cliente
    }

    // (Opcional por ahora) Método para listar todos los descuentos
    public List<Descuento> listarDescuentos() {
        return new ArrayList<>(this.descuentos); // Devuelve una copia de la lista
    } 
}
