package view;

import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class TelaLogin extends TelaAbs{
	
	private Font font = new Font("Consolas", Font.BOLD, 15);

	private JLabel loginLabel, senhaLabel;
	private JTextField loginField;
	private JButton entrarBtn, sairBtn;
	private JPasswordField senhaField;
	
	public TelaLogin(String TITLE, int WIDTH, int HEIGHT) {
		super(TITLE, WIDTH, HEIGHT);
		
		// -=-=--= criando os Objetos =-=-=-=-=-
		loginLabel = new JLabel("Login: ");
		senhaLabel = new JLabel("Senha: ");
		loginField = new JTextField();
		senhaField = new JPasswordField();
		entrarBtn = new JButton("Entrar");
		sairBtn = new JButton("Sair");
		
		// -=-=--= configurando os Objetos =-=-=-=-=-
		loginLabel.setBounds(200, 175, 100, 20);	
		loginLabel.setFont(font);
		senhaLabel.setBounds(200, 200, 100, 20);
		senhaLabel.setFont(font);
		
		loginField.setBounds(260, 175, 150, 20);
		loginField.setFont(font);

		senhaField.setBounds(260, 200, 150, 20);
		senhaField.setFont(font);

		entrarBtn.setBounds(205, 240, 100, 30);
		entrarBtn.setFont(font);
		entrarBtn.setBackground(new Color(27, 245, 45));
		
		sairBtn.setBounds(305, 240, 100, 30);
		sairBtn.setFont(font);
		sairBtn.setBackground(new Color(245, 48, 2));
		
		add(loginLabel);
		add(senhaLabel);
		add(loginField);
		add(senhaField);
		add(entrarBtn);
		add(sairBtn);
		
		setVisible(true);
	}

	public JPasswordField getSenhaField() {
		return senhaField;
	}

	public void setSenhaField(JPasswordField senhaField) {
		this.senhaField = senhaField;
	}

	public JLabel getSenhaLabel() {
		return senhaLabel;
	}

	public JTextField getLoginField() {
		return loginField;
	}

	public JButton getEntrarBtn() {
		return entrarBtn;
	}

	public JButton getSairBtn() {
		return sairBtn;
	}

}
