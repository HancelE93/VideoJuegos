package com.krakdev.jdbc.videojuegos.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.krakdev.jdbc.videojuegos.VideojuegoJdbc;
import com.krakdev.videojuegos.entidades.Videojuego;

@Service
public class ServicioVideojuegoJdbc {

	public Videojuego crear(Videojuego videojuego) {

		return VideojuegoJdbc.insertar(videojuego.getCodigo(), videojuego.getNombre(), 
				videojuego.getPlataforma(),videojuego.getPrecio(), videojuego.isDisponible(),videojuego.getGenero());

	}

	public List<Videojuego> listar() {
		return VideojuegoJdbc.lista();

	}

	public Videojuego  buscarPorCodigo(String codigo) {
		return VideojuegoJdbc.buscar(codigo);
	}

	public Videojuego  actualizar(String codigo, Videojuego videoJuegoActualizar) {

		return VideojuegoJdbc.actualizar(codigo, videoJuegoActualizar.getNombre(), videoJuegoActualizar.getPlataforma(),
				videoJuegoActualizar.getPrecio(), videoJuegoActualizar.isDisponible(),videoJuegoActualizar.getGenero());
	}

	public boolean eliminar(String codigo) {
		return VideojuegoJdbc.eliminar(codigo);
	}
}
