package equipe;

import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Equipe implements Observer, Runnable {

	
	private Lock lock;
	private Condition condition;
	private int alunosRestantes;
	private Lider lider;
	private List<Aluno> alunos;
	
	public Equipe() {
		lock = new ReentrantLock();
		condition = lock.newCondition();
		lider = new Lider(lock, condition);
		for (int i = 0; i < 3; ++i) {
			alunos.add(new Aluno(lock, condition));
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		--integrantesRestantes;
		if (integrantesRestantes == 0) {
			
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
	
}
