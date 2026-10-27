package Ordenamientos;
import Bug.Bug;
import java.util.ArrayList;
/**
 * @author Equipo 10
 * interfaz ordenamiento
 * Ordenamiento
 */
public interface Ordenamiento {
    /**
     * Metodo abstracto que ordena de mayor a menor
     * @param lista lista de bugs
     * @return devuelve una lista de bugs ordenada
     */
    public abstract ArrayList<Bug> ordenarMayor(ArrayList<Bug> lista);

    /**
     * Metodo abstracto que ordena de mayor a menor
     * @param lista lista de bugs
     * @return devuelve una lista de bugs ordenada
     */
    public abstract ArrayList<Bug> ordenarMenor(ArrayList<Bug> lista);

    /**
     * Metodo abstracto que ordena de mayor a menor
     * @param lista lista de bugs
     * @return devuelve una lista de bugs ordenada
     */
    public abstract ArrayList<Bug> ordenarString(ArrayList<Bug> lista);


    
}

