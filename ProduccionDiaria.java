//Práctica Java 1 de Septiembre
//Juan Guillermo Flórez G. 

//Tengan cuidado con la IA. Si no se sabe sustetar su calificación será 0.

//                                        Sistema de análisis y control de producción industrial

//Una empresa manufacturera dedicada a la producción de piezas metálicas para el sector automotriz
//necesita desarrollar un módulo en Java que permita analizar la producción diaria de sus diferentes líneas de fabricación.

//Cada registro de producción debe contener información como código del producto, nombre,
//línea de producción, cantidad producida, cantidad defectuosa, costo unitario, minutos utilizados,
//kilogramos de materia prima consumidos y meta de producción.

//La empresa requiere identificar productos con niveles altos de defectos, calcular el cumplimiento de metas,
//conocer el costo total de fabricación, determinar pérdidas económicas asociadas a productos defectuosos y analizar
//el desempeño general de cada línea de producción.

//El sistema debe permitir transformar los registros de producción en información resumida para los supervisores, obtener los productos de mayor y menor desempeño, identificar líneas con bajo cumplimiento, calcular la cantidad total producida y determinar cuánto dinero se ha invertido en producción.

//También será necesario generar automáticamente un registro de producción de prueba, modificar cantidades producidas cuando se reporten unidades adicionales, aplicar ajustes porcentuales sobre determinados registros y ejecutar un proceso de cierre del turno.

//Al finalizar, el sistema deberá mostrar un reporte con las líneas existentes, producción total por línea, productos críticos, productos que superaron la meta, unidades defectuosas, costo total de producción, pérdidas económicas, producto con mayor pérdida y línea con mayor cantidad producida.

//La solución deberá desarrollarse principalmente utilizando programación funcional en Java, evitando ciclos tradicionales para las operaciones de análisis y transformación cuando puedan resolverse utilizando Streams.


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

class RegistroProduccion {
    private final int codigoProducto;
    private final String nombreProducto;
    private final String lineaProduccion;
    private int cantidadProducida;
    private int cantidadDefectuosa;
    private double costoUnitario;
    private int minutosUtilizados;
    private double kilogramosMateriaPrima;
    private int metaProduccion;

    public RegistroProduccion(int codigoProducto, String nombreProducto, String lineaProduccion,
                              int cantidadProducida, int cantidadDefectuosa, double costoUnitario,
                              int minutosUtilizados, double kilogramosMateriaPrima, int metaProduccion) {

        this.codigoProducto = codigoProducto;
        this.nombreProducto = nombreProducto;
        this.lineaProduccion = lineaProduccion;
        this.cantidadProducida = cantidadProducida;
        this.cantidadDefectuosa = cantidadDefectuosa;
        this.costoUnitario = costoUnitario;
        this.minutosUtilizados = minutosUtilizados;
        this.kilogramosMateriaPrima = kilogramosMateriaPrima;
        this.metaProduccion = metaProduccion;
    } 

    public int getCodigoProducto() {
        return codigoProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public String getLineaProduccion() {
        return lineaProduccion;
    }

    public int getCantidadProducida() {
        return cantidadProducida;
    }

    public int getCantidadDefectuosa() {
        return cantidadDefectuosa;
    }

    public double getCostoUnitario() {
        return costoUnitario;
    }

    public int getMinutosUtilizados() {
        return minutosUtilizados;
    }

    public double getKilogramosMateriaPrima() {
        return kilogramosMateriaPrima;
    }

    public int getMetaProduccion() {
        return metaProduccion;
    }

    public void setCantidadProducida(int cantidadProducida) {
        this.cantidadProducida = cantidadProducida;
    }

    public void setCantidadDefectuosa(int cantidadDefectuosa) {
        this.cantidadDefectuosa = cantidadDefectuosa;
    }

    public void setCostoUnitario(double costoUnitario) {
        this.costoUnitario = costoUnitario;
    }

    public void setMinutosUtilizados(int minutosUtilizados) {
        this.minutosUtilizados = minutosUtilizados;
    }

    public void setKilogramosMateriaPrima(double kilogramosMateriaPrima) {
        this.kilogramosMateriaPrima = kilogramosMateriaPrima;
    }

    public void setMetaProduccion(int metaProduccion) {
        this.metaProduccion = metaProduccion;
    }

    @Override
    public String toString() {
        return "registroProduccion{" +
                "codigoProducto=" + codigoProducto +
                ", nombreProducto='" + nombreProducto + '\'' +
                ", lineaProduccion='" + lineaProduccion + '\'' +
                ", cantidadProducida=" + cantidadProducida +
                ", cantidadDefectuosa=" + cantidadDefectuosa +
                ", costoUnitario=" + costoUnitario +
                ", minutosUtilizados=" + minutosUtilizados +
                ", kilogramosMateriaPrima=" + kilogramosMateriaPrima +
                ", metaProduccion=" + metaProduccion +
                '}';
    }
}

public class ProduccionDiaria {

