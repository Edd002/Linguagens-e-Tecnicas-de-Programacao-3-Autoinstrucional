package model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * @author Eduardo Augusto
 *
 * Classe Fornecedor.
 */
@Entity(name="Fornecedor_SC")
@Table(name="Fornecedor_SC")
@PrimaryKeyJoinColumn(name="codigo_pessoa")
public class Fornecedor extends Pessoa {
	@Column(name="cnpj", nullable=false, columnDefinition="char(18)")
	private String cnpj;

	@Column(name="nome_contato", nullable=false, columnDefinition="varchar(255)")
	private String nomeContato;

	public Fornecedor() {
	}

	public Fornecedor(int codigo, String nome, String telefoneFixo, String telefoneCelular, String email, Date dataCad, String login, String senha, String cnpj, String nomeContato) {
		super(codigo, nome, telefoneFixo, telefoneCelular, email, dataCad, login, senha);
		this.cnpj = cnpj;
		this.nomeContato = nomeContato;
	}

	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public String getNomeContato() {
		return nomeContato;
	}
	public void setNomeContato(String nomeContato) {
		this.nomeContato = nomeContato;
	}

	@Override
	public String toString() {
		return "Fornecedor [cnpj=" + cnpj + ", nomeContato=" + nomeContato + ", codigo=" + codigo + ", nome=" + nome + ", telefoneFixo=" + telefoneFixo + ", telefoneCelular=" + telefoneCelular + ", email=" + email + ", dataCad=" + dataCad + ", login=" + login + ", senha=" + senha + "]";
	}
}