package Busquedas;

import Bug.Bug;
import java.util.ArrayList;

/**
 * @author Equipo 10
 * Clase para la busqueda lineal de los atributos fecha nivel de seguridad y categoria
 * BusquedaLineal
 */
public class BusquedaLineal {
    
    /**************************************************************************************************************************************** */
    /**
     * Busqueda Lineal para la prioridad ordenada con Merge
     * @param listaBugs Lista de bugs
     * @param prioridad Prioridad a buscar
     * @return la lista de los elementos encontrados
     */
    public ArrayList<Bug> BusquedaLinealPrioridad(ArrayList<Bug> listaBugs, int prioridad){
       if(listaBugs.isEmpty())
        {
            System.out.println("La lista esta vacía");
            return listaBugs;
        }
        ArrayList <Bug> encontrados= new ArrayList<>();
        for (Bug bug : listaBugs) {
            if(bug.getPrioridad()==prioridad){
                encontrados.add(bug);
            }
        }
        return encontrados;
    }
    /*************************************************************************************************************************************************** */
    
    /**
     * Busqueda Lineal para el estado ordenado con Insertion
     * @param listaBugs Lista de bugs
     * @param estado estado de bug a buscar
     * @return la lista de los elementos encontrados
     */
    public ArrayList<Bug> BusquedaLinealEstado(ArrayList<Bug> listaBugs, String estado){
       if(listaBugs.isEmpty())
        {
            System.out.println("La lista esta vacía");
            return listaBugs;
        }
        ArrayList <Bug> encontrados= new ArrayList<>();
        for (Bug bug : listaBugs) {
            if(bug.getEstado().equals(estado)){
                encontrados.add(bug);
            }
        }
        return encontrados;
    }
}
