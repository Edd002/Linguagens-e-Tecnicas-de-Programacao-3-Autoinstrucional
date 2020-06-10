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
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import org.hibernate.HibernateException;

import controller.Comercial;
import model.Fornecedor;
import model.ItemCompra;
import model.Produto;
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

public class JFornecedor extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPaneFornecedor;
	private JTable tableListaCompras;
	private JTable tableProdutosCompra;

	private HashMap<Integer, Produto> hashMapProdutos;
	private List<ItemCompra> compraItens;

	/**
	 * Launch the application.
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFornecedor frame = new JFornecedor();
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
	public JFornecedor(Fornecedor fornecedor) {
		setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		setIconImage(Toolkit.getDefaultToolkit().getImage(JFornecedor.class.getResource("/img/logo.png")));
		setResizable(false);
		setTitle("SYSTEM COMMERCE - \u00C1rea do Fornecedor - " + fornecedor.getNome());
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 1400, 750);
		contentPaneFornecedor = new JPanel();
		contentPaneFornecedor.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPaneFornecedor.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPaneFornecedor);

		JPanel panelNorth = new JPanel();
		contentPaneFornecedor.add(panelNorth, BorderLayout.NORTH);

		JLabel lblSystemCommerce = new JLabel("System Commerce");
		lblSystemCommerce.setIcon(new ImageIcon(JFornecedor.class.getResource("/img/logo.png")));
		lblSystemCommerce.setFont(new Font("BankGothic Lt BT", Font.BOLD, 24));
		panelNorth.add(lblSystemCommerce);

		JPanel panelCenter = new JPanel();
		contentPaneFornecedor.add(panelCenter, BorderLayout.CENTER);
		panelCenter.setLayout(null);

		JLabel lblListaCompras = new JLabel("Lista de Compras");
		lblListaCompras.setFont(new Font("BankGothic Lt BT", Font.BOLD, 20));
		lblListaCompras.setBounds(165, 11, 215, 20);
		panelCenter.add(lblListaCompras);

		JScrollPane scrollPaneListaCompras = new JScrollPane();
		scrollPaneListaCompras.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		scrollPaneListaCompras.setBounds(0, 42, 597, 372);
		panelCenter.add(scrollPaneListaCompras);

		tableListaCompras = new JTable() {
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
				default:
					return Object.class;
				}
			}
		};
		tableListaCompras.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Selecione", "C\u00F3digo", "Nome", "Quantidade", "Valor Total"
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
		tableListaCompras.getColumnModel().getColumn(0).setResizable(false);
		tableListaCompras.getColumnModel().getColumn(0).setPreferredWidth(100);
		tableListaCompras.getColumnModel().getColumn(1).setResizable(false);
		tableListaCompras.getColumnModel().getColumn(2).setResizable(false);
		tableListaCompras.getColumnModel().getColumn(2).setPreferredWidth(150);
		tableListaCompras.getColumnModel().getColumn(2).setMinWidth(150);
		tableListaCompras.getColumnModel().getColumn(3).setResizable(false);
		tableListaCompras.getColumnModel().getColumn(3).setPreferredWidth(150);
		tableListaCompras.getColumnModel().getColumn(4).setResizable(false);
		tableListaCompras.getColumnModel().getColumn(4).setPreferredWidth(150);
		tableListaCompras.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tableListaCompras.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		scrollPaneListaCompras.setViewportView(tableListaCompras);
		tableListaCompras.getTableHeader().setReorderingAllowed(false);

		// Alinhamento horizontal e fonte: cabeçalho da tabela de lista de compras
		((JLabel) tableListaCompras.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
		tableListaCompras.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));

		// Cor intercalada nas linhas: tabela de lista de compras
		gerenciarComportamentoTabela(tableListaCompras);

		// Consertar navegação tabela/frame: tabela de lista de compras
		tableListaCompras.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, null);
		tableListaCompras.setFocusTraversalKeys(KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS, null);
		tableListaCompras.setFocusCycleRoot(false);

		JLabel lblProdutosCompra = new JLabel("Produtos para Compra");
		lblProdutosCompra.setFont(new Font("BankGothic Lt BT", Font.BOLD, 20));
		lblProdutosCompra.setBounds(923, 11, 275, 20);
		panelCenter.add(lblProdutosCompra);

		JScrollPane scrollPaneProdutosCompra = new JScrollPane();
		scrollPaneProdutosCompra.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		scrollPaneProdutosCompra.setBounds(787, 42, 597, 372);
		panelCenter.add(scrollPaneProdutosCompra);

		tableProdutosCompra = new JTable() {
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
				default:
					return Object.class;
				}
			}
		};
		tableProdutosCompra.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tableProdutosCompra.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Selecione", "Codigo", "Nome", "Pre\u00E7o Unit\u00E1rio"
				}
				) {
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
					true, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tableProdutosCompra.getColumnModel().getColumn(0).setResizable(false);
		tableProdutosCompra.getColumnModel().getColumn(0).setPreferredWidth(100);
		tableProdutosCompra.getColumnModel().getColumn(1).setResizable(false);
		tableProdutosCompra.getColumnModel().getColumn(2).setResizable(false);
		tableProdutosCompra.getColumnModel().getColumn(2).setPreferredWidth(200);
		tableProdutosCompra.getColumnModel().getColumn(3).setResizable(false);
		tableProdutosCompra.getColumnModel().getColumn(3).setPreferredWidth(200);
		tableProdutosCompra.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		scrollPaneProdutosCompra.setViewportView(tableProdutosCompra);
		tableProdutosCompra.getTableHeader().setReorderingAllowed(false);

		// Alinhamento horizontal e fonte: cabeçalho da tabela de produtos disponíveis
		((JLabel) tableProdutosCompra.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
		tableProdutosCompra.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));

		// Cor intercalada nas linhas: tabela de produtos disponíveis
		gerenciarComportamentoTabela(tableProdutosCompra);

		// Consertar navegação tabela/frame: tabela de produtos disponíveis
		tableProdutosCompra.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, null);
		tableProdutosCompra.setFocusTraversalKeys(KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS, null);
		tableProdutosCompra.setFocusCycleRoot(false);

		JLabel lblQuantidade = new JLabel("Quantidade:");
		lblQuantidade.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		lblQuantidade.setBounds(607, 264, 171, 20);
		panelCenter.add(lblQuantidade);

		JSpinner spinnerQuantidade = new JSpinner();
		spinnerQuantidade.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		spinnerQuantidade.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		spinnerQuantidade.setBounds(607, 295, 171, 26);
		permitirApenasNumerosPositivos(((JSpinner.NumberEditor) spinnerQuantidade.getEditor()).getTextField());
		panelCenter.add(spinnerQuantidade);

		// Mapeamento de produtos por código e gerar lista de compras
		hashMapProdutos = mapearProdutosCompra();
		compraItens = new ArrayList<>();

		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Percorrer tabela de produtos
				boolean verificarSelecao = false;

				for(int i = 0; i < tableProdutosCompra.getRowCount(); i++) {
					Boolean checked = Boolean.valueOf(tableProdutosCompra.getValueAt(i, 0).toString());
					Integer checkedCodigo = Integer.parseInt(tableProdutosCompra.getValueAt(i, 1).toString());

					Produto produtoSelecionado = hashMapProdutos.get(checkedCodigo);
					int quantidadeProdutoSelecionada = Integer.parseInt(spinnerQuantidade.getValue().toString());
					double precoUnitarioProdutoSelecionado = hashMapProdutos.get(checkedCodigo).getPrecoUnitario();

					if(checked) {
						verificarSelecao = true;

						boolean listaPossuiProduto = false;
						for (ItemCompra itemCompra : compraItens) {
							if (itemCompra.getProduto().equals(produtoSelecionado)) {
								itemCompra.setQuantCompra(itemCompra.getQuantCompra() + quantidadeProdutoSelecionada);
								itemCompra.setValorCompra(itemCompra.getValorCompra() + (quantidadeProdutoSelecionada * precoUnitarioProdutoSelecionado));
								listaPossuiProduto = true;
								break;
							}
						}

						if (listaPossuiProduto == false) {
							ItemCompra itemCompra = new ItemCompra();

							itemCompra.setProduto(produtoSelecionado);
							itemCompra.setQuantCompra(quantidadeProdutoSelecionada);
							itemCompra.setValorCompra(quantidadeProdutoSelecionada * precoUnitarioProdutoSelecionado);

							compraItens.add(itemCompra);
						}
					}
				}

				if (verificarSelecao == false)
					JOptionPane.showMessageDialog(null, "Não há nenhum produto selecionado para adicionar à lista de compras.", "FALHA AO ADICIONAR", JOptionPane.WARNING_MESSAGE);
				else
					preecherTabelaCompras();
			}
		});
		btnAdicionar.setIcon(new ImageIcon(new ImageIcon(JFornecedor.class.getResource("/img/seta-esquerda.png")).getImage().getScaledInstance(50, 40, java.awt.Image.SCALE_SMOOTH)));
		btnAdicionar.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		btnAdicionar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAdicionar.setBounds(607, 144, 171, 49);
		panelCenter.add(btnAdicionar);

		JButton btnRemover = new JButton("Remover");
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Percorrer tabela de lista de compras
				boolean verificarSelecao = false;

				for(int i = 0; i < tableListaCompras.getRowCount(); i++) {
					Boolean checked = Boolean.valueOf(tableListaCompras.getValueAt(i, 0).toString());
					Integer checkedCodigo = Integer.parseInt(tableListaCompras.getValueAt(i, 1).toString());
					int quantidadeProdutoSelecionada = Integer.parseInt(spinnerQuantidade.getValue().toString());

					if(checked) {
						verificarSelecao = true;

						Iterator<ItemCompra> iterator = compraItens.iterator();
						while (iterator.hasNext()) {
							ItemCompra itemCompra = iterator.next();
							if (itemCompra.getProduto().getCodigo() == checkedCodigo) {
								if (itemCompra.getQuantCompra() <= quantidadeProdutoSelecionada) {
									iterator.remove();
								} else {
									itemCompra.setQuantCompra(itemCompra.getQuantCompra() - quantidadeProdutoSelecionada);
									itemCompra.setValorCompra(itemCompra.getProduto().getPrecoUnitario() * itemCompra.getQuantCompra());
								}
							}
						}
					}
				}

				if (verificarSelecao == false)
					JOptionPane.showMessageDialog(null, "Não há nenhum produto selecionado para remover da lista de compras.", "FALHA AO REMOVER", JOptionPane.WARNING_MESSAGE);
				else
					preecherTabelaCompras();
			}
		});
		btnRemover.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRemover.setIcon(new ImageIcon(new ImageIcon(JFornecedor.class.getResource("/img/seta-direita.png")).getImage().getScaledInstance(50, 40, java.awt.Image.SCALE_SMOOTH)));
		btnRemover.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		btnRemover.setBounds(607, 204, 171, 49);
		panelCenter.add(btnRemover);

		JLabel lblVersaoSistema = new JLabel("SystemCommerce Vers\u00E3o 1.0");
		lblVersaoSistema.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		lblVersaoSistema.setBounds(10, 472, 260, 14);
		panelCenter.add(lblVersaoSistema);

		JLabel lblDataTempoAtual = new JLabel("HH:mm:ss dd-MM-yyyy");
		lblDataTempoAtual.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		lblDataTempoAtual.setBounds(325, 472, 200, 14);
		panelCenter.add(lblDataTempoAtual);
		JActualDateTime.getCurrentDateTime(lblDataTempoAtual);

		JButton btnComprar = new JButton("Comprar");
		btnComprar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (compraItens.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Não há nenhum produto na lista de compras para realizar uma compra.", "FALHA AO COMPRAR", JOptionPane.WARNING_MESSAGE);
				} else {
					try {
						Comercial.realizarCompraFornecedor(fornecedor, compraItens);
						hashMapProdutos = mapearProdutosCompra();
						compraItens = new ArrayList<>();
						((DefaultTableModel) tableListaCompras.getModel()).setRowCount(0);
						JOptionPane.showMessageDialog(null, "A compra foi realizada.", "COMPRA REALIZADA", JOptionPane.INFORMATION_MESSAGE);
					} catch (HibernateException hibernateException) {
						JOptionPane.showMessageDialog(null, "Erro ao realizar compra.\nErro: " + hibernateException.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
					} catch (Exception exception) {
						JOptionPane.showMessageDialog(null, "Erro ao realizar compra.\nErro: " + exception.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnComprar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnComprar.setIcon(new ImageIcon(new ImageIcon(JFornecedor.class.getResource("/img/comprar.png")).getImage().getScaledInstance(50, 40, java.awt.Image.SCALE_SMOOTH)));
		btnComprar.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		btnComprar.setBounds(849, 437, 171, 49);
		panelCenter.add(btnComprar);

		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				hashMapProdutos = mapearProdutosCompra();
				compraItens = new ArrayList<>();
				((DefaultTableModel) tableListaCompras.getModel()).setRowCount(0);
				spinnerQuantidade.setValue(Integer.parseInt("1"));
			}
		});
		btnLimpar.setIcon(new ImageIcon(new ImageIcon(JFornecedor.class.getResource("/img/limpar.png")).getImage().getScaledInstance(60, 40, java.awt.Image.SCALE_SMOOTH)));
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
		btnSair.setIcon(new ImageIcon(new ImageIcon(JFornecedor.class.getResource("/img/sair.png")).getImage().getScaledInstance(80, 40, java.awt.Image.SCALE_SMOOTH)));
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
		JRootPane rootPane = SwingUtilities.getRootPane(btnComprar);
		rootPane.setDefaultButton(btnComprar);
	}

	// Mapear produtos
	private HashMap<Integer, Produto> mapearProdutosCompra() {
		HashMap<Integer, Produto> hashMapProdutos = new HashMap<>();

		try {
			ArrayList<Produto> arrayListProduto = Comercial.consultarProdutosOrdemAlfabetica();

			DefaultTableModel defaultTableModel = (DefaultTableModel) tableProdutosCompra.getModel();
			Object rowData[] = new Object[4];

			defaultTableModel.setRowCount(0);
			for (int i = 0; i < arrayListProduto.size(); i++) {
				rowData[0] = false;
				rowData[1] = arrayListProduto.get(i).getCodigo();
				rowData[2] = arrayListProduto.get(i).getNome();
				rowData[3] = arrayListProduto.get(i).getPrecoUnitario();

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

	// Preencher tabela de compras
	private void preecherTabelaCompras() {
		DefaultTableModel defaultTableModel = (DefaultTableModel) tableListaCompras.getModel();
		Object rowData[] = new Object[5];

		HashMap<Integer, Boolean> hashMapSelecionado = new HashMap<>();
		for (int i = 0; i < compraItens.size(); i++)
			hashMapSelecionado.put(compraItens.get(i).getProduto().getCodigo(), false);

		if (tableListaCompras.getRowCount() != 0) {
			for (int i = 0; i < tableListaCompras.getRowCount(); i++) {
				hashMapSelecionado.put((Integer) defaultTableModel.getValueAt(i, 1), ((Boolean) defaultTableModel.getValueAt(i, 0)));
			}
		}

		defaultTableModel.setRowCount(0);
		for (int i = 0; i < compraItens.size(); i++) {
			rowData[0] = hashMapSelecionado.get(compraItens.get(i).getProduto().getCodigo());
			rowData[1] = compraItens.get(i).getProduto().getCodigo();
			rowData[2] = compraItens.get(i).getProduto().getNome();
			rowData[3] = compraItens.get(i).getQuantCompra();
			rowData[4] = compraItens.get(i).getValorCompra();

			defaultTableModel.addRow(rowData);
		}
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