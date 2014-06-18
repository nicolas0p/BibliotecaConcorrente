package BibliotecaConcorrente.equipe;

import java.util.Observable;
import BibliotecaConcorrente.estante.Livro;

public class Aluno extends Observable implements Runnable {

	private Livro livro;
	
	public Aluno() {
	}
	
	public void setLivro(Livro livro) {
		this.livro = livro;
	}
	
	public void run() {
		livro.ler();
		setChanged();
		notifyObservers();
	}	
}
