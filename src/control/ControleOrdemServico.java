package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import model.BaseDados;
import model.Cliente;
import model.OrdemServico;
import view.Mensagem;
import view.TelaOrdemServico;

public class ControleOrdemServico implements WindowListener, ActionListener {
	private TelaOrdemServico telaOrdemServico;
	private JPanel cadastrarPanel,listarPanel;
	
	private ArrayList<String[]> tabelaTemp = new ArrayList<String[]>(); 
	private ArrayList<Object[]> listagem;
	private boolean limpar= false;
	private double totalTotal = 0;
	private double totalDivida = 0;

	
	public ControleOrdemServico(TelaOrdemServico telaOrdemServico) {
		this.telaOrdemServico = telaOrdemServico;
		cadastrarPanel = telaOrdemServico.getCadastrarNovaOSPanel();
		listarPanel = telaOrdemServico.getListarOSPanel();
		control();
	}

	private void control() {
		telaOrdemServico.addWindowListener(this);
		telaOrdemServico.getCadastrarNovaOSBtn().addActionListener(this);
		telaOrdemServico.getListarOSBtn().addActionListener(this);
		telaOrdemServico.getCadastrarBtn().addActionListener(this);
		telaOrdemServico.getNovoServicoBtn().addActionListener(this);
		telaOrdemServico.getVerificarOSBtn().addActionListener(this);
		telaOrdemServico.getPesquisarBtn().addActionListener(this);
		telaOrdemServico.getSalvarModificacoesBtn().addActionListener(this);
		telaOrdemServico.getExcluirBtn().addActionListener(this);
	}
	
//	"Nome","Cidade","Numero da O.S.", "Total", "Endereço", "Bairro", "Fone(s)","Estado"
	public ArrayList<Object[]> atualizarListaDeOS(String dia, String mes, String ano) {
		totalDivida = 0;
		DefaultTableModel model = ((DefaultTableModel) telaOrdemServico.getJtableListar().getModel());
		model.setNumRows(0);
		listagem = new ArrayList<Object[]>();
		if(dia.equals("X") && mes.equals("X") && ano.equals("X")) {
			if(telaOrdemServico.getPesquisarNomeField().getText().equals("")) {
				for (OrdemServico os : BaseDados.getOrdemServicos()) {
					porNaLista(os, model);
				}
				telaOrdemServico.getTotalTotalListarLbl().setText(String.valueOf(totalDivida));
			}else {
				
				for (OrdemServico os : BaseDados.getOrdemServicos()) {
					if (telaOrdemServico.getPesquisarNomeField().getText().equalsIgnoreCase(BaseDados.buscarCliente(os.getClienteID()).getNome())) {
						porNaLista(os, model);
					}	
				}
				
			}
		

		}else {
			if(!dia.equals("X") && mes.equals("X") && ano.equals("X")) {
				for (OrdemServico os : BaseDados.getOrdemServicos()) {
					if (os.getDia().equals(dia)) {
						porNaLista(os, model);
					}	
				}
			}else if(dia.equals("X") && !mes.equals("X") && ano.equals("X")) {
				for (OrdemServico os : BaseDados.getOrdemServicos()) {
					if (os.getMes().equals(mes)) {
						porNaLista(os, model);
					}	
				}
			}else if(dia.equals("X") && mes.equals("X") && !ano.equals("X")) {
				for (OrdemServico os : BaseDados.getOrdemServicos()) {
					if (os.getAno().equals(ano)) {
						porNaLista(os, model);
					}	
				}
			}
			else if(!dia.equals("X") && !mes.equals("X") && ano.equals("X")) {
				for (OrdemServico os : BaseDados.getOrdemServicos()) {
					if (os.getDia().equals(dia) && os.getMes().equals(mes)) {
						porNaLista(os, model);
					}	
				} // falta por  com apenas ano e mes
			}else if(!dia.equals("X") && !mes.equals("X") && !ano.equals("X")){
				for (OrdemServico os : BaseDados.getOrdemServicos()) {
					if (os.getDia().equals(dia) && os.getMes().equals(mes) && os.getAno().equals(ano)) {
						porNaLista(os, model);
					}	
				}
			}
		}
		return listagem;
	}
	
