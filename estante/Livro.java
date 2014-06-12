package estante;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Livro {

	private Lock lockEquipe;
	private int tempoDeLeitura;

	public Livro(int tempo) {
		lockEquipe = new ReentrantLock();
		tempoDeLeitura = tempo;
	}

	public void ler() {
		lockEquipe.lock();
		System.out.println("Integrante da equipe " + Thread.currentThread().getName() + " lendo o livro");
		try {
			Thread.sleep(tempoDeLeitura);
		} catch (InterruptedException e) {
		}
		lockEquipe.unlock();
	}
}
