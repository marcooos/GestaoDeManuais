package br.com.pcsocial.gestao.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class Conversores {
	private byte[] bytes;
	private File file;
	
	public byte[] convertFileToByte(File f) {
		try {										
			InputStream is = new FileInputStream(f);
			bytes = new byte[(int) f.length()];
			int offset = 0;
			int numRead = 0;
			while (offset < bytes.length
					&& (numRead = is.read(bytes, offset, bytes.length
							- offset)) >= 0) {
				offset += numRead;
			}					
			is.close();
		} catch (FileNotFoundException e2) {
			e2.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return bytes;
	}
	
	public File convertByteToFile(byte[] bytes, String nomeArquivo) {
		FileOutputStream fileOuputStream;
		file = new File(nomeArquivo);
		try {
			fileOuputStream = new FileOutputStream(file);
			fileOuputStream.write(bytes);
		    fileOuputStream.close();		    
		} catch (FileNotFoundException e) {			
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}	    		
		return file;
		
	}

}
