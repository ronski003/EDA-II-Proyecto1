package Ordenamientos;
import Bug.Bug;
import java.util.ArrayList;

public abstract class Ordenamiento {
    private ArrayList <Bug> bugs;

    public Ordenamiento(ArrayList<Bug> bugs) {
        this.bugs = bugs;
    }

    public abstract void ordenar();

    public ArrayList<Bug> getBugs() {
        return bugs;
    }

    public void setBugs(ArrayList<Bug> bugs) {
        this.bugs = bugs;
    }

    
}

