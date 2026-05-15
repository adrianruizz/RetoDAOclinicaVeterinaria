package DAO;
import java.util.List;

import Modulo.Cliente;
public interface GenericDAO<T> {
	boolean insertar(T objeto);
	List<T> obtenerTodos();
	T obtenerPorId(int id);
	boolean actualizar(T objeto);
	boolean eliminar(int id);
}
