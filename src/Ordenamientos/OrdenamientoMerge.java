package Ordenamientos;

import Bug.Bug;
import java.util.ArrayList;

public class OrdenamientoMerge extends Ordenamiento {
    private int inicio;
    private int fin;


    public OrdenamientoMerge(ArrayList<Bug> bugs) {
        super(bugs);
    }
/**(non-Javadoc)
 * merge para id
 * @see Ordenamientos.Ordenamiento#ordenarMayor(java.util.ArrayList)
 */
    @Override
    public ArrayList<Bug> ordenarMayor(ArrayList<Bug> lista) {
        //merge
        ArrayList<Bug> temporal=lista;
        this.inicio=0;
        this.fin=temporal.size()-1;
        mergesort(temporal, this.inicio, this.fin);
        return temporal;

    }

    public void mergesort(ArrayList<Bug> lista, int izq,int der){
        if (izq<der){
            int mitad=(izq+der)/2; 
            
            mergesort(lista, izq,mitad);
            mergesort(lista, mitad+1, der);

            merge(lista, izq, mitad, der);
        }
    }

    public void merge(ArrayList<Bug> lista,int izq, int mitad, int der){
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

        while(i<n1 && j<n2){
            if(arrizq.get(i).getID()<=arrder.get(j).getID()){
                lista.set(k,arrizq.get(i));
                i++;
            }
            else{
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
/**(non-Javadoc)
 * merge para id 
 * @see Ordenamientos.Ordenamiento#ordenarMenor(java.util.ArrayList)
 */
    @Override
    public ArrayList<Bug> ordenarMenor(ArrayList<Bug> lista) {
       //merge
        throw new UnsupportedOperationException("Unimplemented method 'ordenarMenor'");
    }

    @Override
    public ArrayList<Bug> ordenarString(ArrayList<Bug> lista) {
        //Insertion
        throw new UnsupportedOperationException("Unimplemented method 'ordenarString'");
    }

    
   

}
