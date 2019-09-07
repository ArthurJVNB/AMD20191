package event_driven;
import java.util.ArrayList;

public class Log {
	private int tempoProcessado;
	private ArrayList<Processo> processosFeitos;
	
	// ====== INICIO SINGLETON DE BILL PUGH ======
	private Log() {
		tempoProcessado = 0;
		processosFeitos = new ArrayList<Processo>();
	}
	
	private static class BillPughSingleton {
		private static final Log INSTANCE = new Log();
	}
	
	public static Log getInstance() {
		return BillPughSingleton.INSTANCE;
	}
	// ======= FIM SINGLETON DE BILL PUGH ========
	
	public void addProcessoFeito(Processo p) {
		processosFeitos.add(p);
		tempoProcessado += p.getTempoExecutado();
	}
	
	public int getTempoProcessado() {
		return tempoProcessado;
	}
	
	public int getQtdProcessosFeitos() {
		return processosFeitos.size();
	}
}
