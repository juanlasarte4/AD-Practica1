package Principal.AD_Practica1;

import FuncionesDeMenu.MetodosDelMenu;
import utilidades.Leer;

public class App {
	public static void main(String[] args) {
		// PEDIMOS USUARIO PARA HACER LA CONEXION MAS TARDE
		System.out.println("Nombre de usuario");
		String usuario = Leer.pedirCadena();

		// PEDIMOS CLAVE PARA HACER LA CONEXION MAS TARDE
		System.out.println("Contrasena: ");
		String clave = Leer.pedirCadena();

		// VARIABLE QUE UTILIZAREMOS PARA ELEGIR LA FUNCION DEL MENU
		int opcion = 0;
		do {
			// IMPRIMIMOS MENU
			System.out.println("1.- Anadir un cliente");
			System.out.println("2.- Mostrar un cliente");
			System.out.println("3.- Mostrar todos los clientes");
			System.out.println("4.- Buscar un cliente");
			System.out.println("5.- Editar un producto");
			System.out.println("0.- Salir del programa");
			// PEDIMOS QUE ES LO QUE QUIERE EL USUARIO
			opcion = Leer.pedirEnteroValidar();

			// SEGUN LO QUE HA ELEGIDO EJECUTAREMOS LA FUNCION CORRESPONDIENTE
			switch (opcion) {
			case 1:
				MetodosDelMenu.anadirClientes(usuario, clave);
				break;
			case 2:
				MetodosDelMenu.mostrarCliente(usuario, clave);
				break;
			case 3:
				MetodosDelMenu.mostrarTodosLosClientes(usuario, clave);
				break;
			case 4:
				MetodosDelMenu.buscarClientes(usuario, clave);
				break;
			case 5:
				MetodosDelMenu.editarProducto(usuario, clave);
				break;
			}
		} while (opcion != 0);
	}
}