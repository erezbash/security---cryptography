package security;
import java.io.IOException;
import junit.framework.TestCase;

public class TestTable extends TestCase {
	Table myTable;
	public void testTable8() throws IOException {
		myTable = new Table("hgfedcba".getBytes());
		assertEquals(new String(myTable.replaceAll("hgfedcba".getBytes())), "abcdefgh");
	}
	public void testTable8Rev() throws IOException {
		myTable = new Table("hgfedcba".getBytes());
		assertEquals(new String(myTable.replaceRevAll("hgfedcba".getBytes())), "abcdefgh");
	}
	public void testTable8Second() throws IOException {
		myTable = new Table("abchefgd".getBytes());
		assertEquals(new String(myTable.replaceAll("abcdefgh".getBytes())), "abchefgd");
	}
	public void testTable8RevSecond() throws IOException {
		myTable = new Table("abchefgd".getBytes());
		assertEquals(new String(myTable.replaceRevAll("abcdefgh".getBytes())), "abchefgd");
	}
	public void testTable52() throws IOException {
		myTable = new Table("ZYXWVUTSRQPONMLKJIHGFEDCBAzyxwvutsrqponmlkjihgfedcba".getBytes());
		assertEquals(new String(myTable.replaceAll("ZYXWVUTSRQPONMLKJIHGFEDCBAzyxwvutsrqponmlkjihgfedcba".getBytes())), "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ");
	}
	public void testTable52Rev() throws IOException {
		myTable = new Table("ZYXWVUTSRQPONMLKJIHGFEDCBAzyxwvutsrqponmlkjihgfedcba".getBytes());
		assertEquals(new String(myTable.replaceRevAll("ZYXWVUTSRQPONMLKJIHGFEDCBAzyxwvutsrqponmlkjihgfedcba".getBytes())), "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ");
	}
	public void testTable52Second() throws IOException {
		myTable = new Table("abcdefghijZlmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYk".getBytes());
		assertEquals(new String(myTable.replaceAll("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".getBytes())), "abcdefghijZlmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYk");
	}
	public void testTable52RevSecond() throws IOException {
		myTable = new Table("abcdefghijZlmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYk".getBytes());
		assertEquals(new String(myTable.replaceRevAll("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".getBytes())), "abcdefghijZlmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYk");
	}
}
