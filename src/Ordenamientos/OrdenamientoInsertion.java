package Ordenamientos;

import Bug.Bug;
import java.util.ArrayList;
/**
 * @author Equipo 10
 * Clase ordenameinto por Insertion
 * OrdenamientoInsertion
 */
public class OrdenamientoInsertion implements Ordenamiento{
    /**
     * Constructor de la clase OrdenamientoInsertion
     */
    public OrdenamientoInsertion() {
    }

/******************************************************************************************************************************************************************************** */    
    /**    (non-Javadoc)
     * Metodo de ordenamiento mayor a menor del atributo prioridad con insertion sort
     * @see Ordenamientos.Ordenamiento#ordenarMayor(java.util.ArrayList)
     * @param lista lista de bugs
     */
    @Override
    public ArrayList<Bug> ordenarMayor(ArrayList<Bug> lista) {
        int n=lista.size();
        ArrayList<Bug> temporal=new ArrayList<>(lista);
        for (int j = 1; j < n; j++) {  
            Bug key = temporal.get(j);  
            int i = j-1;  
            while ( (i > -1) && ( temporal.get(i).getPrioridad()< key.getPrioridad() ) ) {  
                temporal.set(i+1, temporal.get(i));  
                i--;  
            }  
            temporal.set(i+1,key);
        }  
        return temporal;

    }

/********************************************************************************************************************************************************************************/
    /**    (non-Javadoc)
     * Metodo de ordenamiento menor a mayor del atributo prioridad con Insertion sort
     * @see Ordenamientos.Ordenamiento#ordenarMenor(java.util.ArrayList)
     * @param lista lista de bugs
     */
    @Override
    public ArrayList<Bug> ordenarMenor(ArrayList<Bug> lista) {
        int n=lista.size();
        ArrayList<Bug> temporal=new ArrayList<>(lista);
        for (int j = 1; j < n; j++) {  
            Bug key = temporal.get(j);  
            int i = j-1;  
            while ( (i > -1) && ( temporal.get(i).getPrioridad()> key.getPrioridad() ) ) {  
                temporal.set(i+1, temporal.get(i));    
                i--;  
            }  
            temporal.set(i+1, key);
        }  
        return temporal;

    }

/********************************************************************************************************************************************************************************/
    /**    (non-Javadoc)
     * Metodo de ordenamiento menor a mayor del atributo estado con insertion sort
     * @see Ordenamientos.Ordenamiento#ordenarString(java.util.ArrayList)
     * @param lista lista de bugs
     */  
    @Override
    public ArrayList<Bug> ordenarString(ArrayList<Bug> lista) {
        int n=lista.size();
        ArrayList<Bug> temporal=new ArrayList<>(lista);
        for (int j = 1; j < n; j++) {  
            Bug key = temporal.get(j);  
            int i = j-1;  
            while ( (i > -1) && ( temporal.get(i).getEstado().compareTo(key.getEstado())<=0) ) { 
                temporal.set(i+1, temporal.get(i));  
                i--;  
            }  
            temporal.set(i+1, key);
        }  
        return temporal;

    }

}
