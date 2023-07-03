package ar.edu.unlam.dominioAlarma;

public class Accion implements Comparable<Accion> {
	private TipoDeAccion tipoDeOperacion;
	private Integer id;

	public Accion(TipoDeAccion tipoDeOperacion, Integer id) {
		this.tipoDeOperacion = tipoDeOperacion;
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public TipoDeAccion getTipo() {
		return tipoDeOperacion;
	}

	public void setTipoDeOperacion(TipoDeAccion tipoDeOperacion) {
		this.tipoDeOperacion = tipoDeOperacion;
	}

	@Override
	public int compareTo(Accion otraAcccion) {
		return this.getId().compareTo(otraAcccion.getId());
	}

	@Override
	public String toString() {
		return "Accion: tipoDeOperacion=" + tipoDeOperacion + ", id=" + id;
	}

}
