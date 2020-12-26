package FuncionesDeMenu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import utilidades.Leer;

public class MetodosDelMenu {
	public static void anadirClientes(String usuario, String clave) {

		// PEDIMOS LOS DATOS PARA OBTENER EL CLIENTE
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

		int codigoCliente = 0;
		try {
			// INICIAMOS LA CONEXION A LA BBDD CON EL USUARIO Y LA CLAVE QUE HEMOS PEDIDO
			// PREVIAMENTE
			Connection conexion = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/jardineria?useUnicode=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
					usuario, clave);

			// CREAMOS UNA CONSULTA Y LA EJECUTAMOS
			Statement statement = conexion.createStatement();
			ResultSet resultSet = statement.executeQuery("select codigo_cliente from cliente");

			// RECOGEMOS EL CODIGO DE CLIENTE QUE NECESITAMOS PARA OBTENER EL CLIENTE
			while (resultSet.next()) {
				if (resultSet.getInt("codigo_cliente") >= codigoCliente) {
					codigoCliente = resultSet.getInt("codigo_cliente");
				}
			}
			codigoCliente++;

			// PREPARAMOS LA CONSULTA QUE QUEREMOS EJECUTAR EN LA BBDD
			PreparedStatement preparedStatement = conexion
					.prepareStatement("insert into cliente values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

			// INSERTAMOS LOS DATOS DEL NUEVO CLIENTE EN LOS VALORES DEL COMANDO SQL
			// ANTERIOR
			preparedStatement.setInt(1, codigoCliente);
			preparedStatement.setString(2, nombreCliente);
			preparedStatement.setString(3, nombreContacto);
			preparedStatement.setString(4, apellidoContacto);
			preparedStatement.setString(5, telefono);
			preparedStatement.setString(6, fax);
			preparedStatement.setString(7, lineaDireccion);
			preparedStatement.setString(8, lineaDireccion2);
			preparedStatement.setString(9, ciudad);
			preparedStatement.setString(10, region);
			preparedStatement.setString(11, pais);
			preparedStatement.setString(12, codigoPostal);
			preparedStatement.setInt(13, codigoEmpleadoRepVentas);
			preparedStatement.setDouble(14, limiteCredito);

			// EJECUTAMOS EL COMANDO SQL Y VERIFICAMOS SI SE HA INSERTADO CORRECTAMENTE
			int correcto = preparedStatement.executeUpdate();
			if (correcto > 0) {
				System.out.println("Valor insertado correctamente.");
			}
			conexion.close();
		} catch (SQLException sqle) {
			System.out.println("SQLState: " + sqle.getSQLState() + " SQLErrorCode: " + sqle.getErrorCode());
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("--------------------------------------------------------");
	}

	public static void mostrarCliente(String usuario, String clave) {
		// PIDE EL CLIENTE QUE QUIERE MOSTRAR
		System.out.println("¿Que cliente quieres mostrar? (NECESITO NUMERO/S)");
		int codigoCliente = Leer.pedirEnteroValidar(), clientesTotales = 0;

		try {
			// INICIAMOS LA CONEXION A LA BBDD CON EL USUARIO Y LA CLAVE QUE HEMOS PEDIDO
			// PREVIAMENTE
			Connection conexion = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/jardineria?useUnicode=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
					usuario, clave);

			// CREAMOS UNA CONSULTA Y LA EJECUTAMOS
			Statement statement = conexion.createStatement();
			ResultSet resultSet = statement.executeQuery("select * from cliente");

			// RECOGEMOS CUANTOS CLIENTES HAY
			while (resultSet.next()) {
				clientesTotales++;
			}

			// EJECUTAMOS LA CONSULTA
			resultSet = statement.executeQuery("select * from cliente where codigo_cliente = " + codigoCliente);

			System.out.println("Mostrando cliente...");
			// MOSTRAMOS EL CLIENTE
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
			if (codigoCliente > clientesTotales) {
				System.out.println(
						"La tabla cliente de la base de datos jardineria contiene " + clientesTotales + " registros.");
			}
			conexion.close();
		} catch (SQLException sqle) {
			System.out.println("No se ha podido acceder a la BBDD.");
		}
		System.out.println("--------------------------------------------------------");
	}

