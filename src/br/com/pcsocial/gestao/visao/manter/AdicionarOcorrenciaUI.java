package br.com.pcsocial.gestao.visao.manter;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import br.com.pcsocial.gestao.controle.OcorrenciaControler;
import br.com.pcsocial.gestao.controle.ValidarLoginControle;
import br.com.pcsocial.gestao.modelo.Indices;
import br.com.pcsocial.gestao.modelo.Ocorrencia;
import br.com.pcsocial.gestao.modelo.Pessoa;
import br.com.pcsocial.gestao.visao.consulta.ListaDeIndicessUI;
import br.com.pcsocial.gestao.util.FixedLengthDocument;

public class AdicionarOcorrenciaUI extends JDialog {

	private static final long serialVersionUID = 1L;
	private JDialog adicionarOcorrencia;
	private Dimension dmsTela;
	private JButton btnConfirmar, btnCancelar, btnBuscarIndices;
	private JPanel panel, panelInfBotoes, panelCentral;
	private BorderLayout layout;
	private FlowLayout layoutInfBotoes;
	private FlowLayout layoutCentral;
	private JLabel lbOcorrencia, lbIndices;
	private JTextField tfIndices;
	private JTextArea tfOcorrencia;
	private JScrollPane jspOcorrencia;
	private Dimension dmsEditTres, dmsEditDois, dmsLabelDois, dmsBotao;
	private Ocorrencia ocorrencia;
	private Pessoa pessoa;
	private Indices indices;
	private byte janelaAtiva;
	private ListaDeIndicessUI listaDeIndicessUI;

	public AdicionarOcorrenciaUI() {

	}

	public void adicionarOcorrencia() {
		// Instanciar Ocorrencia
		ocorrencia = new Ocorrencia();
		indices = new Indices();
		pessoa = new Pessoa();
		pessoa = ValidarLoginControle.getUsuarioLogado();

		// Criar interface
		createAndShowUI("Adicionar ocorrencia", ocorrencia);
		janelaAtiva = 0;
	}

	public void alterarOcorrencia(Long id) {
		// Instanciar Ocorrencia
		ocorrencia = new Ocorrencia();
		indices = new Indices();

		// Intanciar Ocorrencia Cliente
		OcorrenciaControler pc = new OcorrenciaControler();
		ocorrencia = pc.buscarOcorrenciaId(id);
		indices = ocorrencia.getIndices();
		pessoa = ocorrencia.getPessoa();

		// Criar interface
		janelaAtiva = 1;
		createAndShowUI("Alterar ocorrencia", ocorrencia);
	}

	public void excluirOcorrencia(Long id) {
		ocorrencia = new Ocorrencia();
		// Intanciar Ocorrencia Cliente
		OcorrenciaControler pc = new OcorrenciaControler();
		ocorrencia = pc.buscarOcorrenciaId(id);
		pc.excluirOcorrencia(id);

	}

