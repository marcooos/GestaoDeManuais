package br.com.pcsocial.gestao.visao.consulta;

import java.awt.Color;

import javax.swing.JTable;
import javax.swing.table.TableColumn;

import br.com.pcsocial.gestao.controle.DocumentoControler;
import br.com.pcsocial.gestao.modelo.Documento;
import br.com.pcsocial.gestao.util.DadosPesquisaGrid;
import br.com.pcsocial.gestao.visao.base.ListaBaseUI;

public class ListaDeDocumentosUI  extends ListaBaseUI {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JTable gridDocumentos;

	private DocumentoControler dC;
	private Documento documento;

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
			TableColumn col3 = gridDocumentos.getColumnModel().getColumn(2);
			col1.setPreferredWidth(tdCol1);
			col2.setPreferredWidth(tdColOu);
			col3.setPreferredWidth(tdColOu);
			gridDocumentos.setFillsViewportHeight(true);
			gridDocumentos.setGridColor(new Color(160, 160, 160));
			super.getScrollPane().setViewportView(gridDocumentos);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public void confirmarSelecao() {
		DocumentoControler cc = new DocumentoControler();
		documento = cc.buscarDocumentoId((Long) gridDocumentos.getValueAt(gridDocumentos.getSelectedRow(), 0));
		super.getBuscarBase().dispose();
	}

	@Override
	public void cancelarSelecao() {
		super.getBuscarBase().dispose();
	}

	@Override
	public Object returnaObjeto() {
		return documento;
	}

	@Override
	public void criaObjeto() {
		documento = new Documento();
	}

	@Override
	public String getTitulo() {
		return "Buscar documentos";
	}

}