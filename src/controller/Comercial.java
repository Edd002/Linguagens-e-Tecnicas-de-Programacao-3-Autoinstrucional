package controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.hibernate.HibernateException;

import model.Cliente;
import model.Compra;
import model.Fornecedor;
import model.ItemCompra;
import model.ItemVenda;
import model.Pessoa;
import model.Produto;
import model.Venda;
import model.Vendedor;
import utility.LtpLib;

/**
 * @author Eduardo Augusto
 *
 * Classe InterfaceComercial.
 */
public class Comercial {
	/**
	 * @author Eduardo Augusto
	 * @return HashMap<String, Boolean>
	 * @param HashMap<String, String>
	 * @throws Exception 
	 * @throws HibernateException 
	 * @exception
	 * 
	 * Método para validar os campos de cliente.
	 * Parâmetro: HashMap de campos (Strings).
	 */
	public static HashMap<String, Boolean> validarCliente(HashMap<String, String> hashMapCamposCliente) {
		HashMap<String, Boolean> hashMapCamposClienteValidados = new HashMap<>();

		Set<String> keys = hashMapCamposCliente.keySet();
		for(String key: keys)
			hashMapCamposClienteValidados.put(key, true);

		if (hashMapCamposCliente.get("Nome").equals(""))
			hashMapCamposClienteValidados.put("Nome", false);

		if (hashMapCamposCliente.get("TelefoneFixo").equals("(__) ____-____"))
			hashMapCamposClienteValidados.put("TelefoneFixo", false);

		if (hashMapCamposCliente.get("TelefoneCelular").equals("(__) _____-____"))
			hashMapCamposClienteValidados.put("TelefoneCelular", false);

		if (validarEnderecoEmail(hashMapCamposCliente.get("Email")) == false)
			hashMapCamposClienteValidados.put("Email", false);

		if (LtpLib.validarCPF(hashMapCamposCliente.get("Cpf").replace(".", "").replace("-", "")) == false)
			hashMapCamposClienteValidados.put("Cpf", false);

		if (hashMapCamposCliente.get("LimiteCredito").equals("0,00"))
			hashMapCamposClienteValidados.put("LimiteCredito", false);

		if (hashMapCamposCliente.get("Login").equals("") || hashMapCamposCliente.get("Login").length() < 4)
			hashMapCamposClienteValidados.put("Login", false);

		if (hashMapCamposCliente.get("Senha").equals("") || hashMapCamposCliente.get("Senha").length() < 4)
			hashMapCamposClienteValidados.put("Senha", false);

		if (hashMapCamposCliente.get("ConfirmarSenha").equals(hashMapCamposCliente.get("Senha")) == false || hashMapCamposCliente.get("ConfirmarSenha").equals(""))
			hashMapCamposClienteValidados.put("ConfirmarSenha", false);

		return hashMapCamposClienteValidados;
	}

	/**
	 * @author Eduardo Augusto
	 * @return HashMap<String, Boolean>
	 * @param HashMap<String, String>
	 * @exception
	 * 
	 * Método para validar os campos de fornecedor.
	 * Parâmetro: HashMap de campos (Strings).
	 */
	public static HashMap<String, Boolean> validarFornecedor(HashMap<String, String> hashMapCamposFornecedor) {
		HashMap<String, Boolean> hashMapCamposFornecedorValidados = new HashMap<>();

		Set<String> keys = hashMapCamposFornecedor.keySet();
		for(String key: keys)
			hashMapCamposFornecedorValidados.put(key, true);

		if (hashMapCamposFornecedor.get("Nome").equals(""))
			hashMapCamposFornecedorValidados.put("Nome", false);

		if (hashMapCamposFornecedor.get("TelefoneFixo").equals("(__) ____-____"))
			hashMapCamposFornecedorValidados.put("TelefoneFixo", false);

		if (hashMapCamposFornecedor.get("TelefoneCelular").equals("(__) _____-____"))
			hashMapCamposFornecedorValidados.put("TelefoneCelular", false);

		if (validarEnderecoEmail(hashMapCamposFornecedor.get("Email")) == false)
			hashMapCamposFornecedorValidados.put("Email", false);

		if (LtpLib.validarCNPJ(hashMapCamposFornecedor.get("Cnpj").replace(".", "").replace("/", "").replace("-", "")) == false)
			hashMapCamposFornecedorValidados.put("Cnpj", false);

		if (hashMapCamposFornecedor.get("NomeContato").equals(""))
			hashMapCamposFornecedorValidados.put("NomeContato", false);

		if (hashMapCamposFornecedor.get("Login").equals("") || hashMapCamposFornecedor.get("Login").length() < 4)
			hashMapCamposFornecedorValidados.put("Login", false);

		if (hashMapCamposFornecedor.get("Senha").equals("") || hashMapCamposFornecedor.get("Senha").length() < 4)
			hashMapCamposFornecedorValidados.put("Senha", false);

		if (hashMapCamposFornecedor.get("ConfirmarSenha").equals(hashMapCamposFornecedor.get("Senha")) == false || hashMapCamposFornecedor.get("ConfirmarSenha").equals(""))
			hashMapCamposFornecedorValidados.put("ConfirmarSenha", false);

		return hashMapCamposFornecedorValidados;
	}

