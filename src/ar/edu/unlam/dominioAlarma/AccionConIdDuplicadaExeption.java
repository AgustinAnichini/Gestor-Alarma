package ar.edu.unlam.dominioAlarma;

public class AccionConIdDuplicadaExeption extends Exception {

	public AccionConIdDuplicadaExeption() {
		super("Accion con ID duplicado");
	}
}
