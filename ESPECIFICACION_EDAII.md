# Especificación técnica y plan de trabajo

**Proyecto 1 --- Estructuras de Datos y Algoritmos II**\
**Aplicación:** Administrador de reportes de bugs\
**Equipo:** 3 integrantes · **Entrega:** martes 27 de octubre de 2026\
**Estado:** propuesta de implementación para aprobación del equipo ·
**Versión:** 1.0

## 1. Objetivo y alcance

Desarrollar en Java una aplicación de consola, orientada a objetos y
modular, que administre reportes de bugs y permita registrarlos,
eliminarlos, consultarlos, buscarlos y ordenarlos. Los algoritmos de
búsqueda y ordenamiento se implementarán manualmente, sin recurrir a los
métodos de ordenamiento o búsqueda de las bibliotecas de Java.

El programa comenzará con **200 bugs generados automáticamente**. Como
ampliación, permitirá generar conjuntos de 1 000, 10 000, 100 000 y, si
los recursos lo permiten, 1 000 000 de registros para pruebas. La
funcionalidad obligatoria tendrá prioridad sobre las pruebas masivas.

**Base documental:** enunciado oficial «Proyecto #1 --- Ordenamiento y
búsqueda» y descripción de la opción «Análisis de Bugs». La segunda es
una propuesta de referencia de otro equipo, no un diseño que debamos
copiar.

## 2. Reglas técnicas acordadas

-   **Lenguaje y entorno:** Java 21, VS Code, Extension Pack for Java,
    Git y Live Share. Misma versión de JDK para todos.
-   **Código:** clases y métodos en `UpperCamelCase` y `lowerCamelCase`,
    respectivamente; paquetes en minúsculas; nombres descriptivos; una
    responsabilidad principal por clase.
-   **Datos:** colección principal mediante `Bug[]` y contador de
    elementos válidos; crecimiento por duplicación de capacidad cuando
    sea necesario. No usar `Arrays.sort`, `Collections.sort` ni
    búsquedas automáticas.
-   **Modelo:** `Bug` contiene ID, título, prioridad (1--3), nivel de
    seguridad (1--5), equipo, categoría, estado y fecha. Los campos de
    identificación serán inmutables después del alta.
-   **Fechas:** `LocalDate` y formato `AAAA-MM-DD`; se evitarán archivos
    e hilos en la implementación principal.
-   **Resultados:** los algoritmos operan sobre arreglos o devuelven
    resultados claros; nunca imprimen menús ni leen del teclado.
-   **Control de versiones:** repositorio privado de GitHub. En Live
    Share el anfitrión coordina los commits de la sesión; los demás
    actualizan sus copias al terminar.
-   **Documentación:** archivos `.md` en `docs/`, actualizados durante
    el trabajo; reporte final en LaTeX.

## 3. Comportamiento de la aplicación

**Menú principal:** registrar bug, eliminar por ID, buscar,
listar/ordenar, contar reportes, generar lote de prueba y salir.

**Ordenamiento:** por título, prioridad, nivel de seguridad, equipo,
categoría, estado o fecha; orden ascendente predeterminado. Los empates
se resuelven por ID ascendente para obtener resultados deterministas.

**Búsqueda:** ID exacto; título por coincidencia parcial sin distinguir
mayúsculas; categoría, equipo, estado y nivel de seguridad por
coincidencia exacta; fecha exacta; listar todos. Cada consulta mostrará
cantidad de coincidencias y registros encontrados. El usuario elige el
criterio, **no el algoritmo**.

**Bugs duplicados:** pueden compartir título, fecha y atributos; no
pueden compartir ID.

## 4. Arquitectura y contratos mínimos

``` text
ProyectoEDAII/
├── src/edaii/
│   ├── app/                 Main, DemoOrdenamiento, DemoBusqueda
│   ├── modelo/              Bug
│   ├── datos/               GeneradorBugs, RepositorioBugs
│   ├── ordenamiento/        InsertionSort, QuickSort, MergeSort, HeapSort
│   ├── busqueda/            BusquedaLineal, BusquedaBinaria, TablaHash
│   ├── servicio/            GestorBugs
│   └── pruebas/             PruebasFuncionales, PruebasRendimiento
├── docs/
│   ├── bitacoras/
│   ├── evidencias/
│   ├── diseno/
│   ├── pruebas/
│   └── referencias.md
├── reporte/                 reporte.tex y reporte.pdf
├── .gitignore
└── README.md
```

**Modelo:** `Bug` ofrece constructor, getters y representación legible.
No contiene lógica de búsqueda ni ordenamiento.

