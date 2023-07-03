package ar.edu.unlam.dominioAlarma;

public class SensorDesactivadoException extends Exception {

	public SensorDesactivadoException() {
		super("HAY SENSORES DESACTIVADOS");
	}
}
