package br.com.pcsocial.gestao.visao.grid;

import java.awt.Color;


import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

import br.com.pcsocial.gestao.util.DadosPesquisaGrid;
import br.com.pcsocial.gestao.visao.base.ManterBaseUI;
import br.com.pcsocial.gestao.visao.manter.AdicionarPessoaUI;
import br.com.pcsocial.gestao.controle.PessoaControler;
import br.com.pcsocial.gestao.modelo.Pessoa;

public class ManterPessoaUI extends ManterBaseUI {

	private static final long serialVersionUID = 1L;

	private JTable gridPessoas;
	private PessoaControler pC;
	private Pessoa pessoa;
	private AdicionarPessoaUI adicionarPessoaUI;

	@Override
	public void atualizarGrid() {
		pC = new PessoaControler();
		DadosPesquisaGrid dp = new DadosPesquisaGrid();
		try {
			int tmCol1 = 40;
			int tmColOu = 298;
			gridPessoas = new JTable((pC.montarTabela(
					dp.textoPesquisa(super.getDadosPesquisa().getText()),
					dp.valorPesquisa(super.getDadosPesquisa().getText()))));
			gridPessoas.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			TableColumn col1 = gridPessoas.getColumnModel().getColumn(0);
			TableColumn col2 = gridPessoas.getColumnModel().getColumn(1);
			TableColumn col3 = gridPessoas.getColumnModel().getColumn(2);
			TableColumn col4 = gridPessoas.getColumnModel().getColumn(3);			
			col1.setPreferredWidth(tmCol1);
			col2.setPreferredWidth(tmColOu);
			col3.setPreferredWidth(tmColOu);
			col4.setPreferredWidth(tmColOu);			
			gridPessoas.setFillsViewportHeight(true);
			gridPessoas.setGridColor(new Color(160, 160, 160));
			super.getScrollPane().setViewportView(gridPessoas);
		} catch (Exception e1) {			
			e1.printStackTrace();
		}
	}
	
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setAdicionarPessoaUI(AdicionarPessoaUI adicionarPessoaUI) {
		this.adicionarPessoaUI = adicionarPessoaUI;
	}

	public AdicionarPessoaUI getAdicionarPessoaUI() {
		return adicionarPessoaUI;
	}

	@Override
	public String getTituloJanela() {
		String titulo = "Cadastro de pessoas";
		return titulo;
	}

	@Override
	public void adicionarCadastro() {
		AdicionarPessoaUI am = new AdicionarPessoaUI();
		am.adicionarPessoa();
	}

	@Override
	public void excluirCadastro() {
		AdicionarPessoaUI am = new AdicionarPessoaUI();
		Long retornoSel;
		retornoSel = (Long) gridPessoas.getValueAt(gridPessoas.getSelectedRow(),
				0);
		am.excluirPessoa(retornoSel);
	}

	@Override
	public void modificarCadastro() {
		AdicionarPessoaUI am = new AdicionarPessoaUI();
		Long retornoSel;
		try {
			retornoSel = (Long) gridPessoas.getValueAt(
					gridPessoas.getSelectedRow(), 0);
			am.alterarPessoa(retornoSel);
		} catch (NullPointerException e1) {
			javax.swing.JOptionPane.showMessageDialog(null,
					"Selecione um registro para edição", "Informação", 0,
					new ImageIcon(getClass().getResource("/gui/icones/acoes/informacao.png")));
		}
	}

}
