package equipe;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import estante.Estante;
import estante.Livro;

public class Equipe implements Observer, Runnable {

	private int alunosRestantes;
	private Lider lider;
	private List<Aluno> alunos;
	private Thread threadLider;
	boolean concluida;

	public Equipe(Estante estante) {
		concluida = false;
		alunos = new ArrayList<Aluno>();
		alunosRestantes = 3;
		lider = new Lider(estante, this, estante.nroDeLivros());
		for (int i = 0; i < 3; ++i) {
			Aluno aluno = new Aluno();
			aluno.addObserver(this);
			alunos.add(aluno);
		}
	}

	public boolean isConcluida() {
		return concluida;
	}

	public void setConcluida(boolean concluida) {
		this.concluida = concluida;
	}

	public void repassarLivro(Livro livro) {
		System.out.println("Lider da equipe " + Thread.currentThread().getName() + " repassando o livro");
		for (Aluno aluno : alunos) {
			aluno.setLivro(livro);
			new Thread(aluno, Thread.currentThread().getName()).start();
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		--alunosRestantes;
		if (alunosRestantes == 0) {
			alunosRestantes = 3;
			threadLider.interrupt(); // devolve livro e pega outro se for o caso
		}
	}

	@Override
	public void run() {
		System.out.println("Iniciando equipe " + Thread.currentThread().getName());
		threadLider = new Thread(lider, Thread.currentThread().getName());
		threadLider.start();
	}
}
