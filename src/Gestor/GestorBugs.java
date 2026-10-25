package Gestor;

import Bug.Bug;
import Hash.GeneradorID;
import Hash.TablaHashBugs;
import java.util.ArrayList;

public class GestorBugs {

    private ArrayList<Bug> bugs;
    private TablaHashBugs tablaHash;
    private GeneradorID generadorID;

    public GestorBugs() {
        bugs = new ArrayList<>();
        tablaHash = new TablaHashBugs(101);
        generadorID = new GeneradorID();
    }

    /**
     * Registra un nuevo bug y le asigna un ID único.
     *
     * @param datos Bug con los atributos que se registrarán.
     * @return Bug registrado con su ID definitivo.
     */
    public Bug agregar(Bug datos) {

        if (datos == null) {
            throw new IllegalArgumentException(
                "El bug no puede ser null"
            );
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
            throw new IllegalStateException(
                "No se pudo registrar el bug"
            );
        }

        bugs.add(nuevo);

        return nuevo;
    }

    /**
     * Busca un bug por su identificador usando hashing.
     */
    public Bug buscarPorID(int id) {
        return tablaHash.buscar(id);
    }

    /**
     * Elimina un bug de la tabla hash y de la lista.
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
     * Devuelve una nueva lista con los registros actuales.
     * Las referencias a los Bug siguen siendo compartidas.
     */
    public ArrayList<Bug> listar() {
        return new ArrayList<>(bugs);
    }

    public int cantidad() {
        return bugs.size();
    }

    /**
 * Registra un conjunto de bugs utilizando la misma
 * lógica de identificación e inserción individual.
 *
 * @param datos colección de bugs provisionales.
 * @return cantidad de bugs registrados.
 */
public int agregarLote(ArrayList<Bug> datos) {

    if (datos == null || datos.contains(null)) {
        throw new IllegalArgumentException(
            "El lote contiene datos inválidos"
        );
    }

    int registrados = 0;

    for (Bug bug : datos) {
        agregar(bug);
        registrados++;
    }

    return registrados;
}
}