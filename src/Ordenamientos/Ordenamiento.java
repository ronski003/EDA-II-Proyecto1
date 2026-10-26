package Ordenamientos;
import Bug.Bug;
import java.util.ArrayList;

public abstract class Ordenamiento {
    private ArrayList <Bug> bugs;

    public Ordenamiento() {
    }



    public abstract ArrayList<Bug> ordenarMayor(ArrayList<Bug> lista);
    public abstract ArrayList<Bug> ordenarMenor(ArrayList<Bug> lista);
    public abstract ArrayList<Bug> ordenarString(ArrayList<Bug> lista);

    public ArrayList<Bug> getBugs() {
        return bugs;
    }

    public void setBugs(ArrayList<Bug> bugs) {
        this.bugs = bugs;
    }

    
}

