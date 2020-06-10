package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.ButtonGroup;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import org.hibernate.HibernateException;

import controller.Comercial;
import controller.SisComException;
import model.Cliente;
import model.ItemVenda;
import model.Produto;
import model.Vendedor;
import utility.CustomCellRenderer;
import utility.JActualDateTime;

import java.awt.Toolkit;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.KeyboardFocusManager;

import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.Cursor;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class JVendedor extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPaneVendedor;
	private JTable tableListaVendas;
	private JTable tableProdutosVenda;
	private JTable tableListaClientes;
	ButtonGroup buttonGroupTabelaClientes = new ButtonGroup();

	private JComboBox<String> comboBoxFormaPagamento;
	private JFormattedTextField formattedTextFieldValorTotalVenda;

	private HashMap<Integer, Produto> hashMapProdutos;
	private List<ItemVenda> vendaItens;
	private ArrayList<Cliente> arrayListCliente;

	/**
	 * Launch the application.
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JVendedor frame = new JVendedor();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	 */

	/**
	 * Create the frame.
	 */
	public JVendedor(Vendedor vendedor) {
		setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		setIconImage(Toolkit.getDefaultToolkit().getImage(JVendedor.class.getResource("/img/logo.png")));
		setResizable(false);
		setTitle("SYSTEM COMMERCE - \u00C1rea do Vendedor - " + vendedor.getNome());
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 1400, 750);
		contentPaneVendedor = new JPanel();
		contentPaneVendedor.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPaneVendedor.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPaneVendedor);

		JPanel panelNorth = new JPanel();
		contentPaneVendedor.add(panelNorth, BorderLayout.NORTH);

		JLabel lblSystemCommerce = new JLabel("System Commerce");
		lblSystemCommerce.setIcon(new ImageIcon(JVendedor.class.getResource("/img/logo.png")));
		lblSystemCommerce.setFont(new Font("BankGothic Lt BT", Font.BOLD, 24));
		panelNorth.add(lblSystemCommerce);

		JPanel panelCenter = new JPanel();
		contentPaneVendedor.add(panelCenter, BorderLayout.CENTER);
		panelCenter.setLayout(null);

		JLabel lblListaVendas = new JLabel("Lista de Vendas");
		lblListaVendas.setFont(new Font("BankGothic Lt BT", Font.BOLD, 20));
		lblListaVendas.setBounds(165, 11, 215, 20);
		panelCenter.add(lblListaVendas);

		JScrollPane scrollPaneListaVendas = new JScrollPane();
		scrollPaneListaVendas.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		scrollPaneListaVendas.setBounds(0, 42, 597, 220);
		panelCenter.add(scrollPaneListaVendas);

		tableListaVendas = new JTable() {
			private static final long serialVersionUID = 1L;
			@Override
			public Class<?> getColumnClass(int column) {
				switch (column) {
				case 0:
					return Boolean.class;
				case 1:
					return Object.class;
				case 2:
					return Object.class;
				case 3:
					return Object.class;
				case 4:
					return Object.class;
				default:
					return Object.class;
				}
			}
		};
		tableListaVendas.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Selecione", "C\u00F3digo", "Nome", "Quantidade", "Valor"
				}
				) {
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
					true, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tableListaVendas.getColumnModel().getColumn(0).setResizable(false);
		tableListaVendas.getColumnModel().getColumn(0).setPreferredWidth(100);
		tableListaVendas.getColumnModel().getColumn(1).setResizable(false);
		tableListaVendas.getColumnModel().getColumn(2).setResizable(false);
		tableListaVendas.getColumnModel().getColumn(2).setPreferredWidth(150);
		tableListaVendas.getColumnModel().getColumn(2).setMinWidth(150);
		tableListaVendas.getColumnModel().getColumn(3).setResizable(false);
		tableListaVendas.getColumnModel().getColumn(3).setPreferredWidth(150);
		tableListaVendas.getColumnModel().getColumn(4).setResizable(false);
		tableListaVendas.getColumnModel().getColumn(4).setPreferredWidth(150);
		tableListaVendas.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tableListaVendas.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		scrollPaneListaVendas.setViewportView(tableListaVendas);
		tableListaVendas.getTableHeader().setReorderingAllowed(false);

		// Alinhamento horizontal e fonte: cabeçalho da tabela de lista de vendas
		((JLabel) tableListaVendas.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
		tableListaVendas.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));

		// Cor intercalada nas linhas: tabela de lista de vendas
		gerenciarComportamentoTabela(tableListaVendas);

		// Consertar navegação tabela/frame: tabela de lista de vendas
		tableListaVendas.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, null);
		tableListaVendas.setFocusTraversalKeys(KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS, null);
		tableListaVendas.setFocusCycleRoot(false);

		JLabel lblProdutosVenda = new JLabel("Produtos para Venda");
		lblProdutosVenda.setFont(new Font("BankGothic Lt BT", Font.BOLD, 20));
		lblProdutosVenda.setBounds(946, 11, 275, 20);
		panelCenter.add(lblProdutosVenda);

		JScrollPane scrollPaneProdutosVenda = new JScrollPane();
		scrollPaneProdutosVenda.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		scrollPaneProdutosVenda.setBounds(787, 42, 597, 220);
		panelCenter.add(scrollPaneProdutosVenda);

		tableProdutosVenda = new JTable() {
			private static final long serialVersionUID = 1L;
			@Override
			public Class<?> getColumnClass(int column) {
				switch (column) {
				case 0:
					return Boolean.class;
				case 1:
					return Object.class;
				case 2:
					return Object.class;
				case 3:
					return Object.class;
				case 4:
					return Object.class;
				default:
					return Object.class;
				}
			}
		};
		tableProdutosVenda.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tableProdutosVenda.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Selecione", "Codigo", "Nome", "Pre\u00E7o Unit\u00E1rio", "Estoque"
				}
				) {
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
					true, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tableProdutosVenda.getColumnModel().getColumn(0).setResizable(false);
		tableProdutosVenda.getColumnModel().getColumn(0).setPreferredWidth(100);
		tableProdutosVenda.getColumnModel().getColumn(1).setResizable(false);
		tableProdutosVenda.getColumnModel().getColumn(2).setResizable(false);
		tableProdutosVenda.getColumnModel().getColumn(2).setPreferredWidth(150);
		tableProdutosVenda.getColumnModel().getColumn(3).setResizable(false);
		tableProdutosVenda.getColumnModel().getColumn(3).setPreferredWidth(150);
		tableProdutosVenda.getColumnModel().getColumn(4).setResizable(false);
		tableProdutosVenda.getColumnModel().getColumn(4).setPreferredWidth(150);
		tableProdutosVenda.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		scrollPaneProdutosVenda.setViewportView(tableProdutosVenda);
		tableProdutosVenda.getTableHeader().setReorderingAllowed(false);

		// Alinhamento horizontal e fonte: cabeçalho da tabela de produtos disponíveis
		((JLabel) tableProdutosVenda.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
		tableProdutosVenda.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));

		// Cor intercalada nas linhas: tabela de produtos disponíveis
		gerenciarComportamentoTabela(tableProdutosVenda);

		// Consertar navegação tabela/frame: tabela de produtos disponíveis
		tableProdutosVenda.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, null);
		tableProdutosVenda.setFocusTraversalKeys(KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS, null);
		tableProdutosVenda.setFocusCycleRoot(false);

		JLabel lblQuantidade = new JLabel("Quantidade:");
		lblQuantidade.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		lblQuantidade.setBounds(607, 189, 171, 20);
		panelCenter.add(lblQuantidade);

		JSpinner spinnerQuantidade = new JSpinner();
		spinnerQuantidade.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		spinnerQuantidade.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		spinnerQuantidade.setBounds(607, 225, 171, 26);
		permitirApenasNumerosPositivos(((JSpinner.NumberEditor) spinnerQuantidade.getEditor()).getTextField());
		panelCenter.add(spinnerQuantidade);

		// Mapeamento de produtos por código e gerar lista de vendas
		hashMapProdutos = mapearProdutosVenda();
		vendaItens = new ArrayList<>();

		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Percorrer tabela de produtos
				boolean verificarSelecao = false;
				ArrayList<Produto> produtosEstoqueInsuficiente = new ArrayList<>();

				for(int i = 0; i < tableProdutosVenda.getRowCount(); i++) {
					Boolean checked = Boolean.valueOf(tableProdutosVenda.getValueAt(i, 0).toString());
					Integer checkedCodigo = Integer.parseInt(tableProdutosVenda.getValueAt(i, 1).toString());

					Produto produtoSelecionado = hashMapProdutos.get(checkedCodigo);
					int quantidadeProdutoSelecionada = Integer.parseInt(spinnerQuantidade.getValue().toString());
					double precoUnitarioProdutoSelecionado = hashMapProdutos.get(checkedCodigo).getPrecoUnitario();
					int quantidadeEstoque = hashMapProdutos.get(checkedCodigo).getEstoque();

					if(checked) {
						verificarSelecao = true;

						boolean listaPossuiProduto = false;
						for (ItemVenda itemVenda : vendaItens) {
							if (itemVenda.getProduto().equals(produtoSelecionado)) {
								if (quantidadeEstoque < quantidadeProdutoSelecionada) {
									produtosEstoqueInsuficiente.add(produtoSelecionado);
								} else {
									itemVenda.setQuantVenda(itemVenda.getQuantVenda() + quantidadeProdutoSelecionada);
									itemVenda.setValorVenda(itemVenda.getValorVenda() + (quantidadeProdutoSelecionada * precoUnitarioProdutoSelecionado));
									atualizarEstoqueProdutos(produtoSelecionado, tableProdutosVenda, i, (quantidadeEstoque - quantidadeProdutoSelecionada));
								}
								listaPossuiProduto = true;
								break;
							}
						}

						if (listaPossuiProduto == false) {
							if (quantidadeEstoque < quantidadeProdutoSelecionada) {
								produtosEstoqueInsuficiente.add(produtoSelecionado);
							} else {
								ItemVenda itemVenda = new ItemVenda();

								itemVenda.setProduto(produtoSelecionado);
								itemVenda.setQuantVenda(quantidadeProdutoSelecionada);
								itemVenda.setValorVenda(quantidadeProdutoSelecionada * precoUnitarioProdutoSelecionado);
								atualizarEstoqueProdutos(produtoSelecionado, tableProdutosVenda, i, (quantidadeEstoque - quantidadeProdutoSelecionada));

								vendaItens.add(itemVenda);
							}
						}
					}
				}

				if (verificarSelecao == false) {
					JOptionPane.showMessageDialog(null, "Não há nenhum produto selecionado para adicionar à lista de vendas.", "FALHA AO ADICIONAR", JOptionPane.WARNING_MESSAGE);
				} else {
					preecherTabelaVendas();
					atualizarValorTotalVenda();
				}

				if (produtosEstoqueInsuficiente.isEmpty() == false) {
					StringBuilder stringBuilder = new StringBuilder(produtosEstoqueInsuficiente.size());
					for(Produto produtoEstoqueInsuficiente : produtosEstoqueInsuficiente)
						stringBuilder.append(produtoEstoqueInsuficiente.getCodigo() + " - " + produtoEstoqueInsuficiente.getNome() + "\n");

					JOptionPane.showMessageDialog(null, stringBuilder.toString(), "ESTOQUE INSUFICIENTE", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnAdicionar.setIcon(new ImageIcon(new ImageIcon(JVendedor.class.getResource("/img/seta-esquerda.png")).getImage().getScaledInstance(50, 40, java.awt.Image.SCALE_SMOOTH)));
		btnAdicionar.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		btnAdicionar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAdicionar.setBounds(607, 69, 171, 49);
		panelCenter.add(btnAdicionar);

		JButton btnRemover = new JButton("Remover");
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Percorrer tabela de lista de vendas
				boolean verificarSelecao = false;

				for(int i = 0; i < tableListaVendas.getRowCount(); i++) {
					Boolean checked = Boolean.valueOf(tableListaVendas.getValueAt(i, 0).toString());
					Integer checkedCodigo = Integer.parseInt(tableListaVendas.getValueAt(i, 1).toString());
					int quantidadeProdutoSelecionada = Integer.parseInt(spinnerQuantidade.getValue().toString());

					if(checked) {
						verificarSelecao = true;

						Iterator<ItemVenda> iterator = vendaItens.iterator();
						while (iterator.hasNext()) {
							ItemVenda itemVenda = iterator.next();
							if (itemVenda.getProduto().getCodigo() == checkedCodigo) {
								if (itemVenda.getQuantVenda() <= quantidadeProdutoSelecionada) {
									atualizarEstoqueProdutos(itemVenda.getProduto(), tableProdutosVenda, buscarLinhaTabelaPorCodigo(tableProdutosVenda, itemVenda.getProduto().getCodigo()), hashMapProdutos.get(itemVenda.getProduto().getCodigo()).getEstoque() + itemVenda.getQuantVenda());
									iterator.remove();
								} else {
									atualizarEstoqueProdutos(itemVenda.getProduto(), tableProdutosVenda, buscarLinhaTabelaPorCodigo(tableProdutosVenda, itemVenda.getProduto().getCodigo()), (hashMapProdutos.get(itemVenda.getProduto().getCodigo()).getEstoque()) + quantidadeProdutoSelecionada);
									itemVenda.setQuantVenda(itemVenda.getQuantVenda() - quantidadeProdutoSelecionada);
									itemVenda.setValorVenda(itemVenda.getProduto().getPrecoUnitario() * itemVenda.getQuantVenda());
								}
							}
						}
					}
				}

				if (verificarSelecao == false) {
					JOptionPane.showMessageDialog(null, "Não há nenhum produto selecionado para remover da lista de vendas.", "FALHA AO REMOVER", JOptionPane.WARNING_MESSAGE);
				} else {
					preecherTabelaVendas();
					atualizarValorTotalVenda();
				}
			}
		});
		btnRemover.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRemover.setIcon(new ImageIcon(new ImageIcon(JVendedor.class.getResource("/img/seta-direita.png")).getImage().getScaledInstance(50, 40, java.awt.Image.SCALE_SMOOTH)));
		btnRemover.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		btnRemover.setBounds(607, 129, 171, 49);
		panelCenter.add(btnRemover);

		JLabel lblListaClientes = new JLabel("Lista de Clientes");
		lblListaClientes.setFont(new Font("BankGothic Lt BT", Font.BOLD, 20));
		lblListaClientes.setBounds(165, 286, 215, 20);
		panelCenter.add(lblListaClientes);

		JScrollPane scrollPaneListaClientes = new JScrollPane();
		scrollPaneListaClientes.setBounds(0, 317, 597, 148);
		panelCenter.add(scrollPaneListaClientes);

		tableListaClientes = new JTable() {
			private static final long serialVersionUID = 1L;
			public Class<?> getColumnClass(int column) {
				switch (column) {
				case 0:
					return Boolean.class;
				case 1:
					return Object.class;
				case 2:
					return Object.class;
				case 3:
					return Object.class;
				case 4:
					return Object.class;
				default:
					return Object.class;
				}
			}
			public void tableChanged(TableModelEvent e) {
				super.tableChanged(e);
				repaint();
			}
		};
		tableListaClientes.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tableListaClientes.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Selecione", "C\u00F3digo", "Nome", "CPF", "Limite de Cr\u00E9dito"
				}
				) {
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
					true, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tableListaClientes.getColumnModel().getColumn(0).setResizable(false);
		tableListaClientes.getColumnModel().getColumn(0).setPreferredWidth(100);
		tableListaClientes.getColumnModel().getColumn(1).setResizable(false);
		tableListaClientes.getColumnModel().getColumn(2).setResizable(false);
		tableListaClientes.getColumnModel().getColumn(2).setPreferredWidth(150);
		tableListaClientes.getColumnModel().getColumn(3).setResizable(false);
		tableListaClientes.getColumnModel().getColumn(3).setPreferredWidth(170);
		tableListaClientes.getColumnModel().getColumn(4).setResizable(false);
		tableListaClientes.getColumnModel().getColumn(4).setPreferredWidth(150);
		tableListaClientes.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		scrollPaneListaClientes.setViewportView(tableListaClientes);
		tableListaClientes.getTableHeader().setReorderingAllowed(false);
		tableListaClientes.setRowHeight(25);

		// Alterar a skin do JCheckBox nativo da JTable para JRadioButton
		tableListaClientes.setDefaultRenderer(Boolean.class, new CustomCellRenderer());

		// Alinhamento horizontal e fonte: cabeçalho da tabela de lista de clientes
		((JLabel) tableListaClientes.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
		tableListaClientes.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));

		// Cor intercalada nas linhas: tabela de lista de clientes
		gerenciarComportamentoTabela(tableListaClientes);

		// Consertar navegação tabela/frame: tabela de lista de clientes
		tableListaClientes.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, null);
		tableListaClientes.setFocusTraversalKeys(KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS, null);
		tableListaClientes.setFocusCycleRoot(false);

		// Carregar tabela de clientes
		arrayListCliente = buscarClientes();

		JLabel lblValorTotalVenda = new JLabel("Valor Total da Venda:");
		lblValorTotalVenda.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		lblValorTotalVenda.setBounds(607, 352, 232, 14);
		panelCenter.add(lblValorTotalVenda);

		formattedTextFieldValorTotalVenda = new JFormattedTextField();
		formattedTextFieldValorTotalVenda.setEnabled(false);
		formattedTextFieldValorTotalVenda.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		formattedTextFieldValorTotalVenda.setBounds(607, 377, 232, 20);
		panelCenter.add(formattedTextFieldValorTotalVenda);

		JLabel lblFormaDePagamento = new JLabel("Forma de Pagamento:");
		lblFormaDePagamento.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		lblFormaDePagamento.setBounds(607, 408, 232, 20);
		panelCenter.add(lblFormaDePagamento);

		comboBoxFormaPagamento = new JComboBox<>();
		comboBoxFormaPagamento.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		comboBoxFormaPagamento.setModel(new DefaultComboBoxModel<String>(new String[] {"\u00C0 vista", "A prazo (cheque pré-datado)"}));
		comboBoxFormaPagamento.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 13));
		comboBoxFormaPagamento.setBounds(605, 439, 234, 26);
		panelCenter.add(comboBoxFormaPagamento);

		JLabel lblVersaoSistema = new JLabel("SystemCommerce Vers\u00E3o 1.0");
		lblVersaoSistema.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		lblVersaoSistema.setBounds(10, 472, 260, 14);
		panelCenter.add(lblVersaoSistema);

		JLabel lblDataTempoAtual = new JLabel("HH:mm:ss dd-MM-yyyy");
		lblDataTempoAtual.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		lblDataTempoAtual.setBounds(325, 472, 200, 14);
		panelCenter.add(lblDataTempoAtual);
		JActualDateTime.getCurrentDateTime(lblDataTempoAtual);

		JButton btnVender = new JButton("Vender");
		btnVender.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (vendaItens.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Não há nenhum produto na lista de vendas para realizar uma venda.", "FALHA AO VENDER", JOptionPane.WARNING_MESSAGE);
				} else {
					try {
						Cliente cliente = clienteSelecionado();
						if (cliente == null) {
							JOptionPane.showMessageDialog(null, "Não há nenhum cliente selecionado para realizar uma venda.", "FALHA AO VENDER", JOptionPane.WARNING_MESSAGE);
						} else {
							int formaPagamento = comboBoxFormaPagamento.getSelectedIndex() + 1;
							double valorTotalVenda = Double.parseDouble(formattedTextFieldValorTotalVenda.getText());

							Comercial.realizarVendaCliente(cliente, vendedor, vendaItens, formaPagamento, valorTotalVenda);

							hashMapProdutos = mapearProdutosVenda();
							vendaItens = new ArrayList<>();
							((DefaultTableModel) tableListaVendas.getModel()).setRowCount(0);
							formattedTextFieldValorTotalVenda.setText(null);
							JOptionPane.showMessageDialog(null, "A venda foi realizada.", "VENDA REALIZADA", JOptionPane.INFORMATION_MESSAGE);
						}
					} catch (SisComException sisComException) {
						JOptionPane.showMessageDialog(null, sisComException.getMensagemErro(), "FALHA AO VENDER", JOptionPane.WARNING_MESSAGE);
					} catch (HibernateException hibernateException) {
						JOptionPane.showMessageDialog(null, "Erro ao realizar venda.\nErro: " + hibernateException.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
					} catch (Exception exception) {
						JOptionPane.showMessageDialog(null, "Erro ao realizar venda.\nErro: " + exception.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnVender.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnVender.setIcon(new ImageIcon(new ImageIcon(JVendedor.class.getResource("/img/vender.png")).getImage().getScaledInstance(50, 40, java.awt.Image.SCALE_SMOOTH)));
		btnVender.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		btnVender.setBounds(849, 437, 171, 49);
		panelCenter.add(btnVender);

		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				hashMapProdutos = mapearProdutosVenda();
				vendaItens = new ArrayList<>();
				((DefaultTableModel) tableListaVendas.getModel()).setRowCount(0);
				formattedTextFieldValorTotalVenda.setText(null);
				spinnerQuantidade.setValue(Integer.parseInt("1"));
			}
		});
		btnLimpar.setIcon(new ImageIcon(new ImageIcon(JVendedor.class.getResource("/img/limpar.png")).getImage().getScaledInstance(60, 40, java.awt.Image.SCALE_SMOOTH)));
		btnLimpar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnLimpar.setBounds(1030, 437, 171, 49);
		panelCenter.add(btnLimpar);

		JButton btnSair = new JButton("Sair");
		btnSair.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int i = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja sair do programa?", "Fechar o Programa", JOptionPane.YES_NO_OPTION);
				if (i == 0) {
					JLogin jLogin = new JLogin();
					jLogin.setVisible(true);
					jLogin.setLocationRelativeTo(null);
					setVisible(false);
				}
			}
		});
		btnSair.setIcon(new ImageIcon(new ImageIcon(JVendedor.class.getResource("/img/sair.png")).getImage().getScaledInstance(80, 40, java.awt.Image.SCALE_SMOOTH)));
		btnSair.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		btnSair.setBounds(1211, 437, 171, 49);
		panelCenter.add(btnSair);

		// Evento ao clicar no X do Windows
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				int i = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja sair do programa?", "Fechar o Programa", JOptionPane.YES_NO_OPTION);
				if (i == 0) {
					JLogin jLogin = new JLogin();
					jLogin.setVisible(true);
					jLogin.setLocationRelativeTo(null);
					setVisible(false);
				}
			}
		});

		// Evento ao pressionar ESC
		getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "Cancel");
		getRootPane().getActionMap().put("Cancel", new AbstractAction() {
			private static final long serialVersionUID = 1L;
			public void actionPerformed(ActionEvent e) {
				int i = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja sair do programa?", "Fechar o Programa", JOptionPane.YES_NO_OPTION);
				if (i == 0) {
					JLogin jLogin = new JLogin();
					jLogin.setVisible(true);
					jLogin.setLocationRelativeTo(null);
					setVisible(false);
				}
			}
		});

		// Definir botão padrão para a tecla ENTER
		JRootPane rootPane = SwingUtilities.getRootPane(btnVender);
		rootPane.setDefaultButton(btnVender);
	}

	// Mapear produtos
	private HashMap<Integer, Produto> mapearProdutosVenda() {
		HashMap<Integer, Produto> hashMapProdutos = new HashMap<>();

		try {
			ArrayList<Produto> arrayListProduto = Comercial.consultarProdutosOrdemAlfabetica();

			DefaultTableModel defaultTableModel = (DefaultTableModel) tableProdutosVenda.getModel();
			Object rowData[] = new Object[5];

			defaultTableModel.setRowCount(0);
			for (int i = 0; i < arrayListProduto.size(); i++) {
				rowData[0] = false;
				rowData[1] = arrayListProduto.get(i).getCodigo();
				rowData[2] = arrayListProduto.get(i).getNome();
				rowData[3] = arrayListProduto.get(i).getPrecoUnitario();
				rowData[4] = arrayListProduto.get(i).getEstoque();

				defaultTableModel.addRow(rowData);
				hashMapProdutos.put(arrayListProduto.get(i).getCodigo(), arrayListProduto.get(i));
			}
		} catch (HibernateException hibernateException) {
			JOptionPane.showMessageDialog(null, "Erro ao buscar e/ou exibir produtos do banco de dados.\n" + hibernateException.getMessage(), "ERRO AO VISUALIZAR", JOptionPane.ERROR_MESSAGE);
		} catch (Exception exception) {
			JOptionPane.showMessageDialog(null, "Erro ao buscar e/ou exibir produtos do banco de dados.\n" + exception.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
		}

		return hashMapProdutos;
	}

	// Preencher tabela de vendas
	private void preecherTabelaVendas() {
		DefaultTableModel defaultTableModel = (DefaultTableModel) tableListaVendas.getModel();
		Object rowData[] = new Object[5];

		HashMap<Integer, Boolean> hashMapSelecionado = new HashMap<>();
		for (int i = 0; i < vendaItens.size(); i++)
			hashMapSelecionado.put(vendaItens.get(i).getProduto().getCodigo(), false);

		if (tableListaVendas.getRowCount() != 0) {
			for (int i = 0; i < tableListaVendas.getRowCount(); i++) {
				hashMapSelecionado.put((Integer) defaultTableModel.getValueAt(i, 1), ((Boolean) defaultTableModel.getValueAt(i, 0)));
			}
		}

		defaultTableModel.setRowCount(0);
		for (int i = 0; i < vendaItens.size(); i++) {
			rowData[0] = hashMapSelecionado.get(vendaItens.get(i).getProduto().getCodigo());
			rowData[1] = vendaItens.get(i).getProduto().getCodigo();
			rowData[2] = vendaItens.get(i).getProduto().getNome();
			rowData[3] = vendaItens.get(i).getQuantVenda();
			rowData[4] = vendaItens.get(i).getValorVenda();

			defaultTableModel.addRow(rowData);
		}
	}

	// Preencher tabela de clientes
	private ArrayList<Cliente> buscarClientes() {
		ArrayList<Cliente> arrayListCliente = new ArrayList<>();

		try {
			arrayListCliente = Comercial.consultarClientesOrdemAlfabetica();

			DefaultTableModel defaultTableModel = (DefaultTableModel) tableListaClientes.getModel();
			Object rowData[] = new Object[5];

			defaultTableModel.setRowCount(0);
			for (int i = 0; i < arrayListCliente.size(); i++) {
				rowData[0] = false;
				rowData[1] = arrayListCliente.get(i).getCodigo();
				rowData[2] = arrayListCliente.get(i).getNome();
				rowData[3] = arrayListCliente.get(i).getCpf();
				rowData[4] = arrayListCliente.get(i).getLimiteCredito();

				defaultTableModel.addRow(rowData);
			}
		} catch (HibernateException hibernateException) {
			JOptionPane.showMessageDialog(null, "Erro ao buscar e/ou exibir clientes do banco de dados.\n" + hibernateException.getMessage(), "ERRO AO VISUALIZAR", JOptionPane.ERROR_MESSAGE);
		} catch (Exception exception) {
			JOptionPane.showMessageDialog(null, "Erro ao buscar e/ou exibir clientes do banco de dados.\n" + exception.getMessage(), "ERRO AO VISUALIZAR", JOptionPane.ERROR_MESSAGE);
		}

		return arrayListCliente;
	}

	// Atualizar estoque de produtos na tabela e no hashMap de produtos para venda
	private void atualizarEstoqueProdutos(Produto produto, JTable jTable, int linhaTabela, int estoqueNovo) {
		produto.setEstoque(estoqueNovo);
		hashMapProdutos.put(produto.getCodigo(), produto);
		jTable.setValueAt(produto.getEstoque(), linhaTabela, 4);
	}

	// Buscar a linha da tabela por código do produto
	private int buscarLinhaTabelaPorCodigo(JTable jtable, int codigo) {
		for (int i = 0; i < jtable.getRowCount(); i++) {
			if ((Integer) jtable.getValueAt(i, 1) == codigo)
				return i;
		}
		return -1;
	}

	// Atualizar valor total
	private void atualizarValorTotalVenda() {
		double valorTotalVenda = 0.0;
		for (ItemVenda itemVenda : vendaItens)
			valorTotalVenda += itemVenda.getValorVenda();
		formattedTextFieldValorTotalVenda.setText(String.valueOf(valorTotalVenda));
	}

	// Capturar cliente selecionado na tabela
	private Cliente clienteSelecionado() {
		for (int i = 0; i < tableListaClientes.getRowCount(); i++) {
			Boolean checked = Boolean.valueOf(tableListaClientes.getValueAt(i, 0).toString());

			if (checked) {
				int codigoClienteSelecionado = Integer.parseInt(tableListaClientes.getValueAt(i, 1).toString());

				for (Cliente cliente : arrayListCliente)
					if (cliente.getCodigo() == codigoClienteSelecionado)
						return cliente;
			}
		}

		return null;
	}

	// Método para gerenciar comportamento da tabela
	private void gerenciarComportamentoTabela(JTable jTable) {
		jTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
			private static final long serialVersionUID = 1L;
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
				final Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				component.setBackground(row % 2 == 0 ? new Color(220, 220, 220) : Color.WHITE);

				if (isSelected)
					component.setBackground(new Color(0, 120, 215));

				return component;
			}
		});
	}

	// Apenas números positivos
	private void permitirApenasNumerosPositivos(JFormattedTextField jFormattedTextField) {
		jFormattedTextField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent keyEvent) {
				char caracter = keyEvent.getKeyChar();
				if (!((caracter >= '1') && (caracter <= '9') || (caracter == KeyEvent.VK_BACK_SPACE) || (caracter == KeyEvent.VK_DELETE))) {
					getToolkit().beep();
					keyEvent.consume();
				}
			}
		});
	}
}