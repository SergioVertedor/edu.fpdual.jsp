package edu.fpdual.jsp.persistence.dao;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.ResultSet;
import java.sql.SQLException;

@Getter
@Setter
@ToString
public class Usuario implements Comparable<Usuario> {
	private String id;
	private String nombre;
	private String correo;
	private String password;

	public Usuario (String id, String nombre, String correo, String password) {
		this.id = id;
		this.nombre = nombre;
		this.correo =  correo;
		this.password = password;
	}
	public Usuario ( String nombre, String correo, String password) {
		this.nombre = nombre;
		this.correo =  correo;
		this.password = password;
	}
	public Usuario(ResultSet result) {
		try {
			this.id = result.getString("id");
			this.nombre = result.getString("nombre");
			this.correo = result.getString("correo");
			this.password = result.getString("password");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@Override
	public int compareTo(Usuario o) {
		return this.nombre.compareTo(o.getNombre());
	}
}
