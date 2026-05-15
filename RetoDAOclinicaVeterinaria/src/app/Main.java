package app;

import java.util.Scanner;

import DAO.ClienteDAO;
import Modulo.Cliente;

public class Main {

	public static void main(String[] args) {
	
		Scanner scanner = new Scanner(System.in);
ClienteDAO c23 = new ClienteDAO();
		
		for (Cliente c233 : c23.obtenerTodos()) {
			
			System.out.println(c233);
	
		}
		System.out.print("Introduce tu id: ");
	       int id =Integer.parseInt(scanner.nextLine()) ;
	      
	      
	       System.out.println(c23.obtenerPorId(id));

	}
}
