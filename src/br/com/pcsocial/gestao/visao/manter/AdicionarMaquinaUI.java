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

import br.com.pcsocial.gestao.controle.MaquinaControler;
import br.com.pcsocial.gestao.modelo.Maquina;

public class AdicionarMaquinaUI  extends JDialog {

	private static final long serialVersionUID = 1L;
	private JDialog adicionarMaquina;
	private Dimension dmsTela;
	private JButton btnConfirmar, btnCancelar, btnBuscarCidades,
			btnBuscarMercado;
	private JPanel panel, panelInfBotoes, panelCentral;
	private BorderLayout layout;
	private FlowLayout layoutInfBotoes;
	private FlowLayout layoutCentral;
	private JLabel lbNome, lbModelo;
	private JTextField tfNome,tfModelo;
	private Dimension dmsEdit, dmsLabelDois;
	private Maquina maquina;
	private byte janelaAtiva;

	public AdicionarMaquinaUI() {

	}

	public void adicionarMaquina() {
		// Instanciar Maquina
		maquina = new Maquina();

		// Criar interface
		createAndShowUI("Adicionar maquina", maquina);
		janelaAtiva = 0;
	}

	public void alterarMaquina(Long id) {
		// Instanciar Maquina
		maquina = new Maquina();

		// Intanciar Maquina Cliente
		MaquinaControler pc = new MaquinaControler();
		maquina = pc.buscarMaquinaId(id);

		// Criar interface
		janelaAtiva = 1;
		createAndShowUI("Alterar maquina", maquina);
	}

	public void excluirMaquina(Long id) {
		maquina = new Maquina();
		// Intanciar Maquina Cliente
		MaquinaControler pc = new MaquinaControler();
		maquina = pc.buscarMaquinaId(id);
		pc.excluirMaquina(id);

	}

	public void createAndShowUI(String t, Maquina p) {
		// Propriedades dos componentes da janela
		dmsEdit = new Dimension(620, 27);		
		dmsLabelDois = new Dimension(90, 27);

		// Instanciar Janela
		dmsTela = new Dimension(800, 600);
		adicionarMaquina = new JDialog();
		// Tãtulo da Janela
		adicionarMaquina.setTitle(t);

		// Textos
		lbNome = new JLabel("Nome", SwingConstants.RIGHT);
		lbNome.setPreferredSize(dmsLabelDois);
		lbModelo = new JLabel("Modelo", SwingConstants.RIGHT);
		lbModelo.setPreferredSize(dmsLabelDois);		

		// Editores
		tfNome = new JTextField();
		tfNome.setPreferredSize(dmsEdit);
		tfModelo = new JTextField();
		tfModelo.setPreferredSize(dmsEdit);		

		// Atribuir campos a maquina
		if (janelaAtiva == 1) {			
			tfNome.setText(maquina.getNome());
			tfModelo.setText(maquina.getModelo());
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
		panelCentral.add(lbNome);
		panelCentral.add(tfNome);
		panelCentral.add(lbModelo);
		panelCentral.add(tfModelo);

		// Adicionar componentes ao painel inferior
		panelInfBotoes.add(btnConfirmar);
		panelInfBotoes.add(btnCancelar);

		// Propriedades da Janela
		adicionarMaquina.setPreferredSize(dmsTela);
		adicionarMaquina.setSize(dmsTela);
		adicionarMaquina.setMaximumSize(dmsTela);
		adicionarMaquina.setMinimumSize(dmsTela);
		adicionarMaquina.add(panel);
		adicionarMaquina.setLocationRelativeTo(null);
		adicionarMaquina.setModal(true);
		adicionarMaquina.setVisible(true);
		adicionarMaquina
				.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

	}

	//
	ActionListener al = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource().equals(btnConfirmar)) {
				MaquinaControler pc = new MaquinaControler();
				maquina.setNome(tfNome.getText());
				maquina.setModelo(tfModelo.getText());				

				if (janelaAtiva == 0) {
					if (pc.adicionarMaquina(maquina)) {
						javax.swing.JOptionPane.showMessageDialog(
								null,
								"Cadastro realizado com sucesso",
								"Informação",
								0,
								new ImageIcon(getClass().getResource(
										"/gui/icones/acoes/informacao.png")));
						maquina = null;						
						tfNome.setText(null);
						tfModelo.setText(null);
					}
				}
				if (janelaAtiva == 1) {
					if (pc.alterarMaquina(maquina)) {
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
					adicionarMaquina.dispose();
				}
			}
		}
	};
}
