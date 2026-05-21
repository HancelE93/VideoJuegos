package com.krakdev.jdbc.videojuegos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.krakdev.jdbc.Conexion;
import com.krakdev.videojuegos.entidades.Videojuego;

public class VideojuegoJdbc {

	private static final Logger log = LogManager.getLogger(VideojuegoJdbc.class);

	public static Videojuego insertar(String codigo, String nombre, String plataforma, double precio,
			boolean disponible, String genero) {

		Connection con = null;
		PreparedStatement ps = null;
		Videojuego videoJuego = null;

		try {
			con = Conexion.getConnection();

			String sql = "insert into videojuegos (codigo, nombre, plataforma, precio, disponible, genero) values(?,?,?,?,?,?)";

			ps = con.prepareStatement(sql);

			ps.setString(1, codigo);
			ps.setString(2, nombre);
			ps.setString(3, plataforma);
			ps.setDouble(4, precio);
			ps.setBoolean(5, disponible);
			ps.setString(6, genero);

			videoJuego = new Videojuego(codigo, nombre, plataforma, precio, disponible, genero);
			int filas = ps.executeUpdate();
			log.info("Video juego agregado: " + filas);

		} catch (Exception e) {
			log.error("Error al insertar el video juegos" + e.getMessage());
			throw new RuntimeException("Error al insertar video juegor" + e.getMessage());

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

	public static List<Videojuego> lista() {

		List<Videojuego> videoJuegos = new ArrayList<>();
		Connection con = null;

		try {
			con = Conexion.getConnection();

			String sql = "select * from videojuegos";

			PreparedStatement ps = con.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				Videojuego vj = new Videojuego(rs.getString("codigo"), rs.getString("nombre"),
						rs.getString("plataforma"), rs.getDouble("precio"), rs.getBoolean("disponible"),
						rs.getString("genero"));

				videoJuegos.add(vj);

				log.info("Lista  de video juegos agregada");

			}
		} catch (Exception e) {
			log.error("Error al buscar lista de video juegos" + e.getMessage());
			throw new RuntimeException("Error al ver la lista de video juegos" + e.getMessage());
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return videoJuegos;
	}

	public static Videojuego buscar(String codigo) {

		Connection con = null;
		PreparedStatement ps = null;
		String sql = "select * from videojuegos where codigo = ?";
		ResultSet rs = null;
		Videojuego videojuego = null;

		try {
			con = Conexion.getConnection();
			ps = con.prepareStatement(sql);

			ps.setString(1, codigo);

			rs = ps.executeQuery();

			if (rs.next()) {
				videojuego = new Videojuego(rs.getString("codigo"), rs.getString("nombre"), rs.getString("plataforma"),
						rs.getDouble("precio"), rs.getBoolean("disponible"), rs.getString("genero"));
			}

		} catch (Exception e) {
			log.error("error al buscar el video juego", e.getMessage());
			throw new RuntimeException("Error al buscar el video juego" + e.getMessage());
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return videojuego;
	}

	public static Videojuego actualizar(String codigo, String nuevoNombre, String nuevaPlataforma, double nuevoPrecio,
			boolean nuevoDisponible, String nuevoGenero) {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "update videojuegos set nombre = ?, plataforma = ?, precio =?, disponible=?, genero=? where codigo = ?";
		ResultSet rs = null;
		Videojuego videojuego = null;

		try {
			con = Conexion.getConnection();
			ps = con.prepareStatement(sql);

			ps.setString(1, nuevoNombre);
			ps.setString(2, nuevaPlataforma);
			ps.setDouble(3, nuevoPrecio);
			ps.setBoolean(4, nuevoDisponible);
			ps.setString(5, nuevoGenero);
			ps.setString(6, codigo);

			int fila = ps.executeUpdate();

			videojuego = new Videojuego(codigo, nuevoNombre, nuevaPlataforma, nuevoPrecio, nuevoDisponible,
					nuevoGenero);
			log.info("Video juego actualizado" + videojuego);

		} catch (Exception e) {
			log.error("error al actualizar el video juego", e.getMessage());
			throw new RuntimeException("Error al actualizar el video juego " + e.getMessage());
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return videojuego;
	}

	public static boolean eliminar(String codigo) {

		Connection con = null;
		PreparedStatement ps = null;
		String sql = "delete from videojuegos where codigo = ?";
		ResultSet rs = null;

		try {
			con = Conexion.getConnection();
			ps = con.prepareStatement(sql);

			ps.setString(1, codigo);

			int fila = ps.executeUpdate();
			
			log.info("Video juego eliminad correctamente" + fila);

			return true;

		} catch (Exception e) {
			log.error("error al eliminar el video juego", e.getMessage());
			throw new RuntimeException("Error al eliminar el vidoe juego" + e.getMessage());

		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
