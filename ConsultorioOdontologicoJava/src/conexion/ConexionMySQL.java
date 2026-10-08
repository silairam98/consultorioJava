package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionMySQL {
		

		    private static final String URL =
		            "jdbc:mysql://localhost:3306/consultorio_java"
		            + "?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";

		    private static final String USUARIO = "root";
		    private static final String PASSWORD = "";

		    private ConexionMySQL() {
		    }

		    public static Connection obtenerConexion() throws SQLException {
		        return DriverManager.getConnection(URL, USUARIO, PASSWORD);
		    }
	

	}


