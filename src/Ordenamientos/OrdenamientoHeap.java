package Ordenamientos;

import Bug.Bug;
import java.util.ArrayList;



public class OrdenamientoHeap implements  Ordenamiento{

    public OrdenamientoHeap() {
    }


/************************************************************************************************************************/
    //Heap nivel de seguridad mayor a menor
    @Override 
    public ArrayList<Bug> ordenarMayor(ArrayList<Bug> lista){
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

     private void BuildHeapMayor(ArrayList<Bug> listaAux,int size){
        int tamañoHeap=size-1;
        for (int i = (size-1)/2;i>=0;i--){
            HeapifyMayor(listaAux, i, tamañoHeap);
        }
    }

     private void HeapifyMayor(ArrayList<Bug> listaAux,int i,int tamañoHeap){
        int l=2*i+1;
        int r=2*i+2;
        int smallest;

        if (l<=tamañoHeap && listaAux.get(l).getNivelSeguridad()<listaAux.get(i).getNivelSeguridad()){
            smallest=l;
        }
        else{
            smallest=i;
        }
        if(r<=tamañoHeap && listaAux.get(r).getNivelSeguridad()<listaAux.get(smallest).getNivelSeguridad()){
            smallest=r;
        }
        if(smallest!=i){
            swap(listaAux,i,smallest);
            HeapifyMayor(listaAux, smallest, tamañoHeap);
        }
    }   

/*************************************************************************************************************************/
    //Heap nivel de seguridad menor a mayor
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

    private void BuildHeapMenor(ArrayList<Bug> listaAux,int size){
        int tamañoHeap=size-1;
        for (int i = (size-1)/2;i>=0;i--){
            HeapifyMenor(listaAux, i, tamañoHeap);
        }
    }

    private void HeapifyMenor(ArrayList<Bug> listaAux,int i,int tamañoHeap){
        int l=2*i+1;
        int r=2*i+2;
        int largest;

        if (l<=tamañoHeap && listaAux.get(l).getNivelSeguridad()>listaAux.get(i).getNivelSeguridad()){
            largest=l;
        }
        else{
            largest=i;
        }
        if(r<=tamañoHeap && listaAux.get(r).getNivelSeguridad()>listaAux.get(largest).getNivelSeguridad()){
            largest=r;
        }
        if(largest!=i){
            swap(listaAux,i,largest);
            HeapifyMenor(listaAux, largest, tamañoHeap);
        }
    }   

    private void swap(ArrayList<Bug> listaAux,int i,int j){
        Bug t =listaAux.get(i);
        listaAux.set(i, listaAux.get(j));
        listaAux.set(j, t);
    }
/***************************************************************************************************************************/
    //Heap para categoria tipo String
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

    private void BuildHeapString(ArrayList<Bug> listaAux,int size){
        int tamañoHeap=size-1;
        for (int i = (size-1)/2;i>=0;i--){
            HeapifyString(listaAux, i, tamañoHeap);
        }
    }

    private void HeapifyString(ArrayList<Bug> listaAux,int i,int tamañoHeap){
        int l=2*i+1;
        int r=2*i+2;
        int largest;

        if (l<=tamañoHeap && listaAux.get(l).getCategoria().compareToIgnoreCase(listaAux.get(i).getCategoria())>0){
            largest=l;
        }
        else{
            largest=i;
        }

        if(r<=tamañoHeap && listaAux.get(r).getCategoria().compareToIgnoreCase(listaAux.get(largest).getCategoria())>0){
            largest=r;
        }

        if(largest!=i){
            swap(listaAux,i,largest);
            HeapifyString(listaAux, largest, tamañoHeap);
        }
    }   
/*************************************************************************************************************************************/
//Implemente otros metodos que no son de la interface para ordenar la fecha con heap dado que fallo quick. 

//Mayor a menor fecha
    public ArrayList<Bug> ordenarFechaMayor(ArrayList<Bug> listaBugs){
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

     private void BuildHeapMayorFecha(ArrayList<Bug> listaAux,int size){
        int tamañoHeap=size-1;
        for (int i = (size-1)/2;i>=0;i--){
            HeapifyMayorFecha(listaAux, i, tamañoHeap);
        }
    }

     private void HeapifyMayorFecha(ArrayList<Bug> listaAux,int i,int tamañoHeap){
        int l=2*i+1;
        int r=2*i+2;
        int smallest;

        if (l<=tamañoHeap && listaAux.get(l).getFecha().compareTo(listaAux.get(i).getFecha())<0){
            smallest=l;
        }
        else{
            smallest=i;
        }
        if(r<=tamañoHeap && listaAux.get(r).getFecha().compareTo(listaAux.get(smallest).getFecha())<0){
            smallest=r;
        }
        if(smallest!=i){
            swap(listaAux,i,smallest);
            HeapifyMayorFecha(listaAux, smallest, tamañoHeap);
        }
    }   


/*******************************************************************************************************************************************************************************************************/
//Menor a mayor
    public ArrayList<Bug> ordenarFechaMenor(ArrayList<Bug> listaBugs){
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

     private void BuildHeapMenorFecha(ArrayList<Bug> listaAux,int size){
        int tamañoHeap=size-1;
        for (int i = (size-1)/2;i>=0;i--){
            HeapifyMenorFecha(listaAux, i, tamañoHeap);
        }
    }

     private void HeapifyMenorFecha(ArrayList<Bug> listaAux,int i,int tamañoHeap){
        int l=2*i+1;
        int r=2*i+2;
        int largest;

        if (l<=tamañoHeap && listaAux.get(l).getFecha().compareTo(listaAux.get(i).getFecha())>0){
            largest=l;
        }
        else{
            largest=i;
        }
        if(r<=tamañoHeap && listaAux.get(r).getFecha().compareTo(listaAux.get(largest).getFecha())>0){
            largest=r;
        }
        if(largest!=i){
            swap(listaAux,i,largest);
            HeapifyMenorFecha(listaAux, largest, tamañoHeap);
        }
    }   
}
