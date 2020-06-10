package view;

import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.hibernate.HibernateException;

/**
 * @author Eduardo Augusto
 *
 * Classe Interface Comercial.
 */
public class InterfaceComercial {
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			JSplash jSplash = new JSplash();
			jSplash.showSplashAndLoadDatabase();
			jSplash.startApplication();
		} catch (ClassNotFoundException classNotFoundException) {
			JOptionPane.showMessageDialog(null, "Erro ao localizar arquivos do sistema.\nErro: " + classNotFoundException.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		} catch (InstantiationException instantiationException) {
			JOptionPane.showMessageDialog(null, "Erro ao inicializar o sistema.\nErro: " + instantiationException.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		} catch (IllegalAccessException illegalAccessException) {
			JOptionPane.showMessageDialog(null, "Erro ao acessar dependências do sistema.\nErro: " + illegalAccessException.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		} catch (UnsupportedLookAndFeelException unsupportedLookAndFeelException) {
			JOptionPane.showMessageDialog(null, "Erro ao carregar o layout do sistema.\nErro: " + unsupportedLookAndFeelException.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		} catch (HibernateException hibernateException) {
			JOptionPane.showMessageDialog(null, "Erro ao inicializar o banco de dados do sistema.\nErro: " + hibernateException.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		} catch (Exception exception) {
			JOptionPane.showMessageDialog(null, "Erro ao inicializar o sistema.\nErro: " + exception.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
	}
}