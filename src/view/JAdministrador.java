package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.KeyboardFocusManager;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import java.awt.Cursor;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Dimension;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import org.hibernate.HibernateException;

import controller.Comercial;
import controller.SisComEstatistica;
import controller.SisComException;
import model.Cliente;
import model.Compra;
import model.Fornecedor;
import model.Produto;
import model.Venda;
import model.Vendedor;
import net.java.balloontip.BalloonTip;
import net.java.balloontip.styles.EdgedBalloonStyle;
import net.java.balloontip.utils.FadingUtils;
import utility.JActualDateTime;
import utility.JMoneyField;

import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JPasswordField;
import javax.swing.border.LineBorder;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.Icon;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;
import javax.swing.SwingConstants;

public class JAdministrador extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPaneAdministrador;

	private JPanel panelVisualizarClientes;
	private JPanel panelVisualizarFornecedores;
	private JPanel panelVisualizarVendedores;
	private JPanel panelVisualizarProdutos;
	private JPanel panelVisualizarVendas;
	private JPanel panelVisualizarCompras;
	private JPanel panelVisualizarEstatisticas;

	private JTable tableVisualizarClientes;
	private JTable tableVisualizarFornecedores;
	private JTable tableVisualizarVendedores;
	private JTable tableVisualizarProdutos;
	private JTable tableVisualizarVendas;
	private JTable tableVisualizarCompras;
	private JTable tableVisualizarEstatisticas;

	private JFormattedTextField formattedTextFieldCpfClienteBusca;
	private JFormattedTextField formattedTextFieldCnpjFornecedorBusca;
	private JFormattedTextField formattedTextFieldCpfVendedorBusca;
	private JFormattedTextField formattedTextFieldCodigoProdutoBusca;
	private JRadioButton rdbtnTodosProdutos;
	private JRadioButton rdbtnEstoqueAbaixoMinimoProdutos;
	private final ButtonGroup buttonGroupVisualizarProdutos = new ButtonGroup();
	private JFormattedTextField formattedTextFieldOrdenarNomeClienteVendedor;
	private JRadioButton rdbtnOrdenarCliente;
	private JRadioButton rdbtnOrdenarVendedor;
	private final ButtonGroup buttonGroupOrdenarVendas = new ButtonGroup();
	private JDateChooser dateChooserDataInicialConsultaVendas;
	private JDateChooser dateChooserDataFinalConsultaVendas;
	private JFormattedTextField formattedTextFieldOrdernarNomeFornecedor;
	private JDateChooser dateChooserDataFinalConsultaCompras;
	private JDateChooser dateChooserDataInicialConsultaCompras;
	private JRadioButton rdbtnEstatitiscaFornecedor;
	private JRadioButton rdbtnEstatitiscaVendedor;
	private JRadioButton rdbtnEstatitiscaCliente;
	private final ButtonGroup buttonGroupVisualizarEstatisticas = new ButtonGroup();
	private JDateChooser dateChooserDataFinalEstatisticas;
	private JDateChooser dateChooserDataInicialEstatisticas;

	private JTextField textFieldNomeCliente;
	private JFormattedTextField formattedTextFieldTelefoneFixoCliente;
	private JFormattedTextField formattedTextFieldTelefoneCelularCliente;
	private JFormattedTextField formattedTextFieldEmailCliente;
	private JFormattedTextField formattedTextFieldCpfCliente;
	private JFormattedTextField formattedTextFieldLimiteCreditoCliente;
	private JTextField textFieldLoginCliente;
	private JPasswordField passwordFieldSenhaCliente;
	private JPasswordField passwordFieldConfirmarSenhaCliente;

	private JTextField textFieldNomeFornecedor;
	private JFormattedTextField formattedTextFieldTelefoneFixoFornecedor;
	private JFormattedTextField formattedTextFieldTelefoneCelularFornecedor;
	private JFormattedTextField formattedTextFieldEmailFornecedor;
	private JFormattedTextField formattedTextFieldCnpjFornecedor;
	private JTextField textFieldNomeContatoFornecedor;
	private JTextField textFieldLoginFornecedor;
	private JPasswordField passwordFieldSenhaFornecedor;
	private JPasswordField passwordFieldConfirmarSenhaFornecedor;

	private JTextField textFieldNomeVendedor;
	private JFormattedTextField formattedTextFieldTelefoneFixoVendedor;
	private JFormattedTextField formattedTextFieldTelefoneCelularVendedor;
	private JFormattedTextField formattedTextFieldEmailVendedor;
	private JFormattedTextField formattedTextFieldCpfVendedor;
	private JFormattedTextField formattedTextFieldMetaMensalVendedor;
	private JTextField textFieldLoginVendedor;
	private JPasswordField passwordFieldSenhaVendedor;
	private JPasswordField passwordFieldConfirmarSenhaVendedor;

	private JTextField textFieldNomeProduto;
	private JFormattedTextField formattedTextFieldPrecoUnitarioProduto;
	private JFormattedTextField formattedTextFieldEstoqueMinimoProduto;
	private JFormattedTextField formattedTextFieldEstoqueProduto;

	/**
	 * Launch the application.
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JAdministrador frame = new JAdministrador();
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
	 * @throws
	 */

	public JAdministrador() {
		setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		setTitle("SYSTEM COMMERCE - Gerenciamento do Sistema - Administrador");
		setIconImage(Toolkit.getDefaultToolkit().getImage(JAdministrador.class.getResource("/img/logo.png")));
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 1400, 750);
		contentPaneAdministrador = new JPanel();
		contentPaneAdministrador.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		contentPaneAdministrador.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPaneAdministrador.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPaneAdministrador);

		JPanel panelNorth = new JPanel();
		panelNorth.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		contentPaneAdministrador.add(panelNorth, BorderLayout.NORTH);

		JLabel lblSystemCommerce = new JLabel("System Commerce");
		lblSystemCommerce.setIcon(new ImageIcon(JAdministrador.class.getResource("/img/logo.png")));
		lblSystemCommerce.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		panelNorth.add(lblSystemCommerce);

		JPanel panelCenter = new JPanel();
		panelCenter.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		contentPaneAdministrador.add(panelCenter, BorderLayout.CENTER);
		panelCenter.setLayout(new BorderLayout(0, 0));

		JTabbedPane tabbedPaneAdministrador = new JTabbedPane(JTabbedPane.TOP);
		tabbedPaneAdministrador.setBorder(new LineBorder(new Color(0, 0, 0)));
		tabbedPaneAdministrador.setFont(new Font("BankGothic Lt BT", Font.BOLD, 14));
		panelCenter.add(tabbedPaneAdministrador, BorderLayout.CENTER);

		JPanel panelCadastrarCliente = new JPanel();
		panelCadastrarCliente.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		tabbedPaneAdministrador.addTab("", new ImageIcon(new ImageIcon(JAdministrador.class.getResource("/img/adicionar-cliente.png")).getImage().getScaledInstance(60, 60, java.awt.Image.SCALE_SMOOTH)), panelCadastrarCliente, "Cadastrar Cliente");
		GridBagLayout gbl_panelCadastrarCliente = new GridBagLayout();
		gbl_panelCadastrarCliente.columnWidths = new int[]{0, 0, 0};
		gbl_panelCadastrarCliente.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panelCadastrarCliente.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panelCadastrarCliente.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		panelCadastrarCliente.setLayout(gbl_panelCadastrarCliente);

		// Label para aba de cadastrar cliente
		Icon iconAbaCadastrarCliente = new ImageIcon(new ImageIcon(JAdministrador.class.getResource("/img/adicionar-cliente.png")).getImage().getScaledInstance(60, 60, java.awt.Image.SCALE_SMOOTH));
		JLabel labelAbaCadastrarCliente = makeLabel(JLabel.BOTTOM, JLabel.CENTER, iconAbaCadastrarCliente, "Cadastrar Cliente");
		labelAbaCadastrarCliente.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tabbedPaneAdministrador.setTabComponentAt(0, labelAbaCadastrarCliente);

		JLabel lblNomeCliente = new JLabel("Nome*:");
		lblNomeCliente.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		GridBagConstraints gbc_lblNomeCliente = new GridBagConstraints();
		gbc_lblNomeCliente.fill = GridBagConstraints.VERTICAL;
		gbc_lblNomeCliente.anchor = GridBagConstraints.EAST;
		gbc_lblNomeCliente.insets = new Insets(0, 0, 5, 5);
		gbc_lblNomeCliente.gridx = 0;
		gbc_lblNomeCliente.gridy = 0;
		panelCadastrarCliente.add(lblNomeCliente, gbc_lblNomeCliente);

		textFieldNomeCliente = new JTextField();
		textFieldNomeCliente.setToolTipText("Campo obrigat\u00F3rio");
		textFieldNomeCliente.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		textFieldNomeCliente.setBorder(new LineBorder(new Color(0, 0, 0)));
		GridBagConstraints gbc_textFieldNomeCliente = new GridBagConstraints();
		gbc_textFieldNomeCliente.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldNomeCliente.fill = GridBagConstraints.BOTH;
		gbc_textFieldNomeCliente.gridx = 1;
		gbc_textFieldNomeCliente.gridy = 0;
		panelCadastrarCliente.add(textFieldNomeCliente, gbc_textFieldNomeCliente);
		textFieldNomeCliente.setColumns(10);

		JLabel lblTelefoneFixoCliente = new JLabel("Telefone Fixo:");
		lblTelefoneFixoCliente.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		GridBagConstraints gbc_lblTelefoneFixoCliente = new GridBagConstraints();
		gbc_lblTelefoneFixoCliente.fill = GridBagConstraints.VERTICAL;
		gbc_lblTelefoneFixoCliente.anchor = GridBagConstraints.EAST;
		gbc_lblTelefoneFixoCliente.insets = new Insets(0, 0, 5, 5);
		gbc_lblTelefoneFixoCliente.gridx = 0;
		gbc_lblTelefoneFixoCliente.gridy = 1;
		panelCadastrarCliente.add(lblTelefoneFixoCliente, gbc_lblTelefoneFixoCliente);

		formattedTextFieldTelefoneFixoCliente = new JFormattedTextField(gerarMascaraTelefoneFixo());
		formattedTextFieldTelefoneFixoCliente.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		formattedTextFieldTelefoneFixoCliente.setBorder(new LineBorder(new Color(0, 0, 0)));
		GridBagConstraints gbc_formattedTextFieldTelefoneFixoCliente = new GridBagConstraints();
		gbc_formattedTextFieldTelefoneFixoCliente.insets = new Insets(0, 0, 5, 0);
		gbc_formattedTextFieldTelefoneFixoCliente.fill = GridBagConstraints.BOTH;
		gbc_formattedTextFieldTelefoneFixoCliente.gridx = 1;
		gbc_formattedTextFieldTelefoneFixoCliente.gridy = 1;
		panelCadastrarCliente.add(formattedTextFieldTelefoneFixoCliente, gbc_formattedTextFieldTelefoneFixoCliente);

		JLabel lblTelefoneCelularCliente = new JLabel("Telefone Celular*:");
		lblTelefoneCelularCliente.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		GridBagConstraints gbc_lblTelefoneCelularCliente = new GridBagConstraints();
		gbc_lblTelefoneCelularCliente.fill = GridBagConstraints.VERTICAL;
		gbc_lblTelefoneCelularCliente.anchor = GridBagConstraints.EAST;
		gbc_lblTelefoneCelularCliente.insets = new Insets(0, 0, 5, 5);
		gbc_lblTelefoneCelularCliente.gridx = 0;
		gbc_lblTelefoneCelularCliente.gridy = 2;
		panelCadastrarCliente.add(lblTelefoneCelularCliente, gbc_lblTelefoneCelularCliente);

		formattedTextFieldTelefoneCelularCliente = new JFormattedTextField(gerarMascaraTelefoneCelular());
		formattedTextFieldTelefoneCelularCliente.setToolTipText("Campo obrigat\u00F3rio");
		formattedTextFieldTelefoneCelularCliente.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		formattedTextFieldTelefoneCelularCliente.setBorder(new LineBorder(new Color(0, 0, 0)));
		GridBagConstraints gbc_formattedTextFieldTelefoneCelularCliente = new GridBagConstraints();
		gbc_formattedTextFieldTelefoneCelularCliente.insets = new Insets(0, 0, 5, 0);
		gbc_formattedTextFieldTelefoneCelularCliente.fill = GridBagConstraints.BOTH;
		gbc_formattedTextFieldTelefoneCelularCliente.gridx = 1;
		gbc_formattedTextFieldTelefoneCelularCliente.gridy = 2;
		panelCadastrarCliente.add(formattedTextFieldTelefoneCelularCliente, gbc_formattedTextFieldTelefoneCelularCliente);

		JLabel lblEmailCliente = new JLabel("E-mail*:");
		lblEmailCliente.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		GridBagConstraints gbc_lblEmailCliente = new GridBagConstraints();
		gbc_lblEmailCliente.fill = GridBagConstraints.VERTICAL;
		gbc_lblEmailCliente.anchor = GridBagConstraints.EAST;
		gbc_lblEmailCliente.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmailCliente.gridx = 0;
		gbc_lblEmailCliente.gridy = 3;
		panelCadastrarCliente.add(lblEmailCliente, gbc_lblEmailCliente);

		formattedTextFieldEmailCliente = new JFormattedTextField();
		formattedTextFieldEmailCliente.setToolTipText("Campo obrigat\u00F3rio");
		formattedTextFieldEmailCliente.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		formattedTextFieldEmailCliente.setBorder(new LineBorder(new Color(0, 0, 0)));
		GridBagConstraints gbc_formattedTextFieldEmailCliente = new GridBagConstraints();
		gbc_formattedTextFieldEmailCliente.insets = new Insets(0, 0, 5, 0);
		gbc_formattedTextFieldEmailCliente.fill = GridBagConstraints.BOTH;
		gbc_formattedTextFieldEmailCliente.gridx = 1;
		gbc_formattedTextFieldEmailCliente.gridy = 3;
		panelCadastrarCliente.add(formattedTextFieldEmailCliente, gbc_formattedTextFieldEmailCliente);

		JLabel lblCpfCliente = new JLabel("CPF*:");
		lblCpfCliente.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		GridBagConstraints gbc_lblCpfCliente = new GridBagConstraints();
		gbc_lblCpfCliente.fill = GridBagConstraints.VERTICAL;
		gbc_lblCpfCliente.anchor = GridBagConstraints.EAST;
		gbc_lblCpfCliente.insets = new Insets(0, 0, 5, 5);
		gbc_lblCpfCliente.gridx = 0;
		gbc_lblCpfCliente.gridy = 4;
		panelCadastrarCliente.add(lblCpfCliente, gbc_lblCpfCliente);

		formattedTextFieldCpfCliente = new JFormattedTextField(gerarMascaraCPF());
		formattedTextFieldCpfCliente.setToolTipText("Campo obrigat\u00F3rio");
		formattedTextFieldCpfCliente.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		formattedTextFieldCpfCliente.setBorder(new LineBorder(new Color(0, 0, 0)));
		GridBagConstraints gbc_formattedTextFieldCpfCliente = new GridBagConstraints();
		gbc_formattedTextFieldCpfCliente.insets = new Insets(0, 0, 5, 0);
		gbc_formattedTextFieldCpfCliente.fill = GridBagConstraints.BOTH;
		gbc_formattedTextFieldCpfCliente.gridx = 1;
		gbc_formattedTextFieldCpfCliente.gridy = 4;
		panelCadastrarCliente.add(formattedTextFieldCpfCliente, gbc_formattedTextFieldCpfCliente);

		JLabel lblLimiteCreditoCliente = new JLabel("Limite de Cr\u00E9dito*:");
		lblLimiteCreditoCliente.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		GridBagConstraints gbc_lblLimiteCreditoCliente = new GridBagConstraints();
		gbc_lblLimiteCreditoCliente.fill = GridBagConstraints.VERTICAL;
		gbc_lblLimiteCreditoCliente.insets = new Insets(0, 0, 5, 5);
		gbc_lblLimiteCreditoCliente.anchor = GridBagConstraints.EAST;
		gbc_lblLimiteCreditoCliente.gridx = 0;
		gbc_lblLimiteCreditoCliente.gridy = 5;
		panelCadastrarCliente.add(lblLimiteCreditoCliente, gbc_lblLimiteCreditoCliente);

		formattedTextFieldLimiteCreditoCliente = new JMoneyField();
		formattedTextFieldLimiteCreditoCliente.setToolTipText("Campo obrigat\u00F3rio");
		formattedTextFieldLimiteCreditoCliente.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		formattedTextFieldLimiteCreditoCliente.setBorder(new LineBorder(new Color(0, 0, 0)));
		GridBagConstraints gbc_formattedTextFieldLimiteCreditoCliente = new GridBagConstraints();
		gbc_formattedTextFieldLimiteCreditoCliente.insets = new Insets(0, 0, 5, 0);
		gbc_formattedTextFieldLimiteCreditoCliente.fill = GridBagConstraints.BOTH;
		gbc_formattedTextFieldLimiteCreditoCliente.gridx = 1;
		gbc_formattedTextFieldLimiteCreditoCliente.gridy = 5;
		panelCadastrarCliente.add(formattedTextFieldLimiteCreditoCliente, gbc_formattedTextFieldLimiteCreditoCliente);

		JLabel lblLoginCliente = new JLabel("Login*:");
		lblLoginCliente.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		GridBagConstraints gbc_lblLoginCliente = new GridBagConstraints();
		gbc_lblLoginCliente.fill = GridBagConstraints.VERTICAL;
		gbc_lblLoginCliente.anchor = GridBagConstraints.EAST;
		gbc_lblLoginCliente.insets = new Insets(0, 0, 5, 5);
		gbc_lblLoginCliente.gridx = 0;
		gbc_lblLoginCliente.gridy = 6;
		panelCadastrarCliente.add(lblLoginCliente, gbc_lblLoginCliente);

		textFieldLoginCliente = new JTextField();
		textFieldLoginCliente.setToolTipText("Campo Obrigat\u00F3rio");
		textFieldLoginCliente.setBorder(new LineBorder(new Color(0, 0, 0)));
		textFieldLoginCliente.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		GridBagConstraints gbc_textFieldLoginCliente = new GridBagConstraints();
		gbc_textFieldLoginCliente.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldLoginCliente.fill = GridBagConstraints.BOTH;
		gbc_textFieldLoginCliente.gridx = 1;
		gbc_textFieldLoginCliente.gridy = 6;
		panelCadastrarCliente.add(textFieldLoginCliente, gbc_textFieldLoginCliente);
		textFieldLoginCliente.setColumns(10);

		JLabel lblSenhaCliente = new JLabel("Senha*:");
		lblSenhaCliente.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		GridBagConstraints gbc_lblSenhaCliente = new GridBagConstraints();
		gbc_lblSenhaCliente.fill = GridBagConstraints.VERTICAL;
		gbc_lblSenhaCliente.anchor = GridBagConstraints.EAST;
		gbc_lblSenhaCliente.insets = new Insets(0, 0, 5, 5);
		gbc_lblSenhaCliente.gridx = 0;
		gbc_lblSenhaCliente.gridy = 7;
		panelCadastrarCliente.add(lblSenhaCliente, gbc_lblSenhaCliente);

		passwordFieldSenhaCliente = new JPasswordField();
		passwordFieldSenhaCliente.setToolTipText("Campo Obrigat\u00F3rio");
		passwordFieldSenhaCliente.setEchoChar('*');
		passwordFieldSenhaCliente.setBorder(new LineBorder(new Color(0, 0, 0)));
		passwordFieldSenhaCliente.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		GridBagConstraints gbc_passwordFieldSenhaCliente = new GridBagConstraints();
		gbc_passwordFieldSenhaCliente.insets = new Insets(0, 0, 5, 0);
		gbc_passwordFieldSenhaCliente.fill = GridBagConstraints.BOTH;
		gbc_passwordFieldSenhaCliente.gridx = 1;
		gbc_passwordFieldSenhaCliente.gridy = 7;
		panelCadastrarCliente.add(passwordFieldSenhaCliente, gbc_passwordFieldSenhaCliente);

		JLabel lblConfirmarSenhaCliente = new JLabel("Confirmar Senha*:");
		lblConfirmarSenhaCliente.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		GridBagConstraints gbc_lblConfirmarSenhaCliente = new GridBagConstraints();
		gbc_lblConfirmarSenhaCliente.fill = GridBagConstraints.VERTICAL;
		gbc_lblConfirmarSenhaCliente.anchor = GridBagConstraints.EAST;
		gbc_lblConfirmarSenhaCliente.insets = new Insets(0, 0, 5, 5);
		gbc_lblConfirmarSenhaCliente.gridx = 0;
		gbc_lblConfirmarSenhaCliente.gridy = 8;
		panelCadastrarCliente.add(lblConfirmarSenhaCliente, gbc_lblConfirmarSenhaCliente);

		passwordFieldConfirmarSenhaCliente = new JPasswordField();
		passwordFieldConfirmarSenhaCliente.setToolTipText("Campo Obrigat\u00F3rio");
		passwordFieldConfirmarSenhaCliente.setEchoChar('*');
		passwordFieldConfirmarSenhaCliente.setBorder(new LineBorder(new Color(0, 0, 0)));
		passwordFieldConfirmarSenhaCliente.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		GridBagConstraints gbc_passwordFieldConfirmarSenhaCliente = new GridBagConstraints();
		gbc_passwordFieldConfirmarSenhaCliente.insets = new Insets(0, 0, 5, 0);
		gbc_passwordFieldConfirmarSenhaCliente.fill = GridBagConstraints.BOTH;
		gbc_passwordFieldConfirmarSenhaCliente.gridx = 1;
		gbc_passwordFieldConfirmarSenhaCliente.gridy = 8;
		panelCadastrarCliente.add(passwordFieldConfirmarSenhaCliente, gbc_passwordFieldConfirmarSenhaCliente);

		JButton btnCadastrarCliente = new JButton("Cadastrar");
		btnCadastrarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HashMap<String, String> hashMapCamposCliente = new HashMap<>();

				hashMapCamposCliente.put("Nome", textFieldNomeCliente.getText());
				hashMapCamposCliente.put("TelefoneFixo", formattedTextFieldTelefoneFixoCliente.getText());
				hashMapCamposCliente.put("TelefoneCelular", formattedTextFieldTelefoneCelularCliente.getText());
				hashMapCamposCliente.put("Email", formattedTextFieldEmailCliente.getText());
				hashMapCamposCliente.put("Cpf", formattedTextFieldCpfCliente.getText());
				hashMapCamposCliente.put("LimiteCredito", formattedTextFieldLimiteCreditoCliente.getText());
				hashMapCamposCliente.put("Login", textFieldLoginCliente.getText());
				hashMapCamposCliente.put("Senha", new String(passwordFieldSenhaCliente.getPassword()));
				hashMapCamposCliente.put("ConfirmarSenha", new String(passwordFieldConfirmarSenhaCliente.getPassword()));

				HashMap<String, Boolean> hashMapCamposClienteValidados = Comercial.validarCliente(hashMapCamposCliente);

				if (hashMapCamposClienteValidados.get("Nome") == false)
					exibirBalaoAviso(textFieldNomeCliente, "O nome não foi preenchido.");

				if (hashMapCamposClienteValidados.get("TelefoneFixo") == false) {
					hashMapCamposCliente.put("TelefoneFixo", "");
					hashMapCamposClienteValidados.put("TelefoneFixo", true);
				}

				if (hashMapCamposClienteValidados.get("TelefoneCelular") == false)
					exibirBalaoAviso(formattedTextFieldTelefoneCelularCliente, "O telefone celular não foi preenchido.");

				if (hashMapCamposClienteValidados.get("Email") == false)
					exibirBalaoAviso(formattedTextFieldEmailCliente, "E-mail inválido.");

				if (hashMapCamposClienteValidados.get("Cpf") == false)
					exibirBalaoAviso(formattedTextFieldCpfCliente, "CPF inválido.");

				if (hashMapCamposClienteValidados.get("LimiteCredito") == false)
					exibirBalaoAviso(formattedTextFieldLimiteCreditoCliente, "O limite de crédito não foi preenchido.");

				if (hashMapCamposClienteValidados.get("Login") == false)
					exibirBalaoAviso(textFieldLoginCliente, "O login não foi preenchido adequadamente (mínimo 4 caracteres).");

				if (hashMapCamposClienteValidados.get("Senha") == false)
					exibirBalaoAviso(passwordFieldSenhaCliente, "A senha não foi peenchida adequadamente (mínimo 4 caracteres).");

				if (hashMapCamposClienteValidados.get("ConfirmarSenha") == false)
					exibirBalaoAviso(passwordFieldConfirmarSenhaCliente, "Confirmação de senha inválida.");

				if (Collections.frequency(hashMapCamposClienteValidados.values(), true) == hashMapCamposClienteValidados.size()) {
					try {
						boolean cpfRegistrado = Comercial.cpfRegistrado(hashMapCamposCliente.get("Cpf"));
						boolean loginRegistrado = Comercial.loginRegistrado(hashMapCamposCliente.get("Login"));
						String mensagem = null;

						if (cpfRegistrado)
							mensagem = "O CPF já está cadastrado.";

						if (loginRegistrado)
							mensagem = "O login já está cadastrado.";

						if (cpfRegistrado && loginRegistrado)
							mensagem = "O CPF e o login já estão cadastrados.";

						if (!cpfRegistrado && !loginRegistrado) {
							Comercial.inserirCliente(hashMapCamposCliente);
							JOptionPane.showMessageDialog(null, "Cliente cadastrado no banco de dados.", "CADASTRO REALIZADO", JOptionPane.INFORMATION_MESSAGE);
							limparCampos(tabbedPaneAdministrador.getSelectedIndex());
						} else {
							JOptionPane.showMessageDialog(null, mensagem, "FALHA AO CADASTRAR", JOptionPane.WARNING_MESSAGE);
						}
					} catch (HibernateException hibernateException) {
						JOptionPane.showMessageDialog(null, "Erro ao cadastrar cliente no banco de dados.\nErro: " + hibernateException.getMessage(), "ERRO AO CADASTRAR", JOptionPane.ERROR_MESSAGE);
					} catch (Exception exception) {
						JOptionPane.showMessageDialog(null, "Erro ao cadastrar cliente no banco de dados.\nErro: " + exception.getMessage(), "ERRO AO CADASTRAR", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnCadastrarCliente.setIcon(new ImageIcon(new ImageIcon(JAdministrador.class.getResource("/img/adicionar-cliente.png")).getImage().getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH)));
		btnCadastrarCliente.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCadastrarCliente.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		GridBagConstraints gbc_btnCadastrarCliente = new GridBagConstraints();
		gbc_btnCadastrarCliente.anchor = GridBagConstraints.SOUTHEAST;
		gbc_btnCadastrarCliente.gridx = 1;
		gbc_btnCadastrarCliente.gridy = 9;
		panelCadastrarCliente.add(btnCadastrarCliente, gbc_btnCadastrarCliente);

		panelVisualizarClientes = new JPanel();
		panelVisualizarClientes.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		tabbedPaneAdministrador.addTab("", new ImageIcon(new ImageIcon(JAdministrador.class.getResource("/img/visualizar-clientes.png")).getImage().getScaledInstance(60, 60, java.awt.Image.SCALE_SMOOTH)), panelVisualizarClientes, "Visualizar Clientes");
		panelVisualizarClientes.setLayout(null);

		// Label para aba de visualizar clientes
		Icon iconAbaVisualizarClientes = new ImageIcon(new ImageIcon(JAdministrador.class.getResource("/img/visualizar-clientes.png")).getImage().getScaledInstance(60, 60, java.awt.Image.SCALE_SMOOTH));
		JLabel labelAbaVisualizarClientes = makeLabel(JLabel.BOTTOM, JLabel.CENTER, iconAbaVisualizarClientes, "Visualizar Clientes");
		labelAbaVisualizarClientes.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tabbedPaneAdministrador.setTabComponentAt(1, labelAbaVisualizarClientes);

		JScrollPane scrollPaneVisualizarClientes = new JScrollPane();
		scrollPaneVisualizarClientes.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		scrollPaneVisualizarClientes.setBounds(0, 0, 1379, 234);
		panelVisualizarClientes.add(scrollPaneVisualizarClientes);

		tableVisualizarClientes = new JTable() {
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
				case 5:
					return Object.class;
				case 6:
					return Object.class;
				case 7:
					return Object.class;
				case 8:
					return Object.class;
				case 9:
					return Object.class;
				case 10:
					return Object.class;
				case 11:
					return Object.class;
				default:
					return Object.class;
				}
			}
		};
		tableVisualizarClientes.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tableVisualizarClientes.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Selecione", "C\u00F3digo", "Nome", "Telefone Fixo", "Telefone Celular", "E-mail", "Data de Cadastro", "CPF", "Limite de Cr\u00E9dito", "Login", "Senha"
				}
				) {
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
					true, false, false, false, false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tableVisualizarClientes.getColumnModel().getColumn(0).setResizable(false);
		tableVisualizarClientes.getColumnModel().getColumn(0).setPreferredWidth(100);
		tableVisualizarClientes.getColumnModel().getColumn(1).setResizable(false);
		tableVisualizarClientes.getColumnModel().getColumn(2).setPreferredWidth(135);
		tableVisualizarClientes.getColumnModel().getColumn(3).setPreferredWidth(135);
		tableVisualizarClientes.getColumnModel().getColumn(4).setPreferredWidth(135);
		tableVisualizarClientes.getColumnModel().getColumn(5).setPreferredWidth(185);
		tableVisualizarClientes.getColumnModel().getColumn(6).setPreferredWidth(200);
		tableVisualizarClientes.getColumnModel().getColumn(7).setPreferredWidth(150);
		tableVisualizarClientes.getColumnModel().getColumn(8).setPreferredWidth(120);
		tableVisualizarClientes.getColumnModel().getColumn(9).setPreferredWidth(120);
		tableVisualizarClientes.getColumnModel().getColumn(10).setPreferredWidth(120);
		tableVisualizarClientes.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 12));
		tableVisualizarClientes.getTableHeader().setReorderingAllowed(false);
		scrollPaneVisualizarClientes.setViewportView(tableVisualizarClientes);

		// Alinhamento horizontal e fonte: cabeçalho da tabela de clientes
		((JLabel) tableVisualizarClientes.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
		tableVisualizarClientes.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));

		// Cor intercalada nas linhas: tabela de clientes
		gerenciarComportamentoTabela(tableVisualizarClientes);

		JButton btnExcluirClientes = new JButton("Excluir");
		btnExcluirClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ArrayList<Integer> arrayListCodigoCliente = new ArrayList<>();

				for(int i = 0 ; i < tableVisualizarClientes.getRowCount(); i++) {
					Boolean checked = Boolean.valueOf(tableVisualizarClientes.getValueAt(i, 0).toString());
					Integer checkedCodigo = Integer.parseInt(tableVisualizarClientes.getValueAt(i, 1).toString());

					if(checked)
						arrayListCodigoCliente.add(checkedCodigo);
				}

				if (arrayListCodigoCliente.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Não há nenhum cliente selecionado para excluir.", "FALHA AO EXCLUIR", JOptionPane.WARNING_MESSAGE);
				} else {
					try {
						Comercial.excluirCliente(arrayListCodigoCliente);
						exibirClientes();
						JOptionPane.showMessageDialog(null, "Cliente(s) excluído(s) do banco de dados.", "EXCLUSÃO REALIZADA", JOptionPane.INFORMATION_MESSAGE);
					} catch (SisComException sisComException) {
						JOptionPane.showMessageDialog(null, sisComException.getMensagemErro(), "ERRO AO EXCLUIR", JOptionPane.WARNING_MESSAGE);
					} catch (HibernateException hibernateException) {
						JOptionPane.showMessageDialog(null, "Erro ao excluir cliente(s).\nErro: " + hibernateException.getMessage(), "ERRO AO EXCLUIR", JOptionPane.ERROR_MESSAGE);
					} catch (Exception exception) {
						JOptionPane.showMessageDialog(null, "Erro ao excluir cliente(s).\nErro: " + exception.getMessage(), "ERRO AO EXCLUIR", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnExcluirClientes.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnExcluirClientes.setIcon(new ImageIcon(new ImageIcon(JAdministrador.class.getResource("/img/excluir.png")).getImage().getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH)));
		btnExcluirClientes.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		btnExcluirClientes.setBounds(1027, 263, 171, 49);
		panelVisualizarClientes.add(btnExcluirClientes);

		JButton btnVisualizarClientes = new JButton("Visualizar");
		btnVisualizarClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				exibirClientes();
			}
		});
		btnVisualizarClientes.setIcon(new ImageIcon(new ImageIcon(JAdministrador.class.getResource("/img/visualizar-clientes.png")).getImage().getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH)));
		btnVisualizarClientes.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		btnVisualizarClientes.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnVisualizarClientes.setBounds(1208, 263, 171, 49);
		panelVisualizarClientes.add(btnVisualizarClientes);

		JLabel lblCpfClienteBusca = new JLabel("CPF para Busca:");
		lblCpfClienteBusca.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		lblCpfClienteBusca.setBounds(0, 277, 180, 20);
		panelVisualizarClientes.add(lblCpfClienteBusca);

		formattedTextFieldCpfClienteBusca = new JFormattedTextField(gerarMascaraCPF());
		formattedTextFieldCpfClienteBusca.setBorder(new LineBorder(Color.BLACK));
		formattedTextFieldCpfClienteBusca.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		formattedTextFieldCpfClienteBusca.setBounds(195, 275, 200, 26);
		panelVisualizarClientes.add(formattedTextFieldCpfClienteBusca);

		JButton btnConsultarCliente = new JButton("Consultar");
		btnConsultarCliente.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnConsultarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Cliente cliente = Comercial.consultarCliente(formattedTextFieldCpfClienteBusca.getText());

					if (cliente != null) {
						DefaultTableModel defaultTableModel = (DefaultTableModel) tableVisualizarClientes.getModel();
						Object rowData[] = new Object[11];

						defaultTableModel.setRowCount(0);
						rowData[0] = false;
						rowData[1] = cliente.getCodigo();
						rowData[2] = cliente.getNome();
						rowData[3] = cliente.getTelefoneFixo();
						rowData[4] = cliente.getTelefoneCelular();
						rowData[5] = cliente.getEmail();
						rowData[6] = cliente.getDataCad();
						rowData[7] = cliente.getCpf();
						rowData[8] = cliente.getLimiteCredito();
						rowData[9] = cliente.getLogin();
						rowData[10] = cliente.getSenha();
						defaultTableModel.addRow(rowData);
					} else {
						JOptionPane.showMessageDialog(null, "Cliente não encontrado.", "FALHA AO BUSCAR", JOptionPane.WARNING_MESSAGE);
					}
				} catch (HibernateException hibernateException) {
					JOptionPane.showMessageDialog(null, "Erro ao buscar cliente.\n" + hibernateException.getMessage(), "ERRO AO BUSCAR", JOptionPane.ERROR_MESSAGE);
				} catch (Exception exception) {
					JOptionPane.showMessageDialog(null, "Erro ao buscar cliente.\n" + exception.getMessage(), "ERRO AO BUSCAR", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnConsultarCliente.setIcon(new ImageIcon(new ImageIcon(JAdministrador.class.getResource("/img/consultar.png")).getImage().getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH)));
		btnConsultarCliente.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		btnConsultarCliente.setBounds(410, 263, 171, 49);
		panelVisualizarClientes.add(btnConsultarCliente);

		JPanel panelCadastrarFornecedor = new JPanel();
		panelCadastrarFornecedor.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		tabbedPaneAdministrador.addTab("", new ImageIcon(new ImageIcon(JAdministrador.class.getResource("/img/adicionar-fornecedor.png")).getImage().getScaledInstance(60, 60, java.awt.Image.SCALE_SMOOTH)), panelCadastrarFornecedor, "Cadastrar Fornecedor");
		GridBagLayout gbl_panelCadastrarFornecedor = new GridBagLayout();
		gbl_panelCadastrarFornecedor.columnWidths = new int[]{0, 0, 0};
		gbl_panelCadastrarFornecedor.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panelCadastrarFornecedor.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panelCadastrarFornecedor.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		panelCadastrarFornecedor.setLayout(gbl_panelCadastrarFornecedor);

		// Label para aba de cadastrar fornecedor
		Icon iconAbaCadastrarFornecedor = new ImageIcon(new ImageIcon(JAdministrador.class.getResource("/img/adicionar-fornecedor.png")).getImage().getScaledInstance(60, 60, java.awt.Image.SCALE_SMOOTH));
		JLabel labelAbaCadastrarFornecedor = makeLabel(JLabel.BOTTOM, JLabel.CENTER, iconAbaCadastrarFornecedor, "Cadastrar Fornecedor");
		labelAbaCadastrarFornecedor.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tabbedPaneAdministrador.setTabComponentAt(2, labelAbaCadastrarFornecedor);

		JLabel lblNomeFornecedor = new JLabel("Nome*:");
		lblNomeFornecedor.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		GridBagConstraints gbc_lblNomeFornecedor = new GridBagConstraints();
		gbc_lblNomeFornecedor.fill = GridBagConstraints.VERTICAL;
		gbc_lblNomeFornecedor.anchor = GridBagConstraints.EAST;
		gbc_lblNomeFornecedor.insets = new Insets(0, 0, 5, 5);
		gbc_lblNomeFornecedor.gridx = 0;
		gbc_lblNomeFornecedor.gridy = 0;
		panelCadastrarFornecedor.add(lblNomeFornecedor, gbc_lblNomeFornecedor);

		textFieldNomeFornecedor = new JTextField();
		textFieldNomeFornecedor.setBorder(new LineBorder(new Color(0, 0, 0)));
		textFieldNomeFornecedor.setToolTipText("Campo obrigat\u00F3rio");
		textFieldNomeFornecedor.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		GridBagConstraints gbc_textFieldNomeFornecedor = new GridBagConstraints();
		gbc_textFieldNomeFornecedor.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldNomeFornecedor.fill = GridBagConstraints.BOTH;
		gbc_textFieldNomeFornecedor.gridx = 1;
		gbc_textFieldNomeFornecedor.gridy = 0;
		panelCadastrarFornecedor.add(textFieldNomeFornecedor, gbc_textFieldNomeFornecedor);
		textFieldNomeFornecedor.setColumns(10);

		JLabel lblTelefoneFixoFornecedor = new JLabel("Telefone Fixo:");
		lblTelefoneFixoFornecedor.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		GridBagConstraints gbc_lblTelefoneFixoFornecedor = new GridBagConstraints();
		gbc_lblTelefoneFixoFornecedor.fill = GridBagConstraints.VERTICAL;
		gbc_lblTelefoneFixoFornecedor.anchor = GridBagConstraints.EAST;
		gbc_lblTelefoneFixoFornecedor.insets = new Insets(0, 0, 5, 5);
		gbc_lblTelefoneFixoFornecedor.gridx = 0;
		gbc_lblTelefoneFixoFornecedor.gridy = 1;
		panelCadastrarFornecedor.add(lblTelefoneFixoFornecedor, gbc_lblTelefoneFixoFornecedor);

		formattedTextFieldTelefoneFixoFornecedor = new JFormattedTextField(gerarMascaraTelefoneFixo());
		formattedTextFieldTelefoneFixoFornecedor.setBorder(new LineBorder(new Color(0, 0, 0)));
		formattedTextFieldTelefoneFixoFornecedor.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		GridBagConstraints gbc_formattedTextFieldTelefoneFixoFornecedor = new GridBagConstraints();
		gbc_formattedTextFieldTelefoneFixoFornecedor.insets = new Insets(0, 0, 5, 0);
		gbc_formattedTextFieldTelefoneFixoFornecedor.fill = GridBagConstraints.BOTH;
		gbc_formattedTextFieldTelefoneFixoFornecedor.gridx = 1;
		gbc_formattedTextFieldTelefoneFixoFornecedor.gridy = 1;
		panelCadastrarFornecedor.add(formattedTextFieldTelefoneFixoFornecedor, gbc_formattedTextFieldTelefoneFixoFornecedor);

		JLabel lblTelefoneCelularFornecedor = new JLabel("Telefone Celular*:");
		lblTelefoneCelularFornecedor.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		GridBagConstraints gbc_lblTelefoneCelularFornecedor = new GridBagConstraints();
		gbc_lblTelefoneCelularFornecedor.fill = GridBagConstraints.VERTICAL;
		gbc_lblTelefoneCelularFornecedor.anchor = GridBagConstraints.EAST;
		gbc_lblTelefoneCelularFornecedor.insets = new Insets(0, 0, 5, 5);
		gbc_lblTelefoneCelularFornecedor.gridx = 0;
		gbc_lblTelefoneCelularFornecedor.gridy = 2;
		panelCadastrarFornecedor.add(lblTelefoneCelularFornecedor, gbc_lblTelefoneCelularFornecedor);

		formattedTextFieldTelefoneCelularFornecedor = new JFormattedTextField(gerarMascaraTelefoneCelular());
		formattedTextFieldTelefoneCelularFornecedor.setToolTipText("Campo obrigat\u00F3rio");
		formattedTextFieldTelefoneCelularFornecedor.setBorder(new LineBorder(new Color(0, 0, 0)));
		formattedTextFieldTelefoneCelularFornecedor.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		GridBagConstraints gbc_formattedTextFieldTelefoneCelularFornecedor = new GridBagConstraints();
		gbc_formattedTextFieldTelefoneCelularFornecedor.insets = new Insets(0, 0, 5, 0);
		gbc_formattedTextFieldTelefoneCelularFornecedor.fill = GridBagConstraints.BOTH;
		gbc_formattedTextFieldTelefoneCelularFornecedor.gridx = 1;
		gbc_formattedTextFieldTelefoneCelularFornecedor.gridy = 2;
		panelCadastrarFornecedor.add(formattedTextFieldTelefoneCelularFornecedor, gbc_formattedTextFieldTelefoneCelularFornecedor);

		JLabel lblEmailFornecedor = new JLabel("E-mail*:");
		lblEmailFornecedor.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		GridBagConstraints gbc_lblEmailFornecedor = new GridBagConstraints();
		gbc_lblEmailFornecedor.fill = GridBagConstraints.VERTICAL;
		gbc_lblEmailFornecedor.anchor = GridBagConstraints.EAST;
		gbc_lblEmailFornecedor.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmailFornecedor.gridx = 0;
		gbc_lblEmailFornecedor.gridy = 3;
		panelCadastrarFornecedor.add(lblEmailFornecedor, gbc_lblEmailFornecedor);

		formattedTextFieldEmailFornecedor = new JFormattedTextField();
		formattedTextFieldEmailFornecedor.setToolTipText("Campo obrigat\u00F3rio");
		formattedTextFieldEmailFornecedor.setBorder(new LineBorder(new Color(0, 0, 0)));
		formattedTextFieldEmailFornecedor.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		GridBagConstraints gbc_formattedTextFieldEmailFornecedor = new GridBagConstraints();
		gbc_formattedTextFieldEmailFornecedor.insets = new Insets(0, 0, 5, 0);
		gbc_formattedTextFieldEmailFornecedor.fill = GridBagConstraints.BOTH;
		gbc_formattedTextFieldEmailFornecedor.gridx = 1;
		gbc_formattedTextFieldEmailFornecedor.gridy = 3;
		panelCadastrarFornecedor.add(formattedTextFieldEmailFornecedor, gbc_formattedTextFieldEmailFornecedor);

		JLabel lblCnpjFornecedor = new JLabel("CPNJ*:");
		lblCnpjFornecedor.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		GridBagConstraints gbc_lblCnpjFornecedor = new GridBagConstraints();
		gbc_lblCnpjFornecedor.fill = GridBagConstraints.VERTICAL;
		gbc_lblCnpjFornecedor.anchor = GridBagConstraints.EAST;
		gbc_lblCnpjFornecedor.insets = new Insets(0, 0, 5, 5);
		gbc_lblCnpjFornecedor.gridx = 0;
		gbc_lblCnpjFornecedor.gridy = 4;
		panelCadastrarFornecedor.add(lblCnpjFornecedor, gbc_lblCnpjFornecedor);

		formattedTextFieldCnpjFornecedor = new JFormattedTextField(gerarMascaraCNPJ());
		formattedTextFieldCnpjFornecedor.setToolTipText("Campo obrigat\u00F3rio");
		formattedTextFieldCnpjFornecedor.setBorder(new LineBorder(new Color(0, 0, 0)));
		formattedTextFieldCnpjFornecedor.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		GridBagConstraints gbc_formattedTextFieldCnpjFornecedor = new GridBagConstraints();
		gbc_formattedTextFieldCnpjFornecedor.insets = new Insets(0, 0, 5, 0);
		gbc_formattedTextFieldCnpjFornecedor.fill = GridBagConstraints.BOTH;
		gbc_formattedTextFieldCnpjFornecedor.gridx = 1;
		gbc_formattedTextFieldCnpjFornecedor.gridy = 4;
		panelCadastrarFornecedor.add(formattedTextFieldCnpjFornecedor, gbc_formattedTextFieldCnpjFornecedor);

		JLabel lblNomeContatoFornecedor = new JLabel("Nome do Contato*:");
		lblNomeContatoFornecedor.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		GridBagConstraints gbc_lblNomeContatoFornecedor = new GridBagConstraints();
		gbc_lblNomeContatoFornecedor.fill = GridBagConstraints.VERTICAL;
		gbc_lblNomeContatoFornecedor.anchor = GridBagConstraints.EAST;
		gbc_lblNomeContatoFornecedor.insets = new Insets(0, 0, 5, 5);
		gbc_lblNomeContatoFornecedor.gridx = 0;
		gbc_lblNomeContatoFornecedor.gridy = 5;
		panelCadastrarFornecedor.add(lblNomeContatoFornecedor, gbc_lblNomeContatoFornecedor);

		textFieldNomeContatoFornecedor = new JTextField();
		textFieldNomeContatoFornecedor.setBorder(new LineBorder(new Color(0, 0, 0)));
		textFieldNomeContatoFornecedor.setToolTipText("Campo obrigat\u00F3rio");
		textFieldNomeContatoFornecedor.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		GridBagConstraints gbc_textFieldNomeContatoFornecedor = new GridBagConstraints();
		gbc_textFieldNomeContatoFornecedor.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldNomeContatoFornecedor.fill = GridBagConstraints.BOTH;
		gbc_textFieldNomeContatoFornecedor.gridx = 1;
		gbc_textFieldNomeContatoFornecedor.gridy = 5;
		panelCadastrarFornecedor.add(textFieldNomeContatoFornecedor, gbc_textFieldNomeContatoFornecedor);
		textFieldNomeContatoFornecedor.setColumns(10);

		JLabel lblLoginFornecedor = new JLabel("Login*:");
		lblLoginFornecedor.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		GridBagConstraints gbc_lblLoginFornecedor = new GridBagConstraints();
		gbc_lblLoginFornecedor.fill = GridBagConstraints.VERTICAL;
		gbc_lblLoginFornecedor.anchor = GridBagConstraints.EAST;
		gbc_lblLoginFornecedor.insets = new Insets(0, 0, 5, 5);
		gbc_lblLoginFornecedor.gridx = 0;
		gbc_lblLoginFornecedor.gridy = 6;
		panelCadastrarFornecedor.add(lblLoginFornecedor, gbc_lblLoginFornecedor);

		textFieldLoginFornecedor = new JTextField();
		textFieldLoginFornecedor.setToolTipText("Campo Obrigat\u00F3rio");
		textFieldLoginFornecedor.setBorder(new LineBorder(new Color(0, 0, 0)));
		textFieldLoginFornecedor.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		GridBagConstraints gbc_textFieldLoginFornecedor = new GridBagConstraints();
		gbc_textFieldLoginFornecedor.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldLoginFornecedor.fill = GridBagConstraints.BOTH;
		gbc_textFieldLoginFornecedor.gridx = 1;
		gbc_textFieldLoginFornecedor.gridy = 6;
		panelCadastrarFornecedor.add(textFieldLoginFornecedor, gbc_textFieldLoginFornecedor);
		textFieldLoginFornecedor.setColumns(10);

		JLabel lblSenhaFornecedor = new JLabel("Senha*:");
		lblSenhaFornecedor.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		GridBagConstraints gbc_lblSenhaFornecedor = new GridBagConstraints();
		gbc_lblSenhaFornecedor.fill = GridBagConstraints.VERTICAL;
		gbc_lblSenhaFornecedor.anchor = GridBagConstraints.EAST;
		gbc_lblSenhaFornecedor.insets = new Insets(0, 0, 5, 5);
		gbc_lblSenhaFornecedor.gridx = 0;
		gbc_lblSenhaFornecedor.gridy = 7;
		panelCadastrarFornecedor.add(lblSenhaFornecedor, gbc_lblSenhaFornecedor);

		passwordFieldSenhaFornecedor = new JPasswordField();
		passwordFieldSenhaFornecedor.setToolTipText("Campo Obrigat\u00F3rio");
		passwordFieldSenhaFornecedor.setBorder(new LineBorder(new Color(0, 0, 0)));
		passwordFieldSenhaFornecedor.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		passwordFieldSenhaFornecedor.setEchoChar('*');
		GridBagConstraints gbc_passwordFieldSenhaFornecedor = new GridBagConstraints();
		gbc_passwordFieldSenhaFornecedor.insets = new Insets(0, 0, 5, 0);
		gbc_passwordFieldSenhaFornecedor.fill = GridBagConstraints.BOTH;
		gbc_passwordFieldSenhaFornecedor.gridx = 1;
		gbc_passwordFieldSenhaFornecedor.gridy = 7;
		panelCadastrarFornecedor.add(passwordFieldSenhaFornecedor, gbc_passwordFieldSenhaFornecedor);

		JLabel lblConfirmarSenhaFornecedor = new JLabel("Confirmar Senha*:");
		lblConfirmarSenhaFornecedor.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		GridBagConstraints gbc_lblConfirmarSenhaFornecedor = new GridBagConstraints();
		gbc_lblConfirmarSenhaFornecedor.fill = GridBagConstraints.VERTICAL;
		gbc_lblConfirmarSenhaFornecedor.anchor = GridBagConstraints.EAST;
		gbc_lblConfirmarSenhaFornecedor.insets = new Insets(0, 0, 5, 5);
		gbc_lblConfirmarSenhaFornecedor.gridx = 0;
		gbc_lblConfirmarSenhaFornecedor.gridy = 8;
		panelCadastrarFornecedor.add(lblConfirmarSenhaFornecedor, gbc_lblConfirmarSenhaFornecedor);

		passwordFieldConfirmarSenhaFornecedor = new JPasswordField();
		passwordFieldConfirmarSenhaFornecedor.setToolTipText("Campo Obrigat\u00F3rio");
		passwordFieldConfirmarSenhaFornecedor.setBorder(new LineBorder(new Color(0, 0, 0)));
		passwordFieldConfirmarSenhaFornecedor.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		passwordFieldConfirmarSenhaFornecedor.setEchoChar('*');
		GridBagConstraints gbc_passwordFieldConfirmarSenhaFornecedor = new GridBagConstraints();
		gbc_passwordFieldConfirmarSenhaFornecedor.insets = new Insets(0, 0, 5, 0);
		gbc_passwordFieldConfirmarSenhaFornecedor.fill = GridBagConstraints.BOTH;
		gbc_passwordFieldConfirmarSenhaFornecedor.gridx = 1;
		gbc_passwordFieldConfirmarSenhaFornecedor.gridy = 8;
		panelCadastrarFornecedor.add(passwordFieldConfirmarSenhaFornecedor, gbc_passwordFieldConfirmarSenhaFornecedor);

		JButton btnCadastrarFornecedor = new JButton("Cadastrar");
		btnCadastrarFornecedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				HashMap<String, String> hashMapCamposFornecedor = new HashMap<>();

				hashMapCamposFornecedor.put("Nome", textFieldNomeFornecedor.getText());
				hashMapCamposFornecedor.put("TelefoneFixo", formattedTextFieldTelefoneFixoFornecedor.getText());
				hashMapCamposFornecedor.put("TelefoneCelular", formattedTextFieldTelefoneCelularFornecedor.getText());
				hashMapCamposFornecedor.put("Email", formattedTextFieldEmailFornecedor.getText());
				hashMapCamposFornecedor.put("Cnpj", formattedTextFieldCnpjFornecedor.getText());
				hashMapCamposFornecedor.put("NomeContato", textFieldNomeContatoFornecedor.getText());
				hashMapCamposFornecedor.put("Login", textFieldLoginFornecedor.getText());
				hashMapCamposFornecedor.put("Senha", new String(passwordFieldSenhaFornecedor.getPassword()));
				hashMapCamposFornecedor.put("ConfirmarSenha", new String(passwordFieldConfirmarSenhaFornecedor.getPassword()));

				HashMap<String, Boolean> hashMapCamposFornecedorValidados = Comercial.validarFornecedor(hashMapCamposFornecedor);

				if (hashMapCamposFornecedorValidados.get("Nome") == false)
					exibirBalaoAviso(textFieldNomeFornecedor, "O nome não foi preenchido.");

				if (hashMapCamposFornecedorValidados.get("TelefoneFixo") == false) {
					hashMapCamposFornecedor.put("TelefoneFixo", "");
					hashMapCamposFornecedorValidados.put("TelefoneFixo", true);
				}

				if (hashMapCamposFornecedorValidados.get("TelefoneCelular") == false)
					exibirBalaoAviso(formattedTextFieldTelefoneCelularFornecedor, "O telefone celular não foi preenchido.");

				if (hashMapCamposFornecedorValidados.get("Email") == false)
					exibirBalaoAviso(formattedTextFieldEmailFornecedor, "E-mail inválido.");

				if (hashMapCamposFornecedorValidados.get("Cnpj") == false)
					exibirBalaoAviso(formattedTextFieldCnpjFornecedor, "CNPJ inválido.");

				if (hashMapCamposFornecedorValidados.get("NomeContato") == false)
					exibirBalaoAviso(textFieldNomeContatoFornecedor, "O nome do contato não foi preenchido.");

				if (hashMapCamposFornecedorValidados.get("Login") == false)
					exibirBalaoAviso(textFieldLoginFornecedor, "O login não foi preenchido adequadamente (mínimo 4 caracteres).");

				if (hashMapCamposFornecedorValidados.get("Senha") == false)
					exibirBalaoAviso(passwordFieldSenhaFornecedor, "A senha não foi peenchida adequadamente (mínimo 4 caracteres).");

				if (hashMapCamposFornecedorValidados.get("ConfirmarSenha") == false)
					exibirBalaoAviso(passwordFieldConfirmarSenhaFornecedor, "Confirmação de senha inválida.");

				if (Collections.frequency(hashMapCamposFornecedorValidados.values(), true) == hashMapCamposFornecedorValidados.size()) {
					try {
						boolean cnpjRegistrado = Comercial.cnpjRegistrado(hashMapCamposFornecedor.get("Cnpj"));
						boolean loginRegistrado = Comercial.loginRegistrado(hashMapCamposFornecedor.get("Login"));
						String mensagem = null;

						if (cnpjRegistrado)
							mensagem = "O CNPJ já está cadastrado.";

						if (loginRegistrado)
							mensagem = "O login já está cadastrado.";

						if (cnpjRegistrado && loginRegistrado)
							mensagem = "O CNPJ e o login já estão cadastrados.";

						if (!cnpjRegistrado && !loginRegistrado) {
							Comercial.inserirFornecedor(hashMapCamposFornecedor);
							JOptionPane.showMessageDialog(null, "Fornecedor cadastrado no banco de dados.", "CADASTRO REALIZADO", JOptionPane.INFORMATION_MESSAGE);
							limparCampos(tabbedPaneAdministrador.getSelectedIndex());
						} else {
							JOptionPane.showMessageDialog(null, mensagem, "FALHA AO CADASTRAR", JOptionPane.WARNING_MESSAGE);
						}
					} catch (HibernateException hibernateException) {
						JOptionPane.showMessageDialog(null, "Erro ao cadastrar fornecedor no banco de dados.\nErro: " + hibernateException.getMessage(), "ERRO AO CADASTRAR", JOptionPane.ERROR_MESSAGE);
					} catch (Exception exception) {
						JOptionPane.showMessageDialog(null, "Erro ao cadastrar fornecedor no banco de dados.\nErro:" + exception.getMessage(), "ERRO AO CADASTRAR", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnCadastrarFornecedor.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCadastrarFornecedor.setIcon(new ImageIcon(new ImageIcon(JAdministrador.class.getResource("/img/adicionar-fornecedor.png")).getImage().getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH)));
		btnCadastrarFornecedor.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		GridBagConstraints gbc_btnCadastrarFornecedor = new GridBagConstraints();
		gbc_btnCadastrarFornecedor.anchor = GridBagConstraints.SOUTHEAST;
		gbc_btnCadastrarFornecedor.gridx = 1;
		gbc_btnCadastrarFornecedor.gridy = 9;
		panelCadastrarFornecedor.add(btnCadastrarFornecedor, gbc_btnCadastrarFornecedor);

		panelVisualizarFornecedores = new JPanel();
		panelVisualizarFornecedores.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		tabbedPaneAdministrador.addTab("", new ImageIcon(new ImageIcon(JAdministrador.class.getResource("/img/visualizar-fornecedores.png")).getImage().getScaledInstance(60, 60, java.awt.Image.SCALE_SMOOTH)), panelVisualizarFornecedores, "Visualizar Fornecedores");
		panelVisualizarFornecedores.setLayout(null);

		// Label para aba de visualizar fornecedores
		Icon iconAbaVisualizarFornecedores = new ImageIcon(new ImageIcon(JAdministrador.class.getResource("/img/visualizar-fornecedores.png")).getImage().getScaledInstance(60, 60, java.awt.Image.SCALE_SMOOTH));
		JLabel labelAbaVisualizarFornecedores = makeLabel(JLabel.BOTTOM, JLabel.CENTER, iconAbaVisualizarFornecedores, "Visualizar Fornecedores");
		labelAbaVisualizarFornecedores.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tabbedPaneAdministrador.setTabComponentAt(3, labelAbaVisualizarFornecedores);

		JScrollPane scrollPaneVisualizarFornecedores = new JScrollPane();
		scrollPaneVisualizarFornecedores.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		scrollPaneVisualizarFornecedores.setBounds(0, 0, 1379, 234);
		panelVisualizarFornecedores.add(scrollPaneVisualizarFornecedores);

		tableVisualizarFornecedores = new JTable() {
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
				case 5:
					return Object.class;
				case 6:
					return Object.class;
				case 7:
					return Object.class;
				case 8:
					return Object.class;
				case 9:
					return Object.class;
				case 10:
					return Object.class;
				case 11:
					return Object.class;
				default:
					return Object.class;
				}
			}
		};
		tableVisualizarFornecedores.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tableVisualizarFornecedores.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Selecione", "C\u00F3digo", "Nome", "Telefone Fixo", "Telefone Celular", "E-mail", "Data de Cadastro", "CNPJ", "Nome do Contato", "Login", "Senha"
				}
				) {
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
					true, false, false, false, false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tableVisualizarFornecedores.getColumnModel().getColumn(0).setResizable(false);
		tableVisualizarFornecedores.getColumnModel().getColumn(0).setPreferredWidth(100);
		tableVisualizarFornecedores.getColumnModel().getColumn(1).setResizable(false);
		tableVisualizarFornecedores.getColumnModel().getColumn(2).setPreferredWidth(135);
		tableVisualizarFornecedores.getColumnModel().getColumn(3).setPreferredWidth(135);
		tableVisualizarFornecedores.getColumnModel().getColumn(4).setPreferredWidth(135);
		tableVisualizarFornecedores.getColumnModel().getColumn(5).setPreferredWidth(185);
		tableVisualizarFornecedores.getColumnModel().getColumn(6).setPreferredWidth(200);
		tableVisualizarFornecedores.getColumnModel().getColumn(7).setPreferredWidth(165);
		tableVisualizarFornecedores.getColumnModel().getColumn(8).setPreferredWidth(145);
		tableVisualizarFornecedores.getColumnModel().getColumn(9).setPreferredWidth(100);
		tableVisualizarFornecedores.getColumnModel().getColumn(10).setPreferredWidth(100);
		tableVisualizarFornecedores.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 12));
		tableVisualizarFornecedores.getTableHeader().setReorderingAllowed(false);
		scrollPaneVisualizarFornecedores.setViewportView(tableVisualizarFornecedores);

		// Alinhamento horizontal e fonte: cabeçalho da tabela de fornecedores
		((JLabel) tableVisualizarFornecedores.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
		tableVisualizarFornecedores.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));

		// Cor intercalada nas linhas: tabela de fornecedores
		gerenciarComportamentoTabela(tableVisualizarFornecedores);

		JButton btnExcluirFornecedores = new JButton("Excluir");
		btnExcluirFornecedores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ArrayList<Integer> arrayListCodigoFornecedor = new ArrayList<>();

				for(int i = 0 ; i < tableVisualizarFornecedores.getRowCount(); i++) {
					Boolean checked = Boolean.valueOf(tableVisualizarFornecedores.getValueAt(i, 0).toString());
					Integer checkedCodigo = Integer.parseInt(tableVisualizarFornecedores.getValueAt(i, 1).toString());

					if(checked)
						arrayListCodigoFornecedor.add(checkedCodigo);
				}

				if (arrayListCodigoFornecedor.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Não há nenhum fornecedor selecionado para excluir.", "FALHA AO EXCLUIR", JOptionPane.WARNING_MESSAGE);
				} else {
					try {
						Comercial.excluirFornecedor(arrayListCodigoFornecedor);
						exibirFornecedores();
						JOptionPane.showMessageDialog(null, "Fornecedor(es) excluído(s) do banco de dados.", "EXCLUSÃO REALIZADA", JOptionPane.INFORMATION_MESSAGE);
					} catch (SisComException sisComException) {
						JOptionPane.showMessageDialog(null, sisComException.getMensagemErro(), "ERRO AO EXCLUIR", JOptionPane.WARNING_MESSAGE);
					} catch (HibernateException hibernateException) {
						JOptionPane.showMessageDialog(null, "Erro ao excluir fornecedor(es).\nErro: " + hibernateException.getMessage(), "ERRO AO EXCLUIR", JOptionPane.ERROR_MESSAGE);
					} catch (Exception exception) {
						JOptionPane.showMessageDialog(null, "Erro ao excluir fornecedor(es).\nErro: " + exception.getMessage(), "ERRO AO EXCLUIR", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnExcluirFornecedores.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnExcluirFornecedores.setIcon(new ImageIcon(new ImageIcon(JAdministrador.class.getResource("/img/excluir.png")).getImage().getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH)));
		btnExcluirFornecedores.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		btnExcluirFornecedores.setBounds(1027, 263, 171, 49);
		panelVisualizarFornecedores.add(btnExcluirFornecedores);

		JButton btnVisualizarFornecedores = new JButton("Visualizar");
		btnVisualizarFornecedores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exibirFornecedores();
			}
		});
		btnVisualizarFornecedores.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnVisualizarFornecedores.setIcon(new ImageIcon(new ImageIcon(JAdministrador.class.getResource("/img/visualizar-fornecedores.png")).getImage().getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH)));
		btnVisualizarFornecedores.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		btnVisualizarFornecedores.setBounds(1208, 263, 171, 49);
		panelVisualizarFornecedores.add(btnVisualizarFornecedores);

		JLabel lblCnpjFornecedorBusca = new JLabel("CNPJ para Busca:");
		lblCnpjFornecedorBusca.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		lblCnpjFornecedorBusca.setBounds(0, 277, 180, 20);
		panelVisualizarFornecedores.add(lblCnpjFornecedorBusca);

		formattedTextFieldCnpjFornecedorBusca = new JFormattedTextField(gerarMascaraCNPJ());
		formattedTextFieldCnpjFornecedorBusca.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		formattedTextFieldCnpjFornecedorBusca.setBorder(new LineBorder(Color.BLACK));
		formattedTextFieldCnpjFornecedorBusca.setBounds(195, 275, 200, 26);
		panelVisualizarFornecedores.add(formattedTextFieldCnpjFornecedorBusca);

		JButton btnConsultarFornecedor = new JButton("Consultar");
		btnConsultarFornecedor.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnConsultarFornecedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Fornecedor fornecedor = Comercial.consultarFornecedor(formattedTextFieldCnpjFornecedorBusca.getText());

					if (fornecedor != null) {
						DefaultTableModel defaultTableModel = (DefaultTableModel) tableVisualizarFornecedores.getModel();
						Object rowData[] = new Object[11];

						defaultTableModel.setRowCount(0);
						rowData[0] = false;
						rowData[1] = fornecedor.getCodigo();
						rowData[2] = fornecedor.getNome();
						rowData[3] = fornecedor.getTelefoneFixo();
						rowData[4] = fornecedor.getTelefoneCelular();
						rowData[5] = fornecedor.getEmail();
						rowData[6] = fornecedor.getDataCad();
						rowData[7] = fornecedor.getCnpj();
						rowData[8] = fornecedor.getNomeContato();
						rowData[9] = fornecedor.getLogin();
						rowData[10] = fornecedor.getSenha();
						defaultTableModel.addRow(rowData);
					} else {
						JOptionPane.showMessageDialog(null, "Fornecedor não encontrado.", "FALHA AO BUSCAR", JOptionPane.WARNING_MESSAGE);
					}
				} catch (HibernateException hibernateException) {
					JOptionPane.showMessageDialog(null, "Erro ao buscar fornecedor.\n" + hibernateException.getMessage(), "ERRO AO BUSCAR", JOptionPane.ERROR_MESSAGE);
				} catch (Exception exception) {
					JOptionPane.showMessageDialog(null, "Erro ao buscar fornecedor.\n" + exception.getMessage(), "ERRO AO BUSCAR", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnConsultarFornecedor.setIcon(new ImageIcon(new ImageIcon(JAdministrador.class.getResource("/img/consultar.png")).getImage().getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH)));
		btnConsultarFornecedor.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		btnConsultarFornecedor.setBounds(410, 263, 171, 49);
		panelVisualizarFornecedores.add(btnConsultarFornecedor);

		JPanel panelCadastrarVendedor = new JPanel();
		panelCadastrarVendedor.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		tabbedPaneAdministrador.addTab("", new ImageIcon(new ImageIcon(JAdministrador.class.getResource("/img/adicionar-vendedor.png")).getImage().getScaledInstance(60, 60, java.awt.Image.SCALE_SMOOTH)), panelCadastrarVendedor, "Cadastrar Vendedor");
		GridBagLayout gbl_panelCadastrarVendedor = new GridBagLayout();
		gbl_panelCadastrarVendedor.columnWidths = new int[]{0, 0, 0};
		gbl_panelCadastrarVendedor.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panelCadastrarVendedor.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panelCadastrarVendedor.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		panelCadastrarVendedor.setLayout(gbl_panelCadastrarVendedor);

		// Label para aba de cadastrar vendedor
		Icon iconAbaAdicionarVendedor = new ImageIcon(new ImageIcon(JAdministrador.class.getResource("/img/adicionar-vendedor.png")).getImage().getScaledInstance(60, 60, java.awt.Image.SCALE_SMOOTH));
		JLabel labelAbaAdicionarVendedor = makeLabel(JLabel.BOTTOM, JLabel.CENTER, iconAbaAdicionarVendedor, "Cadastrar Vendedor");
		labelAbaAdicionarVendedor.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tabbedPaneAdministrador.setTabComponentAt(4, labelAbaAdicionarVendedor);

		JLabel lblNomeVendedor = new JLabel("Nome*:");
		lblNomeVendedor.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		GridBagConstraints gbc_lblNomeVendedor = new GridBagConstraints();
		gbc_lblNomeVendedor.fill = GridBagConstraints.VERTICAL;
		gbc_lblNomeVendedor.anchor = GridBagConstraints.EAST;
		gbc_lblNomeVendedor.insets = new Insets(0, 0, 5, 5);
		gbc_lblNomeVendedor.gridx = 0;
		gbc_lblNomeVendedor.gridy = 0;
		panelCadastrarVendedor.add(lblNomeVendedor, gbc_lblNomeVendedor);

		textFieldNomeVendedor = new JTextField();
		textFieldNomeVendedor.setToolTipText("Campo obrigat\u00F3rio");
		textFieldNomeVendedor.setBorder(new LineBorder(new Color(0, 0, 0)));
		textFieldNomeVendedor.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		GridBagConstraints gbc_textFieldNomeVendedor = new GridBagConstraints();
		gbc_textFieldNomeVendedor.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldNomeVendedor.fill = GridBagConstraints.BOTH;
		gbc_textFieldNomeVendedor.gridx = 1;
		gbc_textFieldNomeVendedor.gridy = 0;
		panelCadastrarVendedor.add(textFieldNomeVendedor, gbc_textFieldNomeVendedor);
		textFieldNomeVendedor.setColumns(10);

		JLabel lblTelefoneFixoVendedor = new JLabel("Telefone Fixo:");
		lblTelefoneFixoVendedor.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		GridBagConstraints gbc_lblTelefoneFixoVendedor = new GridBagConstraints();
		gbc_lblTelefoneFixoVendedor.fill = GridBagConstraints.VERTICAL;
		gbc_lblTelefoneFixoVendedor.anchor = GridBagConstraints.EAST;
		gbc_lblTelefoneFixoVendedor.insets = new Insets(0, 0, 5, 5);
		gbc_lblTelefoneFixoVendedor.gridx = 0;
		gbc_lblTelefoneFixoVendedor.gridy = 1;
		panelCadastrarVendedor.add(lblTelefoneFixoVendedor, gbc_lblTelefoneFixoVendedor);

		formattedTextFieldTelefoneFixoVendedor = new JFormattedTextField(gerarMascaraTelefoneFixo());
		formattedTextFieldTelefoneFixoVendedor.setBorder(new LineBorder(new Color(0, 0, 0)));
		formattedTextFieldTelefoneFixoVendedor.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		GridBagConstraints gbc_formattedTextFieldTelefoneFixoVendedor = new GridBagConstraints();
		gbc_formattedTextFieldTelefoneFixoVendedor.insets = new Insets(0, 0, 5, 0);
		gbc_formattedTextFieldTelefoneFixoVendedor.fill = GridBagConstraints.BOTH;
		gbc_formattedTextFieldTelefoneFixoVendedor.gridx = 1;
		gbc_formattedTextFieldTelefoneFixoVendedor.gridy = 1;
		panelCadastrarVendedor.add(formattedTextFieldTelefoneFixoVendedor, gbc_formattedTextFieldTelefoneFixoVendedor);

		JLabel lblTelefoneCelularVendedor = new JLabel("Telefone Celular*:");
		lblTelefoneCelularVendedor.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		GridBagConstraints gbc_lblTelefoneCelularVendedor = new GridBagConstraints();
		gbc_lblTelefoneCelularVendedor.fill = GridBagConstraints.VERTICAL;
		gbc_lblTelefoneCelularVendedor.anchor = GridBagConstraints.EAST;
		gbc_lblTelefoneCelularVendedor.insets = new Insets(0, 0, 5, 5);
		gbc_lblTelefoneCelularVendedor.gridx = 0;
		gbc_lblTelefoneCelularVendedor.gridy = 2;
		panelCadastrarVendedor.add(lblTelefoneCelularVendedor, gbc_lblTelefoneCelularVendedor);

		formattedTextFieldTelefoneCelularVendedor = new JFormattedTextField(gerarMascaraTelefoneCelular());
		formattedTextFieldTelefoneCelularVendedor.setToolTipText("Campo obrigat\u00F3rio");
		formattedTextFieldTelefoneCelularVendedor.setBorder(new LineBorder(new Color(0, 0, 0)));
		formattedTextFieldTelefoneCelularVendedor.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		GridBagConstraints gbc_formattedTextFieldTelefoneCelularVendedor = new GridBagConstraints();
		gbc_formattedTextFieldTelefoneCelularVendedor.insets = new Insets(0, 0, 5, 0);
		gbc_formattedTextFieldTelefoneCelularVendedor.fill = GridBagConstraints.BOTH;
		gbc_formattedTextFieldTelefoneCelularVendedor.gridx = 1;
		gbc_formattedTextFieldTelefoneCelularVendedor.gridy = 2;
		panelCadastrarVendedor.add(formattedTextFieldTelefoneCelularVendedor, gbc_formattedTextFieldTelefoneCelularVendedor);

		JLabel lblEmailVendedor = new JLabel("E-mail*:");
		lblEmailVendedor.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		GridBagConstraints gbc_lblEmailVendedor = new GridBagConstraints();
		gbc_lblEmailVendedor.fill = GridBagConstraints.VERTICAL;
		gbc_lblEmailVendedor.anchor = GridBagConstraints.EAST;
		gbc_lblEmailVendedor.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmailVendedor.gridx = 0;
		gbc_lblEmailVendedor.gridy = 3;
		panelCadastrarVendedor.add(lblEmailVendedor, gbc_lblEmailVendedor);

		formattedTextFieldEmailVendedor = new JFormattedTextField();
		formattedTextFieldEmailVendedor.setToolTipText("Campo obrigat\u00F3rio");
		formattedTextFieldEmailVendedor.setBorder(new LineBorder(new Color(0, 0, 0)));
		formattedTextFieldEmailVendedor.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		GridBagConstraints gbc_formattedTextFieldEmailVendedor = new GridBagConstraints();
		gbc_formattedTextFieldEmailVendedor.insets = new Insets(0, 0, 5, 0);
		gbc_formattedTextFieldEmailVendedor.fill = GridBagConstraints.BOTH;
		gbc_formattedTextFieldEmailVendedor.gridx = 1;
		gbc_formattedTextFieldEmailVendedor.gridy = 3;
		panelCadastrarVendedor.add(formattedTextFieldEmailVendedor, gbc_formattedTextFieldEmailVendedor);

		JLabel lblCpfVendedor = new JLabel("CPF*:");
		lblCpfVendedor.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		GridBagConstraints gbc_lblCpfVendedor = new GridBagConstraints();
		gbc_lblCpfVendedor.fill = GridBagConstraints.VERTICAL;
		gbc_lblCpfVendedor.anchor = GridBagConstraints.EAST;
		gbc_lblCpfVendedor.insets = new Insets(0, 0, 5, 5);
		gbc_lblCpfVendedor.gridx = 0;
		gbc_lblCpfVendedor.gridy = 4;
		panelCadastrarVendedor.add(lblCpfVendedor, gbc_lblCpfVendedor);

		formattedTextFieldCpfVendedor = new JFormattedTextField(gerarMascaraCPF());
		formattedTextFieldCpfVendedor.setToolTipText("Campo obrigat\u00F3rio");
		formattedTextFieldCpfVendedor.setBorder(new LineBorder(new Color(0, 0, 0)));
		formattedTextFieldCpfVendedor.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		GridBagConstraints gbc_formattedTextFieldCpfVendedor = new GridBagConstraints();
		gbc_formattedTextFieldCpfVendedor.insets = new Insets(0, 0, 5, 0);
		gbc_formattedTextFieldCpfVendedor.fill = GridBagConstraints.BOTH;
		gbc_formattedTextFieldCpfVendedor.gridx = 1;
		gbc_formattedTextFieldCpfVendedor.gridy = 4;
		panelCadastrarVendedor.add(formattedTextFieldCpfVendedor, gbc_formattedTextFieldCpfVendedor);

		JLabel lblMetaMensalVendedor = new JLabel("Metal Mensal*:");
		lblMetaMensalVendedor.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		GridBagConstraints gbc_lblMetaMensalVendedor = new GridBagConstraints();
		gbc_lblMetaMensalVendedor.fill = GridBagConstraints.VERTICAL;
		gbc_lblMetaMensalVendedor.anchor = GridBagConstraints.EAST;
		gbc_lblMetaMensalVendedor.insets = new Insets(0, 0, 5, 5);
		gbc_lblMetaMensalVendedor.gridx = 0;
		gbc_lblMetaMensalVendedor.gridy = 5;
		panelCadastrarVendedor.add(lblMetaMensalVendedor, gbc_lblMetaMensalVendedor);

		formattedTextFieldMetaMensalVendedor = new JMoneyField();
		formattedTextFieldMetaMensalVendedor.setToolTipText("Campo obrigat\u00F3rio");
		formattedTextFieldMetaMensalVendedor.setBorder(new LineBorder(new Color(0, 0, 0)));
		formattedTextFieldMetaMensalVendedor.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		GridBagConstraints gbc_formattedTextFieldMetaMensalVendedor = new GridBagConstraints();
		gbc_formattedTextFieldMetaMensalVendedor.insets = new Insets(0, 0, 5, 0);
		gbc_formattedTextFieldMetaMensalVendedor.fill = GridBagConstraints.BOTH;
		gbc_formattedTextFieldMetaMensalVendedor.gridx = 1;
		gbc_formattedTextFieldMetaMensalVendedor.gridy = 5;
		panelCadastrarVendedor.add(formattedTextFieldMetaMensalVendedor, gbc_formattedTextFieldMetaMensalVendedor);

		JLabel lblLoginVendedor = new JLabel("Login*:");
		lblLoginVendedor.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		GridBagConstraints gbc_lblLoginVendedor = new GridBagConstraints();
		gbc_lblLoginVendedor.fill = GridBagConstraints.VERTICAL;
		gbc_lblLoginVendedor.anchor = GridBagConstraints.EAST;
		gbc_lblLoginVendedor.insets = new Insets(0, 0, 5, 5);
		gbc_lblLoginVendedor.gridx = 0;
		gbc_lblLoginVendedor.gridy = 6;
		panelCadastrarVendedor.add(lblLoginVendedor, gbc_lblLoginVendedor);

		textFieldLoginVendedor = new JTextField();
		textFieldLoginVendedor.setToolTipText("Campo Obrigat\u00F3rio");
		textFieldLoginVendedor.setBorder(new LineBorder(new Color(0, 0, 0)));
		textFieldLoginVendedor.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		GridBagConstraints gbc_textFieldLoginVendedor = new GridBagConstraints();
		gbc_textFieldLoginVendedor.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldLoginVendedor.fill = GridBagConstraints.BOTH;
		gbc_textFieldLoginVendedor.gridx = 1;
		gbc_textFieldLoginVendedor.gridy = 6;
		panelCadastrarVendedor.add(textFieldLoginVendedor, gbc_textFieldLoginVendedor);
		textFieldLoginVendedor.setColumns(10);

		JLabel lblSenhaVendedor = new JLabel("Senha*:");
		lblSenhaVendedor.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		GridBagConstraints gbc_lblSenhaVendedor = new GridBagConstraints();
		gbc_lblSenhaVendedor.fill = GridBagConstraints.VERTICAL;
		gbc_lblSenhaVendedor.anchor = GridBagConstraints.EAST;
		gbc_lblSenhaVendedor.insets = new Insets(0, 0, 5, 5);
		gbc_lblSenhaVendedor.gridx = 0;
		gbc_lblSenhaVendedor.gridy = 7;
		panelCadastrarVendedor.add(lblSenhaVendedor, gbc_lblSenhaVendedor);

		passwordFieldSenhaVendedor = new JPasswordField();
		passwordFieldSenhaVendedor.setToolTipText("Campo Obrigat\u00F3rio");
		passwordFieldSenhaVendedor.setBorder(new LineBorder(new Color(0, 0, 0)));
		passwordFieldSenhaVendedor.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		passwordFieldSenhaVendedor.setEchoChar('*');
		GridBagConstraints gbc_passwordFieldSenhaVendedor = new GridBagConstraints();
		gbc_passwordFieldSenhaVendedor.insets = new Insets(0, 0, 5, 0);
		gbc_passwordFieldSenhaVendedor.fill = GridBagConstraints.BOTH;
		gbc_passwordFieldSenhaVendedor.gridx = 1;
		gbc_passwordFieldSenhaVendedor.gridy = 7;
		panelCadastrarVendedor.add(passwordFieldSenhaVendedor, gbc_passwordFieldSenhaVendedor);

		JLabel lblConfirmarSenhaVendedor = new JLabel("Confirmar Senha*:");
		lblConfirmarSenhaVendedor.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		GridBagConstraints gbc_lblConfirmarSenhaVendedor = new GridBagConstraints();
		gbc_lblConfirmarSenhaVendedor.fill = GridBagConstraints.VERTICAL;
		gbc_lblConfirmarSenhaVendedor.anchor = GridBagConstraints.EAST;
		gbc_lblConfirmarSenhaVendedor.insets = new Insets(0, 0, 5, 5);
		gbc_lblConfirmarSenhaVendedor.gridx = 0;
		gbc_lblConfirmarSenhaVendedor.gridy = 8;
		panelCadastrarVendedor.add(lblConfirmarSenhaVendedor, gbc_lblConfirmarSenhaVendedor);

		passwordFieldConfirmarSenhaVendedor = new JPasswordField();
		passwordFieldConfirmarSenhaVendedor.setToolTipText("Campo Obrigat\u00F3rio");
		passwordFieldConfirmarSenhaVendedor.setBorder(new LineBorder(new Color(0, 0, 0)));
		passwordFieldConfirmarSenhaVendedor.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		passwordFieldConfirmarSenhaVendedor.setEchoChar('*');
		GridBagConstraints gbc_passwordFieldConfirmarSenhaVendedor = new GridBagConstraints();
		gbc_passwordFieldConfirmarSenhaVendedor.insets = new Insets(0, 0, 5, 0);
		gbc_passwordFieldConfirmarSenhaVendedor.fill = GridBagConstraints.BOTH;
		gbc_passwordFieldConfirmarSenhaVendedor.gridx = 1;
		gbc_passwordFieldConfirmarSenhaVendedor.gridy = 8;
		panelCadastrarVendedor.add(passwordFieldConfirmarSenhaVendedor, gbc_passwordFieldConfirmarSenhaVendedor);

		JButton btnCadastrarVendedor = new JButton("Cadastrar");
		btnCadastrarVendedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HashMap<String, String> hashMapCamposVendedor = new HashMap<>();

				hashMapCamposVendedor.put("Nome", textFieldNomeVendedor.getText());
				hashMapCamposVendedor.put("TelefoneFixo", formattedTextFieldTelefoneFixoVendedor.getText());
				hashMapCamposVendedor.put("TelefoneCelular", formattedTextFieldTelefoneCelularVendedor.getText());
				hashMapCamposVendedor.put("Email", formattedTextFieldEmailVendedor.getText());
				hashMapCamposVendedor.put("Cpf", formattedTextFieldCpfVendedor.getText());
				hashMapCamposVendedor.put("MetaMensal", formattedTextFieldMetaMensalVendedor.getText());
				hashMapCamposVendedor.put("Login", textFieldLoginVendedor.getText());
				hashMapCamposVendedor.put("Senha", new String(passwordFieldSenhaVendedor.getPassword()));
				hashMapCamposVendedor.put("ConfirmarSenha", new String(passwordFieldConfirmarSenhaVendedor.getPassword()));

				HashMap<String, Boolean> hashMapCamposVendedorValidados = Comercial.validarVendedor(hashMapCamposVendedor);

				if (hashMapCamposVendedorValidados.get("Nome") == false)
					exibirBalaoAviso(textFieldNomeVendedor, "O nome não foi preenchido.");

				if (hashMapCamposVendedorValidados.get("TelefoneFixo") == false) {
					hashMapCamposVendedor.put("TelefoneFixo", "");
					hashMapCamposVendedorValidados.put("TelefoneFixo", true);
				}

				if (hashMapCamposVendedorValidados.get("TelefoneCelular") == false)
					exibirBalaoAviso(formattedTextFieldTelefoneCelularVendedor, "O telefone celular não foi preenchido.");

				if (hashMapCamposVendedorValidados.get("Email") == false)
					exibirBalaoAviso(formattedTextFieldEmailVendedor, "E-mail inválido.");

				if (hashMapCamposVendedorValidados.get("Cpf") == false)
					exibirBalaoAviso(formattedTextFieldCpfVendedor, "CPF inválido.");

				if (hashMapCamposVendedorValidados.get("MetaMensal") == false)
					exibirBalaoAviso(formattedTextFieldMetaMensalVendedor, "A metal mensal não foi preenchida. ");

				if (hashMapCamposVendedorValidados.get("Login") == false)
					exibirBalaoAviso(textFieldLoginVendedor, "O login não foi preenchido adequadamente (mínimo 4 caracteres).");

				if (hashMapCamposVendedorValidados.get("Senha") == false)
					exibirBalaoAviso(passwordFieldSenhaVendedor, "A senha não foi peenchida adequadamente (mínimo 4 caracteres).");

				if (hashMapCamposVendedorValidados.get("ConfirmarSenha") == false)
					exibirBalaoAviso(passwordFieldConfirmarSenhaVendedor, "Confirmação de senha inválida.");

				if (Collections.frequency(hashMapCamposVendedorValidados.values(), true) == hashMapCamposVendedorValidados.size()) {
					try {
						boolean cpfRegistrado = Comercial.cpfRegistrado(hashMapCamposVendedor.get("Cpf"));
						boolean loginRegistrado = Comercial.loginRegistrado(hashMapCamposVendedor.get("Login"));
						String mensagem = null;

						if (cpfRegistrado)
							mensagem = "O CPF já está cadastrado.";

						if (loginRegistrado)
							mensagem = "O login já está cadastrado.";

						if (cpfRegistrado && loginRegistrado)
							mensagem = "O CPF e o login já estão cadastrados.";

						if (!cpfRegistrado && !loginRegistrado) {
							Comercial.inserirVendedor(hashMapCamposVendedor);
							JOptionPane.showMessageDialog(null, "Vendedor cadastrado no banco de dados.", "CADASTRO REALIZADO", JOptionPane.INFORMATION_MESSAGE);
							limparCampos(tabbedPaneAdministrador.getSelectedIndex());
						} else {
							JOptionPane.showMessageDialog(null, mensagem, "FALHA AO CADASTRAR", JOptionPane.WARNING_MESSAGE);
						}
					} catch (HibernateException hibernateException) {
						JOptionPane.showMessageDialog(null, "Erro ao cadastrar vendedor no banco de dados.\nErro: " + hibernateException.getMessage(), "ERRO AO CADASTRAR", JOptionPane.ERROR_MESSAGE);
					} catch (Exception exception) {
						JOptionPane.showMessageDialog(null, "Erro ao cadastrar vendedor no banco de dados.\nErro: " + exception.getMessage(), "ERRO AO CADASTRAR", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnCadastrarVendedor.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCadastrarVendedor.setIcon(new ImageIcon(new ImageIcon(JAdministrador.class.getResource("/img/adicionar-vendedor.png")).getImage().getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH)));
		btnCadastrarVendedor.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		GridBagConstraints gbc_btnCadastrarVendedor = new GridBagConstraints();
		gbc_btnCadastrarVendedor.anchor = GridBagConstraints.SOUTHEAST;
		gbc_btnCadastrarVendedor.gridx = 1;
		gbc_btnCadastrarVendedor.gridy = 9;
		panelCadastrarVendedor.add(btnCadastrarVendedor, gbc_btnCadastrarVendedor);

		panelVisualizarVendedores = new JPanel();
		panelVisualizarVendedores.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		tabbedPaneAdministrador.addTab("", new ImageIcon(new ImageIcon(JAdministrador.class.getResource("/img/visualizar-vendedores.png")).getImage().getScaledInstance(60, 60, java.awt.Image.SCALE_SMOOTH)), panelVisualizarVendedores, "Visualizar Vendedor");
		panelVisualizarVendedores.setLayout(null);

		// Label para aba de visualizar vendedores
		Icon iconAbaVisualizarVendedores = new ImageIcon(new ImageIcon(JAdministrador.class.getResource("/img/visualizar-vendedores.png")).getImage().getScaledInstance(60, 60, java.awt.Image.SCALE_SMOOTH));
		JLabel labelAbaVisualizarVendedores = makeLabel(JLabel.BOTTOM, JLabel.CENTER, iconAbaVisualizarVendedores, "Visualizar Vendedores");
		labelAbaVisualizarVendedores.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tabbedPaneAdministrador.setTabComponentAt(5, labelAbaVisualizarVendedores);

		JScrollPane scrollPaneVisualizarVendedores = new JScrollPane();
		scrollPaneVisualizarVendedores.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		scrollPaneVisualizarVendedores.setBounds(0, 0, 1379, 234);
		panelVisualizarVendedores.add(scrollPaneVisualizarVendedores);

		tableVisualizarVendedores = new JTable() {
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
				case 5:
					return Object.class;
				case 6:
					return Object.class;
				case 7:
					return Object.class;
				case 8:
					return Object.class;
				case 9:
					return Object.class;
				case 10:
					return Object.class;
				case 11:
					return Object.class;
				default:
					return Object.class;
				}
			}
		};
		tableVisualizarVendedores.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tableVisualizarVendedores.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Selecione", "C\u00F3digo", "Nome", "Telefone Fixo", "Telefone Celular", "E-mail", "Data de Cadastro", "CPF", "Meta Mensal", "Login", "Senha"
				}
				) {
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
					true, false, false, false, false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tableVisualizarVendedores.getColumnModel().getColumn(0).setResizable(false);
		tableVisualizarVendedores.getColumnModel().getColumn(0).setPreferredWidth(100);
		tableVisualizarVendedores.getColumnModel().getColumn(1).setResizable(false);
		tableVisualizarVendedores.getColumnModel().getColumn(2).setPreferredWidth(135);
		tableVisualizarVendedores.getColumnModel().getColumn(3).setPreferredWidth(135);
		tableVisualizarVendedores.getColumnModel().getColumn(4).setPreferredWidth(135);
		tableVisualizarVendedores.getColumnModel().getColumn(5).setPreferredWidth(185);
		tableVisualizarVendedores.getColumnModel().getColumn(6).setPreferredWidth(200);
		tableVisualizarVendedores.getColumnModel().getColumn(7).setPreferredWidth(150);
		tableVisualizarVendedores.getColumnModel().getColumn(8).setPreferredWidth(120);
		tableVisualizarVendedores.getColumnModel().getColumn(9).setPreferredWidth(120);
		tableVisualizarVendedores.getColumnModel().getColumn(10).setPreferredWidth(120);
		tableVisualizarVendedores.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 12));
		tableVisualizarVendedores.getTableHeader().setReorderingAllowed(false);
		scrollPaneVisualizarVendedores.setViewportView(tableVisualizarVendedores);

		// Alinhamento horizontal e fonte: cabeçalho da tabela de vendedores
		((JLabel) tableVisualizarVendedores.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
		tableVisualizarVendedores.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));

		// Cor intercalada nas linhas: tabela de vendedores
		gerenciarComportamentoTabela(tableVisualizarVendedores);

		JButton btnExcluirVendedores = new JButton("Excluir");
		btnExcluirVendedores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<Integer> arrayListCodigoVendedor = new ArrayList<>();

				for(int i = 0 ; i < tableVisualizarVendedores.getRowCount(); i++) {
					Boolean checked = Boolean.valueOf(tableVisualizarVendedores.getValueAt(i, 0).toString());
					Integer checkedCodigo = Integer.parseInt(tableVisualizarVendedores.getValueAt(i, 1).toString());

					if(checked)
						arrayListCodigoVendedor.add(checkedCodigo);
				}

				if (arrayListCodigoVendedor.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Não há nenhum vendedor selecionado para excluir.", "FALHA AO EXCLUIR", JOptionPane.WARNING_MESSAGE);
				} else {
					try {
						Comercial.excluirVendedor(arrayListCodigoVendedor);
						exibirVendedores();
						JOptionPane.showMessageDialog(null, "Vendedor(es) excluído(s) do banco de dados.", "EXCLUSÃO REALIZADA", JOptionPane.INFORMATION_MESSAGE);
					} catch (SisComException sisComException) {
						JOptionPane.showMessageDialog(null, sisComException.getMensagemErro(), "ERRO AO EXCLUIR", JOptionPane.WARNING_MESSAGE);
					} catch (HibernateException hibernateException) {
						JOptionPane.showMessageDialog(null, "Erro ao excluir vendedor(es).\nErro: " + hibernateException.getMessage(), "ERRO AO EXCLUIR", JOptionPane.ERROR_MESSAGE);
					} catch (Exception exception) {
						JOptionPane.showMessageDialog(null, "Erro ao excluir vendedor(es).\nErro: " + exception.getMessage(), "ERRO AO EXCLUIR", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnExcluirVendedores.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnExcluirVendedores.setIcon(new ImageIcon(new ImageIcon(JAdministrador.class.getResource("/img/excluir.png")).getImage().getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH)));
		btnExcluirVendedores.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		btnExcluirVendedores.setBounds(1027, 263, 171, 49);
		panelVisualizarVendedores.add(btnExcluirVendedores);

		JButton btnVisualizarVendedores = new JButton("Visualizar");
		btnVisualizarVendedores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exibirVendedores();
			}
		});
		btnVisualizarVendedores.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnVisualizarVendedores.setIcon(new ImageIcon(new ImageIcon(JAdministrador.class.getResource("/img/visualizar-vendedores.png")).getImage().getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH)));
		btnVisualizarVendedores.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		btnVisualizarVendedores.setBounds(1208, 263, 171, 49);
		panelVisualizarVendedores.add(btnVisualizarVendedores);

		JLabel lblCpfVendedorBusca = new JLabel("CPF para Busca:");
		lblCpfVendedorBusca.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		lblCpfVendedorBusca.setBounds(0, 277, 180, 20);
		panelVisualizarVendedores.add(lblCpfVendedorBusca);

		formattedTextFieldCpfVendedorBusca = new JFormattedTextField(gerarMascaraCPF());
		formattedTextFieldCpfVendedorBusca.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		formattedTextFieldCpfVendedorBusca.setBorder(new LineBorder(Color.BLACK));
		formattedTextFieldCpfVendedorBusca.setBounds(195, 275, 200, 26);
		panelVisualizarVendedores.add(formattedTextFieldCpfVendedorBusca);

		JButton btnConsultarVendedor = new JButton("Consultar");
		btnConsultarVendedor.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnConsultarVendedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Vendedor vendedor = Comercial.consultarVendedor(formattedTextFieldCpfVendedorBusca.getText());

					if (vendedor != null) {
						DefaultTableModel defaultTableModel = (DefaultTableModel) tableVisualizarVendedores.getModel();
						Object rowData[] = new Object[11];

						defaultTableModel.setRowCount(0);
						rowData[0] = false;
						rowData[1] = vendedor.getCodigo();
						rowData[2] = vendedor.getNome();
						rowData[3] = vendedor.getTelefoneFixo();
						rowData[4] = vendedor.getTelefoneCelular();
						rowData[5] = vendedor.getEmail();
						rowData[6] = vendedor.getDataCad();
						rowData[7] = vendedor.getCpf();
						rowData[8] = vendedor.getMetaMensal();
						rowData[9] = vendedor.getLogin();
						rowData[10] = vendedor.getSenha();
						defaultTableModel.addRow(rowData);
					} else {
						JOptionPane.showMessageDialog(null, "Vendedor não encontrado.", "FALHA AO BUSCAR", JOptionPane.WARNING_MESSAGE);
					}
				} catch (HibernateException hibernateException) {
					JOptionPane.showMessageDialog(null, "Erro ao buscar vendedor.\n" + hibernateException.getMessage(), "ERRO AO BUSCAR", JOptionPane.ERROR_MESSAGE);
				} catch (Exception exception) {
					JOptionPane.showMessageDialog(null, "Erro ao buscar vendedor.\n" + exception.getMessage(), "ERRO AO BUSCAR", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnConsultarVendedor.setIcon(new ImageIcon(new ImageIcon(JAdministrador.class.getResource("/img/consultar.png")).getImage().getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH)));
		btnConsultarVendedor.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		btnConsultarVendedor.setBounds(410, 263, 171, 49);
		panelVisualizarVendedores.add(btnConsultarVendedor);

		JPanel panelCadastrarProduto = new JPanel();
		panelCadastrarProduto.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		panelCadastrarProduto.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		tabbedPaneAdministrador.addTab("", new ImageIcon(new ImageIcon(JAdministrador.class.getResource("/img/adicionar-produto.png")).getImage().getScaledInstance(60, 60, java.awt.Image.SCALE_SMOOTH)), panelCadastrarProduto, "Cadastrar Produto");
		GridBagLayout gbl_panelCadastrarProduto = new GridBagLayout();
		gbl_panelCadastrarProduto.columnWidths = new int[]{0, 0, 0};
		gbl_panelCadastrarProduto.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panelCadastrarProduto.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panelCadastrarProduto.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		panelCadastrarProduto.setLayout(gbl_panelCadastrarProduto);

		// Label para aba de cadastrar produto
		Icon iconAbaCadastrarProduto = new ImageIcon(new ImageIcon(JAdministrador.class.getResource("/img/adicionar-produto.png")).getImage().getScaledInstance(60, 60, java.awt.Image.SCALE_SMOOTH));
		JLabel labelAbaCadastrarProduto = makeLabel(JLabel.BOTTOM, JLabel.CENTER, iconAbaCadastrarProduto, "Cadastrar Produto");
		labelAbaCadastrarProduto.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tabbedPaneAdministrador.setTabComponentAt(6, labelAbaCadastrarProduto);

		JLabel lblNomeProduto = new JLabel("Nome*:");
		lblNomeProduto.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		GridBagConstraints gbc_lblNomeProduto = new GridBagConstraints();
		gbc_lblNomeProduto.fill = GridBagConstraints.VERTICAL;
		gbc_lblNomeProduto.anchor = GridBagConstraints.EAST;
		gbc_lblNomeProduto.insets = new Insets(0, 0, 5, 5);
		gbc_lblNomeProduto.gridx = 0;
		gbc_lblNomeProduto.gridy = 0;
		panelCadastrarProduto.add(lblNomeProduto, gbc_lblNomeProduto);

		textFieldNomeProduto = new JTextField();
		textFieldNomeProduto.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		textFieldNomeProduto.setBorder(new LineBorder(Color.BLACK));
		textFieldNomeProduto.setToolTipText("Campo Obrigat\u00F3rio");
		GridBagConstraints gbc_textFieldNomeProduto = new GridBagConstraints();
		gbc_textFieldNomeProduto.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldNomeProduto.fill = GridBagConstraints.BOTH;
		gbc_textFieldNomeProduto.gridx = 1;
		gbc_textFieldNomeProduto.gridy = 0;
		panelCadastrarProduto.add(textFieldNomeProduto, gbc_textFieldNomeProduto);
		textFieldNomeProduto.setColumns(10);

		JLabel lblPrecoUnitarioProduto = new JLabel("Pre\u00E7o Unit\u00E1rio*:");
		lblPrecoUnitarioProduto.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		GridBagConstraints gbc_lblPrecoUnitarioProduto = new GridBagConstraints();
		gbc_lblPrecoUnitarioProduto.fill = GridBagConstraints.VERTICAL;
		gbc_lblPrecoUnitarioProduto.anchor = GridBagConstraints.EAST;
		gbc_lblPrecoUnitarioProduto.insets = new Insets(0, 0, 5, 5);
		gbc_lblPrecoUnitarioProduto.gridx = 0;
		gbc_lblPrecoUnitarioProduto.gridy = 1;
		panelCadastrarProduto.add(lblPrecoUnitarioProduto, gbc_lblPrecoUnitarioProduto);

		formattedTextFieldPrecoUnitarioProduto = new JMoneyField();
		formattedTextFieldPrecoUnitarioProduto.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		formattedTextFieldPrecoUnitarioProduto.setBorder(new LineBorder(Color.BLACK));
		formattedTextFieldPrecoUnitarioProduto.setToolTipText("Campo Obrigat\u00F3rio");
		GridBagConstraints gbc_formattedTextFieldPrecoUnitarioProduto = new GridBagConstraints();
		gbc_formattedTextFieldPrecoUnitarioProduto.insets = new Insets(0, 0, 5, 0);
		gbc_formattedTextFieldPrecoUnitarioProduto.fill = GridBagConstraints.BOTH;
		gbc_formattedTextFieldPrecoUnitarioProduto.gridx = 1;
		gbc_formattedTextFieldPrecoUnitarioProduto.gridy = 1;
		panelCadastrarProduto.add(formattedTextFieldPrecoUnitarioProduto, gbc_formattedTextFieldPrecoUnitarioProduto);

		JLabel lblEstoqueMinimoProduto = new JLabel("Estoque M\u00EDnimo*:");
		lblEstoqueMinimoProduto.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		GridBagConstraints gbc_lblEstoqueMinimoProduto = new GridBagConstraints();
		gbc_lblEstoqueMinimoProduto.fill = GridBagConstraints.VERTICAL;
		gbc_lblEstoqueMinimoProduto.anchor = GridBagConstraints.EAST;
		gbc_lblEstoqueMinimoProduto.insets = new Insets(0, 0, 5, 5);
		gbc_lblEstoqueMinimoProduto.gridx = 0;
		gbc_lblEstoqueMinimoProduto.gridy = 2;
		panelCadastrarProduto.add(lblEstoqueMinimoProduto, gbc_lblEstoqueMinimoProduto);

		formattedTextFieldEstoqueMinimoProduto = new JFormattedTextField();
		formattedTextFieldEstoqueMinimoProduto.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		formattedTextFieldEstoqueMinimoProduto.setBorder(new LineBorder(Color.BLACK));
		formattedTextFieldEstoqueMinimoProduto.setToolTipText("Campo Obrigat\u00F3rio");
		permitirApenasNumeros(formattedTextFieldEstoqueMinimoProduto);
		GridBagConstraints gbc_formattedTextFieldEstoqueMinimoProduto = new GridBagConstraints();
		gbc_formattedTextFieldEstoqueMinimoProduto.insets = new Insets(0, 0, 5, 0);
		gbc_formattedTextFieldEstoqueMinimoProduto.fill = GridBagConstraints.BOTH;
		gbc_formattedTextFieldEstoqueMinimoProduto.gridx = 1;
		gbc_formattedTextFieldEstoqueMinimoProduto.gridy = 2;
		panelCadastrarProduto.add(formattedTextFieldEstoqueMinimoProduto, gbc_formattedTextFieldEstoqueMinimoProduto);

		JLabel lblEstoqueProduto = new JLabel("Estoque:");
		lblEstoqueProduto.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		GridBagConstraints gbc_lblEstoqueProduto = new GridBagConstraints();
		gbc_lblEstoqueProduto.fill = GridBagConstraints.VERTICAL;
		gbc_lblEstoqueProduto.anchor = GridBagConstraints.EAST;
		gbc_lblEstoqueProduto.insets = new Insets(0, 0, 5, 5);
		gbc_lblEstoqueProduto.gridx = 0;
		gbc_lblEstoqueProduto.gridy = 3;
		panelCadastrarProduto.add(lblEstoqueProduto, gbc_lblEstoqueProduto);

		formattedTextFieldEstoqueProduto = new JFormattedTextField();
		formattedTextFieldEstoqueProduto.setEnabled(false);
		formattedTextFieldEstoqueProduto.setText("0");
		formattedTextFieldEstoqueProduto.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		formattedTextFieldEstoqueProduto.setBorder(new LineBorder(Color.BLACK));
		formattedTextFieldEstoqueProduto.setToolTipText("Campo Obrigat\u00F3rio");
		permitirApenasNumeros(formattedTextFieldEstoqueProduto);
		GridBagConstraints gbc_formattedTextFieldEstoqueProduto = new GridBagConstraints();
		gbc_formattedTextFieldEstoqueProduto.insets = new Insets(0, 0, 5, 0);
		gbc_formattedTextFieldEstoqueProduto.fill = GridBagConstraints.BOTH;
		gbc_formattedTextFieldEstoqueProduto.gridx = 1;
		gbc_formattedTextFieldEstoqueProduto.gridy = 3;
		panelCadastrarProduto.add(formattedTextFieldEstoqueProduto, gbc_formattedTextFieldEstoqueProduto);

		JButton btnCadastrarProduto = new JButton("Cadastrar");
		btnCadastrarProduto.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCadastrarProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				HashMap<String, String> hashMapCamposProdutos = new HashMap<>();

				hashMapCamposProdutos.put("Nome", textFieldNomeProduto.getText());
				hashMapCamposProdutos.put("PrecoUnitario", formattedTextFieldPrecoUnitarioProduto.getText());
				hashMapCamposProdutos.put("Estoque", formattedTextFieldEstoqueProduto.getText());
				hashMapCamposProdutos.put("EstoqueMinimo", formattedTextFieldEstoqueMinimoProduto.getText());

				HashMap<String, Boolean> hashMapCamposProdutoValidados = Comercial.validarProduto(hashMapCamposProdutos);

				if (hashMapCamposProdutoValidados.get("Nome") == false)
					exibirBalaoAviso(textFieldNomeProduto, "O nome não foi preenchido.");

				if (hashMapCamposProdutoValidados.get("PrecoUnitario") == false)
					exibirBalaoAviso(formattedTextFieldPrecoUnitarioProduto, "O preço unitário não foi preenchido.");

				if (hashMapCamposProdutoValidados.get("Estoque") == false)
					exibirBalaoAviso(formattedTextFieldEstoqueProduto, "O estoque do produto não foi preenchido.");

				if (hashMapCamposProdutoValidados.get("EstoqueMinimo") == false)
					exibirBalaoAviso(formattedTextFieldEstoqueMinimoProduto, "O estoque mínimo do produto não foi preenchido.");

				if (Collections.frequency(hashMapCamposProdutoValidados.values(), true) == hashMapCamposProdutoValidados.size()) {
					try {
						Comercial.inserirProduto(hashMapCamposProdutos);
						JOptionPane.showMessageDialog(null, "Produto cadastrado no banco de dados.", "CADASTRO REALIZADO", JOptionPane.INFORMATION_MESSAGE);
						limparCampos(tabbedPaneAdministrador.getSelectedIndex());
					} catch (HibernateException hibernateException) {
						JOptionPane.showMessageDialog(null, "Erro ao cadastrar produto no banco de dados.\nErro: " + hibernateException.getMessage(), "ERRO AO CADASTRAR", JOptionPane.ERROR_MESSAGE);
					} catch (Exception exception) {
						JOptionPane.showMessageDialog(null, "Erro ao cadastrar produto no banco de dados.\nErro: " + exception.getMessage(), "ERRO AO CADASTRAR", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnCadastrarProduto.setIcon(new ImageIcon(new ImageIcon(JAdministrador.class.getResource("/img/adicionar-produto.png")).getImage().getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH)));
		btnCadastrarProduto.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		GridBagConstraints gbc_btnCadastrarProduto = new GridBagConstraints();
		gbc_btnCadastrarProduto.anchor = GridBagConstraints.SOUTHEAST;
		gbc_btnCadastrarProduto.gridx = 1;
		gbc_btnCadastrarProduto.gridy = 4;
		panelCadastrarProduto.add(btnCadastrarProduto, gbc_btnCadastrarProduto);

		panelVisualizarProdutos = new JPanel();
		panelVisualizarProdutos.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		tabbedPaneAdministrador.addTab("", new ImageIcon(new ImageIcon(JAdministrador.class.getResource("/img/visualizar-produtos.png")).getImage().getScaledInstance(60, 60, java.awt.Image.SCALE_SMOOTH)), panelVisualizarProdutos, "Visualizar Produtos");
		panelVisualizarProdutos.setLayout(null);

		// Label para aba de visualizar produtos
		Icon iconAbaVisualizarProdutos = new ImageIcon(new ImageIcon(JAdministrador.class.getResource("/img/visualizar-produtos.png")).getImage().getScaledInstance(60, 60, java.awt.Image.SCALE_SMOOTH));
		JLabel labelAbaVisualizarProdutos = makeLabel(JLabel.BOTTOM, JLabel.CENTER, iconAbaVisualizarProdutos, "Visualizar Produtos");
		labelAbaVisualizarProdutos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tabbedPaneAdministrador.setTabComponentAt(7, labelAbaVisualizarProdutos);

		JScrollPane scrollPaneVisualizarProdutos = new JScrollPane();
		scrollPaneVisualizarProdutos.setBounds(0, 0, 1379, 234);
		panelVisualizarProdutos.add(scrollPaneVisualizarProdutos);

		tableVisualizarProdutos = new JTable() {
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
				case 5:
					return Object.class;
				case 6:
					return Object.class;
				case 7:
					return Object.class;
				default:
					return Object.class;
				}
			}
		};
		tableVisualizarProdutos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tableVisualizarProdutos.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Selecione", "C\u00F3digo", "Nome", "Pre\u00E7o Unit\u00E1rio", "Estoque", "Estoque M\u00EDnimo", "Data de Cadastro"
				}
				) {
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
					true, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tableVisualizarProdutos.getColumnModel().getColumn(0).setResizable(false);
		tableVisualizarProdutos.getColumnModel().getColumn(0).setPreferredWidth(100);
		tableVisualizarProdutos.getColumnModel().getColumn(1).setResizable(false);
		tableVisualizarProdutos.getColumnModel().getColumn(2).setPreferredWidth(200);
		tableVisualizarProdutos.getColumnModel().getColumn(3).setPreferredWidth(200);
		tableVisualizarProdutos.getColumnModel().getColumn(4).setPreferredWidth(200);
		tableVisualizarProdutos.getColumnModel().getColumn(5).setPreferredWidth(200);
		tableVisualizarProdutos.getColumnModel().getColumn(6).setPreferredWidth(200);
		tableVisualizarProdutos.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 12));
		tableVisualizarProdutos.getTableHeader().setReorderingAllowed(false);
		scrollPaneVisualizarProdutos.setViewportView(tableVisualizarProdutos);

		// Alinhamento horizontal e fonte: cabeçalho da tabela de produtos
		((JLabel) tableVisualizarProdutos.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
		tableVisualizarProdutos.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));

		// Cor intercalada nas linhas: tabela de produtos
		gerenciarComportamentoTabela(tableVisualizarProdutos);

		JButton btnVisualizarProdutos = new JButton("Visualizar");
		btnVisualizarProdutos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exibirProdutos(rdbtnTodosProdutos.isSelected());
			}
		});
		btnVisualizarProdutos.setIcon(new ImageIcon(new ImageIcon(JAdministrador.class.getResource("/img/visualizar-produtos.png")).getImage().getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH)));
		btnVisualizarProdutos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnVisualizarProdutos.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		btnVisualizarProdutos.setBounds(1208, 263, 171, 49);
		panelVisualizarProdutos.add(btnVisualizarProdutos);

		rdbtnTodosProdutos = new JRadioButton("Todos");
		rdbtnTodosProdutos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exibirProdutos(rdbtnTodosProdutos.isSelected());
			}
		});
		rdbtnTodosProdutos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		rdbtnTodosProdutos.setSelected(true);
		buttonGroupVisualizarProdutos.add(rdbtnTodosProdutos);
		rdbtnTodosProdutos.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		rdbtnTodosProdutos.setBounds(761, 246, 260, 29);
		panelVisualizarProdutos.add(rdbtnTodosProdutos);

		rdbtnEstoqueAbaixoMinimoProdutos = new JRadioButton("Estoque Abaixo do M\u00EDnimo");
		rdbtnEstoqueAbaixoMinimoProdutos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exibirProdutos(rdbtnTodosProdutos.isSelected());
			}
		});
		rdbtnEstoqueAbaixoMinimoProdutos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		buttonGroupVisualizarProdutos.add(rdbtnEstoqueAbaixoMinimoProdutos);
		rdbtnEstoqueAbaixoMinimoProdutos.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		rdbtnEstoqueAbaixoMinimoProdutos.setBounds(761, 283, 260, 29);
		panelVisualizarProdutos.add(rdbtnEstoqueAbaixoMinimoProdutos);

		JButton btnExcluirProdutos = new JButton("Excluir");
		btnExcluirProdutos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<Integer> arrayListCodigoProduto = new ArrayList<>();

				for(int i = 0 ; i < tableVisualizarProdutos.getRowCount(); i++) {
					Boolean checked = Boolean.valueOf(tableVisualizarProdutos.getValueAt(i, 0).toString());
					Integer checkedCodigo = Integer.parseInt(tableVisualizarProdutos.getValueAt(i, 1).toString());

					if(checked)
						arrayListCodigoProduto.add(checkedCodigo);
				}

				if (arrayListCodigoProduto.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Não há nenhum produto selecionado para excluir.", "FALHA AO EXCLUIR", JOptionPane.WARNING_MESSAGE);
				} else {
					try {
						Comercial.excluirProduto(arrayListCodigoProduto);
						exibirProdutos(true);
						JOptionPane.showMessageDialog(null, "Produto(s) excluído(s) do banco de dados.", "EXCLUSÃO REALIZADA", JOptionPane.INFORMATION_MESSAGE);
					} catch (SisComException sisComException) {
						JOptionPane.showMessageDialog(null, sisComException.getMensagemErro(), "ERRO AO EXCLUIR", JOptionPane.WARNING_MESSAGE);
					} catch (HibernateException hibernateException) {
						JOptionPane.showMessageDialog(null, "Erro ao excluir produto(s).\nErro: " + hibernateException.getMessage(), "ERRO AO EXCLUIR", JOptionPane.ERROR_MESSAGE);
					} catch (Exception exception) {
						JOptionPane.showMessageDialog(null, "Erro ao excluir produto(s).\nErro: " + exception.getMessage(), "ERRO AO EXCLUIR", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnExcluirProdutos.setIcon(new ImageIcon(new ImageIcon(JAdministrador.class.getResource("/img/excluir.png")).getImage().getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH)));
		btnExcluirProdutos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnExcluirProdutos.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		btnExcluirProdutos.setBounds(1027, 263, 171, 49);
		panelVisualizarProdutos.add(btnExcluirProdutos);

		JLabel lblCdigoParaBusca = new JLabel("C\u00F3digo para Busca:");
		lblCdigoParaBusca.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		lblCdigoParaBusca.setBounds(0, 277, 180, 20);
		panelVisualizarProdutos.add(lblCdigoParaBusca);

		formattedTextFieldCodigoProdutoBusca = new JFormattedTextField();
		formattedTextFieldCodigoProdutoBusca.setBorder(new LineBorder(Color.BLACK));
		formattedTextFieldCodigoProdutoBusca.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		formattedTextFieldCodigoProdutoBusca.setBounds(195, 275, 200, 26);
		permitirApenasNumeros(formattedTextFieldCodigoProdutoBusca);
		panelVisualizarProdutos.add(formattedTextFieldCodigoProdutoBusca);

		JButton btnConsultarProduto = new JButton("Consultar");
		btnConsultarProduto.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnConsultarProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Produto produto = null;
					if (!formattedTextFieldCodigoProdutoBusca.getText().equals(""))
						produto = Comercial.consultarProduto(Integer.parseInt(formattedTextFieldCodigoProdutoBusca.getText()));

					if (produto != null) {
						DefaultTableModel defaultTableModel = (DefaultTableModel) tableVisualizarProdutos.getModel();
						Object rowData[] = new Object[7];

						defaultTableModel.setRowCount(0);
						rowData[0] = false;
						rowData[1] = produto.getCodigo();
						rowData[2] = produto.getNome();
						rowData[3] = produto.getPrecoUnitario();
						rowData[4] = produto.getEstoque();
						rowData[5] = produto.getEstoqueMinimo();
						rowData[6] = produto.getDataCad();
						defaultTableModel.addRow(rowData);
					} else {
						JOptionPane.showMessageDialog(null, "Produto não encontrado.", "FALHA AO BUSCAR", JOptionPane.WARNING_MESSAGE);
					}
				} catch (HibernateException hibernateException) {
					JOptionPane.showMessageDialog(null, "Erro ao buscar produto.\n" + hibernateException.getMessage(), "ERRO AO BUSCAR", JOptionPane.ERROR_MESSAGE);
				} catch (Exception exception) {
					JOptionPane.showMessageDialog(null, "Erro ao buscar produto.\n" + exception.getMessage(), "ERRO AO BUSCAR", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnConsultarProduto.setIcon(new ImageIcon(new ImageIcon(JAdministrador.class.getResource("/img/consultar.png")).getImage().getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH)));
		btnConsultarProduto.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		btnConsultarProduto.setBounds(410, 263, 171, 49);
		panelVisualizarProdutos.add(btnConsultarProduto);

		panelVisualizarVendas = new JPanel();
		panelVisualizarVendas.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		tabbedPaneAdministrador.addTab("", new ImageIcon(new ImageIcon(JAdministrador.class.getResource("/img/visualizar-vendas.png")).getImage().getScaledInstance(60, 60, java.awt.Image.SCALE_SMOOTH)), panelVisualizarVendas, "Visualizar Vendas");
		panelVisualizarVendas.setLayout(null);

		// Label para aba de visualizar vendas
		Icon iconAbaVisualizarVendas = new ImageIcon(new ImageIcon(JAdministrador.class.getResource("/img/visualizar-vendas.png")).getImage().getScaledInstance(60, 60, java.awt.Image.SCALE_SMOOTH));
		JLabel labelAbaVisualizarVendas = makeLabel(JLabel.BOTTOM, JLabel.CENTER, iconAbaVisualizarVendas, "Visualizar Vendas");
		labelAbaVisualizarVendas.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tabbedPaneAdministrador.setTabComponentAt(8, labelAbaVisualizarVendas);

		JScrollPane scrollPaneVisualizarVendas = new JScrollPane();
		scrollPaneVisualizarVendas.setBounds(0, 0, 1379, 234);
		panelVisualizarVendas.add(scrollPaneVisualizarVendas);

		tableVisualizarVendas = new JTable() {
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
				case 5:
					return Object.class;
				case 6:
					return Object.class;
				case 7:
					return Object.class;
				default:
					return Object.class;
				}
			}
		};
		tableVisualizarVendas.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tableVisualizarVendas.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Selecione", "N\u00FAmero", "C\u00F3digo do Cliente", "Nome do Cliente", "C\u00F3digo do Vendedor", "Nome do Vendedor", "Forma de Pagamento", "Data da Venda"
				}
				) {
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
					true, false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tableVisualizarVendas.getColumnModel().getColumn(0).setPreferredWidth(100);
		tableVisualizarVendas.getColumnModel().getColumn(2).setPreferredWidth(120);
		tableVisualizarVendas.getColumnModel().getColumn(3).setPreferredWidth(150);
		tableVisualizarVendas.getColumnModel().getColumn(4).setPreferredWidth(120);
		tableVisualizarVendas.getColumnModel().getColumn(5).setPreferredWidth(150);
		tableVisualizarVendas.getColumnModel().getColumn(6).setPreferredWidth(150);
		tableVisualizarVendas.getColumnModel().getColumn(7).setPreferredWidth(200);
		tableVisualizarVendas.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 12));
		tableVisualizarVendas.getTableHeader().setReorderingAllowed(false);
		scrollPaneVisualizarVendas.setViewportView(tableVisualizarVendas);

		// Alinhamento horizontal e fonte: cabeçalho da tabela de vendas
		((JLabel) tableVisualizarVendas.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
		tableVisualizarVendas.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));

		// Cor intercalada nas linhas: tabela de vendas
		gerenciarComportamentoTabela(tableVisualizarVendas);

		JLabel lblOrdenarNomeClienteVendedor = new JLabel("Nome para Ordenar:");
		lblOrdenarNomeClienteVendedor.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		lblOrdenarNomeClienteVendedor.setBounds(0, 277, 180, 20);
		panelVisualizarVendas.add(lblOrdenarNomeClienteVendedor);

		formattedTextFieldOrdenarNomeClienteVendedor = new JFormattedTextField();
		formattedTextFieldOrdenarNomeClienteVendedor.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		formattedTextFieldOrdenarNomeClienteVendedor.setBorder(new LineBorder(Color.BLACK));
		formattedTextFieldOrdenarNomeClienteVendedor.setBounds(195, 275, 200, 26);
		panelVisualizarVendas.add(formattedTextFieldOrdenarNomeClienteVendedor);

		JLabel lblDataFinalConsultaVendas = new JLabel("Data Final:");
		lblDataFinalConsultaVendas.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDataFinalConsultaVendas.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		lblDataFinalConsultaVendas.setBounds(759, 290, 125, 20);
		panelVisualizarVendas.add(lblDataFinalConsultaVendas);

		JLabel lblDataInicialConsultaVendas = new JLabel("Data Inicial:");
		lblDataInicialConsultaVendas.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDataInicialConsultaVendas.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		lblDataInicialConsultaVendas.setBounds(759, 256, 125, 20);
		panelVisualizarVendas.add(lblDataInicialConsultaVendas);

		dateChooserDataInicialConsultaVendas = new JDateChooser();
		dateChooserDataInicialConsultaVendas.getCalendarButton().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		dateChooserDataInicialConsultaVendas.setBorder(new LineBorder(new Color(0, 0, 0)));
		dateChooserDataInicialConsultaVendas.setDateFormatString("dd-MM-yyyy");
		dateChooserDataInicialConsultaVendas.setBounds(899, 250, 113, 26);
		panelVisualizarVendas.add(dateChooserDataInicialConsultaVendas);

		dateChooserDataFinalConsultaVendas = new JDateChooser();
		dateChooserDataFinalConsultaVendas.getCalendarButton().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		dateChooserDataFinalConsultaVendas.setBorder(new LineBorder(new Color(0, 0, 0)));
		dateChooserDataFinalConsultaVendas.setDateFormatString("dd-MM-yyyy");
		dateChooserDataFinalConsultaVendas.setBounds(899, 284, 113, 26);
		panelVisualizarVendas.add(dateChooserDataFinalConsultaVendas);

		// Desabilitar edição manual nos campos para data de consulta de vendas
		((JTextFieldDateEditor) dateChooserDataInicialConsultaVendas.getDateEditor()).setEditable(false);
		((JTextFieldDateEditor) dateChooserDataFinalConsultaVendas.getDateEditor()).setEditable(false);

		rdbtnOrdenarCliente = new JRadioButton("Ordenar por Cliente");
		rdbtnOrdenarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				exibirVendas(rdbtnOrdenarCliente.isSelected());
			}
		});
		rdbtnOrdenarCliente.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		buttonGroupOrdenarVendas.add(rdbtnOrdenarCliente);
		rdbtnOrdenarCliente.setSelected(true);
		rdbtnOrdenarCliente.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		rdbtnOrdenarCliente.setBounds(406, 244, 260, 29);
		panelVisualizarVendas.add(rdbtnOrdenarCliente);

		rdbtnOrdenarVendedor = new JRadioButton("Ordenar por Vendedor");
		rdbtnOrdenarVendedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exibirVendas(rdbtnOrdenarCliente.isSelected());
			}
		});
		rdbtnOrdenarVendedor.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		buttonGroupOrdenarVendas.add(rdbtnOrdenarVendedor);
		rdbtnOrdenarVendedor.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		rdbtnOrdenarVendedor.setBounds(406, 281, 260, 29);
		panelVisualizarVendas.add(rdbtnOrdenarVendedor);

		JButton btnExcluirVendas = new JButton("Excluir");
		btnExcluirVendas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<Integer> arrayListNumeroVenda = new ArrayList<>();

				for(int i = 0 ; i < tableVisualizarVendas.getRowCount(); i++) {
					Boolean checked = Boolean.valueOf(tableVisualizarVendas.getValueAt(i, 0).toString());
					Integer checkedCodigo = Integer.parseInt(tableVisualizarVendas.getValueAt(i, 1).toString());

					if(checked)
						arrayListNumeroVenda.add(checkedCodigo);
				}

				if (arrayListNumeroVenda.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Não há nenhuma venda selecionada para excluir.", "FALHA AO EXCLUIR", JOptionPane.WARNING_MESSAGE);
				} else {
					try {
						Comercial.excluirVenda(arrayListNumeroVenda);
						exibirVendas(true);
						JOptionPane.showMessageDialog(null, "Venda(s) excluída(s) do banco de dados.", "EXCLUSÃO REALIZADA", JOptionPane.INFORMATION_MESSAGE);
					} catch (HibernateException hibernateException) {
						JOptionPane.showMessageDialog(null, "Erro ao excluir venda(s).\nErro: " + hibernateException.getMessage(), "ERRO AO EXCLUIR", JOptionPane.ERROR_MESSAGE);
					} catch (Exception exception) {
						JOptionPane.showMessageDialog(null, "Erro ao excluir venda(s).\nErro: " + exception.getMessage(), "ERRO AO EXCLUIR", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnExcluirVendas.setIcon(new ImageIcon(new ImageIcon(JAdministrador.class.getResource("/img/excluir.png")).getImage().getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH)));
		btnExcluirVendas.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnExcluirVendas.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		btnExcluirVendas.setBounds(1027, 263, 171, 49);
		panelVisualizarVendas.add(btnExcluirVendas);

		JButton btnVisualizarVendas = new JButton("Visualizar");
		btnVisualizarVendas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exibirVendas(true);
			}
		});
		btnVisualizarVendas.setIcon(new ImageIcon(new ImageIcon(JAdministrador.class.getResource("/img/visualizar-vendas.png")).getImage().getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH)));
		btnVisualizarVendas.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnVisualizarVendas.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		btnVisualizarVendas.setBounds(1208, 263, 171, 49);
		panelVisualizarVendas.add(btnVisualizarVendas);

		panelVisualizarCompras = new JPanel();
		panelVisualizarCompras.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		tabbedPaneAdministrador.addTab("", new ImageIcon(new ImageIcon(JAdministrador.class.getResource("/img/visualizar-compras.png")).getImage().getScaledInstance(60, 60, java.awt.Image.SCALE_SMOOTH)), panelVisualizarCompras, "Visualizar Compras");
		panelVisualizarCompras.setLayout(null);

		// Label para aba de visualizar compras
		Icon iconAbaVisualizarCompras = new ImageIcon(new ImageIcon(JAdministrador.class.getResource("/img/visualizar-compras.png")).getImage().getScaledInstance(60, 60, java.awt.Image.SCALE_SMOOTH));
		JLabel labelAbaVisualizarCompras = makeLabel(JLabel.BOTTOM, JLabel.CENTER, iconAbaVisualizarCompras, "Visualizar Compras");
		labelAbaVisualizarCompras.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tabbedPaneAdministrador.setTabComponentAt(9, labelAbaVisualizarCompras);

		JScrollPane scrollPaneVisualizarCompras = new JScrollPane();
		scrollPaneVisualizarCompras.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		scrollPaneVisualizarCompras.setBounds(0, 0, 1379, 234);
		panelVisualizarCompras.add(scrollPaneVisualizarCompras);

		tableVisualizarCompras = new JTable() {
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
		tableVisualizarCompras.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tableVisualizarCompras.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Selecione", "N\u00FAmero", "C\u00F3digo do Fornecedor", "Nome do Fornecedor", "Data da Compra"
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
		tableVisualizarCompras.getColumnModel().getColumn(0).setResizable(false);
		tableVisualizarCompras.getColumnModel().getColumn(0).setPreferredWidth(100);
		tableVisualizarCompras.getColumnModel().getColumn(1).setResizable(false);
		tableVisualizarCompras.getColumnModel().getColumn(1).setPreferredWidth(100);
		tableVisualizarCompras.getColumnModel().getColumn(2).setPreferredWidth(200);
		tableVisualizarCompras.getColumnModel().getColumn(3).setPreferredWidth(200);
		tableVisualizarCompras.getColumnModel().getColumn(4).setPreferredWidth(200);
		tableVisualizarCompras.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 12));
		tableVisualizarCompras.getTableHeader().setReorderingAllowed(false);
		scrollPaneVisualizarCompras.setViewportView(tableVisualizarCompras);

		// Alinhamento horizontal e fonte: cabeçalho da tabela de compras
		((JLabel) tableVisualizarCompras.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
		tableVisualizarCompras.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));

		// Cor intercalada nas linhas: tabela de compras
		gerenciarComportamentoTabela(tableVisualizarCompras);

		JLabel lblOrdernarNomeFornecedor = new JLabel("Nome para Ordenar:");
		lblOrdernarNomeFornecedor.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		lblOrdernarNomeFornecedor.setBounds(0, 277, 180, 20);
		panelVisualizarCompras.add(lblOrdernarNomeFornecedor);

		formattedTextFieldOrdernarNomeFornecedor = new JFormattedTextField();
		formattedTextFieldOrdernarNomeFornecedor.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		formattedTextFieldOrdernarNomeFornecedor.setBorder(new LineBorder(Color.BLACK));
		formattedTextFieldOrdernarNomeFornecedor.setBounds(195, 275, 200, 26);
		panelVisualizarCompras.add(formattedTextFieldOrdernarNomeFornecedor);

		JLabel lblDataInicialConsultaCompras = new JLabel("Data Inicial:");
		lblDataInicialConsultaCompras.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDataInicialConsultaCompras.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		lblDataInicialConsultaCompras.setBounds(759, 256, 125, 20);
		panelVisualizarCompras.add(lblDataInicialConsultaCompras);

		JLabel lblDataFinalConsultaCompras = new JLabel("Data Final:");
		lblDataFinalConsultaCompras.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDataFinalConsultaCompras.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		lblDataFinalConsultaCompras.setBounds(759, 290, 125, 20);
		panelVisualizarCompras.add(lblDataFinalConsultaCompras);

		dateChooserDataInicialConsultaCompras = new JDateChooser();
		dateChooserDataInicialConsultaCompras.getCalendarButton().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		dateChooserDataInicialConsultaCompras.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		dateChooserDataInicialConsultaCompras.setDateFormatString("dd-MM-yyyy");
		dateChooserDataInicialConsultaCompras.setBorder(new LineBorder(new Color(0, 0, 0)));
		dateChooserDataInicialConsultaCompras.setBounds(899, 250, 113, 26);
		panelVisualizarCompras.add(dateChooserDataInicialConsultaCompras);

		dateChooserDataFinalConsultaCompras = new JDateChooser();
		dateChooserDataFinalConsultaCompras.getCalendarButton().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		dateChooserDataFinalConsultaCompras.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		dateChooserDataFinalConsultaCompras.setDateFormatString("dd-MM-yyyy");
		dateChooserDataFinalConsultaCompras.setBorder(new LineBorder(new Color(0, 0, 0)));
		dateChooserDataFinalConsultaCompras.setBounds(899, 284, 113, 26);
		panelVisualizarCompras.add(dateChooserDataFinalConsultaCompras);

		// Desabilitar edição manual nos campos para data de consulta de compras
		((JTextFieldDateEditor) dateChooserDataInicialConsultaCompras.getDateEditor()).setEditable(false);
		((JTextFieldDateEditor) dateChooserDataFinalConsultaCompras.getDateEditor()).setEditable(false);

		JButton btnExcluirCompras = new JButton("Excluir");
		btnExcluirCompras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ArrayList<Integer> arrayListNumeroCompra = new ArrayList<>();

				for(int i = 0 ; i < tableVisualizarCompras.getRowCount(); i++) {
					Boolean checked = Boolean.valueOf(tableVisualizarCompras.getValueAt(i, 0).toString());
					Integer checkedCodigo = Integer.parseInt(tableVisualizarCompras.getValueAt(i, 1).toString());

					if(checked)
						arrayListNumeroCompra.add(checkedCodigo);
				}

				if (arrayListNumeroCompra.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Não há nenhuma compra selecionada para excluir.", "FALHA AO EXCLUIR", JOptionPane.WARNING_MESSAGE);
				} else {
					try {
						Comercial.excluirCompra(arrayListNumeroCompra);
						exibirCompras();
						JOptionPane.showMessageDialog(null, "Compra(s) excluída(s) do banco de dados.", "EXCLUSÃO REALIZADA", JOptionPane.INFORMATION_MESSAGE);
					} catch (HibernateException hibernateException) {
						JOptionPane.showMessageDialog(null, "Erro ao excluir compra(s).\nErro: " + hibernateException.getMessage(), "ERRO AO EXCLUIR", JOptionPane.ERROR_MESSAGE);
					} catch (Exception exception) {
						JOptionPane.showMessageDialog(null, "Erro ao excluir compra(s).\nErro: " + exception.getMessage(), "ERRO AO EXCLUIR", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnExcluirCompras.setIcon(new ImageIcon(new ImageIcon(JAdministrador.class.getResource("/img/excluir.png")).getImage().getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH)));
		btnExcluirCompras.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnExcluirCompras.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		btnExcluirCompras.setBounds(1027, 263, 171, 49);
		panelVisualizarCompras.add(btnExcluirCompras);

		JButton btnVisualizarCompras = new JButton("Visualizar");
		btnVisualizarCompras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exibirCompras();
			}
		});
		btnVisualizarCompras.setIcon(new ImageIcon(new ImageIcon(JAdministrador.class.getResource("/img/visualizar-compras.png")).getImage().getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH)));
		btnVisualizarCompras.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnVisualizarCompras.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		btnVisualizarCompras.setBounds(1208, 263, 171, 49);
		panelVisualizarCompras.add(btnVisualizarCompras);

		panelVisualizarEstatisticas = new JPanel();
		panelVisualizarEstatisticas.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		tabbedPaneAdministrador.addTab("", new ImageIcon(new ImageIcon(JAdministrador.class.getResource("/img/visualizar-estatisticas.png")).getImage().getScaledInstance(60, 60, java.awt.Image.SCALE_SMOOTH)), panelVisualizarEstatisticas, "Visualizar Estatísticas");
		panelVisualizarEstatisticas.setLayout(null);

		// Label para aba de visualizar estatísticas
		Icon iconAbaVisualizarEstatisticas = new ImageIcon(new ImageIcon(JAdministrador.class.getResource("/img/visualizar-estatisticas.png")).getImage().getScaledInstance(60, 60, java.awt.Image.SCALE_SMOOTH));
		JLabel labelAbaVisualizarEstatisticas = makeLabel(JLabel.BOTTOM, JLabel.CENTER, iconAbaVisualizarEstatisticas, "Visualizar Estatísticas");
		labelAbaVisualizarEstatisticas.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tabbedPaneAdministrador.setTabComponentAt(10, labelAbaVisualizarEstatisticas);

		JScrollPane scrollPaneVisualizarEstatisticas = new JScrollPane();
		scrollPaneVisualizarEstatisticas.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		scrollPaneVisualizarEstatisticas.setBounds(0, 0, 1379, 197);
		panelVisualizarEstatisticas.add(scrollPaneVisualizarEstatisticas);

		tableVisualizarEstatisticas = new JTable() {
			private static final long serialVersionUID = 1L;
			@Override
			public Class<?> getColumnClass(int column) {
				switch (column) {
				case 0:
					return Object.class;
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
		tableVisualizarEstatisticas.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tableVisualizarEstatisticas.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"C\u00F3digo", "Nome", "Quantidade de Opera\u00E7\u00F5es", "Valor total das Opera\u00E7\u00F5es"
				}
				) {
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
					false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tableVisualizarEstatisticas.getColumnModel().getColumn(0).setResizable(false);
		tableVisualizarEstatisticas.getColumnModel().getColumn(1).setPreferredWidth(250);
		tableVisualizarEstatisticas.getColumnModel().getColumn(2).setPreferredWidth(250);
		tableVisualizarEstatisticas.getColumnModel().getColumn(3).setPreferredWidth(250);
		tableVisualizarEstatisticas.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 12));
		tableVisualizarEstatisticas.getTableHeader().setReorderingAllowed(false);
		scrollPaneVisualizarEstatisticas.setViewportView(tableVisualizarEstatisticas);

		// Alinhamento horizontal e fonte: cabeçalho da tabela de estatísticas
		((JLabel) tableVisualizarEstatisticas.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
		tableVisualizarEstatisticas.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));

		// Cor intercalada nas linhas: tabela de estatísticas
		gerenciarComportamentoTabela(tableVisualizarEstatisticas);

		JLabel lblDataInicialEstatisticas = new JLabel("Data Inicial:");
		lblDataInicialEstatisticas.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDataInicialEstatisticas.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		lblDataInicialEstatisticas.setBounds(759, 256, 125, 20);
		panelVisualizarEstatisticas.add(lblDataInicialEstatisticas);

		JLabel lblDataFinalEstatisticas = new JLabel("Data Final:");
		lblDataFinalEstatisticas.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDataFinalEstatisticas.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		lblDataFinalEstatisticas.setBounds(759, 290, 125, 20);
		panelVisualizarEstatisticas.add(lblDataFinalEstatisticas);

		dateChooserDataInicialEstatisticas = new JDateChooser();
		dateChooserDataInicialEstatisticas.getCalendarButton().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		dateChooserDataInicialEstatisticas.setDateFormatString("dd-MM-yyyy");
		dateChooserDataInicialEstatisticas.setBorder(new LineBorder(new Color(0, 0, 0)));
		dateChooserDataInicialEstatisticas.setBounds(899, 250, 113, 26);
		panelVisualizarEstatisticas.add(dateChooserDataInicialEstatisticas);

		dateChooserDataFinalEstatisticas = new JDateChooser();
		dateChooserDataFinalEstatisticas.getCalendarButton().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		dateChooserDataFinalEstatisticas.setDateFormatString("dd-MM-yyyy");
		dateChooserDataFinalEstatisticas.setBorder(new LineBorder(new Color(0, 0, 0)));
		dateChooserDataFinalEstatisticas.setBounds(899, 284, 113, 26);
		panelVisualizarEstatisticas.add(dateChooserDataFinalEstatisticas);

		// Desabilitar edição manual nos campos para data de consulta de estatísticas
		((JTextFieldDateEditor) dateChooserDataInicialEstatisticas.getDateEditor()).setEditable(false);
		((JTextFieldDateEditor) dateChooserDataFinalEstatisticas.getDateEditor()).setEditable(false);

		rdbtnEstatitiscaCliente = new JRadioButton("Estat\u00EDstica de Cliente");
		rdbtnEstatitiscaCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exibirEstatisticas('C');
			}
		});
		buttonGroupVisualizarEstatisticas.add(rdbtnEstatitiscaCliente);
		rdbtnEstatitiscaCliente.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		rdbtnEstatitiscaCliente.setSelected(true);
		rdbtnEstatitiscaCliente.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		rdbtnEstatitiscaCliente.setBounds(473, 209, 275, 29);
		panelVisualizarEstatisticas.add(rdbtnEstatitiscaCliente);

		rdbtnEstatitiscaVendedor = new JRadioButton("Estat\u00EDstica de Vendedor");
		rdbtnEstatitiscaVendedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exibirEstatisticas('V');
			}
		});
		buttonGroupVisualizarEstatisticas.add(rdbtnEstatitiscaVendedor);
		rdbtnEstatitiscaVendedor.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		rdbtnEstatitiscaVendedor.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		rdbtnEstatitiscaVendedor.setBounds(473, 246, 275, 29);
		panelVisualizarEstatisticas.add(rdbtnEstatitiscaVendedor);

		rdbtnEstatitiscaFornecedor = new JRadioButton("Estat\u00EDstica de Fornecedor");
		rdbtnEstatitiscaFornecedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exibirEstatisticas('F');
			}
		});
		buttonGroupVisualizarEstatisticas.add(rdbtnEstatitiscaFornecedor);
		rdbtnEstatitiscaFornecedor.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		rdbtnEstatitiscaFornecedor.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		rdbtnEstatitiscaFornecedor.setBounds(473, 283, 275, 29);
		panelVisualizarEstatisticas.add(rdbtnEstatitiscaFornecedor);

		JButton btnVisualizarEstatisticas = new JButton("Visualizar");
		btnVisualizarEstatisticas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdbtnEstatitiscaCliente.isSelected())
					exibirEstatisticas('C');
				else if (rdbtnEstatitiscaVendedor.isSelected())
					exibirEstatisticas('V');
				else if (rdbtnEstatitiscaFornecedor.isSelected())
					exibirEstatisticas('F');
			}
		});
		btnVisualizarEstatisticas.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnVisualizarEstatisticas.setIcon(new ImageIcon(new ImageIcon(JAdministrador.class.getResource("/img/visualizar-estatisticas.png")).getImage().getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH)));
		btnVisualizarEstatisticas.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		btnVisualizarEstatisticas.setBounds(1208, 263, 171, 49);
		panelVisualizarEstatisticas.add(btnVisualizarEstatisticas);

		JPanel panelSouth = new JPanel();
		panelSouth.setPreferredSize(new Dimension(10, 80));
		panelSouth.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		contentPaneAdministrador.add(panelSouth, BorderLayout.SOUTH);

		JLabel lblVersaoSistema = new JLabel("SystemCommerce Vers\u00E3o 1.0");
		lblVersaoSistema.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		lblVersaoSistema.setBounds(10, 51, 260, 14);
		panelSouth.add(lblVersaoSistema);

		JLabel lblDataTempoAtual = new JLabel("HH:mm:ss dd-MM-yyyy");
		lblDataTempoAtual.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		lblDataTempoAtual.setBounds(325, 51, 200, 14);
		panelSouth.add(lblDataTempoAtual);
		JActualDateTime.getCurrentDateTime(lblDataTempoAtual);

		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int aba = tabbedPaneAdministrador.getSelectedIndex();
				limparCampos(aba);
			}
		});
		btnLimpar.setIcon(new ImageIcon(new ImageIcon(JAdministrador.class.getResource("/img/limpar.png")).getImage().getScaledInstance(60, 40, java.awt.Image.SCALE_SMOOTH)));
		btnLimpar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnLimpar.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		btnLimpar.setBounds(1030, 16, 171, 49);
		panelSouth.add(btnLimpar);

		JButton btnSair = new JButton("Sair");
		btnSair.setBounds(1211, 16, 171, 49);
		btnSair.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSair.setIcon(new ImageIcon(new ImageIcon(JAdministrador.class.getResource("/img/sair.png")).getImage().getScaledInstance(80, 40, java.awt.Image.SCALE_SMOOTH)));
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
		panelSouth.setLayout(null);
		btnSair.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		panelSouth.add(btnSair);

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

		// AO MUDAR DE ABA ESSA PROPRIEDADE É ALTERADA (Event)
		// Definir botão padrão para a tecla ENTER
		JRootPane rootPane = SwingUtilities.getRootPane(btnCadastrarCliente);
		rootPane.setDefaultButton(btnCadastrarCliente);

		// Evento acionado ao mudar de aba
		tabbedPaneAdministrador.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				int aba = tabbedPaneAdministrador.getSelectedIndex();
				limparCampos(aba);

				if (aba == 0) {
					JRootPane rootPane = SwingUtilities.getRootPane(btnCadastrarCliente);
					rootPane.setDefaultButton(btnCadastrarCliente);
				} else if (aba == 1) {
					exibirClientes();
					JRootPane rootPane = SwingUtilities.getRootPane(btnVisualizarClientes);
					rootPane.setDefaultButton(btnVisualizarClientes);
					tableVisualizarClientes.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, null);
					tableVisualizarClientes.setFocusTraversalKeys(KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS, null);
					tableVisualizarClientes.setFocusCycleRoot(false);
				} else if (aba == 2) {
					JRootPane rootPane = SwingUtilities.getRootPane(btnCadastrarFornecedor);
					rootPane.setDefaultButton(btnCadastrarFornecedor);
				} else if (aba == 3) {
					exibirFornecedores();
					JRootPane rootPane = SwingUtilities.getRootPane(btnVisualizarFornecedores);
					rootPane.setDefaultButton(btnVisualizarFornecedores);
					tableVisualizarFornecedores.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, null);
					tableVisualizarFornecedores.setFocusTraversalKeys(KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS, null);
					tableVisualizarFornecedores.setFocusCycleRoot(false);
				} else if (aba == 4) {
					JRootPane rootPane = SwingUtilities.getRootPane(btnCadastrarVendedor);
					rootPane.setDefaultButton(btnCadastrarVendedor);
				} else if (aba == 5) {
					exibirVendedores();
					JRootPane rootPane = SwingUtilities.getRootPane(btnVisualizarVendedores);
					rootPane.setDefaultButton(btnVisualizarVendedores);
					tableVisualizarVendedores.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, null);
					tableVisualizarVendedores.setFocusTraversalKeys(KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS, null);
					tableVisualizarVendedores.setFocusCycleRoot(false);
				} else if (aba == 6) {
					JRootPane rootPane = SwingUtilities.getRootPane(btnCadastrarProduto);
					rootPane.setDefaultButton(btnCadastrarProduto);
				} else if (aba == 7) {
					exibirProdutos(true);
					rdbtnTodosProdutos.setSelected(true);
					JRootPane rootPane = SwingUtilities.getRootPane(btnVisualizarProdutos);
					rootPane.setDefaultButton(btnVisualizarProdutos);
					tableVisualizarProdutos.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, null);
					tableVisualizarProdutos.setFocusTraversalKeys(KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS, null);
					tableVisualizarProdutos.setFocusCycleRoot(false);
				} else if (aba == 8) {
					exibirVendas(true);
					rdbtnOrdenarCliente.setSelected(true);
					JRootPane rootPane = SwingUtilities.getRootPane(btnVisualizarVendas);
					rootPane.setDefaultButton(btnVisualizarVendas);
					tableVisualizarVendas.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, null);
					tableVisualizarVendas.setFocusTraversalKeys(KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS, null);
					tableVisualizarVendas.setFocusCycleRoot(false);
				} else if (aba == 9) {
					exibirCompras();
					JRootPane rootPane = SwingUtilities.getRootPane(btnVisualizarCompras);
					rootPane.setDefaultButton(btnVisualizarCompras);
					tableVisualizarCompras.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, null);
					tableVisualizarCompras.setFocusTraversalKeys(KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS, null);
					tableVisualizarCompras.setFocusCycleRoot(false);
				} else if (aba == 10) {
					exibirEstatisticas('C');
					rdbtnEstatitiscaCliente.setSelected(true);
					JRootPane rootPane = SwingUtilities.getRootPane(btnVisualizarEstatisticas);
					rootPane.setDefaultButton(btnVisualizarEstatisticas);
					tableVisualizarEstatisticas.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, null);
					tableVisualizarEstatisticas.setFocusTraversalKeys(KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS, null);
					tableVisualizarEstatisticas.setFocusCycleRoot(false);
				}
			}
		});
	}

	// Método para limpar campos
	private void limparCampos(int aba) {
		if (aba == 0) {
			textFieldNomeCliente.setText(null);
			formattedTextFieldTelefoneFixoCliente.setValue(null);
			formattedTextFieldTelefoneCelularCliente.setValue(null);
			formattedTextFieldEmailCliente.setText(null);
			formattedTextFieldCpfCliente.setValue(null);
			formattedTextFieldLimiteCreditoCliente.setText(null);
			textFieldLoginCliente.setText(null);
			passwordFieldSenhaCliente.setText(null);
			passwordFieldConfirmarSenhaCliente.setText(null);
		} else if (aba == 1) {
			((DefaultTableModel) tableVisualizarClientes.getModel()).setRowCount(0);
			formattedTextFieldCpfClienteBusca.setValue(null);
		} else if (aba == 2) {
			textFieldNomeFornecedor.setText(null);
			formattedTextFieldTelefoneFixoFornecedor.setValue(null);
			formattedTextFieldTelefoneCelularFornecedor.setValue(null);
			formattedTextFieldEmailFornecedor.setText(null);
			formattedTextFieldCnpjFornecedor.setValue(null);
			textFieldNomeContatoFornecedor.setText(null);
			textFieldLoginFornecedor.setText(null);
			passwordFieldSenhaFornecedor.setText(null);
			passwordFieldConfirmarSenhaFornecedor.setText(null);
		} else if (aba == 3) {
			((DefaultTableModel) tableVisualizarFornecedores.getModel()).setRowCount(0);
			formattedTextFieldCnpjFornecedorBusca.setValue(null);
		} else if (aba == 4) {
			textFieldNomeVendedor.setText(null);
			formattedTextFieldTelefoneFixoVendedor.setValue(null);
			formattedTextFieldTelefoneCelularVendedor.setValue(null);
			formattedTextFieldEmailVendedor.setText(null);
			formattedTextFieldCpfVendedor.setValue(null);
			formattedTextFieldMetaMensalVendedor.setText(null);
			textFieldLoginVendedor.setText(null);
			passwordFieldSenhaVendedor.setText(null);
			passwordFieldConfirmarSenhaVendedor.setText(null);
		} else if (aba == 5) {
			((DefaultTableModel) tableVisualizarVendedores.getModel()).setRowCount(0);
			formattedTextFieldCpfVendedorBusca.setValue(null);
		} else if (aba == 6) {
			textFieldNomeProduto.setText(null);
			formattedTextFieldPrecoUnitarioProduto.setText(null);
			formattedTextFieldEstoqueMinimoProduto.setText(null);
			formattedTextFieldEstoqueProduto.setText("0");
		} else if (aba == 7) {
			((DefaultTableModel) tableVisualizarProdutos.getModel()).setRowCount(0);
			formattedTextFieldCodigoProdutoBusca.setText(null);
		} else if (aba == 8) {
			((DefaultTableModel) tableVisualizarVendas.getModel()).setRowCount(0);
			formattedTextFieldOrdenarNomeClienteVendedor.setText(null);
			dateChooserDataInicialConsultaVendas.setDate((new GregorianCalendar(1970, 0, 1)).getTime());
			dateChooserDataFinalConsultaVendas.setDate(new Date());
		} else if (aba == 9) {
			((DefaultTableModel) tableVisualizarCompras.getModel()).setRowCount(0);
			formattedTextFieldOrdernarNomeFornecedor.setText(null);
			dateChooserDataInicialConsultaCompras.setDate((new GregorianCalendar(1970, 0, 1)).getTime());
			dateChooserDataFinalConsultaCompras.setDate(new Date());
		} else if (aba == 10) {
			((DefaultTableModel) tableVisualizarEstatisticas.getModel()).setRowCount(0);
			dateChooserDataInicialEstatisticas.setDate((new GregorianCalendar(1970, 0, 1)).getTime());
			dateChooserDataFinalEstatisticas.setDate(new Date());
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

	// Exibir Clientes
	private void exibirClientes() {
		try {
			ArrayList<Cliente> arrayListCliente = Comercial.consultarClientesOrdemAlfabetica();

			DefaultTableModel defaultTableModel = (DefaultTableModel) tableVisualizarClientes.getModel();
			Object rowData[] = new Object[11];

			defaultTableModel.setRowCount(0);
			for (int i = 0; i < arrayListCliente.size(); i++) {
				rowData[0] = false;
				rowData[1] = arrayListCliente.get(i).getCodigo();
				rowData[2] = arrayListCliente.get(i).getNome();
				rowData[3] = arrayListCliente.get(i).getTelefoneFixo();
				rowData[4] = arrayListCliente.get(i).getTelefoneCelular();
				rowData[5] = arrayListCliente.get(i).getEmail();
				rowData[6] = arrayListCliente.get(i).getDataCad();
				rowData[7] = arrayListCliente.get(i).getCpf();
				rowData[8] = arrayListCliente.get(i).getLimiteCredito();
				rowData[9] = arrayListCliente.get(i).getLogin();
				rowData[10] = arrayListCliente.get(i).getSenha();

				defaultTableModel.addRow(rowData);
			}
		} catch (HibernateException hibernateException) {
			JOptionPane.showMessageDialog(null, "Erro ao buscar e/ou exibir clientes do banco de dados.\n" + hibernateException.getMessage(), "ERRO AO VISUALIZAR", JOptionPane.ERROR_MESSAGE);
		} catch (Exception exception) {
			JOptionPane.showMessageDialog(null, "Erro ao buscar e/ou exibir clientes do banco de dados.\n" + exception.getMessage(), "ERRO AO VISUALIZAR", JOptionPane.ERROR_MESSAGE);
		}
	}

	// Exibir Fornecedores
	private void exibirFornecedores() {
		try {
			ArrayList<Fornecedor> arrayListFornecedor = Comercial.consultarFornecedoresOrdemAlfabetica();

			DefaultTableModel defaultTableModel = (DefaultTableModel) tableVisualizarFornecedores.getModel();
			Object rowData[] = new Object[11];

			defaultTableModel.setRowCount(0);
			for (int i = 0; i < arrayListFornecedor.size(); i++) {
				rowData[0] = false;
				rowData[1] = arrayListFornecedor.get(i).getCodigo();
				rowData[2] = arrayListFornecedor.get(i).getNome();
				rowData[3] = arrayListFornecedor.get(i).getTelefoneFixo();
				rowData[4] = arrayListFornecedor.get(i).getTelefoneCelular();
				rowData[5] = arrayListFornecedor.get(i).getEmail();
				rowData[6] = arrayListFornecedor.get(i).getDataCad();
				rowData[7] = arrayListFornecedor.get(i).getCnpj();
				rowData[8] = arrayListFornecedor.get(i).getNomeContato();
				rowData[9] = arrayListFornecedor.get(i).getLogin();
				rowData[10] = arrayListFornecedor.get(i).getSenha();

				defaultTableModel.addRow(rowData);
			}
		} catch (HibernateException hibernateException) {
			JOptionPane.showMessageDialog(null, "Erro ao buscar e/ou exibir fornecedores do banco de dados.\n" + hibernateException.getMessage(), "ERRO AO VISUALIZAR", JOptionPane.ERROR_MESSAGE);
		} catch (Exception exception) {
			JOptionPane.showMessageDialog(null, "Erro ao buscar e/ou exibir fornecedores do banco de dados.\n" + exception.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
		}
	}

	// Exibir Vendedores
	private void exibirVendedores() {
		try {
			ArrayList<Vendedor> arrayListVendedor = Comercial.consultarVendedoresOrdemAlfabetica();

			DefaultTableModel defaultTableModel = (DefaultTableModel) tableVisualizarVendedores.getModel();
			Object rowData[] = new Object[11];

			defaultTableModel.setRowCount(0);
			for (int i = 0; i < arrayListVendedor.size(); i++) {
				rowData[0] = false;
				rowData[1] = arrayListVendedor.get(i).getCodigo();
				rowData[2] = arrayListVendedor.get(i).getNome();
				rowData[3] = arrayListVendedor.get(i).getTelefoneFixo();
				rowData[4] = arrayListVendedor.get(i).getTelefoneCelular();
				rowData[5] = arrayListVendedor.get(i).getEmail();
				rowData[6] = arrayListVendedor.get(i).getDataCad();
				rowData[7] = arrayListVendedor.get(i).getCpf();
				rowData[8] = arrayListVendedor.get(i).getMetaMensal();
				rowData[9] = arrayListVendedor.get(i).getLogin();
				rowData[10] = arrayListVendedor.get(i).getSenha();

				defaultTableModel.addRow(rowData);
			}
		} catch (HibernateException hibernateException) {
			JOptionPane.showMessageDialog(null, "Erro ao buscar e/ou exibir vendedores do banco de dados.\n" + hibernateException.getMessage(), "ERRO AO VISUALIZAR", JOptionPane.ERROR_MESSAGE);
		} catch (Exception exception) {
			JOptionPane.showMessageDialog(null, "Erro ao buscar e/ou exibir vendedores do banco de dados.\n" + exception.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
		}
	}

	// Exibir Produtos
	private void exibirProdutos(boolean todosProdutos) {
		try {
			ArrayList<Produto> arrayListProduto = null;
			if (todosProdutos)
				arrayListProduto = Comercial.consultarProdutosOrdemAlfabetica();
			else
				arrayListProduto = Comercial.consultarProdutosEstoqueAbaixoMinimoOrdemAlfabetica();

			DefaultTableModel defaultTableModel = (DefaultTableModel) tableVisualizarProdutos.getModel();
			Object rowData[] = new Object[7];

			defaultTableModel.setRowCount(0);
			for (int i = 0; i < arrayListProduto.size(); i++) {
				rowData[0] = false;
				rowData[1] = arrayListProduto.get(i).getCodigo();
				rowData[2] = arrayListProduto.get(i).getNome();
				rowData[3] = arrayListProduto.get(i).getPrecoUnitario();
				rowData[4] = arrayListProduto.get(i).getEstoque();
				rowData[5] = arrayListProduto.get(i).getEstoqueMinimo();
				rowData[6] = arrayListProduto.get(i).getDataCad();

				defaultTableModel.addRow(rowData);
			}
		} catch (HibernateException hibernateException) {
			JOptionPane.showMessageDialog(null, "Erro ao buscar e/ou exibir produtos do banco de dados.\n" + hibernateException.getMessage(), "ERRO AO VISUALIZAR", JOptionPane.ERROR_MESSAGE);
		} catch (Exception exception) {
			JOptionPane.showMessageDialog(null, "Erro ao buscar e/ou exibir produtos do banco de dados.\n" + exception.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
		}
	}

	// Exibir Vendas
	private void exibirVendas(boolean ordemCliente) {
		try {
			ArrayList<Venda> arrayListVenda = null;
			if (ordemCliente)
				arrayListVenda = Comercial.consultarVendasOrdemClienteDataDecrescente(dateChooserDataInicialConsultaVendas.getDate(), dateChooserDataFinalConsultaVendas.getDate(), formattedTextFieldOrdenarNomeClienteVendedor.getText());
			else
				arrayListVenda = Comercial.consultarVendasOrdemVendedorDataDecrescente(dateChooserDataInicialConsultaVendas.getDate(), dateChooserDataFinalConsultaVendas.getDate(), formattedTextFieldOrdenarNomeClienteVendedor.getText());

			DefaultTableModel defaultTableModel = (DefaultTableModel) tableVisualizarVendas.getModel();
			Object rowData[] = new Object[8];

			defaultTableModel.setRowCount(0);
			for (int i = 0; i < arrayListVenda.size(); i++) {
				rowData[0] = false;
				rowData[1] = arrayListVenda.get(i).getNumVenda();
				rowData[2] = arrayListVenda.get(i).getCliente().getCodigo();
				rowData[3] = arrayListVenda.get(i).getCliente().getNome();
				rowData[4] = arrayListVenda.get(i).getVendedor().getCodigo();
				rowData[5] = arrayListVenda.get(i).getVendedor().getNome();

				if (arrayListVenda.get(i).getFormaPagamento() == 1)
					rowData[6] = "À vista";
				else
					rowData[6] = "A prazo";

				rowData[7] = arrayListVenda.get(i).getDataVenda();

				defaultTableModel.addRow(rowData);
			}
		} catch (HibernateException hibernateException) {
			JOptionPane.showMessageDialog(null, "Erro ao buscar e/ou exibir vendas do banco de dados.\n" + hibernateException.getMessage(), "ERRO AO VISUALIZAR", JOptionPane.ERROR_MESSAGE);
		} catch (Exception exception) {
			JOptionPane.showMessageDialog(null, "Erro ao buscar e/ou exibir vendas do banco de dados.\n" + exception.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
		}
	}

	// Exibir Compras
	private void exibirCompras() {
		try {
			ArrayList<Compra> arrayListCompra = Comercial.consultarComprasOrdemFornecedorDataDecrescente(dateChooserDataInicialConsultaCompras.getDate(), dateChooserDataFinalConsultaCompras.getDate(), formattedTextFieldOrdernarNomeFornecedor.getText());

			DefaultTableModel defaultTableModel = (DefaultTableModel) tableVisualizarCompras.getModel();
			Object rowData[] = new Object[5];

			defaultTableModel.setRowCount(0);
			for (int i = 0; i < arrayListCompra.size(); i++) {
				rowData[0] = false;
				rowData[1] = arrayListCompra.get(i).getNumCompra();
				rowData[2] = arrayListCompra.get(i).getFornecedor().getCodigo();
				rowData[3] = arrayListCompra.get(i).getFornecedor().getNome();
				rowData[4] = arrayListCompra.get(i).getDataCompra();

				defaultTableModel.addRow(rowData);
			}
		} catch (HibernateException hibernateException) {
			JOptionPane.showMessageDialog(null, "Erro ao buscar e/ou exibir compras do banco de dados.\n" + hibernateException.getMessage(), "ERRO AO VISUALIZAR", JOptionPane.ERROR_MESSAGE);
		} catch (Exception exception) {
			JOptionPane.showMessageDialog(null, "Erro ao buscar e/ou exibir compras do banco de dados.\n" + exception.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
		}
	}

	// Exibir Estatísticas
	private void exibirEstatisticas(char tipoPessoa) {
		try {
			ArrayList<SisComEstatistica> arrayListSisComEstatistica = null;
			if (tipoPessoa == 'C')
				arrayListSisComEstatistica = Comercial.consultarEstatisticaVendasClientePeriodo(dateChooserDataInicialEstatisticas.getDate(), dateChooserDataFinalEstatisticas.getDate());
			else if (tipoPessoa == 'V')
				arrayListSisComEstatistica = Comercial.consultarEstatisticaVendasVendedorPeriodo(dateChooserDataInicialEstatisticas.getDate(), dateChooserDataFinalEstatisticas.getDate());
			else if (tipoPessoa == 'F')
				arrayListSisComEstatistica = Comercial.consultarEstatisticaComprasFornecedorPeriodo(dateChooserDataInicialEstatisticas.getDate(), dateChooserDataFinalEstatisticas.getDate());

			DefaultTableModel defaultTableModel = (DefaultTableModel) tableVisualizarEstatisticas.getModel();
			Object rowData[] = new Object[4];

			defaultTableModel.setRowCount(0);
			for (int i = 0; i < arrayListSisComEstatistica.size(); i++) {
				rowData[0] = arrayListSisComEstatistica.get(i).getCodigoPessoa();
				rowData[1] = arrayListSisComEstatistica.get(i).getNomePessoa();
				rowData[2] = arrayListSisComEstatistica.get(i).getQuantidadeOperacoes();
				rowData[3] = arrayListSisComEstatistica.get(i).getValorTotalOperacoes();

				defaultTableModel.addRow(rowData);
			}
		} catch (HibernateException hibernateException) {
			JOptionPane.showMessageDialog(null, "Erro ao buscar e/ou exibir estatísticas do banco de dados.\n" + hibernateException.getMessage(), "ERRO AO VISUALIZAR", JOptionPane.ERROR_MESSAGE);
		} catch (Exception exception) {
			JOptionPane.showMessageDialog(null, "Erro ao buscar e/ou exibir estatísticas do banco de dados.\n" + exception.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
		}
	}

	// Máscara de telefone fixo
	private MaskFormatter gerarMascaraTelefoneFixo() {
		MaskFormatter mascaraTelefone = null;
		try {
			mascaraTelefone = new MaskFormatter("(##) ####-####");
			mascaraTelefone.setPlaceholderCharacter('_');
		} catch (ParseException parseException) {
			JOptionPane.showMessageDialog(null, "Erro ao gerar máscara de telefone fixo: \n" + parseException.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
		}

		return mascaraTelefone;
	}

	// Máscara de telefone celular
	private MaskFormatter gerarMascaraTelefoneCelular() {
		MaskFormatter mascaraTelefone = null;
		try {
			mascaraTelefone = new MaskFormatter("(##) #####-####");
			mascaraTelefone.setPlaceholderCharacter('_');
		} catch (ParseException parseException) {
			JOptionPane.showMessageDialog(null, "Erro ao gerar máscara de telefone celular: \n" + parseException.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
		}

		return mascaraTelefone;
	}

	// Mácara de CPF
	private MaskFormatter gerarMascaraCPF() {
		MaskFormatter mascaraTelefone = null;
		try {
			mascaraTelefone = new MaskFormatter("###.###.###-##");
			mascaraTelefone.setPlaceholderCharacter('_');
		} catch (ParseException parseException) {
			JOptionPane.showMessageDialog(null, "Erro ao gerar máscara de CPF: \n" + parseException.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
		}

		return mascaraTelefone;
	}

	// Mácara de CNPJ
	private MaskFormatter gerarMascaraCNPJ() {
		MaskFormatter mascaraTelefone = null;
		try {
			mascaraTelefone = new MaskFormatter("##.###.###/####-##");
			mascaraTelefone.setPlaceholderCharacter('_');
		} catch (ParseException parseException) {
			JOptionPane.showMessageDialog(null, "Erro ao gerar máscara de CNPJ: \n" + parseException.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
		}

		return mascaraTelefone;
	}

	// Apenas números
	private void permitirApenasNumeros(JFormattedTextField jFormattedTextField) {
		jFormattedTextField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent keyEvent) {
				char caracter = keyEvent.getKeyChar();
				if (!((caracter >= '0') && (caracter <= '9') || (caracter == KeyEvent.VK_BACK_SPACE) || (caracter == KeyEvent.VK_DELETE))) {
					getToolkit().beep();
					keyEvent.consume();
				}
			}
		});
	}

	// Balão de Aviso
	private void exibirBalaoAviso(JComponent jComponent, String mensage) {
		BalloonTip balloonTip = new BalloonTip(jComponent, mensage, new EdgedBalloonStyle(Color.WHITE, Color.BLACK), false);
		ActionListener actionListenerFinishedAction = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				balloonTip.closeBalloon();
			}
		};
		FadingUtils.fadeOutBalloon(balloonTip, actionListenerFinishedAction, 3000, 24);
	}

	// Método para gerar uma label personalizada
	private JLabel makeLabel(int vert, int horiz, Icon icon, String title) {
		JLabel jLabel = new JLabel(title, icon, SwingConstants.CENTER);
		jLabel.setFont(new Font("Arial", Font.BOLD, 11));
		jLabel.setVerticalTextPosition(vert);
		jLabel.setHorizontalTextPosition(horiz);
		return jLabel;
	}
}