package br.com.pcsocial.gestao.controle;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import br.com.pcsocial.gestao.daoImp.PessoaDaoImp;

public class ValidarLoginControle {

	public boolean validarLogin(String email, String senha) {
		PessoaDaoImp pDI = new PessoaDaoImp();
		try {
			if (pDI.validarLogin(email, senha)) {
				return false;
			} else
				return true;
		} catch (Exception e) {			
			System.out.println(e.toString());
			JOptionPane.showMessageDialog(
					null,
					"Não foi possível conectar com o servidor",
					null,
					0,
					new ImageIcon(getClass().getResource(
							"/gui/icones/acoes/cancelar.png")));
			return false;
		}
	}

}
