package model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author Eduardo Augusto
 *
 * Classe Compra.
 */
@Entity(name="Compra_SC")
@Table(name="Compra_SC")
public class Compra {
	@Id
	@Column(name="num_compra")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int numCompra;

	@ManyToOne(optional=false)
	@JoinColumn(name="codigo_fornecedor")
	private Fornecedor fornecedor;

	@OneToMany(targetEntity=ItemCompra.class, fetch=FetchType.EAGER, cascade=CascadeType.ALL, orphanRemoval=true)
	@JoinColumn(name="num_compra")
	private List<ItemCompra> compraItens;

	@Column(name="data_compra", nullable=false)
	private Date dataCompra;

	public Compra() {
	}

	public Compra(int numCompra, Fornecedor fornedor, List<ItemCompra> compraItens, Date dataCompra) {
		this.numCompra = numCompra;
		this.fornecedor = fornedor;
		this.compraItens = compraItens;
		this.dataCompra = dataCompra;
	}

	public int getNumCompra() {
		return numCompra;
	}
	public void setNumCompra(int numCompra) {
		this.numCompra = numCompra;
	}
	public Fornecedor getFornecedor() {
		return fornecedor;
	}
	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}
	public List<ItemCompra> getCompraItens() {
		return compraItens;
	}
	public void setCompraItens(List<ItemCompra> compraItens) {
		this.compraItens = compraItens;
	}
	public Date getDataCompra() {
		return dataCompra;
	}
	public void setDataCompra(Date dataCompra) {
		this.dataCompra = dataCompra;
	}

	@Override
	public String toString() {
		return "Compra [numCompra=" + numCompra + ", fornedor=" + fornecedor + ", compraItens=" + compraItens + ", dataCompra=" + dataCompra + "]";
	}
}