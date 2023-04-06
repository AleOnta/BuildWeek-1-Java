package model_parco_mezzi;

public enum E_Veicolo {
	TRAM("TRAM"),
	AUTOBUS("AUTOBUS");
	
	
	private String tipo;
	
	E_Veicolo(String tipo) {
		this.tipo = tipo;
	}
	
	public String getTipo() {
		return tipo;
	}
}

