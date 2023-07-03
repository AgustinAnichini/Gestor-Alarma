package ar.edu.unlam.dominioAlarma;

public class Usuario {
	private Integer id;
	private String nombre;
	private String codigoDeconfiguracion;

	public Usuario(Integer id, String nombre, String codigoDeconfiguracion) {
		this.id = id;
		this.nombre = nombre;
		this.codigoDeconfiguracion = codigoDeconfiguracion;
	}

	public Usuario(Integer id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCodigoDeconfiguracion() {
		return codigoDeconfiguracion;
	}

	public void setCodigoDeconfiguracion(String configuracion) {
		this.codigoDeconfiguracion = configuracion;
	}

}
