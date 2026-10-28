package Pruebas;

import Bug.Bug;
import Gestor.GestorBugs;
import java.time.LocalDate;
import java.util.ArrayList;

public class PruebaLote {

    public static void main(String[] args) {

        GestorBugs gestor = new GestorBugs();
        ArrayList<Bug> lote = new ArrayList<>();

        for (int i = 0; i < 200; i++) {
            lote.add(new Bug(
                0, 3, 5, "No resuelto",
                "Seguridad", LocalDate.of(2026, 10, 25)
            ));
        }

        int registrados = gestor.agregarLote(lote);

        if (registrados != 200 || gestor.cantidad() != 200) {
            throw new AssertionError("Cantidad incorrecta");
        }

        for (int id = 1; id <= 200; id++) {
            Bug encontrado = gestor.buscarPorID(id);

            if (encontrado == null || encontrado.getID() != id) {
                throw new AssertionError(
                    "No se encontró el ID " + id
                );
            }
        }

        System.out.println("200 bugs registrados correctamente");
        System.out.println("Todos los IDs son únicos y recuperables");
    }
}