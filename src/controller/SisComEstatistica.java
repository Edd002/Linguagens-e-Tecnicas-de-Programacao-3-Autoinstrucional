package controller;

public class SisComEstatistica {
	private int codigoPessoa;
	private String nomePessoa;
	private int quantidadeOperacoes;
	private double valorTotalOperacoes;

	public SisComEstatistica() {
	}

	public SisComEstatistica(int codigoPessoa, String nomePessoa, int quantidadeOperacoes, double valorTotalOperacoes) {
		this.codigoPessoa = codigoPessoa;
		this.nomePessoa = nomePessoa;
		this.quantidadeOperacoes = quantidadeOperacoes;
		this.valorTotalOperacoes = valorTotalOperacoes;
	}

	public int getCodigoPessoa() {
		return codigoPessoa;
	}
	public void setCodigoPessoa(int codigoPessoa) {
		this.codigoPessoa = codigoPessoa;
	}
	public String getNomePessoa() {
		return nomePessoa;
	}
	public void setNomePessoa(String nomePessoa) {
		this.nomePessoa = nomePessoa;
	}
	public int getQuantidadeOperacoes() {
		return quantidadeOperacoes;
	}
	public void setQuantidadeOperacoes(int quantidadeOperacoes) {
		this.quantidadeOperacoes = quantidadeOperacoes;
	}
	public double getValorTotalOperacoes() {
		return valorTotalOperacoes;
	}
	public void setValorTotalOperacoes(double valorTotalOperacoes) {
		this.valorTotalOperacoes = valorTotalOperacoes;
	}

	@Override
	public String toString() {
		return "SisComEstatistica [codigoPessoa=" + codigoPessoa + ", nomePessoa=" + nomePessoa + ", quantidadeOperacoes=" + quantidadeOperacoes + ", valorTotalOperacoes=" + valorTotalOperacoes + "]";
	}
}