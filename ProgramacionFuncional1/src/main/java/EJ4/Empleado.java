package EJ4;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class Empleado {
    private String nombre;
    private String departamento;
    private double salario;
    private int edad;
}