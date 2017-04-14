package security;

import static org.junit.Assert.*;

import org.junit.Test;

import junit.framework.TestCase;

public class Test_table extends TestCase {
	
	private table myTable1;
	
	protected void setUp() throws Exception {
		myTable1 = new table("abcdhgef");
		
	}
	@Test
	public void test1() {
		assertEquals(myTable1.replaceRev(myTable1.replace('h')), 'h');
	}
	@Test
	public void test2() {
		char[] a = {'a','c','d','w'};
		String b = new String(myTable1.replaceRevAll(myTable1.replaceAll(a)));
		assertEquals(b, "acdw");
	}

}
