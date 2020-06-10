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
 * Classe ItemVenda.
 */
@Entity(name="ItemVenda_SC")
@Table(name="ItemVenda_SC")
public class ItemVenda {
	@Id
	@Column(name="codigo")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int codigo;

	@ManyToOne(optional=false)
	@JoinColumn(name="codigo_produto")
	private Produto produto;

	@Column(name="quant_venda", nullable=false)
	private int quantVenda;

	@Column(name="valor_venda", nullable=false)
	private double valorVenda;

	public ItemVenda() {
	}

	public ItemVenda(int codigo, Produto produto, int quantVenda, double valorVenda) {
		this.codigo = codigo;
		this.produto = produto;
		this.quantVenda = quantVenda;
		this.valorVenda = valorVenda;
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
	public int getQuantVenda() {
		return quantVenda;
	}
	public void setQuantVenda(int quantVenda) {
		this.quantVenda = quantVenda;
	}
	public double getValorVenda() {
		return valorVenda;
	}
	public void setValorVenda(double valorVenda) {
		this.valorVenda = valorVenda;
	}

	@Override
	public String toString() {
		return "ItemVenda [codigo=" + codigo + ", produto=" + produto + ", quantVenda=" + quantVenda + ", valorVenda=" + valorVenda + "]";
	}
}