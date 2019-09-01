import java.util.ArrayList;

public class Main {

	static final int TEMPO_SIMULACAO = 1000;
	static int tempoAtual = 0;
	static Processador processador;
	static ArrayList<Processo> processosEmFila;
	static Log log;
	
	public static void main(String[] args) {
		iniciarValores();
		iniciarSimulacao();
	}

	public static Processo gerarProcessoAleatorio() {
		return new Processo((int) (Math.random() * 10000));
	}
	
	public static void iniciarValores() {
		processosEmFila = new ArrayList<>();
		processador = Processador.getInstance();
		log = Log.getInstance();
	}
	
	public static void iniciarSimulacao() {
		while (true) {
			tempoAtual++;
			
			// Gerar aleatoriamente alguns processos
			if((int) (Math.random()*10) % 2 == 0) {
				int qtdProcessos = (int) (Math.random() * 10 - 5);
				qtdProcessos = qtdProcessos < 0 ? 0 : qtdProcessos;
				
				for (int i = 0; i < qtdProcessos; i++) {
					processosEmFila.add(new Processo((int) (Math.random() * 10000)));
				}
			}
			
			// Processar alguns dos processos
			if (processador.podeProcessar(tempoAtual)) {
				Processo processoAtual = processador.getProcessoAtual();
				
				if (processoAtual != null) {
					log.addProcessoFeito(processoAtual);
					
					processosEmFila.remove(processoAtual);
					processador.terminarProcessoAtual();
				}
				
				if (processosEmFila.size() > 0) {
					Processo processoNovo = processosEmFila.get(0);
					processador.processar(processoNovo, tempoAtual);
					processosEmFila.remove(processoNovo);
				}
			}
			
			// Condição para finalizar simulação e gerar relatório
			if (tempoAtual > TEMPO_SIMULACAO) {
				int totalProcessos = processosEmFila.size() + log.getQtdProcessosFeitos();
				
				System.out.println("Processos em fila: " + processosEmFila.size());
				System.out.println("Processos feitos: " + log.getQtdProcessosFeitos());
				System.out.println("Processos gerados: " + totalProcessos);
				System.out.println("Tempo processando: " + log.getTempoProcessado());
				System.out.println("Tempo total: " + TEMPO_SIMULACAO);
				System.out.println("Porcentagem de processos concluídos: " + (((float) processosEmFila.size()/totalProcessos) * 100) + "%");
				
				return;
			}
		}
	}
}