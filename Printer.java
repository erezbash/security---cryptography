package security;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Printer {

	
	public String readFile(String inputFILENAME){
		BufferedReader brinput = null;
		FileReader frinput = null;
		String input="";
		try {
			frinput = new FileReader(inputFILENAME);
			brinput = new BufferedReader(frinput);
			String sCurrentLine;
			brinput = new BufferedReader(new FileReader(inputFILENAME));
			while ((sCurrentLine = brinput.readLine()) != null) {
				input = input + sCurrentLine;
				input = input + "\n";
			}
			input=input.substring(0, input.length()-1);
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		return input;
	}

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
