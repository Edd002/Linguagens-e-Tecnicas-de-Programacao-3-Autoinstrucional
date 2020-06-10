package view;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import java.awt.Cursor;
import java.awt.Font;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import javax.swing.JPasswordField;
import javax.swing.border.LineBorder;

import org.hibernate.HibernateException;

import controller.Comercial;
import model.Cliente;
import model.Fornecedor;
import model.Pessoa;
import model.Vendedor;
import utility.JActualDateTime;

import java.awt.Color;

public class JLogin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPaneLogin;
	private JTextField textFieldLogin;
	private JPasswordField passwordFieldSenha;

	/**
	 * Launch the application.
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JLogin frame = new JLogin();
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
	@SuppressWarnings("serial")
	public JLogin() {
		setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		setTitle("SYSTEM COMMERCE - Login");
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(JLogin.class.getResource("/img/logo.png")));
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 650, 475);
		contentPaneLogin = new JPanel();
		contentPaneLogin.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		contentPaneLogin.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPaneLogin.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPaneLogin);

		JPanel panelNorth = new JPanel();
		panelNorth.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		contentPaneLogin.add(panelNorth, BorderLayout.NORTH);

		JLabel lblSystemCommerce = new JLabel("System Commerce");
		lblSystemCommerce.setIcon(new ImageIcon(JLogin.class.getResource("/img/logo.png")));
		lblSystemCommerce.setFont(new Font("BankGothic Lt BT", Font.BOLD, 24));
		panelNorth.add(lblSystemCommerce);

		JPanel panelCenter = new JPanel();
		panelCenter.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		contentPaneLogin.add(panelCenter, BorderLayout.CENTER);

		JLabel lblLogin = new JLabel("Login:");
		lblLogin.setBounds(38, 34, 70, 21);
		lblLogin.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));

		textFieldLogin = new JTextField();
		textFieldLogin.setBounds(123, 31, 430, 27);
		textFieldLogin.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		textFieldLogin.setColumns(10);
		textFieldLogin.setBorder(new LineBorder(new Color(0, 0, 0)));

		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setBounds(38, 79, 70, 21);
		lblSenha.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));

		passwordFieldSenha = new JPasswordField();
		passwordFieldSenha.setEchoChar('*');
		passwordFieldSenha.setBounds(123, 76, 430, 27);
		passwordFieldSenha.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		passwordFieldSenha.setBorder(new LineBorder(new Color(0, 0, 0)));

		JLabel lblVersaoSistema = new JLabel("SystemCommerce Vers\u00E3o 1.0");
		lblVersaoSistema.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		lblVersaoSistema.setBounds(38, 190, 260, 14);
		panelCenter.add(lblVersaoSistema);

		JLabel lblDataTempoAtual = new JLabel("HH:mm:ss dd-MM-yyyy");
		lblDataTempoAtual.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
		lblDataTempoAtual.setBounds(353, 190, 200, 14);
		panelCenter.add(lblDataTempoAtual);
		JActualDateTime.getCurrentDateTime(lblDataTempoAtual);

		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.setBounds(170, 119, 184, 49);
		btnEntrar.setIcon(new ImageIcon(new ImageIcon(JLogin.class.getResource("/img/entrar.png")).getImage().getScaledInstance(80, 40, java.awt.Image.SCALE_SMOOTH)));
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if (textFieldLogin.getText().equals("adm") && (new String(passwordFieldSenha.getPassword())).equals("adm")) {
						setEnabled(false);
						JAdministrador jAdministrador = new JAdministrador();
						jAdministrador.setVisible(true);
						jAdministrador.setLocationRelativeTo(null);
						setVisible(false);
					} else {
						Pessoa pessoa = Comercial.autentificarLogin(textFieldLogin.getText(), new String(passwordFieldSenha.getPassword()));
						if (pessoa instanceof Cliente) {
							setEnabled(false);
							JCliente jCliente = new JCliente((Cliente) pessoa);
							jCliente.setVisible(true);
							jCliente.setLocationRelativeTo(null);
							setVisible(false);
						} else if (pessoa instanceof Fornecedor) {
							setEnabled(false);
							JFornecedor jFornecedor = new JFornecedor((Fornecedor) pessoa);
							jFornecedor.setVisible(true);
							jFornecedor.setLocationRelativeTo(null);
							setVisible(false);
						} else if (pessoa instanceof Vendedor) {
							setEnabled(false);
							JVendedor jVendedor = new JVendedor((Vendedor) pessoa);
							jVendedor.setVisible(true);
							jVendedor.setLocationRelativeTo(null);
							setVisible(false);
						} else {
							JOptionPane.showMessageDialog(null, "Login e/ou senha inválido(s).", "Aviso de Autenticação", JOptionPane.WARNING_MESSAGE);
						}
					}
				} catch (HibernateException hibernateException) {
					JOptionPane.showMessageDialog(null, "Erro ao efetuar login.\n" + hibernateException.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
					setEnabled(true);
					requestFocus();
				} catch (Exception exception) {
					JOptionPane.showMessageDialog(null, "Erro ao efetuar login.\n" + exception.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
					setEnabled(true);
					requestFocus();
				}
			}
		});
		btnEntrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEntrar.setFont(new Font("BankGothic Lt BT", Font.BOLD, 16));

		JButton btnSair = new JButton("Sair");
		btnSair.setBounds(369, 119, 184, 49);
		btnSair.setIcon(new ImageIcon(new ImageIcon(JLogin.class.getResource("/img/sair.png")).getImage().getScaledInstance(80, 40, java.awt.Image.SCALE_SMOOTH)));
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja sair do programa?", "Fechar o Programa", JOptionPane.YES_NO_OPTION);
				if (i == 0)
					System.exit(0);
			}
		});
		btnSair.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSair.setFont(new Font("BankGothic Lt BT", Font.BOLD, 16));

		panelCenter.add(btnEntrar);
		panelCenter.add(btnSair);
		panelCenter.add(lblLogin);
		panelCenter.add(textFieldLogin);
		panelCenter.add(lblSenha);
		panelCenter.add(passwordFieldSenha);
		panelCenter.setLayout(null);

		// Evento ao clicar no X do Windows
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				int i = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja sair do programa?", "Fechar o Programa", JOptionPane.YES_NO_OPTION);
				if (i == 0)
					System.exit(0);
			}
		});

		// Evento ao pressionar ESC
		getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "Cancel");
		getRootPane().getActionMap().put("Cancel", new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				int i = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja sair do programa?", "Fechar o Programa", JOptionPane.YES_NO_OPTION);
				if (i == 0)
					System.exit(0);
			}
		});

		// Definir botão padrão para a tecla ENTER
		JRootPane rootPane = SwingUtilities.getRootPane(btnEntrar); 
		rootPane.setDefaultButton(btnEntrar);
	}
}