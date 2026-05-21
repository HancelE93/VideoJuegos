package com.krakdev.jdbc.videojuegos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.krakdev.jdbc.Conexion;
import com.krakdev.videojuegos.entidades.Videojuego;



public class VideojuegoJdbc {

	
	private static final Logger log = LogManager.getLogger(VideojuegoJdbc.class);
	public static Videojuego insertar(String codigo, String nombre, String plataforma, double precio, boolean disponible, String genero) {

		Connection con = null;
		PreparedStatement ps = null;
		Videojuego videoJuego = null;

		try {
			con = Conexion.getConnection();

			String sql = "insert into Videojuego (codigo, nombre, plataforma, precio, disponible, genero) values(?,?,?,?,?.?)";

			ps = con.prepareStatement(sql);
			
			ps.setString(1, codigo);
			ps.setString(2, nombre);
			ps.setString(3, plataforma);
			ps.setDouble(4, precio);
			ps.setBoolean(5, disponible);
			ps.setString(6, genero);

			videoJuego = new Videojuego(codigo, nombre, plataforma, precio, disponible,genero);
			int filas = ps.executeUpdate();
			log.info("Filas insertadas: " + filas);

		} catch (Exception e) {
			log.error("Error al insertar" + e.getMessage());
			throw new RuntimeException("Error al insertar" + e.getMessage());

		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return videoJuego;
	}
}