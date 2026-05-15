package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import Modulo.Cliente;
import Modulo.Mascota;
import Modulo.Tratamiento;
import Util.ConexionBD;

public class MascotaDAO implements GenericDAO<Mascota>{

	@Override
	public boolean insertar(Mascota mascota) {
		
			String sql = "insert into mascotas (id_cliente, nombre, especie, fecha_nacimiento, peso) values (?,?,?,?,?) ";
			try (Connection conn = ConexionBD.getConnection();
					PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

				pstmt.setInt(1, mascota.getId_cliente());
				pstmt.setString(2, mascota.getNombre());
				pstmt.setString(3, mascota.getEspecie());
				pstmt.setObject(4, mascota.getFecha_nacimiento());
				pstmt.setDouble(5, mascota.getPeso());

				int filas = pstmt.executeUpdate();

				if (filas > 0) {
					try (ResultSet rs = pstmt.getGeneratedKeys()) {
						if (rs.next()) {
							mascota.setId_mascota(1); // 
							return true;
						}
					}
				}
				return false;

			} catch (SQLException e) {
				System.err.println("Error SQL al insertar '" + mascota.getId_mascota() + "': " + e.getMessage());
				return false;
			}
		}
	

	@Override
	public List<Mascota> obtenerTodos() {
		ArrayList<Mascota> lista = new ArrayList<>();
		String sql = "select id_mascota, id_cliente, nombre, especie, fecha_nacimiento, peso from mascotas ";
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
	

	@Override
	public Mascota obtenerPorId(int id) {
		return null;

	}

	@Override
	public boolean actualizar(Mascota objeto) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean eliminar(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	
	public List<Mascota> obtenerMascotaPorIdCliente(int id) {
		ArrayList<Mascota> lista = new ArrayList<>();
		String sql = "select id_mascota, id_cliente, nombre, especie, fecha_nacimiento, peso from mascotas where id_cliente=? ";
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

	private Mascota mapear(ResultSet rs) throws SQLException {
		Mascota a = new Mascota();
		a.setId_mascota(rs.getInt("id_mascota"));
		a.setId_cliente(rs.getInt("id_cliente"));
		a.setNombre(rs.getString("nombre"));
		a.setEspecie(rs.getString("especie"));
		a.setPeso(rs.getDouble("peso"));
		a.setFecha_nacimiento(rs.getObject("fecha_nacimiento", LocalDate.class));
		return a;
	}

	
	
}
