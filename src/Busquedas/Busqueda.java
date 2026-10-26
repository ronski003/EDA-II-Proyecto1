package Busquedas;

import Bug.Bug;
import java.util.ArrayList;

public abstract class Busqueda {
    private ArrayList<Bug> listaBug;

    public abstract ArrayList<Bug> busquedaBinaria();
    public abstract ArrayList<Bug> busquedaLineal();
    public abstract ArrayList<Bug> busquedaString();

    public ArrayList<Bug> getListaBug() {
        return listaBug;
    }

    public void setListaBug(ArrayList<Bug> listaBug) {
        this.listaBug = listaBug;
    }


}
