package sistemaventasteatromoro;

/**
 * Gestiona la colección de asientos del teatro, utilizando un arreglo bidimensional
 * para representar la disposición de las filas (identificadas por letras) y columnas.
 * Proporciona funcionalidades para obtener, verificar la disponibilidad y modificar
 * el estado de los asientos.
 */

public class GestionAsientos {
    public Asiento[][] asientos;
    public String[] filasTeatro; 
    public int columnasTeatro;
    
    public GestionAsientos(String[] filas, int columnas) {
        this.filasTeatro=filas;
        this.columnasTeatro=columnas;
        this.asientos=new Asiento[filasTeatro.length][columnas];
        inicializarAsientos();
    }
    
    public void inicializarAsientos(){
        for (int i=0; i< filasTeatro.length; i++){
            for (int j=0; j < columnasTeatro; j++){
                this.asientos[i][j]= new Asiento(filasTeatro[i], j + 1);
            }
        }
    }
    
    public int obtenerIndiceFila(String fila){
        for(int i=0; i< filasTeatro.length; i++){
            if(filasTeatro[i].equalsIgnoreCase(fila)){
                return i;
            }
        }
        return -1; // Fila no encontrada
    }
    
    public Asiento obtenerAsiento(String fila, int numero){
        int indiceFila=obtenerIndiceFila(fila);
        if (indiceFila != -1 && numero >= 1 && numero <= columnasTeatro){
            return this.asientos[indiceFila][numero -1];
        }
        return null; // asiento inválido
    }
    
    public boolean existeAsiento(String fila, int numero){
        return obtenerAsiento(fila, numero) !=null;
    }
    
    public boolean estaDisponible(String fila, int numero){
        Asiento asiento=obtenerAsiento(fila, numero);
        return asiento != null && !asiento.isOcupado(); 
    }
    
    public boolean marcarOcupado(String fila, int numero) {
        Asiento asiento = obtenerAsiento(fila, numero);
        if (asiento != null && !asiento.isOcupado()) {
            asiento.setOcupado(true);
            return true;
        }
        return false;
    }

    public boolean marcarLibre(String fila, int numero) {
        Asiento asiento = obtenerAsiento(fila, numero);
        if (asiento != null && asiento.isOcupado()) {
            asiento.setOcupado(false);
            return true;
        }
        return false;
    }

    public void mostrarEstadoAsientos() {
        System.out.println("Estado de los Asientos:");
        for (int i = 0; i < filasTeatro.length; i++) {
            for (int j = 0; j < columnasTeatro; j++) {
                System.out.print((this.asientos[i][j].isOcupado() ? "[X]" : "[" + filasTeatro[i] + (j + 1) + "]") + " ");
            }
            System.out.println();
        }
    }
}
