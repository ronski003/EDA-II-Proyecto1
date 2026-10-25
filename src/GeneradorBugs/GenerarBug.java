package GeneradorBugs;

import Bug.Bug;
import java.time.LocalDate;
import java.util.Random;

public class GenerarBug {
    private Random rand = new Random();
    private Bug bug;
    public Bug crearBug(){
        String []arr1={"Resuelto","No resuelto"};
        String []arr2={"Interfaz","Seguridad","Logica","Rendimiento"};
        this.bug.setPrioridad(rand.nextInt(1,4));
        this.bug.setNivelSeguridad(rand.nextInt(1,6));
        this.bug.setEstado(arr1[rand.nextInt(0,2)]);
        this.bug.setCategoria(arr2[rand.nextInt(0,5)]);
        LocalDate fecha = LocalDate.of(2026, rand.nextInt(1, 13), rand.nextInt(1, 30));
        bug.setFecha(fecha);
        return bug;
    }
}
