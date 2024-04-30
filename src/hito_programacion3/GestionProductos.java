package hito_programacion3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date; // Importación necesaria para el tipo de dato Date
import java.util.Scanner;
import java.sql.ResultSet;

public class GestionProductos {
    // Definición de las constantes para la conexión a la base de datos
    private static final String URL = "jdbc:mysql://localhost:3306/gestion_productos";
    private static final String USUARIO = "root";
    private static final String CONTRASEÑA = "";

    public static void main(String[] args) {
        try (Connection conexion = DriverManager.getConnection(URL, USUARIO, CONTRASEÑA)) {
            // Mensaje de conexión exitosa a la base de datos
            System.out.println("Conexión exitosa a la base de datos");
            
            Scanner scanner = new Scanner(System.in);
            int opcion;
            do {
                // Menú de opciones para el usuario
                System.out.println("Menú:");
                System.out.println("1. Añadir producto");
                System.out.println("2. Eliminar producto");
                System.out.println("3. Actualizar producto");
                System.out.println("4. Mostrar productos");
                System.out.println("5. Salir");
                System.out.print("Seleccione una opción: ");
                opcion = scanner.nextInt();
                
                switch (opcion) {
                case 1:
                    // Llamada a la función para agregar un producto
                    agregarProducto(conexion, scanner);
                    break;
                case 2:
                	// Lógica para eliminar producto
                	eliminarProducto(conexion,scanner);
                    break;
                case 3:
                    // Lógica para actualizar producto
                	actualizarProducto(conexion,scanner);
                    break;
                case 4:
                    // Lógica para mostrar productos
                	mostrarProductos(conexion);
                    break;
                case 5:
                    // Mensaje de salida del programa
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    // Mensaje de opción inválida
                    System.out.println("Opción inválida");
            }
        } while (opcion != 5);
        
    } catch (SQLException e) {
        // Manejo de excepción en caso de error de conexión
        System.err.println("Error al conectar a la base de datos");
        e.printStackTrace();
    }
}

// Función para agregar un producto a la base de datos
private static void agregarProducto(Connection conexion, Scanner scanner) {
    try {
        // Solicitar al usuario los datos del producto
        System.out.println("Ingrese el nombre del producto:");
        String nombre = scanner.next();
        System.out.println("Ingrese la fecha de envasado (AAAA-MM-DD):");
        String fechaEnvasado = scanner.next();
        System.out.println("Ingrese el número de unidades:");
        int unidades = scanner.nextInt();
        System.out.println("Ingrese el precio:");
        double precio = scanner.nextDouble();
        System.out.println("¿Está disponible? (true/false):");
        boolean disponible = scanner.nextBoolean();

        // Consulta SQL para insertar el producto en la base de datos
        String consulta = "INSERT INTO productos (nombre, fechaEnvasado, unidades, precio, disponible) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = conexion.prepareStatement(consulta)) {
            // Asignar los valores a los parámetros de la consulta
            statement.setString(1, nombre);
            statement.setString(2, fechaEnvasado);
            statement.setInt(3, unidades);
            statement.setDouble(4, precio);
            statement.setBoolean(5, disponible);

            // Ejecutar la consulta y comprobar si se insertaron filas
            int filasInsertadas = statement.executeUpdate();
            if (filasInsertadas > 0) {
                System.out.println("Producto agregado correctamente");
            } else {
                System.out.println("No se pudo agregar el producto");
            }
        }
    } catch (SQLException e) {
        // Manejo de excepción en caso de error al agregar el producto
        System.err.println("Error al agregar el producto");
        e.printStackTrace();
    }
}


