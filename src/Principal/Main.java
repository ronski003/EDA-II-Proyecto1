package Principal;

import Bug.Bug;
import Busquedas.BusquedaBinaria;
import Busquedas.BusquedaLineal;
import GeneradorBugs.GenerarBug;
import Gestor.GestorBugs;
import Ordenamientos.OrdenamientoHeap;
import Ordenamientos.OrdenamientoInsertion;
import Ordenamientos.OrdenamientoMerge;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
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

        GenerarBug generador = new GenerarBug();

        for (int i = 0; i < 200; i++) {
            gestor.agregar(generador.crearBug());
        }

        System.out.println(
            "Sistema inicializado con " + gestor.cantidad() + " bugs."
        );

        int opcion;

    do {
        mostrarMenu();
        opcion = leerEntero("Selecciona una opción: ", 0, 7);

        switch (opcion) {
            case 1 -> registrarBug();
            case 2 -> menuBusquedas();
            case 3 -> eliminarBug();
            case 4 -> listarBugs();
            case 5 -> mostrarCantidad();
            case 6 -> menuOrdenamientos();
            case 7 -> generarLote();
            case 0 -> System.out.println("Goodbye ;)");
        }

    } while (opcion != 0);
    scanner.close();
    }

    /**
     * Muestra el menú principal del administrador de bugs.
     */
    private static void mostrarMenu() {

        System.out.println("\n===== ADMINISTRADOR DE BUGS =====");
        System.out.println("1. Registrar bug");
        System.out.println("2. Buscar bugs");
        System.out.println("3. Eliminar bug");
        System.out.println("4. Listar todos");
        System.out.println("5. Cantidad de bugs");
        System.out.println("6. Ordenar bugs");
        System.out.println("7. Generar lote aleatorio");
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

    /**
     * Solicita una fecha válida con formato ISO AAAA-MM-DD.
     *
     * Intenta convertir el texto recibido a LocalDate
     * y repite la captura cuando el formato o fecha
     * proporcionados son inválidos.
     *
     * @return fecha válida introducida por el usuario.
     */
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

    /**
     * Captura los atributos de un nuevo reporte de bug.
     *
     * Solicita prioridad, nivel de seguridad, estado,
     * categoría y fecha. Construye un objeto provisional
     * y lo envía al gestor para obtener su ID definitivo.
     *
     * Finalmente muestra el registro creado.
     */
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

        String[] categorias = {"Interfaz", "Seguridad", "Logica", "Rendimiento"};

        String categoria = categorias[opcionCategoria - 1];
        LocalDate fecha = leerFecha();

        // El gestor asignará el identificador definitivo.
        Bug datos = new Bug(0, prioridad, seguridad, estado, categoria, fecha);

        Bug registrado = gestor.agregar(datos);

        System.out.println("\nBug registrado correctamente.");
        mostrarBug(registrado);
    }


    /**
     * Genera y registra un lote de bugs aleatorios.
     *
     * Inserta cada registro inmediatamente mediante GestorBugs,
     * evitando almacenar un lote adicional en memoria.
     *
     * Mide el tiempo total de generación e inserción.
     */
    private static void generarLote() {

        System.out.println("\n===== GENERAR LOTE ALEATORIO =====");
        System.out.println("1. 200 bugs");
        System.out.println("2. 1,000 bugs");
        System.out.println("3. 10,000 bugs");
        System.out.println("4. 100,000 bugs");
        System.out.println("5. 1,000,000 bugs");
        System.out.println("0. Cancelar");

        int opcion = leerEntero("Selecciona: ", 0, 5);

        if (opcion == 0) {
            return;
        }

        int[] cantidades = {
            200, 1000, 10000, 100000, 1000000
        };

        int cantidad = cantidades[opcion - 1];

        System.out.println(
            "\nSe agregarán " + cantidad
            + " bugs a los " + gestor.cantidad()
            + " existentes."
        );

        int confirmar = leerEntero(
            "¿Confirmar generación? (1=Sí, 0=No): ",
            0, 1
        );

        if (confirmar == 0) {
            System.out.println("Operación cancelada.");
            return;
        }

        GenerarBug generador = new GenerarBug();

        int registrados = 0;
        long inicio = System.nanoTime();

        for (int i = 0; i < cantidad; i++) {
            gestor.agregar(generador.crearBug());
            registrados++;
        }

        long fin = System.nanoTime();

        double segundos = (fin - inicio) / 1_000_000_000.0;

        System.out.println("\n--- RESULTADOS ---");
        System.out.println("Bugs agregados: " + registrados);
        System.out.println("Total almacenado: " + gestor.cantidad());
        System.out.printf("Tiempo total: %.4f segundos%n", segundos);
    }


    /**
     * Permite consultar bugs utilizando distintos criterios.
     *
     * El algoritmo empleado depende del atributo elegido:
     * hashing para ID, búsqueda lineal para prioridad y estado,
     * y búsqueda binaria para nivel de seguridad, categoría y fecha.
     */
    private static void menuBusquedas() {

        BusquedaLineal lineal = new BusquedaLineal();
        BusquedaBinaria binaria = new BusquedaBinaria();

        int opcion;

        do {
            System.out.println("\n===== BUSCAR BUGS =====");
            System.out.println("1. Buscar por ID");
            System.out.println("2. Buscar por prioridad");
            System.out.println("3. Buscar por estado");
            System.out.println("4. Buscar por nivel de seguridad");
            System.out.println("5. Buscar por categoría");
            System.out.println("6. Buscar por fecha");
            System.out.println("0. Regresar");

            opcion = leerEntero("Selecciona: ", 0, 6);

            if (opcion == 0) {
                break;
            }

            // Reutilizamos la búsqueda hash existente.
            if (opcion == 1) {
                buscarBug();
                continue;
            }

            ArrayList<Bug> lista = gestor.listar();
            ArrayList<Bug> resultados;

            switch (opcion) {

                case 2 -> {
                    int prioridad = leerEntero("Prioridad (1-3): ", 1, 3);

                    resultados = lineal.BusquedaLinealPrioridad(lista, prioridad);

                    mostrarResultados("Prioridad " + prioridad,resultados);
                }

                case 3 -> {
                    System.out.println("1. Resuelto");
                    System.out.println("2. No resuelto");

                    int estadoElegido = leerEntero("Estado: ", 1, 2);

                    String estado = estadoElegido == 1
                        ? "Resuelto" : "No resuelto";

                    resultados = lineal.BusquedaLinealEstado(lista, estado);

                    mostrarResultados("Estado: " + estado,resultados);
                }

                case 4 -> {
                    int seguridad = leerEntero(
                        "Nivel de seguridad (1-5): ", 1, 5);

                    resultados =
                        binaria.busquedaBinariaNivelSeguridad(lista, seguridad);

                    mostrarResultados("Nivel " + seguridad,resultados);
                }

                case 5 -> {
                    System.out.println("1. Interfaz");
                    System.out.println("2. Seguridad");
                    System.out.println("3. Logica");
                    System.out.println("4. Rendimiento");

                    int categoria = leerEntero("Categoría: ", 1, 4);

                    String[] categorias = {"Interfaz", "Seguridad","Logica", "Rendimiento"};

                    String seleccionada =
                        categorias[categoria - 1];

                    resultados = binaria.BusquedaBinariaCategoria(lista, seleccionada);

                    mostrarResultados("Categoría: " + seleccionada, resultados);
                }

                case 6 -> {
                    LocalDate fecha = leerFecha();

                    resultados = binaria.BusquedaBinariaFecha(lista, fecha);

                    mostrarResultados("Fecha: " + fecha,resultados);
                }
            }

        } while (opcion != 0);
    }

    /**
     * Solicita un identificador y consulta el reporte
     * correspondiente mediante GestorBugs.
     *
     * Muestra los atributos del bug encontrado o informa
     * al usuario cuando el identificador no existe.
     */
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

    /**
     * Solicita el identificador de un reporte y
     * pide al gestor que lo elimine.
     *
     * Informa si la eliminación fue exitosa o
     * si no existe un reporte con dicho ID.
     */
    private static void eliminarBug() {

        System.out.println("\n--- ELIMINAR BUG ---");

        int id = leerEntero("ID a eliminar: ", 1, Integer.MAX_VALUE);

        boolean eliminado = gestor.eliminarPorID(id);

        if (eliminado) {
            System.out.println("Bug eliminado correctamente.");
        } else {
            System.out.println("No existe un bug con ese ID.");
        }
    }
    
    /**
     * Muestra todos los reportes registrados, permitiendo
     * continuar la impresión en bloques de 30 elementos.
     */
    private static void listarBugs() {
        mostrarResultados("LISTADO DE BUGS", gestor.listar());
    }

    /**
     * Consulta y muestra el número total de reportes
     * de bugs almacenados actualmente.
     */
    private static void mostrarCantidad() {
        System.out.println("Total de bugs registrados: " + gestor.cantidad());
    }

    /**
     * Muestra los atributos de un reporte de bug en consola.
     *
     * @param bug objeto Bug cuyos datos se desea presentar.
     */
    private static void mostrarBug(Bug bug) {

        System.out.println("--------------------------");
        System.out.println("ID: " + bug.getID());
        System.out.println("Prioridad: " + bug.getPrioridad());
        System.out.println("Seguridad: " + bug.getNivelSeguridad());
        System.out.println("Estado: " + bug.getEstado());
        System.out.println("Categoría: " + bug.getCategoria());
        System.out.println("Fecha: " + bug.getFecha());
    }

    /**
     * Permite ordenar los reportes según el atributo elegido.
     *
     * Selecciona internamente el algoritmo apropiado sin
     * modificar el orden de almacenamiento del gestor.
     */
    private static void menuOrdenamientos() {

        OrdenamientoInsertion insertion = new OrdenamientoInsertion();
        OrdenamientoMerge merge = new OrdenamientoMerge();
        OrdenamientoHeap heap = new OrdenamientoHeap();

        int opcion;

        do {
            System.out.println("\n===== ORDENAR BUGS =====");
            System.out.println("1. ID ascendente");
            System.out.println("2. Prioridad ascendente");
            System.out.println("3. Prioridad descendente");
            System.out.println("4. Seguridad ascendente");
            System.out.println("5. Seguridad descendente");
            System.out.println("6. Fecha ascendente");
            System.out.println("7. Fecha descendente");
            System.out.println("8. Categoría A-Z");
            System.out.println("9. Estado Z-A");
            System.out.println("0. Regresar");

            opcion = leerEntero("Selecciona: ", 0, 9);

            if (opcion == 0) {
                break;
            }

            ArrayList<Bug> lista = gestor.listar();
            ArrayList<Bug> ordenados;
            String criterio;

            switch (opcion) {

                case 1 -> {
                    // Los IDs se registran de forma creciente.
                    ordenados = lista;
                    criterio = "ID ascendente";
                }
                case 2 -> {
                    ordenados = lista.size() <= 2000
                        ? insertion.ordenarMenor(lista)
                        : merge.ordenarMenor(lista);
                    criterio = "Prioridad ascendente";
                }
                case 3 -> {
                    ordenados = merge.ordenarMayor(lista);
                    criterio = "Prioridad descendente";
                }
                case 4 -> {
                    ordenados = heap.ordenarMenor(lista);
                    criterio = "Seguridad ascendente";
                }
                case 5 -> {
                    ordenados = heap.ordenarMayor(lista);
                    criterio = "Seguridad descendente";
                }
                case 6 -> {
                    ordenados = heap.ordenarFechaMenor(lista);
                    criterio = "Fecha ascendente";
                }
                case 7 -> {
                    ordenados = heap.ordenarFechaMayor(lista);
                    criterio = "Fecha descendente";
                }

                case 8 -> {
                    ordenados = heap.ordenarString(lista);
                    criterio = "Categoría A-Z";
                }

                case 9 -> {
                    ordenados = lista.size() <= 2000
                        ? insertion.ordenarString(lista)
                        : merge.ordenarString(lista);
                    criterio = "Estado Z-A";
                }

                default -> {
                    continue;
                }
            }

            mostrarResultados("Orden: " + criterio, ordenados);

        } while (opcion != 0);
    }
    
    /**
     * Muestra los resultados en bloques de hasta 30 registros.
     *
     * Permite al usuario decidir si desea continuar mostrando
     * los siguientes bloques hasta terminar la lista.
     *
     * @param descripcion criterio de búsqueda u ordenamiento.
     * @param resultados lista de bugs que se desea mostrar.
     */
    private static void mostrarResultados(
            String descripcion, List<Bug> resultados) {

        System.out.println("\n--- " + descripcion + " ---");
        System.out.println("Total de registros: " + resultados.size());

        if (resultados.isEmpty()) {
            System.out.println("No se encontraron registros.");
            return;
        }

        int indice = 0;
        final int TAMANO_BLOQUE = 30;

        while (indice < resultados.size()) {

            int fin = Math.min(
                indice + TAMANO_BLOQUE,
                resultados.size()
            );

            for (int i = indice; i < fin; i++) {
                mostrarBug(resultados.get(i));
            }

            indice = fin;

            System.out.println(
                "\nMostrados " + indice + " de "
                + resultados.size() + " registros."
            );

            if (indice < resultados.size()) {

                int continuar = leerEntero(
                    "¿Mostrar los siguientes 30? (1=Sí, 0=No): ",
                    0, 1
                );

                if (continuar == 0) {
                    System.out.println("Regresando al menú...");
                    break;
                }
            }
        }
    }

}
