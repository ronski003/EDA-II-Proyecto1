package Ordenamientos;
import Bug.Bug;
import java.time.LocalDate;
import java.util.ArrayList;

public class OrdenamientoQuick implements  Ordenamiento{

    /**
     * Contructor de la clase OrdenamientoQuick
     */
    public OrdenamientoQuick() {
    }

    
    //Ordenar con Quick de mayor a menor la fecha
    /**    (non-Javadoc)
     * Metodo que ordena de mayor a menor bugs de acuerdo al atributo fechas
     * @see Ordenamientos.Ordenamiento#ordenarMayor(java.util.ArrayList)
     * @param lista Lista de bugs
     */
    @Override
    public ArrayList<Bug> ordenarMayor(ArrayList<Bug> lista) {
        ArrayList<Bug> listaFinal = new ArrayList<>(lista);
        QuickSortMayor(listaFinal, 0, listaFinal.size()-1);
        return listaFinal;
    }

    /**
     * Metodo recursivo que elige un pivote y al finalizar de acomodarlo divide la lista y se llama recursivamente
     * @param lista lista de bugs
     * @param low   indice del inidio de la lista
     * @param high indice del final de la lista
     */
    private void QuickSortMayor(ArrayList<Bug> lista,int low, int high){
        if(low<high){
            int pi=partitionMayor(lista, low, high);
            QuickSortMayor(lista, low, pi-1);
            QuickSortMayor(lista, pi+1, high);
        }
    }

    /**
     * metodo que acomoda el pibote
     * @param listaAux lista de bugs
     * @param low indice del inicio de la lista
     * @param high indice del final de la lista
     * @return retorna el indice de donde se encuentra el pivote
     */
    private int partitionMayor (ArrayList<Bug> listaAux,int low, int high){
        LocalDate pivot = listaAux.get(high).getFecha();
        int j,i =(low-1);
        for(j = low;j<=high-1;j++){
            if(listaAux.get(j).getFecha().compareTo(pivot)>0){
                i++;
                swap(listaAux,i,j);
            }
        }
        swap(listaAux, i+1, high);
        return (i+1);
    }

    /**
     * Metodo que intercambia elementos
     * @param listaAux lista de bugs
     * @param i indice de un bug
     * @param j indice de otro bug
     */
     private void swap(ArrayList<Bug> listaAux,int i,int j){
        Bug t =listaAux.get(i);
        listaAux.set(i, listaAux.get(j));
        listaAux.set(j, t);
    }

/*****************************************************************************************************************/
    
    /**    (non-Javadoc)
     * Metodo que ordena con Quick de menor a mayor la fecha
     * @see Ordenamientos.Ordenamiento#ordenarMenor(java.util.ArrayList)
     * @param lista Lists de bugs
     */
    @Override
    public ArrayList<Bug> ordenarMenor(ArrayList<Bug> lista) {
        ArrayList<Bug> listaFinal = new ArrayList<>(lista);
        QuickSortMenor(listaFinal, 0, listaFinal.size()-1);
        return listaFinal;
    }

    /**
     * Metodo recursivo que elige un pivote y al finalizar de acomodarlo divide la lista y se llama recursivamente
     * @param lista lista de bugs
     * @param low   indice del inidio de la lista
     * @param high indice del final de la lista
     */
     private void QuickSortMenor(ArrayList<Bug> lista,int low, int high){
        if(low<high){
            int pi=partitionMenor(lista, low, high);
            QuickSortMenor(lista, low, pi-1);
            QuickSortMenor(lista, pi+1, high);
        }
    }
    /**
     * metodo que acomoda el pibote
     * @param listaAux lista de bugs
     * @param low indice del inicio de la lista
     * @param high indice del final de la lista
     * @return retorna el indice de donde se encuentra el pivote
     */
     private int partitionMenor (ArrayList<Bug> listaAux,int low, int high){
        LocalDate pivot = listaAux.get(high).getFecha();
        int j,i =(low-1);
        for(j = low;j<=high-1;j++){
            if(listaAux.get(j).getFecha().compareTo(pivot)<0){
                i++;
                swap(listaAux,i,j);
            }
        }
        swap(listaAux, i+1, high);
        return (i+1);
    }




/*****************************************************************************************************************/    
    //Ordenar con Quick la categoria
    /**    (non-Javadoc)
     * Metodo que ordena bugs por la categoria de menor a mayor
     * @see Ordenamientos.Ordenamiento#ordenarString(java.util.ArrayList)
     * @param lista lista de bugs
     */
    @Override
    public ArrayList<Bug> ordenarString(ArrayList<Bug> lista) {
        ArrayList<Bug> listaFinal = new ArrayList<>(lista);
        QuickSortString(listaFinal, 0, listaFinal.size()-1);
        return listaFinal;
    }

    /**
     * Metodo recursivo que elige un pivote y al finalizar de acomodarlo divide la lista y se llama recursivamente
     * @param lista lista de bugs
     * @param low   indice del inidio de la lista
     * @param high indice del final de la lista
     */
     private void QuickSortString(ArrayList<Bug> lista,int low, int high){
        if(low<high){
            int pi=partitionString(lista, low, high);
            QuickSortString(lista, low, pi-1);
            QuickSortString(lista, pi+1, high);
        }
    }

    /**
     * metodo que acomoda el pibote
     * @param listaAux lista de bugs
     * @param low indice del inicio de la lista
     * @param high indice del final de la lista
     * @return retorna el indice de donde se encuentra el pivote
     */
     private int partitionString(ArrayList<Bug> listaAux,int low, int high){
        String pivot = listaAux.get(high).getCategoria();
        int j,i =(low-1);
        for(j = low;j<=high-1;j++){
            if(listaAux.get(j).getCategoria().compareTo(pivot)<0){
                i++;
                swap(listaAux,i,j);
            }
        }
        swap(listaAux, i+1, high);
        return (i+1);
    }
}
