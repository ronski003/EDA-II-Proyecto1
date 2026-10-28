Asignación de identificadores: se eligieron IDs enteros consecutivos administrados centralmente. Esta estrategia separa la identidad de los atributos modificables del bug. La tabla hash utiliza esos identificadores como claves para realizar búsquedas eficientes. El sistema reserva el valor 0 para registros todavía no incorporados.
Gestión de bugs e identificadores: se implementó un generador de IDs enteros consecutivos y una clase GestorBugs que coordina el registro, búsqueda, eliminación y listado. Los registros se mantienen en una colección principal y se indexan mediante una tabla hash por identificador. Se comprobó que eliminar un registro no provoca la reutilización de su ID.
## Interfaz principal — Menú CLI

**Responsable:** Roberto Ochoa.

Se implementó `Principal.Main` como una Command Line Interface. El menú permite registrar, buscar por ID, eliminar, listar y contar reportes de bugs.

La captura de entradas utiliza `Scanner` y modulos específicos para la validación de números, rangos y fechas. El registro de nuevos bugs se delega completamente a `GestorBugs`, que asigna los identificadores y actualiza las estructuras de almacenamiento.

Las búsquedas por identificador utilizan indirectamente `TablaHashBugs`, mediante el gestor.

**Se decidió:**
- Separar la clase de interacción con el usuario de la lógica de almacenamiento.
- Mostrar un máximo de 30 registros por listado para evitar salidas excesivas. -> posteriormente implementar paginación
- Mantener los datos en memoria durante esta primera versión.
- Dejar la generación masiva, los ordenamientos y las búsquedas por atributos para una etapa posterior de integración.

**Pruebas:**
![alt text](image-1.png)
![alt text](image-2.png)
![alt text](image-3.png)
![alt text](image-4.png)

## Registro de Lotes

**Resposable:** Roberto Ochoa
Implementamos una prueba para el registro de un lote grande de bugs. Reutilizamos `agregar()` para mantener unica la politica de asignación de IDs y se comprobó que la estructura y el diseño actual soportan almacenar 200 bugs sin perder información
![alt text](image-5.png)
