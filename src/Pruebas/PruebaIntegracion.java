package Pruebas;

import Bug.Bug;
import Busquedas.BusquedaBinaria;
import GeneradorBugs.GenerarBug;
import Gestor.GestorBugs;
import Ordenamientos.OrdenamientoHeap;
import Ordenamientos.OrdenamientoInsertion;
import Ordenamientos.OrdenamientoMerge;
import java.time.LocalDate;
import java.util.ArrayList;

public class PruebaIntegracion {

    public static void main(String[] args) {

        int cantidad = 100000;
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

        // Verificar fechas ascendentes y descendentes
        ArrayList<Bug> fechasAsc = heap.ordenarFechaMenor(original);
        ArrayList<Bug> fechasDesc = heap.ordenarFechaMayor(original);

        for (int i = 1; i < cantidad; i++) {
            verificar(fechasAsc.get(i - 1).getFecha().compareTo(fechasAsc.get(i).getFecha()) <= 0,"Heap fecha ascendente");
            verificar(fechasDesc.get(i - 1).getFecha().compareTo(fechasDesc.get(i).getFecha()) >= 0,"Heap fecha descendente");
        }

        System.out.println("Logrado: Heap fechas");

        // Verificar búsqueda binaria por categoría
        BusquedaBinaria binaria = new BusquedaBinaria();

        String[] categoriasPrueba = {"Interfaz", "Seguridad", "Logica", "Rendimiento"};

        int totalCategorias = 0;

        for (String categoria : categoriasPrueba) {
            ArrayList<Bug> encontrados = binaria.BusquedaBinariaCategoria(original, categoria);

            for (Bug bug : encontrados) {
                verificar(bug.getCategoria().equalsIgnoreCase(categoria),"Categoría incorrecta");
            }

            totalCategorias += encontrados.size();
        }

        verificar(totalCategorias == cantidad,"Suma de categorías");

        System.out.println("Logrado: Búsqueda por categoría");

        // Verificar búsqueda binaria por seguridad
        int totalSeguridad = 0;

        for (int nivel = 1; nivel <= 5; nivel++) {
            ArrayList<Bug> encontrados =
                binaria.busquedaBinariaNivelSeguridad(original, nivel);

            for (Bug bug : encontrados) {
                verificar(bug.getNivelSeguridad() == nivel,"Seguridad incorrecta");
            }

            totalSeguridad += encontrados.size();
        }

        verificar(totalSeguridad == cantidad, "Suma de niveles de seguridad");

        System.out.println("Logrado: Búsqueda por seguridad");

        // Verificar búsqueda por una fecha existente
        LocalDate fechaPrueba = original.get(0).getFecha();

        int esperados = 0;

        for (Bug bug : original) {
            if (bug.getFecha().equals(fechaPrueba)) {
                esperados++;
            }
        }

        ArrayList<Bug> encontradosFecha =
            binaria.BusquedaBinariaFecha(original, fechaPrueba);

        verificar(encontradosFecha.size() == esperados, "Cantidad de coincidencias por fecha");

        for (Bug bug : encontradosFecha) {
            verificar(bug.getFecha().equals(fechaPrueba),"Fecha incorrecta");
        }

        System.out.println("Logrado: Búsqueda por fecha");

        System.out.println("\nPruebas completadas");
    }

    private static void verificar(boolean condicion, String descripcion) {

        if (!condicion) {
            throw new AssertionError("FAIL: " + descripcion);
        }
    }
}
