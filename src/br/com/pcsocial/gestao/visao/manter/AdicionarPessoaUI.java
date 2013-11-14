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
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import br.com.pcsocial.gestao.controle.PessoaControler;
import br.com.pcsocial.gestao.modelo.Pessoa;

public class AdicionarPessoaUI extends JDialog {

	private static final long serialVersionUID = 1L;
	private JDialog adicionarPessoa;
	private Dimension dmsTela;
	private JButton btnConfirmar, btnCancelar, btnBuscarCidades,
			btnBuscarMercado;
	private JPanel panel, panelInfBotoes, panelCentral;
	private BorderLayout layout;
	private FlowLayout layoutInfBotoes;
	private FlowLayout layoutCentral;
	private JLabel lbEmail, lbSenha, lbNomeRazaoSocial, lbSobreNomeFantasia;
	private JTextField tfEmail, tfNomeRazaoSocial, tfSobreNomeFantasia;
	private JPasswordField pSenha;
	private Dimension dmsEdit, dmsLabelDois;
	private Pessoa pessoa;
	private byte janelaAtiva;

	public AdicionarPessoaUI() {

	}

	public void adicionarPessoa() {
		// Instanciar Pessoa
		pessoa = new Pessoa();

		// Criar interface
		createAndShowUI("Adicionar pessoa", pessoa);
		janelaAtiva = 0;
	}

	public void alterarPessoa(Long id) {
		// Instanciar Pessoa
		pessoa = new Pessoa();

		// Intanciar Pessoa Cliente
		PessoaControler pc = new PessoaControler();
		pessoa = pc.buscarPessoaId(id);

		// Criar interface
		janelaAtiva = 1;
		createAndShowUI("Alterar pessoa", pessoa);
	}

	public void excluirPessoa(Long id) {
		pessoa = new Pessoa();
		// Intanciar Pessoa Cliente
		PessoaControler pc = new PessoaControler();
		pessoa = pc.buscarPessoaId(id);
		pc.excluirPessoa(id);

	}

	public void createAndShowUI(String t, Pessoa p) {
		// Propriedades dos componentes da janela
		dmsEdit = new Dimension(620, 27);		
		dmsLabelDois = new Dimension(90, 27);

		// Instanciar Janela
		dmsTela = new Dimension(800, 600);
		adicionarPessoa = new JDialog();
		// Tãtulo da Janela
		adicionarPessoa.setTitle(t);

		// Textos
		lbEmail = new JLabel("E-mail", SwingConstants.RIGHT);
		lbEmail.setPreferredSize(dmsLabelDois);
		lbSenha = new JLabel("Senha", SwingConstants.RIGHT);
		lbSenha.setPreferredSize(dmsLabelDois);
		lbNomeRazaoSocial = new JLabel("Nome", SwingConstants.RIGHT);
		lbNomeRazaoSocial.setPreferredSize(dmsLabelDois);
		lbSobreNomeFantasia = new JLabel("Sobrenome", SwingConstants.RIGHT);
		lbSobreNomeFantasia.setPreferredSize(dmsLabelDois);

		// Editores
		tfEmail = new JTextField();
		tfEmail.setPreferredSize(dmsEdit);
		pSenha = new JPasswordField();
		pSenha.setPreferredSize(dmsEdit);
		tfNomeRazaoSocial = new JTextField();
		tfNomeRazaoSocial.setPreferredSize(dmsEdit);
		tfSobreNomeFantasia = new JTextField();
		tfSobreNomeFantasia.setPreferredSize(dmsEdit);

		// Atribuir campos a pessoa
		if (janelaAtiva == 1) {
			tfNomeRazaoSocial.setText(pessoa.getNome());
			tfSobreNomeFantasia.setText(pessoa.getSobreNome());
			tfEmail.setText(pessoa.getEmail());
			pSenha.setText(pessoa.getSenha());
		}

		// Botoes
		btnConfirmar = new JButton("Confirmar");
		btnCancelar = new JButton("Cancelar");
		btnBuscarCidades = new JButton("Buscar Cidade");
		btnBuscarMercado = new JButton("Seguimento de Mercado");

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
		btnBuscarCidades.setIcon(new ImageIcon(getClass().getResource(
				"/gui/icones/acoes/pesquisa.png")));
		btnBuscarCidades.addActionListener(al);
		btnBuscarMercado.setIcon(new ImageIcon(getClass().getResource(
				"/gui/icones/acoes/pesquisa.png")));
		btnBuscarMercado.addActionListener(al);

		// Propriedades dos paineis
		panel.setLayout(layout);
		panelInfBotoes.setLayout(layoutInfBotoes);
		panelCentral.setLayout(layoutCentral);
		panel.add(panelCentral, BorderLayout.CENTER);
		panel.add(panelInfBotoes, BorderLayout.SOUTH);

		// Adicionar componentes ao painel central
		panelCentral.add(lbNomeRazaoSocial);
		panelCentral.add(tfNomeRazaoSocial);
		panelCentral.add(lbSobreNomeFantasia);
		panelCentral.add(tfSobreNomeFantasia);
		panelCentral.add(lbEmail);
		panelCentral.add(tfEmail);
		panelCentral.add(lbSenha);
		panelCentral.add(pSenha);

		// Adicionar componentes ao painel inferior
		panelInfBotoes.add(btnConfirmar);
		panelInfBotoes.add(btnCancelar);

		// Propriedades da Janela
		adicionarPessoa.setPreferredSize(dmsTela);
		adicionarPessoa.setSize(dmsTela);
		adicionarPessoa.setMaximumSize(dmsTela);
		adicionarPessoa.setMinimumSize(dmsTela);
		adicionarPessoa.add(panel);
		adicionarPessoa.setLocationRelativeTo(null);
		adicionarPessoa.setModal(true);
		adicionarPessoa.setVisible(true);
		adicionarPessoa
				.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

	}

	//
	ActionListener al = new ActionListener() {
		@Override
		@SuppressWarnings("deprecation")
		public void actionPerformed(ActionEvent e) {
			if (e.getSource().equals(btnConfirmar)) {
				PessoaControler pc = new PessoaControler();
				pessoa.setNome(tfNomeRazaoSocial.getText());
				pessoa.setSobreNome(tfSobreNomeFantasia.getText());
				pessoa.setEmail(tfEmail.getText());
				pessoa.setSenha(pSenha.getText());

				if (janelaAtiva == 0) {
					if (pc.adicionarPessoa(pessoa)) {
						javax.swing.JOptionPane.showMessageDialog(
								null,
								"Cadastro realizado com sucesso",
								"Informação",
								0,
								new ImageIcon(getClass().getResource(
										"/gui/icones/acoes/informacao.png")));
						pessoa = null;
						tfNomeRazaoSocial.setText(null);
						tfSobreNomeFantasia.setText(null);
						tfEmail.setText(null);
						pSenha.setText(null);
					}
				}
				if (janelaAtiva == 1) {
					if (pc.alterarPessoa(pessoa)) {
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
					adicionarPessoa.dispose();
				}
			}
		}
	};

}