	private void porNaLista(OrdemServico os,DefaultTableModel model) {
		Object [] opcs = new Object[9];
		Cliente cliente = BaseDados.buscarCliente(os.getClienteID());
		opcs[0] = cliente.getNome();
		opcs[1] = cliente.getCidade();
		opcs[2] = os.getNumOS();
		opcs[3] = os.getDia() + " / " + os.getMes() + " / " + os.getAno();
		opcs[4] = BaseDados.somarTudoOS(os.getNumOS());
		opcs[5] = cliente.getEndereco();
		opcs[6] = cliente.getBairro();
		opcs[7] = cliente.getFone();
		opcs[8] = cliente.getEstado();
		
		totalDivida += BaseDados.somarTudoOS(os.getNumOS());
		
		listagem.add(opcs);
		model.addRow(opcs);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (telaOrdemServico.getPesquisarBtn() == e.getSource()) {
			atualizarListaDeOS((String)telaOrdemServico.getDiaListarBox().getSelectedItem(), 
					(String)telaOrdemServico.getMesListarBox().getSelectedItem(),
					(String)telaOrdemServico.getAnoListarBox().getSelectedItem());
		}
		
		if(telaOrdemServico.getCadastrarNovaOSBtn() == e.getSource()) {
			extrairDoXMLpraArray();
			cadastrarPanel.setVisible(true);
			listarPanel.setVisible(false);
		}
		if(telaOrdemServico.getListarOSBtn() == e.getSource()) {
			extrairDoXMLpraArray();
			atualizarListaDeOS("X", "X", "X");
			cadastrarPanel.setVisible(false);
			listarPanel.setVisible(true);
		}
		
		//{"Qnt.", "Uni.","Descrição dos Servços","Valor Uni.","Total"};
		if(telaOrdemServico.getVerificarOSBtn() == e.getSource()) {
			int row = telaOrdemServico.getJtableListar().getSelectedRow();
			int rowSelecionada = 0;
			if(row != -1) {
				rowSelecionada = row;
			}
			
			double total = 0;
			
			OrdemServico numOS = BaseDados.buscarOS((String)listagem.get(rowSelecionada)[2]);
			
			DefaultTableModel model = ((DefaultTableModel) telaOrdemServico.getjTableListarServico().getModel());
			model.setNumRows(0);
			for (String[] objects : numOS.getServicos()) {
				Object [] opcs = new Object[5];
				opcs[0] = objects[0];
				opcs[1] = objects[1];
				opcs[2] = objects[2];
				opcs[3] = objects[3];
				opcs[4] = objects[4];
				total += Double.parseDouble(objects[4]);
				model.addRow(opcs);
			}

			telaOrdemServico.getTotalTotalListarLbl().setText(String.valueOf(total));
			
		}
		
		if(telaOrdemServico.getNovoServicoBtn() == e.getSource()) {
			if(!verificarEntradaVazia()) {
				String[] temp = new String[6]; 
				temp[0] = telaOrdemServico.getQuantidadeField().getText();
				temp[1] = telaOrdemServico.getUnidadeField().getText();
				temp[2] = telaOrdemServico.getDescricaoField().getText();
				temp[3] = telaOrdemServico.getUnitarioField().getText();
				temp[4] = telaOrdemServico.getTotalField().getText();
				temp[5] = Integer.toString(tabelaTemp.size());
				tabelaTemp.add(temp);
				totalTotal += Double.valueOf(telaOrdemServico.getTotalField().getText());
				telaOrdemServico.getTotalDoTotalLbl().setText(String.valueOf(totalTotal));
				mostrarServicos();
				
				limparServico();
			}
		}
		
		if(telaOrdemServico.getExcluirBtn() == e.getSource()) {
			int row = telaOrdemServico.getJtableListar().getSelectedRow();
			int rowSelecionada = 0;
			if(row != -1) {
				rowSelecionada = row;
			}
			OrdemServico numOS = BaseDados.buscarOS((String)listagem.get(rowSelecionada)[2]);
			Cliente cliente = BaseDados.buscarCliente(numOS.getClienteID());
			BaseDados.getClientes().remove(cliente);
			BaseDados.getOrdemServicos().remove(numOS);
			BaseDados.salvarDadosCliente();
			BaseDados.salvarDadosOS();
			atualizarListaDeOS("X", "X", "X");
		}
		
		if(telaOrdemServico.getCadastrarBtn() == e.getSource()) {
			 extrairDoXMLpraArray();
			
			String nome =telaOrdemServico.getNomeField().getText();
			String endereco =telaOrdemServico.getEnderecoField().getText();
			String bairro =telaOrdemServico.getBairroField().getText();
			String cidade =telaOrdemServico.getCidadeField().getText();
			String fone =telaOrdemServico.getFoneField().getText();
			String estado =telaOrdemServico.getEstadoField().getText();
			String dia =(String) telaOrdemServico.getDiaBox().getSelectedItem();
			String mes =(String) telaOrdemServico.getMesBox().getSelectedItem();
			String ano =(String) telaOrdemServico.getAnoBox().getSelectedItem();
			String numOS =telaOrdemServico.getNumOSField().getText();

			
			if(verificarEntradasPricipais().size() == 0) {
				if(!BaseDados.verificarSeExisteOS(numOS)) {
					if(BaseDados.verificarSeExisteCliente(nome.toLowerCase())) {
						//Esse nome já esta cadastrado no sistema deseja atualizar os dados (por os field q foram colcoados e ele nt em)
						BaseDados.addOSAoCliente(BaseDados.buscarIDcliente(nome), numOS);
						BaseDados.addOrdemServico(new OrdemServico(BaseDados.buscarIDcliente(nome), numOS, dia, mes, ano, tabelaTemp));
					}else {
						BaseDados.addCliente(new Cliente(Integer.toString(BaseDados.tamanahoArrayClientes()+1), nome, endereco, bairro, cidade, estado, fone));
						BaseDados.addOSAoCliente(BaseDados.buscarIDcliente(nome), numOS);
						BaseDados.addOrdemServico(new OrdemServico(BaseDados.buscarIDcliente(nome), numOS, dia, mes, ano, tabelaTemp));
					}
					
					BaseDados.salvarDadosCliente();
					BaseDados.salvarDadosOS();
					
					limpar = true;
					
					
					Object[] options = {"OK"};
					JOptionPane.showOptionDialog(null,Mensagem.mensagemNotificacao(4),"Cadastro Concluido!",JOptionPane.PLAIN_MESSAGE, JOptionPane.QUESTION_MESSAGE,null, options,options[0]);
					
				}else {
					JOptionPane.showConfirmDialog(null, Mensagem.mensagemNotificacao(3));
					limpar = false;
				}
			}else {
				String conct = "";
				for (String string : verificarEntradasPricipais()) {
					conct += string + ", ";
				}
				JOptionPane.showConfirmDialog(null, Mensagem.mensagemNotificacao(2) + conct);
				limpar = false;
			}
			
			// RESETANDO TUDO
			if(limpar) {
				tabelaTemp = new ArrayList<String[]>();
				limparTabela();
				novaOS();
				telaOrdemServico.getTotalDoTotalLbl().setText("000,00");
				totalTotal = 0;
			}
			
//			System.out.println("=-=-=-=-=-=-=-=-=-=-=INICIO-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
//			for (OrdemServico os: BaseDados.getOrdemServicos()) {
//				System.out.println(os.toString());
//			}
//			System.out.println("=-=--==-=-=-=-=-=-=-=-=----------------------===-=-=-=-=-=--==-");
//			for (Cliente cliente: BaseDados.getClientes()) {
//				System.out.println(cliente.toString());
//			}
//			System.out.println("=-=--==-=-=-=-=-=--==-=-=-FIM=-=--==-=-=-=-=-=--==-=-=-");
		}
	}

	
	private void limparServico() {

		telaOrdemServico.getQuantidadeField().setText("");
		telaOrdemServico.getUnidadeField().setText("");
		telaOrdemServico.getDescricaoField().setText("");
		telaOrdemServico.getUnitarioField().setText("");
		telaOrdemServico.getTotalField().setText("");
	}

