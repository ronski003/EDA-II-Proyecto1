package Hash;

/**
 * Administra la generación de identificadores enteros,
 * positivos, únicos y consecutivos para los reportes.
 *
 * Cada instancia mantiene un contador interno que
 * comienza en uno. Los identificadores no se reutilizan
 * durante la vida de esa instancia.
 *
 * No implementa persistencia entre ejecuciones.
 */
public class GeneradorID {

    private long siguienteID = 1;

    /**
     * Obtiene el siguiente identificador disponible.
     *
     * Devuelve el valor actual del contador y posteriormente
     * lo incrementa para la siguiente solicitud.
     *
     * Verifica que el identificador no exceda el límite
     * máximo de un entero positivo de Java.
     *
     * @return siguiente identificador entero disponible.
     * @throws IllegalStateException si se agotaron
     *         los identificadores positivos de tipo int.
     */
    public int generarID() {

        if (siguienteID > Integer.MAX_VALUE) {
            throw new IllegalStateException("Se agotaron los identificadores");
        }

        int id = (int) siguienteID;
        siguienteID++;

        return id;
    }
}