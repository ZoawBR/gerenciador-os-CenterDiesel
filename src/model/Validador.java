package model;

public class Validador {
	
	private static String login = "center";
	private static String senha = "1234";
	
	public static boolean validador(String loginField, String senhaField) {
		if(login.equalsIgnoreCase(loginField) && senha.equalsIgnoreCase(senhaField)) {
			return true;
		}
		return false;
	}
	
}
