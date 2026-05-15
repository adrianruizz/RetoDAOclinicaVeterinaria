package Modulo;

public class Cliente extends Persona{

	private int id_cliente;
	private int id_persona;
	private String telefono;
	
	
	public Cliente() {
		super();
	}


	public Cliente(int id_persona, String telefono) {
		super();
		this.id_persona = id_persona;
		this.telefono = telefono;
	}


	public Cliente(int id_cliente, int id_persona, String telefono) {
		super();
		this.id_cliente = id_cliente;
		this.id_persona = id_persona;
		this.telefono = telefono;
	}


	public int getId_cliente() {
		return id_cliente;
	}


	public void setId_cliente(int id_cliente) {
		this.id_cliente = id_cliente;
	}


	public int getId_persona() {
		return id_persona;
	}


	public void setId_persona(int id_persona) {
		this.id_persona = id_persona;
	}


	public String getTelefono() {
		return telefono;
	}


	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}


	@Override
	public String toString() {
		return "Cliente [id_cliente=" + id_cliente + ", id_persona=" + id_persona + ", telefono=" + telefono + "]";
	}
	
	
	
	
	
}
