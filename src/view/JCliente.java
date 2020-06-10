package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.JComponent;
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
import model.Cliente;
import model.ItemVenda;
import model.Venda;
import utility.JActualDateTime;

import java.awt.Toolkit;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Cursor;

public class JCliente extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPaneCliente;
	private JTable tableDadosCliente;
	private JTable tableDadosVendas;

	/**
	 * Launch the application.
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JCliente frame = new JCliente();
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
	public JCliente(Cliente cliente) {
		setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		setIconImage(Toolkit.getDefaultToolkit().getImage(JCliente.class.getResource("/img/logo.png")));
		setTitle("SYSTEM COMMERCE - \u00C1rea do Cliente - " + cliente.getNome());
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 1400, 750);
		contentPaneCliente = new JPanel();
		contentPaneCliente.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPaneCliente.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPaneCliente);

		JPanel panelNorth = new JPanel();
		contentPaneCliente.add(panelNorth, BorderLayout.NORTH);

		JLabel lblSystemCommerce = new JLabel("System Commerce");
		lblSystemCommerce.setIcon(new ImageIcon(JCliente.class.getResource("/img/logo.png")));
		lblSystemCommerce.setFont(new Font("BankGothic Lt BT", Font.BOLD, 24));
		panelNorth.add(lblSystemCommerce);

		JPanel panelCenter = new JPanel();
		contentPaneCliente.add(panelCenter, BorderLayout.CENTER);
		panelCenter.setLayout(null);

		JLabel lblDadosCadastrais = new JLabel("Dados Cadastrais");
		lblDadosCadastrais.setFont(new Font("BankGothic Lt BT", Font.BOLD, 20));
		lblDadosCadastrais.setBounds(585, 11, 230, 20);
		panelCenter.add(lblDadosCadastrais);

		JScrollPane scrollPaneDadosCliente = new JScrollPane();
		scrollPaneDadosCliente.setBounds(0, 42, 1382, 44);
		panelCenter.add(scrollPaneDadosCliente);

		tableDadosCliente = new JTable();
		tableDadosCliente.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tableDadosCliente.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"C\u00F3digo", "Nome", "Telefone Fixo", "Telefone Celular", "E-mail", "Data de Cadastro", "CPF", "Limite de Cr\u00E9dito", "Login", "Senha"
				}
				) {
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
					false, false, false, false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tableDadosCliente.getColumnModel().getColumn(0).setResizable(false);
		tableDadosCliente.getColumnModel().getColumn(1).setResizable(false);
		tableDadosCliente.getColumnModel().getColumn(1).setPreferredWidth(135);
		tableDadosCliente.getColumnModel().getColumn(2).setResizable(false);
		tableDadosCliente.getColumnModel().getColumn(2).setPreferredWidth(135);
		tableDadosCliente.getColumnModel().getColumn(3).setResizable(false);
		tableDadosCliente.getColumnModel().getColumn(3).setPreferredWidth(135);
		tableDadosCliente.getColumnModel().getColumn(4).setResizable(false);
		tableDadosCliente.getColumnModel().getColumn(4).setPreferredWidth(185);
		tableDadosCliente.getColumnModel().getColumn(5).setResizable(false);
		tableDadosCliente.getColumnModel().getColumn(5).setPreferredWidth(200);
		tableDadosCliente.getColumnModel().getColumn(6).setResizable(false);
		tableDadosCliente.getColumnModel().getColumn(6).setPreferredWidth(150);
		tableDadosCliente.getColumnModel().getColumn(7).setResizable(false);
		tableDadosCliente.getColumnModel().getColumn(7).setPreferredWidth(120);
		tableDadosCliente.getColumnModel().getColumn(8).setResizable(false);
		tableDadosCliente.getColumnModel().getColumn(8).setPreferredWidth(120);
		tableDadosCliente.getColumnModel().getColumn(9).setResizable(false);
		tableDadosCliente.getColumnModel().getColumn(9).setPreferredWidth(120);
		tableDadosCliente.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 12));
		tableDadosCliente.getTableHeader().setReorderingAllowed(false);
		scrollPaneDadosCliente.setViewportView(tableDadosCliente);

		// Alinhamento horizontal e fonte: cabeçalho da tabela de dados do cliente
		((JLabel) tableDadosCliente.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
		tableDadosCliente.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));

		// Cor intercalada nas linhas: tabela de dados do cliente
		gerenciarComportamentoTabela(tableDadosCliente);

		// Exibir cliente logado
		exibirCliente(cliente);

		JLabel lblDadosVendas = new JLabel("Dados das Vendas");
		lblDadosVendas.setFont(new Font("BankGothic Lt BT", Font.BOLD, 20));
		lblDadosVendas.setBounds(585, 128, 230, 20);
		panelCenter.add(lblDadosVendas);

		JScrollPane scrollPaneDadosVenda = new JScrollPane();
		scrollPaneDadosVenda.setBounds(0, 159, 1382, 256);
		panelCenter.add(scrollPaneDadosVenda);

		tableDadosVendas = new JTable();
		tableDadosVendas.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tableDadosVendas.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"C\u00F3digo do Vendedor", "Nome do Vendedor", "N\u00FAmero da Venda", "Produto", "Quantidade", "Valor", "Forma de Pagamento", "Data da Venda"
				}
				) {
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
					false, false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tableDadosVendas.getColumnModel().getColumn(0).setResizable(false);
		tableDadosVendas.getColumnModel().getColumn(0).setPreferredWidth(125);
		tableDadosVendas.getColumnModel().getColumn(1).setResizable(false);
		tableDadosVendas.getColumnModel().getColumn(1).setPreferredWidth(150);
		tableDadosVendas.getColumnModel().getColumn(2).setResizable(false);
		tableDadosVendas.getColumnModel().getColumn(2).setPreferredWidth(150);
		tableDadosVendas.getColumnModel().getColumn(3).setResizable(false);
		tableDadosVendas.getColumnModel().getColumn(3).setPreferredWidth(150);
		tableDadosVendas.getColumnModel().getColumn(4).setResizable(false);
		tableDadosVendas.getColumnModel().getColumn(4).setPreferredWidth(150);
		tableDadosVendas.getColumnModel().getColumn(5).setResizable(false);
		tableDadosVendas.getColumnModel().getColumn(5).setPreferredWidth(150);
		tableDadosVendas.getColumnModel().getColumn(6).setResizable(false);
		tableDadosVendas.getColumnModel().getColumn(6).setPreferredWidth(150);
		tableDadosVendas.getColumnModel().getColumn(7).setResizable(false);
		tableDadosVendas.getColumnModel().getColumn(7).setPreferredWidth(200);
		tableDadosVendas.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 12));
		tableDadosVendas.getTableHeader().setReorderingAllowed(false);
		scrollPaneDadosVenda.setViewportView(tableDadosVendas);

		// Alinhamento horizontal e fonte: cabeçalho da dados das vendas
		((JLabel) tableDadosVendas.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
		tableDadosVendas.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));

		// Cor intercalada nas linhas: tabela dados das vendas
		gerenciarComportamentoTabela(tableDadosVendas);

		// Exibir todas as vendas do cliente logado
		exibirVendasClientes(cliente);

		JLabel lblVersaoSistema = new JLabel("SystemCommerce Vers\u00E3o 1.0");
		lblVersaoSistema.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		lblVersaoSistema.setBounds(10, 472, 260, 14);
		panelCenter.add(lblVersaoSistema);

		JLabel lblDataTempoAtual = new JLabel("HH:mm:ss dd-MM-yyyy");
		lblDataTempoAtual.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		lblDataTempoAtual.setBounds(325, 472, 200, 14);
		panelCenter.add(lblDataTempoAtual);
		JActualDateTime.getCurrentDateTime(lblDataTempoAtual);

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
		JRootPane rootPane = SwingUtilities.getRootPane(btnSair);
		rootPane.setDefaultButton(btnSair);
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

	// Exibir cliente logado
	private void exibirCliente(Cliente cliente) {
		try {
			DefaultTableModel defaultTableModel = (DefaultTableModel) tableDadosCliente.getModel();
			Object rowData[] = new Object[10];

			rowData[0] = cliente.getCodigo();
			rowData[1] = cliente.getNome();
			rowData[2] = cliente.getTelefoneFixo();
			rowData[3] = cliente.getTelefoneCelular();
			rowData[4] = cliente.getEmail();
			rowData[5] = cliente.getDataCad();
			rowData[6] = cliente.getCpf();
			rowData[7] = cliente.getLimiteCredito();
			rowData[8] = cliente.getLogin();
			rowData[9] = cliente.getSenha();

			defaultTableModel.addRow(rowData);
		} catch (Exception exception) {
			JOptionPane.showMessageDialog(null, "Erro ao exibir cliente.\n" + exception.getMessage(), "ERRO AO VISUALIZAR", JOptionPane.ERROR_MESSAGE);
		}
	}

	// Exibir todas as vendas do cliente logado
	private void exibirVendasClientes(Cliente cliente) {
		try {
			ArrayList<Venda> arrayListaVenda = Comercial.consultarVendasCliente(cliente);

			DefaultTableModel defaultTableModel = (DefaultTableModel) tableDadosVendas.getModel();
			Object rowData[] = new Object[8];

			defaultTableModel.setRowCount(0);
			for (int i = 0; i < arrayListaVenda.size(); i++) {
				for (ItemVenda vendaItens : arrayListaVenda.get(i).getVendaItens()) {
					rowData[0] = arrayListaVenda.get(i).getVendedor().getCodigo();
					rowData[1] = arrayListaVenda.get(i).getVendedor().getNome();
					rowData[2] = arrayListaVenda.get(i).getNumVenda();
					rowData[3] = vendaItens.getProduto().getNome();
					rowData[4] = vendaItens.getQuantVenda();
					rowData[5] = vendaItens.getValorVenda();

					if (arrayListaVenda.get(i).getFormaPagamento() == 1)
						rowData[6] = "À vista";
					else
						rowData[6] = "A prazo";

					rowData[7] = arrayListaVenda.get(i).getDataVenda();

					defaultTableModel.addRow(rowData);
				}
			}
		} catch (HibernateException hibernateException) {
			JOptionPane.showMessageDialog(null, "Erro ao buscar e/ou exibir vendas do cliente do banco de dados.\n" + hibernateException.getMessage(), "ERRO AO VISUALIZAR", JOptionPane.ERROR_MESSAGE);
		} catch (Exception exception) {
			JOptionPane.showMessageDialog(null, "Erro ao buscar e/ou exibir vendas do cliente do banco de dados.\n" + exception.getMessage(), "ERRO AO VISUALIZAR", JOptionPane.ERROR_MESSAGE);
		}
	}
}