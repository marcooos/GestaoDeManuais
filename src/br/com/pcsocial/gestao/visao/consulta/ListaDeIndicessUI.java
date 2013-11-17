package br.com.pcsocial.gestao.visao.consulta;

import java.awt.Color;

import javax.swing.JTable;
import javax.swing.table.TableColumn;

import br.com.pcsocial.gestao.controle.IndicesControler;
import br.com.pcsocial.gestao.modelo.Indices;
import br.com.pcsocial.gestao.util.DadosPesquisaGrid;
import br.com.pcsocial.gestao.visao.base.ListaBaseUI;

public class ListaDeIndicessUI extends ListaBaseUI {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JTable gridIndices;

	private IndicesControler iC;
	private Indices indices;

	@Override
	public void atualizarGrid() {
		iC = new IndicesControler();
		DadosPesquisaGrid dp = new DadosPesquisaGrid();
		try {
			int tiCol1 = 40;
			int tiColOu = 298;
			gridIndices = new JTable((iC.montarTabela(
					dp.textoPesquisa(super.getDadosPesquisa().getText()),
					dp.valorPesquisa(super.getDadosPesquisa().getText()))));
			gridIndices.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			TableColumn col1 = gridIndices.getColumnModel().getColumn(0);
			TableColumn col2 = gridIndices.getColumnModel().getColumn(1);
			TableColumn col3 = gridIndices.getColumnModel().getColumn(2);
			col1.setPreferredWidth(tiCol1);
			col2.setPreferredWidth(tiColOu);
			col3.setPreferredWidth(tiColOu);
			gridIndices.setFillsViewportHeight(true);
			gridIndices.setGridColor(new Color(160, 160, 160));
			super.getScrollPane().setViewportView(gridIndices);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public void confirmarSelecao() {
		IndicesControler cc = new IndicesControler();
		indices = cc.buscarIndicesId((Long) gridIndices.getValueAt(
				gridIndices.getSelectedRow(), 0));
		super.getBuscarBase().dispose();
	}

	@Override
	public void cancelarSelecao() {
		super.getBuscarBase().dispose();
	}

	@Override
	public Object returnaObjeto() {
		return indices;
	}

	@Override
	public void criaObjeto() {
		indices = new Indices();
	}

	@Override
	public String getTitulo() {
		return "Buscar indices";
	}

}
