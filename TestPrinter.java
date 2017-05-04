package security;
import java.io.IOException;

import junit.framework.TestCase;

public class TestPrinter extends TestCase {
	String key1 = "C:\\Users\\erez\\security\\1\\key_example.txt";
	String key2 = "C:\\Users\\erez\\security\\2\\key_short.txt";
	String key3 = "C:\\Users\\erez\\security\\3\\key_long.txt";
	Printer myPrinter;
	
	public void setUp() throws IOException {
		myPrinter= new Printer();
	}
	public void testKey1() throws IOException{
		String key = myPrinter.readKey(key1);
		assertEquals(key,"dhagcfbe");
	}
	public void testKey2() throws IOException{
		String key = myPrinter.readKey(key2);
		assertEquals(key,"cgedfhab");
	}
	public void testKey3() throws IOException{
		String key = myPrinter.readKey(key3);
		assertEquals(key,"HITcbmgFRxCQEvoljWdrYAKOPwyMtzLBahSVZJNskXpuUGqfnDei");
	}
	public void testPrintKey(){
		String key="ytMLzaBShZVNJkspXUuqGnfeDiHTIbcgmRFCxEQovjldWYrKAPOw";
		String outkey="";
		outkey=myPrinter.printKey1(key, outkey);
		System.out.println(outkey);
	}


}
