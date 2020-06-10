package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Eduardo Augusto
 *
 * Classe ItemCompra.
 */
@Entity(name="ItemCompra_SC")
@Table(name="ItemCompra_SC")
public class ItemCompra {
	@Id
	@Column(name="codigo")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int codigo;

	@ManyToOne(optional=false)
	@JoinColumn(name="codigo_produto")
	private Produto produto;

	@Column(name="quant_compra", nullable=false)
	private int quantCompra;

	@Column(name="valor_compra", nullable=false)
	private double valorCompra;

	public ItemCompra() {
	}

	public ItemCompra(int codigo, Produto produto, int quantCompra, double valorCompra) {
		this.codigo = codigo;
		this.produto = produto;
		this.quantCompra = quantCompra;
		this.valorCompra = valorCompra;
	}

	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public int getQuantCompra() {
		return quantCompra;
	}
	public void setQuantCompra(int quantCompra) {
		this.quantCompra = quantCompra;
	}
	public double getValorCompra() {
		return valorCompra;
	}
	public void setValorCompra(double valorCompra) {
		this.valorCompra = valorCompra;
	}

	@Override
	public String toString() {
		return "ItemCompra [codigo=" + codigo + ", produto=" + produto + ", quantCompra=" + quantCompra + ", valorCompra=" + valorCompra + "]";
	}
}