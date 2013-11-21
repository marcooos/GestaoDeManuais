package br.com.pcsocial.gestao.visao;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
//import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.List;

import javax.swing.ImageIcon;
import br.com.pcsocial.gestao.util.DecoratedDesktopPane;
import br.com.pcsocial.gestao.visao.grid.ManterDocumentoUI;
import br.com.pcsocial.gestao.visao.grid.ManterIndicesUI;
import br.com.pcsocial.gestao.visao.grid.ManterMaquinaUI;
import br.com.pcsocial.gestao.visao.grid.ManterOcorrenciaUI;
import br.com.pcsocial.gestao.visao.grid.ManterPessoaUI;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import org.pushingpixels.flamingo.api.common.JCommandButton;
import org.pushingpixels.flamingo.api.common.icon.ImageWrapperResizableIcon;
import org.pushingpixels.flamingo.api.common.icon.ResizableIcon;
import org.pushingpixels.flamingo.api.ribbon.JRibbon;
import org.pushingpixels.flamingo.api.ribbon.JRibbonBand;
import org.pushingpixels.flamingo.api.ribbon.RibbonElementPriority;
import org.pushingpixels.flamingo.api.ribbon.RibbonTask;
import org.pushingpixels.flamingo.api.ribbon.resize.CoreRibbonResizePolicies;
import org.pushingpixels.flamingo.api.ribbon.resize.IconRibbonBandResizePolicy;
import org.pushingpixels.flamingo.api.ribbon.resize.RibbonBandResizePolicy;


public class PrincipalClienteRibbonUI extends JFrame {
	private static final long serialVersionUID = 1L;
	private DecoratedDesktopPane desktop;
	private JCommandButton cbOcorrencia, cbDocumentos, cbPessoa, cbPermissoes,
			cbMaquinas,cbIndice, cbBuscarDocumentos, cbTemp; 
			/*cbClassesTar, cbRestricoesTar, cbCanais, cbDemandas,
			cbOrigem, cbIndice, cbOcupSeg, cbPricing,
			cbOcupRest, cbOver, cbRentSeg, cbConsSeg, cbEstadiaMed, cdPreviDem;*/
	private JRibbon menuRibbon;
	private JRibbonBand menuCadastroDocumento, menuCadastroEquipamentos,
			menuCadastroPessoas, menuAnalise, 
			//menuAnalise, menuParametros,
			menuAjuda;
	private RibbonTask tsCadastro, tsAnalise; 
			//tsAnalise, tsParametros,
			//tsAjuda;
	private JMenuBar menu;
	private JMenuItem menuItem;

