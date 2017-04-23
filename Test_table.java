package security;
//
//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.IOException;
//
//import org.junit.Test;
//
//import junit.framework.TestCase;
//
public class Test_table {}
//	private table myTable1;
//
//	String FILENAME = "C:\\Users\\erez\\security\\plainMsg_example.txt";
//	String FILENAME2 = "C:\\Users\\erez\\security\\cipherMsg_example.txt";
//	BufferedReader brInput = null;
//	FileReader frInput = null;
//	String input="";
//	BufferedReader brOut = null;
//	FileReader frOut = null;
//	String output="";
//	
//	protected void setUp() throws Exception {//read plainText & chiperText
//		super.setUp();
//		try {
//			frInput = new FileReader(FILENAME);
//			brInput = new BufferedReader(frInput);
//			String sCurrentLine;
//			brInput = new BufferedReader(new FileReader(FILENAME));
//			while ((sCurrentLine = brInput.readLine()) != null) {
//				input = input + sCurrentLine;
//				input = input + "\n";
//			}
//		} 
//		catch (IOException e) {
//			e.printStackTrace();
//		}
//		
//		try {
//			frOut = new FileReader(FILENAME2);
//			brOut = new BufferedReader(frOut);
//			String sCurrentLine1;
//			brInput = new BufferedReader(new FileReader(FILENAME));
//			while ((sCurrentLine1 = brOut.readLine()) != null) {
//				output = output + sCurrentLine1;
//				output = output + "\n";
//			}
//		} 
//		catch (IOException e) {
//			e.printStackTrace();
//		}
//		myTable1 = new table("hgfedcba");
//
//	}
//	
//
//	@Test
//	public void test1() {
//		assertEquals(myTable1.replaceRev(myTable1.replace('a')), 'a');
//	}
//	@Test
//	public void test2() {
//		assertEquals(myTable1.replaceRev(myTable1.replace('b')), 'b');
//	}
//	@Test
//	public void test3() {
//		assertEquals(myTable1.replaceRev(myTable1.replace('c')), 'c');
//	}
//	@Test
//	public void test4() {
//		assertEquals(myTable1.replaceRev(myTable1.replace('d')), 'd');
//	}
//	@Test
//	public void test5() {
//		assertEquals(myTable1.replaceRev(myTable1.replace('e')), 'e');
//	}
//	@Test
//	public void test6() {
//		assertEquals(myTable1.replaceRev(myTable1.replace('f')), 'f');
//	}
//	@Test
//	public void test7() {
//		assertEquals(myTable1.replaceRev(myTable1.replace('g')), 'g');
//	}
//	@Test
//	public void test8() {
//		assertEquals(myTable1.replaceRev(myTable1.replace('h')), 'h');
//	}
////	@Test
////	public void testAll1() {
////		String a="asdjkjandnakjnkjdndskjvnskjcnskjcnkaskajndksajdbkabdvkjdnvkjdfndaksjdnkakajcn";
////		String b = new String(myTable1.replaceRevAll(myTable1.replaceAll(a.toCharArray())));
////		assertEquals(b, a);
////	}
////	@Test
////	public void testAll2() {
////		String a=";;dkvldkvnkuvhidshckajdnakjdnqkwjdiwejfoirehgerjnvfvndsmclkdmclkdlwejfdlwefhej";
////		String b = new String(myTable1.replaceRevAll(myTable1.replaceAll(a.toCharArray())));
////		assertEquals(b, a);
////	}
////	@Test
////	public void testInput() {
////		String b = new String(myTable1.replaceRevAll(myTable1.replaceAll(input.toCharArray())));
////		assertEquals(b, input);
////	}
////	@Test
////	public void testOutpu() {
////		String b = new String(myTable1.replaceRevAll(myTable1.replaceAll(output.toCharArray())));
////		assertEquals(b, output);
////	}
//
//}
