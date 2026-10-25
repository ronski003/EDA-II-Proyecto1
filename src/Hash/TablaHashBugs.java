package Hash;

import Bug.Bug;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class TablaHashBugs {

    private ArrayList<LinkedList<Bug>> cubetas;
    private int cantidad;

    public TablaHashBugs(int capacidad) {

        if (capacidad <= 0) {
            throw new IllegalArgumentException(
                "La capacidad debe ser positiva"
            );
        }

        cubetas = new ArrayList<>(capacidad);

        for (int i = 0; i < capacidad; i++) {
            cubetas.add(new LinkedList<>());
        }

        cantidad = 0;
    }

    private int calcularIndice(int id) {
        return id % cubetas.size();
    }

    /**
     * Inserta un bug si su ID es válido y no está registrado.
     * Redimensiona la tabla cuando se supera el factor de carga.
     *
     * @param bug Bug que se desea almacenar.
     * @return true si se insertó; false si es inválido o duplicado.
     */
    public boolean insertar(Bug bug) {

        if (bug == null || bug.getID() <= 0) {
            return false;
        }

        // No permitir IDs duplicados
        if (buscar(bug.getID()) != null) {
            return false;
        }

        // Comprobar si necesitamos ampliar la tabla
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

    public int cantidad() {
        return cantidad;
    }

    public int capacidad() {
        return cubetas.size();
    }
    private static final double FACTOR_CARGA_MAXIMO = 0.75;
/**
 * Aumenta la capacidad de la tabla y redistribuye sus elementos.
 * Es necesario recalcular las posiciones porque cambia el módulo.
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