package sistemaventasteatromoro;

/**
 * Representa a un cliente del teatro.
 * Contiene información sobre su ID único, nombre y tipo (para descuentos).
 */

public class Cliente {
    private int idCliente;
    private String nombre;
    private String tipo;
    
    // Constructor
    public Cliente(int idCliente, String nombre, String tipo) {
        // Inicializa los atributos
        this.idCliente=idCliente;
        this.nombre=nombre;
        this.tipo=tipo;
    } 
    
    // Métodos getter
    public int getIdCliente() {
        return idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTipo() {
        return tipo;
    }
    
    // Método setter para el tipo
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    // Método toString
    @Override
    public String toString() {
        return "Cliente{" + "idCliente=" + idCliente + ", nombre=" + nombre + ", tipo=" + tipo + '}';
    }
   
}