	/**
	 * @author Eduardo Augusto
	 * @return HashMap<String, Boolean>
	 * @param HashMap<String, String>
	 * @exception
	 * 
	 * Método para validar os campos de vendedor.
	 * Parâmetro: HashMap de campos (Strings).
	 * 
	 * Observação:
	 * - A meta mensal de vendas do vendedor tem que ser maior do que zero.
	 */
	public static HashMap<String, Boolean> validarVendedor(HashMap<String, String> hashMapCamposVendedor) {
		HashMap<String, Boolean> hashMapCamposVendedorValidados = new HashMap<>();

		Set<String> keys = hashMapCamposVendedor.keySet();
		for(String key: keys)
			hashMapCamposVendedorValidados.put(key, true);

		if (hashMapCamposVendedor.get("Nome").equals(""))
			hashMapCamposVendedorValidados.put("Nome", false);

		if (hashMapCamposVendedor.get("TelefoneFixo").equals("(__) ____-____"))
			hashMapCamposVendedorValidados.put("TelefoneFixo", false);

		if (hashMapCamposVendedor.get("TelefoneCelular").equals("(__) _____-____"))
			hashMapCamposVendedorValidados.put("TelefoneCelular", false);

		if (validarEnderecoEmail(hashMapCamposVendedor.get("Email")) == false)
			hashMapCamposVendedorValidados.put("Email", false);

		if (LtpLib.validarCPF(hashMapCamposVendedor.get("Cpf").replace(".", "").replace("-", "")) == false)
			hashMapCamposVendedorValidados.put("Cpf", false);

		if (hashMapCamposVendedor.get("MetaMensal").equals("0,00"))
			hashMapCamposVendedorValidados.put("MetaMensal", false);

		if (hashMapCamposVendedor.get("Login").equals("") || hashMapCamposVendedor.get("Login").length() < 4)
			hashMapCamposVendedorValidados.put("Login", false);

		if (hashMapCamposVendedor.get("Senha").equals("") || hashMapCamposVendedor.get("Senha").length() < 4)
			hashMapCamposVendedorValidados.put("Senha", false);

		if (hashMapCamposVendedor.get("ConfirmarSenha").equals(hashMapCamposVendedor.get("Senha")) == false || hashMapCamposVendedor.get("ConfirmarSenha").equals(""))
			hashMapCamposVendedorValidados.put("ConfirmarSenha", false);

		return hashMapCamposVendedorValidados;
	}

	/**
	 * @author Eduardo Augusto
	 * @return HashMap<String, Boolean>
	 * @param HashMap<String, String>
	 * @exception
	 * 
	 * Método para validar os campos de produto.
	 * Parâmetro: HashMap de campos (Strings).
	 */
	public static HashMap<String, Boolean> validarProduto(HashMap<String, String> hashMapCamposProduto) {
		HashMap<String, Boolean> hashMapCamposProdutoValidados = new HashMap<>();

		Set<String> keys = hashMapCamposProduto.keySet();
		for(String key: keys)
			hashMapCamposProdutoValidados.put(key, true);

		if (hashMapCamposProduto.get("Nome").equals(""))
			hashMapCamposProdutoValidados.put("Nome", false);

		if (hashMapCamposProduto.get("PrecoUnitario").equals("0,00"))
			hashMapCamposProdutoValidados.put("PrecoUnitario", false);

		if (hashMapCamposProduto.get("Estoque").equals(""))
			hashMapCamposProdutoValidados.put("Estoque", false);

		if (hashMapCamposProduto.get("EstoqueMinimo").equals(""))
			hashMapCamposProdutoValidados.put("EstoqueMinimo", false);

		return hashMapCamposProdutoValidados;
	}

	/**
	 * @author Eduardo Augusto
	 * @return
	 * @param String
	 * @exception
	 * 
	 * Método para verificar se um e-mail é válido.
	 */
	private static boolean validarEnderecoEmail(String email) {
		String padronizacaoEmail = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
		Pattern padraoRegex = Pattern.compile(padronizacaoEmail);
		Matcher combinacaoRegex = padraoRegex.matcher(email);
		return combinacaoRegex.matches();
	}

	/**
	 * @author Eduardo Augusto
	 * @return
	 * @param String, String
	 * @exception HibernateException, Exception
	 * 
	 * Método para autentificar login no sistema.
	 * Parâmetros: login e senha (Strings).
	 */
	public static Pessoa autentificarLogin(String login, String senha) throws HibernateException, Exception {
		Pessoa pessoa = (Pessoa) DAO.selectUniqueResult("from Pessoa_SC where login = '" + login + "' and senha = '" + senha + "'", Pessoa.class);
		return pessoa;
	}

	/**
	 * @author Eduardo Augusto
	 * @return
	 * @param String
	 * @exception HibernateException, Exception
	 * 
	 * Método para verificar se o CPF já está cadastrado.
	 * Parâmetros: cpf (String).
	 * 
	 * Observação:
	 * - Não poderá ser cadastrado cliente ou vendedor com cpf já cadastrado.
	 */
	public static boolean cpfRegistrado(String cpf) throws HibernateException, Exception {
		Cliente cliente = (Cliente) DAO.selectUniqueResult("from Cliente_SC where cpf = '" + cpf + "'", Cliente.class);
		Vendedor vendedor = (Vendedor) DAO.selectUniqueResult("from Vendedor_SC where cpf = '" + cpf + "'", Vendedor.class);

		if (cliente == null && vendedor == null)
			return false;
		else
			return true;
	}

	/**
	 * @author Eduardo Augusto
	 * @return
	 * @param String
	 * @exception HibernateException, Exception
	 * 
	 * Método para verificar se o CNPJ já está cadastrado.
	 * Parâmetros: cnpj (String).
	 * 
	 * Observação:
	 * - Não poderá ser cadastrado fornecedor com cnpj já cadastrado.
	 */
	public static boolean cnpjRegistrado(String cnpj) throws HibernateException, Exception {
		Fornecedor fornecedor = (Fornecedor) DAO.selectUniqueResult("from Fornecedor_SC where cnpj = '" + cnpj + "'", Fornecedor.class);

		if (fornecedor == null)
			return false;
		else
			return true;
	}

	/**
	 * @author Eduardo Augusto
	 * @return
	 * @param String
	 * @exception HibernateException, Exception
	 * 
	 * Método para verificar se o login já está cadastrado.
	 * Parâmetros: login (String).
	 * 
	 * Observação:
	 * - Não poderá ser cadastrado pessoa com login já cadastrado.
	 */
	public static boolean loginRegistrado(String login) throws HibernateException, Exception {
		Pessoa pessoa = (Pessoa) DAO.selectUniqueResult("from Pessoa_SC where login = '" + login + "'", Pessoa.class);

		if (pessoa == null)
			return false;
		else
			return true;
	}

