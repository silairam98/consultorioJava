package dao;

import conexion.ConexionMySQL;
import modelo.Paciente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PacienteDAO {

    // REGISTRAR PACIENTE
    public boolean registrarPaciente(Paciente paciente) {

        String sql = "INSERT INTO pacientes "
                   + "(cedula, nombre, apellido, telefono, fecha_nacimiento, direccion) "
                   + "VALUES (?, ?, ?, ?, ?, ?)";

        try {

            Connection conexion =
                    ConexionMySQL.obtenerConexion();

            PreparedStatement statement =
                    conexion.prepareStatement(sql);

            statement.setString(1, paciente.getCedula());
            statement.setString(2, paciente.getNombre());
            statement.setString(3, paciente.getApellido());
            statement.setString(4, paciente.getTelefono());
            statement.setDate(5, paciente.getFechaNacimiento());
            statement.setString(6, paciente.getDireccion());

            statement.executeUpdate();

            statement.close();
            conexion.close();

            return true;

        } catch (SQLException e) {

            System.out.println("Error al registrar paciente:");
            System.out.println(e.getMessage());

            return false;
        }
    }

    // BUSCAR PACIENTE POR CÉDULA
    public Paciente buscarPorCedula(String cedula) {

        String sql =
                "SELECT * FROM pacientes WHERE cedula = ?";

        try {

            Connection conexion =
                    ConexionMySQL.obtenerConexion();

            PreparedStatement statement =
                    conexion.prepareStatement(sql);

            statement.setString(1, cedula);

            ResultSet resultado =
                    statement.executeQuery();

            if (resultado.next()) {

                Paciente paciente = new Paciente();

                paciente.setId(
                        resultado.getInt("id")
                );

                paciente.setCedula(
                        resultado.getString("cedula")
                );

                paciente.setNombre(
                        resultado.getString("nombre")
                );

                paciente.setApellido(
                        resultado.getString("apellido")
                );

                paciente.setTelefono(
                        resultado.getString("telefono")
                );

                paciente.setFechaNacimiento(
                        resultado.getDate("fecha_nacimiento")
                );

                paciente.setDireccion(
                        resultado.getString("direccion")
                );

                resultado.close();
                statement.close();
                conexion.close();

                return paciente;
            }

            resultado.close();
            statement.close();
            conexion.close();

        } catch (SQLException e) {

            System.out.println("Error al buscar paciente:");
            System.out.println(e.getMessage());
        }

        return null;
    }

    // ACTUALIZAR PACIENTE
    public boolean actualizarPaciente(Paciente paciente) {

        String sql = "UPDATE pacientes SET "
                   + "cedula = ?, "
                   + "nombre = ?, "
                   + "apellido = ?, "
                   + "telefono = ?, "
                   + "fecha_nacimiento = ?, "
                   + "direccion = ? "
                   + "WHERE id = ?";

        try {

            Connection conexion =
                    ConexionMySQL.obtenerConexion();

            PreparedStatement statement =
                    conexion.prepareStatement(sql);

            statement.setString(1, paciente.getCedula());
            statement.setString(2, paciente.getNombre());
            statement.setString(3, paciente.getApellido());
            statement.setString(4, paciente.getTelefono());
            statement.setDate(5, paciente.getFechaNacimiento());
            statement.setString(6, paciente.getDireccion());
            statement.setInt(7, paciente.getId());

            int filasModificadas =
                    statement.executeUpdate();

            statement.close();
            conexion.close();

            return filasModificadas > 0;

        } catch (SQLException e) {

            System.out.println("Error al actualizar paciente:");
            System.out.println(e.getMessage());

            return false;
        }
    }

    // ELIMINAR PACIENTE
    public boolean eliminarPaciente(int id) {

        String sql =
                "DELETE FROM pacientes WHERE id = ?";

        try {

            Connection conexion =
                    ConexionMySQL.obtenerConexion();

            PreparedStatement statement =
                    conexion.prepareStatement(sql);

            statement.setInt(1, id);

            int filasEliminadas =
                    statement.executeUpdate();

            statement.close();
            conexion.close();

            return filasEliminadas > 0;

        } catch (SQLException e) {

            System.out.println("Error al eliminar paciente:");
            System.out.println(e.getMessage());

            return false;
        }
    }
    public int contarPacientes() {

        String sql = "SELECT COUNT(*) FROM pacientes";

        try {
            Connection conexion = ConexionMySQL.obtenerConexion();

            PreparedStatement statement =
                    conexion.prepareStatement(sql);

            ResultSet resultado =
                    statement.executeQuery();

            if (resultado.next()) {

                int cantidad =
                        resultado.getInt(1);

                resultado.close();
                statement.close();
                conexion.close();

                return cantidad;
            }

            resultado.close();
            statement.close();
            conexion.close();

        } catch (SQLException e) {

            System.out.println(
                    "Error al contar pacientes:"
            );

            System.out.println(
                    e.getMessage()
            );
        }

        return 0;
    }
}