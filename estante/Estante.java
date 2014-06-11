package estante;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Estante {

	private List<Livro> livros;
	private List<Lock> lockLivros;

	public Estante() {
		livros = new ArrayList<Livro>(8);
		int[] tempoDeLeitura = {4,4,4,4,7,7,7,7};
		lockLivros = new ArrayList<Lock>(8);
		for(int i = 0 ; i < 8 ; ++i) {
			livros.add(new Livro(tempoDeLeitura[i]));
			lockLivros.add(new ReentrantLock());
		}
	}

	public Livro pegarLivro(int index) throws LivroPegoException {
		if(lockLivros.get(index).tryLock()) {
			return livros.get(index);
		}
		throw new LivroPegoException(); 
	}

	public void retornarLivro(Livro retornado) {
		int index = livros.indexOf(retornado);
		lockLivros.get(index).unlock();
	}
}