	/**
	 * @author Eduardo Augusto
	 * @return
	 * @param HashMap<String, String>
	 * @exception HibernateException, Exception 
	 * 
	 * Método para inserir um novo cliente no banco de dados.
	 * Parâmetro: HashMap de campos (Strings).
	 */
	public static void inserirCliente(HashMap<String, String> hashMapCamposCliente) throws HibernateException, Exception {
		Cliente cliente = new Cliente();

		cliente.setNome(hashMapCamposCliente.get("Nome"));
		cliente.setTelefoneFixo(hashMapCamposCliente.get("TelefoneFixo"));
		cliente.setTelefoneCelular(hashMapCamposCliente.get("TelefoneCelular"));
		cliente.setEmail(hashMapCamposCliente.get("Email").toLowerCase());
		cliente.setCpf(hashMapCamposCliente.get("Cpf"));
		cliente.setLimiteCredito(Double.parseDouble(hashMapCamposCliente.get("LimiteCredito").replace(".", "").replace(",", ".")));
		cliente.setLogin(hashMapCamposCliente.get("Login"));
		cliente.setSenha(hashMapCamposCliente.get("Senha"));
		cliente.setDataCad(new Date());

		DAO.insert(cliente);
	}

	/**
	 * @author Eduardo Augusto
	 * @return
	 * @param HashMap<String, String>
	 * @exception HibernateException, Exception 
	 * 
	 * Método para inserir um novo fornecedor no banco de dados.
	 * Parâmetro: HashMap de campos (Strings).
	 */
	public static void inserirFornecedor(HashMap<String, String> hashMapCamposFornecedor) throws HibernateException, Exception {
		Fornecedor fornecedor = new Fornecedor();

		fornecedor.setNome(hashMapCamposFornecedor.get("Nome"));
		fornecedor.setTelefoneFixo(hashMapCamposFornecedor.get("TelefoneFixo"));
		fornecedor.setTelefoneCelular(hashMapCamposFornecedor.get("TelefoneCelular"));
		fornecedor.setEmail(hashMapCamposFornecedor.get("Email").toLowerCase());
		fornecedor.setCnpj(hashMapCamposFornecedor.get("Cnpj"));
		fornecedor.setNomeContato(hashMapCamposFornecedor.get("NomeContato"));
		fornecedor.setLogin(hashMapCamposFornecedor.get("Login"));
		fornecedor.setSenha(hashMapCamposFornecedor.get("Senha"));
		fornecedor.setDataCad(new Date());

		DAO.insert(fornecedor);
	}

	/**
	 * @author Eduardo Augusto
	 * @return
	 * @param HashMap<String, String>
	 * @exception HibernateException, Exception 
	 * 
	 * Método para inserir um novo vendedor no banco de dados.
	 * Parâmetro: HashMap de campos (Strings).
	 */
	public static void inserirVendedor(HashMap<String, String> hashMapCamposVendedor) throws HibernateException, Exception {
		Vendedor vendedor = new Vendedor();

		vendedor.setNome(hashMapCamposVendedor.get("Nome"));
		vendedor.setTelefoneFixo(hashMapCamposVendedor.get("TelefoneFixo"));
		vendedor.setTelefoneCelular(hashMapCamposVendedor.get("TelefoneCelular"));
		vendedor.setEmail(hashMapCamposVendedor.get("Email").toLowerCase());
		vendedor.setCpf(hashMapCamposVendedor.get("Cpf"));
		vendedor.setMetaMensal(Double.parseDouble(hashMapCamposVendedor.get("MetaMensal").replace(".", "").replace(",", ".")));
		vendedor.setLogin(hashMapCamposVendedor.get("Login"));
		vendedor.setSenha(hashMapCamposVendedor.get("Senha"));
		vendedor.setDataCad(new Date());

		DAO.insert(vendedor);
	}

	/**
	 * @author Eduardo Augusto
	 * @return
	 * @param HashMap<String, String>
	 * @exception HibernateException, Exception
	 * 
	 * Método para inserir um novo produto no banco de dados.
	 * Parâmetro: HashMap de campos (Strings).
	 */
	public static void inserirProduto(HashMap<String, String> hashMapCamposProduto) throws HibernateException, Exception {
		Produto produto = new Produto();

		produto.setNome(hashMapCamposProduto.get("Nome"));
		produto.setPrecoUnitario(Double.parseDouble(hashMapCamposProduto.get("PrecoUnitario").replace(".", "").replace(",", ".")));
		produto.setEstoque(Integer.parseInt(hashMapCamposProduto.get("Estoque")));
		produto.setEstoqueMinimo(Integer.parseInt(hashMapCamposProduto.get("EstoqueMinimo").toLowerCase()));
		produto.setDataCad(new Date());

		DAO.insert(produto);
	}

	/**
	 * @author Eduardo Augusto
	 * @return Cliente
	 * @param String
	 * @exception HibernateException, Exception
	 * 
	 * Método para consultar o cliente por cpf.
	 */
	public static Cliente consultarCliente(String cpf) throws HibernateException, Exception {
		Cliente cliente = (Cliente) DAO.selectUniqueResult("from Cliente_SC where cpf = '" + cpf + "'", Cliente.class);
		return cliente;
	}

	/**
	 * @author Eduardo Augusto
	 * @return ArrayList<Cliente>
	 * @param
	 * @exception HibernateException, Exception 
	 * 
	 * Método para imprimir lista de clientes em ordem alfabética por parte do nome do cliente.
	 */
	public static ArrayList<Cliente> consultarClientesOrdemAlfabetica() throws HibernateException, Exception {
		ArrayList<Cliente> arrayListCliente = DAO.select("from Cliente_SC", Cliente.class);

		Collections.sort(arrayListCliente, new Comparator<Cliente>() {
			public int compare(Cliente cliente1, Cliente cliente2) {
				return cliente1.getNome().compareTo(cliente2.getNome());
			}
		});

		return arrayListCliente;
	}

