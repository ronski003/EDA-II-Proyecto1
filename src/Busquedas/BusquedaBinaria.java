package Busquedas;

import Bug.Bug;
import Ordenamientos.OrdenamientoHeap;
import Ordenamientos.OrdenamientoQuick;
import java.time.LocalDate;
import java.util.ArrayList;

public class BusquedaBinaria {

/*********************************************************************************************************************/

//Busqueda Binaria para la fecha ordenando con quick
    public ArrayList<Bug> BusquedaBinariaFecha(ArrayList<Bug> listaBugs,LocalDate fecha){
        OrdenamientoQuick ordenamiento = new OrdenamientoQuick();
        ArrayList<Bug> listaAux1 = ordenamiento.ordenarMenor(listaBugs);
        ArrayList<Bug> listaAux2 = new ArrayList<>();
        int izq=0, der=listaAux1.size()-1;
        while(izq <= der){
            int medio=(der+izq)/2;
            if(fecha.compareTo(listaAux1.get(medio).getFecha())==0){
                listaAux2.add(listaAux1.get(medio));
                int j=medio-1,k=medio+1;
                while(j>=0 && fecha.compareTo(listaAux1.get(j).getFecha())==0){
                    listaAux2.addFirst(listaAux1.get(j));
                    j--;
                }
                while(k<listaAux1.size() && fecha.compareTo(listaAux1.get(k).getFecha())==0){
                    listaAux2.addLast(listaAux1.get(k));
                    k++;
                }
                return listaAux2;
            }
            if(fecha.compareTo(listaAux1.get(medio).getFecha())<0){
                der=medio-1;
            }
            if(fecha.compareTo(listaAux1.get(medio).getFecha())>0){
                izq=medio+1;
            }
        }
        return listaAux2;
    }

/*********************************************************************************************************************/
    
//Busqueda Binaria para el nivel de seguridad ordenando con heap
    public ArrayList<Bug> busquedaBinariaNivelSeguridad(ArrayList<Bug> listaBugs,int nivelSeguridad){
        OrdenamientoHeap ordenamiento = new OrdenamientoHeap();
        ArrayList<Bug> listaAux1 = ordenamiento.ordenarMenor(listaBugs);
        ArrayList<Bug> listaAux2 = new ArrayList<>();
        int izq=0, der=listaAux1.size()-1;
        while(izq <= der){
            int medio=(der+izq)/2;
            if(nivelSeguridad==listaAux1.get(medio).getNivelSeguridad()){
                listaAux2.add(listaAux1.get(medio));
                int j=medio-1,k=medio+1;
                while(j>=0 && nivelSeguridad==listaAux1.get(j).getNivelSeguridad()){
                    listaAux2.addFirst(listaAux1.get(j));
                    j--;
                }
                while(k<listaAux1.size() && nivelSeguridad==listaAux1.get(k).getNivelSeguridad()){
                    listaAux2.addLast(listaAux1.get(k));
                    k++;
                }
                return listaAux2;
            }
            if(nivelSeguridad<listaAux1.get(medio).getNivelSeguridad()){
                der=medio-1;
            }
            if(nivelSeguridad>listaAux1.get(medio).getNivelSeguridad()){
                izq=medio+1;
            }
        }
        return listaAux2;
    }

/*********************************************************************************************************************/

//Busqueda Binaria para la categoria ordenando con quick

public ArrayList<Bug> BusquedaBinariaCategoria(ArrayList<Bug> listaBugs,String categoria){
    OrdenamientoQuick ordenamiento = new OrdenamientoQuick();
    ArrayList<Bug> listaAux1 = ordenamiento.ordenarString(listaBugs);
    ArrayList<Bug> listaAux2 = new ArrayList<>();
     int izq=0, der=listaAux1.size()-1;
        while(izq <= der){
            int medio=(der+izq)/2;
            if(categoria.compareTo(listaAux1.get(medio).getCategoria())==0){
                listaAux2.add(listaAux1.get(medio));
                int j=medio-1,k=medio+1;
                while(j>=0 && categoria.compareTo(listaAux1.get(j).getCategoria())==0){
                    listaAux2.addFirst(listaAux1.get(j));
                    j--;
                }
                while(k<listaAux1.size() && categoria.compareTo(listaAux1.get(k).getCategoria())==0){
                    listaAux2.addLast(listaAux1.get(k));
                    k++;
                }
                return listaAux2;
            }
            if(categoria.compareTo(listaAux1.get(medio).getCategoria())<0){
                der=medio-1;
            }
            if(categoria.compareTo(listaAux1.get(medio).getCategoria())>0){
                izq=medio+1;
            }
        }
        return listaAux2;
    }
}


