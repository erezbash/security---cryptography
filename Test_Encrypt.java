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
//	@Test
//	public void testEncrypt() {
//		//System.out.println("testEncrypt-------------------------------------");
//		Encrypt myEncrypt = new Encrypt(input,"tyueawtmtq","dhagcfbe");
//		//System.out.println(myEncrypt.getChiper());
//		assertEquals(compareText(output,myEncrypt.getChiper()), true);
//		//System.out.println("");
//	}
	@Test
	public void testDecrypt() {
		Encrypt myEncrypt = new Encrypt(input,"tyueawtmtq","dhagcfbe");
		//System.out.println(myEncrypt.getChiper());
		//System.out.println("");
		//System.out.println("testDecrypt-------------------------------------");
		//compareText(input,myEncrypt.decrypt());
		//System.out.println(myEncrypt.decrypt());
		assertEquals(compareText(myEncrypt.decrypt(),input), true);
	}
	public boolean compareText(String a,String b){
		System.out.println("a:"+a.length()+" b:"+b.length());
		boolean bool=true;
		int k=b.length();
		for(int i=0;i<k;i=i+1){
			if(a.charAt(i)==b.charAt(i))
				//System.out.println((int)(b.charAt(i))+" :ok"+" i="+i);
				continue;
			else{
				bool=false;
				//System.out.println("XXXXXXXXXX);
			}
		}
		return bool;
	}
}
