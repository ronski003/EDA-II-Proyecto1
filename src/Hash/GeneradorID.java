package Hash;

/**
 * Genera IDs enteros únicos y consecutivos.
 *
 * El mismo objeto GeneradorID debe utilizarse durante
 * toda la ejecución para no repetir identificadores.
 */
public class GeneradorID {

    private long siguienteID = 1;

    /**
     * Entrega el siguiente ID disponible.
     *
     * @return ID positivo y único.
     */
    public int generarID() {

        if (siguienteID > Integer.MAX_VALUE) {
            throw new IllegalStateException(
                "Se agotaron los identificadores"
            );
        }

        int id = (int) siguienteID;
        siguienteID++;

        return id;
    }
}