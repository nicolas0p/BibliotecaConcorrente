import equipe.Equipe;
import estante.Estante;



public class Main {

	
	public static void main(String[] args) throws InterruptedException {
		Estante estante = new Estante(10);
		for (int i = 0; i < 15; ++i) {
			new Thread(new Equipe(estante), "" + i).start();
		}
		Thread.sleep(200);
		System.out.println(estante.maisLidos());
	}
}
