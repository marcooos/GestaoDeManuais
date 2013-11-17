package br.com.pcsocial.gestao.visao.manter;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import br.com.pcsocial.gestao.util.Conversores;
import br.com.pcsocial.gestao.visao.consulta.ListaDeMaquinasUI;
import br.com.pcsocial.gestao.controle.DocumentoControler;
import br.com.pcsocial.gestao.modelo.Documento;
import br.com.pcsocial.gestao.modelo.Maquina;

public class AdicionarDocumentoUI extends JDialog {

	private static final long serialVersionUID = 1L;
	private JDialog adicionarDocumento;
	private Dimension dmsTela;
	private JButton btnConfirmar, btnCancelar, btnBuscarAnexo,
			btnBuscarMaquina;
	private JPanel panel, panelInfBotoes, panelCentral;
	private BorderLayout layout;
	private FlowLayout layoutInfBotoes;
	private FlowLayout layoutCentral;
	private JLabel lbDocumento, lbBuscarAnexo, lbMaquina;
	private JTextField tfDocumento, tfAnexo, tfMaquina;
	private Dimension dmsEdit, dmsEditDois, dmsLabelDois, dmsBotao;
	private Documento documento;
	private Maquina maquina;
	private byte janelaAtiva;
	private ListaDeMaquinasUI listaDeMaquinasUI;
	private byte[] bytes;
	private Conversores conversores;
	private String nomeArquivo;

	public AdicionarDocumentoUI() {

	}

	public void adicionarDocumento() {
		// Instanciar Documento
		documento = new Documento();
		maquina = new Maquina();

		// Criar interface
		createAndShowUI("Adicionar documento", documento);
		janelaAtiva = 0;
	}

	public void alterarDocumento(Long id) {
		// Instanciar Documento
		documento = new Documento();
		maquina = new Maquina();

		// Intanciar Documento Cliente
		DocumentoControler pc = new DocumentoControler();
		documento = pc.buscarDocumentoId(id);
		maquina = documento.getMaquina();
		try {
			bytes = documento.getAnexo();
		} catch (NullPointerException e) {
			bytes = null;
		}

		// Criar interface
		janelaAtiva = 1;
		createAndShowUI("Alterar documento", documento);
	}

	public void excluirDocumento(Long id) {
		documento = new Documento();
		// Intanciar Documento Cliente
		DocumentoControler pc = new DocumentoControler();
		documento = pc.buscarDocumentoId(id);
		pc.excluirDocumento(id);

	}

