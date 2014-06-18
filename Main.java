package BibliotecaConcorrente;

import BibliotecaConcorrente.professor.Professor;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		int concluidas = 0;
		int livrosAdicionados = 0;
		for(;concluidas < 15;++livrosAdicionados) {
			Professor professor = new Professor();
			professor.iniciarTrabalho(8+livrosAdicionados);
			concluidas = professor.equipesConcluidasNoPrazo();
		}
		System.out.println("Foram necessários " + (8+livrosAdicionados) + " livros para que todas as equipes terminassem no prazo de 200 ms");
	}
}