	/**
	 * @author Eduardo Augusto
	 * @return Fornecedor
	 * @param String
	 * @exception HibernateException, Exception 
	 * 
	 * Método para consultar fornecedor por cnpj.
	 */
	public static Fornecedor consultarFornecedor(String cnpj) throws HibernateException, Exception {
		Fornecedor fornecedor = (Fornecedor) DAO.selectUniqueResult("from Fornecedor_SC where cnpj = '" + cnpj + "'", Fornecedor.class);
		return fornecedor;
	}

	/**
	 * @author Eduardo Augusto
	 * @return ArrayList<Fornecedor>
	 * @param
	 * @exception HibernateException, Exception 
	 * 
	 * Método para imprimir lista de fornecedores em ordem alfabética por parte do nome do fornecedor.
	 */
	public static ArrayList<Fornecedor> consultarFornecedoresOrdemAlfabetica() throws HibernateException, Exception {
		ArrayList<Fornecedor> arrayListFornecedor = DAO.select("from Fornecedor_SC", Fornecedor.class);

		Collections.sort(arrayListFornecedor, new Comparator<Fornecedor>() {
			public int compare(Fornecedor fornecedor1, Fornecedor fornecedor2) {
				return fornecedor1.getNome().compareTo(fornecedor2.getNome());
			}
		});

		return arrayListFornecedor;
	}

	/**
	 * @author Eduardo Augusto
	 * @return Vendedor
	 * @param String
	 * @exception HibernateException, Exception
	 * 
	 * Método para consultar o vendedor por cpf.
	 */
	public static Vendedor consultarVendedor(String cpf) throws HibernateException, Exception {
		Vendedor vendedor = (Vendedor) DAO.selectUniqueResult("from Vendedor_SC where cpf = '" + cpf + "'", Vendedor.class);
		return vendedor;
	}

	/**
	 * @author Eduardo Augusto
	 * @return ArrayList<Vendedor>
	 * @param
	 * @exception HibernateException, Exception
	 * 
	 * Método para imprimir lista de vendedores por parte do nome em ordem alfabética.
	 */
	public static ArrayList<Vendedor> consultarVendedoresOrdemAlfabetica() throws HibernateException, Exception {
		ArrayList<Vendedor> arrayListVendedor = DAO.select("from Vendedor_SC", Vendedor.class);

		Collections.sort(arrayListVendedor, new Comparator<Vendedor>() {
			public int compare(Vendedor vendedor1, Vendedor vendedor2) {
				return vendedor1.getNome().compareTo(vendedor2.getNome());
			}
		});

		return arrayListVendedor;
	}

	/**
	 * @author Eduardo Augusto
	 * @return Produto
	 * @param int
	 * @exception HibernateException, Exception
	 * 
	 * Método para consultar produto por código.
	 */
	public static Produto consultarProduto(int codigo) throws HibernateException, Exception {
		Produto produto = (Produto) DAO.selectUniqueResult("from Produto_SC where codigo = '" + codigo + "'", Produto.class);
		return produto;
	}

	/**
	 * @author Eduardo Augusto
	 * @return ArrayList<Produto>
	 * @param
	 * @exception HibernateException, Exception
	 * 
	 * Método para imprimir lista de produtos em ordem alfabética por parte do nome do produto.
	 */
	public static ArrayList<Produto> consultarProdutosOrdemAlfabetica() throws HibernateException, Exception {
		ArrayList<Produto> arrayListProduto = DAO.select("from Produto_SC", Produto.class);

		Collections.sort(arrayListProduto, new Comparator<Produto>() {
			public int compare(Produto produto1, Produto produto2) {
				return produto1.getNome().compareTo(produto2.getNome());
			}
		});

		return arrayListProduto;
	}

	/**
	 * @author Eduardo Augusto
	 * @return ArrayList<Produto>
	 * @param
	 * @exception HibernateException, Exception
	 * 
	 * Método para imprimir lista de produtos com estoque abaixo do mínimo em ordem alfabética.
	 */
	public static ArrayList<Produto> consultarProdutosEstoqueAbaixoMinimoOrdemAlfabetica() throws HibernateException, Exception {
		ArrayList<Produto> arrayListProduto = DAO.select("from Produto_SC where estoque < estoque_minimo", Produto.class);

		Collections.sort(arrayListProduto, new Comparator<Produto>() {
			public int compare(Produto produto1, Produto produto2) {
				return produto1.getNome().compareTo(produto2.getNome());
			}
		});

		return arrayListProduto;
	}

	/**
	 * @author Eduardo Augusto
	 * @return ArrayList<Venda>
	 * @param Cliente
	 * @exception HibernateException, Exception
	 * 
	 * Método para buscar todas as vendas realizadas por um cliente.
	 * Parâmetro: cliente
	 */
	public static ArrayList<Venda> consultarVendasCliente(Cliente cliente) throws HibernateException, Exception {
		ArrayList<Venda> arrayListCliente = DAO.select("from Venda_SC where codigo_cliente = " + cliente.getCodigo(), Venda.class);
		return arrayListCliente;
	}

	/**
	 * @author Eduardo Augusto
	 * @return ArrayList<Venda>
	 * @param Date, Date, String
	 * @exception HibernateException, Exception
	 * 
	 * Método para imprimir lista de vendas por período em ordem de cliente e data da venda decrescente.
	 * Parâmetros: período das compras (datas de início e fim) e parte do nome do cliente.
	 */
	public static ArrayList<Venda> consultarVendasOrdemClienteDataDecrescente(Date periodoVendaDataInicio, Date periodoVendaDataFim, String parteNome) throws HibernateException, Exception {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dataInicio = simpleDateFormat.format(periodoVendaDataInicio);
		String dataFim = simpleDateFormat.format(periodoVendaDataFim);

		ArrayList<Venda> arrayListVenda = DAO.select("from Venda_SC where data_venda between '" + dataInicio + "' and '" + dataFim + "' order by data_venda desc", Venda.class);
		for (int i = 0; i < arrayListVenda.size(); i++) {
			if(arrayListVenda.get(i).getCliente().getNome().contains(parteNome) && !parteNome.equals("")) {
				Venda auxVenda = arrayListVenda.get(i);
				arrayListVenda.remove(i);
				arrayListVenda.add(0, auxVenda);
			}
		}

		return arrayListVenda;
	}

