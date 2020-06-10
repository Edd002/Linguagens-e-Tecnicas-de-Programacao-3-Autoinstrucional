package model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

/**
 * @author Eduardo Augusto
 * 
 * Classe abstrata Pessoa.
 */
@Entity(name="Pessoa_SC")
@Table(name="Pessoa_SC")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Pessoa implements Comparable<Pessoa> {
	@Id
	@Column(name="codigo")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected int codigo;

	@Column(name="nome", nullable=false, columnDefinition="varchar(255)")
	protected String nome;

	@Column(name="telefone_fixo", columnDefinition="char(14)")
	protected String telefoneFixo;

	@Column(name="telefone_celular", nullable=false, columnDefinition="char(15)")
	protected String telefoneCelular;

	@Column(name="email", nullable=false, columnDefinition="varchar(255)")
	protected String email;

	@Column(name="data_cad", nullable=false)
	protected Date dataCad;

	@Column(name="login", nullable=false, columnDefinition="varchar(255)")
	protected String login;

	@Column(name="senha", nullable=false, columnDefinition="varchar(255)")
	protected String senha;

	public Pessoa() {
	}

	public Pessoa(int codigo, String nome, String telefoneFixo, String telefoneCelular, String email, Date dataCad, String login, String senha) {
		this.codigo = codigo;
		this.nome = nome;
		this.telefoneFixo = telefoneFixo;
		this.telefoneCelular = telefoneCelular;
		this.email = email;
		this.dataCad = dataCad;
		this.login = login;
		this.senha = senha;
	}

	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getTelefoneFixo() {
		return telefoneFixo;
	}
	public void setTelefoneFixo(String telefoneFixo) {
		this.telefoneFixo = telefoneFixo;
	}
	public String getTelefoneCelular() {
		return telefoneCelular;
	}
	public void setTelefoneCelular(String telefoneCelular) {
		this.telefoneCelular = telefoneCelular;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getDataCad() {
		return dataCad;
	}
	public void setDataCad(Date dataCad) {
		this.dataCad = dataCad;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Override
	public String toString() {
		return "Pessoa [codigo=" + codigo + ", nome=" + nome + ", telefoneFixo=" + telefoneFixo + ", telefoneCelular=" + telefoneCelular + ", email=" + email + ", dataCad=" + dataCad + ", login=" + login + ", senha=" + senha + "]";
	}

	/**
	 * @author Eduardo Augusto
	 * @return int
	 * @param Pessoa pessoa
	 * @exception
	 * 
	 * Método int de comparação de nome entre duas pessoas:
	 * 0 para igual em conteúdo e tamanho
	 * 1 para maior ou igual em tamanho
	 * -1 para menor em tamanho
	 */
	@Override
	public int compareTo(Pessoa pessoa) {
		if (pessoa.getNome().equals(this.nome))
			return 0;
		else
			return pessoa.getNome().length() >= this.nome.length() ? 1 : -1;
	}
}