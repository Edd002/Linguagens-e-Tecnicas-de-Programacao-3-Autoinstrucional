package controller;

/**
 * @author Eduardo Augusto
 * 
 * Classe para tratamento de exceções de regras de negócio.
 */
public class SisComException extends Exception {
	private static final long serialVersionUID = 1L;
	private String mensagemErro;

	public SisComException() {
	}

	public SisComException(String mensagemErro) {
		this.mensagemErro = mensagemErro;
	}

	public String getMensagemErro() {
		return mensagemErro;
	}
	public void setMensagemErro(String mensagemErro) {
		this.mensagemErro = mensagemErro;
	}

	@Override
	public String toString() {
		return "SisComException [mensagemErro=" + mensagemErro + "]";
	}
}