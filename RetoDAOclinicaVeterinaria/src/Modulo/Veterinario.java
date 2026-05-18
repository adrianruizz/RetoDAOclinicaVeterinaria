package Modulo;

import java.io.Serializable;

public class Veterinario extends Persona implements Serializable {
	

    private static final long serialVersionUID = 1L;

	private int id_veterinario;
	private int id_persona;
	private String num_colegiado;
	
	
	public Veterinario() {
		super();
	}


	public Veterinario( String dni, String nombre, String num_colegiado) {
		super( dni, nombre);
		this.id_veterinario = id_veterinario;
		this.num_colegiado = num_colegiado;
	}


	public Veterinario(int id_persona, String num_colegiado) {
		super();
		this.id_persona = id_persona;
		this.num_colegiado = num_colegiado;
	}


	public Veterinario(int id_veterinario, int id_persona, String num_colegiado) {
		super();
		this.id_veterinario = id_veterinario;
		this.id_persona = id_persona;
		this.num_colegiado = num_colegiado;
	}


	public int getId_veterinario() {
		return id_veterinario;
	}


	public void setId_veterinario(int id_veterinario) {
		this.id_veterinario = id_veterinario;
	}


	public int getId_persona() {
		return id_persona;
	}


	public void setId_persona(int id_persona) {
		this.id_persona = id_persona;
	}


	public String getNum_colegiado() {
		return num_colegiado;
	}


	public void setNum_colegiado(String num_colegiado) {
		this.num_colegiado = num_colegiado;
	}


	@Override
	public String toString() {
		return "Veterinario [id_veterinario=" + id_veterinario + ", id_persona=" + id_persona + ", num_colegiado="
				+ num_colegiado + "]";
	}
	
	
	
}
