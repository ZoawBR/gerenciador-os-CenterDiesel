package view;

public class Mensagem {
	public static String mensagemNotificacao(int num) {
		switch (num) {
		case 1:
			return "Os dados na tabela de cadastro de O.S. ser�o exluido. Tem certeza que deseja fachar o programa? ";
		case 2:
			return "As seguintes entradas nececitam ser preenchidas: ";
		case 3:
			return "J� existe uma O.S. com essa numera��o!";
		case 4:
			return "Cadastro realizado com sucesso!";
		default:
			break;
		}
		return null;
	}
}	
