package Peaje;

public class Semaforo {

	private int valor = 1;

	synchronized void decrementar(){
		while (valor <= 0) {
			try {
				wait();
			} catch (InterruptedException e) {
			}
		}
		valor--;
	}

	synchronized void incrementa() {
		valor++;
		notify();
	}

	public Semaforo(){

	}

}