	public void extrairDoXMLpraArray() {
		BaseDados.lerDadosCliente();
		BaseDados.lerDadosOS();
	}
	
	public ArrayList<String> verificarEntradasPricipais() {
		String nome =telaOrdemServico.getNomeField().getText();
		String cidade =telaOrdemServico.getCidadeField().getText();
		String estado =telaOrdemServico.getEstadoField().getText();
		String numOS =telaOrdemServico.getNumOSField().getText();
		
		ArrayList<String> arrayDeAviso = new ArrayList<String>();
		
		if(nome.equalsIgnoreCase("") || nome.equalsIgnoreCase(" ")) {
			arrayDeAviso.add("Nome");
		}
		if(cidade.equalsIgnoreCase("") || cidade.equalsIgnoreCase(" ")) {
			arrayDeAviso.add("Cidade");
		}
		if(estado.equalsIgnoreCase("") || estado.equalsIgnoreCase(" ")) {
			arrayDeAviso.add("Estado");
		}
		if(numOS.equalsIgnoreCase("") || numOS.equalsIgnoreCase(" ")) {
			arrayDeAviso.add("Numero da O.S.");
		}
		
		return arrayDeAviso;
	}
	
	
	public void novaOS() {
		telaOrdemServico.getNomeField().setText("");
		telaOrdemServico.getEnderecoField().setText("");
		telaOrdemServico.getBairroField().setText("");
		telaOrdemServico.getCidadeField().setText("");
		telaOrdemServico.getFoneField().setText("");
		telaOrdemServico.getEstadoField().setText("");
		telaOrdemServico.getNumOSField().setText("");
		telaOrdemServico.getQuantidadeField().setText("");
		telaOrdemServico.getUnidadeField().setText("");
		telaOrdemServico.getDescricaoField().setText("");
		telaOrdemServico.getUnitarioField().setText("");
		telaOrdemServico.getTotalField().setText("");
	}
	
