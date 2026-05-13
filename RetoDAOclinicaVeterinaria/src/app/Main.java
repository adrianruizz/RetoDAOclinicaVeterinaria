package app;

import DAO.ClienteDAO;
import Modulo.Cliente;

public class Main {

	public static void main(String[] args) {
	ClienteDAO cl = new ClienteDAO();
	
		for (Cliente cl2 : cl.obtenerTodos()) {
			System.out.println(cl2);
		}
		
		System.out.println(cl.obtenerId(0));
		
		
	}
	
	
	
}
