package model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * @author Eduardo Augusto
 *
 * Classe Cliente.
 */
@Entity(name="Cliente_SC")
@Table(name="Cliente_SC")
@PrimaryKeyJoinColumn(name="codigo_pessoa")
public class Cliente extends Pessoa {
	@Column(name="cpf", nullable=false, columnDefinition="char(14)")
	private String cpf;

	@Column(name="limite_credito", nullable=false)
	private double limiteCredito;

	public Cliente() {
	}

	public Cliente(int codigo, String nome, String telefoneFixo, String telefoneCelular, String email, Date dataCad, String login, String senha, String cpf, double limiteCredito) {
		super(codigo, nome, telefoneFixo, telefoneCelular, email, dataCad, login, senha);
		this.cpf = cpf;
		this.limiteCredito = limiteCredito;
	}

	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public double getLimiteCredito() {
		return limiteCredito;
	}
	public void setLimiteCredito(double limiteCredito) {
		this.limiteCredito = limiteCredito;
	}

	@Override
	public String toString() {
		return "Cliente [cpf=" + cpf + ", limiteCredito=" + limiteCredito + ", codigo=" + codigo + ", nome=" + nome + ", telefoneFixo=" + telefoneFixo + ", telefoneCelular=" + telefoneCelular + ", email=" + email + ", dataCad=" + dataCad + ", login=" + login + ", senha=" + senha + "]";
	}
}