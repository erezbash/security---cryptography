package security;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.io.InputStream;
import java.io.InputStreamReader;


import java.nio.file.Path;

public class Printer {
	
	public String fileToString(String inputFILENAME) throws IOException{
		return new String(fileToBytes(inputFILENAME));
	}
	public byte[] fileToBytes(String fileName) throws IOException{
		Path path = Paths.get(fileName);
		byte[] data = Files.readAllBytes(path);
		return data;
	}
	private  Set<String> WordsToSet(String path) throws IOException {
		Set<String> words = new HashSet<String>();
		InputStream input =  getClass().getResourceAsStream(path);
		BufferedReader br = new BufferedReader(new InputStreamReader(input));
		String wordInEnglish = "";
		while ((wordInEnglish = br.readLine()) != null) {
			words.add(wordInEnglish);
		}
		return words;
	}
	public 	Set<String> readDictionary() throws IOException{
		Set<String> dictionary = new HashSet<String>(10000);
//		Path path = Paths.get("security/words.txt");
//		List<String> data = Files.readAllLines(path);
//		
		Set<String> temp = WordsToSet("words.txt");
		for(String s: temp)
		dictionary.add(s.trim());
		return dictionary;
	}
	public String readKey(String inputFILENAME) throws IOException {
		Path path = Paths.get(inputFILENAME);
		List<String> splitKey=Files.readAllLines(path);
		char[] charKey;
		if(splitKey.size()<=8){
			charKey =new char[8];
			splitKey.forEach(s->{
				charKey[(int)(s.charAt(0)-97)]=s.charAt(2);
			});
		}
		else{
			charKey =new char[52];
			splitKey.forEach(s->{
				if((int)(s.charAt(0))>=97)
					charKey[(int)(s.charAt(0))-97]=s.charAt(2);
				else
				charKey[(int)(s.charAt(0))+26-65]=s.charAt(2);
			});
		}
		return new String(charKey);
	}
	
	public String printKey(String key, String outKey) {
		for(int i=0;i<key.length();i++){
			if(i==key.length()-1)
				outKey=outKey+(char)(i+97)+" "+key.charAt(i);
			else
			outKey=outKey+(char)(i+97)+" "+key.charAt(i)+"\n";
		}
		return outKey;
	}
	public String printKey1(String key, String outKey) {
		for(int i=0;i<key.length();i++){
			if(i>25){
				if(i==key.length()-1)
					outKey=outKey+(char)(i+97-26)+" "+key.charAt(i);
				else
					outKey=outKey+(char)(i+97-26)+" "+key.charAt(i)+"\n";
				continue;
			}
			outKey=outKey+(char)(i+65)+" "+key.charAt(i)+"\n";
		}
		return outKey;
	}
	public void writeToFile(String outputFILENAME,String text) {
		try{
		    PrintWriter writer = new PrintWriter(outputFILENAME, "UTF-8");
		    writer.print(text);
		    writer.close();
		} catch (IOException e) {}
	}
}
