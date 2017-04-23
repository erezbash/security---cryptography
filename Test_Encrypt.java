package security;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;

import org.junit.Test;

import junit.framework.TestCase;

public class Test_Encrypt extends TestCase {
	String plaintTextName = "C:\\Users\\erez\\security\\plainMsg_example.txt";
	String chiperTextName = "C:\\Users\\erez\\security\\cipherMsg_example.txt";

	
	
	@Test
	public void test() throws IOException {
		Printer myPrinter = new Printer();
		byte[] text=myPrinter.read(plaintTextName);
		byte[] chiper=myPrinter.read(chiperTextName);
		CBC myEncrypt = new CBC();
		assertEquals(compareText(myEncrypt.encrypt(text,"tyueawtmtq","dhagcfbe").getBytes(Charset.forName("UTF-8")),chiper), true);
	}
	
	
	public boolean compareText(byte[] a,byte[] b){
		System.out.println("a:"+a.length+" b:"+b.length);
		boolean bool=true;
		int k=b.length;
		for(int i=0;i<k;i=i+1){
			if(a[i]==b[i])
				continue;
			else{
				bool=false;
			}
		}
		return bool;
	}
}