	public void createAndShowUI(String t, Ocorrencia p) {
		// Propriedades dos componentes da janela
		//dmsEdit = new Dimension(620, 27);
		dmsLabelDois = new Dimension(90, 27);
		dmsEditDois = new Dimension(400, 27);
		dmsEditTres = new Dimension(620, 90);
		dmsBotao = new Dimension(218, 27);

		// Instanciar Janela
		dmsTela = new Dimension(800, 600);
		adicionarOcorrencia = new JDialog();
		// Tãtulo da Janela
		adicionarOcorrencia.setTitle(t);

		// Textos
		lbOcorrencia = new JLabel("Ocorrencia", SwingConstants.RIGHT);
		lbOcorrencia.setPreferredSize(dmsLabelDois);
		lbIndices = new JLabel("Indices", SwingConstants.RIGHT);
		lbIndices.setPreferredSize(dmsLabelDois);

		// Editores		
		tfOcorrencia = new JTextArea();		
		//tfOcorrencia.setPreferredSize(dmsEditTres);		
		tfOcorrencia.setLineWrap(true);		
		tfOcorrencia.setDocument(new FixedLengthDocument(255));
		jspOcorrencia = new JScrollPane(tfOcorrencia);
		jspOcorrencia.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		jspOcorrencia.setPreferredSize(dmsEditTres);		
		tfIndices = new JTextField();
		tfIndices.setPreferredSize(dmsEditDois);
		tfIndices.setEditable(false);

		// Atribuir campos a ocorrencia
		if (janelaAtiva == 1) {
			tfOcorrencia.setText(ocorrencia.getDescricao());
			try {
				tfIndices.setText(indices.getIndice());								
			} catch (NullPointerException e) {
				tfIndices.setText("");
			}
		}

		// Botoes
		btnConfirmar = new JButton("Confirmar");
		btnCancelar = new JButton("Cancelar");
		btnBuscarIndices = new JButton("Buscar indices");
		btnBuscarIndices.setPreferredSize(dmsBotao);

		// Paineis
		panel = new JPanel();
		panelInfBotoes = new JPanel();
		panelCentral = new JPanel();
		layout = new BorderLayout();
		layoutInfBotoes = new FlowLayout(FlowLayout.LEFT);
		layoutCentral = new FlowLayout(FlowLayout.LEFT);
		

		// Propriedades dos componentes
		panelInfBotoes.setBorder(new javax.swing.border.LineBorder(
				new java.awt.Color(160, 160, 160), 1, true));
		panelCentral.setBorder(javax.swing.BorderFactory
				.createTitledBorder("Dados gerais"));

		// Acoes
		btnConfirmar.setIcon(new ImageIcon(getClass().getResource(
				"/gui/icones/acoes/confirmar.png")));
		btnConfirmar.addActionListener(al);
		btnCancelar.setIcon(new ImageIcon(getClass().getResource(
				"/gui/icones/acoes/cancelar.png")));
		btnCancelar.addActionListener(al);
		btnBuscarIndices.setIcon(new ImageIcon(getClass().getResource(
				"/gui/icones/acoes/pesquisa.png")));
		btnBuscarIndices.addActionListener(al);

		// Propriedades dos paineis
		panel.setLayout(layout);
		panelInfBotoes.setLayout(layoutInfBotoes);
		panelCentral.setLayout(layoutCentral);
		panel.add(panelCentral, BorderLayout.CENTER);
		panel.add(panelInfBotoes, BorderLayout.SOUTH);

		// Adicionar componentes ao painel central		
		panelCentral.add(lbOcorrencia);		
		panelCentral.add(jspOcorrencia);
		panelCentral.add(lbIndices);
		panelCentral.add(tfIndices);
		panelCentral.add(btnBuscarIndices);

		// Adicionar componentes ao painel inferior
		panelInfBotoes.add(btnConfirmar);
		panelInfBotoes.add(btnCancelar);

		// Propriedades da Janela
		adicionarOcorrencia.setPreferredSize(dmsTela);
		adicionarOcorrencia.setSize(dmsTela);
		adicionarOcorrencia.setMaximumSize(dmsTela);
		adicionarOcorrencia.setMinimumSize(dmsTela);
		adicionarOcorrencia.add(panel);
		adicionarOcorrencia.setLocationRelativeTo(null);
		adicionarOcorrencia.setModal(true);
		adicionarOcorrencia.setVisible(true);
		adicionarOcorrencia
				.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

	}

	//
	ActionListener al = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource().equals(btnConfirmar)) {
				OcorrenciaControler pc = new OcorrenciaControler();
				ocorrencia.setDescricao(tfOcorrencia.getText());
				ocorrencia.setIndices(indices);
				ocorrencia.setPessoa(pessoa);

				if (janelaAtiva == 0) {
					if (pc.adicionarOcorrencia(ocorrencia)) {
						javax.swing.JOptionPane.showMessageDialog(
								null,
								"Cadastro realizado com sucesso",
								"Informação",
								0,
								new ImageIcon(getClass().getResource(
										"/gui/icones/acoes/informacao.png")));
						ocorrencia = null;
						indices = null;
						tfOcorrencia.setText(null);
						tfIndices.setText(null);
						ocorrencia = new Ocorrencia();
						indices = new Indices();
					}
				}
				if (janelaAtiva == 1) {
					if (pc.alterarOcorrencia(ocorrencia)) {
						javax.swing.JOptionPane.showMessageDialog(
								null,
								"Cadastro alterado com sucesso",
								"Informação",
								0,
								new ImageIcon(getClass().getResource(
										"/gui/icones/acoes/informacao.png")));
					}

				}
			}
			if (e.getSource().equals(btnCancelar)) {
				if (javax.swing.JOptionPane
						.showConfirmDialog(
								null,
								"Deseja cancelar a operação? \n"
										+ " Todas as informaçães não salvas serão perdidas",
								"Confirme sua operação ",
								javax.swing.JOptionPane.YES_NO_OPTION,
								0,
								new ImageIcon(getClass().getResource(
										"/gui/icones/acoes/alerta.png"))) == 0) {
					adicionarOcorrencia.dispose();
				}
			}
			if (e.getSource().equals(btnBuscarIndices)) {
				listaDeIndicessUI = new ListaDeIndicessUI();
				indices = (Indices) listaDeIndicessUI.createAndShowUI();
				tfIndices.setText(indices.getIndice());
			}
		}
	};
}
