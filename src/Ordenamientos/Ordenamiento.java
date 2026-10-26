package Ordenamientos;
import Bug.Bug;
import java.util.ArrayList;
/**
 * @author Equipo 10
 * interfaz ordenamiento
 * Ordenamiento
 */
public interface Ordenamiento {

    public abstract ArrayList<Bug> ordenarMayor(ArrayList<Bug> lista);
    public abstract ArrayList<Bug> ordenarMenor(ArrayList<Bug> lista);
    public abstract ArrayList<Bug> ordenarString(ArrayList<Bug> lista);


    
}

