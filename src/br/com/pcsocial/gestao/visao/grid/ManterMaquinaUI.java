package br.com.pcsocial.gestao.visao.grid;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

import br.com.pcsocial.gestao.controle.MaquinaControler;
import br.com.pcsocial.gestao.modelo.Maquina;
import br.com.pcsocial.gestao.util.DadosPesquisaGrid;
import br.com.pcsocial.gestao.visao.base.ManterBaseUI;
import br.com.pcsocial.gestao.visao.manter.AdicionarMaquinaUI;

public class ManterMaquinaUI extends ManterBaseUI {

	private static final long serialVersionUID = 1L;

	private JTable gridMaquinas;
	private MaquinaControler mC;
	private Maquina maquina;
	private AdicionarMaquinaUI adicionarMaquinaUI;

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

	public void setMaquina(Maquina maquina) {
		this.maquina = maquina;
	}

	public Maquina getMaquina() {
		return maquina;
	}

	public void setAdicionarMaquinaUI(AdicionarMaquinaUI adicionarMaquinaUI) {
		this.adicionarMaquinaUI = adicionarMaquinaUI;
	}

	public AdicionarMaquinaUI getAdicionarMaquinaUI() {
		return adicionarMaquinaUI;
	}

	@Override
	public String getTituloJanela() {
		String titulo = "Cadastro de maquinas";
		return titulo;
	}

	@Override
	public void adicionarCadastro() {
		AdicionarMaquinaUI am = new AdicionarMaquinaUI();
		am.adicionarMaquina();
	}

	@Override
	public void excluirCadastro() {
		AdicionarMaquinaUI am = new AdicionarMaquinaUI();
		Long retornoSel;
		retornoSel = (Long) gridMaquinas.getValueAt(
				gridMaquinas.getSelectedRow(), 0);
		am.excluirMaquina(retornoSel);
	}

	@Override
	public void modificarCadastro() {
		AdicionarMaquinaUI am = new AdicionarMaquinaUI();
		Long retornoSel;
		try {
			retornoSel = (Long) gridMaquinas.getValueAt(
					gridMaquinas.getSelectedRow(), 0);
			am.alterarMaquina(retornoSel);
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