	/**
	 * @author Eduardo Augusto
	 * @return ArrayList<Venda>
	 * @param Date, Date, String
	 * @exception HibernateException, Exception
	 * 
	 * Método para imprimir lista de vendas por período em ordem de vendedor e data da venda decrescente.
	 * Parâmetros: período das vendas (datas de início e fim) e parte do nome do vendedor.
	 */
	public static ArrayList<Venda> consultarVendasOrdemVendedorDataDecrescente(Date periodoVendaDataInicio, Date periodoVendaDataFim, String parteNome) throws HibernateException, Exception {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dataInicio = simpleDateFormat.format(periodoVendaDataInicio);
		String dataFim = simpleDateFormat.format(periodoVendaDataFim);

		ArrayList<Venda> arrayListVenda = DAO.select("from Venda_SC where data_venda between '" + dataInicio + "' and '" + dataFim + "' order by data_venda desc", Venda.class);
		for (int i = 0; i < arrayListVenda.size(); i++) {
			if(arrayListVenda.get(i).getVendedor().getNome().contains(parteNome) && !parteNome.equals("")) {
				Venda auxVenda = arrayListVenda.get(i);
				arrayListVenda.remove(i);
				arrayListVenda.add(0, auxVenda);
			}
		}

		return arrayListVenda;
	}

	/**
	 * @author Eduardo Augusto
	 * @return ArrayList<Compra>
	 * @param Date, Date, String
	 * @exception HibernateException, Exception
	 * 
	 * Método para imprimir lista de compras por período em ordem de fornecedor e data da compra decrescente.
	 * Parâmetro: período das compras (datas de início e fim) e parte do nome do fornecedor.
	 */
	public static ArrayList<Compra> consultarComprasOrdemFornecedorDataDecrescente(Date periodoCompraDataInicio, Date periodoCompraDataFim, String parteNome) throws HibernateException, Exception {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dataInicio = simpleDateFormat.format(periodoCompraDataInicio);
		String dataFim = simpleDateFormat.format(periodoCompraDataFim);

		ArrayList<Compra> arrayListCompra = DAO.select("from Compra_SC where data_compra between '" + dataInicio + "' and '" + dataFim + "' order by data_compra desc", Compra.class);
		for (int i = 0; i < arrayListCompra.size(); i++) {
			if(arrayListCompra.get(i).getFornecedor().getNome().contains(parteNome) && !parteNome.equals("")) {
				Compra auxCompra = arrayListCompra.get(i);
				arrayListCompra.remove(i);
				arrayListCompra.add(0, auxCompra);
			}
		}

		return arrayListCompra;
	}

	/**
	 * @author Eduardo Augusto
	 * @param
	 * @param
	 * @return ArrayList<SisComEstatistica>
	 * @param
	 * @exception HibernateException, Exception
	 * 
	 * Método para buscar estatística de vendas por cliente (código, nome, quantas vendas registradas e valor total).
	 * Parâmetros: período das vendas (datas de início e fim).
	 * Retorno: lista SisComEstatistica.
	 */
	public static ArrayList<SisComEstatistica> consultarEstatisticaVendasClientePeriodo(Date periodoVendaDataInicio, Date periodoVendaDataFim) throws HibernateException, Exception {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dataInicio = simpleDateFormat.format(periodoVendaDataInicio);
		String dataFim = simpleDateFormat.format(periodoVendaDataFim);

		ArrayList<Venda> arrayListVenda = DAO.select("from Venda_SC where data_venda between '" + dataInicio + "' and '" + dataFim + "'", Venda.class);
		HashMap<Integer, SisComEstatistica> hashMapSisComEstatistica = new HashMap<>();

		for (Venda venda : arrayListVenda) {
			SisComEstatistica sisComEstatistica = new SisComEstatistica();

			sisComEstatistica.setCodigoPessoa(venda.getCliente().getCodigo());
			sisComEstatistica.setNomePessoa(venda.getCliente().getNome());

			if (hashMapSisComEstatistica.get(venda.getCliente().getCodigo()) == null) {
				sisComEstatistica.setQuantidadeOperacoes(1);
				for (ItemVenda itemVenda : venda.getVendaItens())
					sisComEstatistica.setValorTotalOperacoes(sisComEstatistica.getValorTotalOperacoes() + itemVenda.getValorVenda());
			} else {
				sisComEstatistica.setQuantidadeOperacoes(hashMapSisComEstatistica.get(venda.getCliente().getCodigo()).getQuantidadeOperacoes() + 1);
				for (ItemVenda itemVenda : venda.getVendaItens())
					sisComEstatistica.setValorTotalOperacoes(sisComEstatistica.getValorTotalOperacoes() + itemVenda.getValorVenda());

				sisComEstatistica.setValorTotalOperacoes(sisComEstatistica.getValorTotalOperacoes() + hashMapSisComEstatistica.get(venda.getCliente().getCodigo()).getValorTotalOperacoes());
			}

			hashMapSisComEstatistica.put(venda.getCliente().getCodigo(), sisComEstatistica);
		}

		ArrayList<SisComEstatistica> arrayListSisComEstatistica = new ArrayList<>();
		arrayListSisComEstatistica.addAll(hashMapSisComEstatistica.values());

		return arrayListSisComEstatistica;
	}