    public static void main(String[] args) throws Exception {
        
        List<RegistroProduccion> registros = new ArrayList<>(Arrays.asList(
        
                new RegistroProduccion(1, "Producto A", "Linea 1", 100, 5, 10.0, 60, 50.0, 120),
                new RegistroProduccion(2, "Producto B", "Linea 1", 80, 10, 12.0, 50, 40.0, 100),
                new RegistroProduccion(3, "Producto C", "Linea 2", 150, 15, 8.0, 70, 60.0, 140),
                new RegistroProduccion(4, "Producto D", "Linea 2", 90, 8, 15.0, 55, 45.0, 110),
                new RegistroProduccion(5, "Producto E", "Linea 3", 120, 12, 9.0, 65, 55.0, 130)

        ));

        
        //Identificar productos con niveles altos de defectos
        Predicate<RegistroProduccion> defectosAltos =   
                registro -> registro.getCantidadDefectuosa() > 10;

        List<RegistroProduccion> productosDefectuosos = registros.stream()
                .filter(defectosAltos)
                .collect(Collectors.toList());

                
        //calcular el cumplimiento de metas
        Predicate<RegistroProduccion> cumpleMeta = 
                registro -> registro.getCantidadProducida() >= registro.getMetaProduccion();
        
        List<RegistroProduccion> productosCumplenMeta = registros.stream()
                .filter(cumpleMeta)
                .collect(Collectors.toList());


        //conocer el costo total de fabricación
        Function<RegistroProduccion, Double> costoTotal = 
                registro -> registro.getCantidadProducida() * registro.getCostoUnitario();


        Callable<Double> costoTotalProduccion = () -> registros.stream()
                .map(costoTotal)
                .reduce(0.0, Double::sum);

        Double costoTotalProduccionValor = costoTotalProduccion.call();
        System.out.println("Costo total de producción:" + costoTotalProduccionValor);
        

        //determinar pérdidas económicas asociadas a productos defectuosos
        Function<RegistroProduccion, Double> perdidasEconomicas = 
                registro -> registro.getCantidadDefectuosa() * registro.getCostoUnitario();
        
        Callable<Double> perdidasTotales = () -> registros.stream()
                .map(perdidasEconomicas)
                .reduce(0.0, Double::sum);

        Double perdidasTotalesValor = perdidasTotales.call();
        System.out.println("Pérdidas económicas totales: " + perdidasTotalesValor);


        //analizar el desempeño general de cada línea de producción
        Map<String, Integer> produccionPorLinea = registros.stream()
                .collect(Collectors.groupingBy(
                        registro -> registro.getLineaProduccion(),
                        Collectors.summingInt(registro -> registro.getCantidadProducida())
                ));

        System.out.println("Producción total por línea: " + produccionPorLinea);

         //calcular la cantidad total producida
        Function<RegistroProduccion, Integer> cantidadTotalProducida = 
                registro -> registro.getCantidadProducida();

        Callable<Integer> cantidadTotal = () -> registros.stream()
                .map(cantidadTotalProducida)
                .reduce(0, Integer::sum);

        Double costoTotalProduccionValue = costoTotalProduccion.call();
        System.out.println("Costo total de producción: " + costoTotalProduccionValue);


        //determinar cuánto dinero se ha invertido en producción DUDA
        // El dinero invertido corresponde al total acumulado en fabricación (cantidad * costo unitario)
        Callable<Double> dineroInvertido = () -> registros.stream()
                .map(costoTotal)
                .reduce(0.0, Double::sum);

        Double dineroInvertidoTotal = dineroInvertido.call();
        System.out.println("Dinero invertido en producción: " + dineroInvertidoTotal);


        //El sistema debe permitir transformar los registros de producción en información resumida para los supervisores
        Function<RegistroProduccion, String> resumenProduccion = 
                registro -> "Producto: " + registro.getNombreProducto() +
                            ", Línea: " + registro.getLineaProduccion() +
                            ", Producido: " + registro.getCantidadProducida() +
                            ", Defectuoso: " + registro.getCantidadDefectuosa() +
                            ", Costo Unitario: " + registro.getCostoUnitario();
        
        registros.stream()
                .map(resumenProduccion)
                .forEach(System.out::println);


        //obtener los productos de mayor y menor desempeño
        Optional<RegistroProduccion> mayorDesempeno = registros.stream()
        .max(Comparator.comparing(RegistroProduccion::getCantidadProducida));

        RegistroProduccion productoMayorDesempeno = mayorDesempeno.get();
        System.out.println("Producto de mayor desempeño: " + productoMayorDesempeno);
        
        
        Optional<RegistroProduccion> menorDesempeno = registros.stream()
        .min(Comparator.comparing(RegistroProduccion::getCantidadProducida));

        RegistroProduccion productoMenorDesempeno = menorDesempeno.get();
        System.out.println("Producto de menor desempeño: " + productoMenorDesempeno);


        //identificar líneas con bajo cumplimiento
        Map<String, Integer> metaPorLinea = registros.stream()
                .collect(Collectors.groupingBy(
                        RegistroProduccion::getLineaProduccion,
                        Collectors.summingInt(RegistroProduccion::getMetaProduccion)
                ));

        List<String> lineasBajoCumplimiento = produccionPorLinea.keySet().stream()
                .filter(linea -> produccionPorLinea.get(linea) < metaPorLinea.get(linea))
                .collect(Collectors.toList());

        System.out.println("Líneas con bajo cumplimiento: " + lineasBajoCumplimiento);            

        
        //generar automáticamente un registro de producción de prueba
        Supplier<RegistroProduccion> produccionPrueba = 
            () -> new RegistroProduccion(6, "Producto F", "Linea 3", 110, 9, 11.0, 60, 50.0, 120);

        RegistroProduccion nuevoRegistro = produccionPrueba.get();
        registros.add(nuevoRegistro);


        //modificar cantidades producidas cuando se reporten unidades adicionales
        BiConsumer<RegistroProduccion, Integer> reportarUnidadesAdicionales = 
                (registro, unidades) -> registro.setCantidadProducida(registro.getCantidadProducida() + unidades);

        reportarUnidadesAdicionales.accept(registros.get(0), 20);

        System.out.println("Registro actualizado: " + registros.get(0));


        //aplicar ajustes porcentuales sobre determinados registros
        UnaryOperator<RegistroProduccion> ajustarCostoUnitario =
                registro -> {
                    registro.setCostoUnitario(registro.getCostoUnitario() * 1.10);
                    return registro;
                };

        registros.set(2, ajustarCostoUnitario.apply(registros.get(2)));

        System.out.println("Registro con costo ajustado: " + registros.get(2));  



 
        //
        Runnable cierreTurno = () -> {
            try {
                System.out.println("\n=== Cierre de Turno ===");
                System.out.println("Cantidad de registros procesados: " + registros.size());
                System.out.println("Producción total del turno: " + cantidadTotal.call());
                System.out.println("Costo total de fabricación: " + costoTotalProduccion.call());
                System.out.println("Pérdidas económicas totales: " + perdidasTotales.call());
                System.out.println("Turno cerrado correctamente.");
            } catch (Exception e) {
                System.out.println("Error al cerrar el turno: " + e.getMessage());
            }
        };

        cierreTurno.run();

        // Reporte final consolidado
        Runnable reporteFinal = () -> {
            try {
                System.out.println("\n========================================");
                System.out.println("             REPORTE FINAL");
                System.out.println("========================================");

                //  Líneas existentes
                List<String> lineasExistentes = registros.stream()
                        .map(RegistroProduccion::getLineaProduccion)
                        .distinct()
                        .collect(Collectors.toList());
                System.out.println("- Líneas existentes: " + lineasExistentes);


                //  Producción total por línea
                Map<String, Integer> totalPorLinea = registros.stream()
                        .collect(Collectors.groupingBy(
                                RegistroProduccion::getLineaProduccion,
                                Collectors.summingInt(RegistroProduccion::getCantidadProducida)
                        ));
                System.out.println("- Producción total por línea: " + totalPorLinea);


                //  Productos críticos (niveles altos de defectos)
                List<String> criticos = productosDefectuosos.stream()
                        .map(RegistroProduccion::getNombreProducto)
                        .collect(Collectors.toList());
                System.out.println("- Productos críticos: " + criticos);


                // Productos que superaron la meta
                List<String> superaronMeta = productosCumplenMeta.stream()
                        .map(RegistroProduccion::getNombreProducto)
                        .collect(Collectors.toList());
                System.out.println("- Productos que superaron la meta: " + superaronMeta);


                //  Unidades defectuosas
                int unidadesDefectuosas = registros.stream()
                        .map(RegistroProduccion::getCantidadDefectuosa)
                        .reduce(0, Integer::sum);
                System.out.println("- Unidades defectuosas: " + unidadesDefectuosas);


                //  Costo total de producción
                System.out.println("- Costo total de producción: " + costoTotalProduccion.call());


                // Pérdidas económicas
                System.out.println("- Pérdidas económicas: " + perdidasTotales.call());


                // Producto con mayor pérdida
                Optional<RegistroProduccion> mayorPerdida = registros.stream()
                        .max(Comparator.comparing(r -> r.getCantidadDefectuosa() * r.getCostoUnitario()));
                RegistroProduccion prodMayorPerdida = mayorPerdida.get();
                double valorMayorPerdida = prodMayorPerdida.getCantidadDefectuosa() * prodMayorPerdida.getCostoUnitario();
                System.out.println("- Producto con mayor pérdida: " + prodMayorPerdida.getNombreProducto() + " ($" + valorMayorPerdida + ")");

                
                // 9. Línea con mayor cantidad producida
                Map.Entry<String, Integer> lineaMayorProduccion = totalPorLinea.entrySet().stream()
                        .max(Map.Entry.comparingByValue())
                        .get();
                System.out.println("- Línea con mayor cantidad producida: " + lineaMayorProduccion.getKey() + " (" + lineaMayorProduccion.getValue() + " unidades)");

                System.out.println("========================================");
            } catch (Exception e) {
                System.out.println("Error al generar el reporte final: " + e.getMessage());
            }
        };

        reporteFinal.run();

    }


    
}