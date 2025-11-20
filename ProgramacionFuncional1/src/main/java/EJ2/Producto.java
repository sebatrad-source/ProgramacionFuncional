package EJ2;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class Producto {
    private String nombre;
    private String categoria;
    private double precio;
    private int stock;
}
