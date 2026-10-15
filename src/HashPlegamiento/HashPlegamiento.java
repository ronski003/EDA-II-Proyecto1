package HashPlegamiento;

public class HashPlegamiento {

    public static int hashPlegamiento(int clave){
        String numero = String.valueOf(clave);
        int bloque1 = Integer.parseInt(numero.substring(0, 4));
        int bloque2 = Integer.parseInt(numero.substring(4, 8));
        int bloque3 = Integer.parseInt(numero.substring(8, 9));
        int suma = bloque1 + bloque2 + bloque3;
        return suma % 10;
    }

    public static void insertar(int[][] contenedor, int[] cantidades, int clave){
        String numero = String.valueOf(clave);
        int bloque1 = Integer.parseInt(numero.substring(0, 4));
        int bloque2 = Integer.parseInt(numero.substring(4, 8));
        int bloque3 = Integer.parseInt(numero.substring(8, 9));
        int suma = bloque1 + bloque2 + bloque3;
        int posicion = suma % 10;
        System.out.println("\nClave: " + clave);
        System.out.println("Bloques: "+bloque1+" + "+bloque2+" + "+bloque3);
        System.out.println("Suma: " + suma);
        System.out.println("Posicion: " + posicion);
        int columna = cantidades[posicion];
        contenedor[posicion][columna] = clave;
        cantidades[posicion]++;
        if(columna > 0){
            System.out.println("Colision. Elemento almacenado en la misma posicion hash.");
        }
    }
    public static void imprimirContenedor(int[][] contenedor,int[] cantidades){
        System.out.println("\n--- CONTENEDOR FINAL ---");
        for(int i = 0; i < contenedor.length; i++){
            System.out.print("["+i+"] ");
            if(cantidades[i] == 0){
                System.out.println("Vacio");
            } else {
                for(int j = 0; j < cantidades[i]; j++){
                    System.out.print(contenedor[i][j] + " ");
                }
                System.out.println();
            }
        }
    }
}