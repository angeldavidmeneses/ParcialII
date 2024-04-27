package parcial.main;

import java.util.List;
import java.util.ArrayList;

// Interfaz para el cálculo de descuentos
interface CalculadorDescuento {
    double calcularDescuento(Tienda tienda);
}

// Clase abstracta para diferentes tipos de comercio
abstract class TipoComercio {
    abstract double obtenerDescuento();
}

// Implementación de TipoComercio para un tipo específico (por ejemplo,
// electrónica)
class ComercioElectronico extends TipoComercio {
    @Override
    double obtenerDescuento() {
        return 0.1; // Descuento del 10% para productos electrónicos
    }
}

// Clase Tienda que implementa la interfaz CalculadorDescuento
class Tienda implements CalculadorDescuento {
    private String nombre;
    private TipoComercio tipoComercio;

    public Tienda(String nombre, TipoComercio tipoComercio) {
        this.nombre = nombre;
        this.tipoComercio = tipoComercio;
    }

    @Override
    public double calcularDescuento(Tienda tienda) {
        return tipoComercio.obtenerDescuento();
    }
}

// Clase CentroComercial que maneja la lista de tiendas y calcula descuentos
public class CentroComercial {
    private List<Tienda> tiendas;

    public CentroComercial(List<Tienda> tiendas) {
        this.tiendas = tiendas;
    }

    public List<Tienda> getTiendas() {
        return tiendas;
    }

    // Método para calcular el descuento total usando switch case
    public double calcularDescuento(TipoComercio tipoComercio) {
        double descuentoTotal = 0;
        for (Tienda tienda : tiendas) {
            switch (tipoComercio.getClass().getSimpleName()) {
                case "ComercioElectronico":
                    descuentoTotal += tienda.calcularDescuento(tienda);
                    break;
                // Agregar más casos para otros tipos de comercio si es necesario
            }
        }
        return descuentoTotal;
    }

    public static void main(String[] args) {
        // Crear instancias de tiendas con diferentes tipos de comercio
        Tienda tienda1 = new Tienda("ElectroTienda", new ComercioElectronico());
        Tienda tienda2 = new Tienda("RopaShop", new ComercioElectronico());
        // Agregar más tiendas con otros tipos de comercio si es necesario

        // Crear lista de tiendas
        List<Tienda> tiendas = new ArrayList<>();
        tiendas.add(tienda1);
        tiendas.add(tienda2);
        // Agregar más tiendas a la lista si es necesario

        // Crear instancia de CentroComercial con la lista de tiendas
        CentroComercial centroComercial = new CentroComercial(tiendas);

        // Calcular descuento total para un tipo de comercio específico (por ejemplo,
        // electrónica)
        double descuentoElectronico = centroComercial.calcularDescuento(new ComercioElectronico());
        System.out.println("Descuento total para productos electrónicos: " + descuentoElectronico);
    }
}