	/**
	 * @author Eduardo Augusto
	 * @return ArrayList<SisComEstatistica>
	 * @param
	 * @exception HibernateException, Exception
	 * 
	 * Método para buscar estatística de vendas por vendedor (código, nome, quantas vendas registradas e valor total).
	 * Parâmetros: período das vendas (datas de início e fim).
	 * retorno: lista SisComEstatistica.
	 */
	public static ArrayList<SisComEstatistica> consultarEstatisticaVendasVendedorPeriodo(Date periodoVendaDataInicio, Date periodoVendaDataFim) throws HibernateException, Exception {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dataInicio = simpleDateFormat.format(periodoVendaDataInicio);
		String dataFim = simpleDateFormat.format(periodoVendaDataFim);

		ArrayList<Venda> arrayListVenda = DAO.select("from Venda_SC where data_venda between '" + dataInicio + "' and '" + dataFim + "'", Venda.class);
		HashMap<Integer, SisComEstatistica> hashMapSisComEstatistica = new HashMap<>();

		for (Venda venda : arrayListVenda) {
			SisComEstatistica sisComEstatistica = new SisComEstatistica();

			sisComEstatistica.setCodigoPessoa(venda.getVendedor().getCodigo());
			sisComEstatistica.setNomePessoa(venda.getVendedor().getNome());

			if (hashMapSisComEstatistica.get(venda.getVendedor().getCodigo()) == null) {
				sisComEstatistica.setQuantidadeOperacoes(1);
				for (ItemVenda itemVenda : venda.getVendaItens())
					sisComEstatistica.setValorTotalOperacoes(sisComEstatistica.getValorTotalOperacoes() + itemVenda.getValorVenda());
			} else {
				sisComEstatistica.setQuantidadeOperacoes(hashMapSisComEstatistica.get(venda.getVendedor().getCodigo()).getQuantidadeOperacoes() + 1);
				for (ItemVenda itemVenda : venda.getVendaItens())
					sisComEstatistica.setValorTotalOperacoes(sisComEstatistica.getValorTotalOperacoes() + itemVenda.getValorVenda());

				sisComEstatistica.setValorTotalOperacoes(sisComEstatistica.getValorTotalOperacoes() + hashMapSisComEstatistica.get(venda.getVendedor().getCodigo()).getValorTotalOperacoes());
			}

			hashMapSisComEstatistica.put(venda.getVendedor().getCodigo(), sisComEstatistica);
		}

		ArrayList<SisComEstatistica> arrayListSisComEstatistica = new ArrayList<>();
		arrayListSisComEstatistica.addAll(hashMapSisComEstatistica.values());

		return arrayListSisComEstatistica;
	}

	/**
	 * @author Eduardo Augusto
	 * @return ArrayList<SisComEstatistica>
	 * @param
	 * @exception HibernateException, Exception
	 * 
	 * Método para buscar Estatística de compras por fornecedor (código, nome, quantas compras registradas e valor total).
	 * Parâmetro: período das compras (datas de início e fim).
	 * Retorno: lista SisComEstatistica.
	 */
	public static ArrayList<SisComEstatistica> consultarEstatisticaComprasFornecedorPeriodo(Date periodoCompraDataInicio, Date periodoCompraDataFim) throws HibernateException, Exception {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dataInicio = simpleDateFormat.format(periodoCompraDataInicio);
		String dataFim = simpleDateFormat.format(periodoCompraDataFim);

		ArrayList<Compra> arrayListCompra = DAO.select("from Compra_SC where data_compra between '" + dataInicio + "' and '" + dataFim + "'", Compra.class);
		HashMap<Integer, SisComEstatistica> hashMapSisComEstatistica = new HashMap<>();

		for (Compra compra : arrayListCompra) {
			SisComEstatistica sisComEstatistica = new SisComEstatistica();

			sisComEstatistica.setCodigoPessoa(compra.getFornecedor().getCodigo());
			sisComEstatistica.setNomePessoa(compra.getFornecedor().getNome());

			if (hashMapSisComEstatistica.get(compra.getFornecedor().getCodigo()) == null) {
				sisComEstatistica.setQuantidadeOperacoes(1);
				for (ItemCompra itemCompra : compra.getCompraItens())
					sisComEstatistica.setValorTotalOperacoes(sisComEstatistica.getValorTotalOperacoes() + itemCompra.getValorCompra());
			} else {
				sisComEstatistica.setQuantidadeOperacoes(hashMapSisComEstatistica.get(compra.getFornecedor().getCodigo()).getQuantidadeOperacoes() + 1);
				for (ItemCompra itemCompra : compra.getCompraItens())
					sisComEstatistica.setValorTotalOperacoes(sisComEstatistica.getValorTotalOperacoes() + itemCompra.getValorCompra());

				sisComEstatistica.setValorTotalOperacoes(sisComEstatistica.getValorTotalOperacoes() + hashMapSisComEstatistica.get(compra.getFornecedor().getCodigo()).getValorTotalOperacoes());
			}

			hashMapSisComEstatistica.put(compra.getFornecedor().getCodigo(), sisComEstatistica);
		}

		ArrayList<SisComEstatistica> arrayListSisComEstatistica = new ArrayList<>();
		arrayListSisComEstatistica.addAll(hashMapSisComEstatistica.values());

		return arrayListSisComEstatistica;
	}

