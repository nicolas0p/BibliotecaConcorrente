package equipe;

import java.util.Observable;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

import estante.Livro;

public class Aluno extends Observable implements Runnable {

	private Livro livro;
	private Lock lock;
	private Condition condition;
	
	public Aluno(Lock lock, Condition condition) {
		this.lock = lock;
		this.condition = condition;		
	}
	
	public void setLivro(Livro livro) {
		this.livro = livro;
	}
	
	public void run() {
//		boolean conseguiu = false;	
//		while(!conseguiu) {
//			try {
//				condition.await();
//			} catch (InterruptedException e) {}
//			conseguiu = livro.tentaLer();
//		}
//		condition.signalAll();
		while (!lock.tryLock()) {
			try {
				condition.await();
			} catch (InterruptedException e) {}
		}
		livro.ler();
		setChanged();
		notifyObservers();
		lock.unlock();         /////tretaaa ordem entre signallall e unlock
		condition.signalAll();
	}	
}
