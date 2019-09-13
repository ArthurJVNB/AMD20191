package event_driven;
import java.util.ArrayList;

public class Main {

	static final int TEMPO_SIMULACAO = 60000; // milissegundos
	static int tempoAtual = 0; // milissegundos
	static final int TEMPO_INTERVALO = 500; // milissegundos
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
		Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				while (true) {
					System.out.println("Tempo atual: " + tempoAtual + "ms");
					
					// Gerar aleatoriamente alguns processos
					if((tempoAtual % 1000 == 0) && (int) (Math.random()*10) % 2 == 0) {
						int qtdProcessos = (int) (Math.random() * 10 - 5);
						qtdProcessos = qtdProcessos < 0 ? 0 : qtdProcessos;
						
						for (int i = 0; i < qtdProcessos; i++) {
							processosEmFila.add(new Processo((int) (Math.random() * 10000)));
						}

						if (qtdProcessos > 0) {
							System.out.println("+ " + qtdProcessos + " processo(s)");
							System.out.println("Processos em fila: " + processosEmFila.size());
						}
					}
					
					// Processar alguns dos processos
					if (processador.podeProcessar(tempoAtual)) {
						Processo processoAtual = processador.getProcessoAtual();
						
						if (processoAtual != null) {
							// Processo concluído
							log.addProcessoFeito(processoAtual);
							
							processosEmFila.remove(processoAtual);
							processador.terminarProcessoAtual();

							System.out.println("Processo de " + processoAtual.getTempoExecutado() + "ms concluído");
							System.out.println("- 1 processo");
							System.out.println("Processos em fila: " + processosEmFila.size());
						}
						
						if (processosEmFila.size() > 0) {
							// Processando novo processo
							
							Processo processoNovo = processosEmFila.get(0);
							processador.processar(processoNovo, tempoAtual);
							processosEmFila.remove(processoNovo);
							
							System.out.println("Processo de " + processoNovo.getTempoExecutado() + "ms executando");
						}
					}
					
					// Condição para finalizar simulação e gerar relatório
					if (tempoAtual > TEMPO_SIMULACAO) {
						int totalProcessos = processosEmFila.size() + log.getQtdProcessosFeitos();
						float porcentagemProcessosConcluidos = ((float) log.getQtdProcessosFeitos()/totalProcessos) * 100;
						float porcentagemTempoProcessado = (log.getTempoProcessado()/(float) TEMPO_SIMULACAO) * 100;
						
						System.out.println("============ RESUMO ============");
						System.out.println("Processos feitos: " + log.getQtdProcessosFeitos());
						System.out.println("Processos em fila: " + processosEmFila.size());
						System.out.println("Processos gerados: " + totalProcessos);
						System.out.println("Tempo processando: " + log.getTempoProcessado() + "ms");
						System.out.println("Tempo total: " + TEMPO_SIMULACAO + "ms");
						System.out.println("Porcentagem de processos concluídos: " + porcentagemProcessosConcluidos + "%");
						System.out.println("Porcentagem de tempo processando: " + porcentagemTempoProcessado + "%");
						System.out.println("================================");
						
						return;
					}
					
					tempoAtual+=TEMPO_INTERVALO;
					
					try {
						Thread.sleep(TEMPO_INTERVALO);
						} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				
			}
		});
		thread.start();
	}
}