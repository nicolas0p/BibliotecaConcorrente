package estante;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Estante {

	private List<Livro> livros;
	private List<Lock> lockLivros;
	private List<Integer> vezesPego;

	public Estante() {
		livros = new ArrayList<Livro>(8);
		int[] tempoDeLeitura = {4,4,4,4,7,7,7,7};
		lockLivros = new ArrayList<Lock>(8);
		vezesPego = new ArrayList<Integer>();
		for(int i = 0 ; i < 8 ; ++i) {
			vezesPego.add(0);
			livros.add(new Livro(tempoDeLeitura[i]));
			lockLivros.add(new ReentrantLock());
		}
	}

	public Livro pegarLivro(int index) throws LivroPegoException {
		if(lockLivros.get(index).tryLock()) {
			vezesPego.set(index, vezesPego.get(index) + 1);
			return livros.get(index);
		}
		throw new LivroPegoException(); 
	}

	public void retornarLivro(Livro retornado) {
		int index = livros.indexOf(retornado);
		lockLivros.get(index).unlock();
	}
	
	public String maisLidos() {
		String s = "";
		for (int i = 0; i < 8; ++i) {
			s += "Livro " + i + " foi pego " + vezesPego.get(i) + " vezes. \n";
		}
		return s;
	}
}