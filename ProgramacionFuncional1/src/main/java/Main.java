import EJ1.Alumno;
import EJ2.Producto;
import EJ3.Libro;
import EJ4.Empleado;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        System.out.println("EJERCICIO 1");
        List<Alumno> alumnos = Arrays.asList(
                new Alumno("Ana García", 8.5, "Java Avanzado"),
                new Alumno("Carlos López", 6.2, "Python"),
                new Alumno("María Rodríguez", 9.1, "Java Avanzado"),
                new Alumno("Pedro Martínez", 7.8, "Python"),
                new Alumno("Laura Sánchez", 5.5, "JavaScript"),
                new Alumno("Diego Fernández", 9.5, "Java Avanzado"),
                new Alumno("Sofía Torres", 7.0, "JavaScript"),
                new Alumno("Javier Ruiz", 8.2, "Python"),
                new Alumno("Carmen Díaz", 6.8, "JavaScript"),
                new Alumno("Roberto Morales", 9.8, "Java Avanzado")
        );
        List<String> aprobados = alumnos.stream()
                .filter(alumno -> alumno.getNota()>=7)
                .map(alumno -> alumno.getNombre().toUpperCase())
                .sorted()
                .toList();
        aprobados.forEach(nombre-> System.out.println("Aprobo: "+ nombre));

        double promedioGeneral = alumnos.stream()
                .mapToDouble(Alumno::getNota)
                .average()
                .orElse(0.0);
        System.out.println("PromedioGeneral = "+String.format("%.2f", promedioGeneral));

        Map<String, List<Alumno>> alumnosPorCurso = alumnos.stream()
                .collect(Collectors.groupingBy(Alumno::getCurso));
        alumnosPorCurso.forEach((curso, listaAlumnos) -> {
            System.out.println("Curso: " + curso);
            listaAlumnos.forEach(alumno -> System.out.println(alumno.getNombre()+", nota:"+ alumno.getNota()));
        });

        List<Alumno> top3 = alumnos.stream()
                .sorted(Comparator.comparingDouble(Alumno::getNota).reversed())
                .limit(3)
                .toList();
        int posicion = 1;
        for (Alumno alumno : top3) {
            System.out.println(posicion++ +": "+alumno.getNombre()+", nota: "+alumno.getNota());
        }

        System.out.println("EJERCICIO 2");
        List<Producto> productos = Arrays.asList(
                new Producto("Laptop Dell", "Electrónica", 1200.00, 15),
                new Producto("Mouse Logitech", "Electrónica", 45.50, 50),
                new Producto("Teclado Mecánico", "Electrónica", 89.99, 30),
                new Producto("Monitor Samsung", "Electrónica", 350.00, 20),
                new Producto("Escritorio Ikea", "Muebles", 250.00, 10),
                new Producto("Silla Ergonómica", "Muebles", 180.00, 25),
                new Producto("Lámpara LED", "Muebles", 35.00, 40),
                new Producto("Cafetera Nespresso", "Electrodomésticos", 150.00, 12),
                new Producto("Licuadora Oster", "Electrodomésticos", 85.00, 18),
                new Producto("Microondas LG", "Electrodomésticos", 120.00, 8)
        );

        List<Producto> productosCaros = productos.stream()
                .filter(p -> p.getPrecio() > 100)
                .sorted(Comparator.comparingDouble(Producto::getPrecio).reversed())
                .toList();
        productosCaros.forEach(p ->
                System.out.println(p.getNombre() + " - $" + p.getPrecio() + " (" + p.getCategoria() + ")")
        );

        Map<String, Integer> stockPorCategoria = productos.stream()
                .collect(Collectors.groupingBy(
                        Producto::getCategoria,
                        Collectors.summingInt(Producto::getStock)
                ));
        stockPorCategoria.forEach((categoria, stock) ->
                System.out.println(categoria + ": " + stock + " unidades")
        );

        String reporteCSV = productos.stream()
                .map(p -> p.getNombre() + ";" + p.getPrecio())
                .collect(Collectors.joining("\n"));
        System.out.println(reporteCSV);

        double promedioGeneralProducto = productos.stream()
                .mapToDouble(Producto::getPrecio)
                .average()
                .orElse(0.0);
        System.out.println("Promedio General: $" + String.format("%.2f", promedioGeneralProducto));

        System.out.println("\nPromedio por Categoría:");
        Map<String, Double> promedioPorCategoria = productos.stream()
                .collect(Collectors.groupingBy(
                        Producto::getCategoria,
                        Collectors.averagingDouble(Producto::getPrecio)
                ));
        promedioPorCategoria.forEach((categoria, promedio) ->
                System.out.println(categoria + ": $" + String.format("%.2f", promedio))
        );

        System.out.println("EJERCICIO 3");
        List<Libro> libros = Arrays.asList(
                new Libro("Cien años de soledad", "Gabriel García Márquez", 471, 35.50),
                new Libro("El amor en los tiempos del cólera", "Gabriel García Márquez", 368, 32.00),
                new Libro("Don Quijote de la Mancha", "Miguel de Cervantes", 863, 45.00),
                new Libro("1984", "George Orwell", 328, 28.50),
                new Libro("Rebelión en la granja", "George Orwell", 144, 18.00),
                new Libro("El principito", "Antoine de Saint-Exupéry", 96, 15.50),
                new Libro("Crónica de una muerte anunciada", "Gabriel García Márquez", 120, 22.00),
                new Libro("La sombra del viento", "Carlos Ruiz Zafón", 576, 38.00),
                new Libro("El juego del ángel", "Carlos Ruiz Zafón", 672, 42.00),
                new Libro("Rayuela", "Julio Cortázar", 736, 40.00)
        );

        List<String> librosMas300 = libros.stream()
                .filter(libro -> libro.getPaginas() > 300)
                .map(Libro::getTitulo)
                .sorted()
                .toList();
        librosMas300.forEach(titulo -> System.out.println(titulo));

        double promedioPaginas = libros.stream()
                .mapToInt(Libro::getPaginas)
                .average()
                .orElse(0.0);
        System.out.println("Promedio de páginas: " + String.format("%.2f", promedioPaginas));

        Map<String, Long> librosPorAutor = libros.stream()
                .collect(Collectors.groupingBy(Libro::getAutor, Collectors.counting()));
        librosPorAutor.forEach((autor, cantidad) ->
                System.out.println(autor + ": " + cantidad + " libro(s)")
        );

        libros.stream()
                .max(Comparator.comparingDouble(Libro::getPrecio))
                .ifPresent(libro -> System.out.println(libro.getTitulo() + " - $" + libro.getPrecio() + " (" + libro.getAutor() + ")"));

        System.out.println("EJERCICIO 3");
        List<Empleado> empleados = Arrays.asList(
                new Empleado("Ana García", "IT", 3500.00, 28),
                new Empleado("Carlos López", "Ventas", 2800.00, 35),
                new Empleado("María Rodríguez", "IT", 4200.00, 32),
                new Empleado("Pedro Martínez", "RRHH", 2500.00, 42),
                new Empleado("Laura Sánchez", "Ventas", 1800.00, 25),
                new Empleado("Diego Fernández", "IT", 3800.00, 29),
                new Empleado("Sofía Torres", "RRHH", 2200.00, 38),
                new Empleado("Javier Ruiz", "Ventas", 3100.00, 31),
                new Empleado("Carmen Díaz", "Marketing", 2700.00, 27),
                new Empleado("Roberto Morales", "Marketing", 3300.00, 33)
        );

        List<Empleado> empleadosSalarioAlto = empleados.stream()
                .filter(emp -> emp.getSalario() > 2000)
                .sorted(Comparator.comparingDouble(Empleado::getSalario).reversed())
                .toList();
        empleadosSalarioAlto.forEach(emp ->
                System.out.println(emp.getNombre() + " - $" + emp.getSalario() + " (" + emp.getDepartamento() + ")")
        );

        double salarioPromedio = empleados.stream()
                .mapToDouble(Empleado::getSalario)
                .average()
                .orElse(0.0);
        System.out.println("Salario promedio: $" + String.format("%.2f", salarioPromedio));

        Map<String, Double> salariosPorDepartamento = empleados.stream()
                .collect(Collectors.groupingBy(
                        Empleado::getDepartamento,
                        Collectors.summingDouble(Empleado::getSalario)
                ));
        salariosPorDepartamento.forEach((depto, total) ->
                System.out.println(depto + ": $" + String.format("%.2f", total))
        );

        List<String> masJovenes = empleados.stream()
                .sorted(Comparator.comparingInt(Empleado::getEdad))
                .limit(2)
                .map(Empleado::getNombre)
                .toList();
        int pos = 1;
        for (String nombre : masJovenes) {
            System.out.println(pos++ + ". " + nombre);
        }
    }
}