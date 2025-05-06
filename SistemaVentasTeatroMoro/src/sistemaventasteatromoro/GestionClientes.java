package sistemaventasteatromoro;

import java.util.ArrayList;
import java.util.List;

/**
 * Gestiona la colección de clientes del teatro, utilizando una lista dinámica
 * (ArrayList). Proporciona funcionalidades para agregar nuevos clientes, buscar
 * clientes por su ID y (opcionalmente) listar a todos los clientes registrados.
 */

public class GestionClientes {
    private List<Cliente> clientes;
    private int nextIdCliente;

    public GestionClientes() {
        this.clientes = new ArrayList<>();
        this.nextIdCliente = 1;
    }

    // Agrega un nuevo cliente al sistema y lo retorna.
    public Cliente agregarCliente(String nombre, String tipo) {
        Cliente nuevoCliente = new Cliente(this.nextIdCliente++, nombre, tipo);
        this.clientes.add(nuevoCliente);
        return nuevoCliente; // Retorna el cliente creado
    }


    // Busca un cliente en la lista por su ID.
    public Cliente buscarCliente(int idCliente) {
        for (Cliente cliente : this.clientes) {
            if (cliente.getIdCliente() == idCliente) {
                return cliente;
            }
        }
        return null;
    }


    // lista que contiene todos los objetos Cliente.
    public List<Cliente> listarClientes() {
        return new ArrayList<>(this.clientes);
    }
}
