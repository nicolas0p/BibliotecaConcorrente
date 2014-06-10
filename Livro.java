import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class Livro {
	
	private final int tag;
	private Lock leitura;
	private Long tempoDeLeitura;
	
	public Livro(int numero, long tempo) {
		tag = numero;
		leitura = new ReentrantLock();
		tempoDeLeitura = tempo;
	}
	
	public void ler() {
		leitura.lock();
		try{
			Thread.sleep(tempoDeLeitura);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		finally{
			leitura.unlock();
		}
	}
	
	public int tag() {
		return tag;
	}
}
