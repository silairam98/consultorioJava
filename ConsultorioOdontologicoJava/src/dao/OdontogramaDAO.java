package dao;

import conexion.ConexionMySQL;
import modelo.Odontograma;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OdontogramaDAO {

    public boolean registrarOdontograma(
            List<Odontograma> dientes) {

        String sql =
                "INSERT INTO odontograma "
                + "(historial_id, numero_diente, estado, observacion) "
                + "VALUES (?, ?, ?, ?)";

        try {

            Connection conexion =
                    ConexionMySQL.obtenerConexion();

            PreparedStatement statement =
                    conexion.prepareStatement(sql);

            for (Odontograma diente : dientes) {

                statement.setInt(
                        1,
                        diente.getHistorialId()
                );

                statement.setString(
                        2,
                        diente.getNumeroDiente()
                );

                statement.setString(
                        3,
                        diente.getEstado()
                );

                statement.setString(
                        4,
                        diente.getObservacion()
                );

                statement.addBatch();
            }

            statement.executeBatch();

            statement.close();
            conexion.close();

            return true;

        } catch (SQLException e) {

            System.out.println(
                    "Error al registrar odontograma:"
            );

            System.out.println(
                    e.getMessage()
            );

            return false;
        }
    }


    public List<Odontograma> buscarPorHistorial(
            int historialId) {

        List<Odontograma> lista =
                new ArrayList<>();

        String sql =
                "SELECT * FROM odontograma "
                + "WHERE historial_id = ? "
                + "ORDER BY numero_diente";

        try {

            Connection conexion =
                    ConexionMySQL.obtenerConexion();

            PreparedStatement statement =
                    conexion.prepareStatement(sql);

            statement.setInt(
                    1,
                    historialId
            );

            ResultSet resultado =
                    statement.executeQuery();

            while (resultado.next()) {

                Odontograma diente =
                        new Odontograma();

                diente.setId(
                        resultado.getInt("id")
                );

                diente.setHistorialId(
                        resultado.getInt("historial_id")
                );

                diente.setNumeroDiente(
                        resultado.getString(
                                "numero_diente"
                        )
                );

                diente.setEstado(
                        resultado.getString("estado")
                );

                diente.setObservacion(
                        resultado.getString(
                                "observacion"
                        )
                );

                lista.add(diente);
            }

            resultado.close();
            statement.close();
            conexion.close();

        } catch (SQLException e) {

            System.out.println(
                    "Error al buscar odontograma:"
            );

            System.out.println(
                    e.getMessage()
            );
        }

        return lista;
    }
}