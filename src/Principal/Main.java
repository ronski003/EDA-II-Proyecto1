package Principal;

import Bug.Bug;
import Gestor.GestorBugs;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

/**
 * Implementa la interfaz de consola del administrador de bugs.
 *
 * Presenta las opciones disponibles, solicita y valida
 * datos de entrada, y muestra los resultados al usuario.
 *
 * Delega las operaciones de almacenamiento, consulta y
 * eliminación a GestorBugs.
 *
 * @see GestorBugs
 */
public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final GestorBugs gestor = new GestorBugs();

    /**
     * Punto de entrada de la aplicación.
     *
     * Ejecuta el menú principal de manera repetitiva hasta
     * que el usuario selecciona la opción de salida.
     *
     * @param args argumentos de línea de comandos;
     *             no se utilizan en esta aplicación.
     */

    public static void main(String[] args) {

        int opcion;

        do {
            mostrarMenu();
            opcion = leerEntero("Selecciona una opción: ", 0, 5);

            switch (opcion) {
                case 1 -> registrarBug();
                case 2 -> buscarBug();
                case 3 -> eliminarBug();
                case 4 -> listarBugs();
                case 5 -> mostrarCantidad();
                case 0 -> System.out.println("Goodbye ;)");
            }

        } while (opcion !=0);

        scanner.close();
    }

    /**
     * Muestra en consola las opciones disponibles
     * para administrar los reportes de bugs.
     */
    private static void mostrarMenu() {
        System.out.println("\n===== ANALISIS DE BUGS =====");
        System.out.println("1. Registrar bug");
        System.out.println("2. Buscar bug por ID");
        System.out.println("3. Eliminar bug por ID");
        System.out.println("4. Listar bugs");
        System.out.println("5. Contar bugs");
        System.out.println("0. Salir");
    }

    /**
     * Solicita un número entero dentro de un rango determinado.
     *
     * Valida que la entrada pueda convertirse a entero y que
     * su valor esté comprendido entre los límites indicados.
     * Repite la solicitud mientras la entrada sea inválida.
     *
     * @param mensaje texto que se muestra antes de la captura.
     * @param minimo valor mínimo permitido, inclusive.
     * @param maximo valor máximo permitido, inclusive.
     * @return número entero válido introducido por el usuario.
     */
    private static int leerEntero(
            String mensaje, int minimo, int maximo) {

        while (true) {
            System.out.print(mensaje);
            String entrada = scanner.nextLine().trim();

            try {
                int numero = Integer.parseInt(entrada);

                if (numero >= minimo && numero <= maximo) {
                    return numero;
                }

            } catch (NumberFormatException e) {
                // La entrada no representa un entero válido.
            }

            System.out.println("Entrada inválida. Intenta otra vez.");
        }
    }
    
    /**
     * Solicita una cadena de texto no vacía.
     *
     * Elimina los espacios al inicio y final de la entrada
     * y repite la captura si el resultado está vacío.
     *
     * @param mensaje texto mostrado al solicitar la entrada.
     * @return cadena de texto no vacía.
     */
    private static String leerTexto(String mensaje) {

        while (true) {
            System.out.print(mensaje);
            String texto = scanner.nextLine().trim();

            if (!texto.isEmpty()) {
                return texto;
            }

            System.out.println("El campo no puede estar vacío.");
        }
    }
    private static LocalDate leerFecha() {

        while (true) {
            String entrada = leerTexto("Fecha (AAAA-MM-DD): ");

            try {
                return LocalDate.parse(entrada);
            } catch (DateTimeParseException e) {
                System.out.println("Fecha inválida. Ejemplo: 2026-10-25");
            }
        }
    }

    //captura los datos de un bug y lo registra
    private static void registrarBug() {

        System.out.println("\n--- REGISTRAR BUG ---");

        int prioridad = leerEntero("Prioridad (1-3): ", 1, 3);
        int seguridad = leerEntero("Nivel de seguridad (1-5): ", 1, 5);

        System.out.println("Estado:");
        System.out.println("1. Resuelto");
        System.out.println("2. No resuelto");

        int opcionEstado = leerEntero("Elige estado: ", 1, 2);

        String estado = opcionEstado == 1
                ? "Resuelto" : "No resuelto";

        System.out.println("Categoría:");
        System.out.println("1. Interfaz");
        System.out.println("2. Seguridad");
        System.out.println("3. Logica");
        System.out.println("4. Rendimiento");

        int opcionCategoria = leerEntero("Elige categoría: ", 1, 4);

        String[] categorias = {
            "Interfaz", "Seguridad", "Logica", "Rendimiento"
        };

        String categoria = categorias[opcionCategoria - 1];
        LocalDate fecha = leerFecha();

        // El gestor asignará el identificador definitivo.
        Bug datos = new Bug(
            0, prioridad, seguridad, estado, categoria, fecha
        );

        Bug registrado = gestor.agregar(datos);

        System.out.println("\nBug registrado correctamente.");
        mostrarBug(registrado);
    }

    //Busca un bug apartir de su ID y los datos de la tabla hash
    private static void buscarBug() {

        System.out.println("\n--- BUSCAR BUG ---");

        int id = leerEntero("ID a buscar: ", 1, Integer.MAX_VALUE);

        Bug resultado = gestor.buscarPorID(id);

        if (resultado == null) {
            System.out.println("No existe un bug con ese ID.");
        } else {
            System.out.println("Bug encontrado:");
            mostrarBug(resultado);
        }
    }

    //Elimina un bug apartir de su ID
    private static void eliminarBug() {

        System.out.println("\n--- ELIMINAR BUG ---");

        int id = leerEntero(
            "ID a eliminar: ", 1, Integer.MAX_VALUE
        );

        boolean eliminado = gestor.eliminarPorID(id);

        if (eliminado) {
            System.out.println("Bug eliminado correctamente.");
        } else {
            System.out.println("No existe un bug con ese ID.");
        }
    }

    //Solo muestra hasta 30 bugs por registro
    private static void listarBugs() {

        System.out.println("\n--- LISTADO DE BUGS ---");

        List<Bug> bugs = gestor.listar();

        if (bugs.isEmpty()) {
            System.out.println("No hay bugs registrados.");
            return;
        }

        int limite = Math.min(bugs.size(), 30);

        for (int i = 0; i < limite; i++) {
            mostrarBug(bugs.get(i));
        }

        if (bugs.size() > limite) {
            System.out.println(
                "Mostrando " + limite + " de " + bugs.size()
                + " registros."
            );
        }
    }

    private static void mostrarCantidad() {
        System.out.println(
            "Total de bugs registrados: " + gestor.cantidad()
        );
    }

    //Muestra los atributos de un objeto bug
    private static void mostrarBug(Bug bug) {

        System.out.println("--------------------------");
        System.out.println("ID: " + bug.getID());
        System.out.println("Prioridad: " + bug.getPrioridad());
        System.out.println("Seguridad: " + bug.getNivelSeguridad());
        System.out.println("Estado: " + bug.getEstado());
        System.out.println("Categoría: " + bug.getCategoria());
        System.out.println("Fecha: " + bug.getFecha());
    }
}
