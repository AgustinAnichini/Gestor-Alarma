package ar.edu.unlam.dominioAlarma;

import java.util.Comparator;

public class OrdenPorTipo implements Comparator<Accion> {

	@Override
	public int compare(Accion primerAccion, Accion segundaAccion) {
		return primerAccion.getTipo().compareTo(segundaAccion.getTipo());
	}

}