	public void createAndShowUI(String t, Documento p) {
		// Propriedades dos componentes da janela
		dmsEdit = new Dimension(620, 27);
		dmsLabelDois = new Dimension(90, 27);
		dmsEditDois = new Dimension(400, 27);
		dmsBotao = new Dimension(218, 27);

		// Instanciar Janela
		dmsTela = new Dimension(800, 600);
		adicionarDocumento = new JDialog();
		// Tãtulo da Janela
		adicionarDocumento.setTitle(t);

		// Textos
		lbDocumento = new JLabel("Documento", SwingConstants.RIGHT);
		lbDocumento.setPreferredSize(dmsLabelDois);
		lbBuscarAnexo = new JLabel("Arquivo", SwingConstants.RIGHT);
		lbBuscarAnexo.setPreferredSize(dmsLabelDois);
		lbMaquina = new JLabel("Maquina", SwingConstants.RIGHT);
		lbMaquina.setPreferredSize(dmsLabelDois);

		// Editores
		tfDocumento = new JTextField();
		tfDocumento.setPreferredSize(dmsEdit);
		tfAnexo = new JTextField();
		tfAnexo.setPreferredSize(dmsEditDois);
		tfMaquina = new JTextField();
		tfMaquina.setPreferredSize(dmsEditDois);
		tfMaquina.setEditable(false);

		// Atribuir campos a documento
		if (janelaAtiva == 1) {
			conversores = new Conversores();
			tfDocumento.setText(documento.getDocumento());
			try {
				tfMaquina
						.setText(maquina.getNome() + "/" + maquina.getModelo());
				tfAnexo.setText(conversores.convertByteToFile(
						documento.getAnexo(), documento.getArquivo()).getName());

			} catch (NullPointerException e) {
				tfMaquina.setText("");
				tfAnexo.setText("");
			}
		}

		// Botoes
		btnConfirmar = new JButton("Confirmar");
		btnCancelar = new JButton("Cancelar");
		btnBuscarAnexo = new JButton("Buscar anexo");
		btnBuscarAnexo.setPreferredSize(dmsBotao);
		btnBuscarMaquina = new JButton("Buscar maquina");
		btnBuscarMaquina.setPreferredSize(dmsBotao);

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
		btnBuscarAnexo.setIcon(new ImageIcon(getClass().getResource(
				"/gui/icones/acoes/pesquisa.png")));
		btnBuscarAnexo.addActionListener(al);
		btnBuscarMaquina.setIcon(new ImageIcon(getClass().getResource(
				"/gui/icones/acoes/pesquisa.png")));
		btnBuscarMaquina.addActionListener(al);

		// Propriedades dos paineis
		panel.setLayout(layout);
		panelInfBotoes.setLayout(layoutInfBotoes);
		panelCentral.setLayout(layoutCentral);
		panel.add(panelCentral, BorderLayout.CENTER);
		panel.add(panelInfBotoes, BorderLayout.SOUTH);

		// Adicionar componentes ao painel central
		panelCentral.add(lbDocumento);
		panelCentral.add(tfDocumento);
		panelCentral.add(lbMaquina);
		panelCentral.add(tfMaquina);
		panelCentral.add(btnBuscarMaquina);
		panelCentral.add(lbBuscarAnexo);
		panelCentral.add(tfAnexo);
		panelCentral.add(btnBuscarAnexo);

		// Adicionar componentes ao painel inferior
		panelInfBotoes.add(btnConfirmar);
		panelInfBotoes.add(btnCancelar);

		// Propriedades da Janela
		adicionarDocumento.setPreferredSize(dmsTela);
		adicionarDocumento.setSize(dmsTela);
		adicionarDocumento.setMaximumSize(dmsTela);
		adicionarDocumento.setMinimumSize(dmsTela);
		adicionarDocumento.add(panel);
		adicionarDocumento.setLocationRelativeTo(null);
		adicionarDocumento.setModal(true);
		adicionarDocumento.setVisible(true);
		adicionarDocumento
				.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

	}

	//
	ActionListener al = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource().equals(btnConfirmar)) {
				DocumentoControler pc = new DocumentoControler();
				documento.setDocumento(tfDocumento.getText());
				documento.setMaquina(maquina);
				documento.setArquivo(tfAnexo.getText());
				documento.setAnexo(bytes);

				if (!pc.validaDocumentoCadastrado(nomeArquivo)) {
					javax.swing.JOptionPane.showMessageDialog(
							null,
							"Documento já cadastrado",
							"Informação",
							0,
							new ImageIcon(getClass().getResource(
									"/gui/icones/acoes/informacao.png")));

				} else {
					if (janelaAtiva == 0) {
						if (pc.adicionarDocumento(documento)) {
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
							documento = null;
							maquina = null;
							bytes = null;
							tfDocumento.setText(null);
							tfAnexo.setText(null);
							tfMaquina.setText(null);
							documento = new Documento();
						}
					}
					if (janelaAtiva == 1) {
						if (pc.alterarDocumento(documento)) {
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
					adicionarDocumento.dispose();
				}
			}
			if (e.getSource().equals(btnBuscarMaquina)) {
				listaDeMaquinasUI = new ListaDeMaquinasUI();
				maquina = (Maquina) listaDeMaquinasUI.createAndShowUI();
				tfMaquina
						.setText(maquina.getNome() + "/" + maquina.getModelo());
			}
			if (e.getSource().equals(btnBuscarAnexo)) {
				JFileChooser arquivo = new JFileChooser();
				arquivo.setDialogTitle("Selecione o arquivo desejado");
				arquivo.setFileSelectionMode(JFileChooser.FILES_ONLY);
				arquivo.setFileFilter(new javax.swing.filechooser.FileFilter() {
					public boolean accept(File f) {
						return f.getName().toLowerCase().endsWith(".pdf")
								|| f.isDirectory();
					}

					public String getDescription() {
						return "Arquivos pdf (.pdf)";
					}
				});
				arquivo.showOpenDialog(rootPane);

				// converte o objeto file em array de bytes
				conversores = new Conversores();
				bytes = conversores
						.convertFileToByte(arquivo.getSelectedFile());
				nomeArquivo = arquivo.getSelectedFile().getName();
				try {
					tfAnexo.setText(nomeArquivo);
				} catch (java.lang.NullPointerException e1) {
					tfAnexo.setText("");
				}
			}

		}
	};
}
