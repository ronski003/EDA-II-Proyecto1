package Pruebas;

import Bug.Bug;
import Hash.TablaHashBugs;
import java.time.LocalDate;

public class PruebaHash {
    public static void main(String[] args) {

        TablaHashBugs tabla = new TablaHashBugs(7);

        Bug a = new Bug(1, 3, 5, "No resuelto",
                "Seguridad", LocalDate.of(2026, 10, 24));

        Bug b = new Bug(8, 1, 2, "Resuelto",
                "Interfaz", LocalDate.of(2026, 10, 24));

        Bug c = new Bug(15, 2, 4, "No resuelto",
                "Logica", LocalDate.of(2026, 10, 24));

        System.out.println(tabla.insertar(a));
        System.out.println(tabla.insertar(b));
        System.out.println(tabla.insertar(c));

        System.out.println("Cantidad: " + tabla.cantidad());
        System.out.println("Categoria ID 8: " +
                tabla.buscar(8).getCategoria());

        System.out.println("Duplicado: " + tabla.insertar(b));
        System.out.println("Eliminar ID 8: " + tabla.eliminar(8));
        System.out.println("Buscar ID 8: " + tabla.buscar(8));
        System.out.println("Cantidad final: " + tabla.cantidad());
    
        TablaHashBugs grande = new TablaHashBugs(7);

        // Insertar 100 bugs
        for (int i = 1; i <= 100; i++) {

        Bug bug = new Bug(
                i, 2, 3, "No resuelto",
                "Seguridad",
                LocalDate.of(2026, 10, 25)
        );

        if (!grande.insertar(bug)) {
                throw new RuntimeException("Falló inserción: " + i);
        }
        }

        // Verificar que ninguno se perdió
        for (int i = 1; i <= 100; i++) {

        if (grande.buscar(i) == null) {
                throw new RuntimeException("Se perdió el bug " + i);
        }
        }

        System.out.println("Bugs almacenados: " + grande.cantidad());
        System.out.println("Capacidad final: " + grande.capacidad());
        }
}