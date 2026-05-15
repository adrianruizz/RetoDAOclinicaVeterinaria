package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Modulo.Cliente;
import Util.ConexionBD;

public class ClienteDAO implements GenericDAO<Cliente> {

	

	


	

	@Override
	public boolean insertar(Cliente cliente) {
		String sql = "INSERT INTO personas (id_cliente, telefono) VALUES (?, ? )";

		try (Connection conn = ConexionBD.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

			pstmt.setInt(1, cliente.getId_cliente());

			pstmt.setString(2, cliente.getTelefono());

			int filas = pstmt.executeUpdate();

			if (filas > 0) {
				try (ResultSet rs = pstmt.getGeneratedKeys()) {
					if (rs.next()) {
						cliente.setId_cliente(rs.getInt(1)); // asigna el ID
						return true;
					}
				}
			}

			String sql3 = "INSERT INTO clientes ( id_persona, telefono) VALUES ( ?, ? )";

			try (PreparedStatement pstmt1 = conn.prepareStatement(sql3, Statement.RETURN_GENERATED_KEYS)) {

				pstmt1.setInt(1, cliente.getId_persona());
				pstmt1.setString(2, cliente.getTelefono());

				int filas1 = pstmt.executeUpdate();

				if (filas1 > 0) {
					try (ResultSet rs = pstmt.getGeneratedKeys()) {
						if (rs.next()) {
							cliente.setId_cliente(rs.getInt(1)); // asigna el ID
							return true;
						}
					}
				}
				return false;

			}
		} catch (SQLException e) {
			System.err.println("Error SQL al insertar '" + cliente.getId_cliente() + "': " + e.getMessage());
			return false;
		}

	}

	@Override
	public List<Cliente> obtenerTodos() {

	
		
		List<Cliente> alumnos = new ArrayList<>();
		String sql = "SELECT id_cliente,id_persona,telefono FROM clientes";

		try (Connection conn = ConexionBD.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {

			while (rs.next()) {
				alumnos.add(mapearFila(rs));
			}

		} catch (SQLException e) {
			System.err.println("Error SQL al obtener todos los alumnos: " + e.getMessage());
		}
		return alumnos;
	}



	@Override
	public boolean actualizar(Cliente objeto) {

		return false;
	}

	@Override
	public boolean eliminar(int id) {

		return false;
	}

	private Cliente mapearFila(ResultSet rs) throws SQLException {
		Cliente a = new Cliente();
		a.setId_cliente(rs.getInt("id_cliente"));
		a.setId_persona(rs.getInt("id_persona"));
		a.setTelefono(rs.getString("telefono"));

		return a;
	}



	@Override
	public Cliente obtenerPorId(int id) {
	
			String sql= "select id_cliente ,id_persona,telefono from clientes where id_cliente = ? ";
			try (Connection con = ConexionBD.getConnection();
		            PreparedStatement ps = con.prepareStatement(sql)) {


		           ps.setInt(1, id);
		           ResultSet rs = ps.executeQuery();


		           if (rs.next()) {
		               return mapearFila(rs);
		           }


		       } catch (SQLException e) {
		           System.out.println("Error al obtener por id: " + e.getMessage());
		       }


		       return null;
		   }

	@Override
	public Cliente obtenerId(int id) {
		String sql= "select id_cliente from clientes ";
		try (Connection con = ConexionBD.getConnection();
	            PreparedStatement ps = con.prepareStatement(sql)) {


	           ps.setInt(1, id);
	           ResultSet rs = ps.executeQuery();


	           if (rs.next()) {
	               return mapearFila(rs);
	           }


	       } catch (SQLException e) {
	           System.out.println("Error al obtener por id: " + e.getMessage());
	       }


	       return null;
	   }

	// Muestra todos los clientes, selecciona un ID y muestras sus mascotas.

	// Muestra todos los clientes y selecciona uno por id y muestra sus datos, sus
	// mascotas y sus facturas asociadas.
	// Añade un veterinario: 12345678Z Pepe Carrera, COL-1005
	// Muestra los veterinarios y seleccionando un id muestra las facturas que ha
	// emitido.

}
