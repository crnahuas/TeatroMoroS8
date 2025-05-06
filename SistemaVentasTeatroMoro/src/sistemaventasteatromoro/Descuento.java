package sistemaventasteatromoro;

/**
 * Representa un descuento o promoción que se puede aplicar al precio de una entrada.
 * Contiene información sobre el tipo de descuento y el porcentaje que aplica.
 */

public class Descuento {
    // Atributos
    private String tipo;
    private double porcentaje;
    
    // Constructor
    public Descuento(String tipo, double porcentaje){
        this.tipo=tipo;
        this.porcentaje=porcentaje;
    } 
    
    // Métodos getter
    public String getTipo() {
        return tipo;
    }

    public double getPorcentaje() {
        return porcentaje;
    }
    
    // Método toString
    @Override
    public String toString() {
        return "Descuento{" + "tipo=" + tipo + ", porcentaje=" + porcentaje + '}';
    }
 
    
}
