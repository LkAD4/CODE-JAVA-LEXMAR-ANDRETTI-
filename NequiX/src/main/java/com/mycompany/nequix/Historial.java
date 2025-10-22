package com.mycompany.nequix;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// hola
public class Historial {
	// Historial necesita de User para existir (composición)
	private final User user;
	private final List<String> entradas;

	
	Historial(User user) {
		if (user == null) {
			throw new IllegalArgumentException("User no puede ser null para crear Historial.");
		}
		this.user = user;
		this.entradas = new ArrayList<>();
	}

	public User getUser() {
		return user;
	}

	public void addEntrada(String entrada) {
		if (entrada == null || entrada.trim().isEmpty()) {
			throw new IllegalArgumentException("Entrada vacía no permitida.");
		}
		entradas.add(entrada);
	}

	public List<String> getEntradas() {
		return Collections.unmodifiableList(entradas);
	}

	@Override
	public String toString() {
		return "Historial de " + user.getNombre() + " - Entradas: " + entradas.size();
	}
}
