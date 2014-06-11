package equipe;

import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import estante.*;

public class Equipe implements Observer, Runnable {

	
	private Lock lock;
	private int alunosRestantes;
	private Lider lider;
	private List<Aluno> alunos;
	
	public Equipe() {
		lock = new ReentrantLock();
		lider = new Lider(lock);
		for (int i = 0; i < 3; ++i) {
			alunos.add(new Aluno(lock, condition));
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		--integrantesRestantes;
		if (integrantesRestantes == 0) {
			Livro livro = lider.pegaLivro();
			for (int i = 0; i < 3; ++i) {
				alunos.livro
			}
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
	public void repassarLivro(Livro livro) {
		for(Aluno aluno : alunos) {
			aluno.setLivro(livro);
			Thread threadAluno = new Thread(aluno);
			threadAluno.start();
		}
	}
	
}
