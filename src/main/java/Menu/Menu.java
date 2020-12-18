package Menu;

import FuncionesDeMenu.MetodosDelMenu;
import utilidades.Leer;

public class Menu {
	public static void menu() {
		int opcion = 0;
		do {

			System.out.println("1.- Anadir un cliente");
			System.out.println("2.- Mostrar un cliente");
			System.out.println("3.- Mostrar todos los clientes");
			System.out.println("4.- Buscar un cliente");
			System.out.println("5.- Editar un producto");
			System.out.println("0.- Salir del programa");
			opcion = Leer.pedirEnteroValidar();

			switch (opcion) {
			case 1:
				MetodosDelMenu.anadirClientes();
				break;
			case 2:
				MetodosDelMenu.mostrarCliente();
				break;
			case 3:
				MetodosDelMenu.mostrarTodosLosClientes();
				break;
			case 4:
				MetodosDelMenu.buscarClientes();
				break;
			case 5:
				MetodosDelMenu.editarProducto();
				break;
			}
		} while (opcion != 0);
	}
}