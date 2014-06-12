package estante;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Estante {

	private List<Livro> livros;
	private List<Lock> lockLivros;
	private List<Integer> vezesPego;

	public Estante(int nroDeLivros) {
		livros = new ArrayList<Livro>(nroDeLivros);
		List<Integer> tempoDeLeitura = gerarTemposDeLeitura(nroDeLivros);
		lockLivros = new ArrayList<Lock>(nroDeLivros);
		vezesPego = new ArrayList<Integer>();
		for(int i = 0 ; i < nroDeLivros ; ++i) {
			vezesPego.add(0);
			livros.add(new Livro(tempoDeLeitura.get(i)));
			lockLivros.add(new ReentrantLock());
		}
	}
	
	private List<Integer> gerarTemposDeLeitura(int quantidade) {
		List<Integer> tempoDeLeitura = new ArrayList<Integer>(quantidade);
		tempoDeLeitura.add(4); tempoDeLeitura.add(4);
		tempoDeLeitura.add(4); tempoDeLeitura.add(4);
		tempoDeLeitura.add(7); tempoDeLeitura.add(7);
		tempoDeLeitura.add(7); tempoDeLeitura.add(7);
		for(int i = 8 ; i < quantidade ; ++i) {
			tempoDeLeitura.add(6);
		}
		return tempoDeLeitura;
	}
	
	public int nroDeLivros() {
		return livros.size();
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
		for (int i = 0; i < vezesPego.size() ; ++i) {
			s += "Livro " + i + " foi pego " + vezesPego.get(i) + " vezes. \n";
		}
		return s;
	}
}