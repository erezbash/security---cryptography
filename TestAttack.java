package security;
import java.io.IOException;

import junit.framework.TestCase;

public class TestAttack extends TestCase {
	String chiperTextName = "C:\\Users\\erez\\security\\2\\cipher.txt";
	String ivName = "C:\\Users\\erez\\security\\2\\IV_short.txt";
	String keyName = "C:\\Users\\erez\\security\\2\\key_short.txt";
	
	String chiperTextName4 = "C:\\Users\\erez\\security\\4\\cipher.txt";
	String ivName4 = "C:\\Users\\erez\\security\\4\\IV_short.txt";
	String keyName4 = "C:\\Users\\erez\\security\\4\\key_short.txt";
	
	String knownCipher="C:\\Users\\erez\\security\\3\\known_cipher.txt";
	String knownText="C:\\Users\\erez\\security\\3\\known_plain_long.txt";
	String cipher1="C:\\Users\\erez\\security\\3\\unknown_cipher1.txt";
	String cipher2="C:\\Users\\erez\\security\\3\\unknown_cipher2.txt";
	String ivFileName ="C:\\Users\\erez\\security\\3\\IV_long.txt";
	String longKeyFileName="C:\\Users\\erez\\security\\3\\key_long.txt";
	
	String knownCipher5="C:\\Users\\erez\\security\\5\\known_cipher.txt";
	String knownText5="C:\\Users\\erez\\security\\5\\known_plain_long.txt";
	String cipher15="C:\\Users\\erez\\security\\5\\unknown_cipher1.txt";
	String cipher25="C:\\Users\\erez\\security\\5\\unknown_cipher2.txt";
	String ivFileName5 ="C:\\Users\\erez\\security\\5\\IV_long.txt";
	String longKeyFileName5="C:\\Users\\erez\\security\\5\\key_long.txt";
	
	String knownCipher6="C:\\Users\\erez\\security\\6\\known_cipher.txt";
	String knownText6="C:\\Users\\erez\\security\\6\\known_plain_long.txt";
	String cipher16="C:\\Users\\erez\\security\\6\\unknown_cipher.txt";
	String cipher26="C:\\Users\\erez\\security\\6\\unknown_cipher2.txt";
	String ivFileName6 ="C:\\Users\\erez\\security\\6\\IV_long.txt";
	String longKeyFileName6="C:\\Users\\erez\\security\\6\\key_long.txt";
	
	
	Printer myPrinter;
	byte[] chiperText;
	String key;
	String iv;
	Attack myAttack;
	
	
	byte[] chiperText4;
	String key4;
	String iv4;
	Attack myAttack4;
	public void setUp() throws IOException {
		myAttack = new Attack();
		myPrinter= new Printer();
		chiperText= myPrinter.fileToBytes(chiperTextName);
		key=myPrinter.readKey(keyName);
		iv=new String(myPrinter.fileToBytes(ivName));
		
		chiperText4= myPrinter.fileToBytes(chiperTextName);
		key4=myPrinter.readKey(keyName);
		iv4=new String(myPrinter.fileToBytes(ivName));
	}
	public void test1() throws IOException{
		chiperText= myPrinter.fileToBytes("C:\\Users\\erez\\security\\1\\cipherMsg_example.txt");
		String key2=myPrinter.readKey("C:\\Users\\erez\\security\\1\\key_example.txt");
		String iv2=new String(myPrinter.fileToBytes("C:\\Users\\erez\\security\\1\\IV_example.txt"));
		String keyy = myAttack.chipherTextAttack(chiperText, iv2);
		assertEquals(keyy,key2);
	}
	public void testchipherTextAttack1() throws IOException{
		String key = myAttack.chipherTextAttack(chiperText, iv);
		assertEquals(key,this.key);
	}
	
	public void testchipherTextAttack2() throws IOException{
		String key = myAttack.chipherTextAttack(chiperText4, iv4);
		assertEquals(key,this.key4);
	}
	
	public void testknownTextAttack1() throws IOException{
		String key;
		byte[] _knownText=myPrinter.fileToBytes(knownText);
		byte[] _knowwnCipher=myPrinter.fileToBytes(knownCipher);
		String _iv=new String(myPrinter.fileToBytes(ivFileName));
		String _key = myPrinter.readKey(longKeyFileName);
		key=myAttack.knownTextAttack(_knownText,_knowwnCipher,_iv);
		assertEquals(key,_key);
	}
	
	public void testknownTextAttack2() throws IOException{
		String key;
		byte[] _knownText=myPrinter.fileToBytes(knownText5);
		byte[] _knowwnCipher=myPrinter.fileToBytes(knownCipher5);
		String _iv=new String(myPrinter.fileToBytes(ivFileName5));
		String _key = myPrinter.readKey(longKeyFileName5);
		key=myAttack.knownTextAttack(_knownText,_knowwnCipher,_iv);
		assertEquals(key,_key);
	}
	public void testknownTextAttack3() throws IOException{
		String key;
		byte[] _knownText=myPrinter.fileToBytes(knownText6);
		byte[] _knowwnCipher=myPrinter.fileToBytes(knownCipher6);
		String _iv=new String(myPrinter.fileToBytes(ivFileName6));
		String _key = myPrinter.readKey(longKeyFileName6);
		key=myAttack.knownTextAttack(_knownText,_knowwnCipher,_iv);
		int ok=0;
		for(int i=0;i<key.length();i++){
			if(key.charAt(i)==_key.charAt(i))
				ok++;
		}
		System.out.println(ok+"/52");
		assertEquals(key,_key);
	}
}
