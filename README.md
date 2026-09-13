# Sistema de Análisis y Control de Producción Industrial

**Asignatura:** Práctica Java  
**Docente:** Juan Guillermo Flórez G.  
**Fecha:** 1 de Septiembre  

> *Nota sobre el uso de Inteligencia Artificial:**  
> Se debe tener total claridad sobre la implementación utilizada. Si el código no se sabe sustentar adecuadamente en clase, la calificación será de **0.0**.

---

## Descripción del Proyecto

Una empresa manufacturera dedicada a la producción de piezas metálicas para el sector automotriz requiere un módulo en Java orientado a analizar y procesar la producción diaria de sus diferentes líneas de fabricación.

El objetivo principal es transformar los registros de producción en información estadística resumida para los supervisores mediante **Programación Funcional en Java**, haciendo uso extensivo del API de **Streams** y evitando el uso de ciclos tradicionales (`for`, `while`) en los procesos de análisis y transformación.

---

## Registro de Producción

Cada registro de producción del sistema gestiona la siguiente información:

* **Código del producto**
* **Nombre**
* **Línea de producción**
* **Cantidad producida**
* **Cantidad defectuosa**
* **Costo unitario**
* **Minutos utilizados**
* **Kilogramos de materia prima consumidos**
* **Meta de producción**

---

## Funcionalidades necesarias

El sistema debe realizar las siguientes operaciones sobre los datos de producción:

1. **Análisis de Métricas y Desempeño:**
   * Identificar productos con niveles altos de defectos.
   * Calcular el porcentaje de cumplimiento de metas de producción.
   * Conocer el costo total de fabricación y la inversión realizada.
   * Determinar las pérdidas económicas asociadas a productos defectuosos.
   * Identificar productos de mayor y menor desempeño.
   * Detectar líneas de producción con bajo cumplimiento.

2. **Operaciones y Mantenimiento de Registros:**
   * Generar automáticamente registros de prueba.
   * Modificar la cantidad producida al reportar unidades adicionales.
   * Aplicar ajustes porcentuales sobre registros específicos.
   * Ejecutar el proceso de cierre de turno.

3. **Reporte Final del Sistema:**
   * Listar las líneas de producción existentes.
   * Mostrar la producción total agrupada por línea.
   * Identificar productos críticos.
   * Listar productos que superaron la meta.
   * Totalizar unidades defectuosas globales.
   * Mostrar costo total de producción e inversión acumulada.
   * Calcular pérdidas económicas globales y detallar el producto con mayor pérdida.
   * Destacar la línea de producción con mayor cantidad producida.

---

## Tecnologías e Interfaces Permitidas

El desarrollo debe centrarse en el paradigma funcional utilizando las siguientes interfaces y métodos del ecosistema de Java (JDK 8+):

### Interfaces Funcionales y Expresiones Lambda
* `Predicate<T>` / `BiPredicate<T,U>`
* `Function<T,R>` / `BiFunction<T,U,R>`
* `Consumer<T>` / `BiConsumer<T,U>`
* `Supplier<T>`
* `UnaryOperator<T>` / `BinaryOperator<T>`
* `Runnable` / `Callable<V>`

### Operaciones con Streams (`Stream<T>`)
* **Filtrado y Transformación:** `stream()`, `filter()`, `map()`, `mapToInt()`, `mapToDouble()`, `distinct()`, `sorted()`
* **Reducción y Búsqueda:** `forEach()`, `reduce()`, `count()`, `max()`, `min()`, `findFirst()`, `findAny()`
* **Evaluación de Condiciones:** `anyMatch()`, `allMatch()`, `noneMatch()`
* **Paginación / Recorte:** `limit()`, `skip()`
* **Colectores (`Collectors`):** `toList()`, `toSet()`, `groupingBy()`, `partitioningBy()`, `summingInt()`, `summingDouble()`, `averagingDouble()`, `counting()`, `mapping()`, `joining()`
* **Comparadores (`Comparator`):** `comparing()`, `comparingDouble()`, `reversed()`

---

## Requisitos de Implementación

* Evitar el uso de bucles iterativos convencionales (`for`, `foreach` tradicional, `while`) en la lógica de procesamiento.
* Todo procesamiento de datos deberá desarrollarse principalmente utilizando programación funcional en Java
