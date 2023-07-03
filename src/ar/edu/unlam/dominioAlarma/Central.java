package ar.edu.unlam.dominioAlarma;

import java.lang.reflect.Array;
import java.util.HashSet;
import java.util.Set;

public class Central {

	private Set<Alarma> alarmas;

	public Central() {
		this.alarmas = new HashSet<>();
	}

	public boolean registrarAlarma(Alarma nuevaAlarma) throws AlarmaYaRegistradaExeption {
		if (validarAlarmaRegistrada(nuevaAlarma)) {
			throw new AlarmaYaRegistradaExeption();
		}
		alarmas.add(nuevaAlarma);
		return true;

	}

	private boolean validarAlarmaRegistrada(Alarma nuevaAlarma) {
		if (alarmas.contains(nuevaAlarma)) {
			return true;
		}
		return false;
	}
	
}
