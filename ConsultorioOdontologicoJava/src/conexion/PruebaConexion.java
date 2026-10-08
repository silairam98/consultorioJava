package conexion;

import java.sql.Connection;
import java.sql.SQLException;

public class PruebaConexion {

    public static void main(String[] args) {

        try {

            Connection conexion = ConexionMySQL.obtenerConexion();

            System.out.println("¡Conexión exitosa a MySQL!");
            System.out.println("Base de datos: " + conexion.getCatalog());

            conexion.close();

        } catch (SQLException e) {

            System.out.println("Error al conectar:");
            System.out.println(e.getMessage());
        }
    }
}
