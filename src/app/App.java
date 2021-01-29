package app;

import control.ControleLogin;
import view.TelaLogin;

public class App {

	public static void main(String[] args) {
		TelaLogin telaLogin = new TelaLogin("Login | Ordem de Serviços | v1.0", 600, 320);
		
		new ControleLogin(telaLogin);
	}

}
