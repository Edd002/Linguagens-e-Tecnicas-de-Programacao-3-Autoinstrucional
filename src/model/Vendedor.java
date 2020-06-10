package model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * @author Eduardo Augusto
 *
 * Classe Vendedor.
 */
@Entity(name="Vendedor_SC")
@Table(name="Vendedor_SC")
@PrimaryKeyJoinColumn(name="codigo_pessoa")
public class Vendedor extends Pessoa {
	@Column(name="cpf", nullable=false, columnDefinition="char(14)")
	private String cpf;

	@Column(name="meta_mensal", nullable=false)
	private double metaMensal;

	public Vendedor() {
	}

	public Vendedor(int codigo, String nome, String telefoneFixo, String telefoneCelular, String email, Date dataCad, String login, String senha, String cpf, double metaMensal) {
		super(codigo, nome, telefoneFixo, telefoneCelular, email, dataCad, login, senha);
		this.cpf = cpf;
		this.metaMensal = metaMensal;
	}

	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public double getMetaMensal() {
		return metaMensal;
	}
	public void setMetaMensal(double metaMensal) {
		this.metaMensal = metaMensal;
	}

	@Override
	public String toString() {
		return "Vendedor [cpf=" + cpf + ", metaMensal=" + metaMensal + ", codigo=" + codigo + ", nome=" + nome + ", telefoneFixo=" + telefoneFixo + ", telefoneCelular=" + telefoneCelular + ", email=" + email + ", dataCad=" + dataCad + ", login=" + login + ", senha=" + senha + "]";
	}
}