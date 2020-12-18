package FuncionesDeMenu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ConectarBBDD.ConexionAdmin;
import utilidades.Leer;

public class MetodosDelMenu {
	public static void anadirClientes() {
		System.out.println("Nombre del cliente: ");
		String nombreCliente = Leer.pedirCadena();

		System.out.println("Nombre de contacto: ");
		String nombreContacto = Leer.pedirCadena();

		System.out.println("Apellido de contacto: ");
		String apellidoContacto = Leer.pedirCadena();

		System.out.println("Telefono: ");
		String telefono = Leer.pedirCadena();

		System.out.println("Fax: ");
		String fax = Leer.pedirCadena();

		System.out.println("Linea de direccion: ");
		String lineaDireccion = Leer.pedirCadena();

		System.out.println("Linea de direccion 2: ");
		String lineaDireccion2 = Leer.pedirCadena();

		System.out.println("Ciudad: ");
		String ciudad = Leer.pedirCadena();

		System.out.println("Region: ");
		String region = Leer.pedirCadena();

		System.out.println("Pais: ");
		String pais = Leer.pedirCadena();

		System.out.println("Codigo postal: ");
		String codigoPostal = Leer.pedirCadena();

		System.out.println("Codigo empleado rep ventas: ");
		int codigoEmpleadoRepVentas = Leer.pedirEnteroValidar();

		System.out.println("Limite de credito: ");
		double limiteCredito = Leer.pedirDecimal();
	}

	public static void mostrarCliente() {
		System.out.println("¿Que cliente quieres mostrar? (NECESITO NUMERO/S)");
		int codigoCliente = Leer.pedirEnteroValidar(), clientesTotales = 0;
		Connection conexion = null;
		Statement statement = null;
		ConexionAdmin.abrirConexion(conexion, statement);
		try {
			ResultSet resultSet = statement.executeQuery("select * from cliente");
			while (resultSet.next()) {
				clientesTotales++;
			}

			resultSet = statement.executeQuery("select * from cliente where codigo_cliente = " + codigoCliente);

			System.out.println("Mostrando cliente...");
			while (resultSet.next()) {
				System.out.print(resultSet.getInt("codigo_cliente") + "\t");
				System.out.print(resultSet.getString("nombre_cliente") + "\t");
				System.out.print(resultSet.getString("nombre_contacto") + "\t");
				System.out.print(resultSet.getString("apellido_contacto") + "\t");
				System.out.print(resultSet.getString("telefono") + "\t");
				System.out.print(resultSet.getString("fax") + "\t");
				System.out.print(resultSet.getString("linea_direccion1") + "\t");
				System.out.print(resultSet.getString("linea_direccion2") + "\t");
				System.out.print(resultSet.getString("ciudad") + "\t");
				System.out.print(resultSet.getString("region") + "\t");
				System.out.print(resultSet.getString("pais") + "\t");
				System.out.print(resultSet.getString("codigo_postal") + "\t");
				System.out.print(resultSet.getString("codigo_empleado_rep_ventas") + "\t");
				System.out.print(resultSet.getDouble("limite_credito") + "\t");
				System.out.println();
			}
			if (codigoCliente >= clientesTotales) {
				System.out.println("La base de datos contiene " + clientesTotales + " registros.");
			}
			ConexionAdmin.cerrarConexion(conexion);
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		System.out.println("--------------------------------------------------------");
	}

	public static void mostrarTodosLosClientes() {
		System.out.println("Mostrando clientes...");
		int clientesTotales = 0;
		try {
			Connection conexion = null;
			conexion = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/jardineria?useUnicode=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
					"admin", "4DM1n4DM1n");
			Statement statement = conexion.createStatement();
			ResultSet resultSet = statement.executeQuery("select * from cliente");
			while (resultSet.next()) {
				System.out.print(resultSet.getInt("codigo_cliente") + "\t");
				System.out.print(resultSet.getString("nombre_cliente") + "\t");
				System.out.print(resultSet.getString("nombre_contacto") + "\t");
				System.out.print(resultSet.getString("apellido_contacto") + "\t");
				System.out.print(resultSet.getString("telefono") + "\t");
				System.out.print(resultSet.getString("fax") + "\t");
				System.out.print(resultSet.getString("linea_direccion1") + "\t");
				System.out.print(resultSet.getString("linea_direccion2") + "\t");
				System.out.print(resultSet.getString("ciudad") + "\t");
				System.out.print(resultSet.getString("region") + "\t");
				System.out.print(resultSet.getString("pais") + "\t");
				System.out.print(resultSet.getString("codigo_postal") + "\t");
				System.out.print(resultSet.getString("codigo_empleado_rep_ventas") + "\t");
				System.out.print(resultSet.getDouble("limite_credito") + "\t");
				System.out.println();
				clientesTotales++;
			}
			conexion.close();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		System.out.println(
				"Clientes en total: " + clientesTotales + "\n--------------------------------------------------------");
	}

	public static void buscarClientes() {
		System.out.println("¿Que cliente desea buscar? (NECESITO TEXTO)");
		String cadenaDeTexto = Leer.pedirCadena();
		try {
			Connection conexion = null;
			conexion = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/jardineria?useUnicode=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
					"admin", "4DM1n4DM1n");
			Statement statement = conexion.createStatement();
			ResultSet resultSet = statement
					.executeQuery("select * from cliente where nombre_cliente = '" + cadenaDeTexto + "' or nombre_contacto = '" + cadenaDeTexto + "' or apellido_contacto = '" + cadenaDeTexto + "'");
			while (resultSet.next()) {
				System.out.print(resultSet.getInt("codigo_cliente") + "\t");
				System.out.print(resultSet.getString("nombre_cliente") + "\t");
				System.out.print(resultSet.getString("nombre_contacto") + "\t");
				System.out.print(resultSet.getString("apellido_contacto") + "\t");
				System.out.print(resultSet.getString("telefono") + "\t");
				System.out.print(resultSet.getString("fax") + "\t");
				System.out.print(resultSet.getString("linea_direccion1") + "\t");
				System.out.print(resultSet.getString("linea_direccion2") + "\t");
				System.out.print(resultSet.getString("ciudad") + "\t");
				System.out.print(resultSet.getString("region") + "\t");
				System.out.print(resultSet.getString("pais") + "\t");
				System.out.print(resultSet.getString("codigo_postal") + "\t");
				System.out.print(resultSet.getInt("codigo_empleado_rep_ventas") + "\t");
				System.out.print(resultSet.getDouble("limite_credito") + "\t");
				System.out.println();
			}
			conexion.close();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}

	public static void editarProducto() {

	}
}