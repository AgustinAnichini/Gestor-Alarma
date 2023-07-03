package ar.edu.unlam.dominioAlarma;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class Alarma {

	private Integer id;
	private String codigoDeActivacion;
	private String codigoDeconfiguracion;
	private String lugar;
	private Usuario usuarioConfigurador;
	private Set<Sensor> sensores;
	private Set<Accion> listaDeAcciones;

	public Alarma(Integer id, String codigoDeActivacion, String codigoDeconfiguracion, String lugar) {
		this.id = id;
		this.codigoDeActivacion = codigoDeActivacion;
		this.codigoDeconfiguracion = codigoDeconfiguracion;
		this.lugar = lugar;
		this.usuarioConfigurador = null;
		this.sensores = new HashSet<>();
		this.listaDeAcciones = new TreeSet<>();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCodigoDeActivacion() {
		return codigoDeActivacion;
	}

	public void setCodigoDeActivacion(String activacion) {
		this.codigoDeActivacion = activacion;
	}

	public String getCodigoDeconfiguracion() {
		return codigoDeconfiguracion;
	}

	public void setCodigoDeconfiguracion(String configuracion) {
		this.codigoDeconfiguracion = configuracion;
	}

	public String getLugar() {
		return lugar;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
	}

	public Set<Sensor> getSensores() {
		return sensores;
	}

	public void setSensores(Set<Sensor> sensores) {
		this.sensores = sensores;
	}

	public Set<Accion> getListaDeAcciones() {
		return listaDeAcciones;
	}

	public void setListaDeAcciones(Set<Accion> listaDeAcciones) {
		this.listaDeAcciones = listaDeAcciones;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Alarma other = (Alarma) obj;
		return Objects.equals(id, other.id);
	}

	public boolean agregarUsuario(Usuario nuevoUsuarioConfigurador) {
		if (this.usuarioConfigurador == null) {
			setUsuarioConfigurador(nuevoUsuarioConfigurador);
			return true;
		}
		return false;
	}

	public Usuario getUsuarioConfigurador() {
		return usuarioConfigurador;
	}

	public void setUsuarioConfigurador(Usuario usuarioConfigurador) {
		this.usuarioConfigurador = usuarioConfigurador;
	}

	public boolean validarCodigoConfiguracion(String codigoConfigurador) throws AlarmaIncorrectoException {
		if (this.codigoDeconfiguracion.equals(codigoConfigurador)) {
			return true;
		}
		throw new AlarmaIncorrectoException();
	}

	public boolean agregarSensor(Sensor sensor) throws SensorDuplicadoException {
		if (!(validarSensorExistente(sensor))) {
			this.sensores.add(sensor);
			return true;
		}
		return false;
	}

	private boolean validarSensorExistente(Sensor sensor) throws SensorDuplicadoException {
		if (this.sensores.contains(sensor)) {
			throw new SensorDuplicadoException();
		}
		return false;
	}

	public boolean verificarSensores() throws SensorDesactivadoException {
		for (Sensor sensor : sensores) {
			if (sensor.getEstado() == false) {
				throw new SensorDesactivadoException();
			}
		}
		return true;
	}

	public boolean agregarAccion(Accion nuevaAccion) throws AccionConIdDuplicadaExeption {
		if (!(validarSiContieneAccion(nuevaAccion))) {
			listaDeAcciones.add(nuevaAccion);
			return true;
		}
		return false;
	}

	private boolean validarSiContieneAccion(Accion nuevaAccion) throws AccionConIdDuplicadaExeption {
		if (listaDeAcciones.contains(nuevaAccion)) {
			throw new AccionConIdDuplicadaExeption();
		}
		return false;
	}

	public Set<Accion> oredenadaPorTipo() {
		Set<Accion> ordenadasPorTipo = new TreeSet<>(new OrdenPorTipo());

		ordenadasPorTipo.addAll(listaDeAcciones);
		return ordenadasPorTipo;
	}
}
