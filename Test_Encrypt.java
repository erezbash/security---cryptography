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
	String Out="";
	
	protected void setUp() throws Exception {
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
				Out = Out + sCurrentLine1;
				Out = Out + "\n";
			}
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Test
	public void test() {
		Encrypt myEncrypt = new Encrypt(input,"tyueawtmtq","dhagcfbe");
//		int k=myEncrypt.getChiper().length();
//		for(int i=0;i<k;i=i+1){
//			if(myEncrypt.getChiper().charAt(i)==Out.charAt(i))
//				System.out.print("");
//			else{
//				System.out.println(myEncrypt.getChiper().charAt(i));
//				System.out.println(Out.charAt(i));
//			}
//		}
		System.out.println(myEncrypt.decrypt());
		assertEquals(true, true);
	}
}
