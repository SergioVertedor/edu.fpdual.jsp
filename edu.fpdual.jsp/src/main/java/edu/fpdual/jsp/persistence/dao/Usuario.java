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
	private int id;
	private String nombre;
	private String correo;
	private String password;

	public Usuario (String nombre, String correo, String password) {
		this.id = 0;
		this.nombre = nombre;
		this.correo =  correo;
		this.password = password;
	}
	public Usuario(ResultSet result) {
		try {
			this.id = result.getInt("id");
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
