package security;
import java.io.IOException;

import org.junit.Test;

import junit.framework.TestCase;

public class Test_CBC extends TestCase {
	String plaintTextName8 = "C:\\Users\\erez\\security\\1\\plainMsg_example.txt";
	String chiperTextName8 = "C:\\Users\\erez\\security\\1\\cipherMsg_example.txt";
	String ivName8 = "C:\\Users\\erez\\security\\1\\IV_example.txt";
	String keyName8 = "C:\\Users\\erez\\security\\1\\key_example.txt";
	
	String plaintTextName52 = "C:\\Users\\erez\\security\\3\\known_plain_long.txt";
	String chiperTextName52 = "C:\\Users\\erez\\security\\3\\known_cipher.txt";
	String ivName52 = "C:\\Users\\erez\\security\\3\\IV_long.txt";
	String keyName52 = "C:\\Users\\erez\\security\\3\\key_long.txt";
	
	
	Printer myPrinter;
	byte[] plaintText8;
	byte[] chiperText8;
	String key8;
	String iv8;
	
	byte[] plaintText52;
	byte[] chiperText52;
	String key52;
	String iv52;
	
	CBC myCBC;
	
	public void setUp() throws IOException {
		myPrinter= new Printer();
		myCBC = new CBC(myPrinter.readDictionary());
		
		
		plaintText8= myPrinter.fileToBytes(plaintTextName8);
		chiperText8= myPrinter.fileToBytes(chiperTextName8);
		key8=myPrinter.readKey(keyName8);
		iv8=new String(myPrinter.fileToBytes(ivName8));
		
		plaintText52=myPrinter.fileToBytes(plaintTextName52);
		chiperText52= myPrinter.fileToBytes(chiperTextName52);
		key52=myPrinter.readKey(keyName52);
		iv52=new String(myPrinter.fileToBytes(ivName52));
	}
	
	@Test
	public void testEncrypt8() throws IOException {
		assertEquals(myCBC.encrypt(plaintText8, iv8, key8), new String(chiperText8));
	}
	@Test
	public void testDecrypt8() throws IOException {
		String res=myCBC.decrypt(chiperText8, iv8, key8);
		assertEquals(res.subSequence(0, res.length()-1), new String(plaintText8));
	}
	@Test
	public void testEncryptDecrypt8() throws IOException {
		String message="hi my name is erez !";
		String iv="1q2w3e4r5t";
		String key= "abdcefhg";
		String enc=myCBC.encrypt(message.getBytes(), iv, key);
		String dec=myCBC.decrypt(enc.getBytes(),iv, key);
		assertEquals(dec, message);
	}
	@Test
	public void testEncrypt52() throws IOException {
		assertEquals(myCBC.encrypt(plaintText52, iv52, key52), new String(chiperText52));
	}
	@Test
	public void testDecrypt52() throws IOException {
		String res=myCBC.decrypt(chiperText52, iv52, key52);
		assertEquals(res, new String(plaintText52));
	}
}
