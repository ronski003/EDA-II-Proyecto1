package Ordenamientos;

import Bug.Bug;
import java.util.ArrayList;
/**
 * @author Equipo 10
 * Clase ordenameinto merge
 * OrdenamientoMerge
 */
public class OrdenamientoMerge implements  Ordenamiento {
    /**
     * Contructor de la clase
     */
    public OrdenamientoMerge() {
    }

    //****************************************************************************************************************************************************************** */
    /**(non-Javadoc)
    * Metodo ordenarMayor para ordenar de mayor a menor el atributo prioridad con merge sort
    * @see Ordenamientos.Ordenamiento#ordenarMayor(java.util.ArrayList)
    * @param lista lista de bugs
    */
    @Override
    public ArrayList<Bug> ordenarMayor(ArrayList<Bug> lista) {
        //merge
        ArrayList<Bug> temporal=new ArrayList<>(lista);
        int inicio=0;
        int fin=temporal.size()-1;
        mergesort(temporal, inicio, fin);
        return temporal;

    }

    /**
     * Metodo que implementa el algoritmo mergesort mayor a menor con valores enteros
     * @param lista lista de bugs
     * @param izq primer indice de la lista
     * @param der ultimo indice de la lista
     */
    private void mergesort(ArrayList<Bug> lista, int izq,int der) {
        if (izq<der) {
            int mitad=(izq+der)/2;

            mergesort(lista, izq,mitad);
            mergesort(lista, mitad+1, der);

            merge(lista, izq, mitad, der);
        }
    }

    /**
     * Metodo Merge para Ordenar de Mayor a Menor
     * @param lista lista de bugs
     * @param izq indice del primer elemento de la lista
     * @param mitad indice del elemento a la mita de la lista
     * @param der indice del ultimo elemento de la lista
     */
    private void merge(ArrayList<Bug> lista,int izq, int mitad, int der) {
        int n1=mitad-izq+1;
        int n2= der-mitad;

        ArrayList<Bug> arrizq=new ArrayList<>();
        ArrayList<Bug> arrder=new ArrayList<>();

        for (int i = 0; i < n1; ++i)
            arrizq.add(lista.get(izq+i));
        for (int j = 0; j < n2; ++j)
            arrder.add(lista.get(mitad+1+j));

        int i = 0, j = 0;
        int k = izq;

        while (i<n1 && j<n2) {
            if (arrizq.get(i).getPrioridad()>=arrder.get(j).getPrioridad()) {
                lista.set(k,arrizq.get(i));
                i++;
            }
            else {
                lista.set(k,arrder.get(j));
                j++;
            }
            k++;
        }
        while (i < n1) {
            lista.set(k,arrizq.get(i));
            i++;
            k++;
        }

        while (j < n2) {
            lista.set(k,arrder.get(j));
            j++;
            k++;
        }


    }

    //********************************************************************************************************************************************************************************************* */
    /**(non-Javadoc)
    * Metodo para ordenar de menor a mayor el atributo prioridad con merge sort
    * @see Ordenamientos.Ordenamiento#ordenarMenor(java.util.ArrayList)
    * @param lista lista de bugs
    */
    @Override
    public ArrayList<Bug> ordenarMenor(ArrayList<Bug> lista) {
       //merge
        ArrayList<Bug> temporal=new ArrayList<>(lista);
        int inicio=0;
        int fin=temporal.size()-1;
        mergesortMenor(temporal, inicio, fin);
        return temporal;

    }

    /**
     * Metodo que implementa el algoritmo mergesort menor a mayor con valores enteros
     * @param lista lista de bugs
     * @param izq primer indice de la lista
     * @param der ultimo indice de la lista
     */
    private void mergesortMenor(ArrayList<Bug> lista, int izq,int der) {
        if (izq<der) {
            int mitad=(izq+der)/2;

            mergesortMenor(lista, izq,mitad);
            mergesortMenor(lista, mitad+1, der);

            mergeMenor(lista, izq, mitad, der);
        }
    }

