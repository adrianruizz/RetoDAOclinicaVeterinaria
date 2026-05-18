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

public class LineaFacturaDAO implements GenericDAO<LineaFactura>{

	@Override
	public boolean insertar(LineaFactura objeto) {
		  String sql = "INSERT INTO lineas_factura (id_linea_factura, id_factura, id_tratamiento, fecha, cantidad, precio_tratamiento, importe) VALUES (?, ?, ?, ?, ?, ?, ?)";
		    try (Connection con = ConexionBD.getConnection();
		         PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
		    	ps.setInt(1, objeto.getId_linea_factura());
		        ps.setInt(2, objeto.getId_factura());
		        ps.setInt(3, objeto.getId_tratamiento());
		        ps.setObject(4, objeto.getFecha());
		        ps.setDouble(5, objeto.getCantidad());
		        ps.setDouble(6, objeto.getPrecio_tratamiento());
		        ps.setDouble(7, objeto.getImporte());
		          
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
	public List<LineaFactura> obtenerTodosPorIdFactura(int id_factura) {
		List<LineaFactura> lista = new ArrayList<>();
        String sql = "SELECT * FROM lineas_factura where id_factura = ?";


        try (Connection con = ConexionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
             
        		ps.setInt(1, id_factura);
            	 ResultSet rs = ps.executeQuery();
        	
        	

            while (rs.next()) {
                lista.add(mapearFila(rs));
            }


        } catch (SQLException e) {
            System.out.println("Error al obtener todos: " + e.getMessage());
        }


        return lista;
	}
	@Override
	public List<LineaFactura> obtenerTodos() {
		List<LineaFactura> lista = new ArrayList<>();
        String sql = "SELECT * FROM lineas_factura";


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
	public LineaFactura obtenerPorId(int id) {
		 String sql = "SELECT * FROM lineas_factura WHERE Id_linea_factura = ?";


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
	public boolean actualizar(LineaFactura objeto) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean eliminar(int id) {
		// TODO Auto-generated method stub
		return false;
	}
	private LineaFactura mapearFila(ResultSet rs) throws SQLException {
		LineaFactura a = new LineaFactura();
		a.setId_linea_factura(rs.getInt("Id_linea_factura"));
		a.setId_factura(rs.getInt("id_factura"));
		a.setId_tratamiento(rs.getInt("id_tratamiento"));
		a.setFecha(rs.getObject("fecha",LocalDate.class));
		a.setCantidad(rs.getInt("cantidad"));
		a.setPrecio_tratamiento(rs.getDouble("precio_tratamiento"));
		a.setImporte(rs.getDouble("importe"));
		return a;
	}

}
