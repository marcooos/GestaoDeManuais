package br.com.pcsocial.gestao.visao.grid;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

import br.com.pcsocial.gestao.controle.DocumentoControler;
import br.com.pcsocial.gestao.modelo.Documento;
import br.com.pcsocial.gestao.util.DadosPesquisaGrid;
import br.com.pcsocial.gestao.visao.base.ManterBaseUI;
import br.com.pcsocial.gestao.visao.manter.AdicionarDocumentoUI;

public class ManterDocumentoUI  extends ManterBaseUI {

	private static final long serialVersionUID = 1L;

	private JTable gridDocumentos;
	private DocumentoControler dC;
	private Documento documento;
	private AdicionarDocumentoUI adicionarDocumentoUI;

	@Override
	public void atualizarGrid() {
		dC = new DocumentoControler();
		DadosPesquisaGrid dp = new DadosPesquisaGrid();
		try {
			int tdCol1 = 40;
			int tdColOu = 298;
			gridDocumentos = new JTable((dC.montarTabela(
					dp.textoPesquisa(super.getDadosPesquisa().getText()),
					dp.valorPesquisa(super.getDadosPesquisa().getText()))));
			gridDocumentos.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			TableColumn col1 = gridDocumentos.getColumnModel().getColumn(0);
			TableColumn col2 = gridDocumentos.getColumnModel().getColumn(1);						
			col1.setPreferredWidth(tdCol1);
			col2.setPreferredWidth(tdColOu);			
			gridDocumentos.setFillsViewportHeight(true);
			gridDocumentos.setGridColor(new Color(160, 160, 160));
			super.getScrollPane().setViewportView(gridDocumentos);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	public void setDocumento(Documento documento) {
		this.documento = documento;
	}

	public Documento getDocumento() {
		return documento;
	}

	public void setAdicionarDocumentoUI(AdicionarDocumentoUI adicionarDocumentoUI) {
		this.adicionarDocumentoUI = adicionarDocumentoUI;
	}

	public AdicionarDocumentoUI getAdicionarDocumentoUI() {
		return adicionarDocumentoUI;
	}

	@Override
	public String getTituloJanela() {
		String titulo = "Cadastro de documentos";
		return titulo;
	}

	@Override
	public void adicionarCadastro() {
		AdicionarDocumentoUI am = new AdicionarDocumentoUI();
		am.adicionarDocumento();
	}

	@Override
	public void excluirCadastro() {
		AdicionarDocumentoUI am = new AdicionarDocumentoUI();
		Long retornoSel;
		retornoSel = (Long) gridDocumentos.getValueAt(
				gridDocumentos.getSelectedRow(), 0);
		am.excluirDocumento(retornoSel);
	}

	@Override
	public void modificarCadastro() {
		AdicionarDocumentoUI am = new AdicionarDocumentoUI();
		Long retornoSel;
		try {
			retornoSel = (Long) gridDocumentos.getValueAt(
					gridDocumentos.getSelectedRow(), 0);
			am.alterarDocumento(retornoSel);
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
