package Ordenamientos;
import Bug.Bug;
import java.time.LocalDate;
import java.util.ArrayList;


public class OrdenamientoQuick implements Ordenamiento{


    
    public OrdenamientoQuick() {
    }

    //Ordenar con Quick de mayor a menor la fecha
    @Override
    public ArrayList<Bug> ordenarMayor(ArrayList<Bug> lista) {
        ArrayList<Bug> listaFinal = new ArrayList<>(lista);
        QuickSortMayor(listaFinal, 0, listaFinal.size()-1);
        return listaFinal;
    }

    private void QuickSortMayor(ArrayList<Bug> lista,int low, int high){
        if(low<high){
            int pi=partitionMayor(lista, low, high);
            QuickSortMayor(lista, low, pi-1);
            QuickSortMayor(lista, pi+1, high);
        }
    }
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

     private void swap(ArrayList<Bug> listaAux,int i,int j){
        Bug t =listaAux.get(i);
        listaAux.set(i, listaAux.get(j));
        listaAux.set(j, t);
    }

/*****************************************************************************************************************/
    //Ordenar con Quick de menor a mayor la fecha
    @Override
    public ArrayList<Bug> ordenarMenor(ArrayList<Bug> lista) {
        ArrayList<Bug> listaFinal = new ArrayList<>(lista);
        QuickSortMenor(listaFinal, 0, listaFinal.size()-1);
        return listaFinal;
    }

     private void QuickSortMenor(ArrayList<Bug> lista,int low, int high){
        if(low<high){
            int pi=partitionMenor(lista, low, high);
            QuickSortMenor(lista, low, pi-1);
            QuickSortMenor(lista, pi+1, high);
        }
    }

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
    @Override
    public ArrayList<Bug> ordenarString(ArrayList<Bug> lista) {
        ArrayList<Bug> listaFinal = new ArrayList<>(lista);
        QuickSortString(listaFinal, 0, listaFinal.size()-1);
        return listaFinal;
    }

     private void QuickSortString(ArrayList<Bug> lista,int low, int high){
        if(low<high){
            int pi=partitionString(lista, low, high);
            QuickSortString(lista, low, pi-1);
            QuickSortString(lista, pi+1, high);
        }
    }

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
