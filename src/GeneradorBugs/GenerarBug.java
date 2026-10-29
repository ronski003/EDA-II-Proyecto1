package GeneradorBugs;

import Bug.Bug;
import java.time.LocalDate;
import java.util.Random;
/**
 * @author Equipo 10
 * Clase Generador de Bugs
 * GenerarBug
 */
public class GenerarBug {
    private Random rand = new Random();
    private Bug bug;
    /**
     * Metodo para crear una instancia de Bug de forma aleatorea
     * @return retorna la intancia creada de la clase bug
     */
    public Bug crearBug() {
        bug = new Bug(0,0,0,"","",LocalDate.now());
        String []arr1={"Resuelto","No resuelto"};
        String []arr2={"Interfaz","Seguridad","Logica","Rendimiento"};
        this.bug.setPrioridad(rand.nextInt(1,4));
        this.bug.setNivelSeguridad(rand.nextInt(1,6));
        this.bug.setEstado(arr1[rand.nextInt(0,2)]);
        this.bug.setCategoria(arr2[rand.nextInt(0,4)]);
        LocalDate fecha = LocalDate.of(2026,1,1).plusDays(rand.nextInt(0,297)); //Los bugs son desde inicio de año hasta el 24 de octubre de 2026
        bug.setFecha(fecha);
        return bug;
    }
}