	public PrincipalClienteRibbonUI() {
		super("Gestão de receitas");

		// inicializar componentes
		menu = new JMenuBar();
		menuItem = new JMenuItem();

		JMenu fileMenuSistema = new JMenu("Sistema");
		fileMenuSistema.setMnemonic(KeyEvent.VK_S);
		fileMenuSistema.setIcon(new ImageIcon(getClass().getResource(
				"/gui/icones/menu/parametros.png")));

		menu.add(fileMenuSistema);

		menuItem = new JMenuItem("Sair");
		menuItem.setMnemonic(KeyEvent.VK_S);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
				ActionEvent.ALT_MASK));
		menuItem.setActionCommand("sair");
		menuItem.setIcon(new ImageIcon(getClass().getResource("/gui/icones/menu/sair.png")));
		menuItem.addActionListener(al);
		fileMenuSistema.add(menuItem);

		this.setJMenuBar(menu);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Abrir Miximizado
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension screenMax = new Dimension(screenSize.width, screenSize.height);

		this.setPreferredSize(screenMax);
		desktop = new DecoratedDesktopPane("/gui/fundo/fundo.png");
		this.add(criarRibbonMenu(), BorderLayout.NORTH);
		this.add(desktop, BorderLayout.CENTER);
		this.pack();
		this.setSize(screenMax);
		this.setLocationRelativeTo(null);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		desktop.setBackground(Color.WHITE);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public JRibbon criarRibbonMenu() {

		// Criar Abas
		menuRibbon = new JRibbon();
		menuCadastroPessoas = new JRibbonBand("Pessoas", null);
		menuCadastroEquipamentos = new JRibbonBand("Equipamentos", null);
		menuCadastroDocumento = new JRibbonBand("Documentos", null);				
		//menuAnalise = new JRibbonBand("Busca de documentos", null);
		menuAnalise = new JRibbonBand("Falhas", null);
		//menuParametros = new JRibbonBand("", null);
		menuAjuda = new JRibbonBand("", null);
		

		// Botoes menu cadastro
		cbOcorrencia = new JCommandButton(
				"Ocorrências",
				getResizableIconFromResource("/gui/icones/barras/mercado.png"));
		cbDocumentos = new JCommandButton(
				"Documentos",
				getResizableIconFromResource("/gui/icones/barras/demandas.png"));
		cbPessoa = new JCommandButton(
				"Usuários",
				getResizableIconFromResource("/gui/icones/barras/pessoas.png"));
		cbPermissoes = new JCommandButton(
				"Permissões",
				getResizableIconFromResource("/gui/icones/barras/restricoes.png"));
		cbMaquinas = new JCommandButton(
				"Maquinas",
				getResizableIconFromResource("/gui/icones/barras/over.png"));		
		
		cbIndice = new JCommandButton(
				"Indices",
				getResizableIconFromResource("/gui/icones/barras/rentabilidade.png"));
		
		// Temp
		cbTemp = new JCommandButton("Temp");

		// Botoes do menu Análise
		cbBuscarDocumentos = new JCommandButton(
				"Buscar documentos",
				getResizableIconFromResource("/gui/icones/barras/ocupSeg.png"));	
		
		// Eventos botoes
		cbOcorrencia.addActionListener(al);
		cbDocumentos.addActionListener(al);
		cbPessoa.addActionListener(al);
		cbPermissoes.addActionListener(al);
		cbMaquinas.addActionListener(al);		
		cbIndice.addActionListener(al);		
		cbBuscarDocumentos.addActionListener(al);		

		// Adicionar botoes ao menu cadastro
		menuCadastroDocumento.addCommandButton(cbOcorrencia, RibbonElementPriority.TOP);
		menuCadastroDocumento.addCommandButton(cbDocumentos,RibbonElementPriority.TOP);
		menuCadastroDocumento.addCommandButton(cbIndice,RibbonElementPriority.TOP);
		
		// Adicionar botoes ao menu pessoas
		menuCadastroPessoas.addCommandButton(cbPessoa, RibbonElementPriority.TOP);
		menuCadastroPessoas.addCommandButton(cbPermissoes, RibbonElementPriority.TOP);

		// Adicionar botoes ao menu equipamentos
		menuCadastroEquipamentos.addCommandButton(cbMaquinas, RibbonElementPriority.TOP);
		menuCadastroEquipamentos.addCommandButton(cbTemp, RibbonElementPriority.TOP);
		
		//Adicionar botoes ao menu análise
		menuAnalise.addCommandButton(cbBuscarDocumentos, RibbonElementPriority.TOP);

		
		
		//menuAnalise.addCommandButton(cbTemp, RibbonElementPriority.TOP);
		//menuParametros.addCommandButton(cbTemp, RibbonElementPriority.TOP);
		menuAjuda.addCommandButton(cbTemp, RibbonElementPriority.TOP);

		// Definição de icones
		menuCadastroDocumento.setResizePolicies(Arrays
				.<RibbonBandResizePolicy> asList(
						new CoreRibbonResizePolicies.Mid2Mid(menuCadastroDocumento
								.getControlPanel()),
						new IconRibbonBandResizePolicy(menuCadastroDocumento
								.getControlPanel())));

		menuCadastroPessoas.setResizePolicies(Arrays
				.<RibbonBandResizePolicy> asList(
						new CoreRibbonResizePolicies.Mid2Mid(
								menuCadastroPessoas.getControlPanel()),
						new IconRibbonBandResizePolicy(menuCadastroPessoas
								.getControlPanel())));

		menuCadastroEquipamentos.setResizePolicies(Arrays
				.<RibbonBandResizePolicy> asList(
						new CoreRibbonResizePolicies.Mid2Mid(
								menuCadastroEquipamentos.getControlPanel()),
						new IconRibbonBandResizePolicy(menuCadastroEquipamentos
								.getControlPanel())));

		menuAnalise.setResizePolicies(Arrays
				.<RibbonBandResizePolicy> asList(
						new CoreRibbonResizePolicies.Mid2Mid(menuAnalise
								.getControlPanel()),
						new IconRibbonBandResizePolicy(menuAnalise
								.getControlPanel())));
		
		menuAjuda.setResizePolicies((List) Arrays.asList(
				new CoreRibbonResizePolicies.Mid2Mid(menuAjuda
						.getControlPanel()), new IconRibbonBandResizePolicy(
						menuAjuda.getControlPanel())));

		tsCadastro = new RibbonTask("Cadastro", menuCadastroDocumento,
				menuCadastroPessoas, menuCadastroEquipamentos);

		//tsAnalise = new RibbonTask("Análises", menuAnalise);
		tsAnalise = new RibbonTask("Análise", menuAnalise);
		//tsParametros = new RibbonTask("Parâmetros", menuParametros);
		//tsAjuda = new RibbonTask("Ajuda", menuAjuda);

		menuRibbon.addTask(tsCadastro);
		//menuRibbon.addTask(tsAnalise);
		menuRibbon.addTask(tsAnalise);
		//menuRibbon.addTask(tsParametros);
		//menuRibbon.addTask(tsAjuda);

		return menuRibbon;

	}

	ActionListener al = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// create internal frame
			if (e.getSource().equals(cbPessoa)) {
				ManterPessoaUI mpUI = new ManterPessoaUI();
				try {
					// mpUI = new ManterPessoaUI(desktop);
					desktop.add(mpUI.manterBaseUI(desktop));
					mpUI.pack();
					mpUI.setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			if (e.getSource().equals(cbMaquinas)) {
				ManterMaquinaUI meUI = new ManterMaquinaUI();
				try {
					// mpUI = new ManterPessoaUI(desktop);
					desktop.add(meUI.manterBaseUI(desktop));
					meUI.pack();
					meUI.setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			if (e.getSource().equals(cbDocumentos)) {
				ManterDocumentoUI mdUI = new ManterDocumentoUI();
				try {
					// mpUI = new ManterPessoaUI(desktop);
					desktop.add(mdUI.manterBaseUI(desktop));
					mdUI.pack();
					mdUI.setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			if (e.getSource().equals(cbIndice)) {
				ManterIndicesUI miUI = new ManterIndicesUI();
				try {
					desktop.add(miUI.manterBaseUI(desktop));
					miUI.pack();
					miUI.setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			if (e.getSource().equals(cbOcorrencia)) {
				ManterOcorrenciaUI moUI = new ManterOcorrenciaUI();
				try {
					desktop.add(moUI.manterBaseUI(desktop));
					moUI.pack();
					moUI.setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}			
			/*if (e.getSource().equals(cbIndice)) {
				TempoDePermanenciaUI cmUI = new TempoDePermanenciaUI();
				try {
					desktop.add(cmUI.analiseBaseUI(desktop));
					cmUI.pack();
					cmUI.setVisible(true);
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
			}*/
			if ("sair".equals(e.getActionCommand())) {
				System.exit(0);
			}
		}
	};

	public ResizableIcon getResizableIconFromResource(String resource) {
		Image image;
		try {
			image = new ImageIcon(getClass().getResource(resource)).getImage();
		} catch (final Exception e) {
			e.printStackTrace();
			return null;
		}
		final int height = image.getHeight(null);
		final int width = image.getWidth(null);
		final ResizableIcon resizeIcon = ImageWrapperResizableIcon.getIcon(
				image, new Dimension(width, height));
		return resizeIcon;

	}

	// ocupacaoSegClasTarifa
	public void createAndShowGUI() {
		PrincipalClienteRibbonUI myParentFrame = new PrincipalClienteRibbonUI();
		myParentFrame.setVisible(true);
	}
}