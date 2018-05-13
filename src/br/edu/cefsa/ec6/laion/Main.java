package br.edu.cefsa.ec6.laion;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import br.edu.cefsa.ec6.laion.xml.Xml;

public class Main {
	private static String readInputFile(String path) throws IOException{
		StringBuilder sb = new StringBuilder();
		InputStreamReader inputStream = new InputStreamReader(new FileInputStream(path), Charset.forName("UTF-8"));
        
		int character = 0;
		while ((character = inputStream.read()) != -1) {
			sb.append((char) character);
		}
		inputStream.close();
		return sb.toString();		
	}
	private static void writeInputFile(String path, String text) throws IOException{
		FileOutputStream outputStream = new FileOutputStream(path);
		byte[] data = text.getBytes(Charset.forName("UTF-8"));
		outputStream.write(data);
		outputStream.close();
	}
	
	public static void main(String[] args) {
		try{
			String text = readInputFile(System.getProperty("user.dir") + "/input.xml");
			Xml xml = Xml.parse(text);
			
			String json = xml.toJson();
			writeInputFile(System.getProperty("user.dir") + "/output.json", json);
			System.out.println(json);
		} catch(IOException e){
			e.printStackTrace();
		}
	}
}
