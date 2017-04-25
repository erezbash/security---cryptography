package security;
import java.io.IOException;


import junit.framework.TestCase;

public class TestAttack extends TestCase {
	String chiperTextName = "C:\\Users\\erez\\security\\2\\cipher.txt";
	String ivName = "C:\\Users\\erez\\security\\2\\IV_short.txt";
	String keyName = "C:\\Users\\erez\\security\\2\\key_short.txt";
	Printer myPrinter;
	byte[] chiperText;
	String key;
	String iv;
	Attack myAttack;
	public void setUp() throws IOException {
		myAttack = new Attack();
		myPrinter= new Printer();
		chiperText= myPrinter.read(chiperTextName);
		key=myPrinter.readKey8(keyName);
		iv=new String(myPrinter.read(ivName));
	}
	public void test1() throws IOException{
		chiperText= myPrinter.read("C:\\Users\\erez\\security\\1\\cipherMsg_example.txt");
		String key2=myPrinter.readKey8("C:\\Users\\erez\\security\\1\\key_example.txt");
		String iv2=new String(myPrinter.read("C:\\Users\\erez\\security\\1\\IV_example.txt"));
		String keyy = myAttack.crack(chiperText, iv2);
		assertEquals(keyy,key2);
	}
	public void test2() throws IOException{
		String key = myAttack.crack(chiperText, iv);
		assertEquals(key,this.key);
	}


}
