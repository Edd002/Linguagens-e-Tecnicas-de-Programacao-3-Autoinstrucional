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
 * Classe Venda.
 */
@Entity(name="Venda_SC")
@Table(name="Venda_SC")
public class Venda {
	@Id
	@Column(name="num_venda")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int numVenda;

	@ManyToOne(optional=false)
	@JoinColumn(name="codigo_cliente")
	private Cliente cliente;

	@ManyToOne(optional=false)
	@JoinColumn(name="codigo_vendedor")
	private Vendedor vendedor;

	@OneToMany(targetEntity=ItemVenda.class, fetch=FetchType.EAGER, cascade=CascadeType.ALL, orphanRemoval=true)
	@JoinColumn(name="num_venda")
	private List<ItemVenda> vendaItens;

	@Column(name="forma_pagamento", nullable=false)
	private int formaPagamento;

	@Column(name="data_venda", nullable=false)
	private Date dataVenda;

	public Venda() {
	}

	public Venda(int numVenda, Cliente cliente, Vendedor vendedor, List<ItemVenda> vendaItens, int formaPagamento, Date dataVenda) {
		this.numVenda = numVenda;
		this.cliente = cliente;
		this.vendedor = vendedor;
		this.vendaItens = vendaItens;
		this.formaPagamento = formaPagamento;
		this.dataVenda = dataVenda;
	}

	public int getNumVenda() {
		return numVenda;
	}
	public void setNumVenda(int numVenda) {
		this.numVenda = numVenda;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Vendedor getVendedor() {
		return vendedor;
	}
	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}
	public List<ItemVenda> getVendaItens() {
		return vendaItens;
	}
	public void setVendaItens(List<ItemVenda> vendaItens) {
		this.vendaItens = vendaItens;
	}
	public int getFormaPagamento() {
		return formaPagamento;
	}
	public void setFormaPagamento(int formaPagamento) {
		this.formaPagamento = formaPagamento;
	}
	public Date getDataVenda() {
		return dataVenda;
	}
	public void setDataVenda(Date dataVenda) {
		this.dataVenda = dataVenda;
	}

	@Override
	public String toString() {
		return "Venda [numVenda=" + numVenda + ", cliente=" + cliente + ", vendedor=" + vendedor + ", vendaItens=" + vendaItens + ", formaPagamento=" + formaPagamento + ", dataVenda=" + dataVenda + "]";
	}
}