	public boolean verificarEntradaVazia() {
		if(telaOrdemServico.getQuantidadeField().getText().equalsIgnoreCase("") &&
		telaOrdemServico.getUnidadeField().getText().equalsIgnoreCase("") &&
		telaOrdemServico.getDescricaoField().getText().equalsIgnoreCase("") &&
		telaOrdemServico.getUnitarioField().getText().equalsIgnoreCase("") &&
		telaOrdemServico.getTotalField().getText().equalsIgnoreCase("")) {
			return true;
		}
		return false;
	}
	
	public void mostrarServicos() {
		DefaultTableModel model = ((DefaultTableModel) telaOrdemServico.getjTable().getModel());
		model.setNumRows(0);
		Object [] opcs = new Object[5];
		
		for (int i = 0; i < tabelaTemp.size(); i++) {
			opcs[0] = tabelaTemp.get(i)[0];
			opcs[1] = tabelaTemp.get(i)[1];
			opcs[2] = tabelaTemp.get(i)[2];	
			opcs[3] = tabelaTemp.get(i)[3];	
			opcs[4] = tabelaTemp.get(i)[4];	
			model.addRow(opcs);
		}

	}
	
	public void limparTabela() {
		DefaultTableModel model = ((DefaultTableModel) telaOrdemServico.getjTable().getModel());
		model.setNumRows(0);
	}

	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		int jpanelSair = JOptionPane.showConfirmDialog(null, Mensagem.mensagemNotificacao(1));
		if(jpanelSair == 0) {
			// FAZER UM BACKUP
			System.exit(0);
		}
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}
	
}
