
package Pruebas;

import Bug.Bug;
import GeneradorBugs.GenerarBug;
import Gestor.GestorBugs;
import Ordenamientos.OrdenamientoMerge;
import Ordenamientos.OrdenamientoHeap;
import Ordenamientos.OrdenamientoInsertion;

import java.util.ArrayList;

public class PruebaIntegracion {

    public static void main(String[] args) {

        int cantidad = 10000;
        GestorBugs gestor = new GestorBugs();
        GenerarBug generador = new GenerarBug();
        for (int i = 0; i < cantidad; i++) {
            gestor.agregar(generador.crearBug());
        }

        verificar(gestor.cantidad() == cantidad,"Cantidad de registros");

        for (int id = 1; id <= cantidad; id++) {
            Bug encontrado = gestor.buscarPorID(id);

            verificar(encontrado != null &&encontrado.getID() == id,"Búsqueda hash ID " + id);
        }

        System.out.println("Logrado: Hash e IDs");

        ArrayList<Bug> original = gestor.listar();

        OrdenamientoMerge merge = new OrdenamientoMerge();
        OrdenamientoHeap heap = new OrdenamientoHeap();
        OrdenamientoInsertion insertion =
            new OrdenamientoInsertion();

        ArrayList<Bug> prioridad = merge.ordenarMenor(original);

        for (int i = 1; i < prioridad.size(); i++) {
            verificar(prioridad.get(i - 1).getPrioridad()<= prioridad.get(i).getPrioridad(),"Merge prioridad");
        }

        System.out.println("Logrado: Merge");

        ArrayList<Bug> seguridad = heap.ordenarMenor(original);

        for (int i = 1; i < seguridad.size(); i++) {
            verificar(seguridad.get(i - 1).getNivelSeguridad()<= seguridad.get(i).getNivelSeguridad(),"Heap seguridad");
        }

        System.out.println("Logrado: Heap seguridad");

        ArrayList<Bug> categorias = heap.ordenarString(original);

        for (int i = 1; i < categorias.size(); i++) {
            verificar(categorias.get(i - 1).getCategoria().compareToIgnoreCase(categorias.get(i).getCategoria()) <= 0,"Heap categoría");
        }

        System.out.println("Logrado: Heap categoría");

        ArrayList<Bug> muestra = new ArrayList<>(original.subList(0, 200));

        ArrayList<Bug> insertionOrdenado =
            insertion.ordenarMenor(muestra);

        for (int i = 1; i < insertionOrdenado.size(); i++) {
            verificar(insertionOrdenado.get(i - 1).getPrioridad()<= insertionOrdenado.get(i).getPrioridad(),"Insertion");
        }

        System.out.println("Logrado: Insertion");

        verificar(gestor.cantidad() == cantidad,"Integridad del gestor");

        for (int id = 1; id <= cantidad; id++) {
            verificar(gestor.buscarPorID(id) != null,"Integridad ID " + id);
        }

        System.out.println("Logrado: Integridad");

        System.out.println("\nPruebas completadas");
    }

    private static void verificar(boolean condicion, String descripcion) {

        if (!condicion) {
            throw new AssertionError("FAIL: " + descripcion);
        }
    }
}
