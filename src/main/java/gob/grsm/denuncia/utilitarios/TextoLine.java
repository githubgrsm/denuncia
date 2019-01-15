package gob.grsm.denuncia.utilitarios;

public class TextoLine {
	
	Integer nrolinea;
	String texto;
	
	
	public TextoLine() {
	}
	
	
	
	
	public TextoLine(Integer nrolinea, String texto) {
		super();
		this.nrolinea = nrolinea;
		this.texto = texto;
	}




	public Integer getNrolinea() {
		return nrolinea;
	}
	public void setNrolinea(Integer nrolinea) {
		this.nrolinea = nrolinea;
	}
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	
	

}