**Generador:** `generar(int cantidad, long semilla)` devuelve `Bug[]`;
una semilla fija reproduce los mismos datos. La unicidad de ID se
verifica al generar cada registro.

**Repositorio:** `agregar(Bug)`, `eliminarPorId(long)`,
`obtenerTodos()`, `cantidad()`. Devuelve copias del arreglo activo
cuando un algoritmo pueda modificar el orden.

**Comparadores:** interfaz propia `ComparadorBug` con
`comparar(Bug a, Bug b): int` y una fábrica o selección central de
criterios. Todos los ordenamientos reciben el mismo comparador y aplican
el desempate por ID.

**Ordenamientos:** cada clase expone
`ordenar(Bug[] datos, ComparadorBug criterio): void`. Se ejecutan sobre
copias de los datos; no alteran el orden del repositorio principal.

**Búsquedas:** lineal para coincidencias múltiples y parciales; binaria
para consultas exactas sobre copias previamente ordenadas por la misma
clave; hash para ID. Las búsquedas devuelven `Bug[]` o un resultado
individual, según corresponda, sin imprimir directamente.

**Gestor:** conecta repositorio, búsquedas, ordenamientos y generador;
mantiene actualizado el índice hash cuando se agrega o elimina un bug.

**Interfaz:** únicamente captura opciones, valida entradas y muestra
resultados.

Estas firmas son el contrato inicial; cualquier cambio debe anotarse en
`docs/decisiones.md` antes de modificar módulos de otros integrantes.

## 5. Política de IDs y hashing

El ID debe derivarse del título y la fecha, como solicita la propuesta.
Se calculará una huella determinista de ambos valores y se comprobará su
unicidad al insertar. Si dos reportes producen el mismo candidato, se
aplicará una secuencia determinista de reintentos con un contador hasta
encontrar un ID libre. **El ID definitivo se asigna una vez y no
cambia**.

La tabla hash utilizará una función de transformación de clave
implementada manualmente (plegamiento y módulo) y **encadenamiento
separado** para resolver colisiones. El ID del bug no es la posición de
su cubeta. Se probarán colisiones forzadas, altas, bajas y búsquedas
fallidas.

La generación de IDs y la estructura hash son responsabilidades
distintas: la primera garantiza identidad única; la segunda permite
localizar registros.

## 6. Algoritmos y pruebas

  -----------------------------------------------------------------------
  Algoritmo               Uso                     Complejidad esperada
  ----------------------- ----------------------- -----------------------
  Insertion Sort          Familia de inserción y  O(n²) peor caso
                          referencia con datos    
                          pequeños                

  Quick Sort              Familia de intercambio; O(n log n) promedio
                          pivote central o        
                          mediana de tres         

  Merge Sort              Ordenamiento estable y  O(n log n)
                          prueba masiva           

  Heap Sort               Ordenamiento con poca   O(n log n)
                          memoria auxiliar        

  Búsqueda lineal         Texto parcial y filtros O(n)
                          múltiples               

  Búsqueda binaria        Clave exacta en arreglo O(log n) después de
                          ya ordenado             ordenar

  Tabla hash              Consulta por ID         O(1) esperado con carga
                                                  controlada; O(n) peor
                                                  caso
  -----------------------------------------------------------------------

**Casos mínimos:** arreglo vacío, un elemento, datos ya ordenados,
inversos, claves repetidas, empates, valores extremos, búsquedas
existentes e inexistentes, colisiones y eliminación seguida de búsqueda.

**Rendimiento:** comparar algoritmos sobre copias del mismo conjunto
generado con semilla fija. Medir con `System.nanoTime()` sin incluir
generación, impresión ni validación dentro del intervalo. Verificar
orden correcto y conservación de registros. Escalar 200 → 1 000 → 10 000
→ 100 000 → 1 000 000 según tiempo y memoria; limitar Insertion Sort a
tamaños razonables. Registrar equipo/JDK, tamaño, algoritmo, tiempo y
resultado.

## 7. Reparto de trabajo

  --------------------------------------------------------------------------
  Etapa             Integrante A      Integrante B        Integrante C
                    (coordinación                         
                    técnica)                              
  ----------------- ----------------- ------------------- ------------------
  Base              Contratos,        `Bug`,              `GeneradorBugs`,
                    `GestorBugs`,     `RepositorioBugs`   datos de prueba
                    integración                           

  Algoritmos        Tabla hash e IDs  Insertion y Merge   Quick y Heap Sort
                                      Sort                

  Búsquedas         Hash por ID       Lineal y filtros    Binaria e índices
                                                          ordenados

  Integración       Menús y revisión  Pruebas de gestión  Pruebas de
                    de interfaces                         ordenamiento y
                                                          rendimiento

  Documentación     Arquitectura y    Bitácoras y         UML, pruebas y
                    ensamblaje del    bibliografía        Javadoc
                    reporte                               
  --------------------------------------------------------------------------

