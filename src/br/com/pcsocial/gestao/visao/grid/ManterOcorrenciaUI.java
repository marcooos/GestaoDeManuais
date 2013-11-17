package br.com.pcsocial.gestao.visao.grid;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

import br.com.pcsocial.gestao.controle.OcorrenciaControler;
import br.com.pcsocial.gestao.modelo.Ocorrencia;
import br.com.pcsocial.gestao.util.DadosPesquisaGrid;
import br.com.pcsocial.gestao.visao.base.ManterBaseUI;
import br.com.pcsocial.gestao.visao.manter.AdicionarOcorrenciaUI;

public class ManterOcorrenciaUI extends ManterBaseUI {

	private static final long serialVersionUID = 1L;

	private JTable gridOCorrencias;
	private OcorrenciaControler oC;
	private Ocorrencia ocorrencia;
	private AdicionarOcorrenciaUI adicionarOcorrenciaUI;

	@Override
	public void atualizarGrid() {
		oC = new OcorrenciaControler();
		DadosPesquisaGrid dp = new DadosPesquisaGrid();
		try {
			int toCol1 = 40;
			int toColOu = 298;
			gridOCorrencias = new JTable((oC.montarTabela(
					dp.textoPesquisa(super.getDadosPesquisa().getText()),
					dp.valorPesquisa(super.getDadosPesquisa().getText()))));
			gridOCorrencias.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			TableColumn col1 = gridOCorrencias.getColumnModel().getColumn(0);
			TableColumn col2 = gridOCorrencias.getColumnModel().getColumn(1);
			TableColumn col3 = gridOCorrencias.getColumnModel().getColumn(2);
			col1.setPreferredWidth(toCol1);
			col2.setPreferredWidth(toColOu);
			col3.setPreferredWidth(toColOu);
			gridOCorrencias.setFillsViewportHeight(true);
			gridOCorrencias.setGridColor(new Color(160, 160, 160));
			super.getScrollPane().setViewportView(gridOCorrencias);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	public void setOcorrencia(Ocorrencia ocorrencia) {
		this.ocorrencia = ocorrencia;
	}

	public Ocorrencia getOcorrencia() {
		return ocorrencia;
	}

	public void setAdicionarOcorrenciaUI(AdicionarOcorrenciaUI adicionarOcorrenciaUI) {
		this.adicionarOcorrenciaUI = adicionarOcorrenciaUI;
	}

	public AdicionarOcorrenciaUI getAdicionarOcorrenciaUI() {
		return adicionarOcorrenciaUI;
	}

	@Override
	public String getTituloJanela() {
		String titulo = "Cadastro de ocorrencias";
		return titulo;
	}

	@Override
	public void adicionarCadastro() {
		AdicionarOcorrenciaUI am = new AdicionarOcorrenciaUI();
		am.adicionarOcorrencia();
	}

	@Override
	public void excluirCadastro() {
		AdicionarOcorrenciaUI am = new AdicionarOcorrenciaUI();
		Long retornoSel;
		retornoSel = (Long) gridOCorrencias.getValueAt(
				gridOCorrencias.getSelectedRow(), 0);
		am.excluirOcorrencia(retornoSel);
	}

	@Override
	public void modificarCadastro() {
		AdicionarOcorrenciaUI am = new AdicionarOcorrenciaUI();
		Long retornoSel;
		try {
			retornoSel = (Long) gridOCorrencias.getValueAt(
					gridOCorrencias.getSelectedRow(), 0);
			am.alterarOcorrencia(retornoSel);
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

}
