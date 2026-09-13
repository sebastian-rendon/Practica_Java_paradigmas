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
import java.util.function.*;
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