**Responsabilidad compartida:** cada integrante documenta sus métodos,
aporta evidencias y fuentes, revisa código ajeno y redacta su conclusión
individual. Las tareas pueden rotarse si alguien se bloquea. La
coordinación técnica no significa hacer el trabajo de los demás.

## 8. Dinámica de reuniones (2 horas)

1.  **0--10 min:** revisar pendientes y definir resultado concreto de la
    sesión.
2.  **10--20 min:** `git pull`, abrir carpeta raíz, iniciar Live Share y
    verificar compilación.
3.  **20--95 min:** programación paralela en archivos distintos;
    consultar antes de cambiar contratos.
4.  **95--110 min:** integrar, ejecutar pruebas y corregir errores
    críticos.
5.  **110--120 min:** commit, bitácora, capturas necesarias y tareas de
    la siguiente reunión.

No hacer commits de código que no compile. Usar mensajes claros:
`feat: agrega generador reproducible`, `fix: corrige colisiones hash`,
`docs: bitacora reunion 02`. Excluir `bin/`, `.class`, archivos
temporales y datos masivos del repositorio. Si Live Share falla,
continuar mediante ramas individuales y revisión antes de integrar.

## 9. Evidencias y bitácoras

Al cerrar cada reunión crear `docs/bitacoras/reunion_XX.md` con:

``` md
# Reunión XX — AAAA-MM-DD
**Asistentes:** A, B, C
**Objetivo:**
**Trabajo realizado:** A: ... / B: ... / C: ...
**Decisiones y motivos:**
**Pruebas y resultados:**
**Problemas pendientes:**
**Evidencia:** rutas de capturas y hash del commit
**Tareas siguientes:** responsable y resultado esperado
```

Guardar en `docs/evidencias/reunion_XX/` sólo **1--3 capturas útiles**,
por ejemplo sesión colaborativa, prueba significativa o resultado de
rendimiento. No se necesitan fotografías físicas ni firmas: el enunciado
no las exige. Conservar también commits, diagramas, pruebas y
referencias consultadas. No fabricar evidencias retrospectivas.

## 10. Reporte LaTeX progresivo

**Estructura:** portada; objetivo; introducción breve (opcional);
desarrollo (problema, arquitectura, algoritmos, hashing, pruebas y
resultados); conclusiones individuales; bibliografía y anexos
pertinentes.

-   **En cada reunión:** registrar decisiones, referencias, resultados y
    capturas en Markdown.
-   **Al completar un módulo:** su responsable entrega una explicación
    breve de cómo funciona, por qué se eligió y qué pruebas pasó.
-   **24--25 de octubre:** integrar explicaciones, UML, tablas y
    resultados al `.tex`; generar Javadoc.
-   **26 de octubre:** revisar redacción, conclusiones individuales,
    referencias, PDF y código desde una copia limpia.
-   **27 de octubre:** entregar las carpetas solicitadas mediante enlace
    compartido.

El reporte debe explicar el software realmente implementado, no
funcionalidades que sólo se planearon.

## 11. Criterios de cierre

El proyecto está listo cuando: inicia con 200 reportes; todas las
opciones del menú funcionan; IDs son únicos; ordenamientos y búsquedas
manuales pasan pruebas; se manejan colisiones; el código compila desde
cero; los tres pueden explicar sus módulos; existen fuentes y reporte;
el `.tex` genera el `.pdf`; y la carpeta de entrega está organizada.

**Prioridad ante retrasos:** (1) funcionalidad obligatoria y corrección,
(2) integración y pruebas, (3) reporte y material obligatorio, (4) LaTeX
y opcionales, (5) escala masiva y extras. Ningún extra debe poner en
riesgo la entrega.

## 12. Primera reunión: resultado mínimo

Antes de reunirse: instalar JDK, Git, VS Code y extensiones; crear
cuentas de GitHub; leer esta especificación. Durante la reunión: crear
repositorio, probar Live Share, aprobar contratos, implementar `Bug` y
generador básico, ejecutar 200 registros y cerrar con commit y primera
bitácora. **No iniciar todos los algoritmos antes de tener una base que
compile.**

------------------------------------------------------------------------

*Documento interno de planeación. Los nombres de integrantes se
sustituyen por A/B/C al aprobar el reparto. Los cambios relevantes se
registrarán en `docs/decisiones.md`.*
