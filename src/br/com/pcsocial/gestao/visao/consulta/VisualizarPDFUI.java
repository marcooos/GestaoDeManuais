package br.com.pcsocial.gestao.visao.consulta;


import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import com.sun.pdfview.PDFFile;
import com.sun.pdfview.PDFPage;

import br.com.pcsocial.gestao.visao.base.PDFBaseUI;

public class VisualizarPDFUI extends PDFBaseUI{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private PDFPage page;
	
	public void abrirArquivo(){
		try {
			File file = new File("OS_Arg_2010.pdf");  
	        RandomAccessFile raf = new RandomAccessFile(file, "r");  
	        FileChannel channel = raf.getChannel();  
	        ByteBuffer buf = channel.map(FileChannel.MapMode.READ_ONLY,  
	            0, channel.size());  
	        PDFFile pdffile = new PDFFile(buf);  
	  
	        // show the first page  
	        page = pdffile.getPage(0);
	        //System.out.println(page.toString());	        
		} catch (IOException e){
			System.out.println(e);
		}
		// Criar interface
		createAndShowUI(page);				   		
	}
	
	public String getTitulo() {
		return "Visualizar Arquivos";
	}
	
	public void cancelarSelecao() {
		super.getBuscarBase().dispose();
	}

}
