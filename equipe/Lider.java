package equipe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import estante.Estante;
import estante.Livro;
import estante.LivroPegoException;

public class Lider implements Runnable {

	private Estante estante;
	private Equipe equipe;
	List<Integer> sequenciaLeitura;
	boolean bloqueado = false;
	private int livrosLidos;
	Livro livroAtual;

	public Lider(Estante estante, Equipe equipe) {
		livrosLidos = 0;
		this.estante = estante;
		this.equipe = equipe;
		sequenciaLeitura = new ArrayList<Integer>();
		for (int i = 0; i < 8; ++i) {
			sequenciaLeitura.add(i);
		}
	}

	private void pegaLivro() {
		Collections.shuffle(sequenciaLeitura);
		for (int i = 0; i < sequenciaLeitura.size(); ++i) {
			try {	
				livroAtual = estante.pegarLivro(sequenciaLeitura.get(i));
				System.out.println("Lider da equipe " + Thread.currentThread().getName() + " conseguiu pegar o livro" + sequenciaLeitura.get(i));
				sequenciaLeitura.remove(i);
				livroAtual.ler();
				++livrosLidos;
				equipe.repassarLivro(livroAtual);
				return;
			}
			catch (LivroPegoException e) {
				//System.out.println("Lider da equpe " + Thread.currentThread().getName() + " nao conseguiu pegar o livro" + sequenciaLeitura.get(i));
				if (i == sequenciaLeitura.size() - 1) {
					Collections.shuffle(sequenciaLeitura);
					i = 0;
				}
			}
		}
	}

	@Override
	public void run() {
		System.out.println("Iniciando lider da equipe " + Thread.currentThread().getName());
		pegaLivro();
		while (true) {
			try {
				Thread.sleep(Long.MAX_VALUE);
			}
			catch (InterruptedException e) {
				System.out.println("Lider da equipe " + Thread.currentThread().getName() + " devolvendo livro!");
				estante.retornarLivro(livroAtual);
				if (livrosLidos < 5) {
					pegaLivro();
				} else {
					equipe.setConcluida(true);
					return;
				}
			}
		}		
	}

	public void retornarLivro(Livro livro) {
		estante.retornarLivro(livro);
	}
}
