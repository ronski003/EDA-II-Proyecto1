package Gestor;

import Bug.Bug;
import Hash.GeneradorID;
import Hash.TablaHashBugs;
import java.util.ArrayList;

/**
 * Administra el registro, consulta, eliminación y listado
 * de reportes de bugs.
 *
 * Coordina una colección principal de objetos Bug, un
 * generador de identificadores únicos y una tabla hash
 * que permite localizar registros mediante su ID.
 *
 * Centraliza las operaciones de gestión para evitar que
 * la interfaz principal manipule directamente las
 * estructuras de almacenamiento.
 *
 * @see Bug
 * @see GeneradorID
 * @see TablaHashBugs
 */
public class GestorBugs {

    private ArrayList<Bug> bugs;
    private TablaHashBugs tablaHash;
    private GeneradorID generadorID;



    /**
     * Inicializa las estructuras necesarias para administrar
     * los reportes de bugs.
     *
     * Crea una lista principal vacía, una tabla hash con
     * capacidad inicial de 101 cubetas y un generador
     * de identificadores.
     */
    public GestorBugs() {
        bugs = new ArrayList<>();
        tablaHash = new TablaHashBugs(101);
        generadorID = new GeneradorID();
    }

    /**
     * Registra un nuevo reporte de bug.
     *
     * Solicita un identificador único al generador y construye
     * un nuevo objeto Bug conservando los atributos recibidos.
     *
     * Inserta el objeto en la tabla hash y posteriormente
     * en la colección principal.
     *
     * @param datos objeto con los atributos del reporte
     *              que se desea registrar.
     * @return nuevo objeto Bug con su ID definitivo.
     * @throws IllegalArgumentException si datos es null.
     * @throws IllegalStateException si no puede registrarse
     *         el bug en la tabla hash.
     */
    public Bug agregar(Bug datos) {

        if (datos == null) {
            throw new IllegalArgumentException("El bug no puede ser null");
        }

        int id = generadorID.generarID();

        // Creamos un nuevo objeto para no alterar
        // el ID provisional del objeto recibido.
        Bug nuevo = new Bug(
            id,
            datos.getPrioridad(),
            datos.getNivelSeguridad(),
            datos.getEstado(),
            datos.getCategoria(),
            datos.getFecha()
        );

        if (!tablaHash.insertar(nuevo)) {
            throw new IllegalStateException("No se pudo registrar el bug");
        }

        bugs.add(nuevo);

        return nuevo;
    }

    /**
     * Busca un bug mediante su identificador único.
     *
     * Delega la consulta a la tabla hash utilizada
     * como índice de los reportes registrados.
     *
     * @param id identificador del bug solicitado.
     * @return el bug encontrado o null si no existe.
     */
    public Bug buscarPorID(int id) {
        return tablaHash.buscar(id);
    }

    /**
     * Elimina un reporte de ambas estructuras de almacenamiento.
     *
     * Primero elimina el registro de la tabla hash y luego
     * retira el elemento correspondiente de la lista principal.
     *
     * @param id identificador del bug que se desea eliminar.
     * @return true si se eliminó correctamente;
     *         false si no existe un registro con ese ID.
     * @throws IllegalStateException si existe una
     *         inconsistencia entre la tabla hash y la lista.
     */
    public boolean eliminarPorID(int id) {

        if (!tablaHash.eliminar(id)) {
            return false;
        }

        for (int i = 0; i < bugs.size(); i++) {

            if (bugs.get(i).getID() == id) {
                bugs.remove(i);
                return true;
            }
        }

        throw new IllegalStateException(
            "Inconsistencia entre lista y tabla hash"
        );
    }

    /**
     * Obtiene una lista con todos los bugs registrados.
     *
     * Devuelve una nueva colección para permitir operaciones
     * como el ordenamiento sin alterar el orden de la lista
     * principal.
     *
     * Los objetos Bug no se duplican; ambas colecciones
     * contienen referencias a los mismos registros.
     *
     * @return nueva lista con los bugs registrados.
     */
    public ArrayList<Bug> listar() {
        return new ArrayList<>(bugs);
    }

    /**
     * Consulta el número de bugs registrados en el gestor.
     *
     * @return cantidad actual de reportes almacenados.
     */
    public int cantidad() {
        return bugs.size();
    }

    /**
     * Registra un conjunto de bugs mediante inserciones sucesivas.
     *
     * Reutiliza el método agregar(Bug) para garantizar que
     * todos los registros reciban identificadores únicos
     * y se almacenen mediante el mismo procedimiento.
     *
     * Verifica previamente que la colección no sea null
     * y que no contenga elementos nulos.
     *
     * @param datos lista de reportes que se desean registrar.
     * @return número de bugs insertados correctamente.
     * @throws IllegalArgumentException si la lista es null
     *         o contiene algún elemento null.
     */
    public int agregarLote(ArrayList<Bug> datos) {

        if (datos == null || datos.contains(null)) {
            throw new IllegalArgumentException("El lote contiene datos inválidos");
        }

        int registrados = 0;

        for (Bug bug : datos) {
            agregar(bug);
            registrados++;
        }

        return registrados;
    }
}