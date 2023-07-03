package ar.edu.unlam.testAlarma;

import static org.junit.Assert.*;

import org.junit.Test;

import ar.edu.unlam.dominioAlarma.Accion;
import ar.edu.unlam.dominioAlarma.AccionConIdDuplicadaExeption;
import ar.edu.unlam.dominioAlarma.Alarma;
import ar.edu.unlam.dominioAlarma.AlarmaIncorrectoException;
import ar.edu.unlam.dominioAlarma.AlarmaYaRegistradaExeption;
import ar.edu.unlam.dominioAlarma.Central;
import ar.edu.unlam.dominioAlarma.CodigoDeActivacionIncorrecto;
import ar.edu.unlam.dominioAlarma.Sensor;
import ar.edu.unlam.dominioAlarma.SensorDesactivadoException;
import ar.edu.unlam.dominioAlarma.SensorDuplicadoException;
import ar.edu.unlam.dominioAlarma.TipoDeAccion;
import ar.edu.unlam.dominioAlarma.Usuario;
import ar.edu.unlam.dominioAlarma.UsuarioActivador;
import ar.edu.unlam.dominioAlarma.UsuarioConfigurador;

import java.util.Set;
import java.util.TreeSet;

public class testAlarma {

	// > a cero si es MAYOR
	// 0 si es IGUAL
	// < a cero si es MENOR


	@Test
	public void queSePuedaRegistrarUnaAlarmaEnLaCentral() throws AlarmaYaRegistradaExeption {
		Alarma alarma = new Alarma(1, "ACTIVACION", "CONFIGURACION", "ALARMA-UNLAM");
		Central central = new Central();

		assertTrue(central.registrarAlarma(alarma));
		assertEquals((Integer) 1, alarma.getId());
		assertNotNull(alarma);

	}

	@Test
	public void queSePuedaAgregarUnUsuarioConfiguradorAUnaAlarma() throws AlarmaYaRegistradaExeption {
		Alarma alarma = new Alarma(1, "ACTIVACION", "CONFIGURACION", "ALARMA-UNLAM");
		Central central = new Central();
		assertTrue(central.registrarAlarma(alarma));

		Usuario usuarioConfigurador = new UsuarioConfigurador(1, "Hernan", "CONFIGURACION");
		boolean sePudoAgregar = alarma.agregarUsuario(usuarioConfigurador);

		assertTrue(sePudoAgregar);
		assertNotNull(central);
		assertEquals((Integer) 1, alarma.getId());
	}

	@Test(expected = AlarmaIncorrectoException.class)
	public void alAgregarUnUsuarioAUnaAlarmaConCondigoDeConfiguracionDeAlarmaInvalidoSeLanceElCodigoAlarmaIncorrectoException()
			throws AlarmaIncorrectoException, AlarmaYaRegistradaExeption {
		Alarma alarma = new Alarma(1, "ACTIVACION", "CONFIGURACION", "ALARMA-UNLAM");
		Central central = new Central();
		central.registrarAlarma(alarma);

		String codigoConfigurador = "CODIGOINVALIDO";
		Usuario usuarioConfigurador = new UsuarioConfigurador(1, "Hernan", codigoConfigurador);

		if (alarma.validarCodigoConfiguracion(codigoConfigurador)) {
			assertNull(alarma.agregarUsuario(usuarioConfigurador));
		} else
			throw new AlarmaIncorrectoException();

	}

	@Test(expected = SensorDuplicadoException.class)
	public void alAgregarUnSensorDuplicadoEnUnaAlarmaSeLanceUnaSensorDuplicadoException()
			throws SensorDuplicadoException, AlarmaYaRegistradaExeption {
		Alarma alarma = new Alarma(1, "ACTIVACION", "CONFIGURACION", "ALARMA-UNLAM");
		Central central = new Central();
		assertTrue(central.registrarAlarma(alarma));

		Usuario usuarioConfigurador = new UsuarioConfigurador(1, "Hernan", "CONFIGURACION");
		alarma.agregarUsuario(usuarioConfigurador);

		Sensor sensor = new Sensor(1, false);
		Sensor sensor2 = new Sensor(1, false);// es el mismo

		try {
			assertTrue(((UsuarioConfigurador) usuarioConfigurador).agregarSensorAAlarma(sensor, alarma));
			assertFalse(((UsuarioConfigurador) usuarioConfigurador).agregarSensorAAlarma(sensor2, alarma));
		} catch (Exception SensorDuplicadoException) {
			throw new SensorDuplicadoException();
		}

	}

	@Test(expected = SensorDesactivadoException.class)
	public void queNoSePuedaActivarUnaAlarmaSiHayAlMenosUnSensorDesactivado() throws SensorDuplicadoException,
			CodigoDeActivacionIncorrecto, AlarmaYaRegistradaExeption, SensorDesactivadoException {
		Alarma alarma = new Alarma(1, "ACTIVACION", "CONFIGURACION", "ALARMA-UNLAM");
		Central central = new Central();
		assertTrue(central.registrarAlarma(alarma));

		Usuario usuarioActivador = new UsuarioActivador(1, "Hernan");
		alarma.agregarUsuario(usuarioActivador);

		Sensor sensor = new Sensor(5, false);

		alarma.agregarSensor(sensor);

		String codigoActivacion = "ACTIVACION";

		assertFalse(((UsuarioActivador) usuarioActivador).activarDesactivarAlarma(alarma, codigoActivacion));

	}

	@Test
	public void queParaUnaAlarmaDeterminadaSePuedaObtenerUnaColeccionOrdenadaDeAccionEsDeTipoConfiguracionOrdenadasPorIdDeAccion()
			throws AccionConIdDuplicadaExeption {
		Alarma alarma = new Alarma(1, "ACTIVACION", "CONFIGURACION", "ALARMA-UNLAM");
		Central central = new Central();
		Set<Accion> accionesOrdenadas = new TreeSet<>();
		Set<Accion> accionesOrdenadasPorTipo = new TreeSet<>();

		Accion primerAccion = new Accion(TipoDeAccion.CONFIGURACION, 1);
		Accion segundaAccion = new Accion(TipoDeAccion.DESACTIVAR, 2);
		Accion tercerAccion = new Accion(TipoDeAccion.ACTIVACION, 3);

		alarma.agregarAccion(tercerAccion);
		alarma.agregarAccion(primerAccion);
		alarma.agregarAccion(segundaAccion);
// orden natural
//		for (Accion acciones : alarma.getListaDeAcciones()) {
//			if (acciones.getTipo().equals(TipoDeAccion.CONFIGURACION)) {
//				accionesOrdenadas.add(acciones);
//			}
//		}
//		for (Accion accion : accionesOrdenadas) {
//			System.out.println(accion);
//		}
//-------------------------------------------------------------------------
// orden especifico -- SI ES UN ENUM NO LO ORDENA
		accionesOrdenadasPorTipo = alarma.oredenadaPorTipo();
	
		for (Accion accion : accionesOrdenadasPorTipo) {
			System.out.println(accion);
		}
	}
}
