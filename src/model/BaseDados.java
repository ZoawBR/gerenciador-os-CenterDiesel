package model;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class BaseDados {
	private static ArrayList<Cliente> clientes = new ArrayList<Cliente>();
	private static ArrayList<OrdemServico> ordemServicos = new ArrayList<OrdemServico>();
	
	public static void salvarDadosCliente() {
		
		try {
			XStream xStream = new XStream(new DomDriver());
			File file = new File("res/clientesBD.xml");
			if(!file.exists()) {
				file.createNewFile();
			}else {
				file.delete();
				file.createNewFile();
			}
			PrintWriter write = new PrintWriter(file);
			write.println("<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"no\"?>");
			String dados = xStream.toXML(clientes);
			write.println(dados);
			write.flush();
			write.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	
	}

	public static void lerDadosCliente() {
		XStream xStream = new XStream(new DomDriver());
		File file = new File("res/clientesBD.xml");
		
		if(file.exists()) {
			clientes = (ArrayList<Cliente>) xStream.fromXML(file);
			
		}else {
			clientes = new ArrayList<>();
			
		}
		
	}
	
	public static void salvarDadosOS() {
		
		try {
			XStream xStream = new XStream(new DomDriver());
			File file = new File("res/ordemDeServicosBD.xml");
			if(!file.exists()) {
				file.createNewFile();
			}else {
				file.delete();
				file.createNewFile();
			}
			PrintWriter write = new PrintWriter(file);
			write.println("<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"no\"?>");
			String dados = xStream.toXML(ordemServicos);
			write.println(dados);
			write.flush();
			write.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	
	}

	public static void lerDadosOS() {
		XStream xStream = new XStream(new DomDriver());
		File file = new File("res/ordemDeServicosBD.xml");
		
		if(file.exists()) {
			ordemServicos = (ArrayList<OrdemServico>) xStream.fromXML(file);
			
		}else {
			ordemServicos = new ArrayList<>();
			
		}
		
	}
	
	public static double somarTudoOS(String numOS) {
		double total = 0;
		if(verificarSeExisteOS(numOS)) {
			ArrayList<String[]> os = buscarOS(numOS).getServicos();
			for (String[] servico : os) {
				total += Double.parseDouble(servico[4]);
			}
		}
		return total;
	}
	
	public static boolean addOrdemServico(OrdemServico OS) {
		if(!verificarSeExisteOS(OS.getNumOS())) {
			return ordemServicos.add(OS);
		}
		return false;
	}
	
	public static boolean verificarSeExisteOS(String numOS) {
		for (OrdemServico ordemServico : ordemServicos) {
			if(ordemServico.getNumOS().equalsIgnoreCase(numOS)) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean addOSAoCliente(String IDCliente, String numOS) {
		for (Cliente cliente : clientes) {
			if(cliente.getID().equalsIgnoreCase(IDCliente)) {
				return cliente.getNumOSs().add(numOS);
			}
		}
		return false;
	}
	
	public static boolean addCliente(Cliente cliente) {
		if(!verificarSeExisteCliente(cliente.getNome())) {
			return clientes.add(cliente);
		}
		return false;
	}
	
	
	
	public static String buscarIDcliente(String nome) {
		for (Cliente cliente : clientes) {
			if(cliente.getNome().equalsIgnoreCase(nome)) {
				return cliente.getID();
			}
		}
		return null;
	}
	
	public static OrdemServico buscarOS(String numOS) {
		for (OrdemServico os : ordemServicos) {
			if(os.getNumOS().equalsIgnoreCase(numOS)) {
				return os;
			}
		}
		return null;
	}
	
	public static Cliente buscarCliente(String ID) {
		for (Cliente cliente : clientes) {
			if(cliente.getID().equalsIgnoreCase(ID)) {
				return cliente;
			}
		}
		return null;
	}
	
	public static boolean verificarSeExisteCliente(String nome) {
		for (Cliente cliente : clientes) {
			if(cliente.getNome().equalsIgnoreCase(nome)) {
				return true;
			}
		}
		return false;
	}

	
	public static int tamanahoArrayClientes() {
		return clientes.size();
	}

	public static ArrayList<Cliente> getClientes() {
		return clientes;
	}

	public static ArrayList<OrdemServico> getOrdemServicos() {
		return ordemServicos;
	}
	
}
