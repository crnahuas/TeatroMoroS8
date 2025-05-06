package sistemaventasteatromoro;

import java.util.Scanner;
import java.util.List;

/**
 * Clase principal que inicia y controla el sistema de ventas del Teatro Moro.
 * Proporciona una interfaz de usuario por consola para interactuar con las
 * funcionalidades de gestión de asientos, clientes, ventas y reservas.
 */
public class SistemaVentasTeatroMoro {
    
    private static int obtenerNumeroAsientoValido(Scanner scanner, int maxColumnas) {
        int numeroAsiento = -1;
        while (numeroAsiento == -1) {
            System.out.print("Ingrese el número del asiento (1-" + maxColumnas + "): ");
            if (scanner.hasNextInt()) {
                numeroAsiento = scanner.nextInt();
                scanner.nextLine();
                if (numeroAsiento < 1 || numeroAsiento > maxColumnas) {
                    System.out.println("Número de asiento inválido (1-" + maxColumnas + ").");
                    numeroAsiento = -1; // Resetear para repetir la entrada
                }
            } else {
                System.out.println("Entrada inválida. Debe ser un número.");
                scanner.nextLine(); // Limpiar entrada incorrecta
            }
        }
        return numeroAsiento;
    }

    private static String obtenerFilaAsientoValida(Scanner scanner, String[] filasValidas) {
        String filaAsiento = "";
        boolean filaValida = false;
        while (!filaValida) {
            System.out.print("Ingrese la fila del asiento (" + String.join("-", filasValidas) + "): ");
            filaAsiento = scanner.nextLine().toUpperCase();
            for (String fila : filasValidas) {
                if (fila.equals(filaAsiento)) {
                    filaValida = true;
                    break;
                }
            }
            if (!filaValida) {
                System.out.println("Fila inválida (" + String.join("-", filasValidas) + ").");
            }
        }
        return filaAsiento;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Instancia de las clases de gestión (menos asientos)
        GestionAsientos gestionAsientos = new GestionAsientos(new String[]{"A", "B", "C", "D", "E"}, 10);
        GestionClientes gestionClientes = new GestionClientes();
        GestionDescuentos gestionDescuentos = new GestionDescuentos();
        GestionVentas gestionVentas = new GestionVentas(gestionDescuentos);
        GestionReservas gestionReservas = new GestionReservas();

        int opcion;

        do {
            System.out.println("\n--- Bienvenido al Sistema de Ventas y Reservas del Teatro Moro ---\n");
            System.out.println("--- Gestión de Asientos ---");
            System.out.println("1. Mostrar Estado de Asientos");
            System.out.println("2. Realizar Venta");
            System.out.println("3. Crear Reserva");
            System.out.println("4. Cancelar Reserva");

            System.out.println("\n--- Gestión de Clientes ---");
            System.out.println("5. Agregar Cliente");
            System.out.println("6. Listar Clientes");

            System.out.println("\n--- Reportes ---");
            System.out.println("7. Listar Ventas");
            System.out.println("8. Listar Reservas");

            System.out.println("\n--- General ---");
            System.out.println("9. Ayuda");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            if (scanner.hasNextInt()) {
                opcion = scanner.nextInt();
                scanner.nextLine(); // Consumir la nueva línea

                switch (opcion) {
                    case 1:
                        gestionAsientos.mostrarEstadoAsientos();
                        break;
                    case 2:
                        System.out.println("\n--- Realizar Venta ---");
                        gestionAsientos.mostrarEstadoAsientos();

                        String filaVenta = obtenerFilaAsientoValida(scanner, gestionAsientos.filasTeatro);
                        int numeroAsientoVenta = obtenerNumeroAsientoValido(scanner, gestionAsientos.columnasTeatro);

                        if (gestionAsientos.existeAsiento(filaVenta, numeroAsientoVenta)) {
                            if (gestionAsientos.estaDisponible(filaVenta, numeroAsientoVenta)) {
                                Asiento asientoVenta = gestionAsientos.obtenerAsiento(filaVenta, numeroAsientoVenta);
                                System.out.print("Ingrese el ID del cliente (0 si no aplica): ");
                                int idClienteVenta = scanner.nextInt();
                                scanner.nextLine();
                                Cliente clienteVenta = (idClienteVenta > 0) ? gestionClientes.buscarCliente(idClienteVenta) : null;

                                gestionAsientos.marcarOcupado(filaVenta, numeroAsientoVenta);
                                Venta nuevaVenta = gestionVentas.registrarVenta(asientoVenta, clienteVenta, 20000.0);
                                System.out.println("\n--- Información de la Venta ---");
                                System.out.println("Asiento: " + nuevaVenta.getAsiento().getFila() + nuevaVenta.getAsiento().getNumero());
                                if (nuevaVenta.getCliente() != null) {
                                    System.out.println("Cliente: " + nuevaVenta.getCliente().getNombre() + " (ID: " + nuevaVenta.getCliente().getIdCliente() + ")");
                                } else {
                                    System.out.println("Cliente: No se aplicó cliente.");
                                }
                                System.out.println("Precio Final: $" + String.format("%.2f", nuevaVenta.getPrecio()));
                                System.out.println("Venta realizada con éxito.");
                            } else {
                                System.out.println("El asiento " + filaVenta + numeroAsientoVenta + " ya no está disponible.");
                            }
                        } else {
                            System.out.println("El asiento " + filaVenta + numeroAsientoVenta + " no existe.");
                        }
                        break;
                    case 3:
                        System.out.println("\n--- Crear Reserva ---");
                        gestionAsientos.mostrarEstadoAsientos();

                        int idClienteReserva = -1;
                        Cliente clienteReserva = null;
                        boolean clienteValidoReserva = false;

                        while (!clienteValidoReserva) {
                            System.out.print("Ingrese el ID del cliente para la reserva (0 si no aplica): ");
                            if (scanner.hasNextInt()) {
                                idClienteReserva = scanner.nextInt();
                                scanner.nextLine();
                                if (idClienteReserva == 0) {
                                    clienteValidoReserva = true;
                                } else {
                                    clienteReserva = gestionClientes.buscarCliente(idClienteReserva);
                                    if (clienteReserva != null) {
                                        clienteValidoReserva = true;
                                    } else {
                                        System.out.println("ID de cliente no encontrado.");
                                    }
                                }
                            } else {
                                System.out.println("ID de cliente inválido. Ingrese un número.");
                                scanner.nextLine();
                            }
                        }

                        String filaReserva = obtenerFilaAsientoValida(scanner, gestionAsientos.filasTeatro);
                        int numeroAsientoReserva = obtenerNumeroAsientoValido(scanner, gestionAsientos.columnasTeatro);

                        if (gestionAsientos.existeAsiento(filaReserva, numeroAsientoReserva)) {
                            if (gestionAsientos.estaDisponible(filaReserva, numeroAsientoReserva)) {
                                Asiento asientoReserva = gestionAsientos.obtenerAsiento(filaReserva, numeroAsientoReserva);
                                gestionReservas.crearReserva(clienteReserva, asientoReserva);
                                gestionAsientos.marcarOcupado(filaReserva, numeroAsientoReserva);
                                System.out.println("Reserva creada para el asiento " + filaReserva + numeroAsientoReserva + ".");
                            } else {
                                System.out.println("El asiento " + filaReserva + numeroAsientoReserva + " ya no está disponible.");
                            }
                        } else {
                            System.out.println("El asiento " + filaReserva + numeroAsientoReserva + " no existe.");
                        }
                        break;
                    case 4:
                        System.out.print("Ingrese el ID de la reserva a cancelar: ");
                        if (scanner.hasNextInt()) {
                            int idReservaCancelar = scanner.nextInt();
                            scanner.nextLine();
                            Reserva reservaCancelada = gestionReservas.buscarReserva(idReservaCancelar);
                            if (reservaCancelada != null) {
                                gestionReservas.cancelarReserva(idReservaCancelar);
                                gestionAsientos.marcarLibre(reservaCancelada.getAsiento().getFila(), reservaCancelada.getAsiento().getNumero());
                                System.out.println("Reserva con ID " + idReservaCancelar + " cancelada.");
                            } else {
                                System.out.println("Reserva con ID " + idReservaCancelar + " no encontrada.");
                            }
                        } else {
                            System.out.println("ID de reserva inválido. Ingrese un número.");
                            scanner.nextLine(); // Limpiar entrada incorrecta
                        }
                        break;
                    case 5:
                        System.out.println("\n--- Agregar Cliente ---");
                        System.out.print("Ingrese el nombre del cliente: ");
                        String nombreCliente;
                        while (!(nombreCliente = scanner.nextLine()).matches("[a-zA-Z\\s]+")) {
                            System.out.println("Nombre inválido. Solo se permiten letras y espacios.");
                            System.out.print("Ingrese el nombre del cliente: ");
                        }

                        String tipoCliente = "";
                        boolean tipoValido = false;
                        while (!tipoValido) {
                            System.out.print("Ingrese el tipo de cliente (Estudiante, Tercera Edad, General): ");
                            tipoCliente = scanner.nextLine();
                            if (tipoCliente.equalsIgnoreCase("Estudiante")
                                    || tipoCliente.equalsIgnoreCase("Tercera Edad")
                                    || tipoCliente.equalsIgnoreCase("General")) {
                                tipoValido = true;
                            } else {
                                System.out.println("Tipo de cliente inválido. Debe ser Estudiante, Tercera Edad o General.");
                            }
                        }

                        Cliente nuevoCliente = gestionClientes.agregarCliente(nombreCliente, tipoCliente);
                        System.out.println("\n--- Cliente Agregado ---");
                        System.out.println("ID: " + nuevoCliente.getIdCliente());
                        System.out.println("Nombre: " + nuevoCliente.getNombre());
                        System.out.println("Tipo: " + nuevoCliente.getTipo());
                        System.out.println("Cliente registrado con éxito.");
                        break;
                    case 6:
                        System.out.println("\n--- Listado de Clientes con ID ---");
                        List<Cliente> clientes = gestionClientes.listarClientes();
                        if (clientes.isEmpty()) {
                            System.out.println("No hay clientes registrados.");
                        } else {
                            for (Cliente cliente : clientes) {
                                System.out.println("ID: " + cliente.getIdCliente() + ", Nombre: " + cliente.getNombre() + ", Tipo: " + cliente.getTipo());
                            }
                        }
                        break;
                    case 7:
                        System.out.println("\n--- Listado de Ventas ---");
                        List<String> ventasFormateadas = gestionVentas.listarVentasFormateadas();
                        if (ventasFormateadas.isEmpty()) {
                            System.out.println("No hay ventas registradas.");
                        } else {
                            for (String ventaInfo : ventasFormateadas) {
                                System.out.println(ventaInfo);
                            }
                        }
                        break;
                    case 8:
                        System.out.println("\n--- Listado de Reservas ---");
                        List<Reserva> reservas = gestionReservas.listarReservas();
                        if (reservas.isEmpty()) {
                            System.out.println("No hay reservas registradas.");
                        } else {
                            for (Reserva reserva : reservas) {
                                System.out.println("ID de Reserva: " + reserva.getIdReserva()
                                        + ", Cliente: " + (reserva.getCliente() != null ? reserva.getCliente().getNombre() : "Sin Cliente")
                                        + ", Asiento: " + reserva.getAsiento().getFila() + reserva.getAsiento().getNumero());
                            }
                        }
                        break;
                    case 9:
                        System.out.println("\n--- Ayuda ---");
                        System.out.println("Este es el sistema de ventas del Teatro Moro.\n");
                        System.out.println("--- Gestión de Asientos ---");
                        System.out.println("1. **Mostrar Estado de Asientos:** Muestra la disponibilidad de los asientos en el teatro.");
                        System.out.println("2. **Realizar Venta:** Permite vender un asiento.");
                        System.out.println("3. **Crear Reserva:** Permite reservar un asiento para un cliente.");
                        System.out.println("4. **Cancelar Reserva:** Permite cancelar una reserva existente.");

                        System.out.println("\n--- Gestión de Clientes ---");
                        System.out.println("5. **Agregar Cliente:** Permite registrar un nuevo cliente.");
                        System.out.println("6. **Listar Clientes:** Muestra la lista de clientes con sus identificadores.");

                        System.out.println("\n--- Reportes ---");
                        System.out.println("7. **Listar Ventas:** Muestra la lista de todas las ventas realizadas.");
                        System.out.println("8. **Listar Reservas:** Muestra la lista de todas las reservas activas con sus IDs.");

                        System.out.println("\n--- General ---");
                        System.out.println("0. **Salir:** Cierra la aplicación.");
                        break;
                    case 0:
                        System.out.println("Saliendo del sistema.");
                        break;
                    default:
                        System.out.println("Opción inválida. Ingrese un número del menú.");
                }
            } else {
                System.out.println("Opción inválida. Ingrese un número del menú.");
                scanner.nextLine(); // Limpiar entrada incorrecta
                opcion = -1; // Para que el bucle continúe
            }
        } while (opcion != 0);

        scanner.close();
    }
}
