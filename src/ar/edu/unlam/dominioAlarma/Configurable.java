package ar.edu.unlam.dominioAlarma;

public interface Configurable {

	public void registrarUnaAlarmaEnUnaCentral(Alarma alarma, Central central) throws AlarmaYaRegistradaExeption;
}
