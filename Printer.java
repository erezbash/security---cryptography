package security;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;

public class Printer {

	
	public String readFile(String inputFILENAME) throws IOException{

		return new String(read(inputFILENAME));
	}
	public byte[] read(String fileName) throws IOException{
		Path path = Paths.get(fileName);
		byte[] data = Files.readAllBytes(path);
		return data;
	}

	@SuppressWarnings("resource")
	public String readKey(String inputFILENAME) {
		BufferedReader brinput = null;
		FileReader frinput = null;
		String input="";
		try {
			frinput = new FileReader(inputFILENAME);
			brinput = new BufferedReader(frinput);
			String sCurrentLine;
			brinput = new BufferedReader(new FileReader(inputFILENAME));
			while ((sCurrentLine = brinput.readLine()) != null) {
				input = input + sCurrentLine.charAt(sCurrentLine.length()-1);
			}
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		return input;
	}
	public void writeToFile(String outputFILENAME,String text) {
		try{
		    PrintWriter writer = new PrintWriter(outputFILENAME, "UTF-8");
		    writer.print(text);
		    writer.close();
		} catch (IOException e) {
		   // do something
		}
		
	}
}
