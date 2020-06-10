package model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Eduardo Augusto
 *
 * Classe Produto.
 */
@Entity(name="Produto_SC")
@Table(name="Produto_SC")
public class Produto implements Comparable<Produto> {
	@Id
	@Column(name="codigo")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int codigo;

	@Column(name="nome", nullable=false, columnDefinition="varchar(255)")
	private String nome;

	@Column(name="preco_unitario", nullable=false)
	private double precoUnitario;

	@Column(name="estoque", nullable=false)
	private int estoque;

	@Column(name="estoque_minimo", nullable=false)
	private int estoqueMinimo;

	@Column(name="data_cad", nullable=false)
	private Date dataCad;

	public Produto() {
	}

	public Produto(int codigo, String nome, double precoUnitario, int estoque, int estoqueMinimo, Date dataCad) {
		this.codigo = codigo;
		this.nome = nome;
		this.precoUnitario = precoUnitario;
		this.estoque = estoque;
		this.estoqueMinimo = estoqueMinimo;
		this.dataCad = dataCad;
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
	public double getPrecoUnitario() {
		return precoUnitario;
	}
	public void setPrecoUnitario(double precoUnitario) {
		this.precoUnitario = precoUnitario;
	}
	public int getEstoque() {
		return estoque;
	}
	public void setEstoque(int estoque) {
		this.estoque = estoque;
	}
	public int getEstoqueMinimo() {
		return estoqueMinimo;
	}
	public void setEstoqueMinimo(int estoqueMinimo) {
		this.estoqueMinimo = estoqueMinimo;
	}
	public Date getDataCad() {
		return dataCad;
	}
	public void setDataCad(Date dataCad) {
		this.dataCad = dataCad;
	}

	@Override
	public String toString() {
		return "Produto [codigo=" + codigo + ", nome=" + nome + ", precoUnitario=" + precoUnitario + ", estoque=" + estoque + ", estoqueMinimo=" + estoqueMinimo + ", dataCad=" + dataCad + "]";
	}

	/**
	 * @author Eduardo Augusto
	 * @return int
	 * @param Produto
	 * @exception
	 * 
	 * Método int de comparação de nome entre dois produtos:
	 * 0 para igual em conteúdo e tamanho
	 * 1 para maior ou igual em tamanho
	 * -1 para menor em tamanho
	 */
	@Override
	public int compareTo(Produto produto) {
		if (produto.getNome().equals(this.nome))
			return 0;
		else
			return produto.getNome().length() >= this.nome.length() ? 1 : -1;
	}
}