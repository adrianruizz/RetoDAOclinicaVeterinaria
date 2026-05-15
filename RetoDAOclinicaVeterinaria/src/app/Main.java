package app;

import DAO.ClienteDAO;
import Modulo.Cliente;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		 Scanner scanner = new Scanner(System.in);

		
	
		//Muestra todos los clientes y selecciona uno por id y muestra sus datos, sus mascotas y sus facturas asociadas.
	
ClienteDAO c23 = new ClienteDAO();
		
		for (Cliente c233 : c23.obtenerTodos()) {
			
			System.out.println(c233);
	
		}
		System.out.print("Introduce tu id: ");
	       int id =Integer.parseInt(scanner.nextLine()) ;
	       
	       
	       System.out.println(c23.obtenerPorId(id));
		
		
	
		
		
		
		
	}
	
	
	
}
