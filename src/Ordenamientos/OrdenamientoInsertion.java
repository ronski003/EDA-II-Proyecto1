package Ordenamientos;

import Bug.Bug;
import java.util.ArrayList;

public class OrdenamientoInsertion extends Ordenamiento{

    public OrdenamientoInsertion() {


    }

/******************************************************************************************************************************************************************************** */    
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
    @Override
    public ArrayList<Bug> ordenarString(ArrayList<Bug> lista) {
        int n=lista.size();
        ArrayList<Bug> temporal=new ArrayList<>(lista);
        for (int j = 1; j < n; j++) {  
            Bug key = temporal.get(j);  
            int i = j-1;  
            while ( (i > -1) && ( temporal.get(i).getEstado().compareTo(key.getEstado())>=0) ) { 
                temporal.set(i+1, temporal.get(i));  
                i--;  
            }  
            temporal.set(i+1, key);
        }  
        return temporal;

    }

}
