package time_driven;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class time_driven {
	public static void main(String[] args) {

		Random random = new Random();
		int lugares = random.nextInt(100);
		System.out.println("Tem " + lugares + " lugares na roda gigante.");
                System.out.println("Atendimento: 3 segundos");


		ArrayList<Integer> fila = new ArrayList<>();

		int vezes = 0;

		while (vezes < 5) {
			int aleatorio = random.nextInt(100);
			
			if (aleatorio == 0) {
				aleatorio = random.nextInt(100);
			}
			
			adicionarFila(fila, aleatorio);

			if (fila.isEmpty()) {
				aleatorio = random.nextInt(100);
				
				if (aleatorio == 0) {
					aleatorio = random.nextInt(100);
				}
				
				adicionarFila(fila, aleatorio);
				return;
				
			} else {
				if (fila.size() < lugares) {
					aleatorio = random.nextInt(100);
					adicionarFila(fila, aleatorio);
				} else {
					removerFila(fila, lugares);
                                        
                                       
				}
			}
			vezes++;
		}

	}

	public static void adicionarFila(ArrayList<Integer> fila, int valor) {
                if (valor == 0) {
                    return;
                }
                
                for (int i = 0; i < valor; i++) {
                    fila.add(i);
                }
                
                System.out.println("\n" + valor + " pessoas entraram na fila.");
                System.out.println("Agora tem " + fila.size() + " na fila.");
                
                // 1 segundos
                try {
                Thread.currentThread().sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(time_driven.class.getName()).log(Level.SEVERE, null, ex);
            }
                
	}

	public static void removerFila(ArrayList<Integer> fila, int valor) {
		
		if (valor == 0) {
			return;
		}
		
		for (int i = valor; i > 0; i--) {
			fila.remove(i);
		}         
               
                
		System.out.println("\n" + valor + " pessoas entraram na roda gigante.");
                // atendimento de 3 segundos
                                        try {
                                          Thread.currentThread().sleep(3000);
                                      } catch (InterruptedException ex) {
                                        // exception
                                        }
                System.out.println("Agora tem " + fila.size() + " na fila.");
                
                
	}
}




