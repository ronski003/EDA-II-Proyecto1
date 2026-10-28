package Hash;

import Bug.Bug;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Implementa una tabla hash para almacenar y localizar
 * reportes de bugs mediante sus identificadores únicos.
 *
 * Utiliza la función de dispersión ID % capacidad y
 * resuelve las colisiones mediante encadenamiento separado.
 *
 * La tabla se redimensiona automáticamente cuando el
 * factor de carga supera el umbral establecido.
 *
 * @see Bug
 */

public class TablaHashBugs {

    private ArrayList<LinkedList<Bug>> cubetas;
    private int cantidad;

    /**
     * Construye una tabla hash con la capacidad inicial indicada.
     *
     * Inicializa las cubetas como listas enlazadas vacías y
     * establece la cantidad de registros en cero.
     *
     * @param capacidad número inicial de cubetas de la tabla.
     * @throws IllegalArgumentException si la capacidad es
     *         menor o igual a cero.
     */

    public TablaHashBugs(int capacidad) {

        if (capacidad <= 0) {
            throw new IllegalArgumentException("La capacidad debe ser positiva");
        }

        cubetas = new ArrayList<>(capacidad);

        for (int i = 0; i < capacidad; i++) {
            cubetas.add(new LinkedList<>());
        }

        cantidad = 0;
    }
    /**
     * Calcula la posición de la cubeta correspondiente a un ID.
     *
     * Aplica el método de división usando el módulo entre
     * el identificador y la capacidad actual de la tabla.
     *
     * @param id identificador positivo del bug.
     * @return índice de la cubeta correspondiente.
     */
    private int calcularIndice(int id) {
        return id % cubetas.size();
    }

    /**
     * Inserta un bug en la tabla hash.
     *
     * Verifica que el objeto y su identificador sean válidos,
     * rechaza los IDs duplicados y calcula su cubeta mediante
     * la función de dispersión.
     *
     * Antes de insertar, redimensiona la tabla si el nuevo
     * factor de carga supera el límite permitido.
     *
     * @param bug reporte que se desea almacenar.
     * @return true si el bug fue insertado correctamente;
     *         false si es nulo, tiene un ID inválido
     *         o ya existe un registro con ese ID.
     */
    public boolean insertar(Bug bug) {

        if (bug == null || bug.getID() <= 0) {
            return false;
        }

        // No permite IDs duplicados
        if (buscar(bug.getID()) != null) {
            return false;
        }

        // Comprobar si es necesario ampliar la tabla
        double nuevoFactor =
                (cantidad + 1.0) / cubetas.size();

        if (nuevoFactor > FACTOR_CARGA_MAXIMO) {
            redimensionar();
        }

        // Insertar utilizando la capacidad actualizada
        int indice = calcularIndice(bug.getID());

        cubetas.get(indice).add(bug);
        cantidad++;

        return true;
    }

    /**
     * Busca un reporte de bug a partir de su ID.
     *
     * Calcula la cubeta correspondiente y recorre su lista
     * enlazada hasta encontrar el identificador solicitado.
     *
     * @param id identificador del bug que se desea localizar.
     * @return el objeto Bug encontrado o null si no existe.
     */
    public Bug buscar(int id) {

        if (id <= 0) {
            return null;
        }

        int indice = calcularIndice(id);

        for (Bug bug : cubetas.get(indice)) {
            if (bug.getID() == id) {
                return bug;
            }
        }

        return null;
    }

    /**
     * Elimina un bug de la tabla hash mediante su identificador.
     *
     * Localiza la cubeta correspondiente y utiliza un iterador
     * para retirar el elemento de la lista enlazada.
     * Si la eliminación es exitosa, decrementa la cantidad
     * de registros almacenados.
     *
     * @param id identificador del bug que se desea eliminar.
     * @return true si el bug fue eliminado;
     *         false si no existe o el ID es inválido.
     */
    public boolean eliminar(int id) {

        if (id <= 0) {
            return false;
        }

        int indice = calcularIndice(id);

        Iterator<Bug> iterador =
            cubetas.get(indice).iterator();

        while (iterador.hasNext()) {

            Bug bug = iterador.next();

            if (bug.getID() == id) {
                iterador.remove();
                cantidad--;
                return true;
            }
        }

        return false;
    }

    /**
     * Consulta el número de bugs almacenados actualmente.
     *
     * @return cantidad de registros existentes en la tabla.
     */
    public int cantidad() {
        return cantidad;
    }

    /**
     * Consulta la capacidad actual de la tabla hash.
     *
     * La capacidad representa el número de cubetas disponibles,
     * no la cantidad de bugs almacenados.
     *
     * @return número actual de cubetas.
     */
    public int capacidad() {
        return cubetas.size();
    }
    private static final double FACTOR_CARGA_MAXIMO = 0.75;
    /**
     * Amplía la capacidad de la tabla hash y redistribuye
     * todos los bugs almacenados.
     *
     * La nueva capacidad se calcula duplicando la anterior
     * y sumando uno.
     *
     * Debido a que la función de dispersión depende de la
     * capacidad, recalcula el índice de cada registro y
     * lo coloca en la nueva tabla.
     *
     * El proceso conserva los objetos Bug y sus identificadores.
     * Su costo es lineal respecto de los elementos y cubetas
     * que deben recorrerse o inicializarse.
     */
    private void redimensionar() {

        int nuevaCapacidad = cubetas.size() * 2 + 1;

        ArrayList<LinkedList<Bug>> nuevaTabla =
                new ArrayList<>(nuevaCapacidad);

        // Crear las cubetas nuevas
        for (int i = 0; i < nuevaCapacidad; i++) {
            nuevaTabla.add(new LinkedList<>());
        }

        // Reubicar todos los bugs existentes
        for (LinkedList<Bug> lista : cubetas) {

            for (Bug bug : lista) {

                int nuevoIndice = bug.getID() % nuevaCapacidad;
                nuevaTabla.get(nuevoIndice).add(bug);
            }
        }

        // Sustituir la tabla anterior
        cubetas = nuevaTabla;
    }
}