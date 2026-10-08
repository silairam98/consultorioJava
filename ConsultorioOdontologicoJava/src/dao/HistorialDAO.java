package dao;

import conexion.ConexionMySQL;
import modelo.Historial;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HistorialDAO {

    // REGISTRAR HISTORIAL
	public int registrarHistorial(Historial historial) {

	    String sql =
	            "INSERT INTO historial_clinico "
	            + "(paciente_id, fecha, consulta, tratamiento, observaciones) "
	            + "VALUES (?, ?, ?, ?, ?)";

	    try {

	        Connection conexion =
	                ConexionMySQL.obtenerConexion();

	        PreparedStatement statement =
	                conexion.prepareStatement(
	                        sql,
	                        java.sql.Statement.RETURN_GENERATED_KEYS
	                );

	        statement.setInt(
	                1,
	                historial.getPacienteId()
	        );

	        statement.setDate(
	                2,
	                historial.getFecha()
	        );

	        statement.setString(
	                3,
	                historial.getConsulta()
	        );

	        statement.setString(
	                4,
	                historial.getTratamiento()
	        );

	        statement.setString(
	                5,
	                historial.getObservaciones()
	        );

	        statement.executeUpdate();

	        ResultSet resultado =
	                statement.getGeneratedKeys();

	        if (resultado.next()) {

	            int id =
	                    resultado.getInt(1);

	            resultado.close();
	            statement.close();
	            conexion.close();

	            return id;
	        }

	        resultado.close();
	        statement.close();
	        conexion.close();

	    } catch (SQLException e) {

	        System.out.println(
	                "Error al registrar historial:"
	        );

	        System.out.println(
	                e.getMessage()
	        );
	    }

	    return 0;
	}

    // BUSCAR HISTORIAL POR PACIENTE
    public List<Historial> buscarPorPaciente(int pacienteId) {

        List<Historial> lista =
                new ArrayList<>();

        String sql =
                "SELECT * FROM historial_clinico "
                + "WHERE paciente_id = ? "
                + "ORDER BY fecha DESC";

        try {

            Connection conexion =
                    ConexionMySQL.obtenerConexion();

            PreparedStatement statement =
                    conexion.prepareStatement(sql);

            statement.setInt(1, pacienteId);

            ResultSet resultado =
                    statement.executeQuery();

            while (resultado.next()) {

                Historial historial =
                        new Historial();

                historial.setId(
                        resultado.getInt("id")
                );

                historial.setPacienteId(
                        resultado.getInt("paciente_id")
                );

                historial.setFecha(
                        resultado.getDate("fecha")
                );

                historial.setConsulta(
                        resultado.getString("consulta")
                );

                historial.setTratamiento(
                        resultado.getString("tratamiento")
                );

                historial.setObservaciones(
                        resultado.getString("observaciones")
                );

                lista.add(historial);
            }

            resultado.close();
            statement.close();
            conexion.close();

        } catch (SQLException e) {

            System.out.println("Error al buscar historial:");
            System.out.println(e.getMessage());
        }

        return lista;
    }
}