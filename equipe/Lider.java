package equipe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import estante.Estante;

public class Lider implements Observer, Runnable {
	
	private Estante estante;
	private Equipe equipe;
	List<Integer> sequenciaLeitura;
	
	public Lider() {
		sequenciaLeitura = new ArrayList<>(8);
		for (int i = 0; i < 8; ++i) {
			sequenciaLeitura.add(i);
		}
		Collections.shuffle(sequenciaLeitura);
		sequenciaLeitura.subList(5, 8).clear();
	}
	
	
	public void pegaLivro() {
		
	}
	
	

//	@Override
	public void update(Observable o, Object arg) {
////		estante.recebeLivro(livro);
////		livro = estante.pegaLivro();
	}

	@Override
	public void run() {
		
	}
}
