package DAO;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Modulo.Persona;
import Modulo.Veterinario;
import Util.ConexionBD;

public class VeterinarioDAO extends Persona implements GenericDAO<Veterinario> {

    @Override
    public boolean insertar(Veterinario veterinario) {

        String sql = "INSERT INTO personas (dni, nombre) VALUES (?, ?)";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, veterinario.getDni());
            pstmt.setString(2, veterinario.getNombre());
            int filas = pstmt.executeUpdate();
            if (filas == 0) {
                return false;
            }

            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    veterinario.setId_persona(rs.getInt(1));
                }
            }

            String sql2 = "INSERT INTO veterinarios (id_persona, num_colegiado) VALUES (?, ?)";
            try (PreparedStatement pstmt2 = conn.prepareStatement(sql2, Statement.RETURN_GENERATED_KEYS)) {
                pstmt2.setInt(1, veterinario.getId_persona());
                pstmt2.setString(2, veterinario.getNum_colegiado());
                int filas2 = pstmt2.executeUpdate();
                if (filas2 > 0) {
                    try (ResultSet rs2 = pstmt2.getGeneratedKeys()) {
                        if (rs2.next()) {
                            veterinario.setId_veterinario(rs2.getInt(1));
                        }
                    }
                    return true;
                }
            }
        } catch (SQLException e) {
            System.err.println("Error SQL al insertar veterinario '"
                    + veterinario.getId_veterinario() + "': " + e.getMessage());
        }
        return false;
    }

    @Override
    public List<Veterinario> obtenerTodos() {
        List<Veterinario> veterinarios = new ArrayList<>();
        String sql = "SELECT id_veterinario, id_persona, num_colegiado FROM veterinarios";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                veterinarios.add(mapearFila(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error SQL al obtener todos los veterinarios: " + e.getMessage());
        }
        return veterinarios;
    }

    @Override
    public Veterinario obtenerPorId(int id) {
        String sql = "SELECT id_veterinario, id_persona, num_colegiado FROM veterinarios WHERE id_veterinario = ?";
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
    public boolean actualizar(Veterinario objeto) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean eliminar(int id) {
        // TODO Auto-generated method stub
        return false;
    }

    private Veterinario mapearFila(ResultSet rs) throws SQLException {
        Veterinario a = new Veterinario();
        a.setId_veterinario(rs.getInt("id_veterinario"));
        a.setId_persona(rs.getInt("id_persona"));
        a.setNum_colegiado(rs.getString("num_colegiado"));
        return a;
    }



    public boolean guardarFichero(List<Veterinario> lista) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("veterinarios.dat"))) {
            out.writeObject(lista);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
		return false;
    }

    public ArrayList<Veterinario> leerFichero() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("veterinarios.dat"))) {
            ArrayList<Veterinario> lista = (ArrayList<Veterinario>) in.readObject();
            return lista;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}