    /**
     * Metodo Merge para Ordenar de Menor a Mayor
     * @param lista lista de bugs
     * @param izq indice del primer elemento de la lista
     * @param mitad indice del elemento a la mita de la lista
     * @param der indice del ultimo elemento de la lista
     */
    private void mergeMenor(ArrayList<Bug> lista,int izq, int mitad, int der) {
        int n1=mitad-izq+1;
        int n2= der-mitad;

        ArrayList<Bug> arrizq=new ArrayList<>();
        ArrayList<Bug> arrder=new ArrayList<>();

        for (int i = 0; i < n1; ++i)
            arrizq.add(lista.get(izq+i));
        for (int j = 0; j < n2; ++j)
            arrder.add(lista.get(mitad+1+j));

        int i = 0, j = 0;
        int k = izq;

        while (i<n1 && j<n2) {
            if (arrizq.get(i).getPrioridad()<=arrder.get(j).getPrioridad()) {//importante
                lista.set(k,arrizq.get(i));
                i++;
            }
            else {
                lista.set(k,arrder.get(j));
                j++;
            }
            k++;
        }
        while (i < n1) {
            lista.set(k,arrizq.get(i));
            i++;
            k++;
        }

        while (j < n2) {
            lista.set(k,arrder.get(j));
            j++;
            k++;
        }


    }
//************************************************************************************************************************************************************************* */
    /**(non-Javadoc)
    * Metodo para ordenar de menor a mayor el atributo estado con merge sort
    * @see Ordenamientos.Ordenamiento#ordenarString(java.util.ArrayList)
    * @param lista lista de bugs
    */
    @Override
    public ArrayList<Bug> ordenarString(ArrayList<Bug> lista) {
       //merge
        ArrayList<Bug> temporal=new ArrayList<>(lista);
        int inicio=0;
        int fin=temporal.size()-1;
        mergesortString(temporal, inicio, fin);
        return temporal;

    }

    /**
     * Metodo que implementa el algoritmo mergesort metodo con valores String
     * @param lista lista de bugs
     * @param izq primer indice de la lista
     * @param der ultimo indice de la lista
     */
    private void mergesortString(ArrayList<Bug> lista, int izq,int der) {
        if (izq<der) {
            int mitad=(izq+der)/2;

            mergesortString(lista, izq,mitad);
            mergesortString(lista, mitad+1, der);

            mergeString(lista, izq, mitad, der);
        }
    }

    /**
     * Metodo Merge para Ordenar de Menor a Mayor el atributo estado
     * @param lista lista de bugs
     * @param izq indice del primer elemento de la lista
     * @param mitad indice del elemento a la mita de la lista
     * @param der indice del ultimo elemento de la lista
     */
    private void mergeString(ArrayList<Bug> lista,int izq, int mitad, int der) {
        int n1=mitad-izq+1;
        int n2= der-mitad;

        ArrayList<Bug> arrizq=new ArrayList<>();
        ArrayList<Bug> arrder=new ArrayList<>();

        for (int i = 0; i < n1; ++i)
            arrizq.add(lista.get(izq+i));
        for (int j = 0; j < n2; ++j)
            arrder.add(lista.get(mitad+1+j));

        int i = 0, j = 0;
        int k = izq;

        while (i<n1 && j<n2) {
            if (arrizq.get(i).getEstado().compareTo(arrder.get(j).getEstado())>=0) {//lo importante jaja
                lista.set(k,arrizq.get(i));
                i++;
            }
            else {
                lista.set(k,arrder.get(j));
                j++;
            }
            k++;
        }
        while (i < n1) {
            lista.set(k,arrizq.get(i));
            i++;
            k++;
        }

        while (j < n2) {
            lista.set(k,arrder.get(j));
            j++;
            k++;
        }


    }


}
