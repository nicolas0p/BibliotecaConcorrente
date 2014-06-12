package professor;

import java.util.Observable;
import java.util.Observer;

import equipe.Equipe;
import estante.Estante;

public class Professor implements Observer {
	
	int totalEquipesConcluidas;
	int equipesConcluidasPrazo;
	long tempoInicio;
	Estante estante;
	
	public Professor() {
		totalEquipesConcluidas = 0;
		equipesConcluidasPrazo = 0;
	}
	
	public void iniciarTrabalho() throws InterruptedException {
		estante = new Estante(8);
		tempoInicio = System.currentTimeMillis();
		for (int i = 0; i < 15; ++i) {
			Equipe equipe = new Equipe(estante);
			equipe.addObserver(this);
			new Thread(equipe, "" + i).start();
		}		
	}

	@Override
	public void update(Observable o, Object arg) {
		++totalEquipesConcluidas;
		//System.out.println("\n\n tempo entrega :" + (System.currentTimeMillis() - tempoInicio) + "\n");
		if (System.currentTimeMillis() - tempoInicio < 200) {	// se tempo atual eh menor que 200 ms 
			++equipesConcluidasPrazo;
		}
		if (totalEquipesConcluidas == 15) {
			mostrarResultados();
		}
	}

	private void mostrarResultados() {
		System.out.println("O numero de equipes que entregou o trabalho no prazo eh: " + equipesConcluidasPrazo);
		System.out.println("Livros mais lidos: \n" + estante.maisLidos());
		System.out.println("Foi necessario " + (System.currentTimeMillis() - tempoInicio) + " milisegundos para todos terminarem.");
	}
}
