package Modulo;

public class Tratamiento {
	private int id_tratamiento;
	private String nombre;
	private double precio;
	public Tratamiento(int id_tratamiento, String nombre, double precio) {
		super();
		this.id_tratamiento = id_tratamiento;
		this.nombre = nombre;
		this.precio = precio;
	}
	public Tratamiento(String nombre, double precio) {
		super();
		this.nombre = nombre;
		this.precio = precio;
	}
	public Tratamiento() {
		super();
	}
	public int getId_tratamiento() {
		return id_tratamiento;
	}
	public void setId_tratamiento(int id_tratamiento) {
		this.id_tratamiento = id_tratamiento;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	@Override
	public String toString() {
		return "Tratamiento [id_tratamiento=" + id_tratamiento + ", nombre=" + nombre + ", precio=" + precio + "]";
	}
	
	
}
