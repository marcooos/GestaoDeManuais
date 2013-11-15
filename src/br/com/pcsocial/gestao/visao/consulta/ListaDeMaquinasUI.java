package br.com.pcsocial.gestao.visao.consulta;

import java.awt.Color;

import javax.swing.JTable;
import javax.swing.table.TableColumn;

import br.com.pcsocial.gestao.controle.MaquinaControler;
import br.com.pcsocial.gestao.util.DadosPesquisaGrid;
import br.com.pcsocial.gestao.visao.base.ListaBaseUI;
import br.com.pcsocial.gestao.modelo.Maquina;

public class ListaDeMaquinasUI extends ListaBaseUI {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JTable gridMaquinas;

	private MaquinaControler mC;
	private Maquina maquina;

	@Override
	public void atualizarGrid() {
		mC = new MaquinaControler();
		DadosPesquisaGrid dp = new DadosPesquisaGrid();
		try {
			int tmCol1 = 40;
			int tmColOu = 298;
			gridMaquinas = new JTable((mC.montarTabela(
					dp.textoPesquisa(super.getDadosPesquisa().getText()),
					dp.valorPesquisa(super.getDadosPesquisa().getText()))));
			gridMaquinas.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			TableColumn col1 = gridMaquinas.getColumnModel().getColumn(0);
			TableColumn col2 = gridMaquinas.getColumnModel().getColumn(1);
			TableColumn col3 = gridMaquinas.getColumnModel().getColumn(2);
			col1.setPreferredWidth(tmCol1);
			col2.setPreferredWidth(tmColOu);
			col3.setPreferredWidth(tmColOu);
			gridMaquinas.setFillsViewportHeight(true);
			gridMaquinas.setGridColor(new Color(160, 160, 160));
			super.getScrollPane().setViewportView(gridMaquinas);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public void confirmarSelecao() {
		MaquinaControler cc = new MaquinaControler();
		maquina = cc.buscarMaquinaId((Long) gridMaquinas.getValueAt(gridMaquinas.getSelectedRow(), 0));
		super.getBuscarBase().dispose();
	}

	@Override
	public void cancelarSelecao() {
		super.getBuscarBase().dispose();
	}

	@Override
	public Object returnaObjeto() {
		return maquina;
	}

	@Override
	public void criaObjeto() {
		maquina = new Maquina();
	}

	@Override
	public String getTitulo() {
		return "Buscar maquinas";
	}

}
