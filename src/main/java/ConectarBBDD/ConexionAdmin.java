package ConectarBBDD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexionAdmin {
	public static Statement abrirConexion(Connection conexion, Statement statement) {
		try {
			conexion = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/jardineria?useUnicode=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
					"admin", "4DM1n4DM1n");
			statement = conexion.createStatement();
		} catch (SQLException sqle) {
			System.out.println("No se ha podido abrir la conexion.");
		}
		return statement;
	}
	
	public static void cerrarConexion(Connection conexion) {
		try {
			conexion.close();
		} catch (SQLException e) {
			System.out.println("No se ha podido cerrar la conexion.");
		}
	}
}