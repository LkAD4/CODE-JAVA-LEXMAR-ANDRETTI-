package com.mycompany.nequix;

public class Id_User {
	private final String id;

	/**
	 * Genera una credencial basada en las dos primeras letras del nombre (en mayúsculas)
	 * más la edad. Ej: nombre="Luis", edad=30 -> ID = "LU30".
	 */
	public Id_User(String nombre, int edad) {
		if (nombre == null || nombre.trim().length() < 2) {
			throw new IllegalArgumentException("El nombre debe tener al menos 2 caracteres para generar el ID.");
		}
		String clean = nombre.trim();
		String prefix = clean.substring(0, 2).toUpperCase();
		this.id = prefix + edad;
	}

	public String getId() {
		return id;
	}

	@Override
	public String toString() {
		return id;
	}
}
