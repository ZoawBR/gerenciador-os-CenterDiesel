package model;

import java.util.ArrayList;

public class Cliente {
	
	private String nome, endereco, bairro, cidade, estado, fone, ID;
	private ArrayList<String> numOSs;
		
	

	public Cliente(String iD, String nome, String endereco, String bairro, String cidade, String estado, String fone) {
		this.nome = nome;
		this.endereco = endereco;
		this.bairro = bairro;
		this.cidade = cidade;
		this.estado = estado;
		this.fone = fone;
		ID = iD;
		numOSs = new ArrayList<String>();

	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	@Override
	public String toString() {
		return "Cliente [nome=" + nome + ", endereco=" + endereco + ", bairro=" + bairro + ", cidade=" + cidade
				+ ", estado=" + estado + ", fone=" + fone + ", ID=" + ID + ", numOSs=" + numOSs + "]";
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getFone() {
		return fone;
	}

	public void setFone(String fone) {
		this.fone = fone;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public ArrayList<String> getNumOSs() {
		return numOSs;
	}

	public void setNumOSs(ArrayList<String> numOSs) {
		this.numOSs = numOSs;
	}
	
	
	
}
