package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Validador;
import view.TelaLogin;
import view.TelaOrdemServico;

public class ControleLogin implements ActionListener {
	
	private TelaLogin telaLogin;
	private TelaOrdemServico telaOrdemServico;
	
	public ControleLogin(TelaLogin telaLogin) {
		this.telaLogin = telaLogin;
		
		control();
	}
	
	private void control() {
		telaLogin.getLoginField().addActionListener(this);
		telaLogin.getSenhaField().addActionListener(this);
		telaLogin.getEntrarBtn().addActionListener(this);
		telaLogin.getSairBtn().addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(telaLogin.getSairBtn() == e.getSource()) {
			System.exit(0);
		}
		if(telaLogin.getEntrarBtn() == e.getSource()) {
			boolean isValido = Validador.validador(telaLogin.getLoginField().getText(), new String(telaLogin.getSenhaField().getPassword()));
			if(isValido) {
				telaOrdemServico = new TelaOrdemServico("Center Diesel | Ordem de Serviço | v1.0", 800, 600);
				telaLogin.setVisible(false);
				telaOrdemServico.setVisible(true);
				new ControleOrdemServico(telaOrdemServico);
			}
		}
	}

}