	/**
	 * @author Eduardo Augusto
	 * @return
	 * @param ArrayList<Integer>
	 * @exception HibernateException, Exception
	 * 
	 * Método para excluir clientes do banco de dados.
	 * 
	 * Observações:
	 * - O cliente não pode ser excluído se tiver venda registrada para ele.
	 */
	public static void excluirCliente(ArrayList<Integer> arrayListCodigoCliente) throws SisComException, HibernateException, Exception {
		ArrayList<Venda> arrayListVendasCliente = new ArrayList<>();
		for (Integer codigoCliente : arrayListCodigoCliente)
			arrayListVendasCliente.addAll(DAO.select("from Venda_SC where codigo_cliente = " + codigoCliente, Venda.class));

		if (arrayListVendasCliente.isEmpty()) {
			for (Integer codigoCliente : arrayListCodigoCliente)
				DAO.update("delete from Cliente_SC where codigo = " + codigoCliente);
		} else {
			HashMap<Integer, Cliente> hashMapClientesVendasRegistrada = new HashMap<>();

			for (Venda vendaCliente : arrayListVendasCliente)
				hashMapClientesVendasRegistrada.put(vendaCliente.getCliente().getCodigo(), vendaCliente.getCliente());

			StringBuilder stringBuilder = new StringBuilder(hashMapClientesVendasRegistrada.size());
			stringBuilder.append("Clientes com vendas:\n");
			for(Integer codigoCliente : hashMapClientesVendasRegistrada.keySet())
				stringBuilder.append(codigoCliente + " - " + hashMapClientesVendasRegistrada.get(codigoCliente).getNome() + "\n");

			throw new SisComException(stringBuilder.toString());
		}
	}

	/**
	 * @author Eduardo Augusto
	 * @return
	 * @param ArrayList<Integer>
	 * @exception HibernateException, Exception
	 * 
	 * Método para excluir fornecedores do banco de dados.
	 * 
	 * Observações:
	 * - O fornecedor não pode ser excluído se tiver compra registrada para ele.
	 */
	public static void excluirFornecedor(ArrayList<Integer> arrayListCodigoFornecedor) throws SisComException, HibernateException, Exception {
		ArrayList<Compra> arrayListComprasFornecedor = new ArrayList<>();
		for (Integer codigoFornecedor : arrayListCodigoFornecedor)
			arrayListComprasFornecedor.addAll(DAO.select("from Compra_SC where codigo_fornecedor = " + codigoFornecedor, Compra.class));

		if (arrayListComprasFornecedor.isEmpty()) {
			for (Integer codigoFornecedor : arrayListCodigoFornecedor)
				DAO.update("delete from Fornecedor_SC where codigo = " + codigoFornecedor);
		} else {
			HashMap<Integer, Fornecedor> hashMapFornecedoresComprasRegistrada = new HashMap<>();

			for (Compra compraFornecedor : arrayListComprasFornecedor)
				hashMapFornecedoresComprasRegistrada.put(compraFornecedor.getFornecedor().getCodigo(), compraFornecedor.getFornecedor());

			StringBuilder stringBuilder = new StringBuilder(hashMapFornecedoresComprasRegistrada.size());
			stringBuilder.append("Fornecedores com compras:\n");
			for(Integer codigoForncedor : hashMapFornecedoresComprasRegistrada.keySet())
				stringBuilder.append(codigoForncedor + " - " + hashMapFornecedoresComprasRegistrada.get(codigoForncedor).getNome() + "\n");

			throw new SisComException(stringBuilder.toString());
		}
	}

	/**
	 * @author Eduardo Augusto
	 * @return
	 * @param ArrayList<Integer>
	 * @exception HibernateException, Exception
	 * 
	 * Método para excluir vendedores do banco de dados.
	 * 
	 * Observações:
	 * - O vendedor não pode ser excluído se tiver venda registrada para ele.
	 */
	public static void excluirVendedor(ArrayList<Integer> arrayListCodigoVendedor) throws SisComException, HibernateException, Exception {
		ArrayList<Venda> arrayListVendasVendedor = new ArrayList<>();
		for (Integer codigoVendedor : arrayListCodigoVendedor)
			arrayListVendasVendedor.addAll(DAO.select("from Venda_SC where codigo_vendedor = " + codigoVendedor, Venda.class));

		if (arrayListVendasVendedor.isEmpty()) {
			for (Integer codigoVendedor : arrayListCodigoVendedor)
				DAO.update("delete from Vendedor_SC where codigo = " + codigoVendedor);
		} else {
			HashMap<Integer, Vendedor> hashMapVendedorVendasRegistrada = new HashMap<>();

			for (Venda vendaVendedor : arrayListVendasVendedor)
				hashMapVendedorVendasRegistrada.put(vendaVendedor.getVendedor().getCodigo(), vendaVendedor.getVendedor());

			StringBuilder stringBuilder = new StringBuilder(hashMapVendedorVendasRegistrada.size());
			stringBuilder.append("Vendedor com vendas:\n");
			for(Integer codigoVendedor : hashMapVendedorVendasRegistrada.keySet())
				stringBuilder.append(codigoVendedor + " - " + hashMapVendedorVendasRegistrada.get(codigoVendedor).getNome() + "\n");

			throw new SisComException(stringBuilder.toString());
		}
	}

	/**
	 * @author Eduardo Augusto
	 * @return
	 * @param ArrayList<Integer>
	 * @exception HibernateException, Exception
	 * 
	 * Método para excluir produtos do banco de dados.
	 * 
	 * Observação:
	 * - O produto não pode ser excluído se tiver compra ou venda registrada para ele.
	 */
	public static void excluirProduto(ArrayList<Integer> arrayListCodigoProduto) throws SisComException, HibernateException, Exception {
		ArrayList<ItemVenda> arrayListItemVendasProduto = new ArrayList<>();
		ArrayList<ItemCompra> arrayListItemComprasProduto = new ArrayList<>();

		for (Integer codigoProduto : arrayListCodigoProduto) {
			arrayListItemVendasProduto.addAll(DAO.select("from ItemVenda_SC where codigo_produto = " + codigoProduto, ItemVenda.class));
			arrayListItemComprasProduto.addAll(DAO.select("from ItemCompra_SC where codigo_produto = " + codigoProduto, ItemCompra.class));
		}

		if (arrayListItemVendasProduto.isEmpty() && arrayListItemComprasProduto.isEmpty()) {
			for (Integer codigoProduto : arrayListCodigoProduto)
				DAO.update("delete from Produto_SC where codigo = " + codigoProduto);
		} else {
			HashMap<Integer, Produto> hashMapProdutoVendasComprasRegistrada = new HashMap<>();

			for (ItemVenda itemVendaProduto : arrayListItemVendasProduto)
				hashMapProdutoVendasComprasRegistrada.put(itemVendaProduto.getProduto().getCodigo(), itemVendaProduto.getProduto());

			for (ItemCompra itemCompraProduto : arrayListItemComprasProduto)
				hashMapProdutoVendasComprasRegistrada.put(itemCompraProduto.getProduto().getCodigo(), itemCompraProduto.getProduto());

			StringBuilder stringBuilder = new StringBuilder(hashMapProdutoVendasComprasRegistrada.size());
			stringBuilder.append("Produtos com vendas e/ou compras:\n");
			for(Integer codigoProduto : hashMapProdutoVendasComprasRegistrada.keySet())
				stringBuilder.append(codigoProduto + " - " + hashMapProdutoVendasComprasRegistrada.get(codigoProduto).getNome() + "\n");

			throw new SisComException(stringBuilder.toString());
		}
	}

