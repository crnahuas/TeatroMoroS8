package sistemaventasteatromoro;

/**
 * Representa un asiento individual dentro del teatro.
 * Contiene información sobre su fila, número y estado de ocupación.
 */

public class Asiento {
    // Atributos
    private String fila;
    private int numero;
    boolean ocupado;
    
    // Constructor
    public Asiento(String fila, int numero) {
        // Inicializa los atributos
        this.fila=fila;
        this.numero=numero;
        this.ocupado=false; // Inicialmente, todos los asientos están libres
    } 
    
    // Métodos getter
    public String getFila() {
        return fila;
    }

    public int getNumero() {
        return numero;
    }

    public boolean isOcupado() {
        return ocupado;
    }
    
    // Métodos setter
    public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }
    
    // Método toString
    @Override
    public String toString() {
        return "Asiento{" + "fila=" + fila + ", numero=" + numero + ", ocupado=" + ocupado + '}';
    }
    
}
