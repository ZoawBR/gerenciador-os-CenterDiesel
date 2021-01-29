package model;

import java.util.ArrayList;
import java.util.List;

public class OrdemServico {
	
	private String clienteID, numOS;
	
	private ArrayList<String[]> servicos;
	
	private String dia,mes,ano;

	public OrdemServico(String clienteID, String numOS, String dia, String mes, String ano, ArrayList<String[]> servicos) {
		this.clienteID = clienteID;
		this.numOS = numOS;
		this.dia = dia;
		this.mes = mes;
		this.ano = ano;
		this.servicos = servicos;
	}

	public String getClienteID() {
		return clienteID;
	}

	public void setClienteID(String clienteID) {
		this.clienteID = clienteID;
	}

	@Override
	public String toString() {
		return "OrdemServico [clienteID=" + clienteID + ", numOS=" + numOS + ", servicos=" + servicos + ", dia=" + dia
				+ ", mes=" + mes + ", ano=" + ano + "]";
	}

	public String getNumOS() {
		return numOS;
	}

	public void setNumOS(String numOS) {
		this.numOS = numOS;
	}

	public String getDia() {
		return dia;
	}

	public void setDia(String dia) {
		this.dia = dia;
	}

	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	public String getAno() {
		return ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}

	public ArrayList<String[]> getServicos() {
		return servicos;
	}
	
	
	
	
}
