import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class Estante {
	
	private List<Livro> livros;
	private List<Lock> acessoAosLivros;
	
	public Estante() {
		livros = new ArrayList<Livro>(8);
		int[] tempoDeLeitura = {4,4,4,4,7,7,7,7};
		acessoAosLivros = new ArrayList<Lock>(8);
		for(int i = 0 ; i < 8 ; ++i) {
			livros.add(new Livro(i,tempoDeLeitura[i]));
			acessoAosLivros.add(new ReentrantLock());
		}
	}
	
	public Livro pegarLivro(int index) throws LivroPegoException {
		if(acessoAosLivros.get(index).tryLock()) {
			return livros.get(index);
		}
		throw new LivroPegoException(); //Se o lock do livro já tiver sido pego, joga uma exception
	}
	
	public void retornarLivro(Livro retornado) {
		int index = livros.indexOf(retornado);
		acessoAosLivros.get(index).unlock();
	}
}
