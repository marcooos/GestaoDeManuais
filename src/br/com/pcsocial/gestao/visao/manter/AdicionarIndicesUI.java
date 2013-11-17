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
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import br.com.pcsocial.gestao.controle.IndicesControler;
import br.com.pcsocial.gestao.modelo.Indices;
import br.com.pcsocial.gestao.modelo.Documento;
import br.com.pcsocial.gestao.visao.consulta.ListaDeDocumentosUI;

public class AdicionarIndicesUI extends JDialog {

	private static final long serialVersionUID = 1L;
	private JDialog adicionarIndices;
	private Dimension dmsTela;
	private JButton btnConfirmar, btnCancelar, btnBuscarDocumento;
	private JPanel panel, panelInfBotoes, panelCentral;
	private BorderLayout layout;
	private FlowLayout layoutInfBotoes;
	private FlowLayout layoutCentral;
	private JLabel lbIndices, lbDocumento;
	private JTextField tfIndices, tfDocumento;
	private Dimension dmsEdit, dmsEditDois, dmsLabelDois, dmsBotao;
	private Indices indices;
	private Documento documento;
	private byte janelaAtiva;
	private ListaDeDocumentosUI listaDeDocumentosUI;

	public AdicionarIndicesUI() {

	}

	public void adicionarIndices() {
		// Instanciar Indices
		indices = new Indices();
		documento = new Documento();

		// Criar interface
		createAndShowUI("Adicionar indices", indices);
		janelaAtiva = 0;
	}

	public void alterarIndices(Long id) {
		// Instanciar Indices
		indices = new Indices();
		documento = new Documento();

		// Intanciar Indices Cliente
		IndicesControler pc = new IndicesControler();
		indices = pc.buscarIndicesId(id);
		documento = indices.getDocumento();

		// Criar interface
		janelaAtiva = 1;
		createAndShowUI("Alterar indices", indices);
	}

	public void excluirIndices(Long id) {
		indices = new Indices();
		// Intanciar Indices Cliente
		IndicesControler pc = new IndicesControler();
		indices = pc.buscarIndicesId(id);
		pc.excluirIndices(id);

	}

	public void createAndShowUI(String t, Indices p) {
		// Propriedades dos componentes da janela
		dmsEdit = new Dimension(620, 27);
		dmsLabelDois = new Dimension(90, 27);
		dmsEditDois = new Dimension(400, 27);
		dmsBotao = new Dimension(218, 27);

		// Instanciar Janela
		dmsTela = new Dimension(800, 600);
		adicionarIndices = new JDialog();
		// Tãtulo da Janela
		adicionarIndices.setTitle(t);

		// Textos
		lbIndices = new JLabel("Indices", SwingConstants.RIGHT);
		lbIndices.setPreferredSize(dmsLabelDois);
		lbDocumento = new JLabel("Documento", SwingConstants.RIGHT);
		lbDocumento.setPreferredSize(dmsLabelDois);

		// Editores
		tfIndices = new JTextField();
		tfIndices.setPreferredSize(dmsEdit);
		tfDocumento = new JTextField();
		tfDocumento.setPreferredSize(dmsEditDois);
		tfDocumento.setEditable(false);

		// Atribuir campos a indices
		if (janelaAtiva == 1) {
			tfIndices.setText(indices.getIndice());
			try {
				tfDocumento.setText(documento.getDocumento() + "/"
						+ documento.getArquivo());
			} catch (NullPointerException e) {
				tfDocumento.setText("");
			}
		}

		// Botoes
		btnConfirmar = new JButton("Confirmar");
		btnCancelar = new JButton("Cancelar");
		btnBuscarDocumento = new JButton("Buscar documento");
		btnBuscarDocumento.setPreferredSize(dmsBotao);

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
		btnBuscarDocumento.setIcon(new ImageIcon(getClass().getResource(
				"/gui/icones/acoes/pesquisa.png")));
		btnBuscarDocumento.addActionListener(al);

		// Propriedades dos paineis
		panel.setLayout(layout);
		panelInfBotoes.setLayout(layoutInfBotoes);
		panelCentral.setLayout(layoutCentral);
		panel.add(panelCentral, BorderLayout.CENTER);
		panel.add(panelInfBotoes, BorderLayout.SOUTH);

		// Adicionar componentes ao painel central
		panelCentral.add(lbIndices);
		panelCentral.add(tfIndices);
		panelCentral.add(lbDocumento);
		panelCentral.add(tfDocumento);
		panelCentral.add(btnBuscarDocumento);

		// Adicionar componentes ao painel inferior
		panelInfBotoes.add(btnConfirmar);
		panelInfBotoes.add(btnCancelar);

		// Propriedades da Janela
		adicionarIndices.setPreferredSize(dmsTela);
		adicionarIndices.setSize(dmsTela);
		adicionarIndices.setMaximumSize(dmsTela);
		adicionarIndices.setMinimumSize(dmsTela);
		adicionarIndices.add(panel);
		adicionarIndices.setLocationRelativeTo(null);
		adicionarIndices.setModal(true);
		adicionarIndices.setVisible(true);
		adicionarIndices
				.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

	}

	//
	ActionListener al = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource().equals(btnConfirmar)) {
				IndicesControler pc = new IndicesControler();
				indices.setIndice(tfIndices.getText());
				indices.setDocumento(documento);

				if (!pc.validaIndiceCadastrado(tfIndices.getText())) {
					javax.swing.JOptionPane.showMessageDialog(
							null,
							"Indice já cadastrado",
							"Informação",
							0,
							new ImageIcon(getClass().getResource(
									"/gui/icones/acoes/informacao.png")));

				} else {
					if (janelaAtiva == 0) {
						if (pc.adicionarIndices(indices)) {
							javax.swing.JOptionPane
									.showMessageDialog(
											null,
											"Cadastro realizado com sucesso",
											"Informação",
											0,
											new ImageIcon(
													getClass()
															.getResource(
																	"/gui/icones/acoes/informacao.png")));
							indices = null;
							documento = null;
							tfIndices.setText(null);
							tfDocumento.setText(null);
							indices = new Indices();
						}
					}
					if (janelaAtiva == 1) {
						if (pc.alterarIndices(indices)) {
							javax.swing.JOptionPane
									.showMessageDialog(
											null,
											"Cadastro alterado com sucesso",
											"Informação",
											0,
											new ImageIcon(
													getClass()
															.getResource(
																	"/gui/icones/acoes/informacao.png")));
						}

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
					adicionarIndices.dispose();
				}
			}
			if (e.getSource().equals(btnBuscarDocumento)) {
				listaDeDocumentosUI = new ListaDeDocumentosUI();
				documento = (Documento) listaDeDocumentosUI.createAndShowUI();
				tfDocumento.setText(documento.getDocumento() + "/"
						+ documento.getArquivo());
			}
		}
	};
}