// Función para eliminar un producto de la base de datos
private static void eliminarProducto(Connection conexion, Scanner scanner) {
    try {
        // Solicitar al usuario el ID del producto que desea eliminar
        System.out.println("Ingrese el ID del producto que desea eliminar:");
        int idProducto = scanner.nextInt();

        // Consulta SQL para eliminar el producto de la base de datos
        String consulta = "DELETE FROM productos WHERE idProducto = ?";
        try (PreparedStatement statement = conexion.prepareStatement(consulta)) {
            // Asignar el valor del IDProducto al parámetro de la consulta
            statement.setInt(1, idProducto);

            // Ejecutar la consulta y comprobar si se eliminaron filas
            int filasEliminadas = statement.executeUpdate();
            if (filasEliminadas > 0) {
                System.out.println("Producto eliminado correctamente");
            } else {
                System.out.println("No se encontró un producto con el ID especificado");
            }
        }
    } catch (SQLException e) {
        // Manejo de excepción en caso de error al eliminar el producto
        System.err.println("Error al eliminar el producto");
        e.printStackTrace();
    }
}

// Otras funciones CRUD (leer, actualizar) pueden ser implementadas de manera similar
private static void actualizarProducto(Connection conexion, Scanner scanner) {
    try {
        // Solicitar al usuario el ID del producto que desea actualizar
        System.out.println("Ingrese el ID del producto que desea actualizar:");
        int idProducto = scanner.nextInt();

        // Solicitar al usuario los nuevos datos del producto
        System.out.println("Ingrese el nuevo nombre del producto:");
        String nuevoNombre = scanner.next();
        System.out.println("Ingrese la nueva fecha de envasado (AAAA-MM-DD):");
        String nuevaFechaEnvasado = scanner.next();
        System.out.println("Ingrese el nuevo número de unidades:");
        int nuevasUnidades = scanner.nextInt();
        System.out.println("Ingrese el nuevo precio:");
        double nuevoPrecio = scanner.nextDouble();
        System.out.println("¿Está disponible? (true/false):");
        boolean nuevaDisponibilidad = scanner.nextBoolean();

        // Consulta SQL para actualizar el producto en la base de datos
        String consulta = "UPDATE productos SET nombre = ?, fechaEnvasado = ?, unidades = ?, precio = ?, disponible = ? WHERE idProducto = ?";
        try (PreparedStatement statement = conexion.prepareStatement(consulta)) {
            // Asignar los nuevos valores a los parámetros de la consulta
            statement.setString(1, nuevoNombre);
            statement.setString(2, nuevaFechaEnvasado);
            statement.setInt(3, nuevasUnidades);
            statement.setDouble(4, nuevoPrecio);
            statement.setBoolean(5, nuevaDisponibilidad);
            statement.setInt(6, idProducto);

            // Ejecutar la consulta y comprobar si se actualizaron filas
            int filasActualizadas = statement.executeUpdate();
            if (filasActualizadas > 0) {
                System.out.println("Producto actualizado correctamente");
            } else {
                System.out.println("No se encontró un producto con el ID especificado");
            }
        }
    } catch (SQLException e) {
        // Manejo de excepción en caso de error al actualizar el producto
        System.err.println("Error al actualizar el producto");
        e.printStackTrace();
    }
}

// Función para mostrar todos los productos almacenados en la base de datos
private static void mostrarProductos(Connection conexion) {
    try {
        // Consulta SQL para seleccionar todos los productos de la base de datos
        String consulta = "SELECT * FROM productos";
        try (PreparedStatement statement = conexion.prepareStatement(consulta)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                // Encabezado de la tabla de productos
                System.out.println("Productos:");
                System.out.println("ID\tNombre\tFecha de envasado\tUnidades\tPrecio\tDisponible");
                // Iterar sobre los resultados y mostrar cada producto
                while (resultSet.next()) {
                    int idProducto = resultSet.getInt("idProducto");
                    String nombre = resultSet.getString("nombre");
                    String fechaEnvasado = resultSet.getString("fechaEnvasado");
                    int unidades = resultSet.getInt("unidades");
                    double precio = resultSet.getDouble("precio");
                    boolean disponible = resultSet.getBoolean("disponible");

                    // Mostrar los datos del producto en formato tabular
                    System.out.println(idProducto + "\t" + nombre + "\t" + fechaEnvasado + "\t" + unidades + "\t" + precio + "\t" + disponible);
                }
            }
        }
    } catch (SQLException e) {
        // Manejo de excepción en caso de error al mostrar los productos
        System.err.println("Error al mostrar los productos");
        e.printStackTrace();
    }
}
}