	/**
	 * @author Eduardo Augusto
	 * @return
	 * @param ArrayList<Integer>
	 * @exception HibernateException, Exception
	 * 
	 * Método para excluir compras.
	 * 
	 * Observação:
	 * - Decrementar o estoque para os produtos comprados.
	 */
	public static void excluirCompra(ArrayList<Integer> arrayListNumeroCompra) throws HibernateException, Exception {
		for (Integer numeroCompra : arrayListNumeroCompra) {
			Compra compra = (Compra) DAO.selectUniqueResult("from Compra_SC where num_compra = " + numeroCompra, Compra.class);

			for (ItemCompra compraItens : compra.getCompraItens()) {
				int estoqueNovo = compraItens.getProduto().getEstoque() - compraItens.getQuantCompra();

				if (estoqueNovo < 0)
					DAO.update("update Produto_SC set estoque = 0 where codigo = " + compraItens.getProduto().getCodigo());
				else
					DAO.update("update Produto_SC set estoque = " + estoqueNovo + " where codigo = " + compraItens.getProduto().getCodigo());
			}
		}

		for (Integer numeroCompra : arrayListNumeroCompra) {
			DAO.update("delete from Compra_SC where num_compra = " + numeroCompra);
			DAO.update("delete from ItemCompra_SC where num_compra = " + numeroCompra);
		}
	}

	/**
	 * @author Eduardo Augusto
	 * @return
	 * @param ArrayList<Integer>
	 * @exception HibernateException, Exception
	 * 
	 * Método para excluir vendas.
	 * 
	 * Observação:
	 * - Incrementar o estoque para os produtos vendidos.
	 */
	public static void excluirVenda(ArrayList<Integer> arrayListNumeroVenda) throws HibernateException, Exception {
		for (Integer numeroVenda : arrayListNumeroVenda) {
			Venda venda = (Venda) DAO.selectUniqueResult("from Venda_SC where num_venda = " + numeroVenda, Venda.class);

			for (ItemVenda vendaItens : venda.getVendaItens()) {
				int estoqueNovo = vendaItens.getProduto().getEstoque() + vendaItens.getQuantVenda();
				DAO.update("update Produto_SC set estoque = " + estoqueNovo + " where codigo = " + vendaItens.getProduto().getCodigo());
			}
		}

		for (Integer numeroVenda : arrayListNumeroVenda) {
			DAO.update("delete from Venda_SC where num_venda = " + numeroVenda);
			DAO.update("delete from ItemVenda_SC where num_venda = " + numeroVenda);
		}
	}

	/**
	 * @author Eduardo Augusto
	 * @return
	 * @param Fornecedor, List<ItemCompra>
	 * @exception HibernateException, Exception
	 * 
	 * Método para fazer/cadastrar uma compra para um fornecedor e atualizar o estoque.

	 * Observação:
	 * - A lista de ItemCompra não pode possuir produtos repetidos;
	 * - Incrementar o estoque para os produtos comprados.
	 */
	public static void realizarCompraFornecedor(Fornecedor fornecedor, List<ItemCompra> compraItens) throws HibernateException, Exception {
		Compra compra = new Compra();

		compra.setFornecedor(fornecedor);
		compra.setCompraItens(compraItens);
		compra.setDataCompra(new Date());

		for (ItemCompra itemCompra : compraItens)
			DAO.update("update Produto_SC set estoque = " + (itemCompra.getQuantCompra() + itemCompra.getProduto().getEstoque()) + " where codigo = " + itemCompra.getProduto().getCodigo());

		DAO.insert(compra);
	}

	/**
	 * @author Eduardo Augusto
	 * @return
	 * @param Venda, Cliente
	 * @exception HibernateException, Exception
	 * 
	 * Método para fazer uma venda para um cliente.
	 * 
	 * Observação:
	 * - A lista de ItemVenda não pode possuir produtos repetidos;
	 * - Decrementar o estoque para os produtos vendidos;
	 * - A venda não pode ser realizada se a forma de pagamento for igual a 2
	 * (venda a prazo através de cheque pré-datado) e o total da venda for superior ao limite de crédito que o cliente possui.
	 */
	public static void realizarVendaCliente(Cliente cliente, Vendedor vendedor, List<ItemVenda> vendaItens, int formaPagamento, double valorTotalVenda) throws SisComException, HibernateException, Exception {
		if (formaPagamento == 2 && cliente.getLimiteCredito() < valorTotalVenda) {
			throw new SisComException("O limite total do valor da compra não pode ultrapassar o limite de crédito do cliente na forma de pagamento a prazo (cheque pré-datado).");
		} else {
			Venda venda = new Venda();

			venda.setCliente(cliente);
			venda.setVendedor(vendedor);
			venda.setVendaItens(vendaItens);
			venda.setFormaPagamento(formaPagamento);
			venda.setDataVenda(new Date());

			for (ItemVenda itemVenda : vendaItens)
				DAO.update("update Produto_SC set estoque = " + itemVenda.getProduto().getEstoque() + " where codigo = " + itemVenda.getProduto().getCodigo());

			DAO.insert(venda);
		}
	}
}