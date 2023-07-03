package ar.edu.unlam.dominioAlarma;

public class UsuarioConfigurador extends Usuario implements Configurable{

	public UsuarioConfigurador(Integer id, String nombre, String CodigoDeconfiguracion) {
		super(id, nombre, CodigoDeconfiguracion);
	}

	public boolean agregarSensorAAlarma(Sensor sensor, Alarma alarma) throws SensorDuplicadoException {
		if (alarma.agregarSensor(sensor)) {
			return true;
		}
		return false;
	}

	@Override
	public void registrarUnaAlarmaEnUnaCentral( Alarma alarma, Central central) throws AlarmaYaRegistradaExeption {
		central.registrarAlarma(alarma);
	}

}
