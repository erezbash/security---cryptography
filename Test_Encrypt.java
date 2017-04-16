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
	String coursePlaintText="";
	BufferedReader brOut = null;
	FileReader frOut = null;
	String courseChiperText="";
	
	protected void setUp() throws Exception {//read plainText & chiperText
		super.setUp();
		try {
			frInput = new FileReader(FILENAME);
			brInput = new BufferedReader(frInput);
			String sCurrentLine;
			brInput = new BufferedReader(new FileReader(FILENAME));
			while ((sCurrentLine = brInput.readLine()) != null) {
				coursePlaintText = coursePlaintText + sCurrentLine;
				coursePlaintText = coursePlaintText + "\n";
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
				courseChiperText = courseChiperText + sCurrentLine1;
				courseChiperText = courseChiperText + "\n";
			}
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testEncrypt() {
		Encrypt myEncrypt = new Encrypt(coursePlaintText,"tyueawtmtq","dhagcfbe");
		assertEquals(compareText(courseChiperText,myEncrypt.getChiper()), true);
	}
	@Test
	public void testDecrypt() {
		Encrypt myEncrypt = new Encrypt(coursePlaintText,"tyueawtmtq","dhagcfbe");
		myEncrypt.decrypt();
		assertEquals(compareText(myEncrypt.getDycText(),coursePlaintText), true);
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
