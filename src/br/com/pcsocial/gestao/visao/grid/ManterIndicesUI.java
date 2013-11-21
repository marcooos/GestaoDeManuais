package br.com.pcsocial.gestao.visao.grid;

import java.awt.Color;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

import br.com.pcsocial.gestao.controle.IndicesControler;
import br.com.pcsocial.gestao.modelo.Indices;
import br.com.pcsocial.gestao.util.DadosPesquisaGrid;
import br.com.pcsocial.gestao.visao.base.ManterBaseUI;
import br.com.pcsocial.gestao.visao.consulta.PDFViewer;
import br.com.pcsocial.gestao.visao.consulta.VisualizarPDFUI;
import br.com.pcsocial.gestao.visao.manter.AdicionarIndicesUI;

public class ManterIndicesUI extends ManterBaseUI{

	private static final long serialVersionUID = 1L;

	private JTable gridIndices;
	private IndicesControler mC;
	private Indices indices;
	private AdicionarIndicesUI adicionarIndicesUI;
	

	@Override
	public void atualizarGrid() {
		mC = new IndicesControler();
		DadosPesquisaGrid dp = new DadosPesquisaGrid();
		try {
			int tmCol1 = 40;
			int tmColOu = 298;
			gridIndices = new JTable((mC.montarTabela(
					dp.textoPesquisa(super.getDadosPesquisa().getText()),
					dp.valorPesquisa(super.getDadosPesquisa().getText()))));
			gridIndices.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			TableColumn col1 = gridIndices.getColumnModel().getColumn(0);
			TableColumn col2 = gridIndices.getColumnModel().getColumn(1);
			TableColumn col3 = gridIndices.getColumnModel().getColumn(2);
			TableColumn col4 = gridIndices.getColumnModel().getColumn(3);
			col1.setPreferredWidth(tmCol1);
			col2.setPreferredWidth(tmColOu);
			col3.setPreferredWidth(tmColOu);
			col4.setPreferredWidth(tmColOu);
			gridIndices.setFillsViewportHeight(true);
			gridIndices.setGridColor(new Color(160, 160, 160));
			super.getScrollPane().setViewportView(gridIndices);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	public void setIndices(Indices indices) {
		this.indices = indices;
	}

	public Indices getIndices() {
		return indices;
	}

	public void setAdicionarIndicesUI(AdicionarIndicesUI adicionarIndicesUI) {
		this.adicionarIndicesUI = adicionarIndicesUI;
	}

	public AdicionarIndicesUI getAdicionarIndicesUI() {
		return adicionarIndicesUI;
	}

	@Override
	public String getTituloJanela() {
		String titulo = "Cadastro de indicess";
		return titulo;
	}

	@Override
	public void adicionarCadastro() {
		AdicionarIndicesUI am = new AdicionarIndicesUI();
		am.adicionarIndices();
	}

	@Override
	public void excluirCadastro() {
		AdicionarIndicesUI am = new AdicionarIndicesUI();
		Long retornoSel;
		retornoSel = (Long) gridIndices.getValueAt(
				gridIndices.getSelectedRow(), 0);
		am.excluirIndices(retornoSel);
	}

	@Override
	public void modificarCadastro() {
		AdicionarIndicesUI am = new AdicionarIndicesUI();
		Long retornoSel;
		try {
			retornoSel = (Long) gridIndices.getValueAt(
					gridIndices.getSelectedRow(), 0);
			am.alterarIndices(retornoSel);
		} catch (NullPointerException e1) {
			javax.swing.JOptionPane.showMessageDialog(
					null,
					"Selecione um registro para edição",
					"Informação",
					0,
					new ImageIcon(getClass().getResource(
							"/gui/icones/acoes/informacao.png")));
		}
	}
	
	public void abrirDocumento() {
		PDFViewer am = new PDFViewer(closable);
		File file = new File("OS_Arg_2010.pdf");
			am.iniciarInterface(file,3);
		/*
		Long retornoSel;
		try {
			retornoSel = (Long) gridIndices.getValueAt(
					gridIndices.getSelectedRow(), 0);
			am.abrirArquivo(retornoSel);
		} catch (NullPointerException e1) {
			javax.swing.JOptionPane.showMessageDialog(
					null,
					"Selecione um registro para edição",
					"Informação",
					0,
					new ImageIcon(getClass().getResource(
							"/gui/icones/acoes/informacao.png")));
		}*/
	}

}
