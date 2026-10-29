package Ordenamientos;

import Bug.Bug;
import java.util.ArrayList;

/**
 * @author Equipo 10
 * Clase ordenamiento con Heap Sort
 * OrdenamientoHeap
 */
public class OrdenamientoHeap implements  Ordenamiento{
    /**
    *Costructor de OrdenamientoHeap
    */    
    public OrdenamientoHeap() {
    }


/************************************************************************************************************************/
    /**    (non-Javadoc)
     * Ordena la lista de bugs por nivel de seguridad mayor a menor
     * @see Ordenamientos.Ordenamiento#ordenarMayor(java.util.ArrayList)
     * @param lista recibe la lista de bugs
     */
    @Override
    public ArrayList<Bug> ordenarMayor(ArrayList<Bug> lista) {
        ArrayList<Bug> listaFinal= new ArrayList<>(lista);
        int tamañoHeap=listaFinal.size()-1, tamaño=listaFinal.size();
        BuildHeapMayor(listaFinal, tamaño);
        for (int i=listaFinal.size()-1;i>0;i--)
            {
            swap(listaFinal,0,tamañoHeap);
            tamañoHeap--;
            HeapifyMayor(listaFinal, 0, tamañoHeap);
        }
        return listaFinal;
    }
    /**
     * Metodo que construye un MinHeap con la lista recibida como paramertro
     * @param listaAux recibe una lista de bugs
     * @param size recibe el tamaño del heap
     */
     private void BuildHeapMayor(ArrayList<Bug> listaAux,int size) {
        int tamañoHeap=size-1;
        for (int i = (size-1)/2;i>=0;i--) {
            HeapifyMayor(listaAux, i, tamañoHeap);
        }
    }
     /**
     * Metodo que mantiene la estructura del MinHeap
     * @param listaAux Lista de bugs
     * @param i indice del nodo
     * @param tamañoHeap tamaño del heap
     */
     private void HeapifyMayor(ArrayList<Bug> listaAux,int i,int tamañoHeap) {
        int l=2*i+1;
        int r=2*i+2;
        int smallest;

        if (l<=tamañoHeap && listaAux.get(l).getNivelSeguridad()<listaAux.get(i).getNivelSeguridad()) {
            smallest=l;
        }
        else {
            smallest=i;
        }
        if (r<=tamañoHeap && listaAux.get(r).getNivelSeguridad()<listaAux.get(smallest).getNivelSeguridad()) {
            smallest=r;
        }
        if (smallest!=i) {
            swap(listaAux,i,smallest);
            HeapifyMayor(listaAux, smallest, tamañoHeap);
        }
    }

/*************************************************************************************************************************/
    /**    (non-Javadoc)
     * Heap nivel de seguridad menor a mayor
     * @see Ordenamientos.Ordenamiento#ordenarMenor(java.util.ArrayList)
     * @param lista Lista de bugs a ordenar
     */
    @Override
    public ArrayList<Bug> ordenarMenor(ArrayList<Bug> lista) {
        ArrayList<Bug> listaFinal = new ArrayList<>(lista);
        int tamañoHeap=listaFinal.size()-1, tamaño=listaFinal.size();
        BuildHeapMenor(listaFinal, tamaño);
        for (int i=listaFinal.size()-1;i>0;i--)
            {
            swap(listaFinal,0,tamañoHeap);
            tamañoHeap--;
            HeapifyMenor(listaFinal, 0, tamañoHeap);
        }
        return listaFinal;

    }
    /**
     * Metodo que contruye un MaxHeap
     * @param listaAux Lista de bugs
     * @param size Tamaño del heap
     */
    private void BuildHeapMenor(ArrayList<Bug> listaAux,int size) {
        int tamañoHeap=size-1;
        for (int i = (size-1)/2;i>=0;i--) {
            HeapifyMenor(listaAux, i, tamañoHeap);
        }
    }
    /**
     * Metodo que mantiene la estructura del MaxHeap
     * @param listaAux Lista de Bugs
     * @param i indice del nodo
     * @param tamañoHeap Tamaño del heap
     */
    private void HeapifyMenor(ArrayList<Bug> listaAux,int i,int tamañoHeap) {
        int l=2*i+1;
        int r=2*i+2;
        int largest;

        if (l<=tamañoHeap && listaAux.get(l).getNivelSeguridad()>listaAux.get(i).getNivelSeguridad()) {
            largest=l;
        }
        else {
            largest=i;
        }
        if (r<=tamañoHeap && listaAux.get(r).getNivelSeguridad()>listaAux.get(largest).getNivelSeguridad()) {
            largest=r;
        }
        if (largest!=i) {
            swap(listaAux,i,largest);
            HeapifyMenor(listaAux, largest, tamañoHeap);
        }
    }
    /**
     * Metodo que intercambia elementos
     * @param listaAux lista de bugs
     * @param i indice de un bug
     * @param j indice de otro bug
     */
    private void swap(ArrayList<Bug> listaAux,int i,int j) {
        Bug t =listaAux.get(i);
        listaAux.set(i, listaAux.get(j));
        listaAux.set(j, t);
    }
/***************************************************************************************************************************/
    /**    (non-Javadoc)
     * Heap para categoria tipo String
     * @see Ordenamientos.Ordenamiento#ordenarString(java.util.ArrayList)
     * @param lista Lista de bugs
     */
    @Override
    public ArrayList<Bug> ordenarString(ArrayList<Bug> lista) {
        ArrayList<Bug> listaFinal = new ArrayList<>(lista);
        int tamañoHeap=listaFinal.size()-1, tamaño=listaFinal.size();
        BuildHeapString(listaFinal, tamaño);
        for (int i=listaFinal.size()-1;i>0;i--)
            {
            swap(listaFinal,0,tamañoHeap);
            tamañoHeap--;
            HeapifyString(listaFinal, 0, tamañoHeap);
        }
        return listaFinal;
    }
     /**
     * Metodo que construye un MaxHeap
     * @param listaAux lista de bugs
     * @param size tamaño del heap
     */
    private void BuildHeapString(ArrayList<Bug> listaAux,int size) {
        int tamañoHeap=size-1;
        for (int i = (size-1)/2;i>=0;i--) {
            HeapifyString(listaAux, i, tamañoHeap);
        }
    }
    /**
     * Metodo que mantiene la estructura del MaxHeap
     * @param listaAux lista de bugs
     * @param i indice del nodo
     * @param tamañoHeap tamaño del heap
     */
    private void HeapifyString(ArrayList<Bug> listaAux,int i,int tamañoHeap) {
        int l=2*i+1;
        int r=2*i+2;
        int largest;

        if (l<=tamañoHeap && listaAux.get(l).getCategoria().compareToIgnoreCase(listaAux.get(i).getCategoria())>0) {
            largest=l;
        }
        else {
            largest=i;
        }

        if (r<=tamañoHeap && listaAux.get(r).getCategoria().compareToIgnoreCase(listaAux.get(largest).getCategoria())>0) {
            largest=r;
        }

        if (largest!=i) {
            swap(listaAux,i,largest);
            HeapifyString(listaAux, largest, tamañoHeap);
        }
    }
/*************************************************************************************************************************************/
//Implemente otros metodos que no son de la interface para ordenar la fecha con heap dado que fallo quick.
/**    (non-Javadoc)
     * Heap para Mayor a menor fecha tipo LocalDate 
     * @see Ordenamientos.Ordenamiento#ordenarString(java.util.ArrayList)
     * @param lista Lista de bugs
     */
    public ArrayList<Bug> ordenarFechaMayor(ArrayList<Bug> listaBugs) {
        ArrayList<Bug> listaFinal= new ArrayList<>(listaBugs);
        int tamañoHeap=listaFinal.size()-1, tamaño=listaFinal.size();
        BuildHeapMayorFecha(listaFinal, tamaño);
        for (int i=listaFinal.size()-1;i>0;i--)
            {
            swap(listaFinal,0,tamañoHeap);
            tamañoHeap--;
            HeapifyMayorFecha(listaFinal, 0, tamañoHeap);
        }
        return listaFinal;
    }
    /**
     * Metodo que construye un MinHeap con la lista recibida como paramertro
     * @param listaAux recibe una lista de bugs
     * @param size recibe el tamaño del heap
     */
     private void BuildHeapMayorFecha(ArrayList<Bug> listaAux,int size) {
        int tamañoHeap=size-1;
        for (int i = (size-1)/2;i>=0;i--) {
            HeapifyMayorFecha(listaAux, i, tamañoHeap);
        }
    }
    /**
     * Metodo que mantiene la estructura del MinHeap
     * @param listaAux Lista de bugs
     * @param i indice del nodo
     * @param tamañoHeap tamaño del heap
     */
     private void HeapifyMayorFecha(ArrayList<Bug> listaAux,int i,int tamañoHeap) {
        int l=2*i+1;
        int r=2*i+2;
        int smallest;

        if (l<=tamañoHeap && listaAux.get(l).getFecha().compareTo(listaAux.get(i).getFecha())<0) {
            smallest=l;
        }
        else {
            smallest=i;
        }
        if (r<=tamañoHeap && listaAux.get(r).getFecha().compareTo(listaAux.get(smallest).getFecha())<0) {
            smallest=r;
        }
        if (smallest!=i) {
            swap(listaAux,i,smallest);
            HeapifyMayorFecha(listaAux, smallest, tamañoHeap);
        }
    }


/*******************************************************************************************************************************************************************************************************/
//Menor a mayor
    /**    (non-Javadoc)
     * Heap menor a mayor para fecha tipo LocalDate 
     * @see Ordenamientos.Ordenamiento#ordenarMenor(java.util.ArrayList)
     * @param lista Lista de bugs a ordenar
     */
    public ArrayList<Bug> ordenarFechaMenor(ArrayList<Bug> listaBugs) {
        ArrayList<Bug> listaFinal= new ArrayList<>(listaBugs);
        int tamañoHeap=listaFinal.size()-1, tamaño=listaFinal.size();
        BuildHeapMenorFecha(listaFinal, tamaño);
        for (int i=listaFinal.size()-1;i>0;i--)
            {
            swap(listaFinal,0,tamañoHeap);
            tamañoHeap--;
            HeapifyMenorFecha(listaFinal, 0, tamañoHeap);
        }
        return listaFinal;
    }
    /**
     * Metodo que construye un MinHeap con la lista recibida como paramertro
     * @param listaAux recibe una lista de bugs
     * @param size recibe el tamaño del heap
     */
     private void BuildHeapMenorFecha(ArrayList<Bug> listaAux,int size) {
        int tamañoHeap=size-1;
        for (int i = (size-1)/2;i>=0;i--) {
            HeapifyMenorFecha(listaAux, i, tamañoHeap);
        }
    }
    /**
     * Metodo que mantiene la estructura del MaxHeap
     * @param listaAux Lista de Bugs
     * @param i indice del nodo
     * @param tamañoHeap Tamaño del heap
     */
     private void HeapifyMenorFecha(ArrayList<Bug> listaAux,int i,int tamañoHeap) {
        int l=2*i+1;
        int r=2*i+2;
        int largest;

        if (l<=tamañoHeap && listaAux.get(l).getFecha().compareTo(listaAux.get(i).getFecha())>0) {
            largest=l;
        }
        else {
            largest=i;
        }
        if (r<=tamañoHeap && listaAux.get(r).getFecha().compareTo(listaAux.get(largest).getFecha())>0) {
            largest=r;
        }
        if (largest!=i) {
            swap(listaAux,i,largest);
            HeapifyMenorFecha(listaAux, largest, tamañoHeap);
        }
    }
}
