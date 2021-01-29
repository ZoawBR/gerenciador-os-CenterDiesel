package view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

public class TelaOrdemServico extends TelaAbs{
		
	private Font font = new Font("Consolas", Font.BOLD, 15);
	private JPanel cadastrarNovaOSPanel, listarOSPanel, opcPanel;
	private JButton listarOSBtn, cadastrarNovaOSBtn, cadastrarBtn, novoServicoBtn, verificarOSBtn, salvarModificacoesBtn, excluirBtn, pesquisarBtn;
	private JLabel nomeLbl, enderecoLbl, bairroLbl, cidadeLbl, foneLbl, estadoLbl, quantidadeLbl, unidadeLbl, descricaoLbl, valoresLbl, unitarioLbl, totalLbl, numOSLbl, dataLbl, valorLbl, totalDoTotalLbl, valorListarLbl, totalTotalListarLbl;
	private JTextField pesquisarNomeField, nomeField, enderecoField, bairroField, cidadeField, foneField, estadoField,quantidadeField, unidadeField, descricaoField, unitarioField, totalField, numOSField;
	private JComboBox<String> diaBox, mesBox, anoBox, diaListarBox, mesListarBox, anoListarBox;
	private JTable jTable,jtableListar, jTableListarServico;
	private JScrollPane jScrollPane, jScrollPaneListar, jScrollPaneListarServicos;	
	private Object[] titulos = {"Qnt.", "Uni.","Descrição dos Servços","Valor Uni.","Total"};
	private Object[] titulosListar = {"Nome","Cidade","Numero da O.S.", "Data", "Total", "Endereço", "Bairro", "Fone(s)","Estado"};
	private String[] dias = {"X","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26", "27","28","29","30","31"};
	private String[] meses = {"X","Janeiro", "Fevereiro", "Março", "Abril", "Maio","Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};
	private String[] anos = {"X","2000","2001","2002","2003","2004","2005","2006","2007","2008","2009","2010","2011","2012","2013","2014","2015","2016","2017","2018","2019","2020","2021","2022","2023","2024","2025"};
	
	public TelaOrdemServico(String TITLE, int WIDTH, int HEIGHT) {
		super(TITLE, WIDTH, HEIGHT);
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		
		// -=-=-=-=-=-=- DECLARANDO OS JPNAL =--==--=-=-==-
		cadastrarNovaOSPanel = new JPanel();
		cadastrarNovaOSPanel.setBounds(100, 0, 750, 600);
		cadastrarNovaOSPanel.setBackground(new Color(204,212,199));
		cadastrarNovaOSPanel.setFont(font);
		add(cadastrarNovaOSPanel).setVisible(true);
		cadastrarNovaOSPanel.setLayout(null);
		
		listarOSPanel = new JPanel();
		listarOSPanel.setBounds(100, 0, 750, 600);
		listarOSPanel.setBackground(new Color(204,212,199));
		add(listarOSPanel).setVisible(false);
		listarOSPanel.setLayout(null);
		
		opcPanel = new JPanel();
		opcPanel.setBounds(0, 0, 100, 600);
		opcPanel.setBackground(Color.BLACK);
		add(opcPanel).setVisible(true);
		opcPanel.setLayout(null);
		
		// -=-=--=-=-=-=-= JPANEL OPÇÕES -=-=--=-=-=-=-=-=-=-=-=--=
		jpanelOpcoes();
		
		// -=-=-=-=--==-=- JPANEL CADASTRAR NOVA O.S. -==--==--==--=
		jpanelCadastrar();
		
		// -=-=-=-=--==-=- JPANEL LISTAR O.S. -==--==--==--=
		jpanelListarOS();
	}

	public JTable getJtableListar() {
		return jtableListar;
	}

	public JTable getjTableListarServico() {
		return jTableListarServico;
	}

	private void jpanelListarOS() {
		
		verificarOSBtn = new JButton("Verificar OS's");
		verificarOSBtn.setBounds(485, 370, 150, 40);
		verificarOSBtn.setFont(font);

		salvarModificacoesBtn = new JButton("Salvar Modificações");
		salvarModificacoesBtn.setBounds(465, 415, 195, 40);
		salvarModificacoesBtn.setFont(font);
		
		excluirBtn = new JButton("Excluir Linha");
		excluirBtn.setBounds(465, 460, 195, 40);
		excluirBtn.setFont(font);
		
		pesquisarBtn = new JButton("Pesquisar");
		pesquisarBtn.setBounds(200, 50, 195, 20);
		pesquisarBtn.setFont(font);	

		valorListarLbl = new JLabel("Total: R$ ");
		valorListarLbl.setBounds(280, 525, 100, 20);
		valorListarLbl.setFont(font);
		
		totalTotalListarLbl = new JLabel("000,00");
		totalTotalListarLbl.setBounds(370, 525, 150, 20);
		totalTotalListarLbl.setFont(font);
		
		
		// FORMAS DE PESQUISA
		diaListarBox = new JComboBox<String>(dias);
		diaListarBox.setBounds(345, 25, 80, 20);		
		diaListarBox.setFont(font);
		
		mesListarBox = new JComboBox<String>(meses);
		mesListarBox.setBounds(430, 25, 140, 20);		
		mesListarBox.setFont(font);
		
		anoListarBox = new JComboBox<String>(anos);
		anoListarBox.setBounds(575, 25, 80, 20);		
		anoListarBox.setFont(font);
		
		pesquisarNomeField = new JTextField();
		pesquisarNomeField.setBounds(10, 25, 215, 20);
		pesquisarNomeField.setFont(font);
		
		//Mostrar Nome, cidade, numOS e total
		jScrollPaneListar = new JScrollPane();
		jScrollPaneListar.setBounds(10, 78, 655, 280);
		
		jtableListar = new JTable();
		jtableListar.setModel(new DefaultTableModel(
				new Object[][] {
					
				},titulosListar
		));
		
		
		jScrollPaneListar.setViewportView(jtableListar);
		
		//mostrar os serviços
		jScrollPaneListarServicos = new JScrollPane();
		jScrollPaneListarServicos.setBounds(10, 370, 450, 150);
		
		jTableListarServico = new JTable();
		jTableListarServico.setModel(new DefaultTableModel(
				new Object[][] {
					
				},titulos
		));
		
		
		jScrollPaneListarServicos.setViewportView(jTableListarServico);
		redimencionaColunasServicoListar(jTableListarServico);
		
		listarOSPanel.add(jScrollPaneListar);
		listarOSPanel.add(jScrollPaneListarServicos);
		listarOSPanel.add(verificarOSBtn);
		listarOSPanel.add(salvarModificacoesBtn);
		listarOSPanel.add(pesquisarBtn);
		listarOSPanel.add(excluirBtn);		
		listarOSPanel.add(diaListarBox);		
		listarOSPanel.add(mesListarBox);		
		listarOSPanel.add(anoListarBox);			
		listarOSPanel.add(valorListarLbl);			
		listarOSPanel.add(totalTotalListarLbl);	
		listarOSPanel.add(pesquisarNomeField);
		
	}

	public JTextField getPesquisarNomeField() {
		return pesquisarNomeField;
	}

	public JLabel getTotalTotalListarLbl() {
		return totalTotalListarLbl;
	}

	private void jpanelOpcoes() {
		cadastrarNovaOSBtn = new JButton("Nova O.S.");
		cadastrarNovaOSBtn.setBounds(2, 2, 96, 96);
		cadastrarNovaOSBtn.setBackground(Color.white);
		opcPanel.add(cadastrarNovaOSBtn);
		
		listarOSBtn = new JButton("Listar O.S.");
		listarOSBtn.setBackground(Color.WHITE);
		listarOSBtn.setBounds(2, 102, 96, 96);
		opcPanel.add(listarOSBtn);
		
		cadastrarBtn = new JButton("Cadastrar");
		cadastrarBtn.setBackground(new Color(27, 245, 45));
		cadastrarBtn.setBounds(2, 474, 96, 96);
		opcPanel.add(cadastrarBtn);
	}

	private void jpanelCadastrar() {
		nomeLbl = new JLabel("Nome: ");
		nomeLbl.setBounds(10, 10, 50, 20);
		nomeLbl.setFont(font);
		
		enderecoLbl = new JLabel("Endereço: ");
		enderecoLbl.setBounds(10, 30, 100, 20);
		enderecoLbl.setFont(font);
		
		bairroLbl = new JLabel("Bairro: ");
		bairroLbl.setBounds(10, 50, 100, 20);
		bairroLbl.setFont(font);
		
		cidadeLbl = new JLabel("Cidade: ");
		cidadeLbl.setBounds(10, 70, 100, 20);
		cidadeLbl.setFont(font);
		
		foneLbl = new JLabel("Fone(s): ");
		foneLbl.setBounds(300, 50, 100, 20);
		foneLbl.setFont(font);
		
		estadoLbl = new JLabel("Estado: ");
		estadoLbl.setBounds(300, 70, 100, 20);
		estadoLbl.setFont(font);
		
		nomeField = new JTextField();
		nomeField.setBounds(60, 10, 600, 20);
		nomeField.setFont(font);
		
		enderecoField = new JTextField();
		enderecoField.setBounds(96, 30, 564, 20);
		enderecoField.setFont(font);
		
		bairroField = new JTextField();
		bairroField.setBounds(80, 50, 215, 20);
		bairroField.setFont(font);
		
		cidadeField = new JTextField();
		cidadeField.setBounds(80, 70, 215, 20);
		cidadeField.setFont(font);
		
		foneField = new JTextField();
		foneField.setBounds(375, 50, 285, 20);
		foneField.setFont(font);
		
		estadoField = new JTextField();
		estadoField.setBounds(370, 70, 290, 20);
		estadoField.setFont(font);
		
		numOSLbl = new JLabel("num. O.S.: ");
		numOSLbl.setBounds(10, 97, 90, 20);		
		numOSLbl.setFont(font);
		
		numOSField = new JTextField();
		numOSField.setBounds(105, 97, 90, 20);		
		numOSField.setFont(font);
		
		dataLbl = new JLabel("Data: ");
		dataLbl.setBounds(320, 97, 90, 20);		
		dataLbl.setFont(font);
		
		diaBox = new JComboBox<String>(dias);
		diaBox.setBounds(380, 97, 80, 20);		
		diaBox.setFont(font);
		
		mesBox = new JComboBox<String>(meses);
		mesBox.setBounds(465, 97, 100, 20);		
		mesBox.setFont(font);
		
		anoBox = new JComboBox<String>(anos);
		anoBox.setBounds(570, 97, 90, 20);		
		anoBox.setFont(font);
		
		quantidadeLbl = new JLabel("Quant.: ");
		quantidadeLbl.setBounds(10, 153, 80, 20);
		quantidadeLbl.setFont(font);
		
		unidadeLbl = new JLabel("Unid.: ");
		unidadeLbl.setBounds(130, 153, 80, 20);
		unidadeLbl.setFont(font);
		
		descricaoLbl = new JLabel("Descrição dos Serviços: ");
		descricaoLbl.setBounds(10, 130, 200, 20);
		descricaoLbl.setFont(font);
		
		valoresLbl = new JLabel("Valores -> ");
		valoresLbl.setBounds(235, 153, 100, 20);
		valoresLbl.setForeground(new Color(0, 0, 255));
		valoresLbl.setFont(font);
		
		unitarioLbl = new JLabel("Unitário: ");
		unitarioLbl.setBounds(330, 153, 90, 20);
		unitarioLbl.setFont(font);
		
		totalLbl = new JLabel("Total: ");
		totalLbl.setBounds(505, 153, 80, 20);
		totalLbl.setFont(font);
		
		quantidadeField = new JTextField();
		quantidadeField.setBounds(75, 153, 40, 20);
		quantidadeField.setFont(font);
		
		unidadeField = new JTextField();
		unidadeField.setBounds(185, 153, 40, 20);
		unidadeField.setFont(font);
		
		descricaoField = new JTextField();
		descricaoField.setBounds(210, 130, 453, 20);
		descricaoField.setFont(font);
		
		unitarioField = new JTextField();
		unitarioField.setBounds(415, 153, 80, 20);
		unitarioField.setFont(font);
		
		totalField = new JTextField();
		totalField.setBounds(565, 153, 99, 20);
		totalField.setFont(font);
				
		novoServicoBtn = new JButton("Novo Serviço");
		novoServicoBtn.setBounds(510, 495, 150, 40);
		novoServicoBtn.setFont(font);
		
		valorLbl = new JLabel("Total: R$ ");
		valorLbl.setBounds(480, 465, 100, 20);
		valorLbl.setFont(font);
		
		totalDoTotalLbl = new JLabel("000,00");
		totalDoTotalLbl.setBounds(570, 465, 150, 20);
		totalDoTotalLbl.setFont(font);
		
		jScrollPane = new JScrollPane();
		jScrollPane.setBounds(10, 178, 655, 280);
		add(jScrollPane);
		
		jTable = new JTable();
		jTable.setModel(new DefaultTableModel(
				new Object[][] {
					
				},titulos
		));
		
		
		jScrollPane.setViewportView(jTable);
		
		redimencionaColunas(jTable);
		
		cadastrarNovaOSPanel.add(nomeLbl);
		cadastrarNovaOSPanel.add(enderecoLbl);
		cadastrarNovaOSPanel.add(bairroLbl);
		cadastrarNovaOSPanel.add(cidadeLbl);
		cadastrarNovaOSPanel.add(foneLbl);
		cadastrarNovaOSPanel.add(estadoLbl);
		cadastrarNovaOSPanel.add(nomeField);
		cadastrarNovaOSPanel.add(enderecoField);
		cadastrarNovaOSPanel.add(bairroField);
		cadastrarNovaOSPanel.add(cidadeField);
		cadastrarNovaOSPanel.add(foneField);
		cadastrarNovaOSPanel.add(estadoField);
		cadastrarNovaOSPanel.add(quantidadeLbl);
		cadastrarNovaOSPanel.add(quantidadeField);
		cadastrarNovaOSPanel.add(unidadeLbl);
		cadastrarNovaOSPanel.add(unidadeField);
		cadastrarNovaOSPanel.add(descricaoLbl);
		cadastrarNovaOSPanel.add(descricaoField);
		cadastrarNovaOSPanel.add(valoresLbl);
		cadastrarNovaOSPanel.add(unitarioLbl);
		cadastrarNovaOSPanel.add(unitarioField);
		cadastrarNovaOSPanel.add(totalLbl);
		cadastrarNovaOSPanel.add(totalField);
		cadastrarNovaOSPanel.add(jScrollPane);	
		cadastrarNovaOSPanel.add(numOSLbl);	
		cadastrarNovaOSPanel.add(numOSField);	
		cadastrarNovaOSPanel.add(dataLbl);	
		cadastrarNovaOSPanel.add(diaBox);	
		cadastrarNovaOSPanel.add(mesBox);	
		cadastrarNovaOSPanel.add(anoBox);	
		cadastrarNovaOSPanel.add(novoServicoBtn);	
		cadastrarNovaOSPanel.add(valorLbl);	
		cadastrarNovaOSPanel.add(totalDoTotalLbl);	
	}

	public JLabel getTotalDoTotalLbl() {
		return totalDoTotalLbl;
	}

	private void redimencionaColunas(JTable jTable)
    {
		jTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		jTable.getColumnModel().getColumn(0).setPreferredWidth(70);
		jTable.getColumnModel().getColumn(1).setPreferredWidth(70);
		jTable.getColumnModel().getColumn(2).setPreferredWidth(360);
		jTable.getColumnModel().getColumn(3).setPreferredWidth(70);
		jTable.getColumnModel().getColumn(4).setPreferredWidth(82);
		
    }
	private void redimencionaColunasServicoListar(JTable jTable)
    {
		jTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		jTable.getColumnModel().getColumn(0).setPreferredWidth(50);
		jTable.getColumnModel().getColumn(1).setPreferredWidth(50);
		jTable.getColumnModel().getColumn(2).setPreferredWidth(200);
		jTable.getColumnModel().getColumn(3).setPreferredWidth(72);
		jTable.getColumnModel().getColumn(4).setPreferredWidth(75);
		
    }
	public JPanel getCadastrarNovaOSPanel() {
		return cadastrarNovaOSPanel;
	}

	public JPanel getListarOSPanel() {
		return listarOSPanel;
	}

	public JComboBox<String> getDiaListarBox() {
		return diaListarBox;
	}

	public JComboBox<String> getMesListarBox() {
		return mesListarBox;
	}

	public JComboBox<String> getAnoListarBox() {
		return anoListarBox;
	}

	public JButton getExcluirBtn() {
		return excluirBtn;
	}

	public JButton getPesquisarBtn() {
		return pesquisarBtn;
	}

	public JButton getSalvarModificacoesBtn() {
		return salvarModificacoesBtn;
	}

	public JButton getVerificarOSBtn() {
		return verificarOSBtn;
	}

	public JButton getListarOSBtn() {
		return listarOSBtn;
	}

	public JButton getCadastrarNovaOSBtn() {
		return cadastrarNovaOSBtn;
	}

	public JTextField getNomeField() {
		return nomeField;
	}

	public void setNomeField(JTextField nomeField) {
		this.nomeField = nomeField;
	}

	public JTextField getEnderecoField() {
		return enderecoField;
	}

	public void setEnderecoField(JTextField enderecoField) {
		this.enderecoField = enderecoField;
	}

	public JTextField getBairroField() {
		return bairroField;
	}

	public void setBairroField(JTextField bairroField) {
		this.bairroField = bairroField;
	}

	public JTextField getCidadeField() {
		return cidadeField;
	}

	public void setCidadeField(JTextField cidadeField) {
		this.cidadeField = cidadeField;
	}

	public JTextField getFoneField() {
		return foneField;
	}

	public void setFoneField(JTextField foneField) {
		this.foneField = foneField;
	}

	public JTextField getEstadoField() {
		return estadoField;
	}

	public void setEstadoField(JTextField estadoField) {
		this.estadoField = estadoField;
	}

	public JTextField getQuantidadeField() {
		return quantidadeField;
	}

	public void setQuantidadeField(JTextField quantidadeField) {
		this.quantidadeField = quantidadeField;
	}

	public JTextField getUnidadeField() {
		return unidadeField;
	}

	public void setUnidadeField(JTextField unidadeField) {
		this.unidadeField = unidadeField;
	}

	public JTextField getDescricaoField() {
		return descricaoField;
	}

	public void setDescricaoField(JTextField descricaoField) {
		this.descricaoField = descricaoField;
	}

	public JTextField getUnitarioField() {
		return unitarioField;
	}

	public void setUnitarioField(JTextField unitarioField) {
		this.unitarioField = unitarioField;
	}

	public JTextField getTotalField() {
		return totalField;
	}

	public void setTotalField(JTextField totalField) {
		this.totalField = totalField;
	}

	public JTextField getNumOSField() {
		return numOSField;
	}

	public void setNumOSField(JTextField numOSField) {
		this.numOSField = numOSField;
	}

	public JComboBox<String> getDiaBox() {
		return diaBox;
	}

	public void setDiaBox(JComboBox<String> diaBox) {
		this.diaBox = diaBox;
	}

	public JComboBox<String> getMesBox() {
		return mesBox;
	}

	public void setMesBox(JComboBox<String> mesBox) {
		this.mesBox = mesBox;
	}

	public JComboBox<String> getAnoBox() {
		return anoBox;
	}
	
	public void setAnoBox(JComboBox<String> anoBox) {
		this.anoBox = anoBox;
	}

	public JTable getjTable() {
		return jTable;
	}

	public void setjTable(JTable jTable) {
		this.jTable = jTable;
	}

	public JButton getCadastrarBtn() {
		return cadastrarBtn;
	}

	public JButton getNovoServicoBtn() {
		return novoServicoBtn;
	}
	
	

}