	public static void mostrarTodosLosClientes(String usuario, String clave) {
		System.out.println("Mostrando clientes...");
		int clientesTotales = 0;

		try {
			// INICIAMOS LA CONEXION A LA BBDD CON EL USUARIO Y LA CLAVE QUE HEMOS PEDIDO
			// PREVIAMENTE
			Connection conexion = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/jardineria?useUnicode=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
					usuario, clave);

			// CREAMOS UNA CONSULTA Y LA EJECUTAMOS
			Statement statement = conexion.createStatement();
			ResultSet resultSet = statement.executeQuery("select * from cliente");

			// MOSTRAMOS TODOS LOS CLIENTES
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
			System.out.println("No se ha podido acceder a la BBDD.");
		}
		System.out.println("La tabla cliente de la base de datos jardineria contiene: " + clientesTotales
				+ " registros.\n--------------------------------------------------------");
	}

	public static void buscarClientes(String usuario, String clave) {
		// PIDE EL CLIENTE QUE QUIERE BUSCAR
		System.out.println("¿Que cliente desea buscar? (NECESITO TEXTO)");
		String cadenaDeTexto = Leer.pedirCadena();

		try {
			// INICIAMOS LA CONEXION A LA BBDD CON EL USUARIO Y LA CLAVE QUE HEMOS PEDIDO
			// PREVIAMENTE
			Connection conexion = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/jardineria?useUnicode=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
					usuario, clave);

			// CREAMOS UNA CONSULTA Y LA EJECUTAMOS
			Statement statement = conexion.createStatement();
			ResultSet resultSet = statement.executeQuery(
					"select * from cliente where nombre_cliente = '" + cadenaDeTexto + "' or nombre_contacto = '"
							+ cadenaDeTexto + "' or apellido_contacto = '" + cadenaDeTexto + "'");

			// MOSTRAMOS EL CLIENTE QUE BUSCA EL USUARIO
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
			System.out.println("No se ha podido acceder a la BBDD.");
		}
		System.out.println("--------------------------------------------------------");
	}

	public static void editarProducto(String usuario, String clave) {
		// PEDIMOS EL PRODUCTO QUE QUIERE EDITAR
		System.out.println("¿Que producto quieres editar? (NECESITO TEXTO)");
		String codigoProducto = Leer.pedirCadena();

		try {
			// INICIAMOS LA CONEXION A LA BBDD CON EL USUARIO Y LA CLAVE QUE HEMOS PEDIDO
			// PREVIAMENTE
			Connection conexion = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/jardineria?useUnicode=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
					usuario, clave);

			// CREAMOS UNA CONSULTA Y LA EJECUTAMOS
			Statement statement = conexion.createStatement();
			ResultSet resultSet = statement
					.executeQuery("select * from producto where codigo_producto = " + codigoProducto);
			System.out.println("Mostrando producto...");

			// MOSTRAMOS EL PRODUCTO QUE VAMOS A EDITAR
			while (resultSet.next()) {
				System.out.print(resultSet.getString("codigo_producto") + "\t");
				System.out.print(resultSet.getString("nombre") + "\t");
				System.out.print(resultSet.getString("gama") + "\t");
				System.out.print(resultSet.getString("dimensiones") + "\t");
				System.out.print(resultSet.getString("proveedor") + "\t");
				System.out.print(resultSet.getString("descripcion") + "\t");
				System.out.print(resultSet.getInt("cantidad_en_stock") + "\t");
				System.out.print(resultSet.getDouble("precio_venta") + "\t");
				System.out.print(resultSet.getDouble("precio_proveedor") + "\t");
				System.out.println();
			}

			// PEDIMOS LOS DATOS PARA ACTUALIZAR EL PRODUCTO
			System.out.println("Nuevo nombre del producto: ");
			String nuevoNombre = Leer.pedirCadena();

			System.out.println("Nueva gama del producto: ");
			String nuevaGama = Leer.pedirCadena();

			System.out.println("Nuevas dimensiones del producto: ");
			String nuevasDimensiones = Leer.pedirCadena();

			System.out.println("Nuevo proveedor del producto: ");
			String nuevoProveedor = Leer.pedirCadena();

			System.out.println("Nueva descripcion del producto: ");
			String nuevaDescripcion = Leer.pedirCadena();

			System.out.println("Nueva cantidad en stock del producto: ");
			int nuevaCantidad = Leer.pedirEnteroValidar();

			System.out.println("Nuevo precio de venta del producto: ");
			double nuevoPrecioVenta = Leer.pedirDecimal();

			System.out.println("Nuevo precio de proveedor del producto: ");
			double nuevoPrecioProveedor = Leer.pedirDecimal();

			// PREPARAMOS LA CONSULTA QUE QUEREMOS EJECUTAR EN LA BBDD
			PreparedStatement preparedStatement = conexion
					.prepareStatement("insert into producto values (?,?,?,?,?,?,?,?,?)");

			// INSERTAMOS LOS DATOS DEL NUEVO PRODUCTO EN LOS VALORES DEL COMANDO SQL
			// ANTERIOR
			preparedStatement.setString(1, codigoProducto);
			preparedStatement.setString(2, nuevoNombre);
			preparedStatement.setString(3, nuevaGama);
			preparedStatement.setString(4, nuevasDimensiones);
			preparedStatement.setString(5, nuevoProveedor);
			preparedStatement.setString(6, nuevaDescripcion);
			preparedStatement.setInt(7, nuevaCantidad);
			preparedStatement.setDouble(8, nuevoPrecioVenta);
			preparedStatement.setDouble(9, nuevoPrecioProveedor);

			// EJECUTAMOS EL COMANDO SQL Y VERIFICAMOS SI SE HA INSERTADO CORRECTAMENTE
			int correcto = preparedStatement.executeUpdate();
			if (correcto > 0) {
				System.out.println("Valor editado correctamente.");
			}
			conexion.close();
		} catch (SQLException sqle) {
			System.out.println("No se ha podido acceder a la BBDD.");
		}
		System.out.println("--------------------------------------------------------");
	}
}