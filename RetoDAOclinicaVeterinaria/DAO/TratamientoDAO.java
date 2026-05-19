package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Modulo.Cliente;
import Modulo.Mascota;
import Modulo.Tratamiento;
import Util.ConexionBD;

public class TratamientoDAO implements GenericDAO<Tratamiento> {

	@Override
	public boolean insertar(Tratamiento objeto) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Tratamiento> obtenerTodos() {
		ArrayList<Tratamiento> lista = new ArrayList<>();
		String sql = "select id_tratamiento, nombre, precio from tratamientos ";
		try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				lista.add(mapear(rs));
			}
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}

		return lista;

	}

	public void name() {
		
	}
	
	
	@Override
	public Tratamiento obtenerPorId(int id) {
		return null;
	}

	@Override
	public boolean actualizar(Tratamiento objeto) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean eliminar(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	public ArrayList<Tratamiento> obtenerTratamientos(int id_veterinario) {
		ArrayList<Tratamiento> lista = new ArrayList<>();
		String sql = " select t.id_tratamiento as id_tratamiento, nombre, precio from tratamientos t inner join historial h on t.id_tratamiento=h.id_tratamiento inner join veterinarios v on v.id_veterinario=h.id_veterinario where v.id_veterinario=?";

		try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, id_veterinario);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				lista.add(mapear(rs));
			}
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}

		return lista;

	}

	public ArrayList<Tratamiento> obtenerTratamientosPorIdMascota(int id_mascota) {
		ArrayList<Tratamiento> lista = new ArrayList<>();
		String sql = "select t.id_tratamiento, t.nombre, t.precio from tratamientos t inner join historial h on h.id_tratamiento=t.id_tratamiento inner join mascotas m on m.id_mascota=h.id_mascota where m.id_mascota=?";
		try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, id_mascota);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				lista.add(mapear(rs));
			}
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}

		return lista;

	}
	
	public double obtenerPrecio(int id) {
		
		String sql = "select precio from tratamientos where id_tratamiento=?" ;
		
		try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				
               return rs.getDouble("precio");
			
			}
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}

		return 0;

	}
	
	
	public ArrayList<Tratamiento> obtenerNumTratamientos(int id) {
		ArrayList<Tratamiento> lista = new ArrayList<>();
		String sql = "select t.id_tratamiento, t.nombre, t.precio from tratamientos t inner join historial h on t.id_tratamiento=h.id_tratamiento where h.id_tratamiento=?";
				
		try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				lista.add(mapear(rs));
			}
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}

		return lista;

	}
	
	private Tratamiento mapear(ResultSet rs) throws SQLException {
		Tratamiento a = new Tratamiento();
		a.setId_tratamiento(rs.getInt("id_tratamiento"));
		a.setNombre(rs.getString("nombre"));
		a.setPrecio(rs.getDouble("precio"));
		return a;
	}

	

}
