package ar.edu.unlam.dominioAlarma;

public class UsuarioActivador extends Usuario implements Activable {

	public UsuarioActivador(Integer id, String nombre) {
		super(id, nombre);
	}

	public boolean activarDesactivarAlarma(Alarma alarma, String codigoActivacion) throws SensorDesactivadoException, CodigoDeActivacionIncorrecto {
		if (alarma.verificarSensores()) {
			if (alarma.getCodigoDeActivacion().equals(codigoActivacion)) {
				return true;
			}
			throw new CodigoDeActivacionIncorrecto();
		}
		return false;
	}

}
