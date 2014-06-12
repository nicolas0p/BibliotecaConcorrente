import equipe.Equipe;
import estante.Estante;



public class Main {

	
	public static void main(String[] args) {
		Estante estante = new Estante();
		for (int i = 0; i < 15; ++i) {
			new Thread(new Equipe(estante), "" + i).start();
		}
	}
}
