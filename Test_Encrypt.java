package security;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Test;

import junit.framework.TestCase;

public class Test_Encrypt extends TestCase {
	String FILENAME = "C:\\Users\\erez\\security\\plainMsg_example.txt";
	String FILENAME2 = "C:\\Users\\erez\\security\\cipherMsg_example.txt";
	BufferedReader brInput = null;
	FileReader frInput = null;
	String input="";
	BufferedReader brOut = null;
	FileReader frOut = null;
	String output="";
	
	protected void setUp() throws Exception {//read plainText & chiperText
		super.setUp();
		try {
			frInput = new FileReader(FILENAME);
			brInput = new BufferedReader(frInput);
			String sCurrentLine;
			brInput = new BufferedReader(new FileReader(FILENAME));
			while ((sCurrentLine = brInput.readLine()) != null) {
				input = input + sCurrentLine;
				input = input + "\n";
			}
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			frOut = new FileReader(FILENAME2);
			brOut = new BufferedReader(frOut);
			String sCurrentLine1;
			brInput = new BufferedReader(new FileReader(FILENAME));
			while ((sCurrentLine1 = brOut.readLine()) != null) {
				output = output + sCurrentLine1;
				output = output + "\n";
			}
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testEncrypt() {
		System.out.println("testEncrypt-------------------------------------");
		Encrypt myEncrypt = new Encrypt(input,"tyueawtmtq","dhagcfbe");
		compareText(myEncrypt.getChiper(),output);
		assertEquals(true, true);
		System.out.println("");
	}
	@Test
	public void testDecrypt() {
		System.out.println("");
		System.out.println("testDecrypt-------------------------------------");
		Encrypt myEncrypt = new Encrypt(input,"tyueawtmtq","dhagcfbe");
		compareText(input,myEncrypt.decrypt());
		assertEquals(true, true);
	}
	public void compareText(String a,String b){
		int k=a.length();
		for(int i=0;i<k;i=i+1){
			if(a.charAt(i)==b.charAt(i))
				System.out.println((int)(b.charAt(i))+" :ok");
			else{
				System.err.println("A:"+(int)(a.charAt(i))+" B:"+(int)(b.charAt(i)));
			}
		}
	}
}
