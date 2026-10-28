package Pruebas;

import Bug.Bug;
import Gestor.GestorBugs;
import java.time.LocalDate;

public class PruebaGestor {

    public static void main(String[] args) {

        GestorBugs gestor = new GestorBugs();

        Bug a = new Bug(0, 3, 5, "No resuelto", "Seguridad", LocalDate.of(2026, 10, 25));

        Bug b = new Bug(0, 1, 2, "Resuelto", "Interfaz", LocalDate.of(2026, 10, 24));

        Bug c = new Bug(0, 2, 4, "No resuelto", "Logica", LocalDate.of(2026, 10, 23));

        Bug primero = gestor.agregar(a);
        Bug segundo = gestor.agregar(b);
        Bug tercero = gestor.agregar(c);

        System.out.println("IDs generados:");
        System.out.println(primero.getID());
        System.out.println(segundo.getID());
        System.out.println(tercero.getID());

        System.out.println("Total: " + gestor.cantidad());

        System.out.println("Buscar ID 2: " + gestor.buscarPorID(2).getCategoria());

        System.out.println("Eliminar ID 2: " + gestor.eliminarPorID(2));

        System.out.println("Buscar ID 2: " + gestor.buscarPorID(2));

        System.out.println("Total final: " + gestor.cantidad());

        // Un ID eliminado no debe reutilizarse.
        Bug cuarto = gestor.agregar(a);

        System.out.println("Nuevo ID: " + cuarto.getID());
    }
}