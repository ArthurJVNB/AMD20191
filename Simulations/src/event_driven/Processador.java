package event_driven;

public class Processador {
	private int tempoParaProximoProcessamento;
	private Processo processoAtual;
	
	// ====== INICIO SINGLETON DE BILL PUGH ======
	private Processador() {
		tempoParaProximoProcessamento = 0;
		processoAtual = null;
	}
	
	private static class BillPughSingleton {
		static final Processador INSTANCE = new Processador();
	}
	
	public static Processador getInstance() {
		return BillPughSingleton.INSTANCE;
	}
	// ======= FIM SINGLETON DE BILL PUGH ========
	
	public Processo getProcessoAtual() {
		return processoAtual;
	}
	
	public boolean podeProcessar(int tempoAtual) {
		return tempoAtual > tempoParaProximoProcessamento ? true : false;
	}
	
	public void processar(Processo p, int tempoAtual) {
		processoAtual = p;
		int tempoDeProcessamento = (int) (Math.random() * 10);
		p.setTempoExecutado(tempoDeProcessamento);
		tempoParaProximoProcessamento = tempoAtual + tempoDeProcessamento;
	}
	
	public void terminarProcessoAtual() {
		processoAtual = null;
		tempoParaProximoProcessamento = 0;
	}
}
