package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;

import controller.DAO;

public class JSplash extends JWindow {
	private static final long serialVersionUID = 1L;

	public JSplash() {
		getContentPane().setFont(new Font("BankGothic Lt BT", Font.PLAIN, 16));
	}

	public void showSplashAndLoadDatabase() {
		JPanel jPanel = (JPanel) getContentPane();
		jPanel.setBackground(new Color(220, 220, 220));

		// TAMNHO E POSIÇÃO
		int width = 800;
		int height = 300;
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (dimension.width - width) / 2;
		int y = (dimension.height - height) / 2;
		setBounds(x, y, width, height);

		// SPLASH
		JLabel jLabelImage = new JLabel(new ImageIcon(JLogin.class.getResource("/img/logo.png")));
		JLabel jLabelCopyright = new JLabel("Copyright 2018, SYSTEM COMMERCE", JLabel.CENTER);
		jLabelCopyright.setFont(new Font("BankGothic Lt BT", Font.BOLD, 20));
		jLabelCopyright.setForeground(Color.BLACK);
		jPanel.add(jLabelImage, BorderLayout.NORTH);
		jPanel.add(jLabelCopyright, BorderLayout.CENTER);
		jPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
		setVisible(true);

		// CARREGAR BANCO DE DADOS ENQUANTO O SPLASH ESTÁ VISÍVEL
		DAO.getSessionFactory();
	}

	public void startApplication() {
		JLogin jLogin = new JLogin();
		jLogin.setVisible(true);
		jLogin.setLocationRelativeTo(null);
		setVisible(false);
	}
}