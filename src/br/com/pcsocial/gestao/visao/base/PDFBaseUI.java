package br.com.pcsocial.gestao.visao.base;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import antlr.CppCodeGenerator;

import com.sun.pdfview.PDFPage;
import com.sun.pdfview.PagePanel;

public class PDFBaseUI extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panel, panelSuperior, panelSupBotoes, panelInferior;
	private PagePanel pagePanel;
	private BorderLayout layout;
	private GridLayout layoutSup;
	private FlowLayout layoutSupBotoes;
	private FlowLayout layoutInf;
	private JButton cbConfirmar;
	private JButton cbCancelar;
	private JButton cbProximaPagina;
	private JButton cbPaginaAnterior;
	private JButton cbPrimeiraPagina;
	private JButton cbUltimaPagina;

	private JTextField tfNumPagina;
	private JScrollPane scrollPane;

	private JDialog buscarBase;
	

	public PDFBaseUI() {

	}

	public Object createAndShowUI(PDFPage page) {
		// Instanciar Janela
		Dimension dmsTela = new Dimension(800, 600);
		buscarBase = new JDialog();
		buscarBase.setTitle(getTitulo());
		criaObjeto();

		// Intanciar componentes
		panel = new JPanel();
		panelSuperior = new JPanel();
		panelInferior = new JPanel();
		panelSupBotoes = new JPanel();
		layout = new BorderLayout();
		layoutSup = new GridLayout(2, 1);
		layoutSupBotoes = new FlowLayout(FlowLayout.LEFT);
		layoutInf = new FlowLayout(FlowLayout.LEFT);
		cbConfirmar = new JButton("Confirmar");
		cbCancelar = new JButton("Cancelar");
		cbProximaPagina = new JButton("Próxima");
		cbPaginaAnterior = new JButton("Anterior");
		cbPrimeiraPagina = new JButton("Primeira");
		cbUltimaPagina = new JButton("Ultima");
		//cbPesquisa = new JButton("Pesquisar");
		//dadosPesquisa = new JTextField();
		tfNumPagina = new JTextField();
		scrollPane = new JScrollPane();
		pagePanel = new PagePanel();				

		// Propriedades botoes
		cbConfirmar.setIcon(new ImageIcon(
				getClass().getResource("/gui/icones/acoes/confirmar.png")));
		cbConfirmar.addActionListener(al);

		cbCancelar.setIcon(new ImageIcon(
				getClass().getResource("/gui/icones/acoes/cancelar.png")));
		cbCancelar.addActionListener(al);

		cbPrimeiraPagina.setIcon(new ImageIcon(
				getClass().getResource("/gui/icones/acoes/confirmar.png")));
		cbPrimeiraPagina.addActionListener(al);
		
		cbProximaPagina.setIcon(new ImageIcon(
				getClass().getResource("/gui/icones/acoes/confirmar.png")));
		cbProximaPagina.addActionListener(al);
		
		cbPaginaAnterior.setIcon(new ImageIcon(
				getClass().getResource("/gui/icones/acoes/confirmar.png")));
		cbPaginaAnterior.addActionListener(al);
		
		cbUltimaPagina.setIcon(new ImageIcon(
				getClass().getResource("/gui/icones/acoes/confirmar.png")));
		cbUltimaPagina.addActionListener(al);

		panel.setLayout(layout);
		panelSuperior.setLayout(layoutSup);
		panelSupBotoes.setLayout(layoutSupBotoes);
		panelInferior.setLayout(layoutInf);

		panelSupBotoes.add(cbPrimeiraPagina);
		panelSupBotoes.add(cbPaginaAnterior);
		panelSupBotoes.add(tfNumPagina);
		panelSupBotoes.add(cbProximaPagina);
		panelSupBotoes.add(cbUltimaPagina);

		//panelSuperior.add(dadosPesquisa);
		panelSuperior.add(panelSupBotoes);

		panelInferior.add(cbConfirmar);
		panelInferior.add(cbCancelar);
		
		//scrollPane.add(pagePanel);

		panel.add(pagePanel, BorderLayout.CENTER);
		panel.add(panelSuperior, BorderLayout.NORTH);
		panel.add(panelInferior, BorderLayout.SOUTH);
		panel.setVisible(true);

		// Propriedades da Janela
		buscarBase.setPreferredSize(dmsTela);
		buscarBase.setSize(dmsTela);
		buscarBase.setMaximumSize(dmsTela);
		buscarBase.setMinimumSize(dmsTela);
		buscarBase.add(panel);
		buscarBase.setLocationRelativeTo(null);
		buscarBase.setModal(true);
		buscarBase.setVisible(true);
		buscarBase.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		//buscarBase.pack();
		
		pagePanel.showPage(page);
		System.out.println("2 / / /"+ page);

		return returnaObjeto();
	}
	
	public void atualizar(PDFPage pdf) {
		pagePanel.showPage(pdf);		
	}
	
	public void abrirArquivo() throws IOException {
		// TODO
	}

	public void abrirArquivo(Long id) {
		// TODO
	}

	public JScrollPane getScrollPane() {
		return scrollPane;
	}

	public void setScrollPane(JScrollPane scrollPane) {
		this.scrollPane = scrollPane;
	}

	public JDialog getBuscarBase() {
		return buscarBase;
	}

	public void setBuscarBase(JDialog buscarBase) {
		this.buscarBase = buscarBase;
	}

	ActionListener al = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource().equals(cbConfirmar)) {
				confirmarSelecao();
			}
			if (e.getSource().equals(cbCancelar)) {
				cancelarSelecao();
			}
			if (e.getSource().equals(cbProximaPagina)) {
				//atualizarGrid();
			}
			if (e.getSource().equals(cbPaginaAnterior)) {
				//atualizarGrid();
			}
		}
	};

	public void confirmarSelecao() {
		// TODO
	}
	
	public void proximaPagina() {
		// TODO
	}
	
	public void paginaAnterior() {
		// TODO
	}

	public void cancelarSelecao() {
		// TODO
	}

	public Object returnaObjeto() {
		return null;
	}

	public void criaObjeto() {
		// TODO
	}

	public String getTitulo() {
		return null;
	}
}