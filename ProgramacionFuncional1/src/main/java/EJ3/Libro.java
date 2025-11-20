package EJ3;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class Libro {
    private String titulo;
    private String autor;
    private int paginas;
    private double precio;
}
