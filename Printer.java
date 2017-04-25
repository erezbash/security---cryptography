package security;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
	public 	Set<String> readDic() throws IOException{
		Set<String> dictionary = new HashSet<String>(10000);
		Path path = Paths.get("words.txt");
		List<String> data = Files.readAllLines(path);
		for(String s: data)
		dictionary.add(s.trim());
		return dictionary;
	}
	@SuppressWarnings("resource")
	public String readKey8(String inputFILENAME) {
		BufferedReader brinput = null;
		FileReader frinput = null;
		char[] charKey =new char[8];
		String input="";
		try {
			frinput = new FileReader(inputFILENAME);
			brinput = new BufferedReader(frinput);
			String sCurrentLine;
			brinput = new BufferedReader(new FileReader(inputFILENAME));
			while ((sCurrentLine = brinput.readLine()) != null) {
				input = input + sCurrentLine+"\n";
			}
			String[] splitKey = input.split("\n");
			for(int i=0;i<8;i++){
				charKey[(int)(splitKey[i].charAt(0)-97)]=splitKey[i].charAt(2);
			}
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		return new String(charKey);
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
