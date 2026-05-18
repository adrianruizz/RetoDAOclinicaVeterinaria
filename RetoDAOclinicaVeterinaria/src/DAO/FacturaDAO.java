package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import Modulo.Factura;
import Modulo.LineaFactura;
import Util.ConexionBD;

public class FacturaDAO implements GenericDAO<Factura>{

	@Override
	public boolean insertar(Factura objeto) {
		  String sql = "INSERT INTO facturas (id_cliente, id_veterinario, id_mascota, fecha, subtotal, total_iva, total) VALUES (?, ?, ?, ?, ?, ?, ?)";
		    try (Connection con = ConexionBD.getConnection();
		         PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
		    	ps.setInt(1, objeto.getId_cliente());
		        ps.setInt(2, objeto.getId_veterinario());
		        ps.setInt(3, objeto.getId_mascota());
		        ps.setObject(4, objeto.getFecha());
		        ps.setDouble(5, objeto.getSubtotal());
		        ps.setDouble(6, objeto.getTotal_iva());
		        ps.setDouble(7, objeto.getTotal());
		          
		          int filas = ps.executeUpdate();
		          if (filas > 0) {
		                ResultSet rs = ps.getGeneratedKeys();
		                if (rs.next()) {
		                    objeto.setId_factura(rs.getInt(1));
		                }
		                return true;
		            }
		      } catch (SQLException e) {
		            System.out.println("Error al insertar: " + e.getMessage());
		      }
		        return false;
		    }

	

	@Override
	public List<Factura> obtenerTodos() {
		List<Factura> lista = new ArrayList<>();
        String sql = "SELECT * FROM facturas";


        try (Connection con = ConexionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {


            while (rs.next()) {
                lista.add(mapearFila(rs));
            }


        } catch (SQLException e) {
            System.out.println("Error al obtener todos: " + e.getMessage());
        }


        return lista;
	}

	@Override
	public Factura obtenerPorId(int id_factura) {
		 String sql = "SELECT * FROM facturas WHERE id_factura = ?";


	        try (Connection con = ConexionBD.getConnection();
	             PreparedStatement ps = con.prepareStatement(sql)) {


	            ps.setInt(1, id_factura);
	            ResultSet rs = ps.executeQuery();


	            if (rs.next()) {
	                return mapearFila(rs);
	            }


	        } catch (SQLException e) {
	            System.out.println("Error al obtener por id: " + e.getMessage());
	        }

		return null;
	}
	public List<Factura> obtenerTodosPorIdCliente(int id) {
		List<Factura> lista = new ArrayList<>();
        String sql = "SELECT * FROM facturas where id_cliente = ?";


        try (Connection con = ConexionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
             
        		ps.setInt(1, id);
            	 ResultSet rs = ps.executeQuery();
        	
        	

            while (rs.next()) {
                lista.add(mapearFila(rs));
            }


        } catch (SQLException e) {
            System.out.println("Error al obtener todos: " + e.getMessage());
        }


        return lista;
	}
	private Factura mapearFila(ResultSet rs) throws SQLException {
		Factura a = new Factura();
		a.setId_factura(rs.getInt("id_factura"));;
		a.setId_cliente(rs.getInt("id_cliente"));
		a.setId_veterinario(rs.getInt("id_veterinario"));
		a.setId_mascota(rs.getInt("id_mascota"));
		a.setFecha(rs.getObject("fecha",LocalDate.class));
		a.setSubtotal(rs.getDouble("subtotal"));
		a.setTotal_iva(rs.getDouble("total_iva"));
		a.setTotal(rs.getDouble("total"));
		return a;
	}

	@Override
	public boolean actualizar(Factura objeto) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean eliminar(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	
	
}
