package security;
import java.io.IOException;

import org.junit.Test;

import junit.framework.TestCase;

public class Test_CBC extends TestCase {
	String plaintTextName = "C:\\Users\\erez\\security\\1\\plainMsg_example.txt";
	String chiperTextName = "C:\\Users\\erez\\security\\1\\cipherMsg_example.txt";
	String ivName = "C:\\Users\\erez\\security\\1\\IV_example.txt";
	String keyName = "C:\\Users\\erez\\security\\1\\key_example.txt";
	Printer myPrinter;
	byte[] plaintText;
	byte[] chiperText;
	String key;
	String iv;
	CBC myCBC;
	
	public void setUp() throws IOException {
		myPrinter= new Printer();
		myCBC = new CBC(10,myPrinter.readDic());
		plaintText= myPrinter.read(plaintTextName);
		chiperText= myPrinter.read(chiperTextName);
		key=myPrinter.readKey8(keyName);
		iv=new String(myPrinter.read(ivName));
	}
	
	@Test
	public void testEncrypt() throws IOException {
		assertEquals(myCBC.encrypt(plaintText, iv, key), new String(chiperText));
	}
	@Test
	public void testDecrypt() throws IOException {
		String res=myCBC.decrypt(chiperText, iv, key);
		assertEquals(res.subSequence(0, res.length()-1), new String(plaintText));
	}
	@Test
	public void testEncryptDecrypt() throws IOException {
		String message="hi my name is erez !";
		String iv="1q2w3e4r5t";
		String key= "abdcefhg";
		String enc=myCBC.encrypt(message.getBytes(), iv, key);
		String dec=myCBC.decrypt(enc.getBytes(),iv, key);
		assertEquals(dec, message);
	}
}
