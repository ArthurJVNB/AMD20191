
public class Processo {
	private int id, tempoExecutado;
	
	Processo(int id) {
		this.id = id;
		tempoExecutado = 0;
	}

	public int getTempoExecutado() { return tempoExecutado; }

	public void setTempoExecutado(int tempoExecutado) { this.tempoExecutado = tempoExecutado; }

	public int getId() { return id; }
	
	@Override
	public String toString() {
		return "Processo ID " + id;
	}